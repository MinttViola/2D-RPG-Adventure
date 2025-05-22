package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import InteractableObj.SuperObject;
import Tile.TileManager;
import Util.TemporaryObjPlasment;
import entity.Player;

public class GamePanel extends JPanel implements Runnable {
	//screen settings 
	public final int originalTitleSize = 16; 
	final int scale = 4;
	public final int tileSize = originalTitleSize*scale;
	public final int maxScreenCol=20;//1680
	public final int maxScreenRow=10;//640
	public final int screenWidth=tileSize*maxScreenCol;
	public final int screenHeight=tileSize*maxScreenRow;


	public final int maxWorldCol = 50;
	public final int maxWorldRow = 100;
	public final int worldWidth=tileSize*maxWorldCol;
	public final int worldHeight=tileSize*maxWorldRow;
	public final int colDivisior = 6;

	public int FPS = 60;

	TileManager tileM = new TileManager(this, 4);
	Thread gameThread;
	public CollisionChecker cCheck = new CollisionChecker(this);
	public KeyHandler keyH = new KeyHandler();
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[10];
	TemporaryObjPlasment objPlase = new TemporaryObjPlasment(this);


	public GamePanel(){
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		startGameThread();
	}


	public void startGameThread(){
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long curTime;
		long timer = 0;
		int drawCount = 0;

		while (gameThread !=null){
			curTime = System.nanoTime();
			delta +=(curTime-lastTime)/drawInterval;
			timer +=(curTime-lastTime);
			lastTime=curTime;
			if (delta>=1) {
				update();
				repaint();
				delta--;
				drawCount ++;
			}
			if(timer>=1000000000){
				timer = 0;
				drawCount = 0;
			}
		}
	}

	public void update(){
		player.update();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		tileM.draw(g2);
		for(int i = 0;i<=obj.length-1;i++){
			if(obj[i]!=null){
				obj[i].draw(g2);
			}
		}
		player.draw(g2);
		g2.dispose();
	}

}
