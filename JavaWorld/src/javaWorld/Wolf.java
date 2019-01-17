package javaWorld;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Wolf extends Animal{
	Wolf(int x, int y){
		this.p.setPos_x(x);
		this.p.setPos_y(y);
		this.setPower(5);
		this.setPowerToReproduce(7);
		this.setLiveLength(30);
	}

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
			for(int i=0;i<orgsInRange.size();i++){		//jezeli sila wilka jest wieksza to zjada owce
				if(orgsInRange.get(i) instanceof Sheep){
					if(this.getPower()>orgsInRange.get(i).getPower()) {
						this.setPower(this.getPower()+orgsInRange.get(i).getPower());
						orgsInRange.get(i).setPower(0);	//sila owcy zostaje wyzerowana
						orgs.remove(orgsInRange.get(i));	// owca zostaje usnieta z listy organizmow
						return true;
					}
					
				}
			}
			return false;
		}
		public boolean reproduce(ArrayList<Organism> org){
				if(this.getPower()>=this.getPowerToReproduce()){
					int stuck=0;
					ArrayList<Position> posInRange= new ArrayList<Position>();
					for(int i=0;i<org.size();i++){
						if(this.p.rangeChecking(org.get(i).p,1)){
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
						org.add(new Wolf(this.p.getPos_x()+randx, this.p.getPos_y()+randy));
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
			g.setColor(Color.RED);
			g.fillRect(this.p.getPos_x(), this.p.getPos_y(), World.pixelSize, World.pixelSize);
		}



}

/*public boolean reproduce(ArrayList<Organism> org, ArrayList<Position> positions){
			if(this.getPower()>=this.getPowerToReproduce()){
				ArrayList<Position> posInRange= new ArrayList<Position>();
				for(int i=0;i<positions.size();i++){
					if(positions.get(i).getPos_x()<=this.p.getPos_x()+1 && positions.get(i).getPos_x()>=this.p.getPos_x()-1
					&& positions.get(i).getPos_y()<=this.p.getPos_y()+1 && positions.get(i).getPos_y()<=this.p.getPos_y()-1	){
						posInRange.add(positions.get(i)); // tworzymy liste pozycji, ktore znajduja sie w zasiegu kroku obiektu
					}
				}
				if(posInRange.size()>=9){
					return false;
				}
				if(this.p.equals(new Position(World.xRange, World.yRange))){ //sprawdzanie czy obiekt znajduje sie w prawym dolnym rogu
					while(true){
						int randx=this.r.nextInt(2)-1;			
						int randy=this.r.nextInt(2)-1;			//zmiana pozycji dozwolona w rogu
						boolean check=true;
						for(int i=0;i<posInRange.size();i++){
							if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
								check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
							}	
						}
						if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) to zmienia pozycje
							org.add(new Wolf(this.p.getPos_x()+randx, this.p.getPos_y()+randy));
							this.setPower(this.getPower()/2);
							return true;
						}
					}

				}
				if(this.p.equals(new Position(World.xRange, 0))){ // prawy gorny rog
					while(true){
						int randx=this.r.nextInt(2)-1;			
						int randy=this.r.nextInt(2);			//zmiana pozycji dozwolona w rogu
						boolean check=true;
						for(int i=0;i<posInRange.size();i++){
							if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
								check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
							}	
						}
						if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) lub ruchem bedzie bezruch  to zmienia pozycje
							org.add(new Wolf(this.p.getPos_x()+randx, this.p.getPos_y()+randy));
							this.setPower(this.getPower()/2);
							return true;
						}
					}
				}
				if(this.p.equals(new Position(0, World.yRange))){ //lewy dolny rog
					while(true){
						int randx=this.r.nextInt(2);			
						int randy=this.r.nextInt(2)-1;			//zmiana pozycji dozwolona w rogu
						boolean check=true;
						for(int i=0;i<posInRange.size();i++){
							if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
								check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
							}	
						}
						if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) to zmienia pozycje
							org.add(new Wolf(this.p.getPos_x()+randx, this.p.getPos_y()+randy));
							this.setPower(this.getPower()/2);
							return true;
						}
					}
				}
				if(this.p.equals(new Position(0, 0))){
					while(true){
						int randx=this.r.nextInt(2);			
						int randy=this.r.nextInt(2);			//zmiana pozycji dozwolona w rogu
						boolean check=true;
						for(int i=0;i<posInRange.size();i++){
							if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
								check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
							}	
						}
						if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) to zmienia pozycje
							org.add(new Wolf(this.p.getPos_x()+randx, this.p.getPos_y()+randy));
							this.setPower(this.getPower()/2);
							return true;
						}
					}
				}
				if(this.p.getPos_x()==0){
					while(true){
						int randx=this.r.nextInt(2);			
						int randy=this.r.nextInt(3)-1;			//zmiana pozycji dozwolona w rogu
						boolean check=true;
						for(int i=0;i<posInRange.size();i++){
							if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
								check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
							}	
						}
						if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) to zmienia pozycje
							org.add(new Wolf(this.p.getPos_x()+randx, this.p.getPos_y()+randy));
							this.setPower(this.getPower()/2);
							return true;
						}
					}
				}
				if(this.p.getPos_x()==World.xRange){
					while(true){
						int randx=this.r.nextInt(2)-1;			
						int randy=this.r.nextInt(3)-1;			//zmiana pozycji dozwolona w rogu
						boolean check=true;
						for(int i=0;i<posInRange.size();i++){
							if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
								check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
							}	
						}
						if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) to zmienia pozycje
							org.add(new Wolf(this.p.getPos_x()+randx, this.p.getPos_y()+randy));
							this.setPower(this.getPower()/2);
							return true;
						}
					
					}
				}
				if(this.p.getPos_y()==0){
					while(true){
						int randx=this.r.nextInt(3)-1;			
						int randy=this.r.nextInt(2);			//zmiana pozycji dozwolona w rogu
						boolean check=true;
						for(int i=0;i<posInRange.size();i++){
							if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
								check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
							}	
						}
						if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) to zmienia pozycje
							org.add(new Wolf(this.p.getPos_x()+randx, this.p.getPos_y()+randy));
							this.setPower(this.getPower()/2);
							return true;
						}
					}
				}
				if(this.p.getPos_y()==World.yRange){
					while(true){
						int randx=this.r.nextInt(3)-1;			
						int randy=this.r.nextInt(3)-1;			//zmiana pozycji dozwolona w rogu
						boolean check=true;
						for(int i=0;i<posInRange.size();i++){
							if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
								check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
							}	
						}
						if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) to zmienia pozycje
							org.add(new Wolf(this.p.getPos_x()+randx, this.p.getPos_y()+randy));
							this.setPower(this.getPower()/2);
							return true;
						}
					}
				}
				else{
					while(true){
						int randx=this.r.nextInt(2)-1;			
						int randy=this.r.nextInt(2)-1;			//zmiana pozycji dozwolona w rogu
						boolean check=true;
						for(int i=0;i<posInRange.size();i++){
							if((this.p.getPos_x()+randx==posInRange.get(i).getPos_x() && this.p.getPos_y()+randy==posInRange.get(i).getPos_y())){
								check=false;		//jezeli zmieniona pozycja jest zajeta to ustawia check na false 
							}	
						}
						if(check==true){		//jezeli pozycja jest wolna (check sie nie zmienil) to zmienia pozycje
							org.add(new Wolf(this.p.getPos_x()+randx, this.p.getPos_y()+randy));
							this.setPower(this.getPower()/2);
							return true;
						}
					}
				}

			}
			return false;
		}
 */		