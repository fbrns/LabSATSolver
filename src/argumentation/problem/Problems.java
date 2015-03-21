package argumentation.problem;

public enum Problems {
	ENUMERATE("EE"), SOME("SE"), CREDULOUSLY("DC"), SKEPTICALLY("DS");

	private String description;

	private Problems(String name) {
		this.description = name;
	}

	public String toString() {
		return description;
	}
}
