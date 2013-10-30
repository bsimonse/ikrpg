package com.random.captain.ikrpg.gear;

import com.random.captain.ikrpg.character.Modifier;
import com.random.captain.ikrpg.character.Stat;
import com.random.captain.ikrpg.combat.SpecialRule;
import java.util.ArrayList;
import java.util.List;

public class Armor extends Loot
{
	public Armor(String pName, String pDesc, int pCost, int spdMod, int defMod, int armMod, List<EquipmentSlots> pSlots)
	{
		this(pName,pDesc,pCost,spdMod,defMod,armMod,pSlots,null);
	}
	
	public Armor(String pName, String pDesc, int pCost, int spdMod, int defMod, int armMod, List<EquipmentSlots> pSlots,
					List<SpecialRule> pRules)
	{
		//Java is dumb sometimes.
		super(pName, pDesc, pCost, null, null, null, pRules, pSlots, null);
		
		ArrayList<Modifier<Stat>> stats = new ArrayList<Modifier<Stat>>();
		if(spdMod != 0){stats.add(new Modifier<Stat>(Stat.SPEED, spdMod));}
		if(defMod != 0){stats.add(new Modifier<Stat>(Stat.DEFENSE, defMod));}
		if(armMod != 0){stats.add(new Modifier<Stat>(Stat.ARMOR, armMod));}
		statModifiers = stats;
	}
}
