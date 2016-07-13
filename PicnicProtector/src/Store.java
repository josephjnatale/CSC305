import java.awt.Color;

import processing.core.*;

public class Store extends Main{
	
	private PApplet p;

	private int menuX=1080, menuY=60, menuWidth=200, menuHeight=660;
	private int towerSelected=-1;
	private boolean overTower=false, overStore=false;
	
	
	private Tower tower1;
	
	public Store(PApplet applet){
		
		p=applet;
		tower1= new Tower(menuX+50, menuY+30);
		
	}
		
	public void main(){
		
		//draws the backgorund color for the cannons
		p.fill(Color.cyan.getRGB());
		p.rect(menuX, menuY, menuWidth, menuHeight);
		
		//not a tower / menu --reset check
		overTower=false;
		overStore=false;
		
		//overtower  menu
		if(p.mouseX>menuX && p.mouseX<menuX+190 && p.mouseY>menuY ){
			overStore=true;
			
			//over tower 1
			if(p.mouseY<menuY+110){
		
				overTower=true;
				
				p.fill(Color.pink.getRGB());
				
				//draw pink rect behind tower
				p.rect(menuX+5, menuY+5, 190, 105);
			
			}
		}
		
		//sets select tower background to green
		switch(towerSelected){
		
		case 1:

			p.fill(Color.green.getRGB());
			p.rect(menuX+5, menuY+5, 190, 105);
			break;
			
		default:
				break;
			
		}
		
		mouseClicked();
		
		//drawtowers
		drawTowers(p);
	}

	public boolean overTower(){
		return overTower;
	}
	public boolean overStore(){
		return overStore;
	}
	public PImage getImage(){
		return images.tower1;
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
		}
	}
	
	public void drawTowers(PApplet p){
		
		//backgorund
		p.fill(0);
		p.textSize(20);
		p.text("Tower 1",  menuX+50, menuY+30);
		p.textSize(12);
		p.text(" Dmg: 12 \n Rng: 20 \n $: 100", menuX+135, menuY+60);
		p.image(getImage(), menuX+75, menuY+75, 100, 100);
		
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
