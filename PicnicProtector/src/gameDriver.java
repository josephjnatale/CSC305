import processing.core.*;
import gifAnimation.*;

import java.*;
import java.util.ArrayList;

public class gameDriver extends PApplet{

	private PApplet p;

	private attackerDriver attackerDriver;
	private WaveSetup wave = new WaveSetup(1);
	private Store store;
	public Map MAP;
	
	

	//Arraylist to keep track of the attackers currently on the screen.
	public ArrayList<Attacker> attackerList = new ArrayList<Attacker>();
	
	//keeps track if first time setup or to resetup wave
	private boolean setup=true;


	//will be in tower class
	private int towerX = 100, towerY = 100;
	private String phase = "build";

	
	public gameDriver(PApplet parent, Map m){
		p=parent;
		MAP = m;
		attackerDriver = new attackerDriver(p, MAP);
		store= new Store(p);
	}
	
	public void init(){
		
		//if fist time setup, after each wave this will be reset so that the correct amount of attacker are loaded
		if(setup){
		
			attackerList=wave.setList();
			setup=false;
			
		}
		
		attackPhase();
		
	}
	
	
	public void draw()
	{	
		
		//one call to redraw everything on the screen
		REDRAW();
		
		switch(phase){
		
		case "build":
			buildPhase();
			store.draw();
			break;
		case "attack":
			init();
			break;
		}
		
		
		//attackerDriver deals with any type of attacker movement
		//
		//p.image(tower, towerX, towerY);
	
	}
	
	private void buildPhase(){
		
		
	}
	
	private void store(){
		
	}
	
	private void attackPhase(){
		
		//deals with all attacker movement
		attackerDriver.main(attackerList);
	}
	
	public void mouseClicked() {
	
			if(phase.equals("build")){
				
			}
			
	}
	
	//calls redrawbg and redrawattacker
	private void REDRAW() {
		//System.out.println("Redrawing");
		MAP.drawMap(p);
		
		//happens once wave starts
		//attackerDriver.redrawAttacker();
		
		
	}
	
	
	

	 
}