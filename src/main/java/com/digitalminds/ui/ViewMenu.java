package main.java.com.digitalminds.ui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import main.java.com.digitalminds.program.Program;

public class ViewMenu extends Menu
{
	private static final MenuItem	refresh_view			= new MenuItem("Refresh");
	private static final Menu		clear_view				= new Menu("Clear");
	private static final MenuItem	clear_video_list_view	= new MenuItem("Videos List");
	private static final MenuItem	clear_video_pane_view	= new MenuItem("Video Pane");
	private static final MenuItem	clear_all_view			= new MenuItem("All");

	public ViewMenu(String text)
	{
		super(text);
		ViewMenu.refresh_view.setOnAction(event ->
		{
			VideoPane.clear(Program.video_pane);
			LeftMenu.loadVideosList();
		});
		this.getItems().add(ViewMenu.refresh_view);

		ViewMenu.clear_video_list_view.setOnAction(event -> LeftMenu.clearList());
		ViewMenu.clear_video_pane_view.setOnAction(event -> VideoPane.clear(Program.video_pane));
		ViewMenu.clear_all_view.setOnAction(event ->
		{
			LeftMenu.clearList();
			VideoPane.clear(Program.video_pane);
		});
		ViewMenu.clear_view.getItems().add(ViewMenu.clear_video_list_view);
		ViewMenu.clear_view.getItems().add(ViewMenu.clear_video_pane_view);
		ViewMenu.clear_view.getItems().add(ViewMenu.clear_all_view);
		this.getItems().add(ViewMenu.clear_view);
	}
}
