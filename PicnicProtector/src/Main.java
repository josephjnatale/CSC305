import processing.core.PApplet;
import processing.core.PImage;
import java.*;

public class Main extends PApplet {
	
	
	private PImage basket = loadImage("images\\basket.jpg");
	private PImage squirrel = loadImage("images\\squirrel.jpg");
	
	private int sx=-squirrel.width/6-basket.width/4-100, bx=-basket.width/4, i=0, basketx=0,squirrelx=0;
	
	
	public void setup(){
		size(1500, 900);
		background(255);
		frameRate(60);
		
	}
	
	public void draw(){
		mainMenu();
		
		
				
	}
	
	private void mainMenu(){
		
		
		fill(255);
		stroke(255);
		rect(0,0,1500,1000);
		
		
		basketx=bx+i%(1500+2*basket.width/4);
		squirrelx=sx+i%(1500+2*squirrel.width/6+basket.width/4);
		
		if(basketx>1500)
			basketx=bx;
		if(squirrelx>1500)
			squirrelx=sx;
		
		
		image(squirrel, squirrelx, 270, width/6, height/6);
		image(basket, basketx, 200, width/4, height/4);
		
		textSize(90);
		fill(0);
		text("Picnic Protector!", 350, 150);
		
		text("PLAY", 500, 750);
		
		i+=5;
	}
	
}
