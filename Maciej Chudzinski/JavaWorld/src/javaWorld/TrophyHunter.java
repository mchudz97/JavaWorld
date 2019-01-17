package javaWorld;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class TrophyHunter extends Organism{
private int reload=0;
public TrophyHunter(int x, int y){
	this.p.setPos_x(x);
	this.p.setPos_y(y);
	this.setPower(20000);
	this.setLiveLength(100000);
	
}

public boolean reproduce(ArrayList<Organism> orgs){
	return false;
}
public boolean move(ArrayList<Organism> orgs){
	return false;
}
public boolean updateStats(ArrayList<Organism> arr){
	return false;
}
public boolean interact(ArrayList<Organism> orgs){
	if(reload==0){
	ArrayList<Organism> orgsInRange= new ArrayList<Organism>();
	for(int i=0;i<orgs.size();i++){
		if(this.p.rangeChecking(orgs.get(i).p, 2)){
			orgsInRange.add(orgs.get(i)); // tworzymy liste obiektow, ktore znajduja sie w zasiegu obiektu
		}
	}
	for(int i=0;i<orgsInRange.size();i++){
			if(orgsInRange.get(i).getP().equals(this.p)){
				orgsInRange.remove(i);		//odejmujemy z listy pozycje naszego obiektu
				break;
			}
		
	}
	for(int i=0;i<orgsInRange.size();i++){
		if(orgsInRange.get(i) instanceof Wolf){
			if(this.getPower()>orgsInRange.get(i).getPower()) {
				orgsInRange.get(i).setPower(0);
				orgs.remove(orgsInRange.get(i));
				reload=5;
				return true;
			}
			
		}
	}
	return false;
}
	reload--;
	return false;
}
@Override
public void render(Graphics g){
	g.setColor(Color.yellow);
	g.fillRect(this.p.getPos_x(), this.p.getPos_y(), World.pixelSize, World.pixelSize);
}
}
