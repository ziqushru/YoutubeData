package main.java.com.digitalminds.ui;

import javafx.scene.control.MenuBar;

public class TopMenu extends MenuBar
{
	public TopMenu()
	{
		super();
		this.getMenus().add(new ViewMenu("View"));
		this.getMenus().add(new ServerMenu("Server"));
		this.getMenus().add(new HelpMenu("Help"));
		this.setMaxWidth(207);
		this.setMinWidth(207);
		this.setPrefWidth(207);
	}
}
