package character;

import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;
import object.Item_Blood_Katana;
import object.Item_Coin;
import object.Item_Crusader_Shield;
import object.Item_HP_Potion_B;
import object.Item_HP_Potion_M;
import object.Item_HP_Potion_S;
import object.Item_Iron_Sword;
import object.Item_Key;
import object.Item_LightSaber;
import object.Item_Wooden_Shield;
import object.MasterObject;

public class ItemPicker {
	ArrayList<MasterObject> common = new ArrayList<>();
	ArrayList<MasterObject> standard = new ArrayList<>();
	ArrayList<MasterObject> rare = new ArrayList<>();
	ArrayList<MasterObject> superior = new ArrayList<>();	
	ArrayList<MasterObject> highend = new ArrayList<>();
	ArrayList<MasterObject> exotic = new ArrayList<>();
	
	GamePanel gp;
	public ItemPicker(GamePanel gp){
		this.gp = gp;
		//Common item
		common.add(new Item_Coin(gp));
		common.add(new Item_Iron_Sword(gp));
		common.add(new Item_Wooden_Shield(gp));
		common.add(new Item_HP_Potion_S(gp));
		//Standard item
		standard.add(new Item_HP_Potion_M(gp));
		//Rare item
		rare.add(new Item_HP_Potion_B(gp));
		//Superior
		superior.add(new Item_Key(gp));
		//High-end
		highend.add(new Item_Crusader_Shield(gp));
		//Exotic item
		exotic.add(new Item_Blood_Katana(gp));
		exotic.add(new Item_LightSaber(gp));
	}
	public MasterObject pickItem(ArrayList<MasterObject> item) {
		int i = new Random().nextInt(item.size())+0;
        return item.get(i);
	}
	public MasterObject picker(String rarity) {
		switch(rarity) {
		case "common":                      //40%
			return pickItem(common);
		case "standard":                    //25%
			return pickItem(standard);
		case "rare":                        //15%
			return pickItem(rare);
		case "superior":                    //10%
			return pickItem(superior);
		case "high-end":                    //7%
			return pickItem(highend);
		case "exotic":                      //3%
			return pickItem(exotic);
		}
		return null;
	}
}
