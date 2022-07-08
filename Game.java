//importing to change the font type, size, and color for the end-screens.
import java.awt.Font;

//declaring the Game class
public class Game {
	
	//declaring class variables. gameOver boolean is set to public so it can be used in different conditions in the Player class.
	private static int level;
	private static String image;
	private static int width = 600;
	private static int height = 580;
	public static boolean gameOver;

	//starting the game loop that will continue to run the game while gameOver is false.
	public static void main (String [] args) {
		start();
		while (gameOver == false) {
			update();
			
			//putting render in an if statement so the end-screens stay and don't just flash and go away.
			if (gameOver == false) {
				render(); 
			}
		}
	}

	//setting the level to 0 and starting the World and Scene start methods.
	public static void start() {
		gameOver = false;
		level = 0;
		World.start();
		Scene.start(level);
	}

	//if the player gets to the exit, either start a new level or end the game if it is the last level.
	//the game area is replaced by an end-screen.
	public static void update() {
		Player.update();
		if (Player.getX() == Exit.getX() && Player.getY() == Exit.getY() //&& Scene.egg[y][x] == false
			) {
			level++;
			if (level == World.getLength() //&& Scene.egg[y][x] == false
				) {
				gameOver = true;
				image = "Assets/end-screen.png";
				StdDraw.setCanvasSize(width, height);
				StdDraw.setXscale(0.0, width);
				StdDraw.setYscale(height, 0.0);
				StdDraw.picture(width / 2, height / 2, image);
				Font font = new Font("News Gothic", Font.BOLD, 30);
				StdDraw.setFont(font);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.text(300, 560, "Hooray! You escape with all your eggs!");
				StdDraw.show(100);
			}
			else {
				Scene.start(level);
			}
		}
	}

	//render method to start all of the other class draw methods and also set the text information for end-screens.
	public static void render() {
		Scene.draw();
		Exit.draw();
		Player.draw();
		Bunny.draw();
		StdDraw.show(100);
		Font font = new Font("News Gothic", Font.BOLD, 30);
		StdDraw.setFont(font);
		StdDraw.setPenColor(StdDraw.BLACK);
	}
}