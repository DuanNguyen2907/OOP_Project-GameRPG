package skill;

import java.awt.Graphics2D;

import character.Player;
import main.GamePanel;

public class Bloodslash extends Projectile{
    int counter = 0;
	public Bloodslash(GamePanel gp) {
		super(gp);
		speed = 15;
		graphic.setImage("/skill");
		active = true;
		direction = gp.player.direction;
		// TODO Auto-generated constructor stub
	}
	public void update() {
		counter++;
		if(counter > 120) {
			active = false;
		}
		graphic.UpdateDirection(this, 12, 2);
		avatar.worldX = x;
		avatar.worldY = y;
		int mobIndex = gp.Colchecker.checkCharacter(avatar, gp.npc);
		if(mobIndex != 999) {
			gp.npc[mobIndex].hitted(50,mobIndex);
		}
	}
	public void draw(Graphics2D g2) {
		graphic.draw(g2, gp, this);
	}
	public void set(int x,int y, String direction, Player p) {
		avatar.direction = gp.player.direction;
		active = true;
		this.x = x;
		this.y = y;
		
		this.direction = direction;
	}
	
}