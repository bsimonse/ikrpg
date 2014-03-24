package com.random.captain.ikrpg.gear;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class LootPack
{
	int gold;
	Collection<Loot> loot;

	public LootPack()
	{this(0, new ArrayList<Loot>());}
	
	public LootPack(int pGold, Collection<Loot> pLoot)
	{
		gold = pGold;
		loot = pLoot;
	}
	
	public int gold(){return gold;}
	public Collection<Loot> loot(){return Collections.unmodifiableCollection(loot);}
	
	//hmmm.
	public void addLoot(Loot pLoot)
	{loot.add(pLoot);}
	
	public void addLoot(Collection<Loot> pLoot)
	{loot.addAll(pLoot);}

}
