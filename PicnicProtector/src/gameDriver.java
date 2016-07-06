import processing.core.*;
import gifAnimation.*;

import java.*;
import java.awt.Color;
import java.util.ArrayList;

public class gameDriver extends Main {

	private PApplet p;

	private attackerDriver attackerDriver;
	public towerDriver towerDriver;
	private WaveSetup wave = new WaveSetup(1);
	private Store store;
	public Map MAP;
	
	

	//Arraylist to keep track of the attackers currently on the screen.
	public ArrayList<Attacker> attackerList = new ArrayList<Attacker>();
	
	//keeps track if first time setup or to resetup wave
	private boolean setup=true;


	private String phase = "build";

	
	public gameDriver(PApplet parent, Map m){
		//sets Papplet
		p=parent;
		
		//loads map data
		MAP = m;
		
		//creates attackerdriver sends over applet and map class
		attackerDriver = new attackerDriver(p, MAP);
		
		//creates a new store  sends over the appelt
		store= new Store(p);
		
		//creates tower driver sends over appelt
		towerDriver = new towerDriver();
	}
		
	public void draw()
	{	
		System.out.println(""+greenAtMouse);
		/*
		Order of the layers drawn
		
		
		store images
		tower select rects
		Store
		attackers
		towers
		background		
		
		*/
		
		REDRAW(phase);
		
		
		//controls the phase, things that happen in both are just drawn ouside the switch
		switch(phase){
		
		case "build":
			buildPhase();
			break;
			
		case "attack":
			init();
			break;
		}
		
		//draws images for the store
		store.drawTowers(p);
		
	}
	
	public void init(){
		
		//if fist time setup, after each wave this will be reset so that the correct amount of attacker are loaded
		if(setup){
		
			attackerList=wave.setList();
			setup=false;
			
		}
		
		attackPhase();
		
	}
	
	private void buildPhase(){
		//build phase allows for map select
		//store.main handels any mouse interaction with the store
		store.main();
		
		//will have different options in the future, but for now this dictates what image to draw at the mouse location
		//when player has clicked on a tower image from the store.
		//towerSelected will bee -1 when nothing is selected, the default handles that case
		switch(store.towerSelected()){
		case 1:
			p.image(store.getImage(), p.mouseX, p.mouseY, 100, 100);
			
			break;
		default:
			break;
		}
		
		//if there is a tower selected and the player has clicked and the mouse is not over the store
		//place a tower at the players mouse coor's
		if(store.towerSelected()!=-1 && p.mousePressed && !store.overStore()){
			
			//check the placement of the tower
			if(towerDriver.closeToOtherTower(p.mouseX, p.mouseY)&& !towerDriver.overPath())
				placeTower(p.mouseX, p.mouseY);
			
			//if too close to another tower
			else if(!towerDriver.closeToOtherTower(p.mouseX, p.mouseY)){
				p.fill(Color.red.getRGB());
				p.ellipse(p.mouseX, p.mouseY, 75, 75);
				p.fill(Color.black.getRGB());
				p.textSize(12);
				p.text("Too Close\nTo Other\n  Tower", p.mouseX-25, p.mouseY-12);
			}
			
			//if over the path
			else if(towerDriver.overPath()){
				p.fill(Color.red.getRGB());
				p.ellipse(p.mouseX, p.mouseY, 75, 75);
				p.fill(Color.black.getRGB());
				p.textSize(12);
				p.text("Too Close\nTo Other\n  Path", p.mouseX-25, p.mouseY-12);
			}
			
		}
		
		
		
	}
	
	//adds the tower newly placed tower to the towerList inside gamedriver and deselects the tower from currently selected
	private void placeTower(int x, int y){
		
		towerDriver.addTower(x,  y);
		store.setTowerSelected(-1);
	}
	
	
	private void attackPhase(){
		
		//deals with all attacker movement
		attackerDriver.main(attackerList);
	}
	
	//calls redrawbg and redrawattacker
	private void REDRAW(String phase) {
		//System.out.println("Redrawing");
		
		MAP.drawMap(p);
		
		drawInterface();
		
		//redraws placed tower on map
		towerDriver.redraw(p);
		
		if(phase.equals("attack") && !setup){
			//happens once wave starts
			//System.out.println("Drawing attackers");
			attackerDriver.redrawAttacker();
			
		}
		
		//System.out.println("CurrentPhase: "+phase+"\n");
		
		
	}
	
	private void drawInterface(){
		p.fill(255);
		p.rect(0, 0, 1280, 60);
		
		p.fill(Color.green.getRGB());
		p.rect(1080, 10, 175, 40);
		p.fill(Color.blue.getRGB());
		p.textSize(30);
		p.text("Start Wave", 1100, 35);
		
		//clicked on start wave set phase to attack
		if(p.mousePressed && p.mouseX>1080 && p.mouseX<1260 && p.mouseY>10 && p.mouseY<50){
			//System.out.println("Clicked on wave start, setting phase to attack phase");
			phase="attack";
		}
	
	}
	

	 
}