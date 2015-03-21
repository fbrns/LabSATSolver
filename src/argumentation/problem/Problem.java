package argumentation.problem;

import java.util.Set;

import argumentation.Argument;
import argumentation.DungAF;
import argumentation.reasoner.Reasoner;

import com.google.common.base.Joiner;

public abstract class Problem {

	protected DungAF dungAF;
	protected Reasoner reasoner;

	public Problem(DungAF dungAF) {
		this.dungAF = dungAF;
	}

	public abstract void solve(Reasoner reasoner);

	protected void printSet(Set<Argument> set) {
		System.out.print("[" + Joiner.on(",").skipNulls().join(set) + "]");
	}
}