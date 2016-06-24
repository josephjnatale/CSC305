import processing.core.PApplet;
import processing.core.PImage;

public class Attacker {

	private PImage img;
	private int x, y;

	public Attacker(PImage img, int x, int y){
		this.x = x;
		this.y = y;
		this.img = img;

	}
	
	public void setX(int newX){
		x=newX;
		
	}
	
	public void setY(int newY){
		y=newY;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	public void repos(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void paint(PApplet p){
		p.image(img, x, y,  60, 60);
	}

}


