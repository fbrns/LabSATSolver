package argumentation;

import java.util.ArrayList;
import java.util.List;

import argumentation.loader.FileFormat;
import argumentation.loader.Loader;
import argumentation.loader.LoaderAPX;
import argumentation.support.Configuration;

public class DungAF {
	
	private List<Argument> arguments = new ArrayList<Argument>();
	private List<Attack> attacks = new ArrayList<Attack>();
	
	public DungAF(Configuration configuration) {
		if (configuration.getFileFormat() == FileFormat.APX) {
			loadAF(new LoaderAPX(configuration.getFilename()));
		} else {
			throw new RuntimeException("no valid fileformat given");
		}
		initializeAF();
	}
	
	private void initializeAF() {
		for (Attack attack : attacks) {
			Argument attacker = attack.getAttacker();
			Argument attacked = attack.getAttacked();
			attacker.addAttacking(attacked);
			attacked.setIsAttacked(true);
			attacked.addAttackers(attacker);
		}
		
		for (int i=1; i<= arguments.size(); i++) {
			arguments.get(i-1).setDungAF(this);
			arguments.get(i-1).setSatPosition(i);
		}
	}

	private void loadAF(Loader loader) {
		this.arguments = loader.getArguments();
		this.attacks = loader.getAttacks();
	}
	
	public List<Argument> getArguments() {
		return arguments;
	}
	
	public int getArgumentSize(){
		return arguments.size();
	}
	
	public Argument getArgument(int i){
		return arguments.get(i-1);
	}
	
	public Argument findArgumentByName(String searchArgument) {
		for (Argument arg : arguments){
			if (arg.getArgument().equals(searchArgument)){
				return arg;
			}
		}
		throw new RuntimeException("searchargument not in dungAF.");
	}
}
