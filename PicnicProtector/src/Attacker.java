import processing.core.PApplet;

public abstract class Attacker {

	//movementspeed is how many pixels per update attacker should move
	protected double x, y, movementSpeed;
	
	//will be set depending on the map
	protected String type, moving="down";
	

	public Attacker(double x, double y){
		
		this.x = x;
		this.y = y;

	}
	
	
	public void move(){
		
		switch(moving){
		case "down":
			y+=movementSpeed;
			break;
		case "left":
			x-=movementSpeed;
			break;
		case "right":
			x+=movementSpeed;
			break;
		case "up":
			y-=movementSpeed;
			break;
		}
	}
	
	public void setMovingDirection(String dir){
		moving=dir;
	}
	
	public String getMovingDirection(){
		return moving;
	}
		
	public String getType(){
		return type;
	}
	
	public void setX(int newX){
		x=newX;	
	}
	
	public void setY(int newY){
		y=newY;
	}
	
	public int getX(){
		return (int) x;
	}
	
	public int getY(){
		return (int) y;
	}
	
	public void repos(double x, double y){
		this.x = x;
		this.y = y;
	}


	public abstract void draw(PApplet p);



}


