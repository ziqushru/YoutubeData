package com.digitalminds.ui;

import java.util.ArrayList;
import java.util.List;

import com.digitalminds.program.Program;
import com.digitalminds.program.database.Database;
import com.digitalminds.program.database.Video;
import com.digitalminds.ui.modules.Label;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class LeftMenu extends AnchorPane
{
	private static final List<Label>	list		= new ArrayList<Label>();
	public static final ScrollPane		scroll_pane	= new ScrollPane();
	private static final VBox			v_box		= new VBox();

	public LeftMenu()
	{
		super();
		this.setId("pane");
		
		Label label = new Label("                   Videos");
		AnchorPane.setTopAnchor(label, 25.0);
		this.getChildren().add(label);

		LeftMenu.scroll_pane.setId("pane");
		LeftMenu.scroll_pane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		LeftMenu.scroll_pane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scroll_pane.setContent(v_box);
		AnchorPane.setBottomAnchor(scroll_pane, 5.0);
		AnchorPane.setLeftAnchor(scroll_pane, 5.0);
		AnchorPane.setRightAnchor(scroll_pane, 5.0);
		
		this.getChildren().add(scroll_pane);
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
				label.setPadding(new Insets(10));
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
