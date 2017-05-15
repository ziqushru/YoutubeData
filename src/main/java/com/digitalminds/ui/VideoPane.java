package main.java.com.digitalminds.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import main.java.com.digitalminds.program.database.Video;
import main.java.com.digitalminds.ui.modules.CustomGridPane;

public class VideoPane extends VBox
{
	private static ImageView			thumbnail;
	private static final Label[]		labels					= new Label[] { new Label(), new Label() };
	private static final WebView		web_view				= new WebView();
	private static final CustomGridPane	header_grid_pane		= new CustomGridPane(2, 1);
	private static final CustomGridPane	information_grid_pane	= new CustomGridPane(2, 5);
	private static final Label[][]		information_labels		= new Label[5][2];
	private static final ScrollPane		description_scroll_pane	= new ScrollPane();
	private static final ScrollPane		tags_scroll_pane		= new ScrollPane();
	private static final String			logo_path				= "logo.png";

	public VideoPane()
	{
		super();
		this.setAlignment(Pos.TOP_CENTER);
		this.setBackground(new Background(new BackgroundImage(new Image(VideoPane.logo_path), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(2048, 1355, false, false, true, false))));

		VideoPane.labels[1].setPadding(new Insets(10));

		VideoPane.description_scroll_pane.setId("pane");
		VideoPane.description_scroll_pane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		VideoPane.description_scroll_pane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		VideoPane.description_scroll_pane.setMaxSize(550, 130);
		VideoPane.description_scroll_pane.setMinSize(550, 130);
		VideoPane.description_scroll_pane.setPrefSize(550, 130);

		VideoPane.information_grid_pane.setMinHeight(175);

		VideoPane.tags_scroll_pane.setId("pane");
		VideoPane.tags_scroll_pane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		VideoPane.tags_scroll_pane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		VideoPane.tags_scroll_pane.setMinHeight(50);

		VideoPane.information_labels[0][0] = new Label("Likes: ");
		VideoPane.information_labels[1][0] = new Label("Subscribers: ");
		VideoPane.information_labels[2][0] = new Label("Average Daily Views");
		VideoPane.information_labels[3][0] = new Label("Likes: ");
		VideoPane.information_labels[4][0] = new Label("Tags: ");
		VideoPane.tags_scroll_pane.setPadding(new Insets(10.0));

		for (int y = 0; y < VideoPane.information_labels.length; y++)
			VideoPane.information_labels[y][1] = new Label();
	}

	public static void clear(VideoPane video_pane)
	{
		video_pane.setId("");

		for (Label label : VideoPane.labels)
			video_pane.getChildren().remove(label);

		VideoPane.header_grid_pane.getChildren().remove(VideoPane.thumbnail);
		VideoPane.header_grid_pane.getChildren().remove(VideoPane.labels[0]);
		video_pane.getChildren().remove(VideoPane.header_grid_pane);

		for (int y = 0; y < VideoPane.information_labels.length; y++)
			for (int x = 0; x < VideoPane.information_labels[y].length; x++)
				if (x + y * VideoPane.information_labels.length != (VideoPane.information_labels[y].length - 1) + (VideoPane.information_labels.length - 1) * VideoPane.information_labels.length)
					VideoPane.information_grid_pane.getChildren().remove(VideoPane.information_labels[y][x]);
		VideoPane.information_grid_pane.getChildren().remove(VideoPane.tags_scroll_pane);
		video_pane.getChildren().remove(VideoPane.information_grid_pane);

		video_pane.getChildren().remove(VideoPane.web_view);
		video_pane.getChildren().remove(VideoPane.description_scroll_pane);
	}

	public static void setPane(VideoPane video_pane, Video video)
	{
		VideoPane.clear(video_pane);
		video_pane.setId("pane");

		VideoPane.thumbnail = new ImageView(new Image(video.getThumbnailURL(), 145, 81, true, true));
		VideoPane.header_grid_pane.add(VideoPane.thumbnail, 0, 0);
		VideoPane.labels[0] = new Label(video.getTitle());
		VideoPane.header_grid_pane.add(VideoPane.labels[0], 1, 0);
		video_pane.getChildren().add(VideoPane.header_grid_pane);

		VideoPane.web_view.getEngine().load("https://www.youtube.com/embed/" + video.getVideoURL());
		video_pane.getChildren().add(VideoPane.web_view);

		VideoPane.labels[1] = new Label(video.getDescription());
		VideoPane.description_scroll_pane.setContent(VideoPane.labels[1]);
		video_pane.getChildren().add(VideoPane.description_scroll_pane);

		VideoPane.information_labels[0][1].setText(video.getLikes() + "");
		VideoPane.information_labels[1][1].setText(video.getSubscribers() + "");
		VideoPane.information_labels[2][1].setText(video.getAverageDailyViews() + "");
		String timestamp = video.getPublishDate().toString().replace(" ", ", ");
		VideoPane.information_labels[3][1].setText(timestamp.subSequence(0, timestamp.indexOf(".")).toString().replace(":", ";"));
		VideoPane.information_labels[4][1].setText(video.getTags() + "");
		VideoPane.tags_scroll_pane.setContent(VideoPane.information_labels[4][1]);

		for (int y = 0; y < VideoPane.information_labels.length; y++)
			for (int x = 0; x < VideoPane.information_labels[y].length; x++)
				if (x + y * VideoPane.information_labels.length != (VideoPane.information_labels[y].length - 1) + (VideoPane.information_labels.length - 1) * VideoPane.information_labels.length)
					VideoPane.information_grid_pane.add(VideoPane.information_labels[y][x], x, y);
		VideoPane.information_grid_pane.add(VideoPane.tags_scroll_pane, VideoPane.information_labels[0].length - 1, VideoPane.information_labels.length - 1);
		video_pane.getChildren().add(VideoPane.information_grid_pane);
	}
}
