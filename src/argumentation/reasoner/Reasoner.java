package argumentation.reasoner;

import java.util.HashSet;
import java.util.Set;

import argumentation.Argument;
import argumentation.DungAF;
import argumentation.pl.Disjunction;
import argumentation.sat.Lingeling;
import argumentation.sat.SATSolver;

public abstract class Reasoner {
	
	protected DungAF dungAF;
	protected SATSolver satSolver;
	protected Semantic semantic;

	public Reasoner(DungAF dungAF) {
		this.dungAF = dungAF;
	}

	protected void initializeSatSolver() {
		satSolver = new Lingeling();
		satSolver.addVariables(dungAF.getArgumentSize() * 3);
	}

	protected void definitionLineOne() {
		for (Argument arg : dungAF.getArguments()) {
			// part one
			Disjunction partOne = new Disjunction();
			partOne.addPropositon(arg.getIN());
			partOne.addPropositon(arg.getOUT());
			partOne.addPropositon(arg.getUNDEC());
			// part two
			Disjunction partTwo = new Disjunction();
			partTwo.addNegatedPropositon(arg.getIN());
			partTwo.addNegatedPropositon(arg.getOUT());
			// part three
			Disjunction partThree = new Disjunction();
			partThree.addNegatedPropositon(arg.getIN());
			partThree.addNegatedPropositon(arg.getUNDEC());
			// part four
			Disjunction partFour = new Disjunction();
			partFour.addNegatedPropositon(arg.getOUT());
			partFour.addNegatedPropositon(arg.getUNDEC());
			// fill the solver
			satSolver.addClause(partOne.toClause());
			satSolver.addClause(partTwo.toClause());
			satSolver.addClause(partThree.toClause());
			satSolver.addClause(partFour.toClause());
		}
	}

	protected void definitionLineTwo() {
		for (Argument arg : dungAF.getArguments()) {
			if (!arg.isAttacked()) {
				Disjunction partOne = new Disjunction();
				partOne.addPropositon(arg.getIN());
				Disjunction partTwo = new Disjunction();
				partTwo.addNegatedPropositon(arg.getOUT());
				Disjunction partThree = new Disjunction();
				partThree.addNegatedPropositon(arg.getUNDEC());
				satSolver.addClause(partOne.toClause());
				satSolver.addClause(partTwo.toClause());
				satSolver.addClause(partThree.toClause());
			}
		}
	}

	protected void definitionFiveLineThree() {
		for (Argument arg : dungAF.getArguments()) {
			if (arg.isAttacked()) {
				Disjunction disjunction = new Disjunction();
				disjunction.addPropositon(arg.getIN());
				for (Argument attacker : arg.getAttackers()) {
					disjunction.addNegatedPropositon(attacker.getOUT());
				}
				satSolver.addClause(disjunction.toClause());
			}
		}
	}
	
	protected void definitionLineFour() {
		for (Argument arg : dungAF.getArguments()) {
			if (arg.isAttacked()) {
				for (Argument attacker : arg.getAttackers()) {
					Disjunction disjunction = new Disjunction();
					disjunction.addNegatedPropositon(arg.getIN());
					disjunction.addPropositon(attacker.getOUT());
					satSolver.addClause(disjunction.toClause());
				}
			}
		}
	}
	

	protected void definitionFiveLineFive() {
		for (Argument arg : dungAF.getArguments()) {
			if (arg.isAttacked()) {
				for (Argument attacker : arg.getAttackers()) {
					Disjunction disjunction = new Disjunction();
					disjunction.addNegatedPropositon(attacker.getIN());
					disjunction.addPropositon(arg.getOUT());
					satSolver.addClause(disjunction.toClause());
				}
			}
		}
	}
	protected void definitionLineSix() {
		for (Argument arg : dungAF.getArguments()) {
			if (arg.isAttacked()) {
				Disjunction disjunction = new Disjunction();
				disjunction.addNegatedPropositon(arg.getOUT());
				for (Argument attacker : arg.getAttackers()) {
					disjunction.addPropositon(attacker.getIN());
				}
				satSolver.addClause(disjunction.toClause());
			}
		}
	}
	
	protected void definitionFiveLineSeven() {
		for (Argument arg : dungAF.getArguments()) {
			if (arg.isAttacked()) {
				for (Argument attacker : arg.getAttackers()) {
					Disjunction disjunction = new Disjunction();
					disjunction.addPropositon(arg.getUNDEC());
					disjunction.addNegatedPropositon(attacker.getUNDEC());
					for (Argument attacker2 : arg.getAttackers()) {
						disjunction.addPropositon(attacker2.getIN());
					}
					satSolver.addClause(disjunction.toClause());
				}
			}
		}
	}
	
	protected void definitionLineEight() {
		for (Argument arg : dungAF.getArguments()) {
			if (arg.isAttacked()) {
				for (Argument attacker : arg.getAttackers()) {
					Disjunction disjunction = new Disjunction();
					disjunction.addNegatedPropositon(arg.getUNDEC());
					disjunction.addNegatedPropositon(attacker.getIN());
					satSolver.addClause(disjunction.toClause());
				}
				Disjunction disjunction = new Disjunction();
				disjunction.addNegatedPropositon(arg.getUNDEC());
				for (Argument attacker : arg.getAttackers()) {
					disjunction.addPropositon(attacker.getUNDEC());
				}
				satSolver.addClause(disjunction.toClause());
			}
		}
	}

	protected void definitionLineNine() {
		Disjunction disjunction = new Disjunction();
		for (Argument arg : dungAF.getArguments()) {
			disjunction.addPropositon(arg.getIN());
		}
		satSolver.addClause(disjunction.toClause());
	}

	protected void configureSatSolverComplete() {
		definitionLineOne();
		definitionLineTwo();
		definitionLineFour();
		definitionLineSix();
		definitionLineEight();
	}
	
	public void addSearchArgument(Argument arg) {
		Disjunction disjunction = new Disjunction();
		disjunction.addPropositon(arg.getIN());
		satSolver.addClause(disjunction.toClause());
	}

	public boolean hasNext() {
		return satSolver.solve();
	}

	public Set<Argument> next() {
		Set<Argument> resultSet = new HashSet<Argument>();
		Disjunction excludeDisjunction = new Disjunction();
		for (int i = 1; i <= dungAF.getArgumentSize(); i++) {
			if (satSolver.valueOf(i)) {
				// add all arguments that are IN to resultSet
				resultSet.add(dungAF.getArgument(i));
				excludeDisjunction.addNegatedPropositon(i);
			} else {
				excludeDisjunction.addPropositon(i);
			}
		}
		satSolver.addClause(excludeDisjunction.toClause());
		return resultSet;
	}
	
	public Semantic getSemantic() {
		return semantic;
	}
	
}