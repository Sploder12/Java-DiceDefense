package com.sploder12.main.screens;

import java.awt.Color;

import com.sploder12.main.*;


public class Options {
	public Options(){
		Rend();
		mouse();
	}
	
	public void Rend(){
		Render.g.setColor(Color.white);
		Render.g.drawString("Main Menu",820,820);
		Render.g.drawRect(775, 780, 200, 75);
		Render.g.drawString("FPS: "+Render.tempframes,50,50);
		Render.g.drawString("Wanted fps:",50,100);
		for(byte x = 0; x < 4; x++){
			Render.g.drawRect(55*x+175,90,50,50);
			Render.g.drawString(""+(x+1)*15, 55*x+185, 120);
			Render.g.setColor(Color.black);
			if(Render.wantedfps == (x+1)*15){
				Render.g.fillRect(55*x+175,90,51,51);
			}
			Render.g.setColor(Color.white);
		}
	}
	
	public void mouse(){
		if(Mouse.moveOver(Mouse.mx, Mouse.my, 775, 780, 200, 75) && Mouse.clicked){
			Render.state = "Menu";
			try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(byte x = 0; x < 4; x++){
			if(Mouse.moveOver(Mouse.mx, Mouse.my, 55*x+175, 90, 50, 50)){
				Render.fpslimit = (byte) ((x+1) * 15);
				Render.wantedfps = (byte) ((x+1)*15);
				
			}
		}
	}
}
