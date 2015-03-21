package argumentation.reasoner;

import java.util.HashSet;
import java.util.Set;

import argumentation.Argument;
import argumentation.DungAF;
import argumentation.pl.Disjunction;
import argumentation.sat.SATSolver;

public class ReasonerPreferred extends Reasoner {

	int iteration = 0;
	boolean emptySetSolution = false;

	Set<Argument> preferredCandidate = new HashSet<Argument>();

	public ReasonerPreferred(DungAF dungAF) {
		super(dungAF);
		semantic = Semantic.PREFERRED; 
		initializeSatSolver();
		configureSatSolverComplete();
		
		// At least one argument must be IN
		definitionLineNine();
	}

	@Override
	public boolean hasNext() {
		iteration++;

		preferredCandidate = new HashSet<Argument>();
		Set<Argument> lastCompleteFound = new HashSet<Argument>();

		SATSolver satSolverClone = satSolver.cloneSatSolver();
		do {
			Set<Argument> remaining = new HashSet<Argument>();
			lastCompleteFound = getINSet(satSolverClone, dungAF);

			if (lastCompleteFound.size() != 0) {
				preferredCandidate = lastCompleteFound;

				for (Argument arg : dungAF.getArguments()) {
					if (preferredCandidate.contains(arg)) {
						Disjunction disjunction = new Disjunction();
						disjunction.addPropositon(arg.getIN());
						satSolverClone.addClause(disjunction.toClause());
					} else {
						remaining.add(arg);
					}
				}
				
				if (!remaining.isEmpty()) {
					Disjunction disjunction = new Disjunction();
					for (Argument arg : remaining) {
						disjunction.addPropositon(arg.getIN());
					}
					satSolverClone.addClause(disjunction.toClause());
				}
			}
		} while (!(lastCompleteFound.size() == 0)
				&& (!(lastCompleteFound.size() == dungAF.getArgumentSize())));
		satSolverClone.release();

		if (preferredCandidate.size() == 0) {
			if (iteration == 1) {
				emptySetSolution = true;
				return true;
			} else {
				return false;
			}
		} else {
			Disjunction disjunction = new Disjunction();
			for (Argument argument : dungAF.getArguments()) {
				if (!preferredCandidate.contains(argument)) {
					disjunction.addPropositon(argument.getIN());
				}
			}
			satSolver.addClause(disjunction.toClause());
		}
		return true;
	}

	@Override
	public Set<Argument> next() {
		if (emptySetSolution) {
			return new HashSet<Argument>();
		}
		return preferredCandidate;
	}

	private Set<Argument> getINSet(SATSolver satSolver, DungAF dungAF) {
		Set<Argument> resultSet = new HashSet<Argument>();
		if (satSolver.solve()) {
			for (int i = 1; i <= dungAF.getArgumentSize(); i++) {
				if (satSolver.valueOf(i)) {
					resultSet.add(dungAF.getArgument(i));
				}
			}
		}
		return resultSet;
	}
}