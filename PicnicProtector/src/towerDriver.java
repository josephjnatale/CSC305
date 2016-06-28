import java.util.ArrayList;

import processing.core.*;

public class towerDriver {
	
	private PApplet p;
	private ArrayList<Tower> towerList;
	private PImage tower1;
	
	
	public towerDriver(PApplet applet){
		
		p=applet; 
		towerList = new ArrayList<Tower>();
		tower1= p.loadImage("images\\tower.png");
	}
	
	public void addTower(int x, int y){
		
		if(checkPlacement(x, y)){
		
			towerList.add(new Tower(p, x, y));
			
		}
	}
	
	private boolean checkPlacement(int x, int y){
		
		//checks distance to closest tower
		if(!towerList.isEmpty()){
			
			Tower closestTower=towerList.get(0);
			for(int i=1; i<towerList.size();i++){
				if(closestTower.distanceToTower(towerList.get(i))>150)
					return false;
			}
			
		}
		return true;
		
	}
	
	public void redraw(){
		for(int i =0; i<towerList.size(); i++){
			towerList.get(i).draw(tower1);
		}
	}

}
