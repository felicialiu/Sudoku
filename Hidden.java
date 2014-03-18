import java.util.*;
import java.io.*;

public class Hidden{

	static void hiddenCombi(Entry[] house, Board board){
		hiddenSingle(frequentie(house), house);
		hiddenPair(frequentie(house), house);
	}

	public static int[][] frequentie(Entry[] house){

		// This contains the frequentie of the numbers 1 through 9 in one house
		int[][] frequenties = new int[9][2];

		// These are the options for one entry object
		ArrayList<Integer> currentOptions = new ArrayList<Integer>();

		// This initializes numbers 1 through 9 in the array
		for(int x = 0; x < 9; x++){
			frequenties[x][0] = x+1;
		}

		// This calculates the frequenties in the specified house
		for(int i = 0; i < 9; i++){
			// If value is equal to zero, this object has options
			if(house[i].getValue() == 0){
				// Get the options from the cell at this index
				currentOptions = house[i].getOptions();
				// Loops through the cells options and adds the frequenties 
				for(int j = 0; j < currentOptions.size(); j++){
					frequenties[currentOptions.get(j)-1][1]++;
				}
			}
		}
		return frequenties;
	}

	public static void hiddenSingle(int[][] frequenties, Entry[] house){

		// Loop through all the frequenties
		for(int z = 0; z < 9; z++){
			if(frequenties[z][1] == 1){
				// Loop through the entire house if a frequentie is 1
				for (int s = 0; s < 9; s++) {
					if(house[s].getValue() == 0){
						// If the object contains the specific hidden single, the value is set
						if(house[s].getOptions().contains(frequenties[z][0])){
							house[s].setValue(frequenties[z][0]);
							Sudoku.same = false;
						}
					}
				}
			}
		}

	}

	static void hiddenPair(int[][] frequenties, Entry[] house){

		// hidden pair count
		int hpCount = 0;

		// This contains the possible hidden pairs
		ArrayList<Integer> collection = new ArrayList<Integer>();
		
		// Hidden pair position in the house
		ArrayList<Integer> hpposition = new ArrayList<Integer>();

		// The options of a hidden pair in the house
		ArrayList<Integer> hpoptions = new ArrayList<Integer>();

		// The collection of permutated hidden pairs
		ArrayList<ArrayList<Integer>> pcollection = new ArrayList<ArrayList<Integer>>();

		// This checks which numbers have a frequentie of 2 and adds these to collection
		for(int w = 0; w < 9; w++){
			if(frequenties[w][1] == 2){
				collection.add(frequenties[w][0]);
			}
		}

		//If the collection is bigger then 1 it means there are possible hidden pairs
		if(collection.size() > 1){
			pcollection = Permutation.permutate(collection);
			// This loops through one house

			// This loops through all of the permutations
			for(int l = 0; l < pcollection.size(); l++){
				for(int p = 0; p < 9; p++){
					// If both numbers of the permutation are in the specified house position, a pair is found
					if(house[p].getOptions().contains(pcollection.get(l).get(0))
										&& house[p].getOptions().contains(pcollection.get(l).get(1))){
						hpCount++;
						hpposition.add(p);
					}
				}
				// This means the specific permutation is a hidden pair
				if(hpCount == 2){
					break;
				}else{
					hpCount = 0;
					hpposition.clear();
				}
			}
		}

		// If there are 2 pairs there is a hidden pair
		if(hpCount == 2){
			System.out.println("hidden pair found");
			// This goes through the 2 positions
			//for(int i = 0; i < pcollection.size(); i++){
				for (int t = 0; t < hpposition.size(); t++){
					System.out.println("size = "+hpposition.size());
					System.out.println("collection size = "+pcollection.size());
					// These are the options of a specified house position
					hpoptions = house[hpposition.get(t)].getOptions();
					// This loops through all the options of a specified house position
					for (int k = 0; k < hpoptions.size(); k++) {
							// If the option does not equal the pair, the option is removed
							if(pcollection.get(0).get(0) != hpoptions.get(k) || pcollection.get(0).get(1) != hpoptions.get(k)){
								house[hpposition.get(t)].removeOption(hpoptions.get(k));
								Sudoku.same = false;
							}
					}
				}
			//}
		}

	}

}