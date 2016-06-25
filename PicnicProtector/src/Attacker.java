public abstract class Attacker {

	//movementspeed is how many pixels per update attacker should move
	protected int x, y, movementSpeed;
	
	//will be set depending on the map
	protected String type, moving="down";
	

	public Attacker(int x, int y){
		
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
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void repos(int x, int y){
		this.x = x;
		this.y = y;
	}


}


