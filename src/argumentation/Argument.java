package argumentation;

import java.util.ArrayList;
import java.util.List;

public class Argument {
	
	private DungAF dungAF;
	private String argument;
	private boolean isAttacked = false;	
	private List<Argument> attackers= new ArrayList<Argument>();
	private List<Argument> attacking= new ArrayList<Argument>();
	
	// members used with grounded extension
	private boolean in = false; 
	private boolean out = false;
	private boolean undec = false;
	private int attackersOut = 0; // number of attackers labelled out
	
	// members used with sat-solving
	private int position;
	
	
	public boolean areAllAttackersOUT() {
		return attackersOut == attackers.size();
	}
	
	public List<Argument> getAttackers(){
		return attackers;
	}
	
	public void addAttackers(Argument attacker) {
		attackers.add(attacker);
	}
	
	public void incAttackersOUT(){
		attackersOut++;
	}
	
	public boolean isIN(){
		return in;
	}
	
	public void setIN(boolean in){
		this.in = in;
	}
	
	public boolean isOUT(){
		return out;
	}
	
	public void setOUT(boolean out){
		this.out = out;
	}
	
	public boolean isUNDEC(){
		return undec;
	}
	
	public void setUNDEC(boolean undec){
		this.undec = undec;
	}
	
	public boolean isLabelled(){
		return in || out || undec;
	}

	public Argument(String argument) {
		this.argument = argument;
	}

	public String getArgument() {
		return argument;
	}

	public void setArgument(String argument) {
		this.argument = argument;
	}

	@Override
	public String toString() {
		return argument;
	}

	public int getIN() {
		return position;
	}
	
	public int getOUT() {
		return position + dungAF.getArgumentSize();
	}
	
	public int getUNDEC() {
		return position + (2 * dungAF.getArgumentSize());
	}
	
	public void addAttacking(Argument arg) {
		attacking.add(arg);
	}
	
	public List<Argument> getAttacking() {
		return attacking;
	}
	
	public void setIsAttacked(boolean isAttacked) {
		this.isAttacked = isAttacked;
	}

	public boolean isAttacked(){
		return isAttacked;
	}
	
	public void setSatPosition(int i){
		this.position = i;
	}
	
	public void setDungAF(DungAF dungAF){
		this.dungAF = dungAF;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((argument == null) ? 0 : argument.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Argument other = (Argument) obj;
		if (argument == null) {
			if (other.argument != null)
				return false;
		} else if (!argument.equals(other.argument))
			return false;
		return true;
	}
}
