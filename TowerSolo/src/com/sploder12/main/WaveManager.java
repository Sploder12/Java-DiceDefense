package com.sploder12.main;

import objects.Enemies;
import objects.Enemy;

public class WaveManager implements Runnable{
	public static boolean sending;
	private Thread waveman;
	int curenemysent = 0;
	public Enemy[][] enemies = new Enemy[100][Main.waves[Main.currentwave].length];
	public static boolean canreturn = false;
	
	public Enemy[] getEnemies(){
		return enemies[Main.currentwave];
	}
	
	public WaveManager(){
		try {
			for(byte x = 0; x < Main.waves[Main.currentwave].length;x++){
				if(Main.waves[Main.currentwave][x] != Enemies.t59){
					enemies[Main.currentwave][x] = new Enemy(Main.waves[Main.currentwave][x],Main.xstart,Main.ystart,Main.xend,Main.yend);
					canreturn = true;
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		waveman = new Thread(this);
		waveman.start();
	}
	

	@Override
	public synchronized void run() {
		
		while(Render.state == "GameTime"){
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while(sending){
				
				if(curenemysent < 99 && Main.waves[Main.currentwave][curenemysent] == Enemies.t59 ){
					curenemysent++;
				}else{
					try{
						if(enemies[Main.currentwave][curenemysent] != null){
							enemies[Main.currentwave][curenemysent].wakeup();
							System.out.println("To finally roam and invite");
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				
					if((curenemysent+1)%5 == 0 && curenemysent != 0 && curenemysent < 99){
						try {
							Thread.sleep(Main.waittime[Main.currentwave][(curenemysent+1)/5]);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						try {
							Thread.sleep(150);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					if(curenemysent < 99){
						curenemysent++;
					}
				}
			
			
			
			
				if(curenemysent >= 100){
					Main.currentwave++;
					sending = false;
				}
				
			}
			
		}
		try {
			waveman.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
