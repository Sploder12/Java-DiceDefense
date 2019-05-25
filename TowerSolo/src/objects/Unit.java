package objects;

public enum Unit {
DiceRoller(128,2,64,500,"Die Roller"), DiceDicer(64,1,64,500,"Dice Dicer");

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
