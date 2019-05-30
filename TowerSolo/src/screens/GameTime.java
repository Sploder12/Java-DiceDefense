package screens;

import java.awt.Color;

import objects.Enemy;

import com.sploder12.main.*;

public class GameTime {
	public GameTime(){
		Rend();
		Mouse();
	}
	
	private void Rend(){
		Render.g.setColor(Color.white);
		Render.g.drawString("$"+Player.cash,Math.round(390*Render.xScale),Math.round(15*Render.yScale));
		Render.g.drawString("Life:"+Main.player[0].health,Math.round(390*Render.xScale),Math.round(30*Render.yScale));
		Render.g.drawRect(Math.round(450*Render.xScale), Math.round(390*Render.yScale), Math.round(40*Render.xScale), Math.round(40*Render.yScale));
		Render.g.drawString(">", Math.round(475*Render.xScale), Math.round(415*Render.yScale));
		Enemy[] enemies = WaveManager.getEnemies();
		for(byte drawRange = 0; drawRange < Player.towers.length; drawRange++){
			if(Player.towers[drawRange] == null) continue;
			int x = Player.towers[drawRange].getUnitX()-((Player.towers[drawRange].getUnitRange()/2));
			int y = Player.towers[drawRange].getUnitY()-((Player.towers[drawRange].getUnitRange()/2));
			int r = Player.towers[drawRange].getUnitRange();
			Render.g.drawRect(Math.round((x+12+(r/2))*Render.xScale), Math.round((y+12+(r/2))*Render.yScale), 16, 16);
			Render.g.drawOval(Math.round((x+16)*Render.xScale), Math.round((y+16)*Render.yScale), Math.round(r*Render.yScale),Math.round(r*Render.yScale));
			if(Player.towers[drawRange].attacking){
				
				int enemx = enemies[Player.towers[drawRange].enemyatking].getEnemyXCord();
				int enemy = enemies[Player.towers[drawRange].enemyatking].getEnemyYCord();
				Render.g.setColor(Color.red);
				if(Player.towers[drawRange].attacking){
				Render.g.drawLine(Math.round((x+16+(r/2))*Render.xScale), Math.round((y+16+(r/2))*Render.yScale), Math.round((enemx+8)*Render.xScale), Math.round((enemy+8)*Render.yScale));
				}
			}
		}

			
		
		
		for(byte collumn = 1; collumn <= 3; collumn++){
			for(byte row = 1; row <= 1; row++){
				Render.g.drawRect(Math.round((370+(30*collumn))*Render.xScale), Math.round((20+(30*row))*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale));
			}
		}
		}
	
	private void Mouse(){
		if(Mouse.clicked&&Mouse.moveOver(Mouse.mx,Mouse.my,Math.round(450*Render.xScale), Math.round(390*Render.yScale), Math.round(40*Render.xScale), Math.round(40*Render.yScale))){
			WaveManager.sending = true;
		}
		for(byte collumn = 1; collumn <= 3; collumn++){
			for(byte row = 1; row <= 5; row++){
				if(Mouse.clicked && Mouse.moveOver(Mouse.mx, Mouse.my, Math.round((370+(30*collumn))*Render.xScale), Math.round((20+(30*row))*Render.yScale), Math.round(25*Render.xScale), Math.round(25*Render.yScale))){
					Main.player[0].setplacing(true);
				}
			}
		}
	}
}
