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
	
	private Gif squirrelLeft = new Gif(this, "images\\squirrelLeft.gif");
	private Gif squirrelRight = new Gif(this, "images\\squirrelRight.gif");
	private Gif squirrelUp = new Gif(this, "images\\squirrelUp.gif");
	private Gif squirrelDown = new Gif(this, "images\\squirrelDown.gif");
	
	private attackerDriver attackerDriver = new attackerDriver();
	private WaveSetup wave = new WaveSetup(1);
	
	public Map MAP = new Map(1);
	
	

	//Arraylist to keep track of the attackers currently on the screen.
	public ArrayList<Attacker> attackerList = new ArrayList<Attacker>();
	
	//keeps track if first time setup or to resetup wave
	private boolean reSetup=true, setup=true;


	//will be in tower class
	private int towerX = 100, towerY = 100;
	private Tower tower = new Tower(towerImg, towerX, towerY);

	
	public gameDriver(PApplet parent){
		p=parent;
	}
	
	public void init(){
		
		//if fist time setup, after each wave this will be reset so that the correct amount of attacker are loaded
		
		if(reSetup){
		
			attackerList=wave.setList();
			
			if(setup){
				squirrelDown.play();
				squirrelUp.play();
				squirrelLeft.play();
				squirrelRight.play();
				
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
		p.image(tower, towerX, towerY);
	
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
				switch(attackerList.get(i).getMovingDirection())
				{
					case "down": 
						p.image(squirrelDown, xPos,  yPos);
						break;
					case "left":
						p.image(squirrelLeft, xPos, yPos);
						break;
					case "right":
						p.image(squirrelRight, xPos, yPos);
						break;
					case "up":
						p.image(squirrelUp, xPos, yPos);
						break;
				}
				break;
			
			}
			//System.out.println("redrew Attacker at("+attackerList.get(i).getX()+" ,"+attackerList.get(i).getY()+")");
			
		}
	}
	
	//redraws the background
	private void redrawBg() {
		p.image(map, map.width/2, map.height/2);
		//System.out.println("redrew background");
		
	}
	 
}