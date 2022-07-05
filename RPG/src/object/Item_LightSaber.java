package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Item_LightSaber extends MasterObject{
    GamePanel gp;
    
	public Item_LightSaber(GamePanel gp) {
		
		this.gp = gp;
		downFX = "slash1";
		rightFX = "slash4";
		upFX = "slash7";
		leftFX = "slash10";
		name = "Lightsaber";
		try {
			InventoryImage = ImageIO.read(getClass().getResourceAsStream("/inventory/Lightsaber.png"));
			utility.scaleImage(InventoryImage,gp.tileSize,gp.tileSize);
			image = ImageIO.read(getClass().getResourceAsStream("/object/Lightsaber.png"));
			utility.scaleImage(image,gp.tileSize,gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
		ATK = 50;
		range = 40;
		type = "weapon";
		discription = "Acient cursed weapon. Comsume user \nblood to channeling energy.";
	}
}
