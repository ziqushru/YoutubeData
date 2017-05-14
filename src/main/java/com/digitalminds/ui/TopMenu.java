package main.java.com.digitalminds.ui;

import javafx.scene.control.MenuBar;

public class TopMenu extends MenuBar
{
	public TopMenu()
	{
		super();
		new ViewMenu(this, "View");
		new ServerMenu(this, "Server");
		new HelpMenu(this, "Help");
		this.setMaxWidth(207);
		this.setMinWidth(207);
		this.setPrefWidth(207);
	}
}
