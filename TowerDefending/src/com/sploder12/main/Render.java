package com.sploder12.main;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;

public class Render extends Canvas implements Runnable{
	private static final long serialVersionUID = -5376655677283171262L;
	public static final int WIDTH = 800, HEIGHT = 600;
	private Thread render;
	public boolean rendering = false;
	public static Graphics g;
	public static Font newFont;
	public static Font currentFont;
	
	public Render(){
		new Window(WIDTH, HEIGHT, "Defending Time", this);
	}
	
	public synchronized void start(){
		render = new Thread(this);
		render.start();
		rendering = true;
	}
	
	public synchronized void stop(){
		try{
			render.join();
			rendering = false;     
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	int tempframes;
	public void run(){
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(rendering){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				delta--;                               
			}
			if(rendering)
				render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("(Graphic)FPS: " + frames);  
				tempframes = frames;
				frames = 0;
			}
		}
		stop();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);          //Makes the FPS not 31mil
			return;
		}
		 g = bs.getDrawGraphics();

		 currentFont = g.getFont();
		   newFont = currentFont.deriveFont(currentFont.getSize() * 1.8F); 
		  g.setFont(newFont);
		  g.setColor(Color.black);
		  g.fillRect(0, 0, WIDTH, HEIGHT);
	
		  
		  
		  
		  g.dispose();
	    	bs.show();
	}
	
	
}
