package argumentation.sat;

import java.util.HashMap;
import java.util.Map;

public class Lingeling implements SATSolver{

	private final static int SATISFIABLE = 10;

	private int varSize;
	private long lgl;
	private Map<Integer, Boolean> cache = new HashMap<Integer, Boolean>();
	
	static {
		System.loadLibrary("Lingeling");
	}
	
	private native void lgladd(long lgl, int lit);
	
	private native long lglclone(long lgl);
	
	private native int lglderef(long lgl, int lit);
	
	private native void lglfreeze(long lgl, int lit);
	
	private native long lglinit();
	
	private native void lglrelease(long lgl);
	
	private native int lglsat(long lgl);
	
	public Lingeling() {
		lgl = lglinit();
	}
	
	private Lingeling(long lgl, int variables, Map<Integer, Boolean> cache) {
		this.lgl = lgl;
		this.cache = new HashMap<Integer, Boolean>();
		this.varSize = variables;
	}
	
	public boolean solve() {
		cache.clear();
		return (lglsat(lgl) == SATISFIABLE);
	}

	public void addClause(int[] clause) {
		for (int lit : clause) {
			lgladd(lgl, lit);
			lglfreeze(lgl, lit);
		}
		lgladd(lgl, 0);
	}
	
	public void addVariables(int size) {
		varSize = size;
	}

	public boolean valueOf(int i) {
		if (cache.get(i) == null) {
			if (i <= varSize) {
				cache.put(i, lglderef(lgl, i) > 0);
			} else {
				throw new RuntimeException("index out of bounds.");
			}
		}
		return cache.get(i);
	}

	public SATSolver cloneSatSolver() {
		return new Lingeling(lglclone(lgl), varSize, cache);
	}

	@Override
	public void release() {
		lglrelease(lgl);
	}
}
