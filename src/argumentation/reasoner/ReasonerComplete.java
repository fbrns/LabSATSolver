package argumentation.reasoner;

import argumentation.DungAF;

public class ReasonerComplete extends Reasoner {
	
	public ReasonerComplete(DungAF dungAF) {
		super(dungAF);
		semantic = Semantic.COMPLETE; 
		initializeSatSolver();
		configureSatSolverComplete();
	}
}
