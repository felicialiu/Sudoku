import java.util.*;
import java.io.*;

public class Permutation {
	// Returns a list of permutations of given numbers (at least 2)
	public static ArrayList<ArrayList<Integer>> permutate(ArrayList<Integer> list) {
		ArrayList<ArrayList<Integer>> permutations = new ArrayList<ArrayList<Integer>>();
		if(list.size() == 2) {
			permutations.add(list);
			return permutations;
		} else {
			for(int i = list.size() - 1; i > 0; i--) {
				for(int j = 0; j < i; j++) {
					ArrayList<Integer> permutation = new ArrayList<Integer>();
					permutation.add(list.get(i));
					permutation.add(list.get(j));
					permutations.add(permutation);
				}
			}
		}
	return permutations;
	}
}