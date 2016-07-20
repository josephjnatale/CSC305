import java.awt.Color;
import java.util.ArrayList;

import processing.core.*;

public class Tower extends towerDriver{
	/**fireRate is currently  unused because gameDriver is the one 
	 * that delays the hit detection for now needs to be changed if 
	 * we want multiple tower fire rates
	 */
	protected int x;

	protected int y;

	protected int range;

	protected int damage;

	protected int fireRate;

	protected int price;

	protected Attacker target = null;
	
	protected float lastShot;
	
	protected boolean shooting=false;
			

	public Tower( int x, int y){
		this.x = x;
		this.y = y;
	}

	public void repos(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
	
	public int getRange(){
		return range;
	}
	
	protected void setRange(int rangeSet){
		range=rangeSet;
	}
	
	

	public  void draw(PApplet p){}
	
	

		
		/*
			p.image(images.dark_tower, x, y);
			break;
		case 3: 
			p.image(images.magic_tower, x, y);
			break;
		default:
			break;
		}
		
		if(target!=null){
			
			/* Code for drawing the laser gif at angle to attacker, does not work
			if(shooting){
				p.pushMatrix();
				p.translate(x, y);
				p.rotate(PApplet.radians(angleOfAttacker()));
				images.laser.play();
				p.image(images.laser, 0, 0, 100, distanceToPoint(target.getX(), target.getY()));
				lastShot=p.millis();
				System.out.println("Last shot at "+lastShot);
				p.popMatrix();
			}
			
			else if(p.millis()-lastShot>1000){
				System.out.println("Time since lastShot: " + (p.millis()-lastShot));
				p.pushMatrix();
				p.translate(x, y);
				p.rotate(PApplet.radians(angleOfAttacker()));
				System.out.println("Angle of attacker in radians: "+ angleOfAttacker());
				images.laser.play();
				p.image(images.laser, 0, 0, 100, distanceToPoint(target.getX(), target.getY()));
				p.popMatrix();
			}
			
			//draws line from tower to attacker
			if(shooting){
				p.stroke(Color.blue.getRGB());
				p.line(x, y, target.getX(), target.getY());
				lastShot=p.millis();
				
			}
			else if(p.millis()-lastShot>fireRate && p.millis()-lastShot%20!=0){
				p.stroke(Color.blue.getRGB());
				p.line(x, y, target.getX(), target.getY());
			}
			
		}
	}
	*/
	private float angleOfAttacker(){
		
		PVector v1 = new PVector(x, y);
		PVector v2 = new PVector(target.getX(), target.getY()); 
		return PVector.angleBetween(v1, v2);
		
	}

	public int distanceToPoint(int xPos, int yPos){
		return (int) Math.sqrt(Math.pow((double)yPos-y, 2.0)+Math.pow((double)xPos-x, 2.0));
	}
	public void shoot(Attacker attacker){
		attacker.setHealth(attacker.getHealth() - damage);
		System.out.println("Shoots " + attacker.getHealth());
		System.out.println("drawing laser");
		
	}


	public void hitDetection(ArrayList<Attacker> attackerList){
		
		int rangeCheck;
		try{
			rangeCheck = distanceToPoint(target.getX(),target.getY());
			
			if (rangeCheck <= getRange()){
				shoot(target);
				
				// for shoot animation timing
				shooting = true;
				
				
				
			}else{
				target = null;
				
				//shoot animation non existent otherwise
				shooting = false;
				
			}
			if(target.getHealth() <= 0){
				target.isDead = true;
				target = null;
			}
			//this will be the search behaviour so that the tower only switches targets when it no longer has a target to shoot
		}catch(NullPointerException e){
			System.out.println("Tower Looking for Target");
			for (int index = 0; index < attackerList.size(); index++)
				if(!attackerList.get(index).isDead){
					if(distanceToPoint(attackerList.get(index).getX(), attackerList.get(index).getY()) <= getRange())
						target = attackerList.get(index);
				}
		}/* Exception unneeded as we are no longer using Thread.sleep
			catch(InterruptedException e){
			System.out.println("Ya don goofed it m8");
		}*/
	}

}
