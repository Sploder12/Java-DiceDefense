package com.sploder12.main;

import objects.Enemy;

public class EnemyMovement implements Runnable{
	private Thread pretty;
	private boolean running = true;
	private Enemy enemy;
	private boolean xm=false,ym=false,nxm=false,nym=false;
	public EnemyMovement(Enemy enemie, boolean xmove, boolean ymove, boolean negxmove, boolean negymove){
		enemy = enemie;
		xm = xmove; //@TODO this is very broken
		ym = ymove;
		nxm = negxmove;
		nym = negymove;
		pretty = new Thread(this);
		pretty.start();
	}
	@Override
	public void run() {
		move();
	try {
		pretty.join();
	} catch (InterruptedException e) {
		e.printStackTrace();
		}
	}
	
	public void move(){
		while(running){
			if(xm){
				int loca = enemy.getEnemyXCord()+1;
				enemy.setEnemyXCord(loca);
			}else if(nxm){
				int loca =enemy.getEnemyXCord()-1;
				enemy.setEnemyXCord(loca);
			}else if(ym){
				int loca =enemy.getEnemyYCord()+1;
				System.out.println(loca);
				enemy.setEnemyYCord(loca);
			}else if(nym){
				int loca =enemy.getEnemyYCord()-1;
				enemy.setEnemyYCord(loca);
			}
			
			if((xm || nxm )&& enemy.getEnemyXCord()%32 == 0){
				running = false;
			}else if((ym || nym )&& enemy.getEnemyYCord()%32 == 0){
				running = false;
			}
			try {
				//Thread.sleep(enemy.getEnemySpeed()*32);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
