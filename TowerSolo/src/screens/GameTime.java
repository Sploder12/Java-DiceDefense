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
	}
	
	private void Mouse(){
		if(Mouse.clicked&&Mouse.moveOver(Mouse.mx,Mouse.my,Math.round(450*Render.xScale), Math.round(390*Render.yScale), Math.round(40*Render.xScale), Math.round(40*Render.yScale))){
			WaveManager.sending = true;
		}
	}
}
