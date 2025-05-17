package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public int yChange, xChange = 0;
	@Override
	public void keyPressed(KeyEvent e) {		
		int code = e.getKeyCode();
		switch (code) {
			case KeyEvent.VK_W:
				yChange=-1;
				break;
			case KeyEvent.VK_S:
				yChange=1;
				break;
			case KeyEvent.VK_A:
				xChange=-1;
				break;
			case KeyEvent.VK_D:
				xChange=1;
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
				yChange=0;
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_D:
				xChange=0;
				break;
			default:
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
