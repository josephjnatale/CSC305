import java.util.ArrayList;

import processing.core.*;

public class Tower extends towerDriver{
	/**fireRate is currently  unused because gameDriver is the one 
	 * that delays the hit detection for now needs to be changed if 
	 * we want multiple tower fire rates
	 */
	private int x, y, range = 160, damage = 12, fireRate = 300;

	private Attacker target = null;

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

	public void draw(PApplet p){


		p.image(images.tower1, x, y,  100, 100);
	}

	public int distanceToPoint(int xPos, int yPos){
		return (int) Math.sqrt(Math.pow((double)yPos-y, 2.0)+Math.pow((double)xPos-x, 2.0));
	}
	public void shoot(Attacker attacker){
		attacker.setHealth(attacker.getHealth() - damage);
		System.out.println("Shoots " + attacker.getHealth());
	}


	public void hitDetection(ArrayList<Attacker> attackerList){
		
		int rangeCheck;
		try{
			rangeCheck = distanceToPoint(target.getX(),target.getY());
			
			if (rangeCheck <= range){
				shoot(target);
				//Failed attempts at slowing fire rate
				//Thread.sleep(500);
				//delay(300);
				
				
			}else{
				target = null;
			}
			if(target.getHealth() <= 0){
				target.isDead = true;
				target = null;
			}
			//this will be the search behaviour so that the tower only switches targets when it no longer has a target to shoot
		}catch(NullPointerException e){
			System.out.println("Switch targets");
			for (int index = 0; index < attackerList.size(); index++)
				if(!attackerList.get(index).isDead){
					if(distanceToPoint(attackerList.get(index).getX(), attackerList.get(index).getY()) <= range)
						target = attackerList.get(index);
				}
		}/* Exception unneeded as we are no longer using Thread.sleep
			catch(InterruptedException e){
			System.out.println("Ya don goofed it m8");
		}*/
	}

}
