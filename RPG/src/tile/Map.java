package tile;

import main.GamePanel;

public class Map {
	GamePanel gp;
	public TileManager tile = new TileManager(gp);
	
	public Map(GamePanel gp) {
		this.gp = gp;
	}
	
	public void loadMap(String name) {
		tile.loadMap("/map/"+ name);
	}

}
