import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class Sudoku {

	private static SudokuGraphics drawing; 

	public static boolean same = false;

	private static boolean solved;

	private static BufferedReader sudokuReader;

	/* alle sudoku's inlezen en totaalscore printen */

	public static void main(String[] args)
	{	
		int total = 0;
<<<<<<< HEAD
		int totalSolved = 0;
		int totalUnsolved = 0;
		String fileName = "";

		if(args.length == 0){
            total = 100;
            fileName = "subig20.txt";
        } else {
        	if(args[0].equals("-f")) {
        		fileName = args[1];
        		total = Integer.parseInt(args[2]);
        	} else if(!Pattern.matches("[a-zA-Z]+", args[0])){
        		total = Integer.parseInt(args[0]);
        		fileName = "subig20.txt";
        	}
        }
		try{
			sudokuReader = new BufferedReader(new FileReader(fileName));
		} catch (IOException e) {
	        e.printStackTrace();
	    }
			/*
			drawing = new SudokuGraphics(9,9,test);
			hiddenpair(test.getBlock(0));
			*/

	    // The start time of our algorithm
	    long start = System.currentTimeMillis();

	    // The number of sudoku's the user wants solved
	    length = Integer.parseInt(args[1]);
	 
	 	// Tries to solve all the sudoku's  
		for (int i = 0; i < length ;i++) {
			Board test = new Board(sudokuReader);
			//drawing = new SudokuGraphics(9,9,test);
			initBoard(test);
			solve(test);
			if(solved == true){
				totalSolved++;
				drawSudoku(9,9,test);
			} else {
				totalUnsolved++;
				drawSudoku(9,9,test);
			}
		}

		System.out.println("Total solved " + totalSolved);
		System.out.println("total unsolved " + totalUnsolved);
		System.out.println("Percentage of correctly solved sudoku's is " + (totalSolved/(total+0.0))*100);
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

		// The end time of our algorithm
		long end = System.currentTimeMillis();

		System.out.println("Total number of sudoku's loaded is "+(total+totalnot));
		System.out.println("Number of sudoku's solved is "+total);
		System.out.println("Number of sudoku's unsolved is "+totalnot);
		System.out.println("Solve rate is "+(int)(((double)total/(double)(total+totalnot))*100)+" %");
		System.out.println("Time elapsed is "+(end-start)+" ms");

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

	// This is the solve method which solves the specified sudoku
	static void solve(Board board){
		// Checks whether the sudoku has been solved
		solved = board.checkBoard(board);

		// Checks whether sudoku has been adjusted
		boolean temp;
		
		while(!solved && !same){
			same = true;
			int rowIndex = -1;
			int columnIndex = 0;
			int blockIndex = 0;		
			initBoard(board);
			
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
					}
				}
				columnIndex++;
			}
			if(same){
				//NakedPairs.nakedPairs(board);
				for (int i =0; i<9 ;i++ ) {
					
					Hidden.hiddenCombi(board.getRow(i), board);
					initBoard(board);
					
					Hidden.hiddenCombi(board.getColumn(i), board);
					initBoard(board);
					
					Hidden.hiddenCombi(board.getBlock(i), board);
					initBoard(board);
				}
	
				
			}
			//initBoard(board);
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
					Entry currentCell = board.getRows()[rowIndex][columnIndex];
					if(currentCell.assignValue()) {
						//System.out.println("I assigned a value!");
						//System.out.println("The value was " + currentCell.getValue());
						board.removeOptionComplete(rowIndex, columnIndex, blockIndex, currentCell.getValue());
						same = false;
					}
				}
			}
			columnIndex++;
		}
	}
}