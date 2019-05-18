package com.sploder12.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter{
	public static int my, mx;
	public static boolean clicked;
	
	public void mousePressed(MouseEvent e)
	{
		
		mx = e.getX();
		my = e.getY();
		clicked = true;
		if(Render.state == "MapMakeUI"){
			MapMaker.place(mx, my);
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
		
		clicked = false;
		mx = 5000000;
		my = mx;
	}
	
	public static boolean moveOver(int mx, int my,int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			return(my > y && my < y + height);
		} else return false;
	}
}
