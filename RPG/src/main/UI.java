package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import object.Item_Key;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font arial_30;
	Font warning;
	public Boolean messageOn = false;
	public String message = "";
	int messageDuration = 0;//frame counter
	public String currentDialog = "";
	BufferedImage StatBox = null;
	ArrayList<String> notice = new ArrayList<>();
	ArrayList<Integer> noticeCounter = new ArrayList<>();
	int counter;
	int spriteNum;
	
	
	public UI (GamePanel gp) {
		this.gp = gp;
		arial_30 = new Font("Airal",Font.PLAIN, 30);
		warning = new Font("Airal",Font.BOLD,15);
		Item_Key key = new Item_Key(gp);
		//KeyImage = key.image;
		try {
			StatBox = ImageIO.read(getClass().getResource("/UI/StatBox.png"));
		} 
		catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	public void addMessage(String text) {
		notice.add(text);
		noticeCounter.add(0);
		
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(arial_30);
		g2.setColor(Color.white);
		//g2.drawImage(KeyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
		//g2.drawString("x " +gp.player.Key,80,60);
		if(gp.GameState == gp.playState) {
			drawHUD();
			drawMessage();
		}
		if(gp.GameState == gp.pauseState) {
			drawPauseScreen();
			drawHUD();
		}
		if(gp.GameState == gp.dialogState) {
			drawDialogScreen();
			drawHUD();
		}
		if(gp.GameState == gp.statState) {
			drawHUD();
		}
		if(messageOn == true) {
			g2.setFont(new Font("Teko", Font.PLAIN, 26));
			g2.drawString(message, gp.tileSize*10, gp.tileSize*8);
			
			messageDuration++;
			
			if(messageDuration > 120) {
				messageDuration = 0;
				messageOn = false;
			}
		}
	}
	private void drawMessage() {
		
		int messageX = 800;
		int messageY = 400;
		g2.setFont(new Font("Teko", Font.PLAIN, 18));
		for(int i = 0; i < notice.size(); i++) {
			if(notice.get(i) != null) {
				g2.setColor(Color.BLACK);
				g2.drawString(notice.get(i), messageX+2, messageY+2);
				g2.setColor(Color.WHITE);
				g2.drawString(notice.get(i), messageX, messageY);
				
				int counter =noticeCounter.get(i) + 1; //message Counter ++
				noticeCounter.set(i, counter); //set counter to array
				messageY += 20;
				
				if(noticeCounter.get(i) > 90) {
					notice.remove(i);
					noticeCounter.remove(i);
				}
			}
		}
	}

	//HUD(mp and hp)
	public void drawHUD() {
		g2.drawImage(StatBox,10, 5, null);
		//gp.player.HUD(g2);
		PlayerHUD(g2);
	}
	public void drawPauseScreen() {
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		g2.drawString(text,x,y);
	}
	public void drawDialogScreen() {
		
		//Dialog window size
		int x = gp.tileSize;
		int y = gp.tileSize*12;
		int width = gp.screenWidth - (gp.tileSize*2);
		int height = gp.tileSize*5;
		drawSubWindow(x,y,width,height);
		//Dialog text
		x += gp.tileSize;
		y += gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN));
		//split line whenever the text have "\n"
		for(String line : currentDialog.split("\n")) {
			g2.drawString(currentDialog, x, y);
			y += 40;
		}
	}
	public void drawSubWindow(int x,int y,int width,int height) {
		//Using Paint to define correct RGB,A is for opacity
		Color c = new Color(30,30,30,200);
		g2.setColor(c);
		g2.fillRect(x, y, width, height);
		
		g2.setColor(c);
		g2.fillRect(gp.tileSize, gp.tileSize*11, gp.tileSize*4, gp.tileSize);
		
		c = new Color(255,255,255);
		
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(5));
		g2.drawRect(x+5, y+5, width-10, height-10);
	}
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenHeight/2 - length/2;
		return x;

	}
	public void drawCollision(Graphics2D g2,int x,int y,int width,int height) {
		this.g2 = g2;
		g2.setColor(Color.WHITE);
		g2.fillRect(x, y, width, height);
	}
	public void PlayerHUD(Graphics2D g2) {

		//Health bar
		double hpscale = (double)gp.tileSize*6/gp.player.MaxHP;
		double hpbarValue = hpscale*gp.player.HP;
		g2.setColor(new Color(130,0,0,200));
		g2.fillRect(90, 20, gp.tileSize*6, 20);
		g2.setColor(new Color(215,0,0));
		g2.fillRect(90, 20, (int)hpbarValue, 20);
		//Mana bar
		double mpscale = (double)gp.tileSize*6/gp.player.MaxMP;
		double mpbarValue = mpscale*gp.player.MP;
		g2.setColor(new Color(0,24,83,200));
		g2.fillRect(90, 45, gp.tileSize*6, 20);
		g2.setColor(new Color(14,86,254));
		g2.fillRect(90, 45, (int)mpbarValue, 20);
		g2.setColor(Color.white);
	}
	public void animation(Graphics2D g2,String directory,int x,int y,BufferedImage image,int interval,int NumIndex) {
		g2.drawImage(image,x,y,null);
		counter++;
		if(counter > interval) {
			spriteNum++;
			if(spriteNum > NumIndex) {
				spriteNum = 1; 
			}
		}
		switch(NumIndex) {
		case 1:
			g2.drawImage(image,x,y,null);
		}
	}
}
