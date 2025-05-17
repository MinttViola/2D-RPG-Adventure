package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
	//screen settings 
	public final int originalTitleSize = 32; 
	final int scale = 3;
	public final int tileSize = originalTitleSize*scale;
	final int maxScreenCol=10;
	final int maxScreenRow=8;
	final int screenWidth=tileSize*maxScreenCol;
	final int screenHeight=tileSize*maxScreenRow;

	public int FPS = 60;

	Thread gameThread;
	public KeyHandler keyH = new KeyHandler();
	Player player = new Player(this,keyH);

	//playrs default position
	int playerY = 4;
	int playerX = 4;
	int playerSpeed = 4;

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
		player.draw(g2);
		g2.dispose();
	}

}
