package javaWorld;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Sheep extends Animal {
	//private Random r= new Random();
static int number=0;
int numb;
	Sheep(int x, int y){
		this.p.setPos_x(x);
		this.p.setPos_y(y);
		this.setPower(3);
		this.setPowerToReproduce(4);
		this.setLiveLength(50);
		number++;
		this.numb=number;
		
	};

public Sheep clone(int x, int y) {
	Sheep cloned = new Sheep(x, y);
	//cloned.Animal a =super.clone();
	/*cloned.setPower(3));
	cloned.setSign(this.getSign());
	cloned.setP(this.p.getPos_x(), this.p.getPos_y());*/
	return cloned;
}
@Override
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
	for(int i=0;i<orgsInRange.size();i++){
		if(orgsInRange.get(i) instanceof Grass){
			if(this.getPower()>orgsInRange.get(i).getPower()) {
				this.setPower(this.getPower()+orgsInRange.get(i).getPower());
				orgsInRange.get(i).setPower(0);
				orgs.remove(orgsInRange.get(i));
				return true;
			}
			
		}
	}
	return false;
}
public boolean reproduce(ArrayList<Organism> org){
	int stuck=0;
	if(this.getPower()>=this.getPowerToReproduce()){
		ArrayList<Position> posInRange= new ArrayList<Position>();
		for(int i=0;i<org.size();i++){
			if(this.p.rangeChecking(org.get(i).p, 1)){
				posInRange.add(org.get(i).p); // tworzymy liste pozycji, ktore znajduja sie w zasiegu kroku obiektu
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
			org.add(new Sheep(this.p.getPos_x()+randx, this.p.getPos_y()+randy));
			this.setPower(this.getPower()/2);
			return true;
		}
		stuck++;
	}
}
}
	return false;
}

public void render(Graphics g){
	g.setColor(Color.white);
	g.fillRect(this.p.getPos_x(), this.p.getPos_y(), World.pixelSize, World.pixelSize);
}


}
