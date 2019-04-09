package com.sploder12.main;

import java.awt.Canvas;


public class Main extends Canvas implements Runnable{

	private static final long serialVersionUID = 6449263401446869271L;
	private Mouse mouse;
	private Thread thread;
	public boolean running = false;
	public static String state = "start";
	
	public Main(){
		mouse = new Mouse();
		this.addMouseListener(mouse);
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
		while(running){
			game();
		}
		stop();
	}
	
	
	public boolean game(){
		
		
		return false;
	}
	
	
	public static void main(String[]args){
		new Main();
		new Render();
	}
	
}
