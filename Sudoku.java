import java.util.*;
import java.io.*;

public class Sudoku {

	public static void main(String[] args)
	{
		Board test = new Board();
		drawSudoku(test);

	}

	// Returns the current representation of the sudoku
	static void drawSudoku(Board board){
		System.out.println("-------------");
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
}