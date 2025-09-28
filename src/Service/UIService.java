package Service;

import Main.GamePanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class UIService {
	GamePanel gp;
	Font arial_40;
	BufferedImage keyImage;
	boolean massageVisible = false;
	String massage = "";
	int indent = 10;
	int massageCountdown = 0;

	public UIService(GamePanel gp){
		this.gp = gp;
		arial_40 = new Font("Arial",Font.PLAIN,40);
		keyImage = getImage("/Assets/UI/key_ui.png");
	}

	public void showMassage(String text){
		massage = text;
		massageVisible = true;
	}

	public void draw(Graphics2D g2){
		g2.setFont(arial_40);
		g2.setColor(Color.WHITE);
		g2.drawImage(keyImage,1, 1, gp.tileSize, gp.tileSize,null);
		g2.drawString("x " + gp.player.backpack.size(), gp.tileSize,gp.tileSize/2 + indent);

		if(massageVisible){
			g2.setFont(g2.getFont().deriveFont(30F));
			g2.drawString(massage, gp.tileSize*2, gp.tileSize/2+indent);
			massageCountdown++;
			if(massageCountdown>120){
				massageCountdown=0;
				massageVisible=false;
			}
		}
	}
	
	private BufferedImage getImage(String path){
		BufferedImage img = null;
		try{
			img = ImageIO.read(getClass().getResourceAsStream(path));} catch(IOException e){
				e.printStackTrace();
			}
		return img;
	}
}
