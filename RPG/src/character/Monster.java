package character;

import java.awt.image.BufferedImage;
import java.util.Random;

import main.Content;
import main.GamePanel;


public class Monster extends Character {
	public String name;
	
	
	boolean right = true;
	static int random;
	protected BufferedImage[][] sprite1;
	int ActionCounter;
	boolean hpbarOn;
	int hpbartimer;
	int OnmapIndex;
	boolean death = false;
	boolean activated = false;
	int AnimCounter;
	int cooldown;

	
	public Monster(GamePanel gp) {
		super(gp);
		direction = "right" ;
		speed = 1;
		//getMonsterImage();
	}
	public void getMonsterImage() {
		switch(this.name) {
		case "spider":
			sprite1 = new BufferedImage[4][3];
			for(int i = 0;i < 4 ; i++){
				for(int j = 0; j < 3; j++){
					sprite1[i][j] = Content.MONSTER1[i][j];
				}
			}
			break;
		case "slame":
			sprite1 = new BufferedImage[4][3];
			for(int i = 0;i < 4 ; i++){
				for(int j = 9; j < 12; j++){
					sprite1[i][j-9] = Content.MONSTER1[i][j];	
				}
			}
			break;
		case "bird":
			sprite1 = new BufferedImage[4][3];
			for(int i = 4;i < 8 ; i++){
				for(int j = 0; j < 3; j++){
					sprite1[i-4][j] = Content.MONSTER1[i][j];
				}
			}
			break;
		}
	}

	public void contact(character.Character c) {
		if(activated == false) {
			c.HP -= ATK - c.DEF ;
			activated = true;
		}
	}
	
	public void setAction() {
		ActionCounter++;
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