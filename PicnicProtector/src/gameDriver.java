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
	


	//Arraylist to keep track of the attackers currently on the screen.
	public ArrayList<Attacker> attackerList = new ArrayList<Attacker>();
	
	private boolean setup=true;


	/**Squirrel position
	 * 
	 */
	private int towerX = 100, towerY = 100;
	private Tower tower = new Tower(towerImg, towerX, towerY);
	//private Attacker squirrel = new Attacker(squirrelImg, x, y);
	

	
	public gameDriver(PApplet parent){
		p=parent;
	}
	
	
	/*Just moves a squirrel very basic but at least it isn't just a picture
	 * 
	 
	public void running(){

		
		if(y>= 340 && x>=740 && y<720){
			y++;
		}else if(y <=340){
			y++;
		}else if(x <=740){
			x++;
		}else{
			x = 140;
			y = 60;
		}
		squirrel.repos(x, y);
		squirrel.paint(p);
		tower.paint(p);
	}
	
	 
	 */
	
	public void init(){
		
		//if fist time setup, after each wave this will be reset so that the correct amount of attacker are loaded
		
		if(setup){
			
			WaveSetup wave = new WaveSetup(1);
		
			attackerList=wave.setList();
			
			setup=false;
		}
		draw();
		
	}
	
	
	public void draw()
	{	
		
		
		REDRAW();
		
		attackerMovement();
	
		
	
		//running();
	}
	
	public void mouseClicked() {
		tower.repos(p.mouseX-30, p.mouseY-30);
			
	}
	
	private void REDRAW() {
		System.out.println("Redrawing");
		redrawBg();
		redrawAttacker();
		
	}
	
	private void redrawAttacker() {
		
		for(int i=0; i<attackerList.size(); i++){

			int xPos=attackerList.get(i).getX();
			int yPos=attackerList.get(i).getY();
			
			switch(attackerList.get(i).getType()){
			
			case "squirrel":
				p.image(squirrel, xPos, yPos, 60, 60);
				break;
			
			}
			System.out.println("redrew Attacker at("+attackerList.get(i).getX()+" ,"+attackerList.get(i).getY()+")");
			
		}
	}
	
	private void redrawBg() {
		p.image(map, 0, 0);
		System.out.println("redrew background");
		
	}
	

	private void attackerMovement() {
		
		for(int i=0; i<attackerList.size(); i++){
			
			
			attackerMove(attackerList.get(i));
		
		}
		
		//redrawAttacker();
	}

	private void attackerMove(Attacker attacker) {

		//will get turning point values from array within map class.

		if(attacker.getY() >= 340 && attacker.getX() >=740 && attacker.getY() <720){
			attacker.setY(attacker.getX()+1);
		}else if(attacker.getY() <=340){
			attacker.setY(attacker.getY()+1);
		}else if(attacker.getX() <=740){
			attacker.setX(attacker.getX()+1);
		}else{
			attacker.setX(140);
			attacker.setY(60);
		}

	}


	 
}
