package main.java.com.digitalminds.program;

import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.com.digitalminds.program.database.Database;
import main.java.com.digitalminds.program.server.Server;
import main.java.com.digitalminds.ui.LeftMenu;
import main.java.com.digitalminds.ui.TopMenu;
import main.java.com.digitalminds.ui.VideoPane;

public class Program extends Application
{
	private static final String			TITLE									= "YoutubeData";
	private static int					WIDTH									= 1300;
	public static int					HEIGHT									= 820;

	public static Stage					window;
	private static final BorderPane		main_border_pane						= new BorderPane();
	private static final TopMenu		top_menu								= new TopMenu();
	public static final LeftMenu		left_menu								= new LeftMenu();
	public static final VideoPane		video_pane								= new VideoPane();
	public static final String			icon_logo_path							= "icon_logo.png";
	public static final String			css_path								= "/stylesheets/application.css";
	
	public static final Server			server									= new Server();
	
	@Override
	public void start(Stage window) throws Exception
	{
		Program.main_border_pane.setId("pane");
		Program.main_border_pane.setTop(Program.top_menu);
		Program.main_border_pane.setLeft(Program.left_menu);
		Program.main_border_pane.setCenter(Program.video_pane);
		
		Program.window = window;
		Program.setUserAgentStylesheet(Program.STYLESHEET_CASPIAN);
		Program.window.setScene(new Scene(Program.main_border_pane, Program.WIDTH, Program.HEIGHT));
		Program.window.getScene().getStylesheets().add(getClass().getResource(Program.css_path).toExternalForm());
		Program.window.getIcons().add(new Image(Program.icon_logo_path));
		Program.window.setTitle(Program.TITLE);
		Program.window.widthProperty().addListener((ObservableValue<? extends Number> observable_value, Number old_width, Number new_width) ->
			Program.WIDTH = new_width.intValue());
		Program.window.heightProperty().addListener((ObservableValue<? extends Number> observable_value, Number old_height, Number new_height) ->
			Program.HEIGHT = new_height.intValue());
		Program.window.setOnCloseRequest(event ->
		{
			event.consume();
			Program.closeProgram();
		});
		Program.window.show();
	}

	public static void closeProgram()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(Program.window);
		alert.setTitle("Exit Confirmation");
		alert.setHeaderText("Your program is about to exit, be sure to save your data");
		alert.setContentText("Are you sure you want to exit ?");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK)
		{
			Database.close();
			Program.server.close();
			Program.window.close();
			Platform.exit();
		}
	}

	public static void main(String... args)
	{
		launch(args);
	}
}
