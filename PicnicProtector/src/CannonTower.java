import java.awt.Color;

import processing.core.PApplet;

public class CannonTower extends Tower {

	public CannonTower(int x, int y) {
		super(x, y);
		range = 160;
		damage = 12;
		fireRate = 300;
		price = 100;
	}
	
	public void draw(PApplet p){
		
		p.image(images.cannon_tower, x, y);
		if(target!=null){
			if(shooting){
				p.strokeWeight(5);
				p.stroke(Color.blue.getRGB());
				p.line(x, y, target.getX(), target.getY());
				lastShot=p.millis();
				
			}
			else if(p.millis()-lastShot>fireRate && p.millis()-lastShot%20!=0){
				p.strokeWeight(5);
				p.stroke(Color.blue.getRGB());
				p.line(x, y, target.getX(), target.getY());
			}
		}
	}

}
