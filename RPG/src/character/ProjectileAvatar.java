package character;

import main.GamePanel;

public class ProjectileAvatar extends Character{

	public ProjectileAvatar(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
	}
	public void update() {
		gp.Colchecker.checkCharacter(this,gp.npc);
	}

}
