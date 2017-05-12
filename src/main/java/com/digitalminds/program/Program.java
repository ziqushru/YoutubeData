package com.digitalminds.program;

import java.util.Optional;

import com.digitalminds.program.server.Server;
import com.digitalminds.ui.LeftMenu;
import com.digitalminds.ui.TopMenu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Program extends Application
{
	private static final String			TITLE									= "YoutubeData";
	private static int					WIDTH									= 1024;
	private static int					HEIGHT									= 720;

	public static Stage					window;
	private static final BorderPane		main_border_pane						= new BorderPane();
	private static final String			logo_path								= "logo";
	private static final TopMenu		top_menu								= new TopMenu();
	public static final LeftMenu		left_menu								= new LeftMenu();
	
	public static final Server			server									= new Server();
	
	@Override
	public void start(Stage window) throws Exception
	{
		Program.main_border_pane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
		Program.main_border_pane.setTop(Program.top_menu);
		Program.main_border_pane.setLeft(Program.left_menu);
		
		Program.window = window;
		Program.setUserAgentStylesheet(Program.STYLESHEET_CASPIAN);
		Program.window.setScene(new Scene(Program.main_border_pane, Program.WIDTH, Program.HEIGHT));
		Program.window.getScene().getStylesheets().add(getClass().getResource("/stylesheets/application.css").toExternalForm());
//		Program.window.getIcons().add(new Image(Program.logo_path + ".png"));
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
