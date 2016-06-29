import processing.core.*;

public class Map {
	
	private int map;
	private PApplet p;
	private PImage mapImage;
	//top left and bottom right corner of turning point
	private int[][] turningPoints={{140 ,355 ,200, 400},{765, 345, 800, 400}};
	
	public Map(PApplet applet, int map){
		p=applet;
		this.map=map;
		switch(map){
		case 1:
			mapImage= p.loadImage("images\\map1.png");
			break;
		}
	}
	
	
	
	public int[][] getTurningPoints(){
		return turningPoints;
	}
	
	public void drawMap(PApplet applet){
		applet.fill(255);
		applet.rect(0, 0, 1280, 720);
		applet.image(mapImage, mapImage.width/2, mapImage.height/2+60);
	}
	
	public void distanceToPath(int x, int y){
		
		
	}
	
	

}
