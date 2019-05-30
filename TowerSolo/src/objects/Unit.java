package objects;

public enum Unit {
LazerPointer(128,2,64,500,"Lazer Pointer"), LazerRing(256,4,256,500,"LFG");

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
