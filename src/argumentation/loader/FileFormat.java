package argumentation.loader;

public enum FileFormat {
	APX("apx");

	private String description;

	FileFormat(String description) {
		this.description = description;
	}

	public String toString() {
		return description;
	}
}