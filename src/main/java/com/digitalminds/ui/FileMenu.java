package com.digitalminds.ui;

import com.digitalminds.program.Program;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class FileMenu extends Menu
{
	private static final MenuItem	new_file	= new MenuItem("New");
	private static final MenuItem	open_file	= new MenuItem("Open");
	private static final MenuItem	save_file	= new MenuItem("Save");
	private static final MenuItem	exit_file	= new MenuItem("Exit");

	public FileMenu(MenuBar top_menu, String text)
	{
		super(text);
		FileMenu.new_file.setOnAction(event -> System.out.println("New"));
		FileMenu.open_file.setOnAction(event -> System.out.println("Open"));
		FileMenu.save_file.setOnAction(event -> System.out.println("Save"));
		FileMenu.exit_file.setOnAction(event -> Program.closeProgram());
		this.getItems().add(FileMenu.new_file);
		this.getItems().add(FileMenu.open_file);
		this.getItems().add(FileMenu.save_file);
		this.getItems().add(FileMenu.exit_file);
		top_menu.getMenus().add(this);
	}
}
