package character;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;
import object.Item_Coin;
import skill.Bloodslash;
import skill.PoisonSpit;
import skill.Projectile;

public class GreenSlime extends Character{
	public int ActionCounter;
	int AnimCounter;
	int AtkCounter;
	int cooldown;
	boolean activated = false;
	boolean hpbarOn;
	int hpbartimer;
	int OnmapIndex;
	boolean death = false;
	Random randomatk = new Random();
	int j = randomatk.nextInt(120)+40;
	public GreenSlime(GamePanel gp) {
		super(gp);
		this.name = "Green Slime";
		direction = "down";
		speed = 1;
		lv = 1;
		MaxHP = 40;
		HP = MaxHP;
		ATK = 10;
		
		//Set Image
		graphic.up1 = graphic.setup("/monster/slime1");
		graphic.up2 = graphic.setup("/monster/slime2");
		graphic.down1 = graphic.setup("/monster/slime1");
		graphic.down2 = graphic.setup("/monster/slime2");
		graphic.left1 = graphic.setup("/monster/slime1");
		graphic.left2 = graphic.setup("/monster/slime2");
		graphic.right1 = graphic.setup("/monster/slime1");
		graphic.right2 = graphic.setup("/monster/slime2");
		graphic.idle1 = graphic.setup("/monster/slime1");
		graphic.idle2 = graphic.setup("/monster/slime2");
	}
	public void setAction() {
		ActionCounter++;
		AtkCounter++;		
		if(AtkCounter == j) {
			Projectile poison = new PoisonSpit(gp);
			gp.projectileList.add(poison);
			poison.set(worldX,worldY,direction,this);
			AtkCounter = 0;
		}
		if(ActionCounter == 60) {
			ActionCounter = 0;
			
			Random random = new Random();
			int i = random.nextInt(100)+1;// get a number from 1 to 100
			if(i <= 25) {
				direction = "down";
			}
			if(i > 25 && i <= 50) {
				direction = "up";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
		}	
	}
	public void contact(character.Character c) {
		if(activated == false) {
			c.HP -= ATK - c.DEF ;
			activated = true;
		}
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
		graphic.updateDirection(this, 20,2);
	}
	public void draw(Graphics2D g2) {

		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		if(HP > 0) {
			graphic.drawCharacter(this, g2,gp);
			graphic.drawName(this, g2, name, gp, 22,Color.WHITE);
			graphic.drawLv(this, g2, lv, gp);
		}else {
			//Death Animation
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
				CheckDrop();
				gp.npc[gp.currentMap][OnmapIndex] = null;
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
		//graphic.drawCollision(g2, screenX+collisionBox.x, screenY+collisionBox.y, collisionBox.width,collisionBox.height);
	}
	public void hitted(int dmg,int i) {
		HP -= dmg; 
		OnmapIndex = i;
		hpbarOn = true;
		direction = gp.player.direction;
		gp.ui.addMessage(""+dmg);
		hpbartimer = 0;
		if(HP <= 0) {
			gp.player.exp += 2*lv;
			gp.player.LevelUp();
			gp.ui.addMessage("+ "+ 2*lv+" exp");
			death = true;
			speed = 0;
		}
	}
}
