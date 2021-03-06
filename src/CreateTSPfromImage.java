import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CreateTSPfromImage {
	
	
	public static void main(String[] args) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File(args[0]));
			Dotcloud cl = new Dotcloud();
			cl.generateDotsFromImage(img, Integer.parseInt(args[1]));
			int compTime;
			try {
				compTime = Integer.parseInt(args[2]);
			} catch(NumberFormatException e) {
				compTime = 180;
			}
			cl.solveTSP(compTime);
			ExportSVG exp = new ExportSVG(cl);
			exp.exportSVG("out.svg", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
