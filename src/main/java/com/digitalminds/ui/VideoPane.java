package com.digitalminds.ui;

import com.digitalminds.program.database.Video;
import com.digitalminds.ui.modules.CustomGridPane;
import com.digitalminds.ui.modules.Label;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class VideoPane extends VBox
{
	private static Label[]			labels = new Label[2];
	private static WebView			web_view = new WebView();
	private static CustomGridPane[]	grid_panes = new CustomGridPane[5];

	public VideoPane()
	{
		super();
		this.setId("pane");
		this.setAlignment(Pos.TOP_CENTER);
	}

	private void setPadding(Region region, double padding)
	{
		region.setPadding(new Insets(padding));
		this.getChildren().add(region);
	}
	
	public void setPane(Video video)
	{
		for (Label label : VideoPane.labels)
			this.getChildren().remove(label);
		
		for (CustomGridPane grid_pane : VideoPane.grid_panes)
			this.getChildren().remove(grid_pane);
		
		VideoPane.labels[0] = new Label(video.getTitle());
		setPadding(VideoPane.labels[0], 30);
		
		this.getChildren().remove(VideoPane.web_view);
		VideoPane.web_view.getEngine().load(video.getVideoURL());
	    this.getChildren().add(VideoPane.web_view);
		
		VideoPane.labels[1] = new Label(video.getDescription());
		setPadding(VideoPane.labels[1], 30);
		
		for (int i = 0; i < VideoPane.grid_panes.length; i++)
			VideoPane.grid_panes[i] = new CustomGridPane(2, 1);
		
		VideoPane.grid_panes[0].add(new Label("Likes: "), 0, 0);
		VideoPane.grid_panes[0].add(new Label(video.getLikes()), 1, 0);
		VideoPane.grid_panes[1].add(new Label("Subscribers: "), 0, 0);
		VideoPane.grid_panes[1].add(new Label(video.getSubscribers()), 1, 0);
		VideoPane.grid_panes[2].add(new Label("Average Daily Views: "), 0, 0);
		VideoPane.grid_panes[2].add(new Label(video.getAverageDailyViews()), 1, 0);
		VideoPane.grid_panes[3].add(new Label("Published Date: "), 0, 0);
		VideoPane.grid_panes[3].add(new Label(video.getPublishDate()), 1, 0);
		VideoPane.grid_panes[4].add(new Label("Tags: "), 0, 0);
		VideoPane.grid_panes[4].add(new Label(video.getTags()), 1, 0);
		
		for (CustomGridPane grid_pane : VideoPane.grid_panes)
			setPadding(grid_pane, 10);
	}
}
