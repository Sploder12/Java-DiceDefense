package com.sploder12.main;

import objects.*;

public class Player{
	public String name = "";
	public long cash = 0;
	public boolean placing = false;
	public Unit selectedunit = Unit.DiceDicer;
	private int totaltowers = 0;
	
	public static Units[] towers= new Units[100];
	public Player(){
		//towers[0] = new Units(128,128,Unit.DiceRoller);
	}
	
	public void setplacing(boolean placing){
		this.placing = placing;
	}
	
	public void placeUnit(){
		if(placing && totaltowers < 100){
			if(Mouse.clicked && (Main.file_mappath[Math.round(Mouse.mx/16)][Math.round(Mouse.my/16)]== Paths.Null)){
				towers[totaltowers] = new Units(Mouse.mx,Mouse.my,selectedunit);
				totaltowers++;
			}
		}
	}
}
