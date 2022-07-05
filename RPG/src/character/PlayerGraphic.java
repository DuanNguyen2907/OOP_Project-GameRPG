package character;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UI;
import main.Utility;

public class PlayerGraphic {
	
	private static final String OnHand = null;
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2,idle1,idle2;
	public BufferedImage atkup1,atkup2,atkdown1,atkdown2,atkleft1,atkleft2,atkright1,atkright2;
	public BufferedImage coin;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public BufferedImage onhand;
	public int slotCol = 0;
	public int slotRow = 0;
	public int subState = 0;
	public int SelectionCount = 1;
	public int traderslotCol = 2;
	public int traderslotRow = 0;
	public BufferedImage[][] sprite1;
	
	public PlayerGraphic() {
	}
	
	public BufferedImage setup(String imagename) {
    	Utility utility = new Utility();
		BufferedImage Image = null;
		
		try {
			Image = ImageIO.read(getClass().getResourceAsStream(imagename + ".png"));
			Image = utility.scaleImage(Image,48,48);
			coin = ImageIO.read(getClass().getResourceAsStream("/object/coin.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}   	   	
    	return Image;   	
		
	}
	public BufferedImage setup(String imagename,int width,int height) {
    	Utility utility = new Utility();
		BufferedImage Image = null;
		
		try {
			Image = ImageIO.read(getClass().getResourceAsStream(imagename + ".png"));
			Image = utility.scaleImage(Image,width,height);
			
		}catch(IOException e) {
			e.printStackTrace();
		}   	   	
    	return Image;   	
		
	}
	public void setImage(String filename, String picname) {
		up1 = setup(filename + "/back_1");
		up2 = setup(filename + "/back_2");
		down1 = setup(filename + "/idle_2");
		down2 = setup(filename + "/idle_3");
		left1 = setup(filename + "/idle_L");
		left2 = setup(filename + "/idle_L_2");
		right1 = setup(filename + "/idle_R");
		right2 = setup(filename + "/idle_R_2");
		idle1 = setup(filename + "/idle1");
		idle2 = setup(filename + "/idle_2");
	}
	public void getImage(String filename, String type) {
		switch(type) {
		case "humanoid":
			up1 = setup(filename + "/back_1");
			up2 = setup(filename + "/back_2");
			down1 = setup(filename + "/idle_2");
			down2 = setup(filename + "/idle_3");
			left1 = setup(filename + "/idle_L");
			left2 = setup(filename + "/idle_L_2");
			right1 = setup(filename + "/idle_R");
			right2 = setup(filename + "/idle_R_2");
			idle1 = setup(filename + "/idle1");
			idle2 = setup(filename + "/idle_2");
			break;
		case "slime":
			up1 = setup(filename + "/slime1");
			up2 = setup(filename + "/slime2");
			down1 = setup(filename + "/slime1");
			down2 = setup(filename + "/slime2");
			left1 = setup(filename + "/slime1");
			left2 = setup(filename + "/slime2");
			right1 = setup(filename + "/slime1");
			right2 = setup(filename + "/slime2");
			idle1 = setup(filename + "/slime1");
			idle2 = setup(filename + "/slime2");
			break;
		case "MC":
			up1 = setup(filename + "/idle1");
			up2 = setup(filename + "/idle2");
			down1 = setup(filename + "/idle1");
			down2 = setup(filename + "/idle2");
			left1 = setup(filename + "/idle1");
			left2 = setup(filename + "/idle2");
			right1 = setup(filename + "/idle1");
			right2 = setup(filename + "/idle2");
			idle1 = setup(filename + "/idle1");
			idle2 = setup(filename + "/idle2");
		}
	}
	public void getAtkImage(String filename) {
		atkup1 = setup(filename + "/back_1",96,96);
		atkup2 = setup(filename + "/back_2",96,96);
		atkdown1 = setup(filename + "/atk_down1",96,96);
		atkdown2 = setup(filename + "/atk_down2",96,96);
		atkleft1 = setup(filename + "/idle_L",96,96);
		atkleft2 = setup(filename + "/idle_L_2",96,96);
		atkright1 = setup(filename + "/idle_R",96,96);
		atkright2 = setup(filename + "/idle_R_2",96,96);
	}
	
	//Draw 2 pic set using singular pic each
	public void updateDirection(Character c, int interval, int NumIndex) {
		if(c.collisionOn == false && c.gp.key.interactKey == false) {
			switch(c.direction) {
			case "up":
				c.worldY -= c.speed;
				break;
			case "down":
				c.worldY += c.speed;
				break;
			case "left":
				c.worldX -= c.speed;
				break;
			case "right":
				c.worldX += c.speed;
				break;
			case "upright":
				c.worldY -= c.speed -2;
				c.worldX += c.speed -2;
				break;
			case "upleft":
				c.worldY -= c.speed -2;
				c.worldX -= c.speed -2;
				break;
			case "downright":
				c.worldY += c.speed -2;
				c.worldX += c.speed -2;
				break;
			case "downleft":
				c.worldY += c.speed -2;
				c.worldX -= c.speed -2;
				break;
			}
		}	
		//each frame + 1, every 10 frame change animation once
		spriteCounter++;
		if(spriteCounter > interval) {
			spriteNum++;
			if(spriteNum > NumIndex) {
				spriteNum = 1; 
			}
			/*
			if(spriteNum == 1) {
				spriteNum = 2;
			}else if(spriteNum == 2) {
				spriteNum = 1;
			}
			*/
			spriteCounter = 0;
		}
		c.collisionOn = false;
		
	}
	public void drawName(Character c,Graphics2D g2,String name,GamePanel gp, int offsety,Color color) {
		int screenX = c.worldX - gp.player.worldX + gp.player.screenX;
		int screenY = c.worldY - gp.player.worldY + gp.player.screenY;
		int length = (int)g2.getFontMetrics().getStringBounds(name, g2).getWidth();
		int centeredX = screenX + gp.tileSize/2 - length/2;
		g2.setColor(color);
		g2.drawString(name,centeredX,screenY-offsety);
	}
	public void drawLv(Character c,Graphics2D g2,int lv,GamePanel gp) {
		if(lv - gp.player.lv <= 0) {
			drawName(c, g2, "lv."+c.lv, gp, 9, Color.WHITE);
		}else if(lv - gp.player.lv > 0 && lv - gp.player.lv < 5) {
			drawName(c, g2, "lv."+c.lv, gp, 9, Color.GREEN);
		}else if(lv - gp.player.lv >= 5 && lv -gp.player.lv < 10) {
			drawName(c, g2, "lv."+c.lv, gp, 9, Color.ORANGE);
		}else if(lv -gp.player.lv >= 10) {
			drawName(c, g2, "lv."+c.lv, gp, 9, Color.RED);
		}
	}
	public void drawPlayer(Player c ,Graphics2D g2) {
		int offsetX,offsetY;
		BufferedImage image = null;
		switch(c.direction) {
		case "up":
				if(spriteNum == 1) {image = up1;}
			    if(spriteNum == 2) {image = up2;}
			break;
		case "down":
				if(spriteNum == 1) {image = down1;}
			    if(spriteNum == 2) {image = down2;}
			break;
		case "left":
			if(spriteNum == 1) {image = left1;}
			if(spriteNum == 2) {image = left2;}
			break;
		case "right":
			if(spriteNum == 1) {image = right1;}
			if(spriteNum == 2) {image = right2;}
			break;
		case "upright":
			if(spriteNum == 1) {image = right1;}
			if(spriteNum == 2) {image = right2;}
			break;
		case "upleft":
			if(spriteNum == 1) {image = left1;}
			if(spriteNum == 2) {image = left2;}
			break;
		case "downright":
			if(spriteNum == 1) {image = right1;}
			if(spriteNum == 2) {image = right2;}
			break;
		case "downleft":
			if(spriteNum == 1) {image = left1;}
			if(spriteNum == 2) {image = left2;}
			break;
		}
		g2.drawImage(image, c.screenX, c.screenY,null);
	}
	
	//for debug
	public void drawCollision(Graphics2D g2,int x,int y,int width,int height) {
		g2.setColor(new Color(242,0,6,100));
		g2.fillRect(x, y, width, height);
	}

	public void drawCharacter(Character c ,Graphics2D g2, GamePanel gp) {
		BufferedImage image = null;
		int screenX = c.worldX - gp.player.worldX + gp.player.screenX;
		int screenY = c.worldY - gp.player.worldY + gp.player.screenY;
		
		//Only create object that visible on screen
		if(c.worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   c.worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   c.worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   c.worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			switch(c.direction) {
			case "up":
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
				break;
			case "down":
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
				break;
			case "left":
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				break;
			case "right":
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				break;
			}
			g2.drawImage(image,screenX,screenY,null);
	
		}
	}
	public void attack(Graphics2D g2,int i,Player p,int offsetX,int offsetY) {
		g2.drawImage(switchOnHand("slash"+i),p.screenX+offsetX,p.screenY+offsetY,96,96,null);
	}
	public void SpecialAtkFX(Graphics2D g2,String name,Player p,int offsetX,int offsetY) {
		g2.drawImage(switchOnHand(name),p.screenX+offsetX,p.screenY+offsetY,96,96,null);
	}
	public BufferedImage switchOnHand(String type) {
		onhand = setup("/FX/" + type );
		return onhand;
	}
	public void AnimFX(Graphics2D g2,int i, int x, int y) {
		g2.drawImage(switchOnHand("slime_ex"+i),x,y,null);
	}
	public void drawHP(Graphics2D g2,GamePanel gp,Character c) {
		int screenX = c.worldX - gp.player.worldX + gp.player.screenX;
		int screenY = c.worldY - gp.player.worldY + gp.player.screenY;
		double hpscale = (double)gp.tileSize/c.MaxHP;
		double hpbarValue = hpscale*c.HP;
		g2.setColor(new Color(130,0,0,200));
		g2.fillRect(screenX, screenY-5, gp.tileSize, 5);
		g2.setColor(new Color(215,0,0));
		g2.fillRect(screenX, screenY-5, (int)hpbarValue, 5);
	}
	public void drawStatHUD(GamePanel gp,Graphics g2) {
		final int frameX = 10;
		final int frameY = gp.tileSize*2;
		final int frameW = gp.tileSize*10;
		final int frameH = gp.tileSize*15;
		
		drawSubWindow(gp,g2,frameX, frameY, frameW, frameH);
		g2.drawImage(gp.player.OnhandWP.image, 30, frameY+120, 95, 95,null);
		g2.drawImage(gp.player.Shield.image, 30, frameY+225, 95, 95,null);
		g2.drawImage(idle1, 180, 250, 140, 140, null);
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("x12y16pxMaruMonica", Font.PLAIN, 26));
		//Window Text
		g2.drawString("INFORMATION",205, 150);
		g2.drawString("EQUIPMENT",210, 200);
		g2.drawString("STAT",235, 470);
		((Graphics2D) g2).setStroke(new BasicStroke(3));
		g2.drawRect(20, frameY+10, frameW-20, frameH-20);
		g2.drawRect(30, frameY+20, frameW-40, 50);
		g2.drawRect(170, frameY+120, 160, 200);
		//Left
		g2.drawRect(30, frameY+120, 95, 95);
		g2.drawRect(30, frameY+225, 95, 95);
		//Right
		g2.drawRect(375, frameY+120, 95, 95);
		g2.drawRect(375, frameY+225, 95, 95);
		
		int textX = 30;
		int textY = frameY + 420 ;
		final int lineheight = 36;
		
		//Stat Text
		g2.drawString("Lv ", textX, textY);
		textY += lineheight;
		g2.drawString("HP ", textX, textY);
		textY += lineheight;
		g2.drawString("MP ", textX, textY);
		textY += lineheight;
		g2.drawString("Atk ", textX, textY);
		textY += lineheight;
		g2.drawString("Def ", textX, textY);
		textY += lineheight;
		g2.drawString("EXP ", textX, textY);
		textY += lineheight;
		g2.drawString("Speed ", textX, textY);
		textY += lineheight;
		g2.drawString("Gold ", textX, textY);
		textY += lineheight;
		
		int tailX = (frameX + frameW) - 300;
		textY = frameY + 420;
		String value;
		
		value = String.valueOf(gp.player.lv);
		textX = AlignTextToRight(value,tailX,g2);
		g2.drawString(value, textX, textY);
		textY += lineheight;
		value = String.valueOf(gp.player.HP + "/" + gp.player.MaxHP);
		textX = AlignTextToRight(value,tailX,g2);
		g2.drawString(value, textX, textY);
		textY += lineheight;
		value = String.valueOf(gp.player.MP + "/" + gp.player.MaxMP);
		textX = AlignTextToRight(value,tailX,g2);
		g2.drawString(value, textX, textY);
		textY += lineheight;
		value = String.valueOf(gp.player.ATK);
		textX = AlignTextToRight(value,tailX,g2);
		g2.drawString(value, textX, textY);
		textY += lineheight;
		value = String.valueOf(gp.player.DEF);
		textX = AlignTextToRight(value,tailX,g2);
		g2.drawString(value, textX, textY);
		textY += lineheight;
		value = String.valueOf(gp.player.exp + "/" + gp.player.NextLvexp);
		textX = AlignTextToRight(value,tailX,g2);
		g2.drawString(value, textX, textY);
		textY += lineheight;
		value = String.valueOf(gp.player.speed);
		textX = AlignTextToRight(value,tailX,g2);
		g2.drawString(value, textX, textY);
		textY += lineheight;
		value = String.valueOf(gp.player.gold);
		textX = AlignTextToRight(value,tailX,g2);
		g2.drawString(value, textX, textY);
	
	}
	public void drawInventory(GamePanel gp,Graphics g2,boolean cursor) {
		//Frame
		final int frameX = 500;
		final int frameY = gp.tileSize*2;
		final int frameW = gp.tileSize*15-15;
		final int frameH = gp.tileSize*15;
		g2.setFont(new Font("x12y16pxMaruMonica", Font.PLAIN, 26));
		drawSubWindow(gp,g2,frameX, frameY, frameW, frameH);
		g2.setColor(Color.WHITE);
		g2.drawString("INVENTORY", 800,150);
		((Graphics2D) g2).setStroke(new BasicStroke(3));
		g2.drawRect(510, frameY+10, frameW-20, frameH-20);
		g2.drawRect(520, frameY+20, frameW-40, 50);
		//Slot
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 120;
		int slotX = slotXstart;
		int slotY = slotYstart;
		//draw item
		for(int i = 0; i < gp.player.inventory.size(); i++) {
			
			//Highlight the equipped item.
			if(gp.player.inventory.get(i) == gp.player.OnhandWP || 
					gp.player.inventory.get(i) == gp.player.Shield) {
				g2.setColor(new Color(240,190,90));
				g2.fillRect(slotX, slotY, 95, 95);
			}
			g2.drawImage(gp.player.inventory.get(i).InventoryImage, slotX, slotY,95,95, null);
			slotX += 95;
			if(i == 6 || i == 13 || i == 20 || i == 27 || i == 34 || i == 41) {
				slotX = slotXstart;
				slotY += 95;
			}
		}
		g2.drawString(""+gp.player.gold, 570, gp.tileSize*4+10);
		g2.drawImage(coin,520,gp.tileSize*4-20,50,50,null);
		if(cursor == true) {
			//Cursor
			int cursorX = slotXstart + (95 * slotCol);
			int cursorY = slotYstart + (95 * slotRow);
			int cursorWidth = 95;
			int cursorHeight = 95;
			g2.setColor(Color.GRAY);
			((Graphics2D) g2).setStroke(new BasicStroke(10));
			//g2.drawRect(cursorX, cursorY, cursorWidth, cursorHeight);
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight,10, 10);
			
			//Description Box
			int DframeX = 1215;
			int DframeY = gp.tileSize*2;
			int DframeW = 305;
			int DframeH = gp.tileSize*8;
			//description text
			int textY = gp.tileSize*2+120;
			int itemIndex = getItemIndexSlot(7,slotCol,slotRow);
			if(itemIndex < gp.player.inventory.size()) {
				drawSubWindow(gp,g2,DframeX, DframeY, DframeW, DframeH);
				g2.setColor(Color.WHITE);
				g2.drawString("DESCRIPTION", 1310,150);
				((Graphics2D) g2).setStroke(new BasicStroke(3));
				g2.drawRect(1225, DframeY+10, DframeW-20, DframeH-20);
				g2.drawRect(1235, DframeY+20, DframeW-40, 50);
				g2.drawString("[ "+gp.player.inventory.get(itemIndex).name+" ]", 1250, textY);
				for(String line: gp.player.inventory.get(itemIndex).discription.split("\n")) {
					g2.drawString(line, 1250, textY+32);
					textY += 32;
				}
			}
		}
	}
	public void drawInventorytrader(GamePanel gp,Graphics g2) {
		//Frame
		final int frameX = 10;
		final int frameY = gp.tileSize*2;
		final int frameW = gp.tileSize*9-15;
		final int frameH = gp.tileSize*15;
		g2.setFont(new Font("x12y16pxMaruMonica", Font.PLAIN, 26));
		drawSubWindow(gp,g2,frameX, frameY, frameW, frameH);
		g2.setColor(Color.WHITE);
		g2.drawString("TRADER", 190,150);
		((Graphics2D) g2).setStroke(new BasicStroke(3));
		g2.drawRect(20, frameY+10, frameW-20, frameH-20);
		g2.drawRect(30, frameY+20, frameW-40, 50);
		//Slot
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 120;
		int slotX = slotXstart;
		int slotY = slotYstart;
		//draw item
		for(int i = 0; i < gp.player.currentInteractNPC.inventory.size(); i++) {
			g2.drawImage(gp.player.currentInteractNPC.inventory.get(i).InventoryImage, slotX, slotY,95,95, null);
			slotX += 95;
			if(i == 3 || i == 7 || i == 11 || i == 15) {
				slotX = slotXstart;
				slotY += 95;
			}
		}
		
		//Cursor
		int cursorX = slotXstart + (95 * traderslotCol);
		int cursorY = slotYstart + (95 * traderslotRow);
		int cursorWidth = 95;
		int cursorHeight = 95;
		g2.setColor(Color.GRAY);
		((Graphics2D) g2).setStroke(new BasicStroke(10));
		//g2.drawRect(cursorX, cursorY, cursorWidth, cursorHeight);
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight,10, 10);
		
		//Description Box
		int DframeX = 1215;
		int DframeY = gp.tileSize*2;
		int DframeW = 305;
		int DframeH = gp.tileSize*8;
		//description text
		int textY = gp.tileSize*2+120;
		int itemIndex = getItemIndexSlot(4,traderslotCol,traderslotRow);
		if(itemIndex < gp.player.currentInteractNPC.inventory.size()) {
			drawSubWindow(gp,g2,DframeX, DframeY, DframeW, DframeH);
			g2.setColor(Color.WHITE);
			g2.drawString("DESCRIPTION", 1310,150);
			((Graphics2D) g2).setStroke(new BasicStroke(3));
			g2.drawRect(1225, DframeY+10, DframeW-20, DframeH-20);
			g2.drawRect(1235, DframeY+20, DframeW-40, 50);
			g2.drawString("[ "+gp.player.currentInteractNPC.inventory.get(itemIndex).name+" ]", 1250, textY);
			for(String line: gp.player.currentInteractNPC.inventory.get(itemIndex).discription.split("\n")) {
				g2.drawString(line, 1250, textY+32);
				textY += 32;
			}
			//Price window
			drawSubWindow(gp,g2,DframeX, DframeY+gp.tileSize*8+10, DframeW, gp.tileSize*2);
			g2.setColor(Color.WHITE);
			g2.drawString(""+gp.player.currentInteractNPC.inventory.get(itemIndex).price, 1350, DframeY+gp.tileSize*9+20);
			g2.drawString("Price: ", 1250, DframeY+gp.tileSize*9+20);
			g2.drawImage(coin,1300,DframeY+gp.tileSize*9-10,50,50,null);
			g2.drawRect(1225, DframeY+gp.tileSize*8+20, DframeW-20, gp.tileSize*2-20);
		}
	}
	public int getItemIndexSlot(int maxrow,int col,int row) {
		int itemIndex = col + (row*maxrow);
		return itemIndex;
	}
	public int AlignTextToRight(String text, int tailX, Graphics g2) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
	public void drawDialog(Graphics g2,String text,int x,int y) {
		int textY = y;
		g2.setFont(new Font("x12y16pxMaruMonica", Font.BOLD, 13));
		g2.setColor(new Color(0,0,0,180));
		g2.fillRoundRect(x, y-80, 120, 60,10, 10);
		g2.setColor(Color.white);
		((Graphics2D) g2).setStroke(new BasicStroke(3));
		g2.drawRoundRect(x, y-80, 120, 60, 10, 10);
	    textY -= 95;
		for(String line: text.split("\n")) {
			g2.drawString(line, x+10,textY+32);
			textY += 12;
		}
	}
	public void drawSubWindow(GamePanel gp,Graphics g2,int x,int y,int width,int height) {
		//Using Paint to define correct RGB,A is for opacity
		Color c = new Color(15,15,15,225);
		g2.setColor(c);
		g2.fillRect(x, y, width, height);	
	}

	public void drawTraderInventory(GamePanel gp,Graphics g2) {
		switch(subState) {
		case(0): trade_select(gp,g2);break;
		case(1): trade_buy(gp,g2);break;
		case(2): trade_sell(gp,g2);break;
		}
	}

	private void trade_buy(GamePanel gp,Graphics g2) {
		drawInventorytrader(gp,g2);
		drawInventory(gp,g2,false);
	}

	private void trade_sell(GamePanel gp,Graphics g2) {
		drawInventory(gp,g2,true);
	}

	private void trade_select(GamePanel gp,Graphics g2) {
		
		//sub window
		int x = gp.tileSize * 20;
		int y = gp.tileSize * 4;
		int w = gp.tileSize * 3;
		int h = gp.tileSize * 4-25;
		g2.setColor(new Color(15,15,15,255));
		g2.fillRoundRect(x, y, w, h,10,10);
		g2.setColor(Color.WHITE);
		((Graphics2D) g2).setStroke(new BasicStroke(3));
		g2.drawRoundRect(x, y, w, h,10,10);
		
		//Button
		g2.setColor(new Color(195,195,195));
		g2.fillRoundRect(x+10, y+10, w-20, 45,10,10);
		g2.fillRoundRect(x+10, y+60, w-20, 45,10,10);
		g2.fillRoundRect(x+10, y+110, w-20, 45,10,10);
		//text
		g2.setFont(new Font("x12y16pxMaruMonica", Font.BOLD, 35));
		g2.setColor(Color.BLACK);		
		g2.drawString("BUY", x+30, y+45);		
		g2.drawString("SELL", x+30, y+95);
		g2.drawString("LEAVE", x+30, y+145);
		g2.setColor(Color.WHITE);		
		if(SelectionCount == 1) {
			g2.drawRoundRect(x+10, y+10, w-20, 45,10,10);
		}
		if(SelectionCount == 2) {
			g2.drawRoundRect(x+10, y+60, w-20, 45,10,10);
		}
		if(SelectionCount == 3) {
			g2.drawRoundRect(x+10, y+110, w-20, 45,10,10);
		}
	}
}
