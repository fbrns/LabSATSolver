package argumentation;

import argumentation.problem.Problem;
import argumentation.problem.ProblemCredulously;
import argumentation.problem.ProblemEnum;
import argumentation.problem.ProblemSkeptically;
import argumentation.problem.ProblemSome;
import argumentation.reasoner.ReasonerComplete;
import argumentation.reasoner.ReasonerGrounded;
import argumentation.reasoner.ReasonerPreferred;
import argumentation.reasoner.Reasoner;
import argumentation.reasoner.ReasonerStable;
import argumentation.support.Configuration;
import argumentation.support.Utilities;

public class Main {

	private Configuration configuration;

	public Main(Configuration configuration) {
		this.configuration = configuration;
	}

	public void start() {
		DungAF dungAF = new DungAF(configuration);

		Problem problem = getProblem(dungAF);
		Reasoner reasoner = getReasoner(dungAF);
		problem.solve(reasoner);
	}

	private Reasoner getReasoner(DungAF dungAF) {
		switch (configuration.getSemantic()) {
		case COMPLETE:
			return new ReasonerComplete(dungAF);
		case STABLE:
			return new ReasonerStable(dungAF);
		case PREFERRED:
			return new ReasonerPreferred(dungAF);
		case GROUNDED:
			return new ReasonerGrounded(dungAF);
		default:
			throw new RuntimeException("no semantic given ... giving up.");
		}
	}

	private Problem getProblem(DungAF dungAF) {
		switch (configuration.getProblem()) {
		case ENUMERATE:
			return new ProblemEnum(dungAF);
		case SOME:
			return new ProblemSome(dungAF);
		case SKEPTICALLY:
			return new ProblemSkeptically(dungAF,
					configuration.getSearchArgument());
		case CREDULOUSLY:
			return new ProblemCredulously(dungAF,
					configuration.getSearchArgument());
		default:
			throw new RuntimeException("no problem given ... giving up.");
		}
	}

	public static void main(String[] args) {		
		Configuration configuration = Utilities.parseArguments(args);
		Main main = new Main(configuration);
		main.start();
	}
}