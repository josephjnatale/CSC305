import processing.core.PApplet;

public abstract class Attacker extends Main {

	//movementspeed is how many pixels per update attacker should move
	protected double x, y, movementSpeed;
	
	protected int attackDamage, health;
	
	
	//will be set depending on the map
	protected String type, moving="down";
	
	//true if the attacker is at the end of the path
	protected boolean atEnd=false;
	
	protected boolean isDead = false;

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
		
		endCheck();
	}
	
	public boolean atEnd(){
		return atEnd;
	}
	
	public int attackDamage(){
		return attackDamage;
	}
	
	public int getHealth(){
		return health;
	}
	public void setHealth(int updatedHealth){
		health = updatedHealth;
	}
	private void endCheck(){
		
		//needs to be changed if added different style maps
		if(y>730)
			atEnd=true;
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

	public boolean isHit() {
		return isDead;
	}

}


