import processing.core.*;

import java.*;
import java.util.ArrayList;

public class gameDriver extends PApplet{

	private PApplet p;
	/**Loads images 
	 */
	private PImage map = loadImage("images\\map.png");
	private PImage squirrel = loadImage("images\\squirrel.png");
	private PImage towerImg = loadImage("images\\tower.png");
	
	
	private attackerDriver attackerDriver = new attackerDriver();
	private WaveSetup wave = new WaveSetup(1);
	
	public Map MAP = new Map(1);
	
	

	//Arraylist to keep track of the attackers currently on the screen.
	public ArrayList<Attacker> attackerList = new ArrayList<Attacker>();
	
	//keeps track if wave needs to be set up.
	private boolean setup=true;


	//will be in tower class
	private int towerX = 100, towerY = 100;
	private Tower tower = new Tower(towerImg, towerX, towerY);

	//private Attacker squirrel = new Attacker(squirrelImg, x, y);
	

	
	public gameDriver(PApplet parent){
		p=parent;
	}
	
	
	
	
	public void init(){
		
		//if fist time setup, after each wave this will be reset so that the correct amount of attacker are loaded
		
		if(setup){
		
			attackerList=wave.setList();
			
			setup=false;
		}
		draw();
		
	}
	
	
	public void draw()
	{	
		//one call to redraw everything on the screen
		REDRAW();
		
		//attackerDriver deals with any type of attacker movement
		attackerDriver.main(attackerList);
	
	}
	
	public void mouseClicked() {
		tower.repos(p.mouseX-30, p.mouseY-30);
			
	}
	
	//calls redrawbg and redrawattacker
	private void REDRAW() {
		//System.out.println("Redrawing");
		redrawBg();
		redrawAttacker();
		
	}
	
	//redraws the attackers
	private void redrawAttacker() {
		
		for(int i=0; i<attackerList.size(); i++){

			int xPos=attackerList.get(i).getX();
			int yPos=attackerList.get(i).getY();
			
			switch(attackerList.get(i).getType()){
			
			case "squirrel":
				p.image(squirrel, xPos, yPos, 60, 60);
				break;
			
			}
			//System.out.println("redrew Attacker at("+attackerList.get(i).getX()+" ,"+attackerList.get(i).getY()+")");
			
		}
	}
	
	//redraws the background
	private void redrawBg() {
		p.image(map, 0, 0);
		p.fill(255);
		
		//checking turning points
		p.rect(145, 345, 55, 50);

		
		//System.out.println("redrew background");
		
	}
	

	


	 
}
