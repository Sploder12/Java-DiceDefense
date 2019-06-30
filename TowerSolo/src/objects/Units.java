package objects;

import com.sploder12.main.Main;
import com.sploder12.main.WaveManager;

public class Units{
	private Thread tower;
	public String name;
	private volatile int xcord;
	private volatile int ycord;
	private int range;
	public long dicerolled = 0;
	private int atkspeed;
	public volatile boolean attacking = false;
	public int lazrpowr =0;
	public Unit lazer;
	public volatile byte[] paths = {1,1,1,1}; 
	public volatile byte totalups = 0;
	private float cashmult = 1;
	public int blastr = 32;
	public boolean splashdmg = false;
	public volatile int mutlilazers = 1;
	private long timer;
	public boolean canbreakhard = false, canbreakglass = false;
	//public volatile Projectile[] projects = new Projectile[100];
	public volatile int[] enemysatking = new int[mutlilazers];
	public boolean running = false;
	//public int currentprojectile = 0;
	public Units(int xcorde, int ycorde, Unit unit){
		if(unit == Unit.LazerCannon) {
			splashdmg = true;
			canbreakhard = true;
		}
		if(unit == Unit.LazerShotgun) mutlilazers = 3;
		int[] copy = new int[mutlilazers];
		enemysatking = copy;
		lazer = unit;
		name = unit.getTowerName();
		range = unit.getTowerRange();
		atkspeed = unit.getTowerAtkspeed();
		lazrpowr = unit.getTowerDamage();
		xcord = xcorde;
		ycord = ycorde;
		timer = System.currentTimeMillis();
		running = true;
	}

	public int getUnitX(){
		return xcord;
	}
	public int getUnitY(){
		return ycord;
	}
	public int getUnitRange(){
		return range;
	}
	

	private boolean check;
	public void AI(){
		check = false;
		check = enemyFound();
		//System.out.println(check);
		if(check && System.currentTimeMillis() - timer > atkspeed){
			timer += atkspeed;
			attack();
			//System.out.println("");
			check = false;
		}else if(!check){
			attacking = false;
		}
		
	}
	
	private void attack(){
		for(byte atk = 0; atk < mutlilazers; atk++){
			WaveManager.enemies[Main.currentwave][enemysatking[atk]].setEnemyHp(lazrpowr);
			if(splashdmg){
				for(byte enemiez = 0; enemiez < WaveManager.enemies[Main.currentwave].length; enemiez++){
					if(WaveManager.enemies[Main.currentwave][enemiez] == null || !WaveManager.enemies[Main.currentwave][enemiez].visible) continue;
					int enemyx2 =WaveManager.enemies[Main.currentwave][enemiez].getEnemyXCord();
					int enemyy2 = WaveManager.enemies[Main.currentwave][enemiez].getEnemyYCord();
					int enemyx =WaveManager.enemies[Main.currentwave][enemysatking[atk]].getEnemyXCord();
					int enemyy = WaveManager.enemies[Main.currentwave][enemysatking[atk]].getEnemyYCord();
					if(Math.floor(Math.sqrt(Math.pow(enemyy2-enemyy, 2)+Math.pow(enemyx2-enemyx,2))) <= blastr){
						WaveManager.enemies[Main.currentwave][enemiez].setEnemyHp(lazrpowr);
					}
				}
			}
			//System.out.println((long) Math.ceil(Main.player[0].getcash() + (lazrpowr*cashmult)));
			Main.player[0].setcash((long) Math.ceil(Main.player[0].getcash() + (lazrpowr*cashmult)));	
		}
	}
	
	private boolean enemyFound(){
		boolean foind = false;
		
		byte mutlilazersfond = 0;
		for(byte curenemy = 0; curenemy < WaveManager.enemies[Main.currentwave].length; curenemy++){
			if(WaveManager.enemies[Main.currentwave][curenemy] == null || !WaveManager.enemies[Main.currentwave][curenemy].getVisible())continue;
			if(WaveManager.enemies[Main.currentwave][curenemy].getEnemyHp() <= 0) continue;
			if(WaveManager.enemies[Main.currentwave][curenemy].getcamo() || WaveManager.enemies[Main.currentwave][curenemy].gethard()) {
				if(canbreakhard != WaveManager.enemies[Main.currentwave][curenemy].gethard() || canbreakglass != WaveManager.enemies[Main.currentwave][curenemy].getcamo())continue;
			}
			int enemyx =WaveManager.enemies[Main.currentwave][curenemy].getEnemyXCord();
			int enemyy = WaveManager.enemies[Main.currentwave][curenemy].getEnemyYCord();
			//System.out.println(Math.round(Math.sqrt(Math.pow(enemyx-(xcord+16),2)+Math.pow(enemyy-(ycord+16), 2))));
			if(Math.round(Math.sqrt(Math.pow(enemyx-(xcord+16),2)+Math.pow(enemyy-(ycord+16), 2))) <= range/2){
				enemysatking[mutlilazersfond] = curenemy;
				//System.out.println(enemysatking[mutlilazersfond]);
				mutlilazersfond++;
				foind = true;
				if(mutlilazersfond == enemysatking.length){
					attacking = true;
					break;
				}
			}
			if(curenemy == WaveManager.enemies[Main.currentwave].length-1){
				attacking = true;
				break;
			}
		}
		return foind;
	}
	
	public void upgrades(int i){
		String unit = lazer.name(); 
		int lazerindx = Unit.valueOf(unit).ordinal();
		if(totalups < 8){
			switch(lazerindx){ //Unit index
			case 0:
				switch(i){ //Upgrade to get
				case 1:
					if(Main.player[0].getcash() >= 450) { //1-0-0-0pointer 'Alpha Pointer'
						Main.player[0].setcash(Main.player[0].getcash()-450);  
						lazrpowr *= 2;
						paths[0]++;
						totalups++;
					}
					break;
				case 2:
					if(Main.player[0].getcash() >= 1000){ //2-0-0-0pointer 'Beta Pointer'
						Main.player[0].setcash(Main.player[0].getcash()-1000);
						lazrpowr *= 2;
						totalups++;
						canbreakhard = true;
						paths[0]++;
					}
					break;
				case 3:
					if(Main.player[0].getcash() >= 2000){ //3-0-0-0pointer 'Gamma Pointer'
						Main.player[0].setcash(Main.player[0].getcash()-2000);
						lazrpowr *= 2;
						paths[0]++;
						totalups++;
					}
					break;
				case 4:
					if(Main.player[0].getcash() >= 5000){ //4-0-0-0pointer 'Fission Pointer'
						Main.player[0].setcash(Main.player[0].getcash()-5000);
						lazrpowr*=2;
						paths[0]++;
						totalups++;
					}
					break;
				case 5:
					if(Main.player[0].getcash() >= 450){ //0-1-0-0pointer 'High Frequency Lazer'
						Main.player[0].setcash(Main.player[0].getcash()-450);
						atkspeed /= 2;
						paths[1]++;
						totalups++;
					}
					break;
				case 6:
					if(Main.player[0].getcash() >= 900){ //0-2-0-0pointer 'Higher Frequency Lazer'
						Main.player[0].setcash(Main.player[0].getcash()-900);
						atkspeed /= 2;
						paths[1]++;
						totalups++;
					}
					break;
				case 7:
					if(Main.player[0].getcash() >= 1900){ //0-3-0-0pointer 'Highest Frequency Lazer'
						Main.player[0].setcash(Main.player[0].getcash()-1900);
						atkspeed /= 2;
						paths[1]++;
						totalups++;
					}
					break;
				case 8:
					if(Main.player[0].getcash() >= 4000){ //0-4-0-0pointer 'Highestest Frequency Lazer'
						Main.player[0].setcash(Main.player[0].getcash()-4000);
						atkspeed /= 2;
						paths[1]++;
						totalups++;
					}
					break;
				case 9:
					if(Main.player[0].getcash() >= 200){ //0-0-1-0pointer 'Long Range Pointer'
						Main.player[0].setcash(Main.player[0].getcash()-200);
						range *= 1.1;
						paths[2]++;
						totalups++;
					}
					break;
				case 10:
					if(Main.player[0].getcash() >= 400){ //0-0-2-0pointer 'Longest Range Pointer'
						Main.player[0].setcash(Main.player[0].getcash()-400);
						range *= 1.2;
						paths[2]++;
						totalups++;
					}
					break;
				case 11:
					if(Main.player[0].getcash() >= 400){ //0-0-3-0pointer 'Anti-reflectors'
						Main.player[0].setcash(Main.player[0].getcash()-400);
						canbreakglass = true;
						paths[2]++;
						totalups++;
					}
					break;
				case 12:
					if(Main.player[0].getcash() >= 800){ //0-0-4-0pointer 'MEGA POINTER'
						Main.player[0].setcash(Main.player[0].getcash()-800);
						range *= 1.5;
						paths[2]++;
						totalups++;
					}
					break;
				case 13:
					if(Main.player[0].getcash() >= 400){ //0-0-0-1pointer 'Money Pointer'
						Main.player[0].setcash(Main.player[0].getcash()-400);
						cashmult += 0.5f;
						paths[3]++;
						totalups++;
					}
					break;
				case 14:
					if(Main.player[0].getcash() >= 800){ //0-0-0-2pointer 'Silver Pointer'
						Main.player[0].setcash(Main.player[0].getcash()-800);
						cashmult += 1f;
						paths[3]++;
						totalups++;
					}
					break;
				case 15:
					if(Main.player[0].getcash() >= 1600){ //0-0-0-3pointer 'Gold Pointer'
						Main.player[0].setcash(Main.player[0].getcash()-1600);
						cashmult += 2f;
						paths[3]++;
						totalups++;
					}
					break;
				case 16:
					if(Main.player[0].getcash() >= 3200){ //0-0-0-4pointer 'Big Bucks'
						Main.player[0].setcash(Main.player[0].getcash()-3200);
						cashmult *= 10;
						paths[3]++;
						totalups++;
					}
					break;
				}
		
				break;
			case 1:
				switch(i){
				case 1:
				
					break;
				}
				break;
			
			}
		}
	}
}
