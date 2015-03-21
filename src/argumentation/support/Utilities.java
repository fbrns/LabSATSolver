package argumentation.support;

import java.util.ArrayList;
import java.util.List;

import argumentation.loader.FileFormat;
import argumentation.problem.Problems;
import argumentation.reasoner.Semantic;

import com.google.common.base.Joiner;

public class Utilities {
	
	public static Configuration parseArguments(String[] args) {

		if (args.length == 0) {
			// no arguments given
			System.out.println("LabSATSolver 0.1");
			System.out.println("Florian Brons");
			System.exit(0);
		}

		for (int i = 0; i < args.length; i++) {
			if (args[i].toLowerCase().startsWith("--problems")) {
				System.out.println(getProblems());
				System.exit(0);
			}
			if (args[i].toLowerCase().startsWith("--formats")) {
				System.out.println(getFormats());
				System.exit(0);
			}
		}
		
		Configuration configuration = new Configuration();
		
		for (int i = 0; i < args.length; i++) {
			// problem
			String argument = args[i];
			if (argument.equals("-p")) {
				String[] parts = args[i+1].split("-");
				String problem = parts[0].toUpperCase();
				String extension = parts[1].toUpperCase();

				if (problem.equals("DC")) {
					configuration.setProblem(Problems.CREDULOUSLY);
				} else if (problem.equals("DS")) {
					configuration.setProblem(Problems.SKEPTICALLY);
				} else if (problem.equals("EE")) {
					configuration.setProblem(Problems.ENUMERATE);
				} else if (problem.equals("SE")) {
					configuration.setProblem(Problems.SOME);
				}
				if (extension.equals("CO")) {
					configuration.setSemantic(Semantic.COMPLETE);
				} else if (extension.equals("GR")) {
					configuration.setSemantic(Semantic.GROUNDED);
				} else if (extension.equals("PR")) {
					configuration.setSemantic(Semantic.PREFERRED);
				} else if (extension.equals("ST")) {
					configuration.setSemantic(Semantic.STABLE);
				}
			}
			// file
			if (args[i].equals("-f")) {
				configuration.setFilename(args[i+1]);
			}
			// fileformat
			if (args[i].equals("-fo")) {
				if (args[i+1].toUpperCase().equals("APX")){
					configuration.setFileFormat(FileFormat.APX);
				}
			}
			// argument (cred/skep)
			if (args[i].equals("-a")) {
				configuration.setSearchArgument(args[i+1]);
			}
		}
		return configuration;
	}

	private static String getProblems() {
		List<String> possibleCombinations = new ArrayList<String>();
		for (Problems problem : Problems.values()) {
			for (Semantic semantic : Semantic.values()) {
				possibleCombinations.add(problem.toString() + "-" + semantic.toString());
			}
		}
		return "["+Joiner.on(",").join(possibleCombinations)+"]";
	}

	private static String getFormats() {
		return "["+Joiner.on(",").join(FileFormat.values())+"]";
	}
}
