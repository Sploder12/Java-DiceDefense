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
		Render.g.drawRect(775, 100, 100, 50);
		Render.g.drawString("Prev Tile", 780, 135);
		Render.g.drawRect(885, 100, 100, 50);
		Render.g.drawString("Next Tile", 890, 135);
		Render.g.drawString("Tile Selection", 825, 90);
		Render.g.drawString("Current Tile", 825, 200);
		Render.g.drawRect(865, 215, 32, 32); //Where the Tile is rendered
		Render.g.drawRect(798, 500, 50, 50);
		Render.g.drawRect(858, 500, 50, 50);
		Render.g.drawRect(918, 500, 50, 50);
		Render.g.drawString("Start",800,490);
		Render.g.drawString("Middle",850,490);
		Render.g.drawString("End",925,490);
		Render.g.drawRect(858, 600, 50, 50);
		Render.g.drawString("Remove",840,590);
		Render.g.drawRect(858, 400, 50, 50);
		Render.g.drawString("Place Enemy Paths", 790, 390);
		if(MapMaker.placingpaths){
			Render.g.fillRect(858, 400, 50, 50);
		}
		if(MapMaker.selectedPath != 3){
			Render.g.fillRect((60*MapMaker.selectedPath + 798), 500, 50, 50);
		}else{
			Render.g.fillRect(858, 600, 50, 50);
		}
		
		
	}
	
	public void mouse(){
		for(byte x = 0; x<3; x++){
			if(Mouse.moveOver(Mouse.mx, Mouse.my, (60*x +798), 500, 50, 50)){
				MapMaker.selectedPath = x;
			}
		}
		if(Mouse.moveOver(Mouse.mx, Mouse.my, 858, 600, 50, 50)){
			MapMaker.selectedPath = 3;
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my, 858, 400, 50, 50) && Mouse.clicked){
			MapMaker.placingpaths = !MapMaker.placingpaths;
			try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if(Mouse.moveOver(Mouse.mx,Mouse.my,25,780,200,75)){
			Render.state = "Save";
		} else if(Mouse.moveOver(Mouse.mx,Mouse.my,525,780,200,75)){
			Render.state = "Load";
		} else if(Mouse.moveOver(Mouse.mx, Mouse.my, 885, 100, 100, 50) && Mouse.clicked){
				MapMaker.selectedTile = MapMaker.selectedTile + 1;
				try {
					Thread.sleep(120);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			 if (MapMaker.selectedTile > 96){
				MapMaker.selectedTile = 0;
			}
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my, 780, 100, 100, 50)&&Mouse.clicked){
			MapMaker.selectedTile = MapMaker.selectedTile -1;
			try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(MapMaker.selectedTile < 0){
				MapMaker.selectedTile = 96;
			}
		}
	}
}
