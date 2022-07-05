package main;


import character.Bat;
import character.Bufalo;
import character.GreenSlime;
import character.NPC_1;
import character.flame;
import character.spider;
import object.Item_Blood_Katana;
import object.Item_HP_Potion_B;
import object.Item_HP_Potion_S;
import object.Item_Key;
import object.Item_LightSaber;
import object.Item_Speed_Potion;

public class ItemGenerator {
	//Item and NPC generate
	GamePanel gp;
	
	public ItemGenerator(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new Item_Key(gp);
		gp.obj[0].worldX = 5 * gp.tileSize;
		gp.obj[0].worldY = 7 * gp.tileSize;
		
		gp.obj[1] = new Item_Key(gp);
		gp.obj[1].worldX = 18 * gp.tileSize;
		gp.obj[1].worldY = 20 * gp.tileSize;
		
		gp.obj[2] = new Item_Speed_Potion(gp);
		gp.obj[2].worldX = 8 * gp.tileSize;
		gp.obj[2].worldY = 16 * gp.tileSize;
		
		gp.obj[3] = new Item_HP_Potion_S(gp);
		gp.obj[3].worldX = 8 * gp.tileSize;
		gp.obj[3].worldY = 8 * gp.tileSize;

		gp.obj[4] = new Item_HP_Potion_B(gp);
		gp.obj[4].worldX = 7 * gp.tileSize;
		gp.obj[4].worldY = 7 * gp.tileSize;
		
		gp.obj[5] = new Item_Blood_Katana(gp);
		gp.obj[5].worldX = 7 * gp.tileSize;
		gp.obj[5].worldY = 8 * gp.tileSize;
		
		gp.obj[6] = new Item_LightSaber(gp);
		gp.obj[6].worldX = 17 * gp.tileSize;
		gp.obj[6].worldY = 8 * gp.tileSize;
	}
	public void setNPC() {
		
		gp.npc[0] = new NPC_1(gp, "Markus");
		gp.npc[0].worldX = gp.tileSize*18;
		gp.npc[0].worldY = gp.tileSize*18;
		
		gp.npc[1] = new NPC_1(gp, "Levian");
		gp.npc[1].worldX = gp.tileSize*17;
		gp.npc[1].worldY = gp.tileSize*18;
		
		gp.npc[2] = new NPC_1(gp, "Jonathan");
		gp.npc[2].worldX = gp.tileSize*16;
		gp.npc[2].worldY = gp.tileSize*18;
		
		gp.npc[3] = new NPC_1(gp, "Ewon");
		gp.npc[3].worldX = gp.tileSize*5;
		gp.npc[3].worldY = gp.tileSize*9;
		
		gp.npc[4] = new GreenSlime(gp);
		gp.npc[4].worldX = gp.tileSize*18;
		gp.npc[4].worldY = gp.tileSize*18;
	
		gp.mob[1] = new Bat(gp);
		gp.mob[1].worldX = gp.tileSize*7;
		gp.mob[1].worldY = gp.tileSize*7;
		
		gp.mob[2] = new spider(gp);
		gp.mob[2].worldX = gp.tileSize*6;
		gp.mob[2].worldY = gp.tileSize*7;
		
		gp.mob[3] = new Bufalo(gp);
		gp.mob[3].worldX = gp.tileSize*7;
		gp.mob[3].worldY = gp.tileSize*5;
		
		gp.mob[4] = new flame(gp);
		gp.mob[4].worldX = gp.tileSize*15;
		gp.mob[4].worldY = gp.tileSize*20;
		
    }
}

	
