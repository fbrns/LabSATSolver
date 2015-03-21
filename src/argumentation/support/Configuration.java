package argumentation.support;
import argumentation.loader.FileFormat;
import argumentation.problem.Problems;
import argumentation.reasoner.Semantic;

public class Configuration {
	
	private Semantic extension;
	private Problems problem;
	private FileFormat fileFormat;;
	private String filename;
	private String searchArgument;

	public Semantic getSemantic() {
		return extension;
	}

	public void setSemantic(Semantic extension) {
		this.extension = extension;
	}

	public FileFormat getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(FileFormat fileFormat) {
		this.fileFormat = fileFormat;
	}

	public Problems getProblem() {
		return problem;
	}

	public void setProblem(Problems problem) {
		this.problem = problem;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getSearchArgument() {
		return searchArgument;
	}

	public void setSearchArgument(String searchArgument) {
		this.searchArgument = searchArgument;
	}

}
