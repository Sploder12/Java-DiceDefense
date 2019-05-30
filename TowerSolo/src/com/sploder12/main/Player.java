package com.sploder12.main;

import objects.*;

public class Player{
	public String name = "";
	public static long cash = 1000000;
	public int health = 100;
	public volatile boolean placing = false;
	public Unit selectedunit = Unit.LazerPointer;
	private int totaltowers = 0;
	
	public volatile static Units[] towers= new Units[100];
	public Player(){
		//towers[0] = new Units(128,128,Unit.DiceRoller);
	}
	
	
	public void setplacing(boolean placing){
		this.placing = placing;
	}
	
	public void placeUnit(){
		if(placing && totaltowers < 100 && Mouse.mx < 384*Render.xScale && Mouse.my < 384*Render.yScale){
			if(cash >= selectedunit.getTowerCost() && Mouse.clicked && (Main.file_mappath[Math.round(Mouse.mx/(16*Render.xScale))][Math.round(Mouse.my/(16*Render.yScale))]== Paths.Null)){
				cash -= selectedunit.getTowerCost();
				placing = false;
				towers[totaltowers] = new Units(Mouse.mx/2,Mouse.my/2,selectedunit);
				totaltowers++;
			}
		}
	}
}
