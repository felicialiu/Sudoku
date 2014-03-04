import java.util.*;
import java.io.*;

public class Sudoku{

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

		// This loop draws one row
		for(int i = 0; i < 9;i++){
			Entry currentRow[] = board.getRow(i);
			System.out.print("|");
			System.out.print(currentRow[0].getValue());
			System.out.print(currentRow[1].getValue());
			System.out.print(currentRow[2].getValue());
			System.out.print("|");
			System.out.print(currentRow[3].getValue());
			System.out.print(currentRow[4].getValue());
			System.out.print(currentRow[5].getValue());
			System.out.print("|");
			System.out.print(currentRow[6].getValue());
			System.out.print(currentRow[7].getValue());
			System.out.print(currentRow[8].getValue());
			System.out.println("|");
			if(i == 2 || i == 5){
				System.out.println("-------------");
			}
		}
		System.out.println("-------------");
	}

	// This is the solve method which solves the specified sudoku
	static void solve(Board board){
		int checkCount = 0;
		boolean check = false;
		// This checks if the the sudoku has been solved
		while(check == false){
			check = board.checkBoard(board);
			int count = 0;
			int columnCount = 0;
			int rowCount = -1;
			// It checks all 81 objects if one of the values can be found
			while(count < 81){
				// If count % 9 is 0, it means that the end of a row has been reached and the 
				// next row should be found and should start at column 0
				if(count % 9 == 0){
					rowCount++;
					columnCount = 0;
				}
				// This is an array that holds the current row
				Entry[] currentRow = board.getRow(rowCount);

				// This is an array that holds the current column
				Entry[] currentColumn = board.getColumn(columnCount);

				// This checks if there are any values in the row or column that can be taken out 
				// of the options of the current object 
				for(int i = 0; i < 9; i++){
					// This removes the option from the object if value is not zero of a specified object in the row
					if(currentRow[i].getValue() != 0){
						board.removeBoardOption(rowCount,columnCount,0,currentRow[i].getValue());
					}

					// This removes the option from the object if value is not zero of a specified object in the column
					if(currentColumn[i].getValue() != 0){
						board.removeBoardOption(rowCount,columnCount,0,currentColumn[i].getValue());
					}
				}
				count++;
				columnCount++;
			}
		}
	}
	
}