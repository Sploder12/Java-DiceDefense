package com.sploder12.main.screens;
import java.awt.Color;

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
		Render.g.drawRect(Math.round(25*Render.xScale), Math.round(390*Render.yScale), Math.round(100*Render.xScale), Math.round(38*Render.yScale));
		Render.g.drawString("Save As", Math.round(40*Render.xScale), Math.round(413*Render.yScale));
		
		Render.g.drawRect(Math.round(263*Render.xScale), Math.round(390*Render.yScale), Math.round(100*Render.xScale), Math.round(38*Render.yScale));
		Render.g.drawString("Load", Math.round(290*Render.xScale), Math.round(413*Render.yScale));
		Render.g.drawRect(((int)Math.round(380*Render.xScale)),0,(int)Math.round(500*Render.xScale),(int)Math.round(500*Render.yScale));
		//Render.g.drawRect(850*Render.xScale, 100*Render.yScale, 50*Render.xScale,50*Render.yScale);
		Render.g.drawRect((int)Math.round(388*Render.xScale), (int)Math.round(50*Render.yScale), (int)Math.round(50*Render.xScale), (int)Math.round(25*Render.yScale));
		Render.g.drawString("Prev", (int)Math.round(400*Render.xScale), (int)Math.round(68*Render.yScale));
		Render.g.drawRect((int)Math.round(443*Render.xScale), (int)Math.round(50*Render.yScale), (int)Math.round(50*Render.xScale), (int)Math.round(25*Render.yScale));
		Render.g.drawString("Next", (int)Math.round(458*Render.xScale), (int)Math.round(68*Render.yScale));
		Render.g.drawString("Enemy Selection", (int)Math.round(400*Render.xScale), (int)Math.round(45*Render.yScale));
		Render.g.drawString("Current Enemy", (int)Math.round(405*Render.xScale), (int)Math.round(100*Render.yScale));
		//Render.g.drawRect(815*Render.xScale, 215*Render.yScale, 128*Render.xScale, 128*Render.yScale);
		//Render.g.drawString(""+WaveMaker.selectedenemy, 860*Render.xScale, 270*Render.yScale);
		Render.g.drawString("Current Wave:"+(WaveMaker.currentwave+1),(int)Math.round(125*Render.xScale),(int)Math.round(25*Render.yScale));
		for(byte x = 1; x <= 5; x++){
			Render.g.drawRect((int)Math.round((64*x-39)*Render.xScale),(int)Math.round(75*Render.yScale),(int)Math.round(64*Render.xScale),(int)Math.round(64*Render.yScale));
			Render.g.drawString(""+WaveMaker.waves[WaveMaker.currentwave][((WaveMaker.wavepart-1)*5)+x-1], (int)Math.round((64*x-25)*Render.xScale), (int)Math.round(108*Render.yScale));
			
		}
		Render.g.drawString("Showing enemies "+((WaveMaker.wavepart-1)*5)+"-"+WaveMaker.wavepart*5,(int)Math.round(125*Render.xScale),(int)Math.round(150*Render.yScale));
		Render.g.drawRect((int)Math.round(25*Render.xScale),(int)Math.round(150*Render.yScale),(int)Math.round(25*Render.xScale),(int)Math.round(25*Render.yScale));
		Render.g.drawRect((int)Math.round(320*Render.xScale),(int)Math.round(150*Render.yScale),(int)Math.round(25*Render.xScale),(int)Math.round(25*Render.yScale));
		Render.g.drawString("-->",(int)Math.round(325*Render.xScale),(int)Math.round(168*Render.yScale));
		Render.g.drawString("<--",(int)Math.round(30*Render.xScale),(int)Math.round(168*Render.yScale));
		Render.g.drawString("Main Menu",(int)Math.round(410*Render.xScale),(int)Math.round(410*Render.yScale));
		Render.g.drawRect((int)Math.round(400*Render.xScale), (int)Math.round(390*Render.yScale), (int)Math.round(75*Render.xScale), (int)Math.round(38*Render.yScale));
	
	}
	
	private void mouse(){
		for(byte x = 1; x <= 5; x++){
			if(Mouse.moveOver(Mouse.mx, Mouse.my, (int)Math.round((64*x-39)*Render.xScale), (int)Math.round(75*Render.yScale), (int)Math.round(64*Render.xScale), (int)Math.round(64*Render.yScale)) && Mouse.clicked){
				WaveMaker.waves[WaveMaker.currentwave][((WaveMaker.wavepart-1)*5) + x - 1 ] = Enemies.values()[WaveMaker.selectedenemy];
			}
		}
		
		if(Mouse.moveOver(Mouse.mx, Mouse.my,(int)Math.round(25*Render.xScale),(int)Math.round(150*Render.yScale),(int)Math.round(25*Render.xScale),(int)Math.round(25*Render.yScale)) && WaveMaker.wavepart > 1 && Mouse.clicked){
			WaveMaker.wavepart--;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my,(int)Math.round(320*Render.xScale),(int)Math.round(150*Render.yScale),(int)Math.round(25*Render.xScale),(int)Math.round(25*Render.yScale)) && WaveMaker.wavepart < 20 && Mouse.clicked){
			WaveMaker.wavepart++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my,(int)Math.round(388*Render.xScale),(int)Math.round(50*Render.yScale),(int)Math.round(50*Render.xScale),(int)Math.round(25*Render.yScale))&& Mouse.clicked){
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
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my,(int)Math.round(443*Render.xScale),(int)Math.round(50*Render.yScale),(int)Math.round(50*Render.xScale),(int)Math.round(25*Render.yScale))&& Mouse.clicked){
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
		}else if(Mouse.moveOver(Mouse.mx, Mouse.my, (int)Math.round(400*Render.xScale), (int)Math.round(390*Render.yScale), (int)Math.round(75*Render.xScale), (int)Math.round(38*Render.yScale))){
			Render.state = "Menu";
		}else if(Mouse.moveOver(Mouse.mx,Mouse.my,Math.round(13*Render.xScale),Math.round(390*Render.yScale),Math.round(100*Render.xScale),Math.round(38*Render.yScale))){
			Render.state = "SaveWv";
		} else if(Mouse.moveOver(Mouse.mx,Mouse.my,Math.round(263*Render.xScale),Math.round(390*Render.yScale),Math.round(100*Render.xScale),Math.round(38*Render.yScale))){
			Render.state = "LoadWv";
		}
	}
}
