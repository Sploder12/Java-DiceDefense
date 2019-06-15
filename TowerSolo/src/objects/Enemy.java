package objects;

import com.sploder12.main.Main;
import com.sploder12.main.WaveManager;

import sploder12.json.JSON;
import objects.Enemies;
public class Enemy {
	private final int[] ENEMYINDEX= {10,75,139,204};
	private final String[] ENEMYNAME = {"D6","D4","D8","D10"};
	private JSON json;
	private String name = "D6";
	private int hp = 0,speed = 0,Baddie = 0;
	private String special = "none";
	private byte xcord = -10, ycord = -10, prevxcord=0, prevycord=0;
	private volatile int xdisp = 0, ydisp = 0;
	public volatile boolean visible = false;
	public Enemies enalme;
	private long timer = System.currentTimeMillis();
	private boolean check2 = true;
	private boolean check = false;
	
	public void setCheck(boolean set){
		timer = System.currentTimeMillis();
		check = set;
	}
	
	public boolean getCheck(){
		return check;
	}
	
	public boolean getVisible(){
		return this.visible;
	}
	
	public int getEnemyXCord(){
		return this.xdisp;
	}
	
	public void setEnemyXCord(int xcorde){
		this.xdisp = xcorde;
	}
	public int getEnemyYCord(){
		return this.ydisp;
	}
	public void setEnemyYCord(int ycorde){
		this.ydisp = ycorde;
	}
	
	public int getEnemyHp(){
		return this.hp;
	}
	
	public Enemies getEnemy(){
		return this.enalme;
	}
	
	public boolean setEnemyHp(int damage){
		if(this.hp > 0){
		this.hp = hp - damage;
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
		this.enalme = enemy;
		this.xdisp = xstart*16;
		this.ydisp = ystart*16;
		this.xcord = xstart;
		this.ycord = ystart;
		this.Baddie = ENEMYINDEX[enemy.ordinal()];
		this.name = ENEMYNAME[enemy.ordinal()];
		if(enemy.ordinal() == 0){
			name = "D6";
		}
		this.hp = json.getIntValueOfDict(Main.Enemiefile, json.locateStringEnd(Main.Enemiefile,"hp",Baddie));
		this.speed =json.getIntValueOfDict(Main.Enemiefile, json.locateStringEnd(Main.Enemiefile,"speed",Baddie));
		this.special = json.getValueOfDict(Main.Enemiefile, json.locateStringEnd(Main.Enemiefile,"special",Baddie));
		
	}
	public boolean alive = true;
	
	public void go2(){
		visible = true;
		if(System.currentTimeMillis() - timer > (-speed+128)/4){
			timer+= (-speed+128)/4;
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
		
	    	//Thread.sleep((-speed+128)/5);
			
		}
	}
	public void go() {
		/*try {
		*	synchronized(Enemy.this){
		*		this.wait();
		*	}
		*} catch (InterruptedException e1) {
		*	e1.printStackTrace();
		}*/
		
		if(hp > 0 && check2 && check){
			if(xcord < 24 && ycord < 24){
				if(Main.file_mappath[ycord+1][xcord] != Paths.TqtEnd  && Main.file_mappath[ycord][xcord+1] != Paths.TqtEnd){
			
					go2();
			
			
				}else if(Main.file_mappath[ycord+1][xcord] == Paths.TqtEnd || Main.file_mappath[ycord][xcord+1] == Paths.TqtEnd || !check2){
					if(alive){
						Main.player[0].health -= hp;
						hp = -260;
						alive = false;
					}
					visible = false;
				}else{
					visible = false;
				}
			}else{
				go2();
			}
		}else{
			visible = false;
		}
		
			//visible = false;
	}
	private boolean incx=false,incy=false,decx=false,decy = false;
	public void AI(){
		
		if(xdisp%16 == 0 && ydisp%16 == 0){
			incx=false;
			incy=false;
			decx=false;
			decy=false;
			
			
		if(ycord != 0 && xcord != 0 && xcord != 24  && ycord != 24){
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
		}else if((ycord == 0 || xcord == 0) && (xcord < 23 && ycord < 23)){
			
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
		}else{
			if(ycord == 0){
				prevycord = ycord;
				prevxcord = -10;
				ydisp++;
				incy=true;
			}else if(xcord == 0){
				prevxcord = xcord;
				prevycord = -10;
				xdisp++;
				incx=true;
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
