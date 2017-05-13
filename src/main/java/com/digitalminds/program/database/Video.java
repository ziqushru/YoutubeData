package com.digitalminds.program.database;

import java.sql.Timestamp;

public class Video
{
	private String		title;
	private String		video_url;
	private String		thumbnail_url;
	private String		description;
	private int			likes;
	private int			subscribers;
	private long		average_daily_views;
	private Timestamp	publish_date;
	private String		tags;

	public Video() {}

	public String getThumbnailURL()
	{
		return Video.formater(this.thumbnail_url);
	}

	public void setThumbnailURL(String thumbnail_url)
	{
		this.thumbnail_url = thumbnail_url;
	}
	
	public String getVideoURL()
	{
		return Video.formater(this.video_url);
	}
	
	public void setVideoURL(String video_url)
	{
		this.video_url = video_url;
	}

	public String getTitle()
	{
		return Video.formater(this.title);
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return Video.formater(this.description);
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getLikes()
	{
		return this.likes;
	}

	public void setLikes(int likes)
	{
		this.likes = likes;
	}

	public int getSubscribers()
	{
		return this.subscribers;
	}

	public void setSubscribers(int subscribers)
	{
		this.subscribers = subscribers;
	}

	public long getAverageDailyViews()
	{
		return this.average_daily_views;
	}

	public void setAverageDailyViews(long average_daily_views)
	{
		this.average_daily_views = average_daily_views;
	}

	public Timestamp getPublishDate()
	{
		return this.publish_date;
	}

	public void setPublishDate(Timestamp publish_date)
	{
		this.publish_date = publish_date;
	}

	public String getTags()
	{
		return Video.formater(this.tags);
	}

	public void setTags(String tags)
	{
		this.tags = tags;
	}
	
	public static String formater(String string)
	{
		return string.replace("'", "''");
	}
}
