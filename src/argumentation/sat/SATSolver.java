package argumentation.sat;

public interface SATSolver {
	
	void addClause(int[] clause);

	void addVariables(int size);
	
	SATSolver cloneSatSolver();

	void release();

	boolean solve();

	boolean valueOf(int i);
}