import processing.core.*;
import gifAnimation.*;

import java.*;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class gameDriver extends Main {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private PApplet p;
	private int currentWave=1;

	private attackerDriver attackerDriver;
	public towerDriver towerDriver;
	private WaveSetup wave = new WaveSetup(currentWave);
	private Store store;
	public Map MAP;
	
	
	//the lower the better
	private int towerFireDelay = 180;
	
	//the players health
	private int playerHealth;
	
	private int towerSelected;
	

	//Arraylist to keep track of the attackers currently on the screen.
	private ArrayList<Attacker> attackerList = new ArrayList<Attacker>();
	
	//keeps track if first time setup or to resetup wave
	private boolean setup=true;
	
	//true if player has no health
	private boolean noHealth;
	
	//score of the player
	private int playerScore=1000;
	private int reloadTime = millis();

	public static String phase = "build";


	
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
		
		//sets player health to 100 upon start of new map
		playerHealth=1000;
		noHealth=false;
		
		//sets current wave to 1
		currentWave=1;

		
		
	}
		
	public void draw()
	{	
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
			attackPhaseDriver();
			break;
		
		case "endWave":
			waveSummary();
			break;
				
		
		}
		
		//draws images for the store
		store.drawTowers(p);
		
		
		//check if the player has lost done every 15 frames
		if(p.frameCount%15==0 && playerHealth<=0)
			noHealth=true;
			
		LOGGER.fine("player health: " + playerHealth);
		
		
		
	}
	
	public int getScore(){
		return playerScore;
	}
	
	public int getWave(){
		return currentWave;
	}
	
	public boolean noHealthCheck(){
		
		return noHealth;
	}
	
	
	public void attackPhaseDriver(){
		
		//if fist time setup, after each wave this will be reset so that the correct amount of attacker are loaded
		if(setup){
		
			attackerList=wave.setList();
			setup=false;
			
		}
		
		//all the attacker
		
		attackPhase();
		
	}
	
	private void buildPhase(){
		//build phase allows for map select
		//store.main handels any mouse interaction with the store
		store.main();
		
		//will have different options in the future, but for now this dictates what image to draw at the mouse location
		//when player has clicked on a tower image from the store.
		//towerSelected will bee -1 when nothing is selected, the default handles that case
		p.fill(Color.blue.getRGB(), 112);
		switch(store.towerSelected()){
		case 1:
			p.ellipse(p.mouseX, p.mouseY, 2*store.getTowerRange(store.towerSelected()), 2*store.getTowerRange(store.towerSelected()));	
			p.image(store.getImageT1(), p.mouseX, p.mouseY);			
			break;
			
		case 2:
			p.ellipse(p.mouseX, p.mouseY, 2*store.getTowerRange(store.towerSelected()), 2*store.getTowerRange(store.towerSelected()));	
			p.image(store.getImageT2(), p.mouseX, p.mouseY);			
			break;
			
		case 3:
			p.ellipse(p.mouseX, p.mouseY, 2*store.getTowerRange(store.towerSelected()), 2*store.getTowerRange(store.towerSelected()));	
			p.image(store.getImageT3(), p.mouseX, p.mouseY);
			break;
		
		default:
			break;
		}
		
		//if there is a tower selected and the player has clicked and the mouse is not over the store
		//place a tower at the players mouse coor's
		if(store.towerSelected()!=-1 && p.mousePressed && !store.overStore()){
			
			//check the placement of the tower
			if(towerDriver.closeToOtherTower(p.mouseX, p.mouseY)&& !towerDriver.overPath()){
				placeTower(p.mouseX, p.mouseY);
			}
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
		towerSelected=store.towerSelected();
		
		towerDriver.addTower(x, y , towerSelected);
		store.setTowerSelected(-1);
		
		if(playerScore <= 0){
			playerScore = 0; 
			phase="attack";
		}
	}
	
	
	
	private void attackPhase(){
		
		
		//deals with all attacker movement
		attackerDriver.main(attackerList);
		
		//check to see if any attackers are at end path and returns the damage to be done, returns 0 other wise
		playerHealth-=attackerDriver.endPathcheck(attackerList);
		/**Timing of hit Detection
		 * 
		 */
		if(millis() >= reloadTime){
			towerDriver.hitDetection(attackerList);
			reloadTime = millis() + towerFireDelay;
			
			playerScore+= towerDriver.income(attackerList);
	
		}
		if(attackerList.isEmpty()){
			phase="endWave";
			//adds end of wave bonus
			
			
			phase="build";
			setup=true;
			
			

			currentWave++;
			wave.setWave(currentWave);
		}
		
		
		
	}
	
	private void waveSummary(){
		
		p.stroke(0);
		p.fill(Color.yellow.getRGB());
		p.rect(100, 100,  850,  500);
		p.textSize(35);
		p.fill(0);
		p.text("Wave Summary", 450, 150);
		p.textSize(25);
		p.text("Wave "+currentWave+" Completed!\nAttacker break down\nSquirrels last wave: "+wave.getSquirrelCount(currentWave), 150, 200);
		p.text("Wave Bonus: $"+currentWave*50, 570, 200);
		p.text("Attackers next wave: " +wave.getSquirrelCount(currentWave+1), 570, 270);
		
		p.fill(Color.green.getRGB());
		p.rect(700, 500, 230, 75);
		p.fill(Color.pink.getRGB());
		p.text("Continue", 760, 540);
		
		
		if(p.mousePressed && p.mouseX>=700 && p.mouseX<=930 && p.mouseY>=500 && p.mouseY<=575){
		
			phase="build";
			setup=true;
			currentWave++;			
			wave.setWave(currentWave);
		}
		
	}
	
	//calls redrawbg and redrawattacker
	private void REDRAW(String phase) {
	    
	    LOGGER.fine("Redrawing");

		
		MAP.drawMap(p);
		
		drawInterface();
		
		//redraws placed tower on map
		towerDriver.redraw(p);
		
		if(phase.equals("attack") && !setup){
			//happens once wave starts
			LOGGER.fine("Drawing attackers");
			attackerDriver.redrawAttacker();
			
		}
		
		
		
	}
	
	private void drawInterface(){
		
		p.stroke(0);
		p.strokeWeight(1);
		
		p.fill(255);
		p.rect(0, 0, 1280, 60);
		
		p.fill(Color.green.getRGB());
		p.rect(1080, 10, 175, 40);
		p.fill(Color.blue.getRGB());
		p.textSize(30);
		p.text("Start Wave", 1100, 38);
		
		//clicked on start wave set phase to attack
		if(p.mousePressed && p.mouseX>1080 && p.mouseX<1260 && p.mouseY>10 && p.mouseY<50){
			//System.out.println("Clicked on wave start, setting phase to attack phase");
			phase="attack";
		}
		
		p.textSize(20);
		p.text("Phase: "+phase, 900, 35);
		p.text("Player Health: "+playerHealth, 10,  35);
		p.text("Score: "+playerScore, 400, 35);
		p.text("Wave: "+currentWave, 700, 35);
	
	}
	

	
	 
}
