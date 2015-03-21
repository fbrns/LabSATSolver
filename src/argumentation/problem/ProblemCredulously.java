package argumentation.problem;

import argumentation.Argument;
import argumentation.DungAF;
import argumentation.reasoner.Reasoner;

public class ProblemCredulously extends Problem {

	private Argument searchArgument;

	public ProblemCredulously(DungAF dungAF, String searchArgument) {
		super(dungAF);
		this.searchArgument = dungAF.findArgumentByName(searchArgument);
	}

	@Override
	public void solve(Reasoner reasoner) {
		if (isCredudouslyInferred(reasoner, searchArgument)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	private boolean isCredudouslyInferred(Reasoner reasoner, Argument arg) {
		reasoner.addSearchArgument(arg);
		while (reasoner.hasNext()) {
			if (reasoner.next().contains(arg)) {
				return true;
			}
		}
		return false;
	}
	
}