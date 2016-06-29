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
		
		
		towerList.add(new Tower(p, x, y));
		System.out.println("added a new tower");
	}
	
	public boolean checkPlacement(int x, int y){
		
		//if it is not the first tower to be added
		if(towerList.size()!=0){
			
			//sets closest Tower to the first tower
			Tower closestTower=towerList.get(0);
			
			int closetdistance=closestTower.distanceToPoint(x, y);
			
			//cross check each tower in the towerList
			//also only loops if more than 1 tower in list otherwise it skips over this
			for(int i=1; i<towerList.size();i++){
					
				//if current tower is closer than closet tower, fix that
				if(towerList.get(i).distanceToPoint(x, y)<closetdistance){
					
					//set closest distance to current tower
					closetdistance=towerList.get(i).distanceToPoint(x, y);
					//sets closet tower to current tower
					closestTower=towerList.get(i);
					
				
				}
			}
			
			System.out.println("Distance to closet Tower: "+closetdistance);
			
			//this is where it decides if it the current tower is too close or not
			if(closetdistance<60)
				return false;
			
			
		}
		//check to see if tower is too close to the path
		//if()
		
			
		return true;
		
	}
	
	public void redraw(){
		for(int i =0; i<towerList.size(); i++){
			towerList.get(i).draw(tower1);
		}
	}

}
