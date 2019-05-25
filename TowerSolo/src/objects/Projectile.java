package objects;

public class Projectile {
	private float xvel = 0;
	private float yvel = 0;
	private int xcord = 0;
	private int ycord = 0;
	private Projectiles projectil;
	
	public Projectile(float xvel, float yvel, int xcord, int ycord, Projectiles projectil){
		this.projectil = projectil;
		this.xvel = xvel;
		this.yvel = yvel;
		this.xcord = xcord;
		this.ycord = ycord;
	}
}
