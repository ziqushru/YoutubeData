package main.java.com.digitalminds.ui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import main.java.com.digitalminds.program.Program;

public class ServerMenu extends Menu
{
	private static final MenuItem	refresh_server	= new MenuItem("Refresh");

	public ServerMenu(MenuBar top_menu, String text)
	{
		super(text);
		ServerMenu.refresh_server.setOnAction(event -> Program.server.getData());
		this.getItems().add(ServerMenu.refresh_server);
		top_menu.getMenus().add(this);
	}
}
