package skill;

import java.awt.Graphics2D;

import character.Player;
import character.ProjectileAvatar;
import main.GamePanel;

public class Projectile {
	
	int dmg;
	public int speed;
	public boolean active;
	int MPcost;
	String name;
	SkillGraphic graphic = new SkillGraphic();
	character.Character avatar;
	GamePanel gp;
	int x,y;
	String direction;
	
	public Projectile(GamePanel gp) {
		this.gp = gp;
		avatar = new ProjectileAvatar(gp);
	}
	public void update() {
		
	}
	public void set(int x,int y, String direction, Character c) {
		
	}
    public void set(int x,int y, String direction, Player p) {
		
	}
    public void draw(Graphics2D g2) {
    	
    }

}
