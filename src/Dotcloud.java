import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
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
	
	
	
	public void exportSVG(String filename, boolean tspEnabled) {
		
		Locale.setDefault(Locale.ENGLISH);
		DecimalFormat df = new DecimalFormat("#.########");
		try{
			FileWriter writer = new FileWriter(filename);
			String append = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
					"<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" "
					+ "\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n" + 
					"\n" + 
					"<svg xmlns=\"http://www.w3.org/2000/svg\"\n" + 
					"     xmlns:xlink=\"http://www.w3.org/1999/xlink\" "
					+ "xmlns:ev=\"http://www.w3.org/2001/xml-events\"\n" + 
					"     version=\"1.1\" baseProfile=\"full\"\n" + 
					"     width=\"%widthpx\" height=\"%heightpx\"\n" + 
					"     viewBox=\"%cornerx %cornery %width %height\">\n" + 
					"\n";
			append = append.replaceAll("%width", df.format(this.dimX));
			append = append.replaceAll("%height", df.format(this.dimY));
			append = append.replaceAll("%cornerx", df.format(0.0));
			append = append.replaceAll("%cornery", df.format(0.0));
			
			writer.append(append);
			
			writer.append(
					"\\n\" + \n" + 
					"					\"</svg>"
					);

			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void solveTSP(int iterations) {
		
	}
	
	
	
}
