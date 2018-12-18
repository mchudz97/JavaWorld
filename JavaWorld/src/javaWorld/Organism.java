package javaWorld;

public class Organism {
private int power;
protected Position p= new Position();
private char sign;
Organism(){};
Organism(int pow, char sign, int x, int y){
	setPower(pow);
	setSign(sign);
	p.setPos_x(x);
	p.setPos_y(y);
}
public int getPower() {
	return power;
}
public void setPower(int power) {
	this.power = power;
}
public Position getP() {
	return p;
}
public void setP(int x, int y) {
	this.p.setPos_x(x);
	this.p.setPos_y(y);
}
public char getSign() {
	return sign;
}
public void setSign(char sign) {
	this.sign = sign;
}
@Override
public String toString() {
	return  p.toString() ;
}

}
