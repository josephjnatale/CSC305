import java.util.ArrayList;

import processing.core.*;

public class towerDriver {
	
	private PApplet p;
	private ArrayList<Tower> towerList;
	
	public towerDriver(PApplet applet){
		
		p=applet; 
		towerList = new ArrayList<Tower>();
	}
	
	public void addTower(int x, int y){
		
		towerList.add(new Tower(x, y));
	}
	
	public void redraw(){
		for(int i =0; i<towerList.size(); i++){
			towerList.get(i).draw(p);
		}
	}

}
