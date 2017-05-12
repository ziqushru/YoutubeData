package com.digitalminds.ui.modules;

import java.sql.Timestamp;

import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Label extends javafx.scene.control.Label
{
	public Label(String text)
	{
		super(text);
		this.setFont(Font.font("Alcubierre"));
		this.setTextAlignment(TextAlignment.CENTER);
//		this.setContentDisplay(ContentDisplay.CENTER);
	}
	
	public Label(int text)
	{
		this(text + "");
	}
	
	public Label(double text)
	{
		this(text + "");
	}
	
	public Label(Timestamp text)
	{
		this(text + "");
	}
}
