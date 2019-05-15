package com.sploder12.main.screens;

import java.awt.Color;

import com.sploder12.main.Mouse;
import com.sploder12.main.Render;

public class Menu {
 public Menu(){
	 Rend();
	 mouse();
 }
 
 private void Rend(){
	 Render.g.setColor(Color.white);
	 Render.newFont = Render.currentFont.deriveFont(Render.currentFont.getSize() * 5.0F); 
	 Render.g.setFont(Render.newFont);
	 Render.g.drawRect(240, 100, 500, 250);
	 Render.g.drawString("Map Maker", 340, 250);
	 Render.g.drawRect(240, 400, 500, 250);
	 Render.g.drawString("Wave Maker", 325, 550);
	 Render.g.drawRect(930, 800, 50, 50);
	 Render.newFont = Render.currentFont.deriveFont(Render.currentFont.getSize() * 1.0F); 
	 Render.g.setFont(Render.newFont);
	 Render.g.drawString("Options", 933, 830);
 }
 
 private void mouse(){
	 if(Mouse.moveOver(Mouse.mx, Mouse.my, 240, 100, 500, 250)){
		 Render.state = "MapMakeUI";
	 } else if(Mouse.moveOver(Mouse.mx, Mouse.my, 240, 400, 500, 250)){
		 Render.state = "WaveMake";
		 //new WaveMaker();
	 }else if(Mouse.moveOver(Mouse.mx, Mouse.my, 930, 800, 50, 50) && Mouse.clicked){
		 Render.state = "Options";
		 try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	 }
 }
}
