package main.java.com.digitalminds.program.server;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;

import main.java.com.digitalminds.program.Program;
import main.java.com.digitalminds.program.database.Database;
import main.java.com.digitalminds.program.database.Video;

public class ChannelData implements Runnable
{
	private final String	id;
	private final Thread	thread;
	
	public ChannelData(String id)
	{
		this.id = id;
		this.thread = new Thread(this, "Server Thread - Channel ID: " + this.id);
	}
	
	public void getData()
	{
		thread.start();
	}
	
	@Override
	public void run()
	{
		try
		{			
			ChannelListResponse channel_list_response = Program.server.youtube.channels().
					list("statistics, contentDetails").
					setId(this.id).
					setFields("items(contentDetails/relatedPlaylists/uploads, statistics/subscriberCount),nextPageToken,pageInfo").
					execute();
			
			Channel channel = channel_list_response.getItems().get(0);
			
			if (channel != null) {
				String upload_playlist_id = channel.getContentDetails().getRelatedPlaylists().getUploads();
				
				List<PlaylistItem> playlist_items = new ArrayList<PlaylistItem>();
				
				YouTube.PlaylistItems.List playlist_item_request = Program.server.youtube.playlistItems().
						list("snippet,contentDetails").
						setPlaylistId(upload_playlist_id);
				
				String nextToken = "";
				do
				{
					playlist_item_request.setPageToken(nextToken);
					PlaylistItemListResponse playlist_item_result = playlist_item_request.execute();
					
					playlist_items.addAll(playlist_item_result.getItems());
					
					nextToken = playlist_item_result.getNextPageToken();
				} while (nextToken != null);
				
				long today = System.currentTimeMillis();
				
				for (PlaylistItem playlist_item : playlist_items)
				{
					long published_at = playlist_item.getContentDetails().getVideoPublishedAt().getValue();
					long time_delta = today - published_at;
					time_delta = time_delta / 1000 / 60 / 60 / 24;
					com.google.api.services.youtube.model.Video youtube_video = Program.server.youtube.videos().
					list("snippet,statistics").
					setFields("items(id, snippet/thumbnails, snippet/title, snippet/description, snippet/tags, statistics/viewCount, statistics/likeCount)").
					setId(playlist_item.getContentDetails().getVideoId()).
					execute().
					getItems().
					get(0);
					long views = youtube_video.getStatistics().getViewCount().longValue();
					long average_daily_views = 0;
					if (time_delta != 0)
						average_daily_views = views / time_delta;
					
					if (average_daily_views > 5000)
					{
						Video video = new Video();
						video.setThumbnailURL(youtube_video.getSnippet().getThumbnails().getMedium().getUrl());
						video.setVideoURL(youtube_video.getId());
						video.setTitle(youtube_video.getSnippet().getTitle());
						video.setDescription(youtube_video.getSnippet().getDescription());
						video.setLikes(youtube_video.getStatistics().getLikeCount().intValue());
						video.setSubscribers(channel.getStatistics().getSubscriberCount().intValue());
						video.setAverageDailyViews(average_daily_views);
						video.setPublishDate(new Timestamp(published_at));
						video.setTags(youtube_video.getSnippet().getTags().toString());
						
						Database.insertVideo(video);
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
