package screens;

import java.awt.Color;
import java.util.Arrays;

import objects.Enemy;
import objects.Unit;
import sploder12.json.JSON;

import com.sploder12.main.*;

public class GameTime {
	private JSON json = new JSON();
	public GameTime(){
		Rend();
		Mouse();
	}
	
	Color alphaRed = new Color(255,0,0,175);
	Color alphaGreen = new Color(0,255,0,120);
	private void Rend(){
		Render.g.setColor(Color.white);
		Render.g.drawString("$"+Main.player[0].cash,Math.round(390*Render.xScale),Math.round(15*Render.yScale));
		Render.g.drawString("Life:"+Main.player[0].health,Math.round(390*Render.xScale),Math.round(30*Render.yScale));
		Render.g.drawRect(Math.round(450*Render.xScale), Math.round(390*Render.yScale), Math.round(40*Render.xScale), Math.round(40*Render.yScale));
		Render.g.drawString(">", Math.round(475*Render.xScale), Math.round(415*Render.yScale));
		Render.g.drawString((Main.currentwave+1)+"/"+Main.waves.length,Math.round(465*Render.xScale), Math.round(15 * Render.yScale));
		Enemy[] enemies = WaveManager.getEnemies();
		
		for(byte collumn = 1; collumn <= 3; collumn++){
			for(byte row = 1; row <= 1; row++){
				Render.g.drawRect(Math.round((367+(30*collumn))*Render.xScale), Math.round((20+(30*row))*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale));
			}
		}
		for(byte drawRange = 0; drawRange < Player.towers.length; drawRange++){
			if(Player.towers[drawRange] == null) continue;
			int x = Player.towers[drawRange].getUnitX()-((Player.towers[drawRange].getUnitRange()/2));
			int y = Player.towers[drawRange].getUnitY()-((Player.towers[drawRange].getUnitRange()/2));
			int r = Player.towers[drawRange].getUnitRange();
			if(Main.player[0].getselectedtower() == drawRange) {
				Render.g.drawOval(Math.round((x+16)*Render.xScale), Math.round((y+16)*Render.yScale), Math.round(r*Render.xScale),Math.round(r*Render.yScale));
			}
			//System.out.println(Player.towers[drawRange].attacking);
			
			if(Player.towers[drawRange].attacking){
				for(byte multishot = 0; multishot < Player.towers[drawRange].enemysatking.length; multishot++){
					//System.out.println(Arrays.toString(Player.towers[drawRange].enemysatking));
					int enemx = enemies[Player.towers[drawRange].enemysatking[multishot]].getEnemyXCord();
					int enemy = enemies[Player.towers[drawRange].enemysatking[multishot]].getEnemyYCord();
					Render.g.setColor(Color.red);
					if(Player.towers[drawRange].attacking && enemies[Player.towers[drawRange].enemysatking[multishot]].visible){
						if(Player.towers[drawRange].splashdmg){
							Render.g.drawOval(Math.round((enemx-Player.towers[drawRange].blastr/4)*Render.xScale), Math.round((enemy-Player.towers[drawRange].blastr/4)*Render.yScale), Math.round(Player.towers[drawRange].blastr*Render.xScale),Math.round(Player.towers[drawRange].blastr*Render.yScale));
						}
						Render.g.drawLine(Math.round((x+16+(r/2))*Render.xScale), Math.round((y+16+(r/2))*Render.yScale), Math.round((enemx+8)*Render.xScale), Math.round((enemy+8)*Render.yScale));
					}
					Render.g.setColor(Color.white);
				}
			}
		
		}
		if(Main.player[0].selectedtower != -1) {
			//Render.g.drawString("test", Math.round(475*Render.xScale), Math.round(100*Render.yScale));
			//TODO upgrade interface here
			Render.g.setColor(Color.black);
			Render.g.fillRect(Math.round(384*Render.xScale), Math.round(80*Render.yScale), Math.round(110*Render.xScale), Math.round(304*Render.yScale));
			Render.g.setColor(Color.white);
			Render.g.drawRect(Math.round(384*Render.xScale), Math.round(80*Render.yScale), Math.round(108*Render.xScale), Math.round(304*Render.yScale));
			Render.g.drawRect(Math.round(384*Render.xScale), Math.round(184*Render.yScale), Math.round(56*Render.xScale), Math.round(100*Render.yScale));
			Render.g.drawRect(Math.round(440*Render.xScale), Math.round(284*Render.yScale), Math.round(56*Render.xScale), Math.round(100*Render.yScale));
			Render.g.drawLine(Math.round(440*Render.xScale), Math.round(184*Render.yScale),Math.round(502*Render.xScale), Math.round(184*Render.yScale));
			String upgrade = "";
			int towuer = json.locateStringStart(Main.upgradefile, Main.player[0].towers[Main.player[0].selectedtower].name)-3;
			if(Player.towers[Main.player[0].selectedtower].totalups > 7) {
				Render.g.setColor(alphaRed);
				Render.g.fillRect(Math.round(384*Render.xScale), Math.round(184*Render.yScale), Math.round(108*Render.xScale), Math.round(200*Render.yScale));
				Render.g.setColor(Color.white);
			}
			if(Player.towers[Main.player[0].selectedtower].path1 <= 4) {
				Render.newFont = Render.currentFont.deriveFont(Render.xScale*(Render.currentFont.getSize() * 0.65F)); 
				Render.g.setFont(Render.newFont);
				upgrade = ""+Player.towers[Main.player[0].selectedtower].path1+"000";
				Render.g.drawString(json.getValueOfDict(Main.upgradefile, json.locateStringEnd(Main.upgradefile, upgrade,towuer),15), Math.round(387*Render.xScale), Math.round(200*Render.yScale));
				drawSpecialString(json.getValueOfDict(Main.upgradefile, json.locateStringEnd(Main.upgradefile, upgrade+"d",towuer),50),10,395,210);
				Render.g.drawString("$"+json.getIntValueOfDict(Main.upgradefile, json.locateStringEnd(Main.upgradefile, upgrade+"p",towuer)), Math.round(387*Render.xScale), Math.round(280*Render.yScale));
				if(Player.towers[Main.player[0].selectedtower].totalups < 8 && json.getIntValueOfDict(Main.upgradefile, json.locateStringEnd(Main.upgradefile, upgrade+"p",towuer)) <= Main.player[0].cash) {
					Render.g.setColor(alphaGreen);
					Render.g.fillRect(Math.round(384*Render.xScale), Math.round(184*Render.yScale), Math.round(56*Render.xScale), Math.round(100*Render.yScale));
					Render.g.setColor(Color.white);
				}
			} else {
				Render.g.setColor(Color.green);
				Render.g.fillRect(Math.round(384*Render.xScale), Math.round(184*Render.yScale), Math.round(56*Render.xScale), Math.round(100*Render.yScale));
				Render.g.setColor(Color.white);
			}
			if(Player.towers[Main.player[0].selectedtower].path2 <= 4) {
				Render.newFont = Render.currentFont.deriveFont(Render.xScale*(Render.currentFont.getSize() * 0.65F)); 
				Render.g.setFont(Render.newFont);
				upgrade = "0"+Player.towers[Main.player[0].selectedtower].path2+"00";
				Render.g.drawString(json.getValueOfDict(Main.upgradefile, json.locateStringEnd(Main.upgradefile, upgrade,towuer),15), Math.round(443*Render.xScale), Math.round(200*Render.yScale));
				drawSpecialString(json.getValueOfDict(Main.upgradefile, json.locateStringEnd(Main.upgradefile, upgrade+"d",towuer),50),10,445,210);
				Render.g.drawString("$"+json.getIntValueOfDict(Main.upgradefile, json.locateStringEnd(Main.upgradefile, upgrade+"p",towuer)), Math.round(443*Render.xScale), Math.round(280*Render.yScale));
				if(Player.towers[Main.player[0].selectedtower].totalups < 8 &&json.getIntValueOfDict(Main.upgradefile, json.locateStringEnd(Main.upgradefile, upgrade+"p",towuer)) <= Main.player[0].cash) {
					Render.g.setColor(alphaGreen);
					Render.g.fillRect(Math.round(440*Render.xScale), Math.round(184*Render.yScale), Math.round(56*Render.xScale), Math.round(100*Render.yScale));
					Render.g.setColor(Color.white);
				}
			}else {
				Render.g.setColor(Color.green);
				Render.g.fillRect(Math.round(440*Render.xScale), Math.round(184*Render.yScale), Math.round(56*Render.xScale), Math.round(100*Render.yScale));
				Render.g.setColor(Color.white);
			}
			if(Player.towers[Main.player[0].selectedtower].path3 <= 4) {
				Render.newFont = Render.currentFont.deriveFont(Render.xScale*(Render.currentFont.getSize() * 0.65F)); 
				Render.g.setFont(Render.newFont);
				upgrade = "00"+Player.towers[Main.player[0].selectedtower].path3+"0";
				Render.g.drawString(json.getValueOfDict(Main.upgradefile, json.locateStringEnd(Main.upgradefile, upgrade,towuer),15), Math.round(387*Render.xScale), Math.round(300*Render.yScale));
				drawSpecialString(json.getValueOfDict(Main.upgradefile, json.locateStringEnd(Main.upgradefile, upgrade+"d",towuer),50),10,395,310);
				Render.g.drawString("$"+json.getIntValueOfDict(Main.upgradefile, json.locateStringEnd(Main.upgradefile, upgrade+"p",towuer)), Math.round(387*Render.xScale), Math.round(380*Render.yScale));
				if(Player.towers[Main.player[0].selectedtower].totalups < 8 &&json.getIntValueOfDict(Main.upgradefile, json.locateStringEnd(Main.upgradefile, upgrade+"p",towuer)) <= Main.player[0].cash) {
					Render.g.setColor(alphaGreen);
					Render.g.fillRect(Math.round(384*Render.xScale), Math.round(284*Render.yScale), Math.round(56*Render.xScale), Math.round(100*Render.yScale));
					Render.g.setColor(Color.white);
				}
			}else {
				Render.g.setColor(Color.green);
				Render.g.fillRect(Math.round(384*Render.xScale), Math.round(284*Render.yScale), Math.round(56*Render.xScale), Math.round(100*Render.yScale));
				Render.g.setColor(Color.white);
			}
			if(Player.towers[Main.player[0].selectedtower].path4 <= 4) {
				Render.newFont = Render.currentFont.deriveFont(Render.xScale*(Render.currentFont.getSize() * 0.65F)); 
				Render.g.setFont(Render.newFont);
				upgrade = "000"+Player.towers[Main.player[0].selectedtower].path4;
				Render.g.drawString(json.getValueOfDict(Main.upgradefile, json.locateStringEnd(Main.upgradefile, upgrade,towuer),15), Math.round(443*Render.xScale), Math.round(300*Render.yScale));
				drawSpecialString(json.getValueOfDict(Main.upgradefile, json.locateStringEnd(Main.upgradefile, upgrade+"d",towuer),75),10,445,310);
				Render.g.drawString("$"+json.getIntValueOfDict(Main.upgradefile, json.locateStringEnd(Main.upgradefile, upgrade+"p",towuer)), Math.round(443*Render.xScale), Math.round(380*Render.yScale));
				if(Player.towers[Main.player[0].selectedtower].totalups < 8 &&json.getIntValueOfDict(Main.upgradefile, json.locateStringEnd(Main.upgradefile, upgrade+"p",towuer)) <= Main.player[0].cash) {
					Render.g.setColor(alphaGreen);
					Render.g.fillRect(Math.round(440*Render.xScale), Math.round(284*Render.yScale), Math.round(56*Render.xScale), Math.round(100*Render.yScale));
					Render.g.setColor(Color.white);
				}
			}else {
				Render.g.setColor(Color.green);
				Render.g.fillRect(Math.round(440*Render.xScale), Math.round(284*Render.yScale), Math.round(56*Render.xScale), Math.round(100*Render.yScale));
				Render.g.setColor(Color.white);
			}
		}
	}
	
	private void drawSpecialString(String string,int len,int xlocation, int ylocation) {
		Render.newFont = Render.currentFont.deriveFont(Render.xScale*(Render.currentFont.getSize() * 0.55F)); 
		Render.g.setFont(Render.newFont);
		int spl = (int) Math.ceil(string.length()/len);
		String[] strings = new String[spl];
		for(short y = 0; y < spl; y++) {
			if((y+1)*len <= string.length()) {
				strings[y] = string.substring(y*len, (y+1)*len);
			}else {
				strings[y] = string.substring(y*len, (string.length()-(y+1)*len));
			}
		}
		for(short x = 0; x < spl; x++) {
			Render.g.drawString(strings[x], Math.round(xlocation*Render.xScale), Math.round((ylocation+(x*10))*Render.yScale));
		}
	}
	
	
	private Unit[] unitz ={Unit.LazerPointer, Unit.LazerShotgun, Unit.LazerCannon};
	private void Mouse(){
		if(Mouse.clicked&&Mouse.moveOver(Mouse.mx,Mouse.my,Math.round(450*Render.xScale), Math.round(390*Render.yScale), Math.round(40*Render.xScale), Math.round(40*Render.yScale))){
			WaveManager.sending = true;
			
		}
		for(byte collumn = 1; collumn <= 3; collumn++){
			for(byte row = 1; row <= 1; row++){
				if(Mouse.clicked && Mouse.moveOver(Mouse.mx, Mouse.my, Math.round((370+(30*collumn))*Render.xScale), Math.round((20+(30*row))*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale))){
					Main.player[0].selectedtower = -1;
					Main.player[0].selectedunit = unitz[collumn-1];
					Main.player[0].setplacing(true);
				}
			}
		}
		
		for(int upgrade = 0; upgrade < Main.player[0].towers.length; upgrade++) {
			if(Main.player[0].towers[upgrade] == null)continue;
			//int r = Main.player[0].towers[upgrade].getUnitRange();
			//Render.g.drawRect(Math.round(Main.player[0].towers[upgrade].getUnitX()*2+18), Math.round(Main.player[0].towers[upgrade].getUnitY()*2+18), Math.round(16*Render.xScale), Math.round(16*Render.yScale));
			if(Mouse.clicked && Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(Main.player[0].towers[upgrade].getUnitX()*2+18), Math.round(Main.player[0].towers[upgrade].getUnitY()*2+18), Math.round(16*Render.xScale), Math.round(16*Render.yScale))) {
				Main.player[0].selectedtower = upgrade;
			}
		}
		if(Main.player[0].selectedtower != -1) {
			if(Mouse.clicked&&Main.player[0].towers[Main.player[0].selectedtower].path1 <= 4&&Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(384*Render.xScale), Math.round(184*Render.yScale), Math.round(56*Render.xScale), Math.round(100*Render.yScale))) {
				Mouse.clicked = false;
				Main.player[0].towers[Main.player[0].selectedtower].upgrades(Main.player[0].towers[Main.player[0].selectedtower].path1);
			} else if(Mouse.clicked&&Main.player[0].towers[Main.player[0].selectedtower].path2 <= 4&&Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(440*Render.xScale), Math.round(184*Render.yScale), Math.round(56*Render.xScale), Math.round(100*Render.yScale))) {
				Mouse.clicked = false;
				Main.player[0].towers[Main.player[0].selectedtower].upgrades(Main.player[0].towers[Main.player[0].selectedtower].path2+4);
			} else if(Mouse.clicked&&Main.player[0].towers[Main.player[0].selectedtower].path3 <= 4&&Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(384*Render.xScale), Math.round(284*Render.yScale), Math.round(56*Render.xScale), Math.round(100*Render.yScale))) {
				Mouse.clicked = false;
				Main.player[0].towers[Main.player[0].selectedtower].upgrades(Main.player[0].towers[Main.player[0].selectedtower].path3+8);
			}else if(Mouse.clicked&&Main.player[0].towers[Main.player[0].selectedtower].path4 <= 4&&Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(440*Render.xScale), Math.round(284*Render.yScale), Math.round(56*Render.xScale), Math.round(100*Render.yScale))) {
				Mouse.clicked = false;
				Main.player[0].towers[Main.player[0].selectedtower].upgrades(Main.player[0].towers[Main.player[0].selectedtower].path4+12);
			}
		}
	}
}
