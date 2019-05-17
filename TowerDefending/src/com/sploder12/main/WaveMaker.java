package com.sploder12.main;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import com.sploder12.main.screens.Enemies;

import sploder12.json.JSON;


public class WaveMaker {
	public static Enemies[][] waves = Enemies.arrayOfDefault(100); //how many waves | how many enemies per wave MAX
	
	public static String file;
	public static byte currentwave;
	public static byte wavepart = 1;
	public static int selectedenemy = 0; //by deafult index of D4 then D6...
	public static final short[] enemies= {521,10,586,457};
	public WaveMaker(){
		JSON json = new JSON();
		file = json.convertToString("Enemies.json");
		String[] enemies = {"D6","D8","D4","D10"};
		int[][] test =json.findAllIndexOfStrings(file, enemies);
		System.out.println(Arrays.toString(test));
	}
	
	public void saveas(String name){
		name = name + ".wv";
	}
	
	public void load(String wave){
		wave = wave + ".wv";
		try{
			boolean reading = true;
			int enemies = 0;
			int wavenumb = 0;
        	InputStream wavefile = new FileInputStream("waves/"+wave); 
        	while(reading){
        		
        		int red = wavefile.read() -32;
        		Enemies redest = Enemies.values()[red];
        		if(red < 0){
        			reading = false;
        			
        		}else{
        			if(red == 92){ //seperated by |
        				wavenumb++;
        			}else{
        				waves[wavenumb][enemies] = redest;
        				enemies++;
        			}
        		}
        	}
        	wavefile.close();
        }catch(Exception e){
        	System.out.println(e);
        }
	}
}
