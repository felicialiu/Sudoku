import java.util.*;
import java.io.*;

public class NakedPairs {

	public static void main(String[] args){
		System.out.println("test");	
		ArrayList<Integer> pair1 = new ArrayList<Integer>();	
	}
	
	// Correctly initialises the possible options for all cells 
	public static void nakedPairs(Board board) {	
		// Loop through the 3x9 houses that a sudoku contains
		for(int index = 0; index < 9; index++) {
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
			for(int i = 0; i < 9; i++) {
				if(currentRow[i].getOptions().size() == 2) {
					// Array to contain the pair and index
					ArrayList<ArrayList<Integer>> pairIndex = new ArrayList<ArrayList<Integer>>();
					// Array to contain the index of the found pair
					ArrayList<Integer> index = new ArrayList<Integer>();
					pairIndex.add(currentRow[i].getOptions());
					index.add(i);
					pairIndex.add(index);
					possibleRow.add(pairIndex);
				}

				if(currentColumn[i].getOptions().size() == 2) {
					// Array to contain the pair and index
					ArrayList<ArrayList<Integer>> pairIndex = new ArrayList<ArrayList<Integer>>();
					// Array to contain the index of the found pair
					ArrayList<Integer> index = new ArrayList<Integer>();
					pairIndex.add(currentColumn[i].getOptions());
					index.add(i);
					pairIndex.add(index);
					possibleColumn.add(pairIndex);
				}

				if(currentBlock[i].getOptions().size() == 2) {
					// Array to contain the pair and index
					ArrayList<ArrayList<Integer>> pairIndex = new ArrayList<ArrayList<Integer>>();
					// Array to contain the index of the found pair
					ArrayList<Integer> index = new ArrayList<Integer>();
					pairIndex.add(currentBlock[i].getOptions());
					index.add(i);
					pairIndex.add(index);
					possibleBlock.add(pairIndex);
				}
			}

			for(int i = 0; i < possibleRow.size(); i++) {
				if(pairInList(possibleRow.get(i).get(0), possibleRow) == 2) {
					// Naked Pair!!!!
					for(int j = 0; j < 2; j++) {
						for(int k = 0; k < 9; k++) {
							currentRow[k].removeOption(possibleRow.get(i).get(0).get(j));
						}
					}
				}
			}

			for(int i = 0; i < possibleColumn.size(); i++) {
				if(pairInList(possibleRow.get(i).get(0), possibleRow) == 2) {
					// Naked Pair!!!!
					for(int j = 0; j < 2; j++) {
						for(int k = 0; k < 9; k++) {
							currentRow[k].removeOption(possibleRow.get(i).get(0).get(j));
						}
					}
				}
			}

			for(int i = 0; i < possibleBlock.size(); i++) {
				if(pairInList(possibleRow.get(i).get(0), possibleRow) == 2) {
					// Naked Pair!!!!
					for(int j = 0; j < 2; j++) {
						for(int k = 0; k < 9; k++) {
							currentRow[k].removeOption(possibleRow.get(i).get(0).get(j));
						}
					}
				}
			}

		}
	}
	

	// Returns the number of occurences of the integer array pair in the list
	// of arrays possiblePairs
	private static int pairInList(ArrayList<Integer> pair, ArrayList<ArrayList<ArrayList<Integer>>> possiblePairs) {
		int occurences = 0;
		for(int i = 0; i < possiblePairs.size(); i++) {
			if(equalPair(pair, possiblePairs.get(i).get(0))) {//Arrays.equals(pair, possiblePairs.get(i))) {
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
