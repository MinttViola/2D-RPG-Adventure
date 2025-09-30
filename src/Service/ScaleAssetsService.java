package Service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ScaleAssetsService {

	public static  BufferedImage ScaleImage(int width, int height, BufferedImage originalImg){
		BufferedImage scaledImg = new BufferedImage(width, height,originalImg.getType());
		Graphics2D g2 = scaledImg.createGraphics();
		g2.drawImage(originalImg,0,0,width,height,null); 
		g2.dispose();
		return scaledImg;
	}

}
