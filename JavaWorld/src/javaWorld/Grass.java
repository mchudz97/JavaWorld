package javaWorld;

public class Grass extends Plant {
	
	Grass(){};
	Grass(int pow, char sign, int x, int y){
		super(pow, sign, x, y);
	}

	
	
	public Grass clone() {
		Grass cloned = new Grass();
		cloned.setPower(this.getPower());
		cloned.setSign(this.getSign());
		cloned.setP(this.p.getPos_x(), this.p.getPos_y());
		return cloned;
	}
	public void initParameters(){
		this.setPower(0);
		this.setSign('G');
	}
}
