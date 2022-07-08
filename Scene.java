//declaring Scene class
public class Scene {

	//declaring class variables.
	//Booleans used in the Player class in condition statements are set to public so they can be referenced.
	private static final int TILE_SIZE = 32;
	private static int rows;
	private static int cols;
	private static boolean [][] walls;
	private static boolean [][] ivy;
	public static boolean [][] grain;
	public static boolean [][] poison;
	public static boolean [][] egg;
	public static boolean [][] chick;
	private static int width;
	private static int height;
	private static String floorImage;
	private static String wallImage;
	private static String ivyImage;
	private static String grainImage;
	private static String poisonImage;
	private static String chickImage;
	public static String eggImage;

	//start method assigning image files to all of the Scene assets, setting row and column lengths, and initializing boolean arrays for each asset.
	public static void start(int level) {
		floorImage = "Assets/tile-passage.png";
		wallImage = "Assets/tile-brickwall.png";
		ivyImage = "Assets/ivy.png";
		grainImage = "Assets/grain.png";
		poisonImage = "Assets/poison.png";
		eggImage = "Assets/egg.png";
		chickImage = "Assets/chick.png";
		String [][] map = World.getLevel(level);
		rows = map.length;
		cols = map[0].length;
		width = cols * TILE_SIZE;
		height = rows * TILE_SIZE;
		walls = new boolean [rows][cols];
		ivy = new boolean [rows][cols];
		grain = new boolean [rows][cols];
		poison = new boolean [rows][cols];
		egg = new boolean [rows][cols];
		chick = new boolean [rows][cols];

		//double for loops drawing the game.
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				String tile = map [y][x];
				setTile (x, y, tile);
			}
		}

		//setting game area size
		StdDraw.setCanvasSize (width, height);
		StdDraw.setXscale (0.0, width);
		StdDraw.setYscale (height, 0.0);
	}

	//confining player to the game area by not letting them move in the same direction as a wall if they are adjacent.
	public static boolean canMove(int x, int y) {
		return !walls[y][x];
	}

	//method comparing player position to egg position.
	public static boolean canGather(int x, int y) {
		return egg[y][x];
	}

	//method comparing grain position to player position.
	public static boolean canEat(int x, int y) {
		return grain[y][x];
	}

	//method comparing poison grain position to player position.
	public static boolean cannotEat(int x, int y) {
		return poison[y][x];
	}

	//method comparing chick position to player position.
	public static boolean canFry(int x, int y) {
		return chick[y][x];
	}

	//method setting map characters as asset tiles.
	public static void setTile(int x, int y, String tile) {
		if (tile.equals ("#")) {
			walls [y][x] = true;
		}
		else if (tile.equals ("#")) {
			ivy [y][x] = true;
		}
		else if (tile.equals ("g")) {
			grain [y][x] = true;
		}
		else if (tile.equals ("p")) {
			poison [y][x] = true;			
		}
		else if (tile.equals ("e")) {
			egg [y][x] = true;
		}
		else if (tile.equals ("c")) {
			chick [y][x] = true;
		}
		else if (tile.equals ("@")) {
			Player.start(x, y);
		}
		else if (tile.equals ("!")) {
			Exit.start(x, y);
		}
		else if (tile.equals ("b")) {
			Bunny.start(x, y);
		}
	}

	//method drawing asset tiles to the game area
	public static void draw() {

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				int tileX = x * TILE_SIZE + TILE_SIZE / 2;
				int tileY = y * TILE_SIZE + TILE_SIZE / 2;

				//layering the wall image and ivy image to give a hedge maze look
				if (walls[y][x] == true) {
					StdDraw.picture (tileX, tileY, wallImage);
					StdDraw.picture (tileX, tileY, ivyImage);
				}

				//layering asset images on top of the floor image to get rid of the white space around asset tiles.
				else if (grain[y][x] == true) {
					StdDraw.picture (tileX, tileY, floorImage);
					StdDraw.picture (tileX, tileY, grainImage);
				}
				else if (poison[y][x] == true) {
					StdDraw.picture (tileX, tileY, floorImage);
					StdDraw.picture (tileX, tileY, poisonImage);
				}
				else if (egg[y][x] == true) {
					StdDraw.picture (tileX, tileY, floorImage);
					StdDraw.picture (tileX, tileY, eggImage);
				}
				else if (chick[y][x] == true) {
					StdDraw.picture (tileX, tileY, floorImage);
					StdDraw.picture (tileX, tileY, chickImage);
				}
				else {
					StdDraw.picture (tileX, tileY, floorImage);
				}
			}	
		}
	}
}