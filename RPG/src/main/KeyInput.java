package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{
	public boolean up,down,left,right,idle,interactKey;
	GamePanel gp;
	int counter;
	
	public KeyInput(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// Action when key pressed
		//check();
		int code = e.getKeyCode();//get code of pressed key
		//Play State
		if(gp.GameState == gp.playState) {
			if(code == KeyEvent.VK_W) {
				up = true;
				idle = false;
			}
			if(code == KeyEvent.VK_S) {
				down = true;
				idle = false;
			}
			if(code == KeyEvent.VK_A) {
				left = true;
				idle = false;
			}
			if(code == KeyEvent.VK_D) {
				right = true;
				idle = false;
			}
			if(code == KeyEvent.VK_ESCAPE) {
				gp.GameState = gp.pauseState;
			}
			if(code == KeyEvent.VK_F) {
				interactKey = true;
			}
			if(code == KeyEvent.VK_B) {
				gp.GameState = gp.statState;
			}
			if(code == KeyEvent.VK_1) {
				
			}
			if(code == KeyEvent.VK_SPACE) {
				if(gp.player.attacking == false) {
					gp.player.attacking = true;
				}
			}
		}
		//Pause State
		else if(gp.GameState == gp.pauseState || gp.GameState == gp.statState) {
			if(code == KeyEvent.VK_ESCAPE) {
				gp.GameState = gp.playState;
			}
		}
		//In dialog State
		if(gp.GameState == gp.dialogState) {
			if(code == KeyEvent.VK_SPACE) {
				gp.GameState = gp.playState;
			}
		}
		if(gp.GameState == gp.statState) {
			if(code == KeyEvent.VK_W) {
				if(gp.player.graphic.slotRow != 0) {
					gp.player.graphic.slotRow--;
				}
			}
			if(code == KeyEvent.VK_D) {
				if(gp.player.graphic.slotCol != 6) {
					gp.player.graphic.slotCol++;
				}
			}
			if(code == KeyEvent.VK_S) {
				if(gp.player.graphic.slotRow != 5) {
					gp.player.graphic.slotRow++;
				}
			}
			if(code == KeyEvent.VK_A) {
				if(gp.player.graphic.slotCol != 0) {
					gp.player.graphic.slotCol--;
				}
			}
			if(code == KeyEvent.VK_SPACE) {
				gp.player.selectItem();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//Action when key released
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			up = false;
			idle = true;
		}
		if(code == KeyEvent.VK_S) {
			down = false;
			idle = true;
		}
		if(code == KeyEvent.VK_A) {
			left = false;
			idle = true;
		}
		if(code == KeyEvent.VK_D) {
			right = false;
			idle = true;
		}
		if(code == KeyEvent.VK_F) {
			interactKey = false;
		}
		if(code == KeyEvent.VK_SPACE) {
			//gp.player.speed = 4;
			//gp.player.attacking = false;
		}
			
	}
	public void check() {
		System.out.println("left " +left);
		System.out.println("right " +right);
		System.out.println("down " +down);
		System.out.println("up " +up);
		
	}

}
