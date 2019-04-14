package com.sploder12.main.screens;

import java.awt.Color;

import com.sploder12.main.*;

public class MapMakeUI {

	public MapMakeUI(){
		Rend();
		mouse();
	}
	
	public void Rend(){
		Render.g.setColor(Color.white);
		
		Render.g.drawRect(25, 780, 200, 75);
		Render.g.drawString("Save As", 80, 825);
		
		Render.g.drawRect(525, 780, 200, 75);
		Render.g.drawString("Load", 580, 825);
	}
	
	public void mouse(){
		if(Mouse.moveOver(Mouse.mx,Mouse.my,25,780,200,75)){
			Main.state = "Save";
		} else if(Mouse.moveOver(Mouse.mx,Mouse.my,525,780,200,75)){
			Main.state = "Load";
			//MapMaker.load("test");
		}
	}
}
