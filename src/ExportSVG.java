import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;


public class ExportSVG {
	
	private Dotcloud dotcloud;
	private ArrayList<Vector<Double>> dots;
	
	public ExportSVG(Dotcloud dotcloud) {
		this.dotcloud = dotcloud;
		dots = dotcloud.getDots();
	}
	
	public void exportSVG(String filename, boolean tspEnabled) {
		
		Locale.setDefault(Locale.ENGLISH);
		DecimalFormat df = new DecimalFormat("#.###");
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
			append = append.replaceAll("%width", df.format(dotcloud.getDimX()));
			append = append.replaceAll("%height", df.format(dotcloud.getDimX()));
			append = append.replaceAll("%cornerx", df.format(0.0));
			append = append.replaceAll("%cornery", df.format(0.0));
			
			writer.append(append);
			System.out.println("Listsize: " + dotcloud.getDots().size());
			// Insert dots
			if (tspEnabled) { // Connected with lines
				append = "<polyline points=\"";
				
				for (int i = 0; i < dots.size(); i++, append += " ") {
					append += dots.get(i).get(0) + "," + dots.get(i).get(1) + "\n";
				}
				
				append += "\" style=\"fill:none;stroke:black;stroke-width:2\" />";
				
			} else { // Only dots
				
				for (int i = 0; i < dots.size(); i++) {
					append += "<line x1=" + dots.get(i).get(0)+ 
							" y1=" + dots.get(i).get(1) + 
							" x2=" + dots.get(i).get(0) + 
							" y2=" + dots.get(i).get(1) +
							" style=\"stroke:rgb(255,0,0);stroke-width:2\" /> \n";
				}
				
			}
			
			writer.append(append);
			
			
			writer.append(
					"\n" + 
					"</svg>");

			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
