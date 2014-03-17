import java.util.*;
import java.io.*;

public class NakedPairs {

	public static void main(String[] args){
		/*
		System.out.println("test");
		ArrayList<Integer> pair1 = new ArrayList<Integer>();
		ArrayList<Integer> pair2 = new ArrayList<Integer>();
		ArrayList<Integer> pair3 = new ArrayList<Integer>();
		pair1.add(1);
		pair1.add(2);
		pair2.add(2);
		pair2.add(3);
		pair3.add(6);
		pair3.add(7);
		ArrayList<ArrayList<Integer>> possiblePairs = new ArrayList<ArrayList<Integer>>();
		// possiblePairs.add(pair1);
		possiblePairs.add(pair2);
		possiblePairs.add(pair3);
		// possiblePairs.add(pair1);
		/*
		if(equalPair(pair1, pair2)) {
			System.out.println("pair and pair are equal, true");
		} else {
			System.out.println("not true, unequal");
		}
		/*
		int[] pair = {1,2};
		int[] pair1 = {2,3};
		int[] pair2 = {4,5};
		int[] pair3 = {6,7};
		int[] pair4 = {1,2};
		int[] pair5 = {1};
		ArrayList<int[]> possiblePairs = new ArrayList<int[]>();
		possiblePairs.add(pair1);
		possiblePairs.add(pair2);
		// possiblePairs.add(pair4);
		possiblePairs.add(pair3);
		// possiblePairs.add(pair4);
		System.out.println("Number of occurences " + pairInList(pair1, possiblePairs));*/
		
	}
	
	// Correctly initialises the possible options for all cells 
	public static void nakedPairs(Board board) {	
		// Loop through the 3x9 houses that a sudoku contains
		for(int index = 0; index < 10; index++) {
			// Array that holds possible pairs and their location in a row/
			// column/block. Each element in the array is another array that 
			// contains a possible pair and the location of that pair 
			ArrayList<ArrayList<ArrayList<Integer>>> possibleRow = new ArrayList<ArrayList<ArrayList<Integer>>>();
			ArrayList<ArrayList<ArrayList<Integer>>> possibleColumn = new ArrayList<ArrayList<ArrayList<Integer>>>();
			ArrayList<ArrayList<ArrayList<Integer>>> possibleBlock = new ArrayList<ArrayList<ArrayList<Integer>>>());			
			
			// Current row, column, block
			Entry[] currentRow = board.getRow(index);
			Entry[] currentColumn = board.getColumn(index);
			Entry[] currentBlock = board.getBlock(index);

			// Loop through each element in the 3 houses and add the options of
			// a cell with its index to the array containing all the options
			for(int i = 0; i < 10; i++) {
				if(currentRow[i].getOptions().size() == 2) {
					// Array to contain the pair and index
					ArrayList<ArrayList<Integer>> pairIndex = new ArrayList<ArrayList<Integer>>();
					// Array to contain the index of the found pair
					ArrayList<Integer> index = new ArrayList<Integer>();
					pairIndex.add(currentRow[i].getOptions());
					index.add(i);
					pairIndex.add(index);
				}

				if(currentColumn[i].getOptions().size() == 2) {
					// Array to contain the pair and index
					ArrayList<ArrayList<Integer>> pairIndex = new ArrayList<ArrayList<Integer>>();
					// Array to contain the index of the found pair
					ArrayList<Integer> index = new ArrayList<Integer>();
					pairIndex.add(currentColumn[i].getOptions());
					index.add(i);
					pairIndex.add(index);
				}

				if(currentBlock[i].getOptions().size() == 2) {
					// Array to contain the pair and index
					ArrayList<ArrayList<Integer>> pairIndex = new ArrayList<ArrayList<Integer>>();
					// Array to contain the index of the found pair
					ArrayList<Integer> index = new ArrayList<Integer>();
					pairIndex.add(currentBlock[i].getOptions());
					index.add(i);
					pairIndex.add(index);
				}


			}

			for(int i = 0; i < possibleRow.size(); i++) {
				if()
			}

			for(int i = 0; i < possibleColumn.size(); i++) {
				
			}

			for(int i = 0; i < possibleBlock.size(); i++) {
				
			}

		}

		// Checks all 81 objects if one of the values can be assigned
		for(int count = 0; count < 81; count++) {

			// Sets the current row as the next one, and the current column
			// back to zero in case the end of the row has been reached
			if(count % 9 == 0){
				rowIndex++;
				columnIndex = 0;
			}
			blockIndex = board.calcBlock(rowIndex, columnIndex);

			System.out.println("We are at [" + rowIndex + "," + columnIndex + "]");
			
			// Current row, column, block
			Entry[] currentRow = board.getRow(rowIndex);
			Entry[] currentColumn = board.getColumn(columnIndex);
			Entry[] currentBlock = board.getBlock(blockIndex);

			// Only process cells that don't have a value yet (value of 0)
			if(board.getRows()[rowIndex][columnIndex].getValue() == 0){
				System.out.println("I'm doing something at [" + rowIndex + "," + columnIndex + "]");
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

	// Returns the number of occurences of the integer array pair in the list
	// of arrays possiblePairs
	private static int pairInList(ArrayList<Integer> pair, ArrayList<ArrayList<Integer>> possiblePairs) {
		int occurences = 0;
		for(int i = 0; i < possiblePairs.size(); i++) {
			if(equalPair(pair, possiblePairs.get(i))) {//Arrays.equals(pair, possiblePairs.get(i))) {
				occurences++;
			}
		}
		return occurences;
	}

	// Returns true if the two pairs of integers (ArrayList of size 2) are
	// equal to each other
	private static boolean equalPair(ArrayList<Integer> pair1, ArrayList<Integer> pair2) {
		for(int i = 0; i < pair1.size(); i++) {
			if(pair1.get(i) != pair2.get(i)) {
				return false;
			}
		}
		return true;
	}
}
