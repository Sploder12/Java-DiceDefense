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
	public static short[][] waittime= new short[100][20];
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
        				if(waittime[wavenum][((enemy+1)/5)-1] != 0){
        					byte[] tobytes = new byte[(int) Math.ceil(waittime[wavenum][((enemy+1)/5)-1]/95)+1];
            				short tempnumb = waittime[wavenum][((enemy+1)/5)-1];		
        					for(short outw = 0; outw < tobytes.length; outw++){
        						System.out.println(tempnumb);
        						if(tempnumb >= 95){
        							tobytes[outw] = 95;
        							tempnumb -= 95;
        						}else{
        							tobytes[outw] = (byte) (tempnumb);
        						}
        						output.append((char)(tobytes[outw]+32));
        					}
        				
        				}else{
        					output.append((char)32);
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
			short winput = 0;
			short total = 0;
        	InputStream wavefile = new FileInputStream("waves/"+wave); 
        	while(reading){
        		Enemies redest = null;
        		int red = wavefile.read() -32;
        		if(((red < 0 && red != -31)|| wavenumb >= 100)){
        			reading = false;
        		}else if(red == 94 && !waitinput){	
        			waitinput = true;
        		}
        		if(reading){
        			
        			if(red == 92){
    					wavenumb++;
    					enemies = 0;
    				}
        			if(!waitinput){
        				if(red != -31){
        					redest = Enemies.values()[red];
        				}else{
        					enemies = 100;
        				}
        				if(enemies < 100){
        					
        					waves[wavenumb][enemies] = redest;
        					enemies++;
        				}else{
        					//wavenumb++;
        					enemies = 0;
        				}	
        			}else if(red != 94 && enemies >= 5){
        				System.out.println(total+ "+"+red);
        				 total = (short)(total+red);		
        			}else{
        				if(wavenumb < 100){
        					if(winput >= 1 && enemies >= 5){
        						waittime[wavenumb][(enemies/5)-1] = total;
        						total = 0;
        						waitinput = false;
        						winput = 0;
        					}else{
        						winput++;
        					}
        				}else{
        					reading = false;
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
