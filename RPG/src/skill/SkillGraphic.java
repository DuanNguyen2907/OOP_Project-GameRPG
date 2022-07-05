package skill;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.Utility;

public class SkillGraphic {
	
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	int spriteCounter;
	int spriteNum;
	public void setImage(String filename) {
		up1 = setup(filename + "/Bloodslash3");
		up2 = setup(filename + "/Bloodslash3");
		down1 = setup(filename + "/Bloodslash1");
		down2 = setup(filename + "/Bloodslash1");
		left1 = setup(filename + "/Bloodslash4");
		left2 = setup(filename + "/Bloodslash4");
		right1 = setup(filename + "/Bloodslash2");
		right2 = setup(filename + "/Bloodslash2");
	}
	public BufferedImage setup(String imagename) {
    	Utility utility = new Utility();
		BufferedImage Image = null;
		
		try {
			Image = ImageIO.read(getClass().getResourceAsStream(imagename + ".png"));
			Image = utility.scaleImage(Image,48,48);
			
		}catch(IOException e) {
			e.printStackTrace();
		}   	   	
    	return Image;   	
		
	}
	public void UpdateDirection(Projectile p,int interval, int NumIndex) {
		switch(p.direction) {
		case"up":  p.y -= p.speed;break;
		case"down": p.y += p.speed;break;
		case"left": p.x -= p.speed;break;
		case"right": p.x += p.speed;break;
		}
		spriteCounter++;
		if(spriteCounter > interval) {
			spriteNum++;
			if(spriteNum > NumIndex) {
				spriteNum = 1; 
			}
			spriteCounter = 0;
		}
	}
	public void draw(Graphics2D g2,GamePanel gp, Projectile c) {
		BufferedImage image = null;
		int screenX = c.x - gp.player.worldX + gp.player.screenX;
		int screenY = c.y - gp.player.worldY + gp.player.screenY;
		
		//Only create object that visible on screen
		if(c.x + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   c.x - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   c.y + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   c.y - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
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
			g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
	
		}
	}
}

