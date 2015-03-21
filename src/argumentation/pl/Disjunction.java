package argumentation.pl;

import java.util.Stack;

public class Disjunction {
	
	private int[] elements;
	private Stack<Integer> stack = new Stack<Integer>();
	
	public void addPropositon(int variable) {
		addInt(variable);
	}

	public void addNegatedPropositon(int variable) {
		addInt(-variable);
	}
	
	private void addInt(int internalNumber) {
		if (elements != null) {
			throw new RuntimeException("disjunction already finalized, adding impossible.");
		} else {
			stack.add(internalNumber);
		}
	}
	
	public int[] toClause(){
		if (elements != null) {
			return elements;
		} else {
			elements = new int[stack.size()];
			int i = 0;
			while (!stack.isEmpty()) {
				elements[i] = stack.pop();
				i++;
			}
			return elements;
		}
	}
}
