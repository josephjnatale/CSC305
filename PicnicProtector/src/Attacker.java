public abstract class Attacker {

	
	private int x, y, movementSpeed;
	protected String type;
	

	public Attacker(int x, int y){
		
		this.x = x;
		this.y = y;

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


