package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Item_Door extends MasterObject{
	
	GamePanel gp;
	
	public Item_Door(GamePanel gp) {
		this.gp = gp;
		name = "Door";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/object/Door.png"));
			utility.scaleImage(image,gp.tileSize,gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
