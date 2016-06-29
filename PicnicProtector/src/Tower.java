import processing.core.*;

public class Tower{
	
	private int x, y, range = 60;
	private PApplet p;
	
	public Tower(PApplet applet, int x, int y){
		p=applet;
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
	
	public void draw(PImage image){
		
		
		p.image(image, x, y,  100, 100);
	}
	
	public int distanceToPoint(int xPos, int yPos){
		return (int) Math.sqrt(Math.pow((double)yPos-y, 2.0)+Math.pow((double)xPos-x, 2.0));
	}

}
