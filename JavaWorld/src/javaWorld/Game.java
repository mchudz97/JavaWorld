package javaWorld;

public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Position p1= new Position(3,4);
		Position p2= new Position(5,4);
		if(p1.equals(p2))
		{
			System.out.println("tag");
		}
		Sheep szip= new Sheep(3, 5, 6);
		Sheep szipklon=szip.clone();
		System.out.println(szip+"\n"+szipklon);
		szip.setP(10, 12);
		System.out.println(szip+"\n"+szipklon + "\n RUCH \n");
		szip.move();
		szipklon.move();
		System.out.println(szip+"\n"+szipklon);
		
		
	}

}
