package com.sploder12.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import com.sploder12.main.screens.Enemies;


public class WaveMaker {
	public static Enemies[][] waves = Enemies.arrayOfDefault(100); //how many waves | how many enemies per wave MAX
	
	public static String file;
	public static byte currentwave;
	public static byte wavepart = 1;
	public static int selectedenemy = 0; //by deafult index of D4 then D6...
	public static final byte[][] waittime= new byte[100][20];
	public WaveMaker(){
		
	}
	
	public static void saveas(String name){
		try{
			BufferedWriter output = null;
	        File file = new File("waves\\"+ name +".wv");
	        output = new BufferedWriter(new FileWriter(file));
	        for(byte wavenum = 0; wavenum < 100; wavenum++){
	        	for(byte enemy = 0; enemy < 100; enemy++){
	        		String saving = waves[wavenum][enemy].name();	//gets the value of the waves enemies and converts them into ASCii
        			int out = Enemies.valueOf(saving).ordinal() + 32;
        			if(out >= 51){
        				output.append((char)out);
        				for(int check = enemy; check < waves[wavenum].length; check++){
        					if(waves[wavenum][check] != Enemies.t59){
        						check = waves[wavenum].length+1;
        					}else if(check == waves[wavenum].length-1){
        						output.append('');
        						enemy = 99;
        					}
        				}	
        			}else{
        				output.append((char)out);
        			}
        			if((enemy+1)%5 == 0 && enemy != 0){
        				output.append('~');
        				if(waittime[wavenum][enemy/5] != 0){
        				output.append((char)(waittime[wavenum][enemy/5]+32));
        				}else{
        					output.append((char)80);
        				}
        				output.append('~');
        			}
	        	} 
	        	output.append('|');
	        }
	        output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void load(String wave){
		wave = wave + ".wv";
		try{
			boolean reading = true;
			int enemies = 0;
			int wavenumb = 0;
			boolean waitinput = false;
			byte winput = 0;
			
        	InputStream wavefile = new FileInputStream("waves/"+wave); 
        	while(reading){
        		Enemies redest = null;
        		int red = wavefile.read() -32;
        		
        		if((red < 0 || wavenumb >= 100)){
        			reading = false;
        			
        		}else if(red == 94){
        			waitinput = true;
        			
        		}
        		if(reading){
        			if(!waitinput){
        				if(red != -31){
        					redest = Enemies.values()[red];
        				}else{
        					wavenumb++;
        					enemies = 0;
        				}
        				if(enemies < 100){
        					
        					waves[wavenumb][enemies] = redest;
        					System.out.println(waves[wavenumb][enemies]);
        					enemies++;
        				}else{
        					wavenumb++;
        					enemies = 0;
        				}
        				
        			}else if(red != 94 && enemies >= 5){
        				waittime[wavenumb][(enemies/5)-1] = (byte)red;
        			}else{
        				if(winput == 1){
        					waitinput = false;
        					winput = 0;
        				}else{
        					winput++;
        				}
        			}
        		}
        	
        	}
        	wavefile.close();
        }catch(Exception e){
        	
        	e.printStackTrace();
        }
	}
}
