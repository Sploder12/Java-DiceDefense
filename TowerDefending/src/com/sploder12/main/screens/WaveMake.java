package com.sploder12.main.screens;
import java.awt.Color;
import java.util.Arrays;

import com.sploder12.main.Mouse;
import com.sploder12.main.Render;
import com.sploder12.main.WaveMaker;

public class WaveMake {
	public WaveMake(){
		Rend();
		mouse();
	}
	private void Rend(){
		Render.g.setColor(Color.white);
		Render.g.drawRect(760,0,1000,1000);
		//Render.g.drawRect(850, 100, 50,50);
		Render.g.drawRect(775, 100, 100, 50);
		Render.g.drawString("Prev", 800, 135);
		Render.g.drawRect(885, 100, 100, 50);
		Render.g.drawString("Next", 915, 135);
		Render.g.drawString("Enemy Selection", 800, 90);
		Render.g.drawString("Current Enemy", 810, 200);
		Render.g.drawRect(815, 215, 128, 128);
		Render.g.drawString(""+WaveMaker.enemies[WaveMaker.selectedenemy], 860, 270);
		
		Render.g.drawString("Current Wave:"+(WaveMaker.currentwave+1),250,50);
		for(byte x = 1; x <= 5; x++){
			Render.g.drawRect(128*x-78,150,128,128);
			Render.g.drawString(""+WaveMaker.waves[WaveMaker.currentwave][((WaveMaker.wavepart-1)*5)+x-1], 128*x-25, 215);
		}
		Render.g.drawString("Showing enemies "+((WaveMaker.wavepart-1)*5)+"-"+WaveMaker.wavepart*5,250,300);
		Render.g.drawRect(50,300,50,50);
		Render.g.drawRect(640,300,50,50);
		Render.g.drawString("-->",650,335);
		Render.g.drawString("<--",60,335);
		Render.g.drawString("Main Menu",820,820);
		Render.g.drawRect(775, 780, 200, 75);
	
	}
	
	private void mouse(){
		for(byte x = 1; x <= 5; x++){
			if(Mouse.moveOver(Mouse.mx, Mouse.my, 128*x-78, 150, 128, 128) && Mouse.clicked){
				WaveMaker.waves[WaveMaker.currentwave][((WaveMaker.wavepart-1)*5) + x - 1 ] = WaveMaker.enemies[WaveMaker.selectedenemy];
			}
		}
		
		if(Mouse.moveOver(Mouse.mx, Mouse.my,50,300,50,50) && WaveMaker.wavepart > 1 && Mouse.clicked){
			WaveMaker.wavepart--;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my,640,300,50,50) && WaveMaker.wavepart < 20 && Mouse.clicked){
			WaveMaker.wavepart++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my,775,100,100,50)&& Mouse.clicked){
			if(WaveMaker.selectedenemy > 0){
				WaveMaker.selectedenemy--;
			}else{
				WaveMaker.selectedenemy = (short)(WaveMaker.enemies.length-1);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my,885,100,100,50)&& Mouse.clicked){
			if(WaveMaker.selectedenemy < WaveMaker.enemies.length - 1){
				WaveMaker.selectedenemy++;
			}else{
				WaveMaker.selectedenemy = 0;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my, 775, 780, 200, 75)){
			Render.state = "Menu";
		}
	}
}
