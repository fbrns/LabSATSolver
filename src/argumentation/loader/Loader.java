package argumentation.loader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import argumentation.Argument;
import argumentation.Attack;

public abstract class Loader {

	List<Argument> arguments = new ArrayList<Argument>();
	List<Attack> attacks = new ArrayList<Attack>();
	
	Map<String,Argument> argumentCache = new HashMap<String, Argument>();

	public Loader(String filename) {
		parseLines(filename);
	}

	List<String> getLines(String filename) {
		Path path = FileSystems.getDefault().getPath(filename);
		try {
			List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
			return lines;
		} catch (IOException e) {
			throw new RuntimeException("File not found.");
		}
	}
	
	protected void parseLines(String filename) {
		for (String line : getLines(filename)) {
			parseLine(line);
		}
	}

	protected abstract void parseLine(String line);
	
	public List<Argument> getArguments() {
		return arguments;
	}

	public List<Attack> getAttacks() {
		return attacks;
	}

}
