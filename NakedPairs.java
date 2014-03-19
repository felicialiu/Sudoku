import java.util.*;
import java.io.*;

public class NakedPairs {

	// Correctly initialises the possible options for all cells 
	public static void nakedPairs(Entry[] house, Board board) {	
		// Array that holds possible pairs and their location in a house.
		// Each element in the array is another array that contains a 
		// possible pair and the location of that pair 
		ArrayList<ArrayList<ArrayList<Integer>>> possiblePairs = new ArrayList<ArrayList<ArrayList<Integer>>>();			

		// Loop through each element in the 3 houses and add the options of
		// a cell with its index to the array containing all the options
		for(int i = 0; i < 9; i++) {
			if(house[i].getValue() == 0 && house[i].getOptions().size() == 2) {
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
				// Naked Pair
				// Get locations of that pair and save in array locations
				ArrayList<Integer> locations = getPairLocations(possiblePairs.get(i).get(0), possiblePairs);
				for(int k = 0; k < 9; k++) {
					if(!locations.contains(k)) {
						if (possiblePairs.get(i).get(0).size() == 2) {
							house[k].removeOption(possiblePairs.get(i).get(0).get(0));
							house[k].removeOption(possiblePairs.get(i).get(0).get(1));
							Sudoku.initBoard(board);
						}
					}
				}
			}
		}
	}

	// Returns a list with the two locations at which the naked pair can be found
	private static ArrayList<Integer> getPairLocations(ArrayList<Integer> pair, ArrayList<ArrayList<ArrayList<Integer>>> possiblePairs) {
		ArrayList<Integer> locations = new ArrayList<Integer>();
		for(int i = 0; i < possiblePairs.size(); i++) {
			if(equalPair(pair, possiblePairs.get(i).get(0)) && possiblePairs.get(i).get(0).size() == 2) {
				locations.add(possiblePairs.get(i).get(1).get(0));
			}
		}
		return locations;
	}
	

	// Returns the number of occurences of the integer array pair in the list
	// of arrays possiblePairs
	private static int pairInList(ArrayList<Integer> pair, ArrayList<ArrayList<ArrayList<Integer>>> possiblePairs) {
		int occurences = 0;
		for(int i = 0; i < possiblePairs.size(); i++) {
			if(equalPair(pair, possiblePairs.get(i).get(0))) {
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