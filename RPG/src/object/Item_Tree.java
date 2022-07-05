package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Item_Tree extends MasterObject{
	
	GamePanel gp;
	
	public Item_Tree(GamePanel gp ) {
		this.gp = gp;
		name = "Tree";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/Tree.png"));
			utility.scaleImage(image,gp.tileSize*5,gp.tileSize*5);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = false;
	}

}
