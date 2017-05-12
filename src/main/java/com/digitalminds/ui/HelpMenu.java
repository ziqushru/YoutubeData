package com.digitalminds.ui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class HelpMenu extends Menu
{
	private static final MenuItem	about_help = new MenuItem("About");

	public HelpMenu(MenuBar top_menu, String text)
	{
		super(text);
		HelpMenu.about_help.setOnAction(event -> System.out.println("About"));
		this.getItems().add(HelpMenu.about_help);
		top_menu.getMenus().add(this);
	}
}
