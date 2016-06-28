import processing.core.PApplet;
import processing.core.PImage;
import java.*;
import java.awt.Color;

public class Main extends PApplet {
	
	private Map MAP;
	private gameDriver game;
	
	private PImage basket = loadImage("images\\basket.png");
	public PImage squirrel = loadImage("images\\squirrel.png");
	private PImage menuBackground = loadImage("images\\menu.jpg");
	private PImage map = loadImage("images\\map1.png");
	
	private int gameState = 0, mapSelected =-1;
	
	private int sx=-squirrel.width/6-basket.width/4-100, bx=-basket.width/4, i=0, basketx=0,squirrelx=0;
	
	
	public void setup(){
		
	
		size(menuBackground.width, menuBackground.height);
		background(255);
		frameRate(60);
		imageMode(CENTER);
	}
	
	public void draw(){
		
		
		
		//state machine
		switch(gameState){
		case 0:
			mainMenu();
			break;
			
		case 1:
			selectMap();
			break;
			
		case 2:
			game.draw();
			break;	
		
		case 3:
			instructions();
			break;
		}
		
		//draws framerate over any screen.
		fill(Color.cyan.getRGB());
		text(frameRate, 50, 50);			
	}
	
	private void instructions(){
		background(100);
		textSize(60);
		fill(255);
		text("I will fill in instructions later.", 200, 400);
		//If mouse is over play, change the fill purple 
		if(mouseX >= 50 && mouseX <=50+125 && mouseY>=650 && mouseY<=700 && gameState == 3)
			fill(186,85,211);
		text("Back", 50, 700);
		
		
		
	}
	
	private void selectMap(){
		fill(255);
		rect(0,0,1280,720);
		
		if(mouseX > 70 && mouseX <420 && mouseY> 100 && mouseY<470)
			fill(Color.green.getRGB());
		else
			fill(Color.pink.getRGB());
		
		rect(70, 100, 350, 370);
		
		image(map, 250, 200, map.width/4, map.height/4);
		textSize(35);
		fill(0);
		text("Map 1", 200, 400);
	}
	
	private void mainMenu(){
		
		//sets background image
		image(menuBackground,menuBackground.width/2, menuBackground.height/2);
				
		//basket is the basket's x position, bx is the distance needed to hide the image off the left side of the screen. 
		//squirrel's x needs to also clear the baskets x so sx is larger than bx.
		//% makes it loop back to the beginning.
		basketx=bx+i%(1280+2*basket.width/4);
		squirrelx=sx+i%(1280+2*squirrel.width/6+basket.width/4);
		
		
		//if at end, then go back to left side
		if(basketx>1280)
			basketx=bx;
		if(squirrelx>1280)
			squirrelx=sx;
		
		//draw the images at the specified positions, make the image a sixth and a fourth the original size.
		//System.out.println("Drawing moving things");
		image(squirrel, squirrelx, 270, width/6, height/6);
		image(basket, basketx, 200, width/4, height/4);
		
		
		//Increments i, i is the amount of pixels the basket and squirrel moves each time around
		i+=5;
		
		//writes Picnic game title
		textSize(90);
		fill(100,0,100);
		text("Picnic Protector!", 325, 90);
		
		
		//Checks each of the text to see if mouse is over that text, if so change corresponding text to purple
		//sets text size to 60 and the fill to white
		textSize(60);
		fill(255);
		
		
		
		//If mouse is over play, change the fill purple 
		if(mouseX >= 580 && mouseX <=580+140 && mouseY>=650 && mouseY<=700)
			fill(186,85,211);
		
		//Write play set color back to white
		text("PLAY", 580, 700);
		fill(255);
		
		//if mouse is over how to play, change the fill
		if(mouseX >= 100 && mouseX <=450 && mouseY>=650 && mouseY<=700)
			fill(186,85,211);
		
		//write how to play, and set fill back to white
		text("How to play", 100, 700);
		fill(255);
		
		//rect(950, 650, 130, 60);
		if(mouseX >= 950 && mouseX <=950+130 && mouseY>=650 && mouseY<=700)
			fill(186, 85, 211);
		
		//write quit,
		text("Quit", 950, 700);
		fill(255);
	}
	
	public void mouseClicked() {
		//if on main menu and they click
		if(gameState==0 && mouseX >= 580 && mouseX <=580+140 && mouseY>=650 && mouseY<=700){
			System.out.println("Detected click on Play, sending over to gameDriver");
			//Changes the game state from start menu to running
			gameState = 1;
			
		}		
		
		//if on menu n click instructions
		if(gameState==0 && mouseX >= 100 && mouseX <=450 && mouseY>=650 && mouseY<=700){
			System.out.println("Detected click on Instructions, sending over to gameDriver");
			//Changes the game state from start menu to instructions
			gameState = 3 ;
		}
		
		//if user clicks on back change game state
		if(mouseX >= 50 && mouseX <=50+125 && mouseY>=650 && mouseY<=700 && gameState == 3)
			gameState = 0;
		
		
		//if they are on mapSelect and they click
		if(gameState==1 && mouseX > 70 && mouseX <420 && mouseY> 100 && mouseY<470){
			System.out.println("selected map1, sending over to game driver");
			mapSelected=1;
			MAP=new Map(this, mapSelected);
			game = new gameDriver(this,  MAP);
			gameState=2;
		}
		
		//if they click quit
		if(gameState==0 && mouseX >= 950 && mouseX <=950+130 && mouseY>=650 && mouseY<=700){
			System.out.println("Detected quit button, now closing window");
			System.exit(0);
		}
	}
	
}
