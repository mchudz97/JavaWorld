package javaWorld;

import java.util.ArrayList;

public abstract class Plant extends Organism{
	Plant(){
		super();
	};

		
	public boolean interact(ArrayList<Organism> orgs){
		return false;
	}
	public abstract boolean reproduce(ArrayList<Organism> orgs);
	public boolean updateStats(ArrayList<Organism> arr){
		if(this.getLiveLength()<1) {
			arr.remove(this);
			return true;
		}
		else{
			this.setLiveLength(this.getLiveLength()-1);
			this.setPower(this.getPower()+0.1);
			return false;
		}
	}
	public boolean move(ArrayList<Organism> orgs){ 
		return false;
	}	
		

		
	
	
}
