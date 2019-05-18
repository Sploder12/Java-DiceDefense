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
		Render.g.drawRect(Math.round(175*Render.xScale), Math.round(138*Render.yScale), Math.round(150*Render.xScale), Math.round(100*Render.yScale));
		Render.newFont = Render.currentFont.deriveFont(Math.round(Render.currentFont.getSize() * 2.0F)*Render.xScale); 
		Render.g.setFont(Render.newFont);
		Render.g.drawString("Load", Math.round(218*Render.xScale), Math.round(200*Render.yScale));
		Render.newFont = Render.currentFont.deriveFont(Math.round(Render.currentFont.getSize() * 1.0F)*Render.xScale); 
		Render.g.setFont(Render.newFont);
		Render.g.drawRect(Math.round(175*Render.xScale),Math.round(75*Render.yScale), Math.round(150*Render.xScale), Math.round(25*Render.yScale));
		Render.g.drawString(name, Math.round(188*Render.xScale), Math.round(88*Render.yScale));
		Render.g.drawString("File Name", Math.round(200*Render.xScale), Math.round(65*Render.yScale));
		Render.g.drawRect(Math.round(25*Render.xScale), Math.round(25*Render.yScale), Math.round(75*Render.xScale), Math.round(50*Render.yScale));
		Render.g.drawString("Back", Math.round(43*Render.xScale), Math.round(50*Render.yScale));
	}
	
	public void mouse(){
		if(Mouse.moveOver(Mouse.mx,Mouse.my,Math.round(175*Render.xScale),Math.round(138*Render.yScale),Math.round(150*Render.xScale),Math.round(100*Render.yScale))){
			MapMaker.load(name);
			Keyboard.curinpt[1] = false;
			Render.state = "MapMakeUI";
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(25*Render.xScale), Math.round(25*Render.yScale), Math.round(75*Render.xScale), Math.round(50*Render.yScale))){
			Keyboard.curinpt[1] = false;
			Render.state = "MapMakeUI";
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(175*Render.xScale),Math.round(75*Render.yScale), Math.round(150*Render.xScale), Math.round(25*Render.yScale))){
			Keyboard.curinpt[1] = true;	
		}
	}
}
