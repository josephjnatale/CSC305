import java.awt.Color;

import processing.core.*;

public class Store {
	
	private PApplet p;

	private int menuX=1080, menuY=60, menuWidth=200, menuHeight=660;
	private int towerSelected=-1;
	private boolean overMenu=false;
	
	private PImage tower1;
	
	public Store(PApplet applet){
		
		p=applet;
		tower1= p.loadImage("images\\tower.png");
		
	}
		
	public void main(){
		
		//draws the backgorund color for the cannons
		p.fill(Color.cyan.getRGB());
		p.rect(menuX, menuY, menuWidth, menuHeight);
		
		//not over menu
		overMenu=false;
		
		if(p.mouseX>menuX && p.mouseX<menuX+190 && p.mouseY>menuY && p.mouseY<menuY+110){
			
			//is over menu
			overMenu=true;
			
			p.fill(Color.pink.getRGB());
			
			//tower1
			p.rect(menuX+5, menuY+5, 190, 105);
			
		}
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

	public boolean overMenu(){
		return overMenu;
	}
	public PImage getImage(){
		return tower1;
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
		p.image(tower1, menuX+75, menuY+75, 100, 100);
		
	}
	
	public int getMenuX(){
		return menuX;
	}
	
	public int getMenuY(){
		return menuY;
	}

}
