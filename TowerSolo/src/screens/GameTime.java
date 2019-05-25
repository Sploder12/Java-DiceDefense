package screens;

import java.awt.Color;

import com.sploder12.main.*;

public class GameTime {
	public GameTime(){
		Rend();
		Mouse();
	}
	
	private void Rend(){
		Render.g.setColor(Color.white);
		Render.g.drawRect(Math.round(450*Render.xScale), Math.round(390*Render.yScale), Math.round(40*Render.xScale), Math.round(40*Render.yScale));
		Render.g.drawString(">", Math.round(475*Render.xScale), Math.round(415*Render.yScale));
		for(byte drawRange = 0; drawRange < Player.towers.length; drawRange++){
			if(Player.towers[drawRange] == null) continue;
			int x = Player.towers[drawRange].getUnitX()-((Player.towers[drawRange].getUnitRange()/2));
			int y = Player.towers[drawRange].getUnitY()-((Player.towers[drawRange].getUnitRange()/2));
			int r = Player.towers[drawRange].getUnitRange();
			Render.g.drawRect(Math.round((x+12+(r/2))*Render.xScale), Math.round((y+12+(r/2))*Render.yScale), 16, 16);
			Render.g.drawOval(Math.round((x+16)*Render.xScale), Math.round((y+16)*Render.yScale), Math.round(r*Render.yScale),Math.round(r*Render.yScale));
		}
	}
	
	private void Mouse(){
		if(Mouse.clicked&&Mouse.moveOver(Mouse.mx,Mouse.my,Math.round(450*Render.xScale), Math.round(390*Render.yScale), Math.round(40*Render.xScale), Math.round(40*Render.yScale))){
			WaveManager.sending = true;
		}
	}
}
