package com.sploder12.main;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.Image;
//import java.awt.Toolkit;



import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;

import javax.imageio.ImageIO;






import com.sploder12.main.screens.*;

public class Render extends Canvas implements Runnable{
	
	private static final int tW = 32; // tile width
    private static final int tH = 32; // tile height
	private static final long serialVersionUID = -5376655677283171262L;
	public static final int WIDTH = 1000, HEIGHT = 900; //Play Area is 768x768
	private Thread render;
	private Mouse mouse;
	private Keyboard keyboard;
	public boolean rendering = false;
	public static Graphics g;
	public static Font newFont;
	public static Font currentFont;
	private Image tileset;
	
	public Render(){
		mouse = new Mouse();
		this.addMouseListener(mouse);
		keyboard = new Keyboard();
		this.addKeyListener(keyboard);
		new MapMaker("template");
		try{
		tileset = ImageIO.read(new File("resources\\tileset.png"));
		}catch(Exception e){
			System.out.println(e);
		}
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
	
	protected void drawTile(Graphics g, Tiles t, int x, int y){
        int mx = t.ordinal()%20;
        int my = t.ordinal()/20;
        g.drawImage(tileset, x, y, x+tW, y+tH,
                mx*tW, my*tH,  mx*tW+tW, my*tH+tH, this);
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
		  
		  
		  try{
			  String rendstate = "com.sploder12.main.screens." +Main.state;
			  //System.out.println(rendstate);
			  Class<?> cls = Class.forName(rendstate);
			  cls.newInstance();
		  } catch(Exception e){
			  System.out.println(e);
		  }
		  if(Main.state == "MapMakeUI"){
			  for(int i=0;i<24;i++)
				  for(int j=0;j < 24;j++)
					  drawTile(g, MapMaker.file_map[j][i], i*tW,j*tH);
		  }
		  
		  
		  g.dispose();
	    	bs.show();
	}
	
	
}
