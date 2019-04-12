package com.sploder12.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter{
	public static int my;
	public static int mx;
	
	public void mousePressed(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();


		if(Main.state == "MapMakeUI"){
			MapMaker.place(mx, my);
		}
	
	}
	
	public void mouseReleased(MouseEvent e)
	{
		
	}
	
	public static boolean moveOver(int mx, int my,int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			} else return false;
		} else return false;
	}
	
}
