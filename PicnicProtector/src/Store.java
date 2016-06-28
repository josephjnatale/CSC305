import java.awt.Color;

import processing.core.*;

public class Store {
	
	private PApplet p;
	private PImage tower;
	private int menuX=1080, menuY=60;
	
	public Store(PApplet applet){
		
		p=applet;
		tower = p.loadImage("images\\tower.png");
	}
	
	public void draw(){
		p.fill(Color.cyan.getRGB());
		p.rect(menuX, menuY, 200, 700);
		p.image(tower, menuX+75, menuY+75, 100, 100);
		
	}

}
