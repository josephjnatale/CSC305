import processing.core.PApplet;
import processing.core.PImage;
import java.*;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Logger;

public class Main extends PApplet {
	
	//Initiates Map and gamedriver
	private Map MAP;
	private gameDriver game;
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	protected static allMyImages images = new allMyImages();
	
	private int basketx=-250, squirrelx=-550;
	
	
	private int gameState = 0, mapSelected =-1;
	
	//is the value of green at the current mouse position
	protected static int greenAtMouse;
	
	//can be a text file holding the scores but for now just a int
	private int endScore, endWave;
	
	
	
	public void setup(){
		
		//Log files setup
		try {
		      LoggerMain.setup();
		    } catch (IOException e) {
		      e.printStackTrace();
		      throw new RuntimeException("Problems with creating the log files");
		    }
		//log files setup end
	
		size(images.menuBackground.width, images.menuBackground.height);
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
			//checks to see if player has lost every 20 frame
			if(game.noHealthCheck() && frameCount%20==0){
				//gets final score and wave
				endScore=game.getScore();
				endWave=game.getWave();
				
				
				//sests game state to end game screen;
				gameState=4;
				
			}
			break;	
			
		case 3:
			instructions();
			break;
			
		case 4:
			gameOver();
			break;
			
		}
		
		
		//checks to see if the player has lost
		
		
		//gets green color at mouse every 5 frames
		if(frameCount%10==0)
			greenAtMouse= (int) green(images.map1.get(mouseX, mouseY-60));
		//System.out.println("Green: "+green);
		
		
		//draws framerate over any screen.
//		this.textSize(45);
//		fill(Color.cyan.getRGB());
//		text(frameRate, 50, 150);	
		
	}
	
	private void gameOver(){
		background(100);
		textSize(70);
		fill(255);
		text("Game Over", 450, 70);
		text("Final Score: "+endScore+"\n\nFinal Wave: "+endWave, 100, 300);
		
		fill(100, 200, 105);
		
		if(mouseX>680 && mouseX<1200 && mouseY>520 && mouseY<630)
			fill(Color.orange.getRGB());
		
		rect(680, 520, 420, 110);
		fill(255);
		text("Play Again?", 700, 600);
		
	}
	
	private void instructions(){
		
		background(100);
		textSize(60);
		fill(255);
		text(" Picnic Protector", 200, 100);
		textSize(20);

		text (" You are given a health, score, bank and wave number to start with.", 50, 200);
		
		//build phase
			//access the store and buy towers
			// click on tower and place on the map anywhere besides the path
			// click start wave when done
		//attk phase
			//gain income for each successful kill
			// lose health if attker reaches end
		// when health goes to 0 you lose
		// good luck!
		
		
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
		
		image(images.map1, 250, 200, images.map1.width/4, images.map1.height/4);
		textSize(35);
		fill(0);
		text("Map 1", 200, 400);
		
		//back button highlight
		textSize(60);
		//If mouse is over back, change the fill purple 
		if(mouseX >= 50 && mouseX <=50+125 && mouseY>=650 && mouseY<=700 && gameState == 1){
			fill(186,85,211);
		text("Back", 50, 700);
	    LOGGER.info("Back button Pressed");
		}

	}
	
	private void mainMenu(){
		//sets background image
		image(images.menuBackground,images.menuBackground.width/2, images.menuBackground.height/2);
				
		//basket is the basket's x position, bx is the distance needed to hide the image off the left side of the screen. 
		//squirrel's x needs to also clear the baskets x so sx is larger than bx.
		//% makes it loop back to the beginning.
		basketx+=3;
		squirrelx+=3;
		
		if(basketx>1800)
			basketx=-250;
		
		if(squirrelx>1800)
			squirrelx=-250;
		
		//draw the images at the specified positions, make the image a sixth and a fourth the original size.
		//System.out.println("Drawing moving things");
		images.squirrelRight.play();
		image(images.squirrelRight, squirrelx, 270, width/6, height/6);
		image(images.basket, basketx, 230, width/4, height/4);
		

		
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
			LOGGER.info("Detected click on Play, sending over to gameDriver");
			//Changes the game state from start menu to running
			gameState = 1;
			
		}		
		
		//if on menu n click instructions
		if(gameState==0 && mouseX >= 100 && mouseX <=450 && mouseY>=650 && mouseY<=700){
			LOGGER.info("Detected click on Instructions, sending over to gameDriver");
			//Changes the game state from start menu to instructions
			gameState = 3 ;
		}
		
		//if user clicks on back change game state while on map select or instructions
		if((gameState == 3||gameState == 1)&& mouseX >= 50 && mouseX <=50+125 && mouseY>=650 && mouseY<=700 )
			gameState = 0;
		
		
		//if they are on mapSelect and they click
		if(gameState==1 && mouseX > 70 && mouseX <420 && mouseY> 100 && mouseY<470){
			LOGGER.info("selected map1, sending over to game driver");
			mapSelected=1;
			MAP=new Map(this, mapSelected);
			game = new gameDriver(this,  MAP);
			gameState=2;
		}
		
		//if they click quit while on the main menu
		if(gameState==0 &&  mouseX >= 950 && mouseX <=950+130 && mouseY>=650 && mouseY<=700 ){
			LOGGER.info("Detected quit button, now closing window");
			System.exit(0);
		}
		
		//if they click on play agagin button while at the game overscrren
		if(gameState==4 && mouseX>680 && mouseX<1200 && mouseY>520 && mouseY<630){
			//send them to the select menu screen
			gameState=1;
		}
		
		//displays rgb color values when click, no matter the screen
//		println("Red: "+red(get().pixels[mouseX + mouseY * width]));
//        println("Green: "+green(get().pixels[mouseX + mouseY * width]));
//        println("Blue: "+blue(get().pixels[mouseX + mouseY * width]));
//		println();
	}	
}
