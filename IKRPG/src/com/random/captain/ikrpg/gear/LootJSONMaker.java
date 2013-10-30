package com.random.captain.ikrpg.gear;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class LootJSONMaker
{
	public static void getSomeJSON()
	{
		Armor a = new Armor("Full Plate", "This is full plate", 100, -1, -3, 8, new ArrayList<Loot.EquipmentSlots>());
		
		Gson g = new GsonBuilder().create();
		String json = g.toJson(a);
		
		Log.i("IKRPG","FIRST: "+json);
		
		Armor b = g.fromJson(json, Armor.class);
		String json2 = g.toJson(b);
		
		Log.i("IKRPG","SECOND: "+json2);
	}
}
