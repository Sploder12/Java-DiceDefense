package screens;

import java.awt.Color;

import com.sploder12.main.*;


public class DefualtMaps {
	private final String[] NAME = {"Grass Fields","Watery Way" ,"Custom Maps"};
	
	

	public DefualtMaps(){
		Rend();
		Mouse();
	}
	
	private void Rend(){
		Render.g.setColor(Color.black);
		Render.newFont = Render.currentFont.deriveFont(Render.xScale*(Render.currentFont.getSize() * 2.5F)); 
		Render.g.setFont(Render.newFont);
		Render.g.drawString(NAME[Render.mapselect], Math.round(160*Render.xScale), Math.round(25*Render.yScale));
		Render.g.setColor(Color.white);
		Render.g.drawRect(Math.round(150*Render.xScale), Math.round(390*Render.yScale), Math.round(200*Render.xScale), Math.round(40*Render.yScale));
		Render.g.drawString("Start", Math.round(210*Render.xScale), Math.round(420*Render.yScale));
		Render.g.setColor(Color.white);
		Render.newFont = Render.currentFont.deriveFont(Render.xScale*(Render.currentFont.getSize() * 1.5F)); 
		Render.g.setFont(Render.newFont);
		Render.g.drawRect(Math.round(450*Render.xScale), Math.round(390*Render.yScale), Math.round(40*Render.xScale), Math.round(40*Render.yScale));
		Render.g.drawString("Back", Math.round(450*Render.xScale), Math.round(415*Render.yScale));
		Render.g.drawRect(Math.round(355*Render.xScale), Math.round(390*Render.yScale), Math.round(40*Render.xScale), Math.round(40*Render.yScale));
		Render.g.drawRect(Math.round(105*Render.xScale), Math.round(390*Render.yScale), Math.round(40*Render.xScale), Math.round(40*Render.yScale));
		Render.g.drawString("-->", Math.round(365*Render.xScale), Math.round(415*Render.yScale));
		Render.g.drawString("<--", Math.round(115*Render.xScale), Math.round(415*Render.yScale));
		
	}

	
	private void Mouse(){
		if(Mouse.moveOver(Mouse.mx,Mouse.my,Math.round(150*Render.xScale), Math.round(390*Render.yScale), Math.round(200*Render.xScale), Math.round(40*Render.yScale))&& Mouse.clicked){
			Mouse.clicked = false;
			Render.state ="startmenu";
		}else if(Mouse.clicked && Mouse.moveOver(Mouse.mx,Mouse.my, Math.round(450*Render.xScale), Math.round(390*Render.yScale), Math.round(40*Render.xScale), Math.round(40*Render.yScale))){
			Render.state = "Menu";
		}else if(Mouse.clicked && Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(355*Render.xScale), Math.round(390*Render.yScale), Math.round(40*Render.xScale), Math.round(40*Render.yScale))){
			if(Render.mapselect < NAME.length-1){
				Main.reloadmap = true;
				Render.mapselect++;
			}else{
				Main.reloadmap = true;
				Render.mapselect = 0;
			}
			try{
				Thread.sleep(100);
			}catch(Exception e){
				
			}
		}else if(Mouse.clicked && Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(105*Render.xScale), Math.round(390*Render.yScale), Math.round(40*Render.xScale), Math.round(40*Render.yScale))){
			if(Render.mapselect > 0){
				Main.reloadmap = true;
				Render.mapselect--;
			}else{
				Main.reloadmap = true;
				Render.mapselect = NAME.length-1;
			}
			try{
				Thread.sleep(100);
			}catch(Exception e){
				
			}
		}
	}
}
