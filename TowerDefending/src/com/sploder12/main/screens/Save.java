package com.sploder12.main.screens;

import java.awt.Color;

import com.sploder12.main.*;



public class Save {
	public Save(){
		Rend();
		mouse();
	}
	
	public void Rend(){
		Render.g.setColor(Color.white);
		
		Render.g.drawRect(350, 275, 300, 300);
		Render.g.drawString("Save", 475, 400);

	}
	
	public void mouse(){
		if(Mouse.moveOver(Mouse.mx,Mouse.my,350,275,300,300)){
			MapMaker.saveas();
			Main.state = "MapMakeUI";
		}
		
	}
}

