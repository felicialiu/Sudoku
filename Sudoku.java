import java.util.*;
import java.io.*;

public class Sudoku {

	private static SudokuGraphics drawing; 

	public static boolean same = false;

	/* alle sudoku's inlezen en totaalscore printen */

	public static void main(String[] args)
	{
		
		Board test = new Board();
		/*
		drawing = new SudokuGraphics(9,9,test);
		hiddenpair(test.getBlock(0));
		*/

		System.out.println("Loaded board!");
		drawSudoku(9,9, test);
		initBoard(test);
		System.out.println("Initialized board");
		drawSudoku(9,9, test);
		solve(test);
		System.out.println("solved board");
		drawSudoku(9,9, test);
		/*
		for (int i =0; i<9 ;i++ ) {
			Hidden.hiddenpair(test.getColumn(i));	
			Hidden.hiddenpair(test.getRow(i));
			Hidden.hiddenpair(test.getColumn(i));
		}	
		*/
		//Hidden.hiddenpair(test.getColumn(8));
		//drawSudoku(9,9, test);
		//System.out.println("hidden solved");
		//System.out.println("hidden pair = "+test.getColumn(8)[4].getOptions().get(2));
		/*
		initBoard(test);
		System.out.println("Updated options!");
		drawSudoku(9,9, test);
		solve(test);
		System.out.println("Tried to solve!");
		drawSudoku(9,9, test);
		*/
	}

	// This will draw the specified sudokuboard in the users terminal
	static void drawSudoku(int length, int width, Board board){
		// drawing = new SudokuGraphics(length, width, board);
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

/*
			// Current row and column
			Entry[] currentRow = board.getRow(rowCount);
			Entry[] currentColumn = board.getColumn(columnCount);

			
			for(int i = 0; i < 9; i++){
				// Single
				if(currentRow[i].checkOptions()) {
					changed = true;
					// remove from row/column/block
				}
				if (currentColumn[i].checkOptions()) {
					changed = true;
				}




				
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

				// MISSING: removing values from options of a block 

			}
			columnCount++;
		}
		solved = board.checkBoard(board);
	}
}
*/

	// This is the solve method which solves the specified sudoku
	static void solve(Board board){
		// Checks whether the sudoku has been solved
		boolean solved = board.checkBoard(board);

		// Checks whether sudoku has been adjusted
		boolean temp;
		
		while(!solved && !same){
			same = true;
			int rowIndex = -1;
			int columnIndex = 0;
			int blockIndex = 0;		
			
			
			// Checks all 81 objects if one of the values can be assigned
			for(int count = 0; count < 81; count++) {

				// Sets the current row as the next one, and the current column
				// back to zero in case the end of the row has been reached
				if(count % 9 == 0){
					rowIndex++;
					columnIndex = 0;
				}
				blockIndex = board.calcBlock(rowIndex, columnIndex);
				//System.out.println("We are at [" + rowIndex + "," + columnIndex + "]");
				// Current row, column, and block
				Entry[] currentRow = board.getRow(rowIndex);
				Entry[] currentColumn = board.getColumn(columnIndex);
				Entry[] currentBlock = board.getBlock(blockIndex);

				// Current Entry
				Entry currentCell = board.getRows()[rowIndex][columnIndex];
				// Only process cells that don't have a value yet (value of 0)
				if(currentCell.getValue() == 0){
					//System.out.println("I'm doing something at [" + rowIndex + "," + columnIndex + "]");
					if(currentCell.assignValue()) {
						//System.out.println("I assigned a value!");
						//System.out.println("The value was " + currentCell.getValue());
						board.removeOptionComplete(rowIndex, columnIndex, blockIndex, currentCell.getValue());
						same = false;
						/*
						// Checks if there are any values in the row or column that can be 
						// removed from the options of the current object 
						for(int i = 0; i < 9; i++){

							// Removes a specific value from "options" from an Entry if
							// that value has been found in the same row
							if(currentRow[i].getValue() != 0){
								temp = board.removeBoardOption(rowIndex, columnIndex, 
														0, currentRow[i].getValue());
								// if(board.assignValue(rowIndex, columIndex)) {
								// removeFromAll(rowIndex)
								// }
								if(same) {
									same = temp;
								}
								// System.out.println("ROW at ["+rowIndex+","+columnIndex+"], value " + currentRow[i].getValue());
							}

							// Does the same for an Entry in a column
							if(currentColumn[i].getValue() != 0){
								temp = board.removeBoardOption(rowIndex, columnIndex,
													0, currentColumn[i].getValue());
								if(same) {
									same = temp;
								}
								// System.out.println("COLUMN at ["+rowIndex+","+columnIndex+"], value " + currentColumn[i].getValue());

							}
							// Does the same for an Entry in a block
							if(currentBlock[i].getValue() != 0) {
								temp = board.removeBoardOption(rowIndex, columnIndex,
									0, currentBlock[i].getValue());
								if(same) {
									same = temp;
								}
							}
						}
						*/
					}
				}
				columnIndex++;
			}
			if(same){
				for (int i =0; i<9 ;i++ ) {
					Hidden.hiddenCombi(board.getColumn(i), board);
					initBoard(board);
				}	
				for (int i =0; i<9 ;i++ ) {
					Hidden.hiddenCombi(board.getBlock(i), board);
					initBoard(board);
				}
				for (int i =0; i<9 ;i++ ) {
					Hidden.hiddenCombi(board.getRow(i), board);
					initBoard(board);
				}
			}
			solved = board.checkBoard(board);
		}
	}

	// Correctly initalises the possible options for all cells 
	static void initBoard(Board board) {
		int rowIndex = -1;
		int columnIndex = 0;
		int blockIndex = 0;		
		
		// Checks all 81 objects if one of the values can be assigned
		for(int count = 0; count < 81; count++) {

			// Sets the current row as the next one, and the current column
			// back to zero in case the end of the row has been reached
			if(count % 9 == 0){
				rowIndex++;
				columnIndex = 0;
			}
			blockIndex = board.calcBlock(rowIndex, columnIndex);

			//System.out.println("We are at [" + rowIndex + "," + columnIndex + "]");
			
			// Current row, column, block
			Entry[] currentRow = board.getRow(rowIndex);
			Entry[] currentColumn = board.getColumn(columnIndex);
			Entry[] currentBlock = board.getBlock(blockIndex);

			// Only process cells that don't have a value yet (value of 0)
			if(board.getRows()[rowIndex][columnIndex].getValue() == 0){
				//System.out.println("I'm doing something at [" + rowIndex + "," + columnIndex + "]");
				// Checks if there are any values in the row or column that can be 
				// removed from the options of the current object 
				for(int i = 0; i < 9; i++){

					// Removes a specific value from "options" from an Entry if
					// that value has been found in the same row
					if(currentRow[i].getValue() != 0){
						board.removeBoardOption(rowIndex, columnIndex, 
												0, currentRow[i].getValue());
					}

					// Does the same for an Entry in a column
					if(currentColumn[i].getValue() != 0){
						board.removeBoardOption(rowIndex, columnIndex,
											0, currentColumn[i].getValue());
					}
					// Does the same for an Entry in a block
					if(currentBlock[i].getValue() != 0) {
						board.removeBoardOption(rowIndex, columnIndex,
							0, currentBlock[i].getValue());
					}
				}
			}
			columnIndex++;
		}
	}
}