import java.util.*;
import java.io.*;

public class sudokuBoard{

	//Er moet nog een setboard method in die alle 3 de 2d arrays veranderd!!

	//2d array for rows
	private sudokuObject[][] rows = new sudokuObject[9][9];

	//2d array for columns
	private sudokuObject[][] columns = new sudokuObject[9][9];

	//2d array for blocks
	private sudokuObject[][] blocks = new sudokuObject[9][9];

	private int count = 0;
	private int rowcount = 0;

	public static void main(String[] args)
	{
		sudokuBoard test = new sudokuBoard();
	} 

	public sudokuBoard(){
		try{

			BufferedReader sudokuReader = new BufferedReader(new FileReader("easySudoku.txt"));
			String oneSudoku = sudokuReader.readLine();//This is one sudoku

			//This adds all the objects to the row and column arrays
			for(int i = 0; i < oneSudoku.length(); i++){
				sudokuObject object = new sudokuObject(Character.getNumericValue(oneSudoku.charAt(i)));
				if(count == 9){
					rowcount++;
					count = 0;
				}
				rows[rowcount][count] = object;
				columns[count][rowcount] = object;
				count++;
			}

			//This adds blocks to the blocks array using the just created row array
			int blockindex = 0;
			int block = 0;
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

	//this returns a specified row
	public sudokuObject[] getRow(int row){
		return rows[row];
	}

	//this returns a specified column
	public sudokuObject[] getColumn(int column){
		return columns[column];
	}

	//this returns a specified block
	public sudokuObject[] getBlock(int block){
		return blocks[block];
	}
}