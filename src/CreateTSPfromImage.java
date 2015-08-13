import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class CreateTSPfromImage {
	
	
	public static void main(String[] args) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File("IMG_204.JPG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
