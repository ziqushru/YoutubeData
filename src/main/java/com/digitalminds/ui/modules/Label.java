package com.digitalminds.ui.modules;

import java.sql.Timestamp;

import javafx.scene.text.TextAlignment;

public class Label extends javafx.scene.control.Label
{
	public Label(String text)
	{
		super(text);
		this.setTextAlignment(TextAlignment.CENTER);
	}
	
	public Label(int text)
	{
		this(text + "");
	}
	
	public Label(long text)
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
