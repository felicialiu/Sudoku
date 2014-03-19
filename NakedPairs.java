import java.util.*;
import java.io.*;

public class NakedPairs {

	public static void main(String[] args){
		Entry[] test = new Entry[9];

		// fill cells with entry objects
		for(int i = 0; i < 9; i++){
			Entry object = new Entry(0);
			test[i] = object;
		}

		// Value is 1
		test[0].setValue(1);

		// Options are 3 and 7
		for(int i = 1; i < 10; i++){
			if(i != 7 && i != 3){
				test[1].removeOption(i);
			}
		}

		// Options 3 and 9
		for(int i  = 1; i < 10; i++){
			if(i != 9 && i != 3){
				test[2].removeOption(i);
			}
		}

		// Options 3 and 9
		for(int i  = 1; i < 10; i++){
			if(i != 9 && i != 3){
				test[3].removeOption(i);
			}
		}

		// Options 2 and 7
		for(int i = 1; i < 10; i++){
			if(i != 7 && i != 2){
				test[4].removeOption(i);
			}
		}
		// Value 8
		test[5].setValue(8);

		// Options 2, 4, 5, 7
		for(int i  = 1; i < 10; i++){
			if(i != 2 && i != 4 && i != 5 && i != 7){
				test[6].removeOption(i);
			}
		}

		// Value 6
		test[7].setValue(6);

		// Options 2, 4, 5, 7
		for(int i  = 1; i < 10; i++){
			if(i != 2 && i != 4 && i != 5 && i != 7){
				test[8].removeOption(i);
			}
		}
		//System.out.println("Options in oneth cell are " + test[1].getOptions());
		nakedPairs(test);
		//System.out.println("Options in oneth cell are " + test[1].getOptions());

	}
	
	// Correctly initialises the possible options for all cells 
	public static void nakedPairs(Entry[] house) {	
		// Loop through the 9 entries that a house contains
		//for(int index = 0; index < 9; index++) {
			// Array that holds possible pairs and their location in a house.
			// Each element in the array is another array that contains a 
			// possible pair and the location of that pair 
			ArrayList<ArrayList<ArrayList<Integer>>> possiblePairs = new ArrayList<ArrayList<ArrayList<Integer>>>();			

			// Loop through each element in the 3 houses and add the options of
			// a cell with its index to the array containing all the options
			for(int i = 0; i < 9; i++) {
				if(house[i].getValue() != 0 && house[i].getOptions().size() == 2) {
					System.out.println(house[i].getOptions().size());
					// Array to contain the pair and index
					ArrayList<ArrayList<Integer>> pairIndex = new ArrayList<ArrayList<Integer>>();
					// Array to contain the index of the found pair
					ArrayList<Integer> location = new ArrayList<Integer>();
					pairIndex.add(house[i].getOptions());
					location.add(i);
					pairIndex.add(location);
					possiblePairs.add(pairIndex);
				}
			}

			for(int i = 0; i < possiblePairs.size(); i++) {
				if(pairInList(possiblePairs.get(i).get(0), possiblePairs) == 2) {
					// Naked Pair!!!!
					// Get locations of that pair and save in array locations
					ArrayList<Integer> locations = getPairLocations(possiblePairs.get(i).get(0), possiblePairs);
					if(possiblePairs.get(i).get(0).size() != 2) {
						System.out.println("Pair found not size 2!");
						System.out.println(possiblePairs.get(i).get(0));
					}
					//for(int j = 0; j < 2; j++) {
						for(int k = 0; k < 9; k++) {
							if(!locations.contains(k)) {
								//if (possiblePairs.get(i).get(0).size() == 2) {
									house[k].removeOption(possiblePairs.get(i).get(0).get(0));
									house[k].removeOption(possiblePairs.get(i).get(0).get(1));
								//}
							}
						}
					//}
				}
			}
		//}
	}

	// Returns a list with the two locations at which the naked pair can be found
	private static ArrayList<Integer> getPairLocations(ArrayList<Integer> pair, ArrayList<ArrayList<ArrayList<Integer>>> possiblePairs) {
		ArrayList<Integer> locations = new ArrayList<Integer>();
		for(int i = 0; i < possiblePairs.size(); i++) {
			//System.out.println("Index i is at " + i);
			//System.out.println("Is the given pair equal to " + possiblePairs.get(i).get(0));
			if(equalPair(pair, possiblePairs.get(i).get(0)) && possiblePairs.get(i).get(0).size() == 2) {
				System.out.println("Pair is in table " + possiblePairs.get(i).get(0) + " and its size is " + possiblePairs.get(i).get(0).size());
				locations.add(possiblePairs.get(i).get(1).get(0));
				//System.out.println("added!");
			}
		}
		return locations;
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

	// Returns true if the two pairs of integers (ArrayLists of size 2) are
	// equal to each other
	private static boolean equalPair(ArrayList<Integer> pair1, ArrayList<Integer> pair2) {
		if(pair1.size() == pair2.size()){
			for(int i = 0; i < pair1.size(); i++) {
				if(pair1.get(i) != pair2.get(i)) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}		
	}
}





	/*
	// Correctly initialises the possible options for all cells 
	public static void nakedPairs(Board board) {	
		// Loop through the 3x9 houses that a sudoku contains
		for(int index = 0; index < 9; index++) {
			// Array that holds possible pairs and their location in a row/
			// column/block. Each element in the array is another array that 
			// contains a possible pair and the location of that pair 
			ArrayList<ArrayList<ArrayList<Integer>>> possibleRow = new ArrayList<ArrayList<ArrayList<Integer>>>();
			ArrayList<ArrayList<ArrayList<Integer>>> possibleColumn = new ArrayList<ArrayList<ArrayList<Integer>>>();
			ArrayList<ArrayList<ArrayList<Integer>>> possibleBlock = new ArrayList<ArrayList<ArrayList<Integer>>>();			
			
			// Current row, column, block
			Entry[] currentRow = board.getRow(index);
			Entry[] currentColumn = board.getColumn(index);
			Entry[] currentBlock = board.getBlock(index);

			// Loop through each element in the 3 houses and add the options of
			// a cell with its index to the array containing all the options
			for(int i = 0; i < 9; i++) {
				if(currentRow[i].getOptions().size() == 2) {
					// Array to contain the pair and index
					ArrayList<ArrayList<Integer>> pairRowIndex = new ArrayList<ArrayList<Integer>>();
					// Array to contain the index of the found pair
					ArrayList<Integer> rowIndex = new ArrayList<Integer>();
					pairRowIndex.add(currentRow[i].getOptions());
					rowIndex.add(i);
					pairRowIndex.add(rowIndex);
					possibleRow.add(pairRowIndex);
				}

				if(currentColumn[i].getOptions().size() == 2) {
					// Array to contain the pair and index
					ArrayList<ArrayList<Integer>> pairColumnIndex = new ArrayList<ArrayList<Integer>>();
					// Array to contain the index of the found pair
					ArrayList<Integer> columnIndex = new ArrayList<Integer>();
					pairColumnIndex.add(currentColumn[i].getOptions());
					columnIndex.add(i);
					pairColumnIndex.add(columnIndex);
					possibleColumn.add(pairColumnIndex);
				}

				if(currentBlock[i].getOptions().size() == 2) {
					// Array to contain the pair and index
					ArrayList<ArrayList<Integer>> pairBlockIndex = new ArrayList<ArrayList<Integer>>();
					// Array to contain the index of the found pair
					ArrayList<Integer> blockIndex = new ArrayList<Integer>();
					pairBlockIndex.add(currentBlock[i].getOptions());
					blockIndex.add(i);
					pairBlockIndex.add(blockIndex);
					possibleBlock.add(pairBlockIndex);
				}
			}

			for(int i = 0; i < possibleRow.size(); i++) {
				if(pairInList(possibleRow.get(i).get(0), possibleRow) == 2) {
					// Naked Pair!!!!
					for(int j = 0; j < 2; j++) {
						for(int k = 0; k < 9; k++) {
							if(!(k == possibleRow.get(i).get(1).get(0)) || k == possibleRow.get(i).get(1).get(0)) {
							currentRow[k].removeOption(possibleRow.get(i).get(0).get(j));
							}
						}
					}
				}
			}

			for(int i = 0; i < possibleColumn.size(); i++) {
				if(pairInList(possibleColumn.get(i).get(0), possibleColumn) == 2) {
					// Naked Pair!!!!
					for(int j = 0; j < 2; j++) {
						for(int k = 0; k < 9; k++) {
							if(!(k == possibleColumn.get(i).get(1).get(0)) || k == possibleColumn.get(i).get(1).get(0)) {
							currentColumn[k].removeOption(possibleColumn.get(i).get(0).get(j));
							}						
						}
					}
				}
			}

			for(int i = 0; i < possibleBlock.size(); i++) {
				if(pairInList(possibleBlock.get(i).get(0), possibleBlock) == 2) {
					// Naked Pair!!!!
					for(int j = 0; j < 2; j++) {
						for(int k = 0; k < 9; k++) {
							if(!(k == possibleBlock.get(i).get(1).get(0)) || k == possibleBlock.get(i).get(1).get(0)) {
							currentBlock[k].removeOption(possibleBlock.get(i).get(0).get(j));
							}
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
*/
