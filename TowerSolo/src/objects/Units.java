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

	@Override
	public void run() {
		while(true){
			AI();
		}
	}
	private boolean check;
	private void AI(){
		
		if(check){
			attack();
			
			try {
				Thread.sleep(-atkspeed+256);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		check = enemyFound();
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
			if(WaveManager.enemies[Main.currentwave][curenemy].getEnemyHp() <= 0) continue;
			int curenemyx = WaveManager.enemies[Main.currentwave][curenemy].getEnemyXCord();
			boolean xcheck =(xcord+(range*16) > curenemyx && xcord-(range*16) < curenemyx);
			if(!xcheck) continue;
			int curenemyy = WaveManager.enemies[Main.currentwave][curenemy].getEnemyYCord();
			boolean ycheck =(ycord+(range*16) > curenemyy && ycord-(range*16) < curenemyy);
			if(!ycheck)continue;
			enemyfound = curenemy;
			foind = true;
			break;
		}
		return foind;
	}
}
