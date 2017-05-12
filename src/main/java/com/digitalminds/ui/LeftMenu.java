package com.digitalminds.ui;

import java.util.List;

import com.digitalminds.program.database.Video;
import com.digitalminds.ui.modules.Label;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class LeftMenu extends ToolBar
{
	public List<Video>		videos;
	public ListView<String>	list	= new ListView<String>();

	public LeftMenu()
	{
		super();
		this.setOrientation(Orientation.VERTICAL);
		this.getItems().add(new Label("Videos"));
		this.setVideoList(null);
	}

	public void setVideoList(List<Video> videos)
	{
		ObservableList<String> items = FXCollections.observableArrayList("Single", "Double", "Suite", "Family App");
		
		VBox box = new VBox();
        VBox.setVgrow(list, Priority.ALWAYS);
        list.setItems(items);
        box.getChildren().addAll(list);
        
        this.getItems().add(box);
	}
}
