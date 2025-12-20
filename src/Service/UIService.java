package Service;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import Entity.NPC;
import Main.GamePanel;
import Util.GameState;
import Util.ImageUtil;
import java.awt.image.BufferedImage;

public class UIService {
	GamePanel gp;
	Font myFont;
	boolean massageVisible = false;
	String massage = "";
	int dialogueCounter = 0;
	int maxDialogueNum = 0;
	int indent = 10;
	int massageCountdown = 0;
	Graphics2D g2;
	Color baseStrokeColor,baseDialogWindowColor;
	private int commandNum = 1;
	int maxNumCommand = 0;
	GameState prevGameState;

	public UIService(GamePanel gp){
		this.gp = gp;
			File file = new File("Assets/Fonts/FontForGame2.ttf");
			try{
			myFont = Font.createFont(Font.TRUETYPE_FONT,file);
			}catch(FontFormatException e){} catch (IOException e) {
				e.printStackTrace();
			}
		baseStrokeColor = Color.white;
		baseDialogWindowColor = new Color(0,0,0,255);
	}

	public void showMassage(String text){
		massage = text;
		massageVisible = true;
	}

	public void draw(Graphics2D g2){
		this.g2 = g2;
		g2.setFont(myFont);
		g2.setColor(Color.white);
		
		switch(gp.getGameState()){
			case GameState.TitleState:
			if(prevGameState != gp.getGameState()){
				maxNumCommand = 3;
				commandNum = 1;
			}
			prevGameState = GameState.TitleState;
			drawTitleScreen();
			break;
			case GameState.PlayState:
			break;
			case GameState.PauseState:
			drawPauseScreen();
			break;
			case GameState.DialogueState:
			drawDialogScreen();
			break;
		}
	}
	
	private void drawTitleScreen(){

		g2.setColor(new Color(0,0,0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		int y = gp.tileSize*2;
		drawImage(0, y+(gp.tileSize), gp.tileSize*2, gp.tileSize*2, "Assets/UI/CharForMenu.png");
		drawShadowTextByCenter("2D RPG Adventure", Color.gray, Color.white, y);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		drawTextByCenter("NEW GAME", Color.white, y+(gp.tileSize*5),0);
		drawTextByCenter("LOAD GAME", Color.white, y+(gp.tileSize*6),0);
		drawTextByCenter("QUIT", Color.white, y+(gp.tileSize*7), 0);
		drawImage(6*gp.tileSize,gp.tileSize*(5+commandNum)+10, gp.tileSize, gp.tileSize, "Assets/UI/select_icon_ui.png");
	}

	private void drawDialogScreen(){
		NPC npc = gp.player.NPCForDialogue;
		maxDialogueNum = npc.dialoguesArray.length;
		int x = gp.tileSize*2;
		int y = gp.tileSize /2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*2;
		drawSubWindow(x, y, width, height,baseDialogWindowColor,baseStrokeColor);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
		x+=gp.tileSize;
		y+=gp.tileSize;
		if(dialogueCounter !=npc.dialoguesArray.length)
			g2.drawString(npc.dialoguesArray[dialogueCounter], x, y);
		else{
			gp.setGameState(GameState.PlayState);
			dialogueCounter = 0;
		}
	}

	private void drawPauseScreen(){
		int y = gp.screenHeight/2;
		Color color = new Color(0,0,0,120);
		drawSubWindow(0, 0, gp.screenWidth, gp.screenHeight, color,color);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		drawShadowTextByCenter("pause", Color.gray, Color.white, y);
	}

	private void drawSubWindow(int x, int y, int width, int height, Color windowColor, Color strokeColor){
		g2.setColor(windowColor);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		g2.setColor(strokeColor);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}


	private void drawShadowTextByCenter(String text, Color shadowColor, Color mainTextColor, int y){
		drawTextByCenter(text, shadowColor, y+5, 5);
		drawTextByCenter(text, mainTextColor, y,0);
	}

	private void drawTextByCenter(String text, Color color, int y, int xChangeForShadow){
		int x = getXForCenterText(text);
		g2.setColor(color);
		g2.drawString(text, x+xChangeForShadow, y);
	}

	private int getXForCenterText(String text){
		int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		int x = gp.screenWidth/2- length/2;
		return x;
	}

	private void drawImage(int x, int y, int width, int height, String path){
		BufferedImage imageToDraw = ImageUtil.getMainImage(path);
		if(x==0){
			x = gp.screenWidth/2 - width/2;
		}
		g2.drawImage(imageToDraw,x,y,width,height, null);
	}

	public void nextDialogue(){
		if(dialogueCounter <maxDialogueNum)
		dialogueCounter++;
	}



	public void increaseNumCommand(){
		if(commandNum+1<=maxNumCommand){
			commandNum +=1;
			drawImage(0,gp.tileSize*(5+commandNum)+10, gp.tileSize, gp.tileSize, "Assets/UI/select_icon_ui.png");
		}
	}

	public void decreaseNumCommand(){
		if(commandNum-1>0){
			commandNum -=1;
			drawImage(0,gp.tileSize*(5+commandNum)+10, gp.tileSize, gp.tileSize, "Assets/UI/select_icon_ui.png");
		}
	}

	public void enterCommand(){
		switch(gp.getGameState()){
			case GameState.TitleState:
			titleStateSwitch();
			break;
		}
	}

	private void titleStateSwitch(){
		switch(commandNum){
			case 1:
				gp.setGameState(GameState.PlayState);
				break;
			case 2:

			break;
			case 3:
			System.exit(0);
			break;
		}
	}
	
}
