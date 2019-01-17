package javaWorld;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Weed extends Plant{
	
public Weed(int x, int y){
	this.p.setPos_x(x);
	this.p.setPos_y(y);
	this.setPower(2.5);
	this.setPowerToReproduce(3);
	this.setLiveLength(18);
	
}






@Override
public void render(Graphics g){
	g.setColor(new Color(128, 128, 0));
	g.fillRect(this.p.getPos_x(), this.p.getPos_y(), World.pixelSize, World.pixelSize);
}
public boolean interact(ArrayList<Organism> orgs){
	ArrayList<Organism> orgsInRange= new ArrayList<Organism>();
	for(int i=0;i<orgs.size();i++){
		if(this.p.rangeChecking(orgs.get(i).p, 1)){
			orgsInRange.add(orgs.get(i)); // tworzymy liste obiektow, ktore znajduja sie w zasiegu obiektu
		}
	}
	for(int i=0;i<orgsInRange.size();i++){
			if(orgsInRange.get(i).getP().equals(this.p)){
				orgsInRange.remove(i);		//odejmujemy z listy pozycje naszego obiektu
				break;
			}
		
	}
	for(int i=0;i<orgsInRange.size();i++){		//jezeli sila wilka jest wieksza to zjada owce
		if(orgsInRange.get(i) instanceof Grass){
			if(this.getPower()>orgsInRange.get(i).getPower()) {
				this.setPower(this.getPower()+orgsInRange.get(i).getPower());
				orgsInRange.get(i).setPower(0);	//sila owcy zostaje wyzerowana
				orgs.remove(orgsInRange.get(i));	// owca zostaje usnieta z listy organizmow
				return true;
			}
			
		}
	}
	return false;
}
public boolean reproduce(ArrayList<Organism> orgs){
	int stuck=0;
	if(this.getPower()>=this.getPowerToReproduce()){
		ArrayList<Position> posInRange= new ArrayList<Position>();
		for(int i=0;i<orgs.size();i++){
			if(this.p.rangeChecking(orgs.get(i).p, 1)){
				posInRange.add(orgs.get(i).p); // tworzymy liste pozycji, ktore znajduja sie w zasiegu kroku obiektu
			}
		}
		if(posInRange.size()>=9){
			return false;
		}
		
while(stuck<90){
	int randx=(r.nextInt(3)-1)*World.pixelSize;
	int randy=(r.nextInt(3)-1)*World.pixelSize;
	boolean check=true;
	if(this.p.getPos_x()+randx<World.xRange && this.p.getPos_x()+randx>=0 &&this.p.getPos_y()+randy<World.yRange && 
			this.p.getPos_y()+randy>=0 ){
		for(int i=0;i<posInRange.size();i++){
			if(posInRange.get(i).getPos_x()== this.p.getPos_x()+randx && posInRange.get(i).getPos_y()== this.p.getPos_y()+randy)	check=false;
		}
		if(check==true){
			orgs.add(new Weed(this.p.getPos_x()+randx, this.p.getPos_y()+randy));
			this.setPowerToReproduce(this.getPowerToReproduce()+1);
			this.setPower(this.getPower()/2);
			return true;
		}
		stuck++;
	}
}
}
	return false;
}

}
