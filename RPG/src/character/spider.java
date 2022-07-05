package character;

import java.awt.Color;
import java.awt.Graphics2D;
import main.GamePanel;

public class spider extends Monster{

	public spider(GamePanel gp) {
		super(gp);
		MaxHP = 25;
		HP = 25;
		name = "spider";
		lv = 2;
		direction = "down";
		speed = 1;
		
		graphic.getImageMonster("spider");
	}
	public void update() {
		if(activated == true) {
			cooldown++;
			if(cooldown == 60) {
				activated = false;
				cooldown = 0;
			}
		}
		setAction();
		gp.Colchecker.checkTile(this);
		gp.Colchecker.checkPlayer(this);
		graphic.updateDirection(this,10,3);
	}
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		if(HP > 0) {
			graphic.drawCharacter(this, g2,gp);
			graphic.drawName(this, g2, name, gp, 22,Color.WHITE);
			graphic.drawLv(this, g2, lv, gp);
		}else {
			//Death Anim
			AnimCounter++;
			if(AnimCounter <= 5 ) {
				graphic.AnimFX(g2,1,screenX,screenY);			
			}
			if(AnimCounter > 5 && AnimCounter < 15) {
				graphic.AnimFX(g2,2,screenX,screenY);
				
			}
			if(AnimCounter >= 15 && AnimCounter < 20) {
				graphic.AnimFX(g2,3,screenX,screenY);
			}
			if(AnimCounter >= 20) {
				AnimCounter = 0;
				gp.mob[OnmapIndex] = null;
			}
		}
		if(hpbarOn == true) {
			hpbartimer ++;
			graphic.drawHP(g2, gp, this);
			if(hpbartimer > 600) {
				hpbartimer = 0;
				hpbarOn = false;
			}
		}
		graphic.drawMonster(this, g2,gp,3,0,1,2);
	}
}
