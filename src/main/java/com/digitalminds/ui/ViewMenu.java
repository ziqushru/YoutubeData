package com.digitalminds.ui;

import com.digitalminds.program.Program;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class ViewMenu extends Menu
{
	private static final MenuItem	refresh_view	= new MenuItem("Refresh");

	public ViewMenu(MenuBar top_menu, String text)
	{
		super(text);
		ViewMenu.refresh_view.setOnAction(event -> Program.left_menu.loadVideosList());
		this.getItems().add(ViewMenu.refresh_view);
		top_menu.getMenus().add(this);
	}
}
