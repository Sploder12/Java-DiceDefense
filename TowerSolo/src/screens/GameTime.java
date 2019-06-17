package screens;

import java.awt.Color;
import java.util.Arrays;

import objects.Enemy;
import objects.Unit;

import com.sploder12.main.*;

public class GameTime {
	public GameTime(){
		Rend();
		Mouse();
	}
	
	private void Rend(){
		Render.g.setColor(Color.white);
		Render.g.drawString("$"+Main.player[0].cash,Math.round(390*Render.xScale),Math.round(15*Render.yScale));
		Render.g.drawString("Life:"+Main.player[0].health,Math.round(390*Render.xScale),Math.round(30*Render.yScale));
		Render.g.drawRect(Math.round(450*Render.xScale), Math.round(390*Render.yScale), Math.round(40*Render.xScale), Math.round(40*Render.yScale));
		Render.g.drawString(">", Math.round(475*Render.xScale), Math.round(415*Render.yScale));
		Render.g.drawString((Main.currentwave+1)+"/"+Main.waves.length,Math.round(465*Render.xScale), Math.round(15 * Render.yScale));
		Enemy[] enemies = WaveManager.getEnemies();
		if(Main.player[0].selectedtower != -1) {
			//Render.g.drawString("test", Math.round(475*Render.xScale), Math.round(100*Render.yScale));
			//TODO upgrade interface here
		}
		for(byte collumn = 1; collumn <= 3; collumn++){
			for(byte row = 1; row <= 1; row++){
				Render.g.drawRect(Math.round((370+(30*collumn))*Render.xScale), Math.round((20+(30*row))*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale));
			}
		}
		for(byte drawRange = 0; drawRange < Player.towers.length; drawRange++){
			if(Player.towers[drawRange] == null) continue;
			int x = Player.towers[drawRange].getUnitX()-((Player.towers[drawRange].getUnitRange()/2));
			int y = Player.towers[drawRange].getUnitY()-((Player.towers[drawRange].getUnitRange()/2));
			int r = Player.towers[drawRange].getUnitRange();
			System.out.println(Main.player[0].selectedtower);
			if(Main.player[0].selectedtower == drawRange){
				
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
			Render.g.drawRect(Math.round(Main.player[0].towers[upgrade].getUnitX()), Math.round(Main.player[0].towers[upgrade].getUnitY()), Math.round(16*Render.xScale), Math.round(16*Render.yScale));
			if(Mouse.clicked && Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(Main.player[0].towers[upgrade].getUnitX()), Math.round(Main.player[0].towers[upgrade].getUnitY()), Math.round(320*Render.xScale), Math.round(320*Render.yScale))) {
				Main.player[0].selectedtower = upgrade;
				System.out.println(upgrade);
			}
		}
	}
}
