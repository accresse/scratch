package org.cresse.scratch.mesh;

import java.util.LinkedList;
import java.util.List;

public class CombinationGenerator {
	
	public List<Combination> generateCombinations(List<String> values) {
		List<Combination> combinations = new LinkedList<>();
		
		for(int i=0; i<values.size(); i++) {
			for (int j = i+1; j < values.size(); j++) {
				String val1 = values.get(i);
				String val2 = values.get(j);
				List<String> remainder = new LinkedList<>(values);
				remainder.remove(val1);
				remainder.remove(val2);
				Combination comb = new Combination(val1,val2,remainder);
				combinations.add(comb);
			}
		}
		
		return combinations;
	}

}
