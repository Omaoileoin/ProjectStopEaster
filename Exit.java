//declaring Exit class
public class Exit {
	
	//declaring class variables. TILE_SIZE is public so it can be referenced by the Scene class.
	public static final int TILE_SIZE = 32;
	private static int x;
	private static int y;
	private static String image;

	//setting the Exit location and assigning it an image. It is the same as the floor tile and there is a 
	//"gap" in the maze for a cleaner game look.
	public static void start(int x, int y) {
		Exit.x = x;
		Exit.y = y;
		image = "Assets/tile-passage.png";
	}

	//drawing the Exit location to the game area.
	public static void draw() {
		int tileX = x * TILE_SIZE + TILE_SIZE / 2;
		int tileY = y * TILE_SIZE + TILE_SIZE / 2;
		StdDraw.picture(tileX, tileY, image);
	}

	//get methods so the Scene class can pull the Exit location.
	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}
}