package objects;

import com.sploder12.main.Main;
import com.sploder12.main.Player;
import com.sploder12.main.WaveManager;

public class Units implements Runnable{
	private Thread tower;
	public String name;
	private volatile int xcord;
	private volatile int ycord;
	private int range;
	public long dicerolled = 0;
	private int atkspeed;
	public boolean attacking = false;
	public int lazrpowr =0;
	public Unit lazer;
	public byte path1 = 1,path2 = 1,path3 = 1, path4 = 1, totalups = 0;
	private float cashmult = 1;
	public boolean canbreakhard = false, canbreakglass = false;
	//public volatile Projectile[] projects = new Projectile[100];
	public volatile int enemyatking;
	//public int currentprojectile = 0;
	public Units(int xcorde, int ycorde, Unit unit){
		lazer = unit;
		name = unit.getTowerName();
		range = unit.getTowerRange();
		atkspeed = unit.getTowerAtkspeed();
		lazrpowr = unit.getTowerDamage();
		xcord = xcorde;
		ycord = ycorde;
		tower = new Thread(this);
		tower.start();
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
	
	
	@Override
	public void run() {
		while(true){
			AI();
		}
	}
	private boolean check;
	private void AI(){
		check = false;
		check = enemyFound();
		//System.out.println(check);
		if(check){
			attack();
			System.out.println("attacked");
			try {
				check = false;
				
				Thread.sleep(-atkspeed+256);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else{
			attacking = false;
		}
		
	}
	
	private void attack(){
		WaveManager.enemies[Main.currentwave][enemyatking].setEnemyHp(lazrpowr);
		Player.cash = (long) Math.ceil((Player.cash + lazrpowr)*cashmult);
	}
	
	private boolean enemyFound(){
		boolean foind = false;
		for(byte curenemy = 0; curenemy < WaveManager.enemies[Main.currentwave].length; curenemy++){
			if(WaveManager.enemies[Main.currentwave][curenemy] == null)continue;
			if(WaveManager.enemies[Main.currentwave][curenemy].getEnemyHp() <= 0) continue;
			int enemyx =WaveManager.enemies[Main.currentwave][curenemy].getEnemyXCord();
			int enemyy = WaveManager.enemies[Main.currentwave][curenemy].getEnemyYCord();
			//System.out.println(Math.round(Math.sqrt(Math.pow(enemyx-(xcord+16),2)+Math.pow(enemyy-(ycord+16), 2))));
			if(Math.round(Math.sqrt(Math.pow(enemyx-(xcord+16),2)+Math.pow(enemyy-(ycord+16), 2))) <= range/2){
				enemyatking = curenemy;
				attacking = true;
				foind = true;
				break;
			}
		}
		return foind;
	}
	
	public void upgrades(byte upgradenum){
		String unit = lazer.name(); 
		int lazerindx = Unit.valueOf(unit).ordinal();
		if(totalups < 8){
			switch(lazerindx){ //Unit index
			case 1:
				switch(upgradenum){ //Upgrade to get
				case 1:
					if(Player.cash >= 450) { //1-0-0-0pointer 'Alpha Pointer'
						Player.cash -= 450;  
						lazrpowr *= 2;
						path1++;
						totalups++;
						System.out.println("bhouagt");
					}else{
						System.out.println("Lol ur poor");
					}
					break;
				case 2:
					if(Player.cash >= 1000){ //2-0-0-0pointer 'Beta Pointer'
						Player.cash -= 1000;
						lazrpowr *= 2;
						totalups++;
						canbreakhard = true;
						path1++;
					}
					break;
				case 3:
					if(Player.cash >= 2000){ //3-0-0-0pointer 'Gamma Pointer'
						Player.cash -= 2000;
						lazrpowr *= 2;
						path1++;
						totalups++;
					}
					break;
				case 4:
					if(Player.cash >= 5000){ //4-0-0-0pointer 'Fission Pointer'
						Player.cash -= 5000;
						lazrpowr*=2;
						path1++;
						totalups++;
					}
					break;
				case 5:
					if(Player.cash >= 450){ //0-1-0-0pointer 'High Frequency Lazer'
						Player.cash -= 450;
						atkspeed /= 2;
						path2++;
						totalups++;
					}
					break;
				case 6:
					if(Player.cash >= 900){ //0-2-0-0pointer 'Higher Frequency Lazer'
						Player.cash -= 900;
						atkspeed /= 2;
						path2++;
						totalups++;
					}
					break;
				case 7:
					if(Player.cash >= 1900){ //0-3-0-0pointer 'Highest Frequency Lazer'
						Player.cash -= 1900;
						atkspeed /= 2;
						path2++;
						totalups++;
					}
					break;
				case 8:
					if(Player.cash >= 4000){ //0-4-0-0pointer 'Highestest Frequency Lazer'
						Player.cash -= 4000;
						atkspeed /= 2;
						path2++;
						totalups++;
					}
					break;
				case 9:
					if(Player.cash >= 200){ //0-0-1-0pointer 'Long Range Pointer'
						Player.cash -= 200;
						range *= 1.1;
						path3++;
						totalups++;
					}
					break;
				case 10:
					if(Player.cash >= 400){ //0-0-2-0pointer 'Longest Range Pointer'
						Player.cash -= 400;
						range *= 1.2;
						path3++;
						totalups++;
					}
					break;
				case 11:
					if(Player.cash >= 400){ //0-0-3-0pointer 'Anti-reflectors'
						Player.cash -= 400;
						canbreakglass = true;
						path3++;
						totalups++;
					}
					break;
				case 12:
					if(Player.cash >= 800){ //0-0-4-0pointer 'MEGA POINTER'
						Player.cash -= 800;
						range *= 1.5;
						path3++;
						totalups++;
					}
					break;
				case 13:
					if(Player.cash >= 400){ //0-0-0-1pointer 'Money Pointer'
						Player.cash -= 400;
						cashmult += 0.5f;
						path4++;
						totalups++;
					}
					break;
				case 14:
					if(Player.cash >= 800){ //0-0-0-2pointer 'Silver Pointer'
						Player.cash -= 800;
						cashmult += 0.5f;
						path4++;
						totalups++;
					}
					break;
				case 15:
					if(Player.cash >= 1600){ //0-0-0-3pointer 'Gold Pointer'
						Player.cash -= 1600;
						cashmult += 4f;
						path4++;
						totalups++;
					}
					break;
				case 16:
					if(Player.cash >= 3200){ //0-0-0-4pointer 'Big Bucks'
						Player.cash -= 3200;
						cashmult *= 10;
						path4++;
						totalups++;
					}
					break;
				}
		
				break;
			case 2:
				switch(upgradenum){
				case 1:
				
					break;
				}
				break;
			
			}
		}else{
			System.out.println("Too Many Upgrades RIP");
		}
	}
}
