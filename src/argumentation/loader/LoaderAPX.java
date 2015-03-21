package argumentation.loader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import argumentation.Argument;
import argumentation.Attack;

public class LoaderAPX extends Loader {
	
	public LoaderAPX(String filename) {
		super(filename);
	}
	
	@Override
	protected void parseLine(String line) {
		line = line.trim();
		if (line.startsWith("arg")) {
			final Pattern p = Pattern.compile("arg\\((\\w+)\\)\\.");
			final Matcher mp = p.matcher(line);
			if(mp.matches()) { 
				if (argumentCache.get(mp.group(1)) == null) {
					Argument argument = new Argument(mp.group(1));
					arguments.add(argument);
					argumentCache.put(argument.toString(), argument);
				} else {
					arguments.add(argumentCache.get(mp.group(1)));
				}
			} else {
				throw new RuntimeException("arg: no match found.");
			}
		} else if (line.startsWith("att")) {
			final Pattern p = Pattern.compile("att\\(\\s*(\\w+)\\s*,\\s*(\\w+)\\s*\\)\\.");
			final Matcher mp = p.matcher(line);
			if(mp.matches()) {
				String arg1Name = mp.group(1);
				String arg2Name = mp.group(2);
				Argument argument1;
				Argument argument2;
				if (argumentCache.get(arg1Name) == null) {
					argument1 = new Argument(arg1Name);
					argumentCache.put(argument1.toString(), argument1);
				} else {
					argument1 = argumentCache.get(arg1Name);
				}
				if (argumentCache.get(arg2Name) == null) {
					argument2 = new Argument(arg2Name);
					argumentCache.put(argument2.toString(), argument2);
				} else {
					argument2 = argumentCache.get(arg2Name);
				}
				attacks.add(new Attack(argument1,argument2));
			} else {
				throw new RuntimeException("att: no matches found.");
			}
		} else if (line.length() == 0 ) {
			// ignore empty line
		} else {
			throw new RuntimeException("line contains illegal pattern.");
		}
	}
}
