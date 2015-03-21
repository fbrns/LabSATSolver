package argumentation.problem;

import argumentation.DungAF;
import argumentation.reasoner.Reasoner;

public class ProblemSome extends Problem {

	public ProblemSome(DungAF dungAF) {
		super(dungAF);
	}

	@Override
	public void solve(Reasoner reasoner) {
		if (reasoner.hasNext()) {
			printSet(reasoner.next());
			System.out.println();
		} else {
			System.out.println("NO");
		}
	}
}
