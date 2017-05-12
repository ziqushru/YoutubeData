package com.digitalminds.program.database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Database
{
	private static java.sql.Connection	connection;

	public Database(String database_ip, String database_name, String username, String password)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		
		String connection_url = "jdbc:mysql://" + database_ip + "/" + database_name;
		
//		try
//		{
//			connection = DriverManager.getConnection(connection_url, username, password);
//		}
//		catch (SQLException e) { e.printStackTrace(); }
	}

	public List<Video> selectQuery(String query)
	{
		if (query == null || Database.connection == null) return null;

		try
		{
			ResultSet rs = Database.connection.createStatement().executeQuery(query);
			List<Video> data = new ArrayList<Video>();
			while (rs.next())
			{
				Video video = new Video();
				video.setTitle(rs.getString("title"));
//				video.setVideo(rs.getLong("video"));
//				video.setThumbnail(rs.getString("thumbnail"));
				video.setDescription(rs.getString("description"));
				video.setLikes(rs.getInt("likes"));
				video.setSubscribers(rs.getInt("subscribers"));
				video.setAverageDailyViews(rs.getInt("average_daily_views"));
				video.setPublishDate(rs.getTimestamp("publish_date"));
				video.setTags(rs.getString("tags"));
				data.add(video);
			}
			rs.close();
			return data;
		}
		catch (Exception e) { return null; }
	}
	
	public static void insertVideo(Video video)
	{
		String query = "INSERT INTO videos (thumbnail, video, title, description, likes, subscribers, average_daily_views, publish_date, tags) VALUES ("
				+ video.getThumbnail() + ", "
				+ video.getVideo() + ", '"
				+ video.getTitle() + "', '"
				+ video.getDescription() + "', "
				+ video.getLikes() + ", "
				+ video.getSubscribers() + ", "
				+ video.getAverageDailyViews() + ", "
				+ video.getPublishDate() + ", '"
				+ video.getTags() + "')";
		insertQuery(query);
	}
	
	public static void insertQuery(String query)
	{
		if (query == null || Database.connection == null) return;
		try
		{
			Database.connection.createStatement().executeQuery(query);
		}
		catch (Exception e) { return; }
	}	
}
