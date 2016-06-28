import processing.core.*;

public class Tower {
	
	private int x, y, range = 60;
	private PImage towerImage;
	
	public Tower(int x, int y){
		this.x = x;
		this.y = y;
		
		
	}
	
	public void repos(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void draw(PApplet p){
		
		p.image(towerImage, x, y,  60, 60);
	}

}
