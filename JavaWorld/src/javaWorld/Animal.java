package javaWorld;

import java.util.ArrayList;

public abstract class Animal extends Organism{
	Animal(){};

	/*public bolean setTarget(ArrayList<Organism> positions){
		if(prey.getPower()>this.getPower())	return false;
		if(prey.p.getPos_x()>)
		if(prey == null){
			ArrayList<Organism> view= new ArrayList<Organism>();
			for(int i=0; i<positions.size();i++){
				if (positions.get(i).p.getPos_x()>this.p.getPos_x()+3*5)
			}
			
			
		}
	}*/
	public void upgradeStats(){
		
	}
	public abstract boolean interact(ArrayList<Organism> orgs);
	public abstract boolean reproduce(ArrayList<Organism> orgs);
	public boolean updateStats(ArrayList<Organism> arr){
		if(this.getLiveLength()<1) {
			arr.remove(this);
			return true;
		}
		else{
			this.setLiveLength(this.getLiveLength()-1);
			return false;
		}
	}
	
	public boolean move(ArrayList<Organism> org){
	ArrayList<Position> posInRange= new ArrayList<Position>();
	for(int i=0;i<org.size();i++){
		if(this.p.rangeChecking(org.get(i).p, 1)){
			posInRange.add(org.get(i).p); // tworzymy liste pozycji, ktore znajduja sie w zasiegu kroku obiektu
		}
	}
	if(posInRange.size()>=9)return true;
	while(true){
		int randx=	(r.nextInt(3)-1)*World.pixelSize;
		int randy=	(r.nextInt(3)-1)*World.pixelSize;
		if(randx==0 && randy==0) return true;
		boolean check=true;
		if(this.p.getPos_x()+randx<World.xRange && this.p.getPos_x()+randx>=0 &&this.p.getPos_y()+randy<World.yRange && 
				this.p.getPos_y()+randy>=0 ){
			
			for(int i=0;i<posInRange.size();i++){
				if(posInRange.get(i).getPos_x()== this.p.getPos_x()+randx && posInRange.get(i).getPos_y()== this.p.getPos_y()+randy)	check=false;
			}
			if(check==true){
				this.p.setPos_x(this.p.getPos_x()+randx);
				this.p.setPos_y(this.p.getPos_y()+randy);
				return true;
			}
		}
	}
	

}

	@Override
	public String toString() {
		return super.toString();
	}
}

/*public boolean move(ArrayList<Position> lockedPositions){ //lockedPositions
	ArrayList<Position> posInRange= new ArrayList<Position>();
	for(int i=0;i<lockedPositions.size();i++){
		if(lockedPositions.get(i).getPos_x()<=this.p.getPos_x()+1 && lockedPositions.get(i).getPos_x()>=this.p.getPos_x()-1
				&& lockedPositions.get(i).getPos_y()<=this.p.getPos_y()+1 && lockedPositions.get(i).getPos_y()>=this.p.getPos_y()-1	){
			posInRange.add(lockedPositions.get(i)); // tworzymy liste pozycji, ktore znajduja sie w zasiegu kroku obiektu
		}
	}
	
	posInRange.remove(this.p);
	for(int i=0;i<posInRange.size();i++){
		if(posInRange.get(i).getPos_x()==this.p.getPos_x() && posInRange.get(i).getPos_y()==this.p.getPos_y()){
			posInRange.remove(i);		//odejmujemy z listy pozycje naszego obiektu
			break;
		}
	}
		for(int i=0; i<posInRange.size(); i++){
			System.out.println(this.toString()+"posinrange" +posInRange.get(i));
		}
	 	if(this.p.equals(new Position(World.xRange, World.yRange))){ //sprawdzanie czy obiekt znajduje sie w prawym dolnym rogu
		 while(true){
			 int randx=r.nextInt(2)-1;			
			 int randy=r.nextInt(2)-1;
			 //zmiana pozycji dozwolona w rogu
			 System.out.println(randx+ '\t' + randy);
			 boolean check=true;
			 for(int i=0;i<posInRange.size();i++){
				 if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
					 check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
				 }	
			 }
			 if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) to zmienia pozycje
				 this.p.setPos_x(this.p.getPos_x()+randx);
				 this.p.setPos_y(this.p.getPos_y()+randy);
				 break;
			 }
		 }
		 return true;
	 }
	 if(this.p.equals(new Position(World.xRange, 0))){ // prawy gorny rog
		 while(true){
			 int randx=r.nextInt(2)-1;			
			 int randy=r.nextInt(2);			//zmiana pozycji dozwolona w rogu
			 boolean check=true;
			 for(int i=0;i<posInRange.size();i++){
				 if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
					 check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
				 }	
			 }
			 if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) lub ruchem bedzie bezruch  to zmienia pozycje
				 this.p.setPos_x(this.p.getPos_x()+randx);
				 this.p.setPos_y(this.p.getPos_y()+randy);
				 break;
			 }
		 }
		 return true;
	 }
	 if(this.p.equals(new Position(0, World.yRange))){ //lewy dolny rog
		 while(true){
			 int randx=r.nextInt(2);			
			 int randy=r.nextInt(2)-1;			//zmiana pozycji dozwolona w rogu
			 boolean check=true;
			 for(int i=0;i<posInRange.size();i++){
				 if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
					 check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
				 }	
			 }
			 if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) to zmienia pozycje
				 this.p.setPos_x(this.p.getPos_x()+randx);
				 this.p.setPos_y(this.p.getPos_y()+randy);
				 break;
			 }
		 }
		 return true;
	 }
	 if(this.p.equals(new Position(0, 0))){
		 while(true){
			 int randx=r.nextInt(2);			
			 int randy=r.nextInt(2);			//zmiana pozycji dozwolona w rogu
			 boolean check=true;
			 for(int i=0;i<posInRange.size();i++){
				 if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
					 check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
				 }	
			 }
			 if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) to zmienia pozycje
				 this.p.setPos_x(this.p.getPos_x()+randx);
				 this.p.setPos_y(this.p.getPos_y()+randy);
				 break;
			 }
		 }
		 return true;
	 }
	 if(this.p.getPos_x()==0){
		 while(true){
			 int randx=r.nextInt(2);			
			 int randy=r.nextInt(3)-1;			//zmiana pozycji dozwolona w rogu
			 boolean check=true;
			 for(int i=0;i<posInRange.size();i++){
				 if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
					 check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
				 }	
			 }
			 if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) to zmienia pozycje
				 this.p.setPos_x(this.p.getPos_x()+randx);
				 this.p.setPos_y(this.p.getPos_y()+randy);
				 break;
			 }
		 }
		 return true;
	 }
	 if(this.p.getPos_x()==World.xRange){
		 while(true){
			 int randx=r.nextInt(2)-1;			
			 int randy=r.nextInt(3)-1;			//zmiana pozycji dozwolona w rogu
			 boolean check=true;
			 for(int i=0;i<posInRange.size();i++){
				 if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
					 check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
				 }	
			 }
			 if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) to zmienia pozycje
				 this.p.setPos_x(this.p.getPos_x()+randx);
				 this.p.setPos_y(this.p.getPos_y()+randy);
				 break;
			 }
		 }
		 return true;
	 }
	 if(this.p.getPos_y()==0){
		 while(true){
			 int randx=r.nextInt(3)-1;			
			 int randy=r.nextInt(2);			//zmiana pozycji dozwolona w rogu
			 boolean check=true;
			 for(int i=0;i<posInRange.size();i++){
				 if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
					 check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
				 }	
			 }
			 if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) to zmienia pozycje
				 this.p.setPos_x(this.p.getPos_x()+randx);
				 this.p.setPos_y(this.p.getPos_y()+randy);
				 break;
			 }
		 }
		 return true;
	 }
	 if(this.p.getPos_y()==World.yRange){
		 while(true){
			 int randx=r.nextInt(3)-1;			
			 int randy=r.nextInt(2)-1;			//zmiana pozycji dozwolona w rogu
			 boolean check=true;
			 for(int i=0;i<posInRange.size();i++){
				 if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
					 check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
				 }	
			 }
			 if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) to zmienia pozycje
				 this.p.setPos_x(this.p.getPos_x()+randx);
				 this.p.setPos_y(this.p.getPos_y()+randy);
				 break;
			 }
		 }
		 return true;
	 }
	 else{
		 
		 int randx=r.nextInt(3)-1;			
		 int randy=r.nextInt(3)-1;			//zmiana pozycji dozwolona w rogu
		 boolean check=true;
		 for(int i=0;i<posInRange.size();i++){
			 if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
				 check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
			 }	
		 }
		 if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) to zmienia pozycje
			 this.p.setPos_x(this.p.getPos_x()+randx);
			 this.p.setPos_y(this.p.getPos_y()+randy);
			 
		 }
		 return true;
	 }
}*/