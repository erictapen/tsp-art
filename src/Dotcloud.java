import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;


public class Dotcloud {
	private ArrayList<Vector<Double>> dots = new ArrayList<Vector<Double>>();
	private double dimX = 1024.0;
	private double dimY = 1024.0;
	
	
	public Dotcloud() {
		
	}

	public ArrayList<Vector<Double>> getDots() {
		return dots;
	}

	public void addDots(ArrayList<Vector<Double>> addDots) {
		this.dots.addAll(addDots);
	}
	
	
	public void generateDotsFromImage(BufferedImage img, int amount) {
		this.dimX = img.getWidth();
		this.dimY = img.getHeight();
		int[][] brightness = new int[img.getWidth()][img.getHeight()];
		for(int i=0; i<img.getWidth(); i++) {
			for(int j=0; j<img.getHeight(); j++) {
				int hexColor = img.getRGB(i, j);
				brightness[i][j] = (int)(	((hexColor >> 16)
											+ ((hexColor & 0x00ff00) >> 8)
											+ (hexColor & 0x0000ff))
										*(1.0/3.0));
			}
		}
		while(amount > 0) {
			Vector<Double> dot = new Vector<Double>(2);
			dot.add(0, Math.random()*this.dimX);
			dot.add(1, Math.random()*this.dimY);
			if(Math.random()*brightness[dot.get(0).intValue()][(int)dot.get(1).intValue()] > 1.0) {
				this.dots.add(dot);
				amount--;
			}
		}	
	}
	
	public void solveTSP() {
		//build adjacency matrix
		int factor = 100; //constant factor, because NearestNeighbour seems to accept only discrete values
		int[][] amatrix = new int[this.dots.size()][this.dots.size()];
		for(int i=0; i<this.dots.size(); i++) {
			for(int j=0; j<this.dots.size(); j++) {
				double dx = this.dots.get(i).get(0) - this.dots.get(j).get(0);
				double dy = this.dots.get(i).get(1) - this.dots.get(j).get(1);
				amatrix[i][j] = ((int) Math.sqrt(dx*dx + dy*dy))*factor;
			}
		}
			
		
		TSPNearestNeighbour solver = new TSPNearestNeighbour();
		solver.tsp(amatrix);
		ArrayList<Vector<Double>> res = new ArrayList<Vector<Double>>();
		while(!solver.getStack().isEmpty()) {
			res.add(this.dots.get(solver.getStack().pop()));
		}
		this.dots = res;
	}
	
	public double getDimX() {
		return dimX;
	}

	public double getDimY() {
		return dimY;
	}
	
	
}
