package argumentation.problem;

import argumentation.Argument;
import argumentation.DungAF;
import argumentation.reasoner.Semantic;
import argumentation.reasoner.Reasoner;
import argumentation.reasoner.ReasonerGrounded;

public class ProblemSkeptically extends Problem{
	
	private Argument searchArgument;

	public ProblemSkeptically(DungAF dungAF, String searchArgument) {
		super(dungAF);
		this.searchArgument = dungAF.findArgumentByName(searchArgument);
	}

	@Override
	public void solve(Reasoner reasoner) {
		if(isSkepticallyInferred(reasoner, searchArgument)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
	
	private boolean isSkepticallyInferred(Reasoner reasoner, Argument arg) {
		
		if (reasoner.getSemantic() == Semantic.COMPLETE) {
			// use grounded semantic to calculate DS-CO  
			reasoner = new ReasonerGrounded(dungAF);
		}
		
		while (reasoner.hasNext()) {
			if (!reasoner.next().contains(arg)) {
				return false;
			}
		}
		return true;
	}
}
