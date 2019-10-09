import java.util.Scanner;

public class testMaze {

	private static int[][] mazeArray;
	static int rowSize = 9;
	static int colSize = 9;
	static int mouseRow = 0, mouseCol = 0;
	static int catRow = 0, catCol = 0;
	static int failOut = 0, caught = 0, backpaddle = 0;
	static int stepCounter = 1;


	public static void main(String[] args) {
		//read row and col size of the maze
		Scanner input = new Scanner(System.in);
		System.out.println("row size?");
		rowSize = input.nextInt();
		System.out.println("col size?");
		colSize = input.nextInt();
		input.close();

		//check the maze to see if the size is correct and set up the maze
		checkMaze();
		mazeArray = new int[rowSize][colSize];
		fillArray();
		
		//run
		while((mouseRow != rowSize - 1) || (mouseCol != colSize - 1)) {
			mouseMove();
			catMove();
			checkCatch();
			checkFinish();
			stepCounter = stepCounter + 1;
		}
		int total = failOut + caught + backpaddle;
		
		//print
		printArray();
		System.out.println("mouse restarted : " + total + " times.");
		System.out.println("mouse went back : " +  backpaddle + " times.");
		System.out.println("mouse fell off : " + failOut + " times.");
		System.out.println("mouse got caught : " + caught + " times.");
		
	}
	private static void checkMaze() {
		//make sure maze is not too small
		if(rowSize < 3 || colSize < 3) {
			System.out.println("maze size is too small.");
			System.exit(0);
		}
	}
	private static void checkCatch() {
		//check if mouse caught the mouse
		if( mouseRow == catRow && mouseCol == catCol ||
			mouseRow == catRow + 1 && mouseCol == catCol ||
			mouseRow == catRow + 1 && mouseCol == catCol + 1 ||
			mouseRow == catRow && mouseCol == catCol + 1) {
			caughtByCat();
			
		}
	}
	
	private static void checkFinish() {
		//check when cats location when mouse finished the maze
		if (mouseRow == rowSize - 1 && mouseCol == colSize - 1) {
			mazeArray[catRow][catCol] = -1;
			mazeArray[catRow+1][catCol] = -1;
			mazeArray[catRow+1][catCol+1] = -1;
			mazeArray[catRow][catCol+1] = -1;
		}
	}
	private static void catMove() {
		//movement of the cat
		catRow = (int) (Math.random() * rowSize - 2) + 1;
		catCol = (int) (Math.random() * colSize - 2) + 1;		
				
	}

	private static void mouseMove() {
		//movement of the mouse
		int rand = (int) (Math.random() * 8) + 1;
		mazeArray[0][0] = 1;
		switch (rand) {
		case 1:
			if (mouseCol == colSize - 1 || mouseRow == 0) {
				failedOut();
			} else {
				mouseRow = mouseRow - 1;
				mouseCol = mouseCol + 1;
				mouseFillZero();
			}

			break;
		case 2:
			if (mouseCol == colSize - 1) {
				failedOut();
			} else {
				mouseCol = mouseCol + 1;
				mouseFillZero();
			}
			break;
		case 3:
			if (mouseRow == rowSize - 1 || mouseCol == colSize - 1) {
				failedOut();
			} else {
				mouseRow = mouseRow + 1;
				mouseCol = mouseCol + 1;
				mouseFillZero();
			}
			break;
		case 4:
			if (mouseRow == rowSize - 1) {
				failedOut();
			} else {
				mouseRow = mouseRow + 1;
				mouseFillZero();
			}
			break;
		case 5:
			if (mouseRow == rowSize - 1 || mouseCol == 0) {
				failedOut();
			} else {
				mouseRow = mouseRow + 1;
				mouseCol = mouseCol - 1;
				mouseFillZero();
			}
			break;
		case 6:
			if (mouseCol == 0) {
				failedOut();
			}else {
				failedBackpaddle();
			}
			break;
		case 7:
			if (mouseCol == 0 || mouseRow == 0) {
				failedOut();
			}else {
				failedBackpaddle();
			}
			break;
		case 8:
			if (mouseRow == 0) {
				failedOut();
			}else {
				failedBackpaddle();
			}
			break;
		default:
			break;

		}
	}

	private static void mouseFillZero() {
		//marks mouses' path
		mazeArray[mouseRow][mouseCol] = stepCounter;
	}
	private static void failedBackpaddle() {
		//count how many times mouse went back (restart)
		backpaddle = backpaddle + 1;
		mouseRow = 0;
		mouseCol = 0;
		fillArray();
	}
	
	private static void caughtByCat() {
		//count how many times mouse got caught
		caught = caught + 1;
		mouseRow = 0;
		mouseCol = 0;
		fillArray();
	}
	private static void failedOut() {
		//count how many times mouse failed by falling off
		failOut = failOut + 1;
		mouseRow = 0;
		mouseCol = 0;
		stepCounter = 1;
		fillArray();
	}
	private static void fillArray() {
		// method for filling array with numbers
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				mazeArray[row][col] = 0;
			}
		}

	}

	private static void printArray() {
		// method for printing out array in line.
		int value;
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				value = mazeArray[row][col];
				if (value > 9) {
					System.out.print(" " + value);
				} else if (value <= 9 && value > 0) {
					System.out.print("  " + value);
			}else if (value == -1){
					System.out.print("  C");
				}else {
				
					System.out.print("  " + 0);
				}

			}
			System.out.println();
		}

	}
}
