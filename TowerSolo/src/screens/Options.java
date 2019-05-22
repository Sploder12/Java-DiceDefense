package screens;

import java.awt.Color;

import com.sploder12.main.*;


public class Options {
	public Options(){
		Rend();
		mouse();
	}
	
	public void Rend(){
		Render.g.setColor(Color.white);
		Render.g.drawString(Render.Version,Math.round(410*Render.xScale),Math.round(375*Render.yScale));
		Render.g.drawString("Main Menu",Math.round(410*Render.xScale),Math.round(410*Render.yScale));
		Render.g.drawRect(Math.round(388*Render.xScale), Math.round(390*Render.yScale), Math.round(100*Render.xScale), Math.round(39*Render.yScale));
		Render.g.drawString("FPS: "+Render.tempframes,Math.round(25*Render.xScale),Math.round(25*Render.yScale));
		Render.g.drawString("Wanted fps:",Math.round(25*Render.xScale),Math.round(50*Render.yScale));
		for(byte x = 0; x < 4; x++){
			Render.g.drawRect(Math.round((26*x+88)*Render.xScale),Math.round(45*Render.yScale),Math.round(25*Render.xScale),Math.round(25*Render.yScale));
			Render.g.drawString(""+(x+1)*15, Math.round((26*x+93)*Render.xScale), Math.round(60*Render.yScale));
			Render.g.setColor(Color.black);
			if(Render.wantedfps == (x+1)*15){
				Render.g.fillRect(Math.round((26*x+88)*Render.xScale),Math.round(45*Render.yScale),Math.round(26*Render.xScale),Math.round(26*Render.yScale));
			}
			Render.g.setColor(Color.white);
		}
		Render.g.drawString("Fps Last 30sec",Math.round(150*Render.xScale),Math.round(250*Render.yScale));
		Render.g.drawLine(Math.round(25*Render.xScale),Math.round(400*Render.yScale) , Math.round(325*Render.xScale), Math.round(400*Render.yScale));
		Render.g.drawLine(Math.round(25*Render.xScale),Math.round(400*Render.yScale), Math.round(25*Render.xScale), Math.round(250*Render.yScale));
		Render.g.setColor(Color.green);
		Render.g.drawLine(Math.round(25*Render.xScale), Math.round((2*(-Render.wantedfps)+400)*Render.yScale), Math.round(325*Render.xScale), Math.round((2*(-Render.wantedfps)+400)*Render.yScale));
		Render.g.setColor(Color.red);
		for(byte graph = 0; graph < Render.fpsgraph.length-1; graph++){
			if(Render.fpsgraph[graph] != 0){
			Render.g.drawLine(Math.round((10*(graph+1)+15)*Render.xScale),Math.round((-(Render.fpsgraph[graph]*2)+400)*Render.yScale),Math.round((10*(graph+2)+15)*Render.xScale),Math.round((-(Render.fpsgraph[graph+1]*2)+400)*Render.yScale));
			}
		}
		Render.g.setColor(Color.white);
		
	}
	
	public void mouse(){
		if(Mouse.moveOver(Mouse.mx, Mouse.my, Math.round(388*Render.xScale), Math.round(390*Render.yScale), Math.round(100*Render.xScale), Math.round(39*Render.yScale)) && Mouse.clicked){
			Render.state = "Menu";
			try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(byte x = 0; x < 4; x++){
			if(Mouse.moveOver(Mouse.mx, Mouse.my, Math.round((26*x+88)*Render.xScale),Math.round(45*Render.yScale),Math.round(25*Render.xScale),Math.round(25*Render.yScale))&& Mouse.clicked){
				Render.fpslimit = (byte) ((x+1) * 15);
				Render.wantedfps = (byte) ((x+1)*15);
				
			}
		}
	}
}
