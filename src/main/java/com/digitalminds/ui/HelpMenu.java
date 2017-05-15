package main.java.com.digitalminds.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.com.digitalminds.program.Program;

public class HelpMenu extends Menu
{
	private static final MenuItem	about_help		= new MenuItem("About");
	private static final MenuItem	how_to_use_help	= new MenuItem("How to Use");

	public HelpMenu(String text)
	{
		super(text);
		HelpMenu.about_help.setOnAction(event ->
		{
			VBox window = new VBox();
			window.setId("pane");
			window.setAlignment(Pos.CENTER);
			window.setPadding(new Insets(30.0));

			Label[] labels = new Label[4];
			labels[0] = new Label("YoutubeData is a YouTube aggregator");
			labels[1] = new Label("It uses the YouTube Data API to get the information");
			labels[2] = new Label("It retrieves data and displays them on a Java FX interface");
			labels[3] = new Label("Developed by Jason Koutoulias");

			for (Label label : labels)
			{
				label.setPadding(new Insets(10.0));
				window.getChildren().add(label);
			}

			Stage stage = new Stage();
			stage.setTitle("About");
			stage.getIcons().add(new Image(Program.icon_logo_path));
			stage.setResizable(false);
			Scene scene = new Scene(window, 600, 225);
			scene.getStylesheets().add(Program.class.getResource(Program.css_path).toExternalForm());
			stage.setScene(scene);
			stage.show();
		});
		this.getItems().add(HelpMenu.about_help);
		
		HelpMenu.how_to_use_help.setOnAction(event ->
		{
			VBox window = new VBox();
			window.setId("pane");
			window.setAlignment(Pos.CENTER);
			window.setPadding(new Insets(30.0));

			Label[] labels = new Label[7];
			labels[0] = new Label("View -> Refresh ; Loads the stored data from the database and displays them as a list");
			labels[1] = new Label("View -> Clean -> Videos List ; Clears the Videos list on the left");
			labels[2] = new Label("View -> Clean -> Video Pane ; Clears the Video Pane on the center");
			labels[3] = new Label("View -> Clean -> All ; Clears Videos list and Video Pane");
			labels[4] = new Label("Server -> Refresh ; Collects from YouTube the data and populates the database with them");
			labels[5] = new Label("Help -> About ; General information about this application");
			labels[6] = new Label("Help-> How to Use ; Explanation of UI");
			
			for (Label label : labels)
			{
				label.setPadding(new Insets(10.0));
				window.getChildren().add(label);
			}

			Stage stage = new Stage();
			stage.setTitle("How to Use");
			stage.getIcons().add(new Image(Program.icon_logo_path));
			stage.setResizable(false);
			Scene scene = new Scene(window, 735, 300);
			scene.getStylesheets().add(Program.class.getResource(Program.css_path).toExternalForm());
			stage.setScene(scene);
			stage.show();
		});
		this.getItems().add(HelpMenu.how_to_use_help);
	}
}
