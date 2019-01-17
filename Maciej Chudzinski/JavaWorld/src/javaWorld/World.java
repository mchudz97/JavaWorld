package javaWorld;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.Random;

public class World {
static final int xRange = 820;
static final int yRange = xRange/12*9;
static final int pixelSize=10;
private int turn;
private Random ran= new Random();
ArrayList<Organism> organisms= new ArrayList<Organism>();


World(){
	this.turn=0;
	
};


public boolean positionOnBoard(Position p){
	return p.getPos_x()>=0 && p.getPos_y()>=0 &&
		p.getPos_x()<this.xRange && p.getPos_y()<this.yRange;
}
public Organism getOrganismFromPos(Position pos){
	for (Organism org:organisms){
		if (org.p.getPos_x()==pos.getPos_x() &&
			org.p.getPos_y()==pos.getPos_y()){
			return org;
		}
	}
	 return null;
}

public boolean addOrganism(Organism o){
	Position checkPos= o.getP();
	if(positionOnBoard(checkPos)){
		
		this.organisms.add(o);
	return true;
	}
	return false;
}

public void tick(){
	turn++;
	System.out.println("TURN: "+turn);
	for(int i=0;i<organisms.size();i++){
		//System.out.println(organisms.get(i));
		organisms.get(i).doSomething(organisms);
	}
	/*organisms.addAll(newOrganisms);
	lockedPositions.clear();
	for(int i=0;i<organisms.size();i++){
		lockedPositions.add(organisms.get(i).p);
	}*/
}
public void render(Graphics g){
	for(int i=0;i<organisms.size();i++){
		organisms.get(i).render(g);
	}
}
public boolean checkPosition(int x, int y){
	for(int i=0;i<organisms.size();i++){
	if(organisms.get(i).p.getPos_x()==x && organisms.get(i).p.getPos_y()==y)
		return false;
	}
	return true;
}
public void generateobjects(int a){
	
	
	for(int i=0; i<a;i++){
		int x=ran.nextInt(xRange/pixelSize)*pixelSize;
		int y=ran.nextInt(yRange/pixelSize)*pixelSize;
		while(checkPosition(x,y)==false){
			 x=ran.nextInt(xRange/pixelSize)*pixelSize;
			 y=ran.nextInt(yRange/pixelSize)*pixelSize;
		}
		int choice=ran.nextInt(82);
		if(choice<20) organisms.add(new Sheep(x, y));
		if(choice>=20 && choice<40) organisms.add(new Wolf(x, y));
		if(choice>=40 && choice<60) organisms.add(new Weed(x,y));
		if(choice>=60 && choice<80) organisms.add(new Grass(x, y));
		if(choice==61)	organisms.add(new TrophyHunter(x, y));
		
	}

}

}
