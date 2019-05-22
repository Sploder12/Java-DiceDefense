package objects;

import com.sploder12.main.Main;
import com.sploder12.main.Render;

import sploder12.json.JSON;
import objects.Enemies;
public class Enemy implements Runnable{
	private Thread enemie;
	private final int[] ENEMYINDEX= {10,75,139,204};
	private final String[] ENEMYNAME = {"D6","D4","D8","D10"};
	private JSON json;
	private String name = "D6";
	private int hp = 0,speed = 0,Baddie = 0;
	private String special = "none";
	private byte xcord = -10, ycord = -10, prevxcord=0, prevycord=0;
	private byte xend = -10, yend = -10;
	private int xdisp = 0, ydisp = 0;
	public boolean running = false;
	public boolean visible = false;
	public Enemies enalme;
	private boolean sleeping = false;
	
	public boolean getVisible(){
		return visible;
	}
	
	public int getEnemyXCord(){
		return xdisp;
	}
	
	public void setEnemyXCord(int xcorde){
		xdisp = xcorde;
	}
	public int getEnemyYCord(){
		return ydisp;
	}
	public void setEnemyYCord(int ycorde){
		ydisp = ycorde;
	}
	
	public int getEnemyHp(){
		return hp;
	}
	
	public Enemies getEnemy(){
		return enalme;
	}
	
	public synchronized void setEnemyHp(int newHp){
		hp = newHp;
	}
	
	public synchronized void wakeup(){
		synchronized(Enemy.this){
			this.notify();	
		}
	}
	
	public Enemy(Enemies enemy, byte xstart, byte ystart, byte xEnd, byte yEnd){
		
		json = new JSON();
		enalme = enemy;
		xcord = xstart;
		ycord = ystart;
		xend = xEnd;
		yend = yEnd;
		Baddie = ENEMYINDEX[enemy.ordinal()];
		name = ENEMYNAME[enemy.ordinal()];
		if(enemy.ordinal() == 0){
			name = "D6";
		}
		hp = json.getIntValueOfDict(Main.Enemiefile, json.locateStringEnd(Main.Enemiefile,"hp",Baddie));
		speed =json.getIntValueOfDict(Main.Enemiefile, json.locateStringEnd(Main.Enemiefile,"speed",Baddie));
		special = json.getValueOfDict(Main.Enemiefile, json.locateStringEnd(Main.Enemiefile,"special",Baddie));
		enemie = new Thread(this);
		enemie.start();
	}
	@Override
	public synchronized void run() {
		try {
			synchronized(Enemy.this){
				this.wait();
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		while(hp > 0){
			visible = true;
			
			
			AI();
			xdisp = xcord;
			ydisp = ycord;
			
			if(Baddie == 10){
				speed = (-hp+6)*8+16;
			}
			try {
		    	Thread.sleep(-speed+128);
			} catch (InterruptedException e) {	
				e.printStackTrace();
			}
		}
		try {
			visible = false;
			enemie.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void AI(){
		if(ycord != 0 && xcord != 0){
			if(Main.file_mappath[ycord][xcord+1] == Paths.TexPath && xcord+1 != prevxcord){
				prevxcord = xcord;
				prevycord = -10;
				xcord = (byte)(xcord+1);
			}else if(Main.file_mappath[ycord][xcord-1] == Paths.TexPath && xcord-1 != prevxcord){
				prevxcord = xcord;
				prevycord = -10;
				xcord = (byte)(xcord-1);
			}else if(Main.file_mappath[ycord+1][xcord] == Paths.TexPath && ycord+1 != prevycord){
				prevycord = ycord;
				prevxcord = -10;
				ycord = (byte)(ycord+1);
			}else if(Main.file_mappath[ycord-1][xcord] == Paths.TexPath && ycord-1 != prevycord){
				prevycord = ycord;
				prevxcord = -10;
				ycord = (byte)(ycord-1);
			}
		}else{
			if(Main.file_mappath[ycord][xcord+1] == Paths.TexPath && xcord+1 != prevxcord){
				prevxcord = xcord;
				xcord = (byte)(xcord+1);
			}else if(Main.file_mappath[ycord+1][xcord] == Paths.TexPath && ycord+1 != prevycord){
				prevycord = ycord;
				ycord = (byte)(ycord+1);
			}
		}
		
		
	}
}
