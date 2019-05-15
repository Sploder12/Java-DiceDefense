package com.sploder12.main;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import com.sploder12.main.screens.*;

public class Render extends Canvas implements Runnable{
	
	public static final int tW = 32; // tile width
    public static final int tH = 32; // tile height
	private static final long serialVersionUID = -5376655677283171262L;
	public static final int WIDTH = 1000, HEIGHT = 900; //Play Area is 768x768
	public static Thread render;
	private Mouse mouse;
	private Keyboard keyboard;
	public boolean rendering = false;
	public static Graphics g;
	public static Font newFont;
	public static Font currentFont;
	public static Image tileset;
	public static Image paths;
	public static byte fpslimit = 62;
	public static byte wantedfps = 60;
	public static String state = "Menu";
	
	public Render(){
		mouse = new Mouse();
		this.addMouseListener(mouse);
		keyboard = new Keyboard();	//starting mouse and keyboard listeners
		this.addKeyListener(keyboard);
		
		try{
		tileset = ImageIO.read(new File("resources\\tileset.png")); //loads tileset
		paths = ImageIO.read(new File("resources\\paths.png"));
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
	
	public static int tempframes;
	public void run(){
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(rendering){
			long now = System.nanoTime();		//loops the rendering
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
				//System.out.println(fpslimit);
				if(frames > wantedfps+2 && fpslimit > 2){
					fpslimit -= 2;
				}else if(frames < wantedfps-2 && fpslimit < 254){ //framerate stablizer
					fpslimit += 2;
				}
				tempframes = frames;
				frames = 0;
			}
		}
		stop();
	}
	
	protected void drawTile(Graphics g, Tiles t, int x, int y){
        int mx = t.ordinal()%20;
        int my = t.ordinal()/20;	//draws the tiles in the location
        g.drawImage(tileset, x, y, x+tW, y+tH,mx*tW, my*tH,  mx*tW+tW, my*tH+tH, this);
        Tiles z = Tiles.values()[MapMaker.selectedTile];
        mx = z.ordinal()%20;
        my = z.ordinal()/20;
        g.drawImage(tileset, 865, 215, 865+tW, 215+tH,mx*tW, my*tH,  mx*tW+tW, my*tH+tH, this);
    }
	
	protected void drawPath(Graphics g, Paths t, int x, int y){
        int mx = t.ordinal()%20;
        int my = t.ordinal()/20;	//draws the tiles in the location
        g.drawImage(paths, x, y, x+tW, y+tH,mx*tW, my*tH,  mx*tW+tW, my*tH+tH, this);
    }

	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);          //Makes the FPS not 31mil also prevents flashing
			return;
		}
		 g = bs.getDrawGraphics();
		 currentFont = g.getFont();
		   newFont = currentFont.deriveFont(currentFont.getSize() * 1.8F); 
		  g.setFont(newFont);
		  g.setColor(Color.black);
		  g.fillRect(0, 0, WIDTH, HEIGHT);
		  try{
			  String rendstate = "com.sploder12.main.screens." +state;
			  Class<?> cls = Class.forName(rendstate);	//gets the screen to render from com.sploder.main.screens
			  cls.newInstance();
		  } catch(Exception e){
			  System.out.println(e);
		  }
		  if(state == "MapMakeUI"){
			  for(int i=0;i<24;i++){
				  for(int j=0;j < 24;j++){
					  drawTile(g, MapMaker.file_map[j][i], i*tW,j*tH); //draws the tiles
			  		  drawPath(g, MapMaker.file_mappath[j][i], i*tW,j*tH);
				  }
			  }
		  }
		  g.dispose();
	    	bs.show();
	    try {
	    	if(fpslimit > 0 && fpslimit < 1000){
	    		Thread.sleep(1000/(fpslimit));			//frame limiter
	    	}else{
	    		Thread.sleep(17);
	    	}
		} catch (InterruptedException e) {	
			e.printStackTrace();
		}
	}
		public static void main(String[] args) {
			new Render();
		}
}
