import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Locale;


public class ExportSVG {
	
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
}
