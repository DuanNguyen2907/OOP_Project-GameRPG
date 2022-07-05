package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Item_Iron_Sword extends MasterObject{
    GamePanel gp;

	public Item_Iron_Sword(GamePanel gp) {
		
		this.gp = gp;
		name = "Iron Sword";
		downFX = "slash1";
		rightFX = "slash4";
		upFX = "slash7";
		leftFX = "slash10";
		try {
			InventoryImage = ImageIO.read(getClass().getResourceAsStream("/inventory/Iron_Sword.png"));
			utility.scaleImage(InventoryImage,gp.tileSize,gp.tileSize);
			image = ImageIO.read(getClass().getResourceAsStream("/object/Iron_Sword.png"));
			utility.scaleImage(image,gp.tileSize,gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
		ATK = 5;
		range = 36;
		type = "weapon";
		discription = "An old odinary melee weapon of a\n warrior. Nothing special.";
	}

}
