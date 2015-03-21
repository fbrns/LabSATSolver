package argumentation.reasoner;

import java.util.HashSet;
import java.util.Set;

import argumentation.Argument;
import argumentation.DungAF;

public class ReasonerGrounded extends Reasoner {

	private boolean used = false;
	private Set<Argument> resultSet = new HashSet<Argument>();

	public ReasonerGrounded(DungAF dungAF) {
		super(dungAF);
		semantic = Semantic.GROUNDED; 
	}

	@Override
	public boolean hasNext() {
		if (used) {
			return false;
		} else {
			used = true;
			Set<Argument> unlabelledArguments = new HashSet<Argument>();
			for (Argument arg : dungAF.getArguments()) {
				if (!arg.isAttacked()) {
					arg.setIN(true);
				} else {
					unlabelledArguments.add(arg);
				}
			}
			if (unlabelledArguments.size() == dungAF.getArguments().size()) {
				return true;
			}
			boolean changed;
			do {
				changed = false;
				Set<Argument> excludeArguments = new HashSet<Argument>();

				for (Argument arg : unlabelledArguments) {
					for (Argument attacker : arg.getAttackers()) {
						if (!arg.isLabelled() && attacker.isIN()) {
							arg.setOUT(true);
							excludeArguments.add(arg);
							for (Argument toBeNotified : arg.getAttacking()) {
								toBeNotified.incAttackersOUT();
							}
							changed = true;
						}
					}
				}
				unlabelledArguments.removeAll(excludeArguments);
				excludeArguments.clear();
				
				for (Argument arg : unlabelledArguments) {
					if (arg.areAllAttackersOUT()) {
						arg.setIN(true);
						excludeArguments.add(arg);
						changed = true;
					}
				}
				unlabelledArguments.removeAll(excludeArguments);
			} while (changed);

			for (Argument arg : dungAF.getArguments()) {
				if (arg.isIN()) {
					resultSet.add(arg);
				}
			}
			return true;
		}
	}

	@Override
	public Set<Argument> next() {
		return resultSet;
	}
	
	@Override
	public void addSearchArgument(Argument arg) {
		// nothing to do here
	}
}
