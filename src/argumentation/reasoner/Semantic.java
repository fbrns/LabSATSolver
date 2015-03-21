package argumentation.reasoner;

public enum Semantic {
	COMPLETE("CO"), PREFERRED("PR"), STABLE("ST"), GROUNDED("GR");
	
	private String description;
	
	private Semantic(String name) {
		this.description = name;
	}
	
	public String toString() {
		return description;
	}
	
}
