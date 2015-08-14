package tsp_ikm;


/**
 * Main class of the project
 * @author Lucia Blondel
 */

public class TSP {
	private static int[] path;

	
	public static int[] TSP (int[][] d) {
		//do simulated annealing
	
		SimulatedAnnealing annealing = new SimulatedAnnealing(d);
		annealing.simulatedAnnealing();
		
		path = annealing.getPath();
		return path;
		
	}
	
	
}