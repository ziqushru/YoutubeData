package com.digitalminds.ui;

import javafx.scene.control.MenuBar;

public class TopMenu extends MenuBar
{
	public TopMenu()
	{
		super();
		new FileMenu(this, "File");
		new ViewMenu(this, "View");
		new ServerMenu(this, "Server");
		new HelpMenu(this, "Help");
		this.setMaxWidth(252);
		this.setMinWidth(252);
		this.setPrefWidth(252);
	}
}
