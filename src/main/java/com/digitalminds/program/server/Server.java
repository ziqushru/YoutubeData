package com.digitalminds.program.server;

import java.io.InputStreamReader;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.digitalminds.program.database.Database;
import com.digitalminds.program.database.Video;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;

public class Server implements Runnable
{
	private static final String			APPLICATION_NAME	= "YoutubeData";
//	private static final String			KEY					= "AIzaSyBD3PBJ4FfSdAoJugY_vSoCD2p1GL0c2Js";
	private static final String			REFRESH_TOKEN		= "1/5UDcsjUBaMP8lSQKqc6WIroUJGfQrCjS5W-bLOojl_2YHEyLmbyK44zAWSp0QSi3";

	public YouTube						youtube;
	private static final HttpTransport	HTTP_TRANSPORT		= new NetHttpTransport();
	private static final GsonFactory	GSON_FACTORY		= new GsonFactory();
	private Thread 						thread;
	public static final Database		connection			= new Database("127.0.0.1", "youtubedata", "ziqushru", "Ena1dio2tria3");
	
	public Server()
	{
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");

		org.eclipse.jetty.server.Server jetty_server = new org.eclipse.jetty.server.Server(8080);
		jetty_server.setHandler(context);

		ServletHolder jersey_servlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jersey_servlet.setInitOrder(0);

		jersey_servlet.setInitParameter("jersey.config.server.provider.classnames", Video.class.getCanonicalName());

		try
		{
			Credential credentials = new GoogleCredential.Builder().setTransport(Server.HTTP_TRANSPORT).setJsonFactory(Server.GSON_FACTORY).setClientSecrets(GoogleClientSecrets.load(Server.GSON_FACTORY, new InputStreamReader(Server.class.getResourceAsStream("/client_secret.json")))).build();
			credentials.setRefreshToken(Server.REFRESH_TOKEN);
			youtube = new YouTube.Builder(Server.HTTP_TRANSPORT, Server.GSON_FACTORY, credentials).setApplicationName(Server.APPLICATION_NAME).build();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		String[] channel_ids = { "UCoRh0k2djLqXLnZy1fy0A0w",
				"UC9M8JOr7Fb_Eek_4HH9MjeA",
				"UCINQg0BNDHQvndHRw6bsxnA",
				"UC6m3fpcYxJ6UzrYFdv00Xjw",
				"UCnJnnEztMhpBEZaVORLriFg" };
		
		final ChannelData[] channel_data = new ChannelData[channel_ids.length];
		for (int i = 0; i < channel_data.length; i++)
		{
			channel_data[i] = new ChannelData(this, channel_ids[i]);
			channel_data[i].getData();
		}
	}
	
	public void getData()
	{
		thread = new Thread(this, "Server - GetData");
		thread.start();
	}
	
	public void close()
	{
		if (this.thread != null) try
		{
			this.thread.interrupt();
			this.thread.join();
		}
		catch (InterruptedException e) { e.printStackTrace(); }
	}
}
