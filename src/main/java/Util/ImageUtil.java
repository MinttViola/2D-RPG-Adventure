package Util;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageUtil {

	public static BufferedImage TransperentImg(BufferedImage originalImg){
		BufferedImage transparentImg = new BufferedImage(
		originalImg.getWidth(),
		originalImg.getHeight(),
		BufferedImage.TYPE_INT_ARGB
		);
		for (int y = 0; y < originalImg.getHeight(); y++) {
			for (int x = 0; x < originalImg.getWidth(); x++) {
					Color color = new Color(originalImg.getRGB(x, y), true);
					
					Color newColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), 10);
					transparentImg.setRGB(x, y, newColor.getRGB());
			}
		}
		return transparentImg;
	}
	public static BufferedImage HorizontalFlip(BufferedImage img){
		//flip img y 
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-img.getHeight(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		img = op.filter(img, null);
		return img;
	}

	
	public static BufferedImage getMainImage(String path){
		WorkWithFilesUtil fileUtil = new WorkWithFilesUtil();
		BufferedImage image;
		try{
			URL file = fileUtil.get(path);
			image = ImageIO.read(file);
			return image;
		} catch(IOException e){
				e.printStackTrace();
				return null;
			}
	}
}
