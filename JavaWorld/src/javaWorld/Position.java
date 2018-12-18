package javaWorld;

public class Position {
private int pos_x;
private int pos_y;
Position(){};
Position(int x, int y){
	setPos_x(x);
	setPos_y(y);
}
public int getPos_x() {
	return pos_x;
}
public void setPos_x(int pos_x) {
	this.pos_x = pos_x;
}
public int getPos_y() {
	return pos_y;
}
public void setPos_y(int pos_y) {
	this.pos_y = pos_y;
}
public double distance(Position p){
	return Math.sqrt(Math.pow(p.getPos_x()-this.getPos_x(), 2)+Math.pow(p.getPos_y()-this.getPos_y(), 2));
}
public boolean equals(Position p){
	if(p.getPos_x()==this.getPos_x() && p.getPos_y()==this.getPos_y() ) return true;
	return false;
}
@Override
public String toString() {
	return "  (" + pos_x + ", " + pos_y + ")  ";
}

}
