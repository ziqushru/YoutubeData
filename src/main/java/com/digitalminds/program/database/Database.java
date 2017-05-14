package main.java.com.digitalminds.program.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database
{
	private static java.sql.Connection	connection;

	public Database(String database_ip, String database_name, String username, String password)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (InstantiationException e) { e.printStackTrace();	}
		catch (IllegalAccessException e) { e.printStackTrace();	}
		
		String connection_url = "jdbc:mysql://" + database_ip + "/" + database_name;
		
		try
		{
			connection = DriverManager.getConnection(connection_url, username, password);
		}
		catch (SQLException e) { e.printStackTrace(); }
	}

	public static void deleteData(String table)
	{
		String delete_query = "DELETE FROM " + table;
		String reset_auto_increment_query = "ALTER TABLE videos AUTO_INCREMENT = 1";
		if (Database.connection == null) return;
		try
		{
			Database.connection.createStatement().executeUpdate(delete_query);
			Database.connection.createStatement().executeUpdate(reset_auto_increment_query);
		}
		catch (SQLException e) { e.printStackTrace(); }
		
	}
	
	public static List<Video> selectQuery(String query)
	{
		if (query == null || Database.connection == null) return null;

		try
		{
			ResultSet rs = Database.connection.createStatement().executeQuery(query);
			List<Video> data = new ArrayList<Video>();
			while (rs.next())
			{
				Video video = new Video();
				video.setThumbnailURL(rs.getString("thumbnail_url"));
				video.setVideoURL(rs.getString("video_url"));
				video.setTitle(rs.getString("title"));
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
		catch (SQLException e) { e.printStackTrace(); return null;}
	}
	
	public static void insertVideo(Video video)
	{
		String query = "INSERT INTO videos (thumbnail_url, video_url, title, description, likes, subscribers, average_daily_views, publish_date, tags) VALUES ('"
				+ video.getThumbnailURL() + "', '"
				+ video.getVideoURL() + "', '"
				+ video.getTitle() + "', '"
				+ video.getDescription() + "', "
				+ video.getLikes() + ", "
				+ video.getSubscribers() + ", "
				+ video.getAverageDailyViews() + ", '"
				+ video.getPublishDate() + "', '"
				+ video.getTags() + "')";
		Database.insertQuery(query);
	}
	
	public static void insertQuery(String query)
	{
		if (query == null || Database.connection == null) return;
		try
		{
			Database.connection.createStatement().executeUpdate(query);
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	public static void close()
	{
		if (Database.connection == null) return;
		try
		{
			Database.connection.close();
		}
		catch (SQLException e) { e.printStackTrace(); }
	}
}
