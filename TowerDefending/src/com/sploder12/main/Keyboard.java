package com.sploder12.main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import com.sploder12.main.screens.*;

public class Keyboard implements KeyListener{
public static boolean[] curinpt = new boolean[5]; //see the screens for their index
public static String tempstring = "";
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
			}else if(curinpt[4]){
				tempstring = tempstring +key;
				try{
					WaveMaker.waittime[WaveMaker.currentwave][WaveMaker.wavepart-1] = Short.parseShort(tempstring);
					if(WaveMaker.waittime[WaveMaker.currentwave][WaveMaker.wavepart-1] > 32767){
						WaveMaker.waittime[WaveMaker.currentwave][WaveMaker.wavepart-1] = 32767;
						tempstring = "32767";
					}
				}catch(Exception s){
					tempstring = "";
				}
			}
		}
	}
	
}
