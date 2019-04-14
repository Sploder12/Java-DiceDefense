package com.sploder12.main;

import java.awt.Canvas;
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;

public class Main extends Canvas implements Runnable{

	private static final long serialVersionUID = 6449263401446869271L;

	private Thread thread;
	public boolean running = false;
	public static String state = "MapMakeUI";
	
	public Main(){
		start();
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;     
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run(){
		while(running && state != "end"){
			
		}
		stop();
	}
	
	public static void main(String[]args){
		//new MapMaker("test");
		new Main();
		new Render();
	}
	
}
