import java.util.*;
import java.io.*;

public class sudoku{

	private sudokuObject[][] board = new sudokuObject[9][9];
	private int count = 1; 

	public static void main(String[] args)
	{
		sudoku test = new sudoku();
		test.readSudoku();
	}

	public sudoku()
	{
		
	}

	public void readSudoku(){
		try{
			BufferedReader sudokuReader = new BufferedReader(new FileReader("easySudoku.txt"));
			String oneSudoku = sudokuReader.readLine();
			int i = 0;
			System.out.println(oneSudoku.charAt(i));
			
			while(oneSudoku.charAt(i) != '')
			{
				sudokuObject object = new sudokuObject(Character.getNumericValue(oneSudoku.charAt(i)), i);
				i++;
				if(i >= 9){
					i = 1;
					count++;
				}
				board[count][i] = object;

			}

			System.out.println(oneSudoku);
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
}