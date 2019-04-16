package com.sploder12.main.screens;

import java.awt.Color;

import com.sploder12.main.*;

public class Load {
	public static String name = "";
	
	public Load(){
		Rend();
		mouse();
	}
	
	public void Rend(){
		Render.g.setColor(Color.white);
		Render.g.drawRect(350, 275, 300, 200);
		Render.newFont = Render.currentFont.deriveFont(Render.currentFont.getSize() * 4.0F); 
		Render.g.setFont(Render.newFont);
		Render.g.drawString("Load", 435, 400);
		Render.newFont = Render.currentFont.deriveFont(Render.currentFont.getSize() * 2.0F); 
		Render.g.setFont(Render.newFont);
		Render.g.drawRect(350,150, 300, 50);
		Render.g.drawString(name, 375, 175);
		Render.g.drawString("File Name", 400, 130);
		Render.g.drawRect(50, 50, 150, 100);
		Render.g.drawString("Back", 85, 100);
	}
	
	public void mouse(){
		if(Mouse.moveOver(Mouse.mx,Mouse.my,350,275,300,200)){
			MapMaker.load(name);
			Keyboard.curinpt[1] = false;
			Render.state = "MapMakeUI";
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my, 50, 50, 150, 100)){
			Render.state = "MapMakeUI";
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my, 350,150, 300, 50)){
			Keyboard.curinpt[1] = true;
		} else{
			Keyboard.curinpt[1] = false;
		}
		
	}
}
