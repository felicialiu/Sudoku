import java.util.*;
import java.io.*;

public class Sudoku{

	/* alle sudoku's inlezen en totaalscore printen */

	public static void main(String[] args)
	{
		Board test = new Board();
		drawSudoku(test);
		solve(test);
		drawSudoku(test);

	}

	// This will draw the specified sudokuboard in the users terminal
	static void drawSudoku(Board board){
		System.out.println("-------------");

		// This loop draws a row per iteration
		for(int i = 0; i < 9;i++){
			Entry currentRow[] = board.getRow(i);
			for(int j = 0; j < 9; j++) {
				if(j % 3 == 0) {
					System.out.print("|");
				}
				System.out.print(currentRow[j].getValue());
			}
			System.out.println("|");
			if(i == 2 || i == 5){
				System.out.println("-------------");
			}
		}
		System.out.println("-------------");
	}

/* manier om uit de loop te breken als geen oplossing te vinden is, boolean
om bij te houden of er aanpassingen zijn geweest tijdens 1 while */
/* TO DO: Felicia: code netter maken, while loop fixen */
	// This is the solve method which solves the specified sudoku
	static void solve(Board board){
		boolean check = false;
		// This checks if the the sudoku has been solved
		while(check == false){
			check = board.checkBoard(board);
			// Keeps track 
			int columnCount = 0;
			int rowCount = -1;
			// It checks all 81 objects if one of the values can be found

			for(int count = 0; count < 81; count++) {
				// If count % 9 is 0, it means that the end of a row has been 
				// reached and the next row should be found and should start 
				// at column 0
				if(count % 9 == 0){
					rowCount++;
					columnCount = 0;
				}
				// This is an array that holds the current row
				Entry[] currentRow = board.getRow(rowCount);

				// This is an array that holds the current column
				Entry[] currentColumn = board.getColumn(columnCount);

				// Checks if there are any values in the row or column that can 
				// removed from the options of the current object 
				for(int i = 0; i < 9; i++){
					// Removes a specific value from "options" from an Entry if
					// that value has been found in the same row
					if(currentRow[i].getValue() != 0){
						board.removeBoardOption(rowCount, columnCount, 
												0, currentRow[i].getValue());
					}

					// Does the same for an Entry in a column
					if(currentColumn[i].getValue() != 0){
						board.removeBoardOption(rowCount, columnCount,
												0, currentColumn[i].getValue());
					}

					/* MISSING: removing values from options of a block */
				}
				columnCount++;
			}
		}
	}
	
}