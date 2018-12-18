package javaWorld;

public class Sheep extends Animal {
Sheep(){};
Sheep(int pow, char sign, int x, int y){
	super(pow, sign, x, y);
}
	Sheep(int pow, int x, int y){
	this.setPower(pow);
	this.p.setPos_x(x);
	this.p.setPos_y(y);
	this.setSign('S');
	
}
public Sheep clone() {
	Sheep cloned = new Sheep();
	//cloned.Animal a =super.clone();
	cloned.setPower(this.getPower());
	cloned.setSign(this.getSign());
	cloned.setP(this.p.getPos_x(), this.p.getPos_y());
	return cloned;
}
public void initParameters(){
	this.setPower(3);
	this.setSign('S');
}
@Override
public String toString() {
	return "Sheep [getPower()=" + getPower() + ", getSign()=" + getSign() +  "\n"+super.toString() ;
}

}
