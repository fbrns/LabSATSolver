package argumentation.reasoner;

import argumentation.Argument;
import argumentation.DungAF;
import argumentation.pl.Disjunction;

public class ReasonerStable extends Reasoner {

	public ReasonerStable(DungAF dungAF) {
		super(dungAF);
		semantic = Semantic.STABLE; 
		initializeSatSolver();
		configureSatSolverComplete();
		
		// stable: no UNDEC arguments allowed
		for (Argument arg : dungAF.getArguments()) {
			Disjunction disjunction = new Disjunction();
			disjunction.addNegatedPropositon(arg.getUNDEC());
			satSolver.addClause(disjunction.toClause());
		}
	}
}
