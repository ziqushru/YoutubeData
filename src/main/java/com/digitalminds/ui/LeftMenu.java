package main.java.com.digitalminds.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.java.com.digitalminds.program.Program;
import main.java.com.digitalminds.program.database.Database;
import main.java.com.digitalminds.program.database.Video;

public class LeftMenu extends AnchorPane
{
	private static final List<Label>	list		= new ArrayList<Label>();
	private static final ScrollPane		scroll_pane	= new ScrollPane();
	private static final VBox			v_box		= new VBox();

	public LeftMenu()
	{
		super();
		this.setId("pane");
		this.setPrefSize(400, 1337);
		
		Label label = new Label("Videos");
		AnchorPane.setTopAnchor(label, 20.0);
		double x_position = 400 / 2 - label.getText().length() * 5;
		AnchorPane.setLeftAnchor(label, x_position);
		this.getChildren().add(label);

		LeftMenu.scroll_pane.setId("pane");
		LeftMenu.scroll_pane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		LeftMenu.scroll_pane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		LeftMenu.scroll_pane.setContent(LeftMenu.v_box);
		AnchorPane.setTopAnchor(LeftMenu.scroll_pane, 45.0);
		AnchorPane.setBottomAnchor(LeftMenu.scroll_pane, 5.0);
		AnchorPane.setLeftAnchor(LeftMenu.scroll_pane, 5.0);
		AnchorPane.setRightAnchor(LeftMenu.scroll_pane, 5.0);
		
		this.getChildren().add(LeftMenu.scroll_pane);
	}

	private void setVideoList(List<Video> videos)
	{		
		if (videos != null)
		{
			for (Label label : list)
				LeftMenu.v_box.getChildren().remove(label);
			LeftMenu.list.clear();

			for (Video video : videos)
			{
				Label label = new Label(video.getTitle());
				label.setPadding(new Insets(10));
				label.setOnMouseClicked(event -> Program.video_pane.setPane(video));
				LeftMenu.v_box.getChildren().add(label);
				LeftMenu.list.add(label);
			}
		}
	}

	public void loadVideosList()
	{
		this.setVideoList(Database.selectQuery("SELECT * FROM videos ORDER BY average_daily_views"));
	}
}
