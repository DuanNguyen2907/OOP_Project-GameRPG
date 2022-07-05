package main;

public class EventHandler {
	
	GamePanel gp;
	EventRectangle eventRect[][][];
	
	int previousEventX,previousEventY;
	boolean canReTrigger = true;
	
	int FrameCounter = 0;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRectangle[10][gp.maxWorldCol][gp.maxWorldRow];
		
		int col = 0;
		int row = 0;
		int map = 0;
		
		while(map < 10 && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[map][col][row] = new EventRectangle();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 2;
			eventRect[map][col][row].height = 2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
		
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
				
				if(row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
	
	}
	int i;
	boolean executed;
	public void checkEvent() {
		
		FrameCounter++;
		if(FrameCounter == 40) {
			FrameCounter = 0;
		}
		if( hit(0,4,3) == true ) {
			tp(1,25,22);
		}
		if( hit(1,25,24) == true ) {
			tp(0,4,4);
		}
		if( hit(0,5,6) == true ) {damagePit();}
		if( hit(0,6,7) == true ) {healingWell();}
		if( hit(0,8,8) == true ) {
			setspawnpoint(8,8);
			if(executed == false) {
				executed = true;
				gp.ui.addMessage("F to set spawn point");
				}
		}else {executed = false;}
	}
	private void tp(int map, int row,int col) {
		// TODO Auto-generated method stub
		gp.currentMap = map;
		gp.player.worldX = gp.tileSize*row;
		gp.player.worldY = gp.tileSize*col;
	}
	private void setspawnpoint(int col,int row) {
		// TODO Auto-generated method stub
		if(gp.key.interactKey == true) {
			gp.ui.addMessage("Spawn point set");
			gp.player.spawnX = gp.tileSize*col;
			gp.player.spawnY = gp.tileSize*row;
		}
		gp.key.interactKey = false;
	}
	public boolean hit(int map,int col,int row) {
		
		boolean hit = false;
		if(map == gp.currentMap) {
			gp.player.collisionBox.x = gp.player.worldX + gp.player.collisionBox.x;
			gp.player.collisionBox.y = gp.player.worldY + gp.player.collisionBox.y;
			eventRect[gp.currentMap][col][row].x = col*gp.tileSize + eventRect[gp.currentMap][col][row].x;
			eventRect[gp.currentMap][col][row].y = row*gp.tileSize + eventRect[gp.currentMap][col][row].y;
			
			if(gp.player.collisionBox.intersects(eventRect[gp.currentMap][col][row]) ) {
				hit = true;

			}else {
				//eventRect[col][row].Executed = false;//for One Time Event
		
			}
			
			gp.player.collisionBox.x = gp.player.collisionDefaultX;
			gp.player.collisionBox.y = gp.player.collisionDefaultY;
			eventRect[gp.currentMap][col][row].x = eventRect[gp.currentMap][col][row].eventRectDefaultX;
			eventRect[gp.currentMap][col][row].y = eventRect[gp.currentMap][col][row].eventRectDefaultY;
			
		}
		return hit;
	}
	
    //OnTouch Event
	public void damagePit() {
		if(FrameCounter == 0) {
			if(gp.player.HP > 0 ) {
				gp.player.HP -= 15;
			}
		}
	}
	//OnTouch & PressKey Event
	public void healingWell() {
		if(gp.key.interactKey == true) {
			if(gp.player.HP < gp.player.MaxHP) {
				gp.player.HP += 1;
			}
		}
		gp.key.interactKey = false;
	}
}
