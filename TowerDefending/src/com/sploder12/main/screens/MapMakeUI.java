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
		Render.g.drawRect(865, 215, 32, 32);
		
		
	}
	
	public void mouse(){
		
		if(Mouse.moveOver(Mouse.mx,Mouse.my,25,780,200,75)){
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
