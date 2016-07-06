import processing.core.*;

public class Map extends Main{
	
	private PImage mapImage;
	//top left and bottom right corner of turning point
	private int[][] turningPoints={{140 ,355 ,200, 400},{765, 345, 800, 400}};
	
	public Map(PApplet applet, int map){
		
		switch(map){
		case 1:
			mapImage= images.map1;
			break;
		}
	}
	
	
	
	public int[][] getTurningPoints(){
		return turningPoints;
	}
	
	public void drawMap(PApplet applet){
		
		//System.out.println("Drawing map");
		applet.image(mapImage, mapImage.width/2, mapImage.height/2+60);
	}
	
	public void distanceToPath(int x, int y){
		
		
	}
	
	

}
