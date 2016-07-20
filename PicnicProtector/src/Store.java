import java.awt.Color;

import processing.core.*;

public class Store extends Main{
	
	private PApplet p;

	private int menuX=1080, menuY=60, menuWidth=200, menuHeight=660;
	private int towerSelected=-1, overTower=-1;
	private boolean overStore=false;
	private int[] BoxYheight = {175, 200, 175};
	
	
	private Tower tower1, tower2, tower3;
	
	public Store(PApplet applet){
		
		p=applet;
		tower1= new Tower(menuX+50, menuY+30, 1);
		tower2= new Tower(menuX+50, menuY+130, 2);
		tower3= new Tower(menuX+50, menuY+230, 3);
		
		
	}
		
	public void main(){
		
		//draws the backgorund color for the cannons
		p.fill(Color.cyan.getRGB());
		p.rect(menuX, menuY, menuWidth, menuHeight);
		
		//not a tower / menu --reset check
		
		
		
		//if Over the menu, this if statement sets overTower to what ever tower the user is over
		if(p.mouseX>menuX && p.mouseX<menuX+190 && p.mouseY>menuY ){
			
			overStore=true;
	
			//over tower 1
			if(p.mouseY<menuY+BoxYheight[0])
				overTower=1;
			
			//over tower 2
			else if(p.mouseY<menuY+BoxYheight[0]+5+BoxYheight[1])
				overTower=2;
			
			//over tower 3
			else if(p.mouseY<menuY+BoxYheight[0]+BoxYheight[1]+BoxYheight[2])
				overTower=3;
			
			//over no tower
			else
				overTower=-1;
			
			
			
		}
		else{
			overTower=-1;
			overStore=false;
		}
		
		p.fill(Color.pink.getRGB());
		
		//this draws a pink rect around the tower that the mouse is over
		switch(overTower){
		case 1:
			p.rect(menuX+5, menuY+5, 190, BoxYheight[0]);
			break;
		
		case 2:
			p.rect(menuX+5,  menuY+5+BoxYheight[0],  190, BoxYheight[1]);
			break;
			
		case 3:
			p.rect(menuX+5, menuY+5+BoxYheight[0]+BoxYheight[1],  190, BoxYheight[2]);
			break;
			
		default:
			break;
		}
		
		
		//sets select tower background to green
		p.fill(Color.green.getRGB());
		switch(towerSelected){

		case 1:
			p.rect(menuX+5, menuY+5, 190, BoxYheight[0]);
			break;

		case 2:
			p.rect(menuX+5,  menuY+5+BoxYheight[0],  190, BoxYheight[1]);
			break;
			
		case 3:
			p.rect(menuX+5, menuY+5+BoxYheight[0]+BoxYheight[1],  190, BoxYheight[2]);
			break;
			
		default:
			break;

		}
			
			mouseClicked();
			
			//drawtowers
			drawTowers(p);
		
	}

	public boolean overStore(){
		return overStore;
	}
	
	public PImage getImageT1(){
		return images.cannon_tower;
	}
	
	public PImage getImageT2(){
		return images.dark_tower;
	}

	public PImage getImageT3(){
		return images.magic_tower;
	}

	
	public int towerSelected(){
		return towerSelected;
	}
	
	public void setTowerSelected(int newtowerSelected){
		towerSelected=newtowerSelected;
	}
	
	public void mouseClicked(){
		
		if(p.mousePressed &&( p.mouseX>menuX && p.mouseX<menuX+190 )){

			//tower1
			if(p.mouseY>menuY && p.mouseY<menuY+BoxYheight[0])
				towerSelected = 1;
			
			
			//tower2
			else if(p.mouseY>menuY+BoxYheight[0] && p.mouseY<menuY+BoxYheight[0]+BoxYheight[1])
				towerSelected = 2;
			
			
			//tower 3
			else if(p.mouseY>menuY+BoxYheight[0]+BoxYheight[1] && p.mouseY<menuY+BoxYheight[0]+BoxYheight[1]+BoxYheight[2])
				towerSelected = 3;
			
			 
			
			}
	}
	
	public void drawTowers(PApplet p){
		
		//backgorund
		p.fill(0);
		p.textSize(20);
		p.text("Cannon Tower",  menuX+40, menuY+30);
		p.textSize(12);
		p.text(" Dmg: 12 \n Rng: 20 \n $: 100", menuX+135, menuY+60);
		p.image(getImageT1(), menuX+85, menuY+100);
		
		
		p.textSize(20);
		p.text("Dark Tower", menuX+50, menuY+200);
		p.textSize(12);
		p.text(" Dmg: 20 \n Rng: 15 \n $: 150", menuX+135, menuY+260);
		p.image(getImageT2(), menuX+90, menuY+BoxYheight[0]+110);
		
		
		p.textSize(20);
		p.text("magic Tower", menuX+50, menuY+410);
		p.textSize(12);
		p.text(" Dmg: 10 \n Rng: 25 \n $: 200", menuX+135, menuY+460);
		p.image(getImageT3(), menuX+85, menuY+BoxYheight[0]+BoxYheight[1]+120);
	
	}
	
	public int getTowerRange(int towerSelected){
		return tower1.getRange();
	}
	
	public int getMenuX(){
		return menuX;
	}
	
	public int getMenuY(){
		return menuY;
	}

}
