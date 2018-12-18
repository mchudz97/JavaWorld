package javaWorld;
import java.util.Random;

public class Animal extends Organism{
	private Random r= new Random();
	Animal(){};
	Animal(int pow, char sign, int x, int y){
		super(pow, sign, x, y);
	}
	public void move(){
		this.p.setPos_x(this.p.getPos_x()+r.nextInt(3)-1);
		this.p.setPos_y(this.p.getPos_y()+r.nextInt(3)-1);
	}
	@Override
	public String toString() {
		return super.toString();
	}
/*	public Animal clone(){
		Animal cloned= new Animal(this.getPower(), this.getSign(), this.p.getPos_x(), this.p.getPos_y() );
		return cloned;
	}*/
}
