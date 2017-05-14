package main.java.com.digitalminds.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import main.java.com.digitalminds.program.database.Video;
import main.java.com.digitalminds.ui.modules.CustomGridPane;

public class VideoPane extends VBox
{
	private static final Label[]			labels					= new Label[2];
	private static final WebView			web_view				= new WebView();
	private static final CustomGridPane[]	grid_panes				= new CustomGridPane[5];
	private static final ScrollPane			description_scroll_pane	= new ScrollPane();
	private static final String				logo_path				= "logo.png";
	
	public VideoPane()
	{
		super();
		this.setAlignment(Pos.TOP_CENTER);
		VideoPane.description_scroll_pane.setId("pane");
		VideoPane.description_scroll_pane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		VideoPane.description_scroll_pane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		VideoPane.description_scroll_pane.setMaxSize(400, 160);
		VideoPane.description_scroll_pane.setMinSize(400, 160);
		VideoPane.description_scroll_pane.setPrefSize(400, 160);
		
		this.setBackground(new Background(new BackgroundImage(new Image(VideoPane.logo_path), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(2048, 1355, false, false, true, false))));
	}

	private void setPadding(Region region, double padding)
	{
		region.setPadding(new Insets(padding));
		this.getChildren().add(region);
	}

	public void setPane(Video video)
	{
		this.setId("pane");
		
		for (Label label : VideoPane.labels)
			this.getChildren().remove(label);

		for (CustomGridPane grid_pane : VideoPane.grid_panes)
			this.getChildren().remove(grid_pane);

		VideoPane.labels[0] = new Label(video.getTitle());
		setPadding(VideoPane.labels[0], 20);

		this.getChildren().remove(VideoPane.web_view);
		VideoPane.web_view.getEngine().load("https://www.youtube.com/embed/" + video.getVideoURL());
		this.getChildren().add(VideoPane.web_view);

		this.getChildren().remove(VideoPane.description_scroll_pane);
		VideoPane.labels[1] = new Label(video.getDescription());
		setPadding(VideoPane.labels[1], 20);
		VideoPane.description_scroll_pane.setContent(VideoPane.labels[1]);
		this.getChildren().add(VideoPane.description_scroll_pane);		

		for (int i = 0; i < VideoPane.grid_panes.length; i++)
			VideoPane.grid_panes[i] = new CustomGridPane(2, 1);

		VideoPane.grid_panes[0].add(new Label("Likes: "), 0, 0);
		VideoPane.grid_panes[0].add(new Label(video.getLikes() + ""), 1, 0);
		VideoPane.grid_panes[1].add(new Label("Subscribers: "), 0, 0);
		VideoPane.grid_panes[1].add(new Label(video.getSubscribers() + ""), 1, 0);
		VideoPane.grid_panes[2].add(new Label("Average Daily Views: "), 0, 0);
		VideoPane.grid_panes[2].add(new Label(video.getAverageDailyViews() + ""), 1, 0);
		VideoPane.grid_panes[3].add(new Label("Published Date: "), 0, 0);
		VideoPane.grid_panes[3].add(new Label(video.getPublishDate() + ""), 1, 0);
		VideoPane.grid_panes[4].add(new Label("Tags: "), 0, 0);
		VideoPane.grid_panes[4].add(new Label(video.getTags()), 1, 0);

		for (CustomGridPane grid_pane : VideoPane.grid_panes)
			setPadding(grid_pane, 5);
	}
}
