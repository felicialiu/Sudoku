import java.util.*;
import java.io.*;

public class Board {

	//Er moet nog een setboard method in die alle 3 de 2d arrays verandert!!

	private Entry[][] rows = new Entry[9][9];
	private Entry[][] columns = new Entry[9][9];
	private Entry[][] blocks = new Entry[9][9];
	private int count = 0;
	private int rowcount = 0;

	public static void main(String[] args)
	{
		Board test = new Board();
		/*for(int j = 0; j < 9; j++ ){
			System.out.println(test.blocks[3][j].getValue());
		}*/
	} 

	public sudokuBoard() {
		try {
			BufferedReader sudokuReader = new BufferedReader(new FileReader("easySudoku.txt"));
			String oneSudoku = sudokuReader.readLine();
			for(int i = 0; i < oneSudoku.length(); i++){
				Entry object = new Entry(Character.getNumericValue(oneSudoku.charAt(i)));
				if(count == 9){
					rowcount++;
					count = 0;
				}
				//System.out.println("row : "+rowcount+"count : "+count);
				rows[rowcount][count] = object;
				columns[count][rowcount] = object;
				count++;
			}
			int one = 0;
			int two = 1;
			int three = 2;
			int j = 0;
			for(int i = 0; i < 9; i++){
				blocks[j][one] = rows[i][0];
				blocks[j][two] = rows[i][1];
				blocks[j][three] = rows[i][2];
				blocks[j+1][one] = rows[i][3];
				blocks[j+1][two] = rows[i][4];
				blocks[j+1][three] = rows[i][5];
				blocks[j+2][one] = rows[i][6];
				blocks[j+2][two] = rows[i][7];
				blocks[j+2][three] = rows[i][8];
				one += 3;
				two += 3;
				three += 3;
				if(i == 2 || i == 5){
					j += 3;
					one = 0;
					two = 1;
					three = 2;
				}
			}

			} catch (IOException e) {
	            e.printStackTrace();
	        }
		}

	public Entry[] getRow(int row){
		return rows[row];
	}

	public Entry[] getColumn(int column){
		return columns[column];
	}

	public Entry[] getBlock(int block){
		return blocks[block];
	}

/*
	public void makeBlock(){
		int one = 0;
		int two = 1;
		int three = 2;
		int j = 0;
		for(int i = 0; i < 9; i++){
			blocks[j][one] = rows[i][0];
			blocks[j][two] = rows[i][1];
			blocks[j][three] = rows[i][2];
			blocks[j+1][one] = rows[i][3];
			blocks[j+1][two] = rows[i][4];
			blocks[j+1][three] = rows[i][5];
			blocks[j+2][one] = rows[i][6];
			blocks[j+2][two] = rows[i][7];
			blocks[j+2][three] = rows[i][8];
			one += 3;
			two += 3;
			three += 3;
			if(i == 2 || i == 5){
				j++;
				one = 0;
				two = 1;
				three = 2;
			}
		}
	}*/
}