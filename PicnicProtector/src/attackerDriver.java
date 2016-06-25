import java.util.ArrayList;

public class attackerDriver {
	
	private ArrayList<Attacker> attackerList;
	private Map MAP = new Map(1);
	
	//class driver
	public void main(ArrayList<Attacker> attackerList){
		
		this.attackerList=attackerList;
		
		attackerMovement();
	}
	
	private void attackerMovement() {
			
			for(int i=0; i<attackerList.size(); i++){
				
				
				attackerMove(attackerList.get(i));
				//System.out.println(attackerList.get(i).getMovingDirection());
			
			}
		}
	
		private void attackerMove(Attacker attacker) {
	
			int xPos = attacker.getX();
			int yPos = attacker.getY();
			
			
			//first turning point for map 1, NEED to find way to hold in map class, wont work with multiple maps
			if(xPos >= MAP.getTurningPoints()[0][0] && xPos <= MAP.getTurningPoints()[0][2] && yPos >= MAP.getTurningPoints()[0][1] && yPos <= MAP.getTurningPoints()[0][3]){
				attacker.setMovingDirection("right");
			}
			//second turning point for map 1
			else if(xPos >= MAP.getTurningPoints()[1][0] && xPos <= MAP.getTurningPoints()[1][2] && yPos >= MAP.getTurningPoints()[1][1] && yPos <= MAP.getTurningPoints()[1][3])
				attacker.setMovingDirection("down");
			
			
			//once the attacker knows what direction he is going in then the position can be calculated in the attacker class
			attacker.move();
			
			if(yPos>725)
				attacker.repos(140, -40);
			

	
		}

}
