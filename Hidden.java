import java.util.*;
import java.io.*;

public class Hidden{

	static void hiddenpair(Entry[] house){
		int[][] quantities = new int[9][2];
		ArrayList<Integer> currentOptions = new ArrayList<Integer>();
		ArrayList<Integer> collection = new ArrayList<Integer>();

		// This initializes numbers 1 through 9 in the array
		for(int x = 0; x < 9; x++){
			quantities[x][0] = x+1;
		}

		// This calculates the quantities in the specified house
		for(int i = 0; i < 9; i++){
			if(house[i].getValue() == 0){
				currentOptions = house[i].getOptions();
				for(int j = 0; j < currentOptions.size(); j++){
					quantities[currentOptions.get(j) - 1][1] += 1;
				}
			}
		}

		// hidden single
		for(int z = 0; z < 9; z++){
			if(quantities[z][1] == 1){
				for (int s = 0; s < 9; s++) {
					if(house[s].getValue() == 0){
						for (int d = 0; d < house[s].getOptions().size();d++) {
							if(quantities[z][0] == house[s].getOptions().get(d)){
								house[s].setValue(quantities[z][0]);
							}
						}
					}
				}
			}
		}

		// Hidden pairs
		int hpCount = 0;
		ArrayList<Integer> hpposition = new ArrayList<Integer>();
		ArrayList<Integer> hpoptions = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> pcollection = new ArrayList<ArrayList<Integer>>();
		for(int w = 0; w < 9; w++){
			if(quantities[w][1] == 2){
				collection.add(quantities[w][0]);
			}
		}
		System.out.println("collection requirement");
		if(collection.size() > 1){
			pcollection = Permutation.permutate(collection);
			System.out.println(pcollection.get(0).get(0));
			System.out.println(pcollection.get(0).get(1));
			for(int p = 0; p < 9; p++){
				for(int l = 0; l < pcollection.size(); l++){
					for(int y = 0; y < house[p].getOptions().size(); y++){
						if(house[p].getOptions().contains(pcollection.get(l).get(0)) && house[p].getOptions().contains(pcollection.get(l).get(1))){
							hpCount++;
							hpposition.add(y);
						}
					}
				}
			}
		}
		if(hpCount == 2){
			for (int t = 0; t < 2; t++){
				hpoptions = house[hpposition.get(t)].getOptions();
				for (int k = 0; k < hpoptions.size(); k++) {
					if(hpoptions.get(k) != pcollection.get(t).get(0) || hpoptions.get(k) != pcollection.get(t).get(1)){
						house[hpposition.get(t)].removeOption(hpoptions.get(k));
					}
				}
			}
		}
	}

}