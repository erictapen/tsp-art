package tsp_ikm;


/**
 * Main class of the project
 * @author Lucia Blondel
 */

public class TSP {
	private static int[] path;

	
	public static int[] TSP (int[][] d, int computingTimeInSec) {
		//do simulated annealing
	
		SimulatedAnnealing annealing = new SimulatedAnnealing(d, computingTimeInSec);
		annealing.simulatedAnnealing();
		
		path = annealing.getPath();
		return path;
		
	}
	
	
}