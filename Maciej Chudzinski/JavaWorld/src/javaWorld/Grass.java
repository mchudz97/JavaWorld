package javaWorld;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Grass extends Plant {
	
	Grass(){};

	Grass(int x, int y){
		this.p.setPos_x(x);
		this.p.setPos_y(y);
		this.setPower(1);
		this.setLiveLength(20);
		this.setPowerToReproduce(2);
	}
	
	
	public Grass clone() {
		Grass cloned = new Grass();
		cloned.setPower(this.getPower());
		cloned.setP(this.p.getPos_x(), this.p.getPos_y());
		return cloned;
	}

@Override
public void render(Graphics g){
	g.setColor(Color.GREEN);
	g.fillRect(this.p.getPos_x(), this.p.getPos_y(), World.pixelSize, World.pixelSize);
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
			orgs.add(new Grass(this.p.getPos_x()+randx, this.p.getPos_y()+randy));
			
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