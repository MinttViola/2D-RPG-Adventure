package Service;

import Main.GamePanel;
import Util.GameState;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyService implements KeyListener{

	public int yChange, xChange = 0;
	public boolean EPressed = false;
	GamePanel gp;
	public KeyService(GamePanel gp){
		this.gp = gp;
	}
	@Override
	public void keyPressed(KeyEvent e) {		
		int code = e.getKeyCode();
		if(gp.gameState == GameState.PlayState){
			switch (code) {
				case KeyEvent.VK_W:
					xChange=1;
					break;
				case KeyEvent.VK_S:
					xChange=-1;
					break;
				case KeyEvent.VK_A:
					yChange=-1;
					break;
				case KeyEvent.VK_D:
					yChange=1;
					break;
					case KeyEvent.VK_E:
					EPressed = true;
					break;
				case KeyEvent.VK_ESCAPE:
					gp.gameState = GameState.PauseState;
					break;
					}
				}
		if(gp.gameState == GameState.PauseState && code == KeyEvent.VK_ESCAPE){
				gp.gameState = GameState.PlayState;

		}
		if(gp.gameState == GameState.DialogState && code == KeyEvent.VK_E){
			gp.ui.dialogueCounter++;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
			case KeyEvent.VK_W:
			case KeyEvent.VK_S:
				xChange=0;
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_D:
				yChange=0;
				break;
				case KeyEvent.VK_E:
				EPressed = false;
			default:
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
