import processing.core.PApplet;
import processing.core.PImage;

import java.*;
public class gameDriver extends PApplet{

	private PApplet p;
	/**Loads images 
	 */
	private PImage map = loadImage("images\\map.png");
	private PImage squirrelImg = loadImage("images\\squirrel.png");
	private PImage towerImg = loadImage("images\\tower.png");
	
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
	public void draw()
	{	
		p.background(map);
		running();
	}
	public void mouseClicked() {
		tower.repos(p.mouseX-30, p.mouseY-30);
			
	}
}
