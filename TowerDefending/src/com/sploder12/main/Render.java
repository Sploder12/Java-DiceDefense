package com.sploder12.main;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import com.sploder12.main.screens.*;

public class Render extends Canvas implements Runnable{
	
	public static final int tW = 16,tH = 16; // tile width & tile height
	private static final long serialVersionUID = -5376655677283171262L;
	public static int WIDTH = 1000, HEIGHT = 900; //Play Area is 768x768
	public static float xScale, yScale;
	public static Thread render;
	private Mouse mouse;
	private Keyboard keyboard;
	public boolean rendering = false;
	public static Graphics2D g;
	public static Font newFont, currentFont;
	public static BufferedImage tileset, paths,enemies;
	public Image enemiescale,pathscale,tilescale;
	public static byte fpslimit = 47, wantedfps = 45;
	public static String state = "Menu";
	public static String prevstate = "Menu";
	
	public Render(){
		
		mouse = new Mouse();
		this.addMouseListener(mouse);
		keyboard = new Keyboard();	//starting mouse and keyboard listeners
		this.addKeyListener(keyboard);
		
		try{
		tileset = ImageIO.read(new File("resources\\tileset.png")); //loads tilesets
		paths = ImageIO.read(new File("resources\\paths.png"));
		enemies = ImageIO.read(new File("resources\\enemies.png"));
		enemiescale = enemies.getScaledInstance(Math.round(160*xScale), -1, Image.SCALE_SMOOTH);
		pathscale = paths.getScaledInstance(Math.round(64*xScale), -1, Image.SCALE_SMOOTH);
		tilescale = tileset.getScaledInstance(Math.round(320*xScale), -1, Image.SCALE_SMOOTH);
		
		}catch(Exception e){
			System.out.println(e);
		}
		new Window(WIDTH, HEIGHT, "Now With Extra Scales!", this);
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
				frames = 0;
			}
		}
		stop();
	}
	
	protected void drawTile(Graphics g, Tiles t, int x, int y){
        int mx = t.ordinal()%20;
        int my = t.ordinal()/20;	//draws the tiles in the location
        g.drawImage(tilescale, (int)Math.round(x*xScale), (int)Math.round(y*yScale), (int)Math.round((x+tW)*xScale),(int)Math.round((y+tH)*yScale),(int)Math.round((mx*tW)*xScale), (int)Math.round((my*tH)*yScale),  (int)Math.round((mx*tW+tW)*xScale), (int)Math.round((my*tH+tH)*yScale), this);
        Tiles z = Tiles.values()[MapMaker.selectedTile];
        mx = z.ordinal()%20;
        my = z.ordinal()/20;
        g.drawImage(tilescale, (int)Math.round(433*xScale), (int)Math.round(108*xScale), (int)Math.round((433+tW)*xScale), (int)Math.round((108+tH)*yScale),(int)Math.round((mx*tW)*xScale), (int)Math.round((my*tH)*yScale),  (int)Math.round((mx*tW+tW)*xScale), (int)Math.round((my*tH+tH)*yScale), this);
    }
	
	protected void drawPath(Graphics g, Paths t, int x, int y){
        int mx = t.ordinal()%4;	//draws the tiles in the location
        int my = t.ordinal()/4;
        g.drawImage(pathscale, (int)Math.round(x*xScale), (int)Math.round(y*yScale), (int)Math.round((x+tW)*xScale),(int)Math.round((y+tH)*yScale),(int)Math.round(((mx*tW)*xScale)), (int)Math.round((my*tH*yScale)),  (int)Math.round((mx*tW+tW)*xScale), (int)Math.round((my*tH+tH)*yScale), this);
    }

	protected void drawEnemy(Graphics g, Enemies t, int x, int y){
		int mx = t.ordinal()%10;
		int my = t.ordinal()/10;
		g.drawImage(enemiescale, (int)Math.round(x*xScale),(int)Math.round(y*yScale), (int)Math.round((x+tW*4)*xScale), (int)Math.round((y+tH*4)*yScale),(int)Math.round((mx*tW)*xScale), (int)Math.round((my*tH)*yScale),  (int)Math.round((mx*tW+tW)*xScale), (int)Math.round((my*tH+tH)*yScale), this);
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
					  drawTile(g, MapMaker.file_map[j][i], (i*tW),(j*tH)); //draws the tiles
			  		  drawPath(g, MapMaker.file_mappath[j][i], (i*tW),(j*tH));
				  }
			  }
			  
		  }else if(state == "WaveMake"){
			  for(int i = 0; i < 5; i++){
				  drawEnemy(g,WaveMaker.waves[WaveMaker.currentwave][((WaveMaker.wavepart-1)*5)+i],i*64+26,75);
			  }
			  drawEnemy(g,Enemies.values()[WaveMaker.selectedenemy],408, 108);
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
	
	
		public static void main(String[] args) {
			WIDTH = Integer.parseInt(args[0]);
			HEIGHT = Integer.parseInt(args[1]);
			xScale = WIDTH/500F; 
			yScale = HEIGHT/450F;
			new Render();
		}
}
