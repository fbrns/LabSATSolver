package argumentation;

public class Attack {

	private Argument attacker;
	private Argument attacked;

	public Attack(Argument attacker, Argument attacked) {
		this.attacker = attacker;
		this.attacked = attacked;
	}

	public Argument getAttacker() {
		return attacker;
	}

	public Argument getAttacked() {
		return attacked;
	}
}
