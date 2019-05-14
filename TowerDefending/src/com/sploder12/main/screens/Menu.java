package com.sploder12.main.screens;

import java.awt.Color;

import com.sploder12.main.Mouse;
import com.sploder12.main.Render;
import com.sploder12.main.WaveMaker;

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
 }
 
 private void mouse(){
	 if(Mouse.moveOver(Mouse.mx, Mouse.my, 240, 100, 500, 250)){
		 Render.state = "MapMakeUI";
	 } else if(Mouse.moveOver(Mouse.mx, Mouse.my, 240, 400, 500, 250)){
		 Render.state = "WaveMake";
		 new WaveMaker();
	 }
 }
}
