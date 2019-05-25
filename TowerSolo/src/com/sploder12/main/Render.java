package com.sploder12.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import objects.*;

public class Render extends Canvas implements Runnable{
	private static final long serialVersionUID = -9013264526583867430L;
	public static final int tW = 16,tH = 16; // tile width & tile height
	public static int WIDTH = 1000, HEIGHT = 900; //Play Area is 768x768
	public static float xScale, yScale;
	public static Thread render;
	private Mouse mouse;
	private Keyboard keyboard;
	public boolean rendering = false;
	public static Graphics2D g;
	public static Font newFont, currentFont;
	public static BufferedImage tileset,enemies;
	public Image enemiescale,tilescale;
	public static byte fpslimit = 47, wantedfps = 45;
	public volatile static String state = "Menu";
	public static int[] fpsgraph = new int[30];
	public static final String Version = "V0.0.1";
	public static int mapselect = 0;
	public static WaveManager waving;
	
	public Render(){
		mouse = new Mouse();
		this.addMouseMotionListener(mouse);
		this.addMouseListener(mouse);
		keyboard = new Keyboard();	//starting mouse and keyboard listeners
		this.addKeyListener(keyboard);
		
		try{
		tileset = ImageIO.read(new File("resources\\tileset.png")); //loads tilesets
		enemies = ImageIO.read(new File("resources\\enemies.png"));
		enemiescale = enemies.getScaledInstance(Math.round(160*xScale), -1, Image.SCALE_SMOOTH);
		tilescale = tileset.getScaledInstance(Math.round(320*xScale), -1, Image.SCALE_SMOOTH);
		
		}catch(Exception e){
			System.out.println(e);
		}
		new Window(WIDTH, HEIGHT, "Now with more dice dicing!", this);
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
	
	protected void drawTile(Graphics g, Tiles t, int x, int y){
        int mx = t.ordinal()%20;
        int my = t.ordinal()/20;	//draws the tiles in the location
        g.drawImage(tilescale, (int)Math.round(x*xScale), (int)Math.round(y*yScale), (int)Math.round((x+tW)*xScale),(int)Math.round((y+tH)*yScale),(int)Math.round((mx*tW)*xScale), (int)Math.round((my*tH)*yScale),  (int)Math.round((mx*tW+tW)*xScale), (int)Math.round((my*tH+tH)*yScale), this);
	}
	
	protected void drawEnemy(Graphics g, Enemies t, int x, int y){
        int mx = t.ordinal()%10;
        int my = t.ordinal()/10;	//draws the tiles in the location
        g.drawImage(enemiescale, (int)Math.round(x*xScale), (int)Math.round(y*yScale), (int)Math.round((x+tW)*xScale),(int)Math.round((y+tH)*yScale),(int)Math.round((mx*tW)*xScale), (int)Math.round((my*tH)*yScale),  (int)Math.round((mx*tW+tW)*xScale), (int)Math.round((my*tH+tH)*yScale), this);
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
			if(rendering){
				render();	
			}
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
				int[] tempgraph = new int[30];
				tempgraph = fpsgraph;
				for(byte shift = 0; shift < fpsgraph.length; shift++){
					if(shift >= 29){
						fpsgraph[shift] = tempframes;
					}else{
						fpsgraph[shift] = tempgraph[shift+1];
					}
				}
				
				frames = 0;
			}
		}
		stop();
	}
	
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);          //Makes the FPS not 31mil also prevents flashing
			return;
		}
		 g =  (Graphics2D) bs.getDrawGraphics(); 
		 
		currentFont = g.getFont();
		   newFont = currentFont.deriveFont(xScale*(currentFont.getSize() * 0.9F)); 
		  g.setFont(newFont);
		  g.setColor(Color.black);
		  g.fillRect(0, 0, WIDTH, HEIGHT);
		  
		  if(state == "DefualtMaps"){
			  for(int i=0;i<24;i++){
				  for(int j=0;j < 24;j++){
					  drawTile(g, Main.file_map[j][i], (i*tW)+Math.round(27*xScale),(j*tH)); //draws the tiles
				  }
			  }
		  }else if(state == "GameTime"){
			  for(int i=0;i<24;i++){
				  for(int j=0;j < 24;j++){
					  drawTile(g, Main.file_map[j][i], (i*tW),(j*tH)); //draws the tiles
				  }
			  }
		  }
		  
		  
		 
		  try{
			  String rendstate = "screens." +state;
			  Class<?> cls = Class.forName(rendstate);	//gets the screen to render from com.sploder.main.screens
			  cls.newInstance();
		  } catch(Exception e){
			  System.out.println(e);
		  }
		  if(state == "GameTime" && WaveManager.canreturn){
			  Enemy[] enemingus = waving.getEnemies(); 
			  for(int showenemies = 0; showenemies < enemingus.length; showenemies++){
				 if(enemingus[showenemies] != null){
					  //System.out.println(enemingus[showenemies].visible);
					  if(enemingus[showenemies].visible){
						  drawEnemy(g, enemingus[showenemies].getEnemy(),enemingus[showenemies].getEnemyXCord(),enemingus[showenemies].getEnemyYCord());
					  } 
				 }
			 }
		  }
		  

		  
		  
		  
		  
		  g.dispose();
		  
	    	bs.show();
	    	
	    try {
	    	if(fpslimit > 0 && fpslimit < 254){
	    		Thread.sleep(1000/(fpslimit));			//frame limiter
	    	}else{
	    		Thread.sleep(17);
	    	}
		} catch (InterruptedException e) {	
			e.printStackTrace();
		}
	}
}

