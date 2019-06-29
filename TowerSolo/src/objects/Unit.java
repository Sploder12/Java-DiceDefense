package objects;

public enum Unit {
LazerPointer(192,1,64,500,"Lazer Pointer"), LazerShotgun(112,1,128,500,"Lazer Shotgun"), LazerCannon(144,1,256,500,"Lazer Cannon");

private String dispname;
private int range;
private int damage;
private int initcost;
private int atkspeed;

public int getTowerAtkspeed(){
	return this.atkspeed;
}

public int getTowerDamage(){
	return this.damage;
}

public int getTowerCost(){
	return this.initcost;
}

public int getTowerRange(){
	return this.range;
}

public String getTowerName(){
	return this.dispname;
}
private Unit(int range,int damage,int atkspeed,int initcost,String dispname){
	this.atkspeed = atkspeed;
	this.initcost = initcost;
	this.damage = damage;
	this.dispname = dispname;
	this.range = range;
}
}
