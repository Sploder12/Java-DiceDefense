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
		
		Render.g.drawRect(Math.round(25*Render.xScale), Math.round(390*Render.yScale), Math.round(100*Render.xScale), Math.round(38*Render.yScale));
		Render.g.drawString("Save As", Math.round(40*Render.xScale), Math.round(413*Render.yScale));
		
		Render.g.drawRect(Math.round(263*Render.xScale), Math.round(390*Render.yScale), Math.round(100*Render.xScale), Math.round(38*Render.yScale));
		Render.g.drawString("Load", Math.round(290*Render.xScale), Math.round(413*Render.yScale));
		Render.g.drawRect(Math.round(388*Render.xScale), Math.round(50*Render.yScale), Math.round(50*Render.xScale), Math.round(25*Render.yScale));
		Render.g.drawString("Prev Tile", Math.round(390*Render.xScale), Math.round(68*Render.yScale));
		Render.g.drawRect(Math.round(443*Render.xScale), Math.round(50*Render.yScale), Math.round(50*Render.xScale), Math.round(25*Render.yScale));
		Render.g.drawString("Next Tile", Math.round(445*Render.xScale), Math.round(68*Render.yScale));
		Render.g.drawString("Tile Selection", Math.round(413*Render.xScale), Math.round(45*Render.yScale));
		Render.g.drawString("Current Tile", Math.round(413*Render.xScale), Math.round(100*Render.yScale));
		//Render.g.drawRect(Math.round(433*Render.xScale), Math.round(108*Render.yScale), Math.round(16*Render.xScale), Math.round(16*Render.yScale)); //Where the Tile is rendered
		Render.g.drawRect(Math.round(399*Render.xScale), Math.round(250*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale));
		Render.g.drawRect(Math.round(429*Render.xScale), Math.round(250*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.xScale));
		Render.g.drawRect(Math.round(459*Render.xScale), Math.round(250*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale));
		Render.g.drawString("Start",Math.round(400*Render.xScale),Math.round(245*Render.yScale));
		Render.g.drawString("Middle",Math.round(425*Render.xScale),Math.round(245*Render.yScale));
		Render.g.drawString("End",Math.round(463*Render.xScale),Math.round(245*Render.yScale));
		Render.g.drawRect(Math.round(429*Render.xScale), Math.round(300*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale));
		Render.g.drawString("Remove",Math.round(420*Render.xScale),Math.round(295*Render.yScale));
		Render.g.drawRect(Math.round(429*Render.xScale), Math.round(200*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale));
		Render.g.drawString("Main Menu",Math.round(410*Render.xScale),Math.round(410*Render.yScale));
		Render.g.drawRect(Math.round(388*Render.xScale), Math.round(390*Render.yScale), Math.round(100*Render.xScale), Math.round(38*Render.yScale));
		Render.g.drawString("Place Enemy Paths", Math.round(395*Render.xScale), Math.round(195*Render.yScale));
		if(MapMaker.placingpaths){
			Render.g.fillRect(Math.round(429*Render.xScale), Math.round(200*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale));
		}
		if(MapMaker.selectedPath != 3){
			Render.g.fillRect(Math.round((30*MapMaker.selectedPath + 399)*Render.xScale), Math.round(250*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale));
		}else{
			Render.g.fillRect(Math.round(429*Render.xScale), Math.round(300*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale));
		}
	}
	
	public void mouse(){
		for(byte x = 0; x<3; x++){
			if(Mouse.moveOver(Mouse.mx, Mouse.my, Math.round((30*x +399)*Render.xScale), Math.round(250*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale))){
				MapMaker.selectedPath = x;
			}
		}
		if(Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(429*Render.xScale), Math.round(300*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale))){
			MapMaker.selectedPath = 3;
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(429*Render.xScale), Math.round(200*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale)) && Mouse.clicked){
			MapMaker.placingpaths = !MapMaker.placingpaths;
			try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if(Mouse.moveOver(Mouse.mx,Mouse.my,Math.round(13*Render.xScale),Math.round(390*Render.yScale),Math.round(100*Render.xScale),Math.round(38*Render.yScale))){
			Render.state = "Save";
		} else if(Mouse.moveOver(Mouse.mx,Mouse.my,Math.round(263*Render.xScale),Math.round(390*Render.yScale),Math.round(100*Render.xScale),Math.round(38*Render.yScale))){
			Render.state = "Load";
		} else if(Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(443*Render.xScale), Math.round(50*Render.xScale), Math.round(50*Render.xScale), Math.round(25*Render.yScale)) && Mouse.clicked){
				MapMaker.selectedTile = MapMaker.selectedTile + 1;
				try {
					Thread.sleep(120);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			 if (MapMaker.selectedTile > 96){
				MapMaker.selectedTile = 0;
			}
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(390*Render.xScale), Math.round(50*Render.yScale), Math.round(50*Render.xScale), Math.round(25*Render.yScale))&&Mouse.clicked){
			MapMaker.selectedTile = MapMaker.selectedTile -1;
			try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(MapMaker.selectedTile < 0){
				MapMaker.selectedTile = 96;
			}
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(388*Render.xScale), Math.round(390*Render.yScale), Math.round(100*Render.xScale), Math.round(38*Render.yScale))){
			Render.state = "Menu";
		}
	}
}
