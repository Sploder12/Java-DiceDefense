package com.sploder12.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;

import com.sploder12.main.screens.Tiles;

public class MapMaker{
   
	public static String filetoload;
    public static int selectedTile = 0;
    public static final Tiles file_map[][] = {{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},
    	{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null},{Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null,Tiles.Null}};
    //All cells set to Null so there is no nullpointer error
    
    public MapMaker(String map){
        load(map);
    }
    
    public static void place(int mx, int my){
        int cellx = (int)Math.ceil(mx / 32.0)- 1;
        int celly = (int)Math.ceil(my / 32.0) - 1; //takes the current mouse postition and converts it to an index
        if(mx <= 768 && my <= 768){
        	Tiles placing = Tiles.values()[selectedTile];
        	file_map[celly][cellx] = placing;
        }
    }
    
    public static boolean load(String map){
    	try{
        	map = map + ".wd";
        	InputStream mapfile = new FileInputStream("maps/"+map);   
        	for(byte y = 0; y <24; y++){
        		for(byte x = 0; x <24; x++){
        			int z = mapfile.read(); 	//This is going to be fun (see Tiles.java for ASCii data)
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


    public static boolean saveas(String name){
        try
        {
        	BufferedWriter output = null;
	        File file = new File("Maps\\"+ name +".wd");
	        output = new BufferedWriter(new FileWriter(file));
	        for(byte y = 0; y < 24; y++){
	        	for(byte x = 0; x < 24; x++){
	        		String saving = file_map[y][x].name();	//gets the value of the maps tiles and converts them back into ASCii
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
