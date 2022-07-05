package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Item_Speed_Potion extends MasterObject{
	
	GamePanel gp;
	
	public Item_Speed_Potion(GamePanel gp) {
		
		this.gp = gp;
		name = "Speed_Potion";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/Speed_Potion.png"));
			utility.scaleImage(image,gp.tileSize,gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
		type = "consumable";
	}
	public void interact(int i) {
		gp.player.speed += 2;
		gp.obj[i] = null;
		gp.playSound(1);
		gp.ui.showMessage("speed +2");
	}

}
