package Service;

import Main.GamePanel;
import Util.GameState;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UIService {
	GamePanel gp;
	Font arial_40;
	boolean massageVisible = false;
	String massage = "";
	int indent = 10;
	int massageCountdown = 0;
	Graphics2D g2;

	public UIService(GamePanel gp){
		this.gp = gp;
		arial_40 = new Font("Arial",Font.PLAIN,40);
	}

	public void showMassage(String text){
		massage = text;
		massageVisible = true;
	}

	public void draw(Graphics2D g2){
		this.g2 = g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		switch(gp.gameState){
			case GameState.PlayState:
			break;
			case GameState.PauseState:
			drawPauseScreen();
			break;
		}
	}

	private void drawPauseScreen(){
		String text ="pause";
		int x = GetXForCenterText(text);
		int y = gp.screenHeight/2;
		g2.drawString(text, x, y);

	}

	public int GetXForCenterText(String text){
		int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		int x = gp.screenWidth/2- length/2;
		return x;
	}
}
