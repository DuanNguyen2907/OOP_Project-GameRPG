package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Item_Key extends MasterObject{
	
	GamePanel gp;
	
	public Item_Key(GamePanel gp ) {
		this.gp = gp;
		name = "Key";
		try {
			InventoryImage = ImageIO.read(getClass().getResourceAsStream("/inventory/Key.png"));
			utility.scaleImage(InventoryImage,gp.tileSize,gp.tileSize);
			image = ImageIO.read(getClass().getResourceAsStream("/object/Key.png"));
			utility.scaleImage(image,gp.tileSize,gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
		type = "key";
	}
	public void interact(int i) {
		gp.obj[i] = null;//disappear
		gp.playSound(1);// i is the sound index in sound.java
		gp.ui.showMessage("key +1");
	}

}
