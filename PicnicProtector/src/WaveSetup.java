import java.util.ArrayList;

public class WaveSetup {
	
	private int wave;
	private ArrayList<Attacker> attackerList = new ArrayList<Attacker>();
	
	//for now statically adding the attackers per wave, [wave][# of squirrels, etc, ...]
	private int[][] eachWave = {{5}};
	
	public WaveSetup(int wave){
		this.wave = wave;
	}
	
	public ArrayList<Attacker> setList(){
		
		//will hold wave attackerlist but for now only 1 attacker
		addAttacker();
		
		
		return attackerList;
	}
	
	//takes the wave and adds the corresponding amount of attackers to the attackerList
	private void addAttacker(){
		
		//add squirrels
		for(int i=0; i<=eachWave[wave-1][0]; i++){
			
			attackerList.add(new squirrel(140, -(i*60)));
			
		}
		
		
	}

}
