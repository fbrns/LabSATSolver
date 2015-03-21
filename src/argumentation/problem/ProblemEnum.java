package argumentation.problem;

import argumentation.DungAF;
import argumentation.reasoner.Reasoner;

public class ProblemEnum extends Problem {

	public ProblemEnum(DungAF aaf) {
		super(aaf);
	}

	@Override
	public void solve(Reasoner reasoner) {
		if (reasoner.hasNext()) {
			System.out.print("[");
			boolean hasNext = false;
			do {
				printSet(reasoner.next());
				hasNext = reasoner.hasNext();
				if (hasNext) {
					System.out.print(",");
				}
			} while (hasNext);
			System.out.println("]");
		} else {
			System.out.println("NO");
		}
	}
}