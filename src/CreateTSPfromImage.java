import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class CreateTSPfromImage {
	
	
	public static void main(String[] args) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File("IMG_2040.JPG"));
			Dotcloud cl = new Dotcloud();
			cl.generateDotsFromImage(img, 100);
			cl.solveTSP();
			ExportSVG exp = new ExportSVG(cl);
			exp.exportSVG("out.svg", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
