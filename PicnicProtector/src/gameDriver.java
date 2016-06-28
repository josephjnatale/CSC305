import processing.core.*;
import gifAnimation.*;

import java.*;
import java.util.ArrayList;

public class gameDriver extends PApplet{

	private PApplet p;
	
	
	/**Loads images 
	 */
	private PImage map = loadImage("images\\map1.png");
	//private PImage squirrel = loadImage("images\\squirrel.png");
	private PImage towerImg = loadImage("images\\tower.png");

	
	private attackerDriver attackerDriver;
	private WaveSetup wave = new WaveSetup(1);
	
	public Map MAP;
	
	

	//Arraylist to keep track of the attackers currently on the screen.
	public ArrayList<Attacker> attackerList = new ArrayList<Attacker>();
	
	//keeps track if first time setup or to resetup wave
	private boolean reSetup=true, setup=true;


	//will be in tower class
	private int towerX = 100, towerY = 100;
	private Tower tower = new Tower(towerImg, towerX, towerY);

	
	public gameDriver(PApplet parent, Map m){
		p=parent;
		MAP = m;
		attackerDriver = new attackerDriver(p, MAP);
	}
	
	public void init(){
		
		//if fist time setup, after each wave this will be reset so that the correct amount of attacker are loaded
		
		if(reSetup){
		
			attackerList=wave.setList();
			
			if(setup){
				
				
				setup=false;
			}
				
			reSetup=false;
			
		}
		draw();
		
	}
	
	
	public void draw()
	{	
		//one call to redraw everything on the screen
		REDRAW();
		
		//attackerDriver deals with any type of attacker movement
		attackerDriver.main(attackerList);
		//p.image(tower, towerX, towerY);
	
	}
	
	public void mouseClicked() {
		tower.repos(p.mouseX-30, p.mouseY-30);
			
	}
	
	//calls redrawbg and redrawattacker
	private void REDRAW() {
		//System.out.println("Redrawing");
		MAP.drawMap(p);
		//happens once wave starts
		//attackerDriver.redrawAttacker();
		
		
	}
	
	
	

	 
}