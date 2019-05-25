package objects;

import com.sploder12.main.Main;
import com.sploder12.main.WaveManager;

public class Units implements Runnable{
	private Thread tower;
	public String name;
	private int xcord;
	private int ycord;
	private int range;
	public long dicerolled = 0;
	private int damage;
	private int atkspeed;
	public Units(int xcorde, int ycorde, Unit unit){
		name = unit.getTowerName();
		range = unit.getTowerRange();
		damage = unit.getTowerDamage();
		atkspeed = unit.getTowerAtkspeed();
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
		if(check){
			System.out.println(enemyFound());
			attack();
			
			try {
				check = false;
				Thread.sleep(-atkspeed+256);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void attack(){
		int curenemyhp = WaveManager.enemies[Main.currentwave][enemyfound].getEnemyHp();
		if(WaveManager.enemies[Main.currentwave][enemyfound].setEnemyHp(curenemyhp-damage)){
			check = false;
		}
	}
	
	private int enemyfound;
	private boolean enemyFound(){
		boolean foind = false;
		for(byte curenemy = 0; curenemy < WaveManager.enemies[Main.currentwave].length; curenemy++){
			if(WaveManager.enemies[Main.currentwave][curenemy] == null)continue;
			if(WaveManager.enemies[Main.currentwave][curenemy].getEnemyHp() <= 0) continue;
			int enemyx =WaveManager.enemies[Main.currentwave][curenemy].getEnemyXCord();
			int enemyy = WaveManager.enemies[Main.currentwave][curenemy].getEnemyYCord();
			System.out.println(Math.round(Math.sqrt(Math.pow(enemyx-xcord,2)+Math.pow(enemyy-ycord, 2))));
			if(Math.round(Math.sqrt(Math.pow(enemyx-xcord,2)+Math.pow(enemyy-ycord, 2))) <= range/2){
				foind = true;
				break;
			}
		}
		return foind;
	}
}
