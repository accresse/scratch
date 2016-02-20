package org.cresse.scratch.mesh;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class TheMeshSolver {
	
	private CombinationGenerator generator = new CombinationGenerator();
	
	public int solve(LinkedList<String> stack, int answer, String... tiles) {
		return solve(stack, answer, Arrays.asList(tiles));
	}
	
	private int solve(LinkedList<String> stack, int answer, List<String> tiles) {
		if(tiles.size()==1) {
			if(tiles.get(0).equals("x2")) {
				return -1;
			} else {
				return Integer.parseInt(tiles.get(0));
			}
		} else {
			List<Combination> combs = generator.generateCombinations(tiles);
			for (Combination comb : combs) {
				List<List<String>> nextSteps = getNextSteps(comb, answer);
				for (List<String> step : nextSteps) {
					int ret = solve(stack, answer, step);
					if(ret==answer) {
						String frame = String.format("%s -> (%s,%s)",tiles,comb.getValue1(),comb.getValue2());
						stack.push(frame);
						return ret;
					}
				}
			}
			return -1;
		}
	}
	
	private List<List<String>> getNextSteps(Combination comb, int answer) {
		List<List<String>> nextSteps = new LinkedList<>();

		List<String> unused = comb.getUnused();
		//value1 is always < value2 alphabetically
		String value1 = comb.getValue1();
		String value2 = comb.getValue2();
		
		if(value1.equals("x2")) {
			//both are x2 - illegal
		} else if(value2.equals("x2")) {
			addMultDivSteps(answer, nextSteps, unused, value1);
		} else {
			addAddSubSteps(answer, nextSteps, unused, value1, value2);
		}
		return nextSteps;
	}

	private void addMultDivSteps(int answer, List<List<String>> nextSteps, List<String> unused, String value1) {
		int val = Integer.parseInt(value1);
		
		List<String> mult = new LinkedList<String>(unused);
		int merged = val*2;
		if(merged!=answer || unused.isEmpty()) {
			mult.add(String.valueOf(merged));
		}
		nextSteps.add(mult);
		
		//can't do /2 with an odd tile
		if(val%2==0) {
			List<String> div = new LinkedList<String>(unused);
			merged = val/2;
			if(merged!=answer || unused.isEmpty()) {
				div.add(String.valueOf(merged));
			}
			nextSteps.add(div);
		}
	}

	private void addAddSubSteps(int answer, List<List<String>> nextSteps, List<String> unused, String value1, String value2) {
		int val1 = Integer.parseInt(value1);
		int val2 = Integer.parseInt(value2);
		
		List<String> add = new LinkedList<String>(unused);
		int merged = val1+val2;
		if((merged!=answer || unused.isEmpty()) && merged!=0) {
			add.add(String.valueOf(merged));
		}
		nextSteps.add(add);

		List<String> sub = new LinkedList<String>(unused);
		merged = Math.abs(val1-val2);
		if((merged!=answer || unused.isEmpty()) && merged!=0) {
			sub.add(String.valueOf(merged));
		}
		nextSteps.add(sub);
	}

	public static void main(String[] args) {
		TheMeshSolver solver=new TheMeshSolver();
		LinkedList<String> stack = new LinkedList<String>();
		solver.solve(stack, 1, "2","8","7","x2","5","5");
		for (String frame : stack) {
			System.out.println(frame);
		}
	}

}
