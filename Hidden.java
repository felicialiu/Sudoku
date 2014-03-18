import java.util.*;
import java.io.*;

public class Hidden{

	private static Entry[] test = new Entry[9];

	public static void main(String[] args){
		for(int i = 0; i < 9; i++){
			Entry object = new Entry(0);
			test[i] = object;
		}
		test[0].setValue(7);
		for(int i  = 1; i < 10; i++){
			if(i != 2 && i != 3){
				test[1].removeOption(i);
			}
		}
		for(int i  = 1; i < 10; i++){
			if(i != 2 && i != 3 && i != 5){
				test[2].removeOption(i);
			}
		}
		for(int i  = 1; i < 10; i++){
			if(i != 1 && i != 2 && i != 5 && i!= 9){
				test[3].removeOption(i);
			}
		}
		test[4].setValue(8);
		test[5].setValue(4);
		for(int i  = 1; i < 10; i++){
			if(i != 1 && i != 5 && i != 9){
				test[6].removeOption(i);
			}
		}
		test[7].setValue(6);
		for(int i  = 1; i < 10; i++){
			if(i != 5 && i != 3){
				test[8].removeOption(i);
			}
		}
		hiddenPair(getFrequencies(test), test);
	}

	public static void hiddenCombi(Entry[] house, Board board){
		hiddenSingle(getFrequencies(house), house);
		Sudoku.initBoard(board);
		// hiddenPair(getFrequencies(house), house);
		// Sudoku.initBoard(board);
	}

	// Returns a table that contains the number of occurences for
	// each option in a house
	public static int[][] getFrequencies(Entry[] house){
		// This contains the frequenties of the numbers 1 through 9 in one house
		int[][] frequencyTable = new int[9][2];

		// This initializes numbers 1 through 9 in the array
		for(int x = 0; x < 9; x++){
			frequencyTable[x][0] = x+1;
		}

		// These are the options for one entry object
		ArrayList<Integer> currentOptions = new ArrayList<Integer>();

		// This calculates the frequencies in the specified house
		for(int i = 0; i < 9; i++){
			// If value is equal to zero, this object has options
			if(house[i].getValue() == 0){
				// Get the options from the cell at this index
				currentOptions = house[i].getOptions();
				// Loops through the cell's options and counts occurences 
				for(int j = 0; j < currentOptions.size(); j++){
					frequencyTable[currentOptions.get(j)-1][1]++;
				}
			}
		}
		return frequencyTable;
	}

	// Looks for Hidden Singles in a house
	public static void hiddenSingle(int[][] frequencyTable, Entry[] house){
		// Loop through all the frequenties
		for(int z = 0; z < 9; z++){
			if(frequencyTable[z][1] == 1){
				// Loop through the entire house if a frequency is 1
				for (int s = 0; s < 9; s++) {
					if(house[s].getValue() == 0){
						// If the object contains the specific hidden single, the value is set
						if(house[s].getOptions().contains(frequencyTable[z][0])){
							house[s].setValue(frequencyTable[z][0]);
							Sudoku.same = false;
						}
					}
				}
			}
		}
	}

	// Searches for Hidden Pairs in a house by looking if any of the pairs of 
	// all numbers are found twice in one house
	static void hiddenPair(int[][] frequencyTable, Entry[] house){
		// Hidden pair count
		int hpCount = 0;

		// This contains all the options that occur in a house
		ArrayList<Integer> collection = new ArrayList<Integer>();
		
		// Hidden pair position in the house
		ArrayList<Integer> hpPosition = new ArrayList<Integer>();

		// The options of the cell containing a hidden pair
		ArrayList<Integer> hpOptions = new ArrayList<Integer>();

		// The collection of possible number pair combinations
		ArrayList<ArrayList<Integer>> permutations = new ArrayList<ArrayList<Integer>>();

		// This checks which numbers have a frequency of 2 and adds these to collection
		for(int w = 0; w < 9; w++){
			if(frequencyTable[w][1] == 2){
				collection.add(frequencyTable[w][0]);
			}
		}

		//If the collection is bigger then 1 it means there are possible hidden pairs
		if(collection.size() > 1){
			permutations = Permutation.permutate(collection);
			
			// This loops through all of the permutations
			for(int l = 0; l < permutations.size(); l++){
				// This loops through one house
				for(int p = 0; p < 9; p++){
					// If both numbers of the permutation are in the specified house position, a pair is found
					if(house[p].getOptions().contains(permutations.get(l).get(0))
										&& house[p].getOptions().contains(permutations.get(l).get(1))){
						hpCount++;
						hpPosition.add(p);
					}
				}
				// This means the specific permutation is a hidden pair
				if(hpCount == 2){
					System.out.println("hidden pair found");
					// This goes through the 2 positions
					//for(int i = 0; i < pcollection.size(); i++){
					for (int t = 0; t < hpPosition.size(); t++){
						System.out.println("size = " + hpPosition.size());
						System.out.println("collection size = " + permutations.size());
						// These are the options of a specified house position
						hpOptions = house[hpPosition.get(t)].getOptions();
						// This loops through all the options of a specified house position
						for (int k = 0; k < hpOptions.size(); k++) {
							// If the option does not equal the pair, the option is removed
							if(permutations.get(l).get(0) != hpOptions.get(k) && permutations.get(l).get(1) != hpOptions.get(k)){
								house[hpPosition.get(t)].removeOption(hpOptions.get(k));
								System.out.println("remove "+hpOptions.get(k)+"from"+k);
								Sudoku.same = false;
							}
						}
					}
				} else {
					hpCount = 0;
					hpPosition.clear();
				}
			}
		}
	}
}