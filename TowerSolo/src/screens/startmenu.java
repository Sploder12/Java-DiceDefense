package screens;

import java.awt.Color;

import com.sploder12.main.Main;
import com.sploder12.main.Mouse;
import com.sploder12.main.Render;
import com.sploder12.main.WaveManager;

public class startmenu {
	public static final String[] difficulties ={"D2","D4","D6","D8","D10","D20","Unrollable","Sandy Dice"};
	public startmenu(){
		Rend();
		Mouse();
	}
	
	private void Rend(){
		Render.g.setColor(Color.white);
		for(byte dif = 1; dif <= difficulties.length; dif++){
			Render.g.drawString(difficulties[dif-1], Math.round(((49*dif)+50)*Render.xScale),Math.round(99*Render.yScale)); //Will be replaced by pictures in later versions
			Render.g.drawString("Difficulties:", Math.round(40*Render.xScale),Math.round(99*Render.yScale));
			Render.g.drawRect(Math.round(((50*dif)+50)*Render.xScale),Math.round(100*Render.yScale),Math.round(25*Render.xScale),Math.round(25*Render.yScale));
			if(Main.difficulty == dif-1){
				Render.g.fillRect(Math.round(((50*dif)+50)*Render.xScale),Math.round(100*Render.yScale),Math.round(25*Render.xScale),Math.round(25*Render.yScale));
			}
		}
		Render.g.drawRect(Math.round(150*Render.xScale), Math.round(390*Render.yScale), Math.round(200*Render.xScale), Math.round(40*Render.yScale));
		Render.newFont = Render.currentFont.deriveFont(Render.xScale*(Render.currentFont.getSize() * 2.5F)); 
		Render.g.setFont(Render.newFont);
		Render.g.drawString("Start", Math.round(210*Render.xScale), Math.round(420*Render.yScale));
		Render.newFont = Render.currentFont.deriveFont(Render.xScale*(Render.currentFont.getSize() * 1.5F)); 
		Render.g.setFont(Render.newFont);
		Render.g.drawRect(Math.round(450*Render.xScale), Math.round(390*Render.yScale), Math.round(40*Render.xScale), Math.round(40*Render.yScale));
		Render.g.drawString("Back", Math.round(450*Render.xScale), Math.round(415*Render.yScale));
		
		
	}
	
	private void Mouse(){
		for(byte dif = 1; dif <= difficulties.length; dif++){
			if(Mouse.clicked &&Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(((50*dif)+50)*Render.xScale),Math.round(100*Render.yScale),Math.round(25*Render.xScale),Math.round(25*Render.yScale))){
				Main.difficulty = (byte) (dif-1);
			}
		}
		
		if(Mouse.moveOver(Mouse.mx,Mouse.my,Math.round(150*Render.xScale), Math.round(390*Render.yScale), Math.round(200*Render.xScale), Math.round(40*Render.yScale))&& Mouse.clicked){
			Render.state ="GameTime";
			Render.waving = new WaveManager();
		}else if(Mouse.clicked && Mouse.moveOver(Mouse.mx,Mouse.my, Math.round(450*Render.xScale), Math.round(390*Render.yScale), Math.round(40*Render.xScale), Math.round(40*Render.yScale))){
			Mouse.clicked = false;
			Render.state = "DefualtMaps";
		}
	}
	
}
