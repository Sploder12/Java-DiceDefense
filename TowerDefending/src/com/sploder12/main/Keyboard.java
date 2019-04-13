package com.sploder12.main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.sploder12.main.screens.Save;




public class Keyboard implements KeyListener{
public static boolean[] curinpt = new boolean[2]; //see the screens for their index
private char key;

	@Override
	public void keyPressed(KeyEvent e) {
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		key = e.getKeyChar();
		if((int)e.getKeyChar()  == 8){
			if(curinpt[0]){
			Save.name = "";
			}
		} else if ((int)e.getKeyChar()  != 8){
			if(curinpt[0]){
			Save.name = Save.name + key;
			}
		}
	}
	
	public void delete(){
		
	}
	
	
}
