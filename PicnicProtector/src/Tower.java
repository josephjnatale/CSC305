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
	
	public int distanceToTower(Tower tower){
		return (int) Math.sqrt(Math.pow((double)tower.getY()-y, 2.0)+Math.pow((double)tower.getX()-x, 2.0));
	}

}
