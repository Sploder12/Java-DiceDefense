package com.sploder12.main;

import objects.*;

public class Player{
	public String name = "";
	public long cash = 1000000;
	public int health = 1000;
	public volatile boolean placing = false;
	public volatile Unit selectedunit = Unit.LazerPointer;
	private int totaltowers = 0;
	public int selectedtower = -1;
	
	public volatile static Units[] towers= new Units[100];
	public Player(){
		//towers[0] = new Units(128,128,Unit.DiceRoller);
	}
	
	public Unit getselectunit() {
		return selectedunit;
	}
	public long getcash() {
		return cash;
	}
	
	public void setcash(long cash) {
		this.cash = cash;
	}
	
	public boolean getplacing() {
		return placing;
	}
	public void setplacing(boolean placing){
		this.placing = placing;
	}
	
	public void placeUnit(){
		if(placing && totaltowers < 100 && Mouse.mx < 384*Render.xScale && Mouse.my < 384*Render.yScale){
			if(cash >= selectedunit.getTowerCost() && Mouse.clicked && (Main.file_mappath[(int) Math.floor(Mouse.my/32)][(int) Math.floor(Mouse.mx/32)]== Paths.Null)){
				cash -= selectedunit.getTowerCost();
				placing = false;
				towers[totaltowers] = new Units(Math.round((Mouse.mx-35)/Render.xScale),Math.round((Mouse.my-35)/Render.yScale),selectedunit);
				totaltowers++;
			}
		}
	}
}
