package Service;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import Entity.NPC;
import Main.GamePanel;
import Util.GameState;

public class UIService {
	GamePanel gp;
	Font myFont;
	boolean massageVisible = false;
	String massage = "";
	int dialogueCounter = 0;
	int indent = 10;
	int massageCountdown = 0;
	Graphics2D g2;

	public UIService(GamePanel gp){
		this.gp = gp;
			File file = new File("Assets/Fonts/FontForGame.ttf");
			try{
			myFont = Font.createFont(Font.TRUETYPE_FONT,file);
			}catch(FontFormatException e){} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

	public void showMassage(String text){
		massage = text;
		massageVisible = true;
	}

	public void draw(Graphics2D g2){
		this.g2 = g2;
		g2.setFont(myFont);
		g2.setColor(Color.white);
		
		switch(gp.gameState){
			case GameState.PlayState:
			break;
			case GameState.PauseState:
			drawPauseScreen();
			break;
			case GameState.DialogState:
			drawDialogScreen();
			break;
		}
	}

	private void drawDialogScreen(){
		NPC npc = gp.player.NPCForDialogue;
			int x = gp.tileSize*2;
			int y = gp.tileSize /2;
			int width = gp.screenWidth - (gp.tileSize*4);
			int height = gp.tileSize*2;
			drawSubWindow(x, y, width, height);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
			x+=gp.tileSize;
			y+=gp.tileSize;
			if(dialogueCounter !=npc.dialoguesArray.length)
				g2.drawString( npc.dialoguesArray[dialogueCounter], x, y);
			else{
			gp.gameState = GameState.PlayState;
			dialogueCounter = 0;
		}
	}

	private void drawSubWindow(int x, int y, int width, int height){
		g2.setColor(new Color(0,0,0,255));
		g2.fillRoundRect(x, y, width, height, 35, 35);
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
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
