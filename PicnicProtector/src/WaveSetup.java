import java.util.ArrayList;

public class WaveSetup {
	
	private int wave;
	private ArrayList<Attacker> attackerList = new ArrayList<Attacker>();
	
	//for now statically adding the attackers per wave, [wave][# of squirrels, etc, ...]
	private int[][] eachWave = {{5},{8},{15},{30},{55}};
	
	
	public WaveSetup(int wave){
		this.wave = wave;
	}
	
	public ArrayList<Attacker> setList(){
		
		
		addAttacker();
		
		
		return attackerList;
	}
	
	//takes the wave and adds the corresponding amount of attackers to the attackerList
	private void addAttacker(){
		
		//add squirrels
		for(int i=0; i<eachWave[wave-1][0]; i++){
			
			attackerList.add(new squirrel(170, -(i*115)));
			
		}
		
	}
	public int getWave(){
		return wave;
	}
	public int getSquirrelCount(int wave){
		return eachWave[wave-1][0];
		
	}
	
	public void setWave(int wave){
		this.wave=wave;
	}

}
