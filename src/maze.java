import java.util.Arrays;

public class maze {

	final public int rowSize;
	final public int colSize;
	public int[][] mazeArray;
	public int[][] mousePosition;
	public int[][] catPosition;
	
	public maze() {
		rowSize = 9;
		colSize = 9;
		mazeArray = new int[rowSize][colSize];
	}

	public void fillArray() {
		int number = 0;
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				number++;
				mazeArray[row][col] = number;
			}
		}
	}

	public void printArray() {
		int value;
		for(int row = 0 ; row < rowSize; row++) {
			for(int col = 0 ; col < colSize; col++) {
			value = mazeArray[row][col];
				if(value>9) {
					System.out.print(" " + value);
				}else {
					System.out.print("  " + value);
				}
				
			}
			System.out.println();
		}
	}
}
