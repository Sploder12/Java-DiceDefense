package com.sploder12.main.screens;

import java.awt.Color;

import com.sploder12.main.*;



public class Save {
	public static String name = "unnamed";
	public Save(){
		Rend();
		mouse();
	}
	
	public void Rend(){
		Render.g.setColor(Color.white);
		
		Render.g.drawRect(350, 275, 300, 200);
	
		Render.newFont = Render.currentFont.deriveFont(Render.currentFont.getSize() * 4.0F); 
		Render.g.setFont(Render.newFont);
		Render.g.drawString("Save", 435, 400);
		
		Render.newFont = Render.currentFont.deriveFont(Render.currentFont.getSize() * 2.0F); 
		Render.g.setFont(Render.newFont);
		Render.g.drawRect(350,150, 300, 50);
		Render.g.drawString(name, 375, 175);
		Render.g.drawString("File Name", 400, 130);

	}
	
	public void mouse(){
		if(Mouse.moveOver(Mouse.mx,Mouse.my,350,275,300,200)){
			MapMaker.saveas(name);
			Main.state = "MapMakeUI";
		} else if(Mouse.moveOver(Mouse.mx, Mouse.my, 350,150, 300, 50)){
			if(name == "unnamed"){
				name = "";
			}
			Keyboard.curinpt[0] = true;
		} else{
			Keyboard.curinpt[0] = false;
		}
		
	}
}

