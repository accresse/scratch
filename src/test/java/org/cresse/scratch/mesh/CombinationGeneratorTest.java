package org.cresse.scratch.mesh;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

public class CombinationGeneratorTest extends TestCase {
	
	public void testShouldGenerateCombinationsFor2Inputs() {
		CombinationGenerator target = new CombinationGenerator();
		List<Combination> combs = target.generateCombinations(Arrays.asList("1","2"));
		assertEquals(1, combs.size());
		assertCombination(combs.get(0), "1", "2");
	}

	public void testShouldGenerateCombinationsFor3Inputs() {
		CombinationGenerator target = new CombinationGenerator();
		List<Combination> combs = target.generateCombinations(Arrays.asList("1","2","3"));
		assertEquals(3, combs.size());
		assertCombination(combs.get(0), "1", "2", "3");
		assertCombination(combs.get(1), "1", "3", "2");
		assertCombination(combs.get(2), "2", "3", "1");
	}
	
	public void testShouldGenerateCombinationsFor4Inputs() {
		CombinationGenerator target = new CombinationGenerator();
		List<Combination> combs = target.generateCombinations(Arrays.asList("1","2","3","4"));
		assertEquals(6, combs.size());
		assertCombination(combs.get(0), "1", "2", "3", "4");
		assertCombination(combs.get(1), "1", "3", "2", "4");
		assertCombination(combs.get(2), "1", "4", "2", "3");
		assertCombination(combs.get(3), "2", "3", "1", "4");
		assertCombination(combs.get(4), "2", "4", "1", "3");
		assertCombination(combs.get(5), "3", "4", "1", "2");
	}
	
	private void assertCombination(Combination comb, String val1, String val2, String... unused) {
		assertEquals(val1, comb.getValue1());
		assertEquals(val2, comb.getValue2());
		List<String> unusedList = Arrays.asList(unused);
		assertEquals(unusedList, comb.getUnused());
	}

}
