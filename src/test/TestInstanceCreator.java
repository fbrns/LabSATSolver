package test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestInstanceCreator {

	public static void makeRndTestInstance(int numberOfArguments,
			double propability) throws IOException {
		
		for (int nr=0; nr <=9; nr++) {
			List<String> line = new ArrayList<String>();
			boolean avoidSelfAttack = true;
			for (int i = 1; i <= numberOfArguments; i++) {
				line.add("arg(" + i + ").");
				for (int j = 1; j <= numberOfArguments; j++) {
					if (i == j && avoidSelfAttack) {
						continue;
					} else if (Math.random() < propability) {
						line.add("att(" + i + "," + j + ").");
					}
				}
			}
			Path exportFile = Paths.get("/export/rnd_"
					+ numberOfArguments + "_" + String.valueOf(propability)
					+ "_"+nr+".apx");
			Files.write(exportFile, line, Charset.forName("ISO-8859-1"));
		}
		
		
		
	}

	public static void main(String[] args) throws IOException {
		for (int i = 50; i <= 500; i = i + 50) {
			double[] props = new double[] { 0.2d, 0.4d};
			for (double prop : props) {
				makeRndTestInstance(i, prop);
			}
		}
	}
}
