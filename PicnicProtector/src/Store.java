import java.awt.Color;

import processing.core.*;

public class Store extends Main{
	
	private PApplet p;

	private int menuX=1080, menuY=60, menuWidth=200, menuHeight=660;
	private int towerSelected=-1;
	private boolean overTower=false, overStore=false;
	
	
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
		overTower=false;
		overStore=false;
		
		//overtower menu
		if(p.mouseX>menuX && p.mouseX<menuX+190 && p.mouseY>menuY ){
			overStore=true;
	
			//over tower 1
			if(p.mouseY<menuY+110){
	
			overTower=true;
	
			p.fill(Color.pink.getRGB());
	
			//draw pink rect behind tower
			p.rect(menuX+5, menuY+5, 190, 105);

		}
		// over tower 2
		else if(p.mouseY<menuY+210){

			overTower=true;
	
			p.fill(Color.pink.getRGB());
	
			//draw pink rect behind tower
			p.rect(menuX+5, menuY+105, 190, 105);

		}

		// over tower 3
		else if(p.mouseY<menuY+310){

			overTower=true;
	
			p.fill(Color.pink.getRGB());
	
			//draw pink rect behind tower
			p.rect(menuX+5, menuY+205, 190, 105);

		}

		// over tower 4
		else if(p.mouseY<menuY+410){

			overTower=true;
	
			p.fill(Color.pink.getRGB());
	
			//draw pink rect behind tower
			p.rect(menuX+5, menuY+305, 190, 105);

		}
		
		
		
		//sets select tower background to green
		switch(towerSelected){

		case 1:

			p.fill(Color.green.getRGB());
			p.rect(menuX+5, menuY+5, 190, 105);
			break;

		case 2:
			p.fill(Color.green.getRGB());
			p.rect(menuX+5, menuY+105, 190, 105);
			break;

		case 3:
			p.fill(Color.green.getRGB());
			p.rect(menuX+5, menuY+205, 190, 105);
			break;

		case 4:
			p.fill(Color.green.getRGB());
			p.rect(menuX+5, menuY+305, 190, 105);
			break;

		default:
			break;

		}
			
			mouseClicked();
			
			//drawtowers
			drawTowers(p);
		}
	}

	public boolean overTower(){
		return overTower;
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
		
		if(p.mousePressed){

			//tower1
			if(p.mouseX>menuX && p.mouseX<menuX+190 && p.mouseY>menuY && p.mouseY<menuY+110){

				towerSelected = 1;
			}
			//tower2
			else if(p.mouseX>menuX && p.mouseX<menuX+190 && p.mouseY>menuY+100 && p.mouseY<menuY+210){

				towerSelected = 2;
			}
			//tower 3
			else if(p.mouseX>menuX && p.mouseX<menuX+190 && p.mouseY>menuY+200 && p.mouseY<menuY+310){

				towerSelected = 3;
			}

			//tower 4
			else if(p.mouseX>menuX && p.mouseX<menuX+190 && p.mouseY>menuY+300 && p.mouseY<menuY+410){

				towerSelected = 4;
			}
			}
	}
	
	public void drawTowers(PApplet p){
		
		//backgorund
		p.fill(0);
		p.textSize(20);
		p.text("Cannon Tower",  menuX+40, menuY+30);
		p.textSize(12);
		p.text(" Dmg: 12 \n Rng: 20 \n $: 100", menuX+135, menuY+60);
		p.image(getImageT1(), menuX+75, menuY+85, 100, 100);
		
		p.textSize(20);
		p.text("Dark Tower", menuX+50, menuY+170);
		p.textSize(12);
		p.text(" Dmg: 20 \n Rng: 15 \n $: 150", menuX+135, menuY+200);
		p.image(getImageT2(), menuX+75, menuY+175, 100, 100);

		p.textSize(20);
		p.text("magic Tower", menuX+50, menuY+270);
		p.textSize(12);
		p.text(" Dmg: 10 \n Rng: 25 \n $: 200", menuX+135, menuY+320);
		p.image(getImageT3(), menuX+75, menuY+275, 100, 100);

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
