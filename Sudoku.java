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

	static void solve(Board board){
		Entry[] currentRow = board.getRow(0);
		Entry[] currentColumn = board.getColumn(0);
		int count = 0;
		int columnCount = 0;
		int rowCount = 0;
		while(count < 9){
			for(int i = 0; i < 9; i++){
				if(currentRow[i].getValue() != 0){
					board.removeBoardOption(count,i,0,currentRow[i].getValue();
				}
				if(currentColumn[i].getValue() != 0){
					board.removeBoardOption(i,count,0,currentRow[i].getValue();
				}
			}
			rowCount++;
			count++;
		}
	}
	
}