package Service;

import Main.GamePanel;
import Util.GameState;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyService implements KeyListener{

	public int yChange, xChange = 0;
	public boolean EPressed = false;
	GamePanel gp;	
	long timerForEliminateDoubleClick = 0;
	public KeyService(GamePanel gp){
		this.gp = gp;
	}
	@Override
	public void keyPressed(KeyEvent e) {	
		int code = e.getKeyCode();
		switch (gp.getGameState()) {
				case GameState.PlayState:
					playStateSwitch(code);
					break;
				case GameState.PauseState:
					pauseStateSwitch(code);
					break;
				case GameState.DialogueState:
					dialogueStateSwitch(code);
					break;
				case GameState.TitleState:
					titleStateSwitch(code);
					break;
				default:
					break;
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

	private void playStateSwitch(int code){
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
				case KeyEvent.VK_I:
					if(System.nanoTime()>=timerForEliminateDoubleClick)
						gp.player.changeHP(10);
					timerForEliminateDoubleClick = System.nanoTime() +10000;
					break;
				case KeyEvent.VK_K:
					if(System.nanoTime()>=timerForEliminateDoubleClick)
						gp.player.changeHP(-10);
					timerForEliminateDoubleClick = System.nanoTime() +10000;
					break;
				case KeyEvent.VK_P:
					gp.setGameState(GameState.PauseState);
					timerForEliminateDoubleClick = System.nanoTime() +10000;
					break;
			}
		}
		
		private void pauseStateSwitch(int code){
			switch (code) {
					case KeyEvent.VK_P:
							if(System.nanoTime()>=timerForEliminateDoubleClick)
								gp.setGameState(GameState.PlayState);
							break;
					default:
							throw new AssertionError();
			}
		}

		public void dialogueStateSwitch(int code){
			switch (code) {
					case KeyEvent.VK_E:
							if(System.nanoTime()>=timerForEliminateDoubleClick){
								gp.nextDialogue();
								timerForEliminateDoubleClick = System.nanoTime() + 1000;
							}
							break;
					default:
							throw new AssertionError();
			}
		}

		private void titleStateSwitch(int code){
			if(System.nanoTime()>=timerForEliminateDoubleClick){
				switch (code) {
						case KeyEvent.VK_W:
							gp.ui.decreaseNumCommand();
							timerForEliminateDoubleClick = System.nanoTime() + 1000;
							break;
						case KeyEvent.VK_S:
							gp.ui.increaseNumCommand();
							timerForEliminateDoubleClick = System.nanoTime() + 1000;
							break;
						case KeyEvent.VK_E:
							timerForEliminateDoubleClick = System.nanoTime() + 1000;
							gp.ui.enterCommand();
							break;
						case KeyEvent.VK_ENTER:
							timerForEliminateDoubleClick = System.nanoTime() + 1000;
							gp.ui.enterCommand();
							break;
						default:
						break;
				}
			}
		}
}
