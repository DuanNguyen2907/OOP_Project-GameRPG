package main;

import character.Character;

public class CollisionCheck {
	
	GamePanel gp;
	
	public CollisionCheck(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Character character) {
		//coordinate of x left x right y top y bottom line that indicate character collision Box (square)
		int CharLeftWorldX = character.worldX + character.collisionBox.x;//left edge of character's sprite 
		int CharRightWorldX = character.worldX + character.collisionBox.x + character.collisionBox.width;//right edge of character's sprite
		int CharTopWorldY = character.worldY + character.collisionBox.y;//top edge of character's sprite
		int CharBottomWorldY = character.worldY + character.collisionBox.y + character.collisionBox.height;//bottom edge of character's sprite
		
		//From those coordinate we can divide to tile Size to find which tile is it on the 2D array map
		int CharLeftCol = CharLeftWorldX/gp.tileSize;
		int CharRightCol = CharRightWorldX/gp.tileSize;
		int CharTopRow = CharTopWorldY/gp.tileSize;
		int CharBottomRow = CharBottomWorldY/gp.tileSize;
		
		int tile1 = 0,tile2 = 0,tile3 = 0;
		//Check 2 tile in front of player depend on direction// if 1 of those 2 tile have collision -> Turn player collision On(Disable movement)
		switch(character.direction) {
		case "up":
			CharTopRow = (CharTopWorldY - character.speed)/gp.tileSize;
			tile1 = gp.tile.mapTileNum[gp.currentMap][CharLeftCol][CharTopRow];
			tile2 = gp.tile.mapTileNum[gp.currentMap][CharRightCol][CharTopRow];
			if(gp.tile.tile[tile1].collision == true || gp.tile.tile[tile2].collision == true) {
				character.collisionOn = true;
			}
			break;
		case "down":
			CharBottomRow = (CharBottomWorldY + character.speed)/gp.tileSize;
			tile1 = gp.tile.mapTileNum[gp.currentMap][CharLeftCol][CharBottomRow];
			tile2 = gp.tile.mapTileNum[gp.currentMap][CharRightCol][CharBottomRow];
			if(gp.tile.tile[tile1].collision == true || gp.tile.tile[tile2].collision == true) {
				character.collisionOn = true;
			}			
			break;
		case "left":
			CharLeftCol = (CharLeftWorldX - character.speed)/gp.tileSize;
			tile1 = gp.tile.mapTileNum[gp.currentMap][CharLeftCol][CharTopRow];
			tile2 = gp.tile.mapTileNum[gp.currentMap][CharLeftCol][CharBottomRow];
			if(gp.tile.tile[tile1].collision == true || gp.tile.tile[tile2].collision == true) {
				character.collisionOn = true;
			}
			break;
		case "right":
			CharRightCol = (CharRightWorldX + character.speed)/gp.tileSize;
			tile1 = gp.tile.mapTileNum[gp.currentMap][CharRightCol][CharTopRow];
			tile2 = gp.tile.mapTileNum[gp.currentMap][CharRightCol][CharBottomRow];
			if(gp.tile.tile[tile1].collision == true || gp.tile.tile[tile2].collision == true) {
				character.collisionOn = true;
			}
			break;
		case "upright":
			CharRightCol = (CharRightWorldX + character.speed)/gp.tileSize;
			tile1 = gp.tile.mapTileNum[gp.currentMap][CharRightCol][CharTopRow];
			tile2 = gp.tile.mapTileNum[gp.currentMap][CharRightCol][CharBottomRow];
			tile3 = gp.tile.mapTileNum[gp.currentMap][CharRightCol][CharTopRow-1];
			if(gp.tile.tile[tile1].collision == true || gp.tile.tile[tile2].collision == true || gp.tile.tile[tile3].collision == true) {
				character.collisionOn = true;
			}	
			break;
		case "upleft":
			CharLeftCol = (CharLeftWorldX - character.speed)/gp.tileSize;
			tile1 = gp.tile.mapTileNum[gp.currentMap][CharLeftCol][CharTopRow];
			tile2 = gp.tile.mapTileNum[gp.currentMap][CharLeftCol][CharBottomRow];
			tile3 = gp.tile.mapTileNum[gp.currentMap][CharLeftCol][CharTopRow+1];
			if(gp.tile.tile[tile1].collision == true || gp.tile.tile[tile2].collision == true || gp.tile.tile[tile3].collision == true) {
				character.collisionOn = true;
			}
			break;
		case "downright":
			CharRightCol = (CharRightWorldX + character.speed)/gp.tileSize;
			tile1 = gp.tile.mapTileNum[gp.currentMap][CharRightCol][CharTopRow];
			tile2 = gp.tile.mapTileNum[gp.currentMap][CharRightCol][CharBottomRow];
			tile3 = gp.tile.mapTileNum[gp.currentMap][CharRightCol][CharBottomRow-1];
			if(gp.tile.tile[tile1].collision == true || gp.tile.tile[tile2].collision == true || gp.tile.tile[tile3].collision == true) {
				character.collisionOn = true;
			}	
			break;
		case "downleft":
			CharLeftCol = (CharLeftWorldX - character.speed)/gp.tileSize;
			tile1 = gp.tile.mapTileNum[gp.currentMap][CharLeftCol][CharTopRow];
			tile2 = gp.tile.mapTileNum[gp.currentMap][CharLeftCol][CharBottomRow];
			tile3 = gp.tile.mapTileNum[gp.currentMap][CharLeftCol][CharBottomRow+1];
			if(gp.tile.tile[tile1].collision == true || gp.tile.tile[tile2].collision == true || gp.tile.tile[tile3].collision == true) {
				character.collisionOn = true;
			}
			break;
		}
		//System.out.printf("%d-%d-%d\n",tile1,tile2,tile3);
	}
	public int CheckObject(Character character,boolean player) {
		int index = 999;
		for(int i = 0; i < gp.obj[1].length ; i++) {
			if(gp.obj[gp.currentMap][i] != null) {
				
				//get Char collision position
				character.collisionBox.x = character.worldX + character.collisionDefaultX;
				character.collisionBox.y = character.worldY + character.collisionDefaultY;
				
				//get object collision position
				gp.obj[gp.currentMap][i].collisionBox.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].collisionBox.x;
				gp.obj[gp.currentMap][i].collisionBox.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].collisionBox.y;
				
				
				//if char touch object then i will shift to non 999 number
				switch(character.direction) {
				case"up":
					character.collisionBox.y -= character.speed;
					if(character.collisionBox.intersects(gp.obj[gp.currentMap][i].collisionBox)) {
						if(gp.obj[gp.currentMap][i].collision == true) {
							character.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case"down":
					character.collisionBox.y += character.speed;
					if(character.collisionBox.intersects(gp.obj[gp.currentMap][i].collisionBox)) {
						if(gp.obj[gp.currentMap][i].collision == true) {
							character.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case"left":
					character.collisionBox.x -= character.speed;
					if(character.collisionBox.intersects(gp.obj[gp.currentMap][i].collisionBox)) {
						if(gp.obj[gp.currentMap][i].collision == true) {
							character.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case"right":
					character.collisionBox.x += character.speed;
					if(character.collisionBox.intersects(gp.obj[gp.currentMap][i].collisionBox)) {
						if(gp.obj[gp.currentMap][i].collision == true) {
							character.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "upright":
					character.collisionBox.y -= character.speed;
					character.collisionBox.x += character.speed;
					if(character.collisionBox.intersects(gp.obj[gp.currentMap][i].collisionBox)) {
						if(gp.obj[gp.currentMap][i].collision == true) {
							character.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "upleft":
					character.collisionBox.y -= character.speed;
					character.collisionBox.x -= character.speed;
					if(character.collisionBox.intersects(gp.obj[gp.currentMap][i].collisionBox)) {
						if(gp.obj[gp.currentMap][i].collision == true) {
							character.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "downright":
					character.collisionBox.y += character.speed;
					character.collisionBox.x += character.speed;
					if(character.collisionBox.intersects(gp.obj[gp.currentMap][i].collisionBox)) {
						if(gp.obj[gp.currentMap][i].collision == true) {
							character.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "downleft":
					character.collisionBox.y += character.speed;
					character.collisionBox.x -= character.speed;
					if(character.collisionBox.intersects(gp.obj[gp.currentMap][i].collisionBox)) {
						if(gp.obj[gp.currentMap][i].collision == true) {
							character.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				}
				character.collisionBox.x = character.collisionDefaultX;
				character.collisionBox.y = character.collisionDefaultY;
				gp.obj[gp.currentMap][i].collisionBox.x = gp.obj[gp.currentMap][i].collisionBoxDefaultX;
				gp.obj[gp.currentMap][i].collisionBox.y = gp.obj[gp.currentMap][i].collisionBoxDefaultY;	
			}
			
		}
		
		return index;
	}
	public int checkCharacter(character.Character character,character.Character[][] target) {
		int index = 999;
		for(int i = 0; i < target[1].length ; i++) {
			if(target[gp.currentMap][i] != null) {
				
				//get Char collision position
				character.collisionBox.x = character.worldX + character.collisionDefaultX;
				character.collisionBox.y = character.worldY + character.collisionDefaultY;
				
				//get object collision position
				target[gp.currentMap][i].collisionBox.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].collisionBox.x;
				target[gp.currentMap][i].collisionBox.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].collisionBox.y;
				
				
				//if char touch object then i will shift to non 999 number
				switch(character.direction) {
				case"up":
					character.collisionBox.y -= character.speed;
					if(character.collisionBox.intersects(target[gp.currentMap][i].collisionBox)) {
						character.collisionOn = true;				
						index = i;
					}
					break;
				case"down":
					character.collisionBox.y += character.speed;
					if(character.collisionBox.intersects(target[gp.currentMap][i].collisionBox)) {
						character.collisionOn = true;				
						index = i;
					}
					break;
				case"left":
					character.collisionBox.x -= character.speed;
					if(character.collisionBox.intersects(target[gp.currentMap][i].collisionBox)) {
						character.collisionOn = true;				
						index = i;
					}
					break;
				case"right":
					character.collisionBox.x += character.speed;
					if(character.collisionBox.intersects(target[gp.currentMap][i].collisionBox)) {
						character.collisionOn = true;				
						index = i;
					}
					break;
				case "upright":
					character.collisionBox.y -= character.speed;
					character.collisionBox.x += character.speed;
					if(character.collisionBox.intersects(target[gp.currentMap][i].collisionBox)) {
						character.collisionOn = true;				
						index = i;
					}
					break;
				case "upleft":
					character.collisionBox.y -= character.speed;
					character.collisionBox.x -= character.speed;
					if(character.collisionBox.intersects(target[gp.currentMap][i].collisionBox)) {
						character.collisionOn = true;				
						index = i;
					}
					break;
				case "downright":
					character.collisionBox.y += character.speed;
					character.collisionBox.x += character.speed;
					if(character.collisionBox.intersects(target[gp.currentMap][i].collisionBox)) {
						character.collisionOn = true;				
						index = i;
					}
					break;
				case "downleft":
					character.collisionBox.y += character.speed;
					character.collisionBox.x -= character.speed;
					if(character.collisionBox.intersects(target[gp.currentMap][i].collisionBox)) {
						character.collisionOn = true;				
						index = i;
					}
					break;
				}
				
				character.collisionBox.x = character.collisionDefaultX;
				character.collisionBox.y = character.collisionDefaultY;
				target[gp.currentMap][i].collisionBox.x = target[gp.currentMap][i].collisionDefaultX;
				target[gp.currentMap][i].collisionBox.y = target[gp.currentMap][i].collisionDefaultY;	
			}
			
		}
		
		return index;
	}
	public void checkPlayer(character.Character character) {
		character.collisionBox.x = character.worldX + character.collisionDefaultX;
		character.collisionBox.y = character.worldY + character.collisionDefaultY;
		
		//get object collision position
		gp.player.collisionBox.x = gp.player.worldX + gp.player.collisionBox.x;
		gp.player.collisionBox.y = gp.player.worldY + gp.player.collisionBox.y;
		
		
		//if char touch object then i will shift to non 999 number
		switch(character.direction) {
		case"up":
			character.collisionBox.y -= character.speed;
			if(character.collisionBox.intersects(gp.player.collisionBox)) {
				character.collisionOn = true;				
			}
			break;
		case"down":
			character.collisionBox.y += character.speed;
			if(character.collisionBox.intersects(gp.player.collisionBox)) {
				character.collisionOn = true;				
			}
			break;
		case"left":
			character.collisionBox.x -= character.speed;
			if(character.collisionBox.intersects(gp.player.collisionBox)) {
				character.collisionOn = true;				
			}
			break;
		case"right":
			character.collisionBox.x += character.speed;
			if(character.collisionBox.intersects(gp.player.collisionBox)) {
				character.collisionOn = true;				
			}
			break;
		}
		character.collisionBox.x = character.collisionDefaultX;
		character.collisionBox.y = character.collisionDefaultY;
		gp.player.collisionBox.x = gp.player.collisionDefaultX;
		gp.player.collisionBox.y = gp.player.collisionDefaultY;	
	
	}
	public boolean checkPlayerCol(character.Character character) {
		boolean isPlayer = false;
		character.collisionBox.x = character.worldX + character.collisionDefaultX;
		character.collisionBox.y = character.worldY + character.collisionDefaultY;
		
		//get object collision position
		gp.player.collisionBox.x = gp.player.worldX + gp.player.collisionBox.x;
		gp.player.collisionBox.y = gp.player.worldY + gp.player.collisionBox.y;
		
		
		//if char touch object then i will shift to non 999 number
		switch(character.direction) {
		case"up":
			character.collisionBox.y -= character.speed;
			if(character.collisionBox.intersects(gp.player.collisionBox)) {
				character.collisionOn = true;	
				isPlayer = true;	
			}	
			break;
		case"down":
			character.collisionBox.y += character.speed;
			if(character.collisionBox.intersects(gp.player.collisionBox)) {
				character.collisionOn = true;	
				isPlayer = true;	
			}
			break;
		case"left":
			character.collisionBox.x -= character.speed;
			if(character.collisionBox.intersects(gp.player.collisionBox)) {
				character.collisionOn = true;	
				isPlayer = true;	
			}
			break;
		case"right":
			character.collisionBox.x += character.speed;
			if(character.collisionBox.intersects(gp.player.collisionBox)) {
				character.collisionOn = true;	
				isPlayer = true;	
			}	
			break;
		}
		character.collisionBox.x = character.collisionDefaultX;
		character.collisionBox.y = character.collisionDefaultY;
		gp.player.collisionBox.x = gp.player.collisionDefaultX;
		gp.player.collisionBox.y = gp.player.collisionDefaultY;	
		return isPlayer;
	}
}
