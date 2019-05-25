package com.sploder12.main;

import objects.Enemies;
import objects.Enemy;


public class WaveManager implements Runnable{
	public static boolean sending;
	private Thread waveman;
	public static int curenemysent = 0;
	public static Enemy[][] enemies = new Enemy[100][Main.waves[Main.currentwave].length];
	public static boolean canreturn = false;
	public static boolean wavefin = true;
	
	public Enemy[] getEnemies(){
		return enemies[Main.currentwave];
	}
	
	public WaveManager(){
		
		try {
			for(byte x = 0; x < Main.waves[Main.currentwave].length;x++){
				if(Main.waves[Main.currentwave][x] != Enemies.t59){
					enemies[Main.currentwave][x] = new Enemy(Main.waves[Main.currentwave][x],Main.xstart,Main.ystart);
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
		
		long sendtimer = System.currentTimeMillis();
		
		long waittime = 0;
		boolean waitor = false;
		long sendothertimer = System.currentTimeMillis();
		while(Render.state == "GameTime"){
			if(waitor){
				
				
				if(System.currentTimeMillis()-sendothertimer > waittime){
					
					if(enemies[Main.currentwave][curenemysent] != null){
						enemies[Main.currentwave][curenemysent].setCheck(true);
					}
					sendothertimer += waittime;
					waitor = false;
					sendtimer = System.currentTimeMillis();
					if(curenemysent < 99){
						curenemysent++;
					}
				}
			}else if(sending && System.currentTimeMillis()- sendtimer > 25){
				
				sendtimer+=25;
				if(curenemysent < 99 && Main.waves[Main.currentwave][curenemysent] == Enemies.t59 ){
					curenemysent++;
				}else{
					waittime =((curenemysent+1)%5 == 0 && curenemysent != 0 && curenemysent < 99)?Main.waittime[Main.currentwave][(curenemysent+1)/5]:150;
					sendothertimer = System.currentTimeMillis();
					waitor = true;
				}
				if(curenemysent >= 100){
					Main.currentwave++;
					sending = false;
				}
				
			}
			
			for(short x = 0; x < enemies[Main.currentwave].length; x++){
				if(enemies[Main.currentwave][x] == null || !enemies[Main.currentwave][x].getCheck())continue;
				enemies[Main.currentwave][x].go();
			}
				
			
			
		}
		try {
			waveman.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
