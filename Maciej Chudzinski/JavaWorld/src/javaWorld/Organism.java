package javaWorld;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public abstract class Organism {
private double power;
protected Position p= new Position();
private int liveLength;
private int powerToReproduce;

protected Random r= new Random();
Organism(){};

public double getPower() {
	return power;
}
public void setPower(double power) {
	this.power = power;
}
public Position getP() {
	return p;
}
public void setP(int x, int y) {
	this.p.setPos_x(x);
	this.p.setPos_y(y);
}
public void render(Graphics g){
	g.setColor(Color.CYAN);
	g.fillRect(this.p.getPos_x(), this.p.getPos_y(), 5, 5);
}
public abstract boolean interact(ArrayList<Organism> orgs);
public abstract boolean reproduce(ArrayList<Organism> orgs);
public abstract boolean move(ArrayList<Organism> orgs);
public abstract boolean updateStats(ArrayList<Organism> arr);
public boolean doSomething(ArrayList<Organism> orgs){

	boolean done=false;
	done=updateStats(orgs);
	if(done==true) return true;
	done=reproduce(orgs);
	if(done==true) return true;
	done=interact(orgs);
	if(done==true) return true;
	done=move(orgs);
	if(done==true) return true;
	return false;
}

@Override
public String toString() {
	return  p.toString() ;
}
public int getLiveLength() {
	return liveLength;
}
public void setLiveLength(int liveLength) {
	this.liveLength = liveLength;
}
public int getPowerToReproduce() {
	return powerToReproduce;
}
public void setPowerToReproduce(int powerToReproduce) {
	this.powerToReproduce = powerToReproduce;
}


}
