import gifAnimation.Gif;
import processing.core.*;

public class squirrel extends Attacker{

	
	
	public squirrel(double x, double y) {
		super(x, y);
		type="squirrel";
		
		movementSpeed=3;
		
		attackDamage=20;
				
	}
	
	
	public void draw(PApplet p){
		
		
		
		
		switch(moving)
		{
			case "down":
				images.squirrelDown.play();
				p.image(images.squirrelDown, this.getX(),  this.getY());
				break;
			case "left":
				images.squirrelLeft.play();
				p.image(images.squirrelLeft, this.getX(),  this.getY());
				break;
			case "right":
				images.squirrelRight.play();
				p.image(images.squirrelRight, this.getX(),  this.getY());
				break;
			case "up":
				images.squirrelUp.play();
				p.image(images.squirrelUp, this.getX(),  this.getY());
				break;
		}
		
		
		
	}

	
	
	
	
	

}
