package com.sploder12.main;


import java.io.FileInputStream;
import java.io.InputStream;




import objects.*;
import sploder12.json.JSON;

public class Main implements Runnable{
	public JSON json;
	public boolean logic = false;
	public static Thread main;
	public static String Enemiefile = "";
	public static byte currentwave = 0;
	public static Enemies[][] waves = Enemies.arrayOfDefault(100);
	public static short[][] waittime= new short[100][20];
	public static byte xstart, ystart, xend, yend;
	private final String[] LOADNAME = {"Grass_Fields","Watery_Way","template"};
	private final String[] WAVENAME = {};
	public static byte difficulty = 0;
	public static boolean reloadmap = true;
	public static final Tiles file_map[][] = {{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null}};
    public static final Paths file_mappath[][] = {{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},
    	{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},
    	{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},
    	{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},
    	{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},
    	{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},
    	{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null},{Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null,Paths.Null}};
    //All cells set to Null so there is no nullpointer error
	public Main(){
		
		json = new JSON();
		Enemiefile = json.convertToString("Enemies.json");
		
		
		
		start();
		
	}
	

	
	private void loadmap(String mapname, String wavename, boolean isdefault){
		try{
			if(isdefault){
				mapname = mapname + ".wdd";
			}else{
				mapname = mapname + ".wd";
			}
        	InputStream mapfile = new FileInputStream("maps/"+mapname);  
        	for(byte y = 0; y <48; y++){
        		for(byte x = 0; x <24; x++){
        			try{
        				int z = mapfile.read(); 	//This is going to be fun (see Tiles.java for ASCii data)
        				if(z >= 32 && y < 24){
        					file_map[y][x] = Tiles.values()[z-32];
        				} else if(z >= 32){
        					file_mappath[y-24][x] = Paths.values()[z-32];
        				}else{
        					if(y<24){
            					file_map[y][x] = Tiles.T;
            				}else{
            					file_mappath[y-24][x] = Paths.Null;
            				}
        				}
        			}catch(ArrayIndexOutOfBoundsException r){
        				System.out.println(r);
        			}
        		}	
        	}
        	mapfile.close();
        }catch(Exception e){
        	mapname = "template.wd";
        	System.out.println(e);
        }
		wavename = wavename + ".wv";
		try{
			boolean reading = true;
			int enemies = 0;
			int wavenumb = 0;
			boolean waitinput = false;
			short winput = 0;
			short total = 0;
        	InputStream wavefile = new FileInputStream("waves/"+wavename); 
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
        					enemies++;
        				}else{
        					wavenumb++;
        					enemies = 0;
        				}	
        			}else if(red != 94 && enemies >= 5){
        				 total = (short)(total+red);		
        			}else{
        				if(winput == 1){
        					waittime[wavenumb][(enemies/5)-1] = total;
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
		preparepath();
		
	}
	
	private void preparepath(){
		for(byte y = 0; y < file_mappath.length; y++){
			for(byte x = 0; x < file_mappath[y].length; x++){
				if(file_mappath[y][x] == Paths.TspcStart){
					xstart = x;
					ystart = y;
				}else if(file_mappath[y][x] == Paths.TspcStart){
					xend = x;
					yend = y;
				}
			}
		}
	}
	
	public synchronized void run() {
		while(logic){
			
			
			if(reloadmap){
				loadmap(LOADNAME[Render.mapselect],"test",true);
				Main.reloadmap = false;
			}
			
			try {
		    	if(Render.fpslimit > 0 && Render.fpslimit < 254){
		    		Thread.sleep(1000/(Render.fpslimit));			//frame limiter
		    	}else{
		    		Thread.sleep(17);
		    	}
			} catch (InterruptedException e) {	
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void start(){
		main = new Thread(this);
		main.start();
		logic = true;
	}
	
	public synchronized void stop(){
		try{
			main.join();
			logic = false;     
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Main();
		try{
			Render.WIDTH = Integer.parseInt(args[0]);
			Render.HEIGHT = Integer.parseInt(args[1]);
			}catch(Exception e){
				System.out.println("No Width or Heigh Args Given, Starting 1000x900");
			}
			Render.xScale = Render.WIDTH/500F; 
			Render.yScale = Render.HEIGHT/450F;
			new Render();
	}



	

}
