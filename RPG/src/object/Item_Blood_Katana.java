package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import skill.Bloodslash;
import skill.Projectile;

public class Item_Blood_Katana extends MasterObject{
    GamePanel gp;
    Projectile slash;
    
	public Item_Blood_Katana(GamePanel gp) {
		
		this.gp = gp;
		downFX = "slashblood1";
		rightFX = "slashblood2";
		upFX = "slashblood3";
		leftFX = "slashblood4";
		name = "River of Blood";
		try {
			InventoryImage = ImageIO.read(getClass().getResourceAsStream("/inventory/Blood_Katana.png"));
			utility.scaleImage(InventoryImage,gp.tileSize,gp.tileSize);
			image = ImageIO.read(getClass().getResourceAsStream("/object/Blood_Katana.png"));
			utility.scaleImage(image,gp.tileSize,gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
		ATK = 250;
		range = 50;
		type = "weapon";
		discription = "Acient cursed weapon. Comsume user \nblood to channeling energy.";
	}
	public void effect(character.Character c) {
		Projectile slash = new Bloodslash(gp);
		gp.projectileList.add(slash);
		slash.set(gp.player.worldX,gp.player.worldY,gp.player.direction,gp.player);
		c.HP -= 10;
	}

}
