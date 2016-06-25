
public class Map {
	
	private int map;
	
	//top left and bottom right corner of turning point
	private int[][] turningPoints={{140 ,345 ,200, 400},{745, 345, 800, 400}};
	
	public Map(int map){
		this.map=map;
	}
	
	public int[][] getTurningPoints(){
		return turningPoints;
	}
	
	

}
