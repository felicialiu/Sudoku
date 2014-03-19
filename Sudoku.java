import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class Sudoku {

	// This will represent the GUI
	private static SudokuGraphics drawing; 

	// Boolean which is true if nothing changes in the main solve else is false
	public static boolean same = false;

	// Boolean to see whether the sudoku has been solved
	private static boolean solved;

	// Bufferedreader which reads in a specific sudoku file
	private static BufferedReader sudokuReader;

	// alle sudoku's inlezen en totaalscore printen
	public static void main(String[] args)
	{	
		int total = 0;
		int totalSolved = 0;
		int totalUnsolved = 0;
		String fileName = "";

		// Checks if the user specified a file or how many sudoku's should be solved
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

        // Reads in the above specified sudoku file
		try{
			sudokuReader = new BufferedReader(new FileReader(fileName));
		} catch (IOException e) {
	        e.printStackTrace();
	    }
			/* drawing = new SudokuGraphics(9,9,test);	*/

	    // The start time of our algorithm
	    long start = System.currentTimeMillis();
	 
	 	// Tries to solve all the sudoku's  
		for (int i = 0; i < total ;i++) {
			System.out.println("This is sudoku number " + i);
			Board test = new Board(sudokuReader);
			initBoard(test);
			solve(test);
			if(solved == true){
				totalSolved++;
			} else {
				totalUnsolved++;
			}
		}

		// The end time of our algorithm
		long end = System.currentTimeMillis();

		System.out.println("Total number of sudoku's loaded is "+(totalSolved+totalUnsolved));
		System.out.println("Number of sudoku's solved is "+totalSolved);
		System.out.println("Number of sudoku's unsolved is "+totalUnsolved);
		System.out.println("Solve rate is "+(int)(((double)totalSolved/(double)(totalSolved+totalUnsolved))*100)+" %");
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
				// Current row, column, and block
				Entry[] currentRow = board.getRow(rowIndex);
				Entry[] currentColumn = board.getColumn(columnIndex);
				Entry[] currentBlock = board.getBlock(blockIndex);

				// Current Entry
				Entry currentCell = board.getRows()[rowIndex][columnIndex];
				// Only process cells that don't have a value yet (value of 0)
				if(currentCell.getValue() == 0){
					if(currentCell.assignValue()) {
						board.removeOptionComplete(rowIndex, columnIndex, blockIndex, currentCell.getValue());
						same = false;
					}
				}
				columnIndex++;
			}
			// If nothing changed in the main solve, more complex methods are used
			if(same){
				// This boolean checks if hidden single makes any changes
				Hidden.sameHidden = true;

				// This boolean is used to see if hidden pair makes any changes
				Hidden.hpHidden = true;

				// This calls the hidden single method on all thr rows, columns and blocks
				for (int i =0; i<9 ;i++ ) {
					Hidden.hiddenCombi(board.getRow(i), board);
					initBoard(board);
					
					Hidden.hiddenCombi(board.getColumn(i), board);
					initBoard(board);
					
					Hidden.hiddenCombi(board.getBlock(i), board);
					initBoard(board);
				}

				for (int i =0; i<9 ;i++ ) {
					NakedPairs.nakedPairs(board.getRow(i));
					//initBoard(board);
				}
				initBoard(board);
				for (int i =0; i<9 ;i++ ) {					
					NakedPairs.nakedPairs(board.getColumn(i));
					//initBoard(board);
				}
				initBoard(board);
				for (int i =0; i<9 ;i++ ) {					
					NakedPairs.nakedPairs(board.getBlock(i));
					//initBoard(board);
				}
				initBoard(board);
				// If there isnt a hidden single found, hidden pair method is used
				if (Hidden.sameHidden) {
					for (int i =0; i < 9 ;i++ ) {
						Hidden.hiddenPair(Hidden.getFrequencies(board.getBlock(i)), board.getBlock(i), board);
						Sudoku.initBoard(board);
					}

					// If there is no hidden pair found in blocks, row and column will be checked
					if(Hidden.hpHidden){
						for (int i =0; i < 9 ;i++ ) {
							Hidden.hiddenPair(Hidden.getFrequencies(board.getRow(i)), board.getRow(i), board);
							Sudoku.initBoard(board);
						}
						
						for (int i =0; i < 9 ;i++ ) {
							Hidden.hiddenPair(Hidden.getFrequencies(board.getColumn(i)), board.getColumn(i), board);
							Sudoku.initBoard(board);
						}
					}
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
			
			// Current row, column, block
			Entry[] currentRow = board.getRow(rowIndex);
			Entry[] currentColumn = board.getColumn(columnIndex);
			Entry[] currentBlock = board.getBlock(blockIndex);

			// Only process cells that don't have a value yet (value of 0)
			if(board.getRows()[rowIndex][columnIndex].getValue() == 0){
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
					// Assigns the value if only one option is left
					Entry currentCell = board.getRows()[rowIndex][columnIndex];
					if(currentCell.assignValue()) {
						board.removeOptionComplete(rowIndex, columnIndex, blockIndex, currentCell.getValue());
						same = false;
					}
				}
			}
			columnIndex++;
		}
	}
}