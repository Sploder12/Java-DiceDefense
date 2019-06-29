package objects;


import com.sploder12.main.Main;
import com.sploder12.main.Mouse;
import com.sploder12.main.WaveManager;
//TODO This Class Has Been Taken Out Of Working Code Due To Difficulty To Properly Implement.
public class Projectile {
	private int xvel = 0;
	private int yvel = 0;
	public volatile int xcord = 0;
	public volatile int ycord = 0;
	public double rotation = 0; //in degrees
	public Projectiles projectil;
	public volatile boolean isalive = true;
	
	public Projectile(double rotation, int xcord, int ycord, Projectiles projectil){
		this.projectil = projectil;
		this.rotation = rotation;
		this.xcord = xcord;
		this.ycord = ycord;
		getVel(Math.sin(rotation));
		
		do{
			fly();
		
		}while(isalive);
		
	}
	
	/*Was In Units
	private void attack(){
		int enemyx =WaveManager.enemies[Main.currentwave][enemyatking].getEnemyXCord();
		int enemyy = WaveManager.enemies[Main.currentwave][enemyatking].getEnemyYCord();
		double radians = Math.asin(Math.sqrt(Math.pow(enemyy-ycord, 2))/(Math.sqrt(Math.pow(enemyx-xcord,2)+Math.pow(enemyy-ycord, 2))));
		//double angle = radians * (180/Math.PI);
		//System.out.println(angle);
		if(projects[currentprojectile] == null || !projects[currentprojectile].isalive){
			projects[currentprojectile] = new Projectile(radians,xcord ,ycord, projecti);
		}else{
			for(byte x = 0; x < projects.length; x++){
				if(projects[x] == null)continue;
				currentprojectile = (projects[x].isalive)?x+1:x;
			}
		}
	}*/
	
	public int getxcord(){
		return this.xcord;
	}
	
	public int getycord(){
		return this.ycord;
	}
	
    public void getVel(double d) {
    	
    	d = Math.tan(d*(180/Math.PI));
    	d = (d >= 0)?(Math.ceil(d*10)/10):-(Math.ceil(d*10)/10);
    	int num = (int) Math.round(d*10);
    	int den = 10;
        int g = gcd(num, den);
        
        this.yvel = (num/g);
        this.xvel = (den/g);
        

    }
    
    static int gcd(int num, int den) 
    { 
       int x = 10;
       if(num == den) return 10;
       for(int ck = 2; ck <= 10; ck++){
    	   if(num % ck == 0 && den % ck == 0)x = ck;
    	   if(num / ck < 1 || den / ck < 1) break;
       }
       return x;
    } 
	
	
	public void fly(){
		//System.out.println(xcord+"+"+ycord);	
		xcord += xvel;
		ycord += yvel;	
		//System.out.println(xcord+" "+ycord);
		if(xcord > 768 || ycord > 768 || xcord < 0 || ycord < 0)isalive = false;
		for(byte x =0; x < WaveManager.enemies[Main.currentwave].length; x++){
			
			if(WaveManager.enemies[Main.currentwave][x] == null)continue;
			if(Mouse.moveOver(xcord,ycord,WaveManager.enemies[Main.currentwave][x].getEnemyXCord()-4,WaveManager.enemies[Main.currentwave][x].getEnemyYCord()-4,16,16)){
				WaveManager.enemies[Main.currentwave][x].setEnemyHp(0);
				isalive = false;
				System.out.println("oh get em");
			}
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
