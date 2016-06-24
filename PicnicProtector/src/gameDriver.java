import processing.core.PApplet;
import processing.core.PImage;

import java.*;
import java.util.ArrayList;
public class gameDriver extends PApplet{

	private PApplet p;
	/**Loads images 
	 */
	private PImage map = loadImage("images\\map.png");
	private PImage squirrelImg = loadImage("images\\squirrel.png");
	private PImage towerImg = loadImage("images\\tower.png");
	


	//Arraylist to keep track of the attackers currently on the screen.
	public ArrayList<Attacker> attackerList = new ArrayList<Attacker>();
	


	/**Squirrel position
	 * 
	 */
	private int x = 140,y = 60, towerX = 100, towerY = 100;
	private Tower tower = new Tower(towerImg, towerX, towerY);
	private Attacker squirrel = new Attacker(squirrelImg, x, y);
	public gameDriver(PApplet parent){
		p= parent;
	}
	/**Just moves a squirrel very basic but at least it isn't just a picture
	 * 
	 */
	public void running(){

		/**These values just control the movement of the squirrel
		 * 
		 */
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
	
	/**Sets background first so it paints over it
	 * 
	 */
	public void init() {
		p.fill(255);
		p.rect(0, 0, 100001, 0101000);
		redrawBg();
		addAttacker();
		p.frameRate(60);
		draw();
	}
	
	
	public void draw()
	{	
		int counter=0;
		while(true){
			
			//if(counter++ %100==0)
			//REDRAW();
			if(p.frameCount %50==0)
			attackerMovement();
			
		}
		
	
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
			p.image(squirrelImg, attackerList.get(i).getX(), attackerList.get(i).getY());
			System.out.println("redrew Attacker at("+attackerList.get(i).getX()+" ,"+attackerList.get(i).getY()+")");
			i++;
		}
	}
	
	private void redrawBg() {
		p.image(map, 0, 0);
		System.out.println("redrew background");
		
	}
	
	private void addAttacker(){
		//will get list of attackers meant to be added this wave, list will be part of the map class
		Attacker squirrel1 = new Attacker(squirrelImg, 140, 60);
		attackerList.add(squirrel1);
		System.out.println("Added squirrel to attackerList");

	}

	private void attackerMovement() {
		
		for(int i=0; i<attackerList.size(); i++){
			attackerMove(attackerList.get(i));
		
		}
		
		redrawAttacker();
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
