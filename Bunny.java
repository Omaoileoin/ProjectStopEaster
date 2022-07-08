//declaring Bunny class
public class Bunny {
	
	//declaring class variables. TILE_SIZE is public so it can be referenced in the Scene class.
	private static int x;
	private static int y;
	private static String image;
	public static final int TILE_SIZE = 32;

	//setting the Easter Bunny's position to the game area and assigning an image file.
	public static void start(int x, int y) {
		Bunny.x = x;
		Bunny.y = y;
		image = "Assets/bunny.png";
	}

	//drawing the Easter Bunny to the game area.
	public static void draw() {
		int tileX = x * TILE_SIZE + TILE_SIZE / 2;
		int tileY = y * TILE_SIZE + TILE_SIZE / 2;
		StdDraw.picture(tileX, tileY, image);
	}

	//get methods so the Scene class can pull the Easter Bunny's position.
	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}
}