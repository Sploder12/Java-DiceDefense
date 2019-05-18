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
	 Render.newFont = Render.currentFont.deriveFont(Render.xScale*(Render.currentFont.getSize() * 2.5F)); 
	 Render.g.setFont(Render.newFont);
	 Render.g.drawRect(Math.round(120*Render.xScale), Math.round(50*Render.xScale), Math.round(250*Render.xScale), Math.round(125*Render.xScale));
	 Render.g.drawString("Map Maker", Math.round(170*Render.xScale), Math.round(125*Render.yScale));
	 Render.g.drawRect(Math.round(120*Render.xScale), Math.round(200*Render.yScale), Math.round(250*Render.xScale), Math.round(125*Render.yScale));
	 Render.g.drawString("Wave Maker", Math.round(163*Render.xScale), Math.round(275*Render.yScale));
	 Render.g.drawRect(Math.round(465*Render.xScale), Math.round(385*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale));
	 Render.newFont = Render.currentFont.deriveFont(Render.xScale*(Render.currentFont.getSize() * 0.5F)); 
	 Render.g.setFont(Render.newFont);
	 Render.g.drawString("Options", Math.round(467*Render.xScale), Math.round(400*Render.yScale));
 }
 
 private void mouse(){
	 if(Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(120*Render.xScale), Math.round(50*Render.yScale), Math.round(250*Render.xScale), Math.round(125*Render.yScale))){
		 Render.state = "MapMakeUI";
	 } else if(Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(120*Render.xScale), Math.round(200*Render.yScale), Math.round(250*Render.xScale), Math.round(125*Render.yScale))){
		 Render.state = "WaveMake";
		 //new WaveMaker();
	 }else if(Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(465*Render.xScale), Math.round(385*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale)) && Mouse.clicked){
		 Render.state = "Options";
		 try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	 }
 }
}
