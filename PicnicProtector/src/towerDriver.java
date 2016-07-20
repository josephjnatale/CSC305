import java.util.ArrayList;

import processing.core.*;

public class towerDriver extends Main{
	

	private ArrayList<Tower> towerList;
	private ArrayList<Attacker> attackerList;
	protected PImage tower1;
	
	
	public towerDriver(){
		towerList = new ArrayList<Tower>();
	}
	
	public void addTower(int x, int y, int towerSelected){

		switch(towerSelected){
		case 1:
			towerList.add(new CannonTower(x, y));
			System.out.println("added a new Cannon tower");
			break;
		case 2:
			towerList.add(new DarkTower(x, y));
			System.out.println("added a new Dark tower");
			break;
		case 3:
			towerList.add(new MagicTower(x, y));
			System.out.println("added a new Magic tower");
			break;
			
		default:
			break;
		}
			
	}
	
	public boolean closeToOtherTower(int x, int y){
		
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
			if(closetdistance<65)
				return false;
			
			
		}
		
		//check to see if tower is too close to the path
		
		//int red= (int) red(images.map1.get(x, y));
	
		
		//System.out.println(""+x+" , "+y+"\n"+ green);
        
       // int blue= (int) blue(images.map1.get(x, y));
        
        /*if the user clicks on the path return false
		if(green==122)
			return false;
		
		//changes pixel to left edge
		green = green(images.map1.get(x-Tower.WIDTH/2, y));
		
		if(green==122)
			return false;
		
		//changes pixel to top edge
				green = green(images.map1.get(x, y-Tower.WIDTH/2));
				
				if(green==122)
					return false;
				
		//changes pixel to right edge
		green = (int) green(images.map1.get(x+Tower.WIDTH/2, y));
		
		if(green==122)
			return false;
		
		//changes pixel to bottom edge
		green = (int) green(images.map1.get(x, y+Tower.WIDTH/2));
		
		if(green==122)
			return false;
		
		
		*/
			
		return true;
		
	}
	/**Executes each towers 
	 * 
	 * @param attackerList
	 */
	public void hitDetection(ArrayList<Attacker> attackerList){
		for(int i = 0; i<towerList.size(); i++){
			towerList.get(i).hitDetection(attackerList);
		}
		
	}
	
	
	/**gain income
	 * 
	 * @param attackerList
	 */
	public int income(ArrayList<Attacker> attackerList) {
		int score=0;
		
		for(int i=0; i<attackerList.size(); i++){
			if(attackerList.get(i).isHit()){
				if(attackerList.get(i).isDead){
					score += attackerList.get(i).attackDamage();
					attackerList.remove(i);
				}
			}
		}
		
		return score;
	}
	
	public boolean overPath(){
		
		if(greenAtMouse==177)
			return false;
		
		else if(greenAtMouse==122){
			
			System.out.println("INVALID POSITION");
			return true;
		}
		
		
		return false;
	}
	public void redraw(PApplet p){
		
		
		for(int i =0; i<towerList.size(); i++){
			//System.out.println("redrawing tower "+ i);
			towerList.get(i).draw(p);
		}
	}
	
	
	public int getPrice(int towerSelected){
		int price = 0;
		

		price = towerList.get(towerSelected-1).getPrice();
		
		return price;
		
	}
	
}
