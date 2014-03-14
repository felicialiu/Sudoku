import java.util.*;
import java.io.*;

public class Board {

	private Entry[][] rows = new Entry[9][9];
	private Entry[][] columns = new Entry[9][9];
	private Entry[][] blocks = new Entry[9][9];

	public static void main(String[] args) {
		System.out.println(calcBlock(0,3));
		/*
		Board test = new Board();
		test.setBoard(0,1,0,6);
		Entry[] current = test.getBlock(0);
		System.out.println(current[0].getOptions().get(5));
		test.removeBoardOption(0,0,0,6);
		System.out.println(current[0].getOptions().get(5));
		
		for(int j = 0; j < 9; j++ ){
			System.out.println(test.blocks[3][j].getValue());
		}
		*/
	} 

	// Constructor that creates a sudokuBoard representation from String
	public Board(String sudoku) {
		/* do something */
	}

	// Constructor that creates a sudokuBoard representation from reading in
	// the textfile easySudoku.txt
/* TO DO: Jonas - use methods to make Board() constructor more readable */
	public Board() {
		try {
			int blockindex = 0;
			int block = 0;
			int count = 0;
			int rowcount = 0;
			BufferedReader sudokuReader = new BufferedReader(new FileReader("easySudoku.txt"));
			String oneSudoku = sudokuReader.readLine();//This is one sudoku

			//This adds all the objects to the row and column arrays
			for(int i = 0; i < oneSudoku.length(); i++){
				Entry object = new Entry(Character.getNumericValue(oneSudoku.charAt(i)));
				if(count == 9){
					rowcount++;
					count = 0;
				}
				rows[rowcount][count] = object;
				columns[count][rowcount] = object;
				count++;
			}

			//This adds blocks to the blocks array using the just created row array
			for(int i = 0; i < 9; i++){
				blocks[block][blockindex] = rows[i][0];
				blocks[block][blockindex+1] = rows[i][1];
				blocks[block][blockindex+2] = rows[i][2];
				blocks[block+1][blockindex] = rows[i][3];
				blocks[block+1][blockindex+1] = rows[i][4];
				blocks[block+1][blockindex+2] = rows[i][5];
				blocks[block+2][blockindex] = rows[i][6];
				blocks[block+2][blockindex+1] = rows[i][7];
				blocks[block+2][blockindex+2] = rows[i][8];
				blockindex += 3;
				if(i == 2 || i == 5){
					block += 3;
					blockindex = 0;
				}
			}

			} catch (IOException e) {
	            e.printStackTrace();
	        }
		}

	// This method loops through the entire board and checks if there are still
	// Entries with value 0; meaning the sudoku is not solved yet 
	public boolean checkBoard(Board board){
		for(int i = 0; i < 9; i++){
			Entry[] currentRow = board.getRow(i);
			for(int j = 0; j < 9; j++){
				if(currentRow[j].getValue() == 0){
					return false;
				} 
			}
		}
		return true;
	}

	// Sets a value in a specific row/column/block to newValue
	public void setBoard(int row, int column, int block, int newValue) {
		// Set row value 
		rows[row][column].setValue(newValue);
		/*
		// Set column value 
		columns[column][row].setValue(newValue);

		// Set block value 
		int index = calcBlockIndex(row, column);
		blocks[block][index].setValue(newValue);
		*/
	}

	/*
	// Removes an option from all the Entries in the same row/column/block
	public void removeOptionComplete(int row, int column, int block, int option) {
		// Remove from all Entries in the same row, and also their reference
		// in column block

		for(int n = 0; n < 9; n++) {
			rows[row][n].removeOption(option);
			columns[n][row].removeOption(option);
		}

		// Remove from all Entries in the same column, and also their reference
		// in row
		for(int m = 0; m < 9; m++) {
			columns[column][m].removeOption(option);
			rows[m][column].removeOption(option);
		}

		for(int k = 0; k < 9; k++) {
			// block
		}
	}
	*/

	// Removes an option from an Entry in a given row/column/block
	public boolean removeBoardOption(int row, int column, int block, int option){
		// Removes row option 
		return rows[row][column].removeOption(option);
		/*
		// Removes column option 
		columns[column][row].removeOption(option);

		// Removes block option 
		int index = calcBlockIndex(row, column);
		blocks[block][index].removeOption(option);
		*/
	}

	// Returns the row representation of the sudoku
	public Entry[][] getRows() {
		return rows;
	}

	// Returns the row at index row
	public Entry[] getRow(int row){
		return rows[row];
	}

	// Returns the column at index column
	public Entry[] getColumn(int column){
		return columns[column];
	}

	// Returns the block at index block
	public Entry[] getBlock(int block){
		return blocks[block];
	}

	// Calculates the local index of an Entry in a block, given location
	// specified by row and column
	static int calcBlockIndex(int row, int column){
		int blockplace = column;
		
		// Rows 0, 3 and 6 contains all the first 3 objects in the blocks, 
		// meaning index is always 0, 1 or 2
		if(row % 3 == 0) {
			if(column > 2){
				blockplace -= 3;
			} else if(column > 5){
				blockplace -= 6;
			}
		}

		// Rows 1, 4 and 7 contains all the middle 3 objects in the blocks, 
		// meaning index is always 3, 4 or 5
		else if((row - 1) % 3 == 0) {
			if(column < 3){
				blockplace += 3;
			} else if(column > 5){
				blockplace -= 3;
			}
		}

		// Rows 2, 5 and 8 contains all the last 3 objects in the blocks, 
		// meaning index is always 6, 7 or 8
		else if((row - 2)% 3 == 0) {
			if(column < 3){
				blockplace += 6;
			} else if(column < 5){
				blockplace += 3;
			}
		}
		return blockplace;
	}

	// Given the location (row, column) of a cell, returns an integer
	// representing the block (0,...,8) the cell belongs to
	// For calcBlock(0,3) it will return 1
	static int calcBlock(int row, int column) {
		// First in-block row
		if(row - 3 < 0 && column - 3 < 0) {
			return 0;
		} else if (row - 3 < 0 && column - 6 < 0) {
			return 1;
		} else if (row - 3 < 0 && column - 9 < 0) {
			return 2;
		} 
		// Second in-block row
		else if(row - 6 < 0 && column - 3 < 0) {
			return 3;
		} else if(row - 6 < 0 && column - 6 < 0) {
			return 4;
		} else if(row - 6 < 0 && column - 9 < 0) {
			return 5;
		}
		// Third in-block row
		else if(row - 9 < 0 && column - 3 < 0) {
			return 6;
		} else if(row - 9 < 0 && column - 6 < 0) {
			return 7;
		} else if (row - 9 < 0 && column - 9 < 0) {
			return 8;
		}
		return 0;	
	}
}