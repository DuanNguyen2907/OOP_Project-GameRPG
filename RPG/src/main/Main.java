package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Dungeon Traveller");
		
		
		
		GamePanel gamePanel = new GamePanel();
		
		window.add(gamePanel);
		gamePanel.SetUpGame();
		gamePanel.startGameThread();
		
		window.pack();
		window.setLocationRelativeTo(null);//set to null will auto set it to middle of screen.
		window.setVisible(true);
		
		

	}

}