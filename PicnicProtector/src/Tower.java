import processing.core.PApplet;
import processing.core.PImage;

public class Tower {
	
	private PImage img;
	private int x, y, range = 60;
	
	public Tower(PImage img, int x, int y){
		this.x = x;
		this.y = y;
		this.img = img;
		
	}
	public void repos(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void paint(PApplet p){
		p.image(img, x, y,  60, 60);
	}

}
