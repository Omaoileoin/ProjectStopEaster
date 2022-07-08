//importing scanner to keep track of world levels
import java.util.Scanner;

//declaring World class
public class World {

	//putting levels into arrays.
	private static String [][][] levels;

	//start method to start level 0 and move to levels 1 and 2.
	//reading row and column sizes for each level.
	public static void start() {
		Scanner input = new Scanner(System.in);
		int count = input.nextInt();
		
		levels = new String [count][][];

		for (int lvl = 0; lvl < count; lvl++) {
			int rows = input.nextInt();
			int cols = input.nextInt();
			setLevel (lvl, rows, cols, input);
		}
	}

	//setLevel method to set each level's col and row size.
	public static void setLevel(int lvl, int rows, int cols, Scanner input) {
		levels  [lvl] = new String [rows][cols];
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				String tile = input.next();
				levels [lvl][y][x] = tile;
			}
		}
	}

	//get method so the levels can be referenced in the Game class while loop.
	public static String [][] getLevel(int level) {
		return levels [level];
	}

	public static int getLength() {
		return levels.length;
	}
}