package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Item_Wooden_Shield extends MasterObject{
	
GamePanel gp;
	
	public Item_Wooden_Shield(GamePanel gp) {
		
		this.gp = gp;
		name = "Wooden Shield";
		try {
			InventoryImage = ImageIO.read(getClass().getResourceAsStream("/inventory/Wooden_Shield.png"));
			utility.scaleImage(InventoryImage,gp.tileSize,gp.tileSize);
			image = ImageIO.read(getClass().getResourceAsStream("/object/Wooden_Shield.png"));
			utility.scaleImage(image,gp.tileSize,gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
		DEF = 1;
		type = "shield";
	}

}
