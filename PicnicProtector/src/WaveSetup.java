import java.util.ArrayList;

public class WaveSetup {
	
	private int wave;
	private ArrayList<Attacker> attackerList = new ArrayList<Attacker>();
	
	public WaveSetup(int wave){
		this.wave = wave;
	}
	
	public ArrayList<Attacker> setList(){
		
		//will hold wave attackerlist but for now only 1 attacker
		attackerList.add(new squirrel(140,60));
		
		
		return attackerList;
	}

}
