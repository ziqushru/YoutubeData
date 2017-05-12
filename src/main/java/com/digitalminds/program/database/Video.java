package com.digitalminds.program.database;

import java.sql.Timestamp;

import javafx.scene.image.Image;

public class Video
{
	private String		title;
	private Video		video;
	private Image		thumbnail;
	private String		description;
	private int			likes;
	private int			subscribers;
	private long		average_daily_views;
	private Timestamp	publish_date;
	private String		tags;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Video getVideo()
	{
		return video;
	}

	public void setVideo(Video video)
	{
		this.video = video;
	}

	public Image getThumbnail()
	{
		return thumbnail;
	}

	public void setThumbnail(Image thumbnail)
	{
		this.thumbnail = thumbnail;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getLikes()
	{
		return likes;
	}

	public void setLikes(int likes)
	{
		this.likes = likes;
	}

	public int getSubscribers()
	{
		return subscribers;
	}

	public void setSubscribers(int subscribers)
	{
		this.subscribers = subscribers;
	}

	public long getAverageDailyViews()
	{
		return average_daily_views;
	}

	public void setAverageDailyViews(long average_daily_views)
	{
		this.average_daily_views = average_daily_views;
	}

	public Timestamp getPublishDate()
	{
		return publish_date;
	}

	public void setPublishDate(Timestamp publish_date)
	{
		this.publish_date = publish_date;
	}

	public String getTags()
	{
		return tags;
	}

	public void setTags(String tags)
	{
		this.tags = tags;
	}
}
