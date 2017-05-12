package com.digitalminds.ui;

import java.util.ArrayList;
import java.util.List;

import com.digitalminds.program.Program;
import com.digitalminds.program.database.Database;
import com.digitalminds.program.database.Video;
import com.digitalminds.ui.modules.Label;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;

public class LeftMenu extends ToolBar
{
	private static final List<Label>	list		= new ArrayList<Label>();
	public static final ScrollPane		scroll_pane	= new ScrollPane();
	private static final VBox			v_box		= new VBox();

	public LeftMenu()
	{
		super();
		this.setOrientation(Orientation.VERTICAL);
		this.getItems().add(new Label("Videos"));
		LeftMenu.scroll_pane.setPrefSize(250, Program.HEIGHT - 175);
		LeftMenu.scroll_pane.setId("pane");
		LeftMenu.scroll_pane.setHbarPolicy(ScrollBarPolicy.NEVER);
		LeftMenu.scroll_pane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		LeftMenu.v_box.setAlignment(Pos.TOP_CENTER);

		List<Video> videos = new ArrayList<Video>();
		for (int i = 1; i <= 10; i++)
			videos.add(new Video(i));

		this.setVideoList(videos);

		scroll_pane.setContent(v_box);
		this.getItems().add(scroll_pane);
	}

	private void setVideoList(List<Video> videos)
	{		
		if (videos != null)
		{
			for (Label label : list)
				v_box.getChildren().remove(label);
			list.clear();

			for (Video video : videos)
			{
				Label label = new Label(video.getTitle());
				label.setPadding(new Insets(20));
				label.setOnMouseClicked(event -> Program.video_pane.setPane(video));
				v_box.getChildren().add(label);
				list.add(label);
			}
		}
	}

	public void loadVideosList()
	{
		this.setVideoList(Database.selectQuery("SELECT * FROM videos ORDER BY average_daily_views"));
	}
}
