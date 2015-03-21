package argumentation.support;

public class TerminatorDaemon implements Runnable {

	private long lifeTime; // seconds

	public TerminatorDaemon(long lifeTime) {
		this.lifeTime = lifeTime * 1000;
	}

	@Override
	public void run() {

		long startTime = System.currentTimeMillis();
		long endTime = startTime + lifeTime;

		while (System.currentTimeMillis() < endTime){
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				//
			}
		}

		System.err.println("timelimit of " + (lifeTime / 1000)
				+ "secs reached.");
		System.exit(1);
	}
}
