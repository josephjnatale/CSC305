import gifAnimation.Gif;
import processing.core.*;

public class squirrel extends Attacker{

	
	private Gif squirrelLeft;
	private Gif squirrelRight;
	private Gif squirrelUp;
	private Gif squirrelDown;
	
	public squirrel(double x, double y) {
		super(x, y);
		type="squirrel";
		
		movementSpeed=1;
				
	}
	
	
	public void draw(PApplet p){
		
		
		squirrelDown.play();
		squirrelUp.play();
		squirrelLeft.play();
		squirrelRight.play();
		
		switch(moving)
		{
			case "down": 
				p.image(squirrelDown, this.getX(),  this.getY());
				break;
			case "left":
				p.image(squirrelLeft, this.getX(),  this.getY());
				break;
			case "right":
				p.image(squirrelRight, this.getX(),  this.getY());
				break;
			case "up":
				p.image(squirrelUp, this.getX(),  this.getY());
				break;
		}
		
		
		
	}

	
	
	
	
	

}
