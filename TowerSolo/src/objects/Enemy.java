package objects;

import com.sploder12.main.Main;

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
	
	public boolean setEnemyHp(int newHp){
		if(hp > 0){
		hp = newHp;
		return true;
		}else{
			return false;
		}
	}
	
	public synchronized void wakeup(){
		synchronized(Enemy.this){
			this.notify();	
		}
	}
	
	public Enemy(Enemies enemy, byte xstart, byte ystart){
		
		json = new JSON();
		enalme = enemy;
		xdisp = xstart*16;
		ydisp = ystart*16;
		xcord = xstart;
		ycord = ystart;
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
	private boolean check2 = true;
	@Override
	public synchronized void run() {
		try {
			synchronized(Enemy.this){
				this.wait();
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		while(hp > 0 && Main.file_mappath[ycord+1][xcord] != Paths.TqtEnd && Main.file_mappath[ycord][xcord+1] != Paths.TqtEnd && check2){
			visible = true;
			
			
			AI();
			if(xdisp %16==0){
				xcord = (byte) (xdisp/16);
			}
			if(ydisp %16==0){
				ycord = (byte) (ydisp/16);
			}
			
			if(Baddie == 10){
				speed = (-hp+6)*8+16;
			}
			try {
		    	Thread.sleep((-speed+128)/5);
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
	private boolean incx=false,incy=false,decx=false,decy = false;
	public void AI(){
		
		if(xdisp%16 == 0 && ydisp%16 == 0){
			incx=false;
			incy=false;
			decx=false;
			decy=false;
			
		if(ycord != 0 && xcord != 0){
			if((Main.file_mappath[ycord][xcord+1] == Paths.TqtEnd || Main.file_mappath[ycord][xcord+1] == Paths.TexPath) && xcord+1 != prevxcord ){
				prevxcord = xcord;
				prevycord = -10;
				xdisp++;
				incx=true;
			}else if((Main.file_mappath[ycord][xcord-1] == Paths.TqtEnd || Main.file_mappath[ycord][xcord-1] == Paths.TexPath) && xcord-1 != prevxcord){
				if(Main.file_mappath[ycord][xcord-1] == Paths.TqtEnd){
					check2 = false;
				}
				prevxcord = xcord;
				prevycord = -10;
				xdisp--;
				decx=true;
			}else if((Main.file_mappath[ycord+1][xcord] == Paths.TqtEnd || Main.file_mappath[ycord+1][xcord] == Paths.TexPath )&& ycord+1 != prevycord){
				prevycord = ycord;
				prevxcord = -10;
				ydisp++;
				incy=true;
			}else if((Main.file_mappath[ycord-1][xcord] == Paths.TqtEnd ||Main.file_mappath[ycord-1][xcord] == Paths.TexPath) && ycord-1 != prevycord){
				if(Main.file_mappath[ycord-1][xcord] == Paths.TqtEnd){
					check2 = false;
				}
				prevycord = ycord;
				prevxcord = -10;
				ydisp--;
				decy=true;
			}
		}else{
			if((Main.file_mappath[ycord][xcord+1] == Paths.TqtEnd || Main.file_mappath[ycord][xcord+1] == Paths.TexPath )&& xcord+1 != prevxcord){
				prevxcord = xcord;
				prevycord = -10;
				xdisp++;
				incx=true;
			}else if((Main.file_mappath[ycord+1][xcord] == Paths.TqtEnd ||Main.file_mappath[ycord+1][xcord] == Paths.TexPath) && ycord+1 != prevycord){
				prevycord = ycord;
				prevxcord = -10;
				ydisp++;
				incy=true;
			}
		}
		}else if(Main.file_mappath[ycord][xcord] != Paths.TqtEnd){
			if(incx){
				xdisp++;
			}
			if(decx){
				xdisp--;
			}
			if(incy){
				ydisp++;
			}
			if(decy){
				ydisp--;
			}
		}
	}
}
