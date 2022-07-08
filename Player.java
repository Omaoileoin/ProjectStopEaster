//importing to change the font type, size, and color for the end-game screens
import java.awt.Font;

//declaring the Player class
public class Player {

	//declaring the class variables. isBuff and TILE_SIZE are public so they can be referenced in the Scene class.
	private static int x;
	private static int y;
	private static String image1;
	private static String image2;
	private static String bunnyImage;
	private static String chickImage;
	private static String poisonImage;
	private static int bunnyWidth = 600;
	private static int bunnyHeight = 632;
	private static int chickWidth = 600;
	private static int chickHeight = 635;
	private static int poisonWidth = 600;
	private static int poisonHeight = 600;
	public static boolean isBuff = false;
	public static final int TILE_SIZE = 32;

	//Setting the Player's position to the game area and assigning image files for the player, buff player, and three different end-screens.
	public static void start(int x, int y) {
		Player.x = x;
		Player.y = y;
		image1 = "Assets/chicken.png";
		image2 = "Assets/chicken-buff.png";
		bunnyImage = "Assets/bunny-end-screen.png";
		chickImage = "Assets/chick-end-screen.png";
		poisonImage = "Assets/poison-end-screen.png";
	}

	//drawing Player to the game area.
	public static void draw() {
		int tileX = x * TILE_SIZE + TILE_SIZE / 2;
		int tileY = y * TILE_SIZE + TILE_SIZE / 2;
		StdDraw.picture (tileX, tileY, image1);
	}

	//Player movement is handled by the WASD keys, and moves the Player one space in the direction selected.
	public static void update() {
		if (StdDraw.hasNextKeyTyped() == true) {
			char key = StdDraw.nextKeyTyped();
			if (key == 'w' && Scene.canMove(x, y - 1)) {
				y--;
			}
			else if (key == 's' && Scene.canMove(x, y + 1)) {
				y++;
			}
			else if (key == 'a' && Scene.canMove(x - 1, y)) {
				x--;
			}
			else if (key == 'd' && Scene.canMove(x + 1, y)) {
				x++;
			}
		}

		//changing the Player's image file when he gets the buff.
		if (isBuff == true) {
			image1 = image2;
		}	

		//method to change the egg boolean to false so they are removed from the game area when collected.
		if (Scene.canGather(x, y)) {
			Scene.egg[y][x] = false;
		}

		//method to change the grain boolean to false so they are removed from the game area when collected.
		//also changes the isBuff boolean to true and redraws the game area.
		if (Scene.canEat(x, y)) {
			Scene.grain[y][x] = false;
			isBuff = true;
			Scene.draw();
			Bunny.draw();
			Exit.draw();
		}

		//method to change the poison grain boolean to false so they are removed from the game area when collected.
		//also changes the isBuff boolean to false if it is currently true, and sets the Player image back to the original chicken image - if you're a buff chicken,
		//you lose your buff.
		//redraws the game area.
		if (Scene.cannotEat(x, y) && isBuff == true) {
			Scene.poison[y][x] = false;
			isBuff = false;
			image1 = "Assets/chicken.png";
			Scene.draw();
			Bunny.draw();
			Exit.draw();
		}

		//method to change the gameOver boolean to true if the poison grain is collected while isBuff is false - if you're a normal chicken, you die.
		//the game area is replaced by an end-screen telling you that you died from being poisoned.
		if (Scene.cannotEat(x, y) && isBuff == false) {
			Scene.poison[y][x] = false;
			Game.gameOver = true;
			StdDraw.setCanvasSize(poisonWidth, poisonHeight); 
			StdDraw.setXscale(0.0, poisonWidth);
			StdDraw.setYscale(poisonHeight, 0.0);
			StdDraw.picture(poisonWidth / 2, poisonHeight / 2, poisonImage);
			Font font = new Font("News Gothic", Font.BOLD, 30);
			StdDraw.setFont(font);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(300, 510, "You ate poison grain!");
			StdDraw.text(300, 540, "You died and all your eggs were stolen!");
			StdDraw.show(100);		
		}

		//method to change the chick boolean to false so they are removed from the game area. You can only do this if isBuff is true.
		//you lose your buff and the image is changed back to the original normal chicken. The game area is redrawn.
		if (Scene.canFry(x, y) && isBuff == true) {
			Scene.chick[y][x] = false;
			isBuff = false;
			image1 = "Assets/chicken.png";
			Scene.draw();
			Bunny.draw();
			Exit.draw();
		}

		//method to change the gameOver boolean to true if you try to defeat a chick while isBuff is false. You cannot defeat the chick without being a "buff chicken".
		//the game area is replaced by an end-screen telling you that you were captured by the chick.
		if (Scene.canFry(x, y) && isBuff == false) {
			Game.gameOver = true;
			StdDraw.setCanvasSize(chickWidth, chickHeight); 
			StdDraw.setXscale(0.0, chickWidth);
			StdDraw.setYscale(chickHeight, 0.0);
			StdDraw.picture(chickWidth / 2, chickHeight / 2, chickImage);
			Font font = new Font("News Gothic", Font.BOLD, 30);
			StdDraw.setFont(font);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(300, 575, "The evil chick minion captured you!");
			StdDraw.text(300, 605, "All your eggs were stolen!");
			StdDraw.show(100);		
		}

		//method to change the gameOver boolean to true if you try to get past the Easter Bunny while isBuff is false.
		//you cannot get past the Easter Bunny to get to the next level without being a "buff chicken".
		//the game area is replaced by an end-screen telling you that you were captured by the Easter Bunny.
		if (x == Bunny.getX() && y == Bunny.getY() && isBuff == false) {
			Game.gameOver = true;
			StdDraw.setCanvasSize(bunnyWidth, bunnyHeight); 
			StdDraw.setXscale(0.0, bunnyWidth);
			StdDraw.setYscale(bunnyHeight, 0.0);
			StdDraw.picture(bunnyWidth / 2, bunnyHeight / 2, bunnyImage);
			Font font = new Font("News Gothic", Font.BOLD, 30);
			StdDraw.setFont(font);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(300, 570, "The Easter Bunny captured you!");
			StdDraw.text(300, 600, "All your eggs were stolen and dyed!");
			StdDraw.show(100);		
		}
	}

	//get methods so the Scene class can pull the Player's position after each move.
	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}
}