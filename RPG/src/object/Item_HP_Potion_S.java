package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Item_HP_Potion_S extends MasterObject{
	
	GamePanel gp;
	
	public Item_HP_Potion_S(GamePanel gp) {
		
		this.gp = gp;
		name = "Small Health Potion";
		try {
			InventoryImage = ImageIO.read(getClass().getResourceAsStream("/inventory/HP_Potion_S.png"));
			utility.scaleImage(InventoryImage,gp.tileSize,gp.tileSize);
			image = ImageIO.read(getClass().getResourceAsStream("/object/HP_Potion_S.png"));
			utility.scaleImage(image,gp.tileSize,gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
		type = "consumable";
	}
	public void interact(int i) {
		gp.player.HP += 15;
		if(gp.player.HP < gp.player.MaxHP) {
		}else {
			gp.player.HP = gp.player.MaxHP;
		}
	}

}
