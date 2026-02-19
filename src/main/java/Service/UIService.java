package Service;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import Entity.NPC;
import Main.GamePanel;
import Util.ImageUtil;
import Util.WorkWithFilesUtil;
import Util.Enums.GameStateEnum;

public class UIService {
	GamePanel gp;
	WorkWithFilesUtil fileUtil = new WorkWithFilesUtil();
	LanguageService lang = new LanguageService();
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
	GameStateEnum prevGameState;
	String startPath = "Assets/UI/";
	String imageFileFormat = ".png";
	int healthBarSize,maxHealthBarSize;
	int tileSize;

	public UIService(GamePanel gp){
		this.gp = gp;
		LoadFont();
		lang.loadLanguage("UI", gp.getStringLang());
		tileSize = gp.getTileSize();
		healthBarSize = (int)((tileSize*3-tileSize/2));
		maxHealthBarSize = healthBarSize;
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
			case TitleState:
			if(prevGameState != gp.getGameState()){
				maxNumCommand = 4;
				commandNum = 1;
			}
			prevGameState = GameStateEnum.TitleState;
			drawTitleScreen();
			break;
			case OptionsState:
			if(prevGameState != gp.getGameState()){
				maxNumCommand = 2;
				commandNum = 1;
			}
			prevGameState = GameStateEnum.OptionsState;
			drawOptionsScreen();
			break;
			case PlayState:
			drawPlayStateUI();
			break;
			case PauseState:
			drawPauseScreen();
			break;
			case DialogueState:
			drawDialogScreen();
			break;
		}
	}

	private void LoadFont(){
	try (
		InputStream is = getClass()
			.getClassLoader()
			.getResourceAsStream("Assets/Fonts/FontForGame2.ttf")) 
		{
			if (is == null) {
					throw new RuntimeException("Font not found");
			}
			myFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} 
		catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	private void LoadLangue(){
		lang.loadLanguage("UI", gp.getStringLang());
	}

	private void drawPlayStateUI(){
		drawPlayerHealthBar();
	}
	
	private void drawTitleScreen(){
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		int y = tileSize*2;
		drawImage(0, y+(tileSize), tileSize*2, tileSize*2,"CharForMenu");
		drawShadowTextByCenter(lang.get("name"), Color.gray, Color.white, y);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		drawTextByCenter(lang.get("menu.NewGame"), Color.white, y+(tileSize*4),0);
		drawTextByCenter(lang.get("menu.LoadGame"), Color.white, y+(tileSize*5),0);
		drawTextByCenter(lang.get("menu.Options"), Color.white, y+(tileSize*6),0);
		drawTextByCenter(lang.get("menu.Quit"), Color.white, y+(tileSize*7), 0);
		drawImage(6*tileSize,tileSize*(4+commandNum)+10, tileSize, tileSize, "select_icon_ui");
	}

	private void drawOptionsScreen(){
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
		int y = tileSize*2;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		drawTextByCenter(lang.get("menu.Options"), Color.white, y, 0);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		drawImage(0, y+(tileSize), tileSize*2, tileSize*2,"CharForMenu");
		drawTextByCenter(lang.get("options.Langueage") + ":   " + gp.getStringLang(), Color.white, y+(tileSize*4), 0);
		drawTextByCenter(lang.get("options.Back"), Color.white, y+(tileSize*5), 0);
		drawImage(6*tileSize,tileSize*(4+commandNum)+10, tileSize, tileSize, "select_icon_ui");
	}

	private void drawDialogScreen(){
		NPC npc = gp.getPlayer().getDialogueNPC();
		String id = npc.name+"_"+npc.id;
		List<String> dialogueList = gp.getlevelService().getDialogueList(id);
		maxDialogueNum = gp.getlevelService().getMaxDialogueNumFromID(dialogueList);
		int x = tileSize*2;
		int y = tileSize /2;
		int width = gp.getScreenWidth() - (tileSize*4);
		int height = tileSize*2;
		drawSubWindow(x, y, width, height,baseDialogWindowColor,baseStrokeColor);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
		x+=tileSize;
		y+=tileSize;
		if(dialogueCounter !=maxDialogueNum)
			g2.drawString(dialogueList.get(dialogueCounter), x, y);
		else{
			gp.setGameState(GameStateEnum.PlayState);
			dialogueCounter = 0;
		}
	}

	private void drawPauseScreen(){
		drawPlayerHealthBar();
		int y = gp.getScreenHeight()/2;
		Color color = new Color(0,0,0,120);
		drawSubWindow(0, 0, gp.getScreenWidth(), gp.getScreenHeight(), color,color);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		drawShadowTextByCenter(lang.get("pause"), Color.gray, Color.white, y);
	}

	private void drawPlayerHealthBar(){
		drawImage(((tileSize/4)+tileSize+tileSize/16), tileSize/4, healthBarSize, tileSize,"health_hud");
		drawImage(tileSize/4, tileSize/4, tileSize*4, tileSize,"health_bar_hud");
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
		int x = gp.getScreenWidth()/2- length/2;
		return x;
	}

	private void drawImage(int x, int y, int width, int height, String fileName){
		String fullPath = startPath+fileName+imageFileFormat;
		BufferedImage imageToDraw = ImageUtil.getMainImage(fullPath);
		if(x==0){
			x = gp.getScreenWidth()/2 - width/2;
		}
		g2.drawImage(imageToDraw,x,y,width,height, null);
	}

	public void nextDialogue(){
		if(dialogueCounter <maxDialogueNum)
		dialogueCounter++;
	}

	public void changeHealthBar(double curHP){

		int newHealthBarSize = (int)(maxHealthBarSize*(curHP/100));
		drawImage(((tileSize/4)+tileSize+tileSize/16), tileSize/4, newHealthBarSize, tileSize,"health_hud");
		healthBarSize = newHealthBarSize;
	}

	public void increaseNumCommand(){
		if(commandNum+1<=maxNumCommand){
			commandNum +=1;
			drawImage(0,tileSize*(5+commandNum)+10, tileSize, tileSize, "select_icon_ui");
		}
	}

	public void decreaseNumCommand(){
		if(commandNum-1>0){
			commandNum -=1;
			drawImage(0,tileSize*(5+commandNum)+10, tileSize, tileSize,"select_icon_ui");
		}
	}

	public void enterCommand(){
		switch(gp.getGameState()){
			case TitleState:
			titleStateSwitch();
			break;
			case OptionsState:
			optionStateSwitch();
			break;
		}
	}

	private void titleStateSwitch(){
		switch(commandNum){
			case 1:
				gp.setGameState(GameStateEnum.PlayState);
				break;
			case 2:
			//load
			break;
			case 3:
				gp.setGameState(GameStateEnum.OptionsState);
				break;
			case 4:
			System.exit(0);
			break;
		}
	}

	private void optionStateSwitch(){
		switch(commandNum){
			case 1:
				gp.nextLang();
				LoadLangue();
				drawOptionsScreen();
				break;
			case 2:
				gp.setGameState(GameStateEnum.TitleState);
			break;
		}
	}
	
}
