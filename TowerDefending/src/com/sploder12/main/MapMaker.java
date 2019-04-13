package com.sploder12.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import com.sploder12.main.screens.Tiles;


public class MapMaker{
   
	public static String filetoload;
    public static Tiles selectedTile = Tiles.T8Wooder;
    public static final Tiles file_map[][] = {{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null}};
    
    
    
    
    public MapMaker(String map){
        load(map);
        
        
        //for(int y = 0; y < 24; y++){
          //  for(int x = 0; x < 24; x++){
             //   cur_map[x][y] = file_map[x][y];
           // }
        //}   
        
        //save();
    }
    
    public static void place(int mx, int my){
    	
        int cellx = (int)Math.ceil(mx / 32.0)- 1;
        int celly = (int)Math.ceil(my / 32.0) - 1;
        if(mx <= 768 && my <= 768){
        	file_map[celly][cellx] = selectedTile;
        }
    }
    
    public static boolean load(String map){
    	try{
        	
        	map = map + ".wd";
        	InputStream mapfile = new FileInputStream("maps/"+map);
        	
        	for(int y = 0; y <24; y++){
        		for(int x = 0; x <24; x++){
        			int z = mapfile.read(); 	//This is going to be fun (see Tiles.java for ASCii data)
        			//System.out.println(z);
        			//byte b = 127;
        			//char t = '?';
        			//System.out.println((char)b);
        			//System.out.println((byte)t);
        			//System.out.println(Tiles.values()[z-65]);
        			file_map[y][x] = Tiles.values()[z-32];
        			
        			
        		}	
        	}
        	mapfile.close();
        	return true;
        }catch(Exception e){
        	map = "template.wd";
        	System.out.println(e);
        	return false;
        }
    }


    public static boolean saveas(){
        try
        {
        	String newmapname = "test";
        	BufferedWriter output = null;
	        File file = new File("Maps\\"+ newmapname +".wd");
	        output = new BufferedWriter(new FileWriter(file));
	        for(byte y = 0; y < 24; y++){
	        	for(byte x = 0; x < 24; x++){
	        		String saving = file_map[y][x].name();
	        		int z = Tiles.valueOf(saving).ordinal() + 32;
	        		output.append((char)z);
	        	}
	        }
	        output.close();
            return true;
        } catch (Exception e)
        {
        	System.out.println(e);
            return false;
        }
    }




}
