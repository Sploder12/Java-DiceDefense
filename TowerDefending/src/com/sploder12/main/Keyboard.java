package com.sploder12.main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.sploder12.main.screens.*;

public class Keyboard implements KeyListener{
public static boolean[] curinpt = new boolean[4]; //see the screens for their index

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		if((int)e.getKeyChar()  == 8){
			if(curinpt[0]){				//8 is backspace
			Save.name = "";
			} else if(curinpt[1]){
				Load.name = "";
			} else if(curinpt[2]){
				SaveWv.name = "";
			} else if(curinpt[3]){
				LoadWv.name = "";
			} 
		} else if ((int)e.getKeyChar()  != 8){
			if(curinpt[0]){
			Save.name = Save.name + key;
			} else if(curinpt[1]){
				Load.name = Load.name + key;
			}else if(curinpt[2]){
				SaveWv.name = SaveWv.name + key;
			}else if(curinpt[3]){
				LoadWv.name = LoadWv.name + key;
			}
		}
	}
	
}
