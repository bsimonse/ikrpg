package com.random.captain.ikrpg.model.Attributes;

import android.util.Pair;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StatsBundle
{
	private Map<Stat, Integer> activeStats;
	private Map<Stat, Integer> baseStats;
	
	private Map<String, Modifier<Stat>> modifiers;
	
	public StatsBundle(Collection<Pair<Stat, Integer>> stats)
	{
		activeStats = new HashMap<Stat, Integer>();
		baseStats = new HashMap<Stat, Integer>();
		
		for(Pair<Stat, Integer> stat : stats)
		{
			baseStats.put(stat.first, stat.second);
		}
		
		modifiers = new HashMap<String, Modifier<Stat>>();
		
		rederiveBaseStats();
	}
	
	public int getStat(Stat stat)
	{
		Integer value = activeStats.get(stat);
		if(value==null){return -1;}
		return value.intValue();
	}
	
	public int getBaseStat(Stat stat)
	{
		Integer value = baseStats.get(stat);
		if(value==null){return -1;}
		return value.intValue();
	}

	public void setBaseStat(Stat stat, int value)
	{
		baseStats.put(stat, value);
		rederiveBaseStats();
	}
	
	public boolean addModifier(Modifier<Stat> modifier, String key)
	{
		if(modifiers.containsKey(key))
		{
			return false;
		}
		
		modifiers.put(key, modifier);
		rederiveActiveStats();
		return true;
	}
	
	public boolean removeModifier(String key)
	{
		boolean result = (modifiers.remove(key) != null);
		if(result){rederiveActiveStats();}
		return result;
	}
	
	private void rederiveBaseStats()
	{
		//Cheating because null = 0;
		//Shouldn't have to count on it, though... will probably do check later
		int pPhy = baseStats.get(Stat.PHYSIQUE);
		int pSpd = baseStats.get(Stat.SPEED);
		int pAgi = baseStats.get(Stat.AGILITY);
		int pPrw = baseStats.get(Stat.PROWESS);
		int pPer = baseStats.get(Stat.PERCEPTION);
		int pInt = baseStats.get(Stat.INTELLECT);
		int pArc = baseStats.get(Stat.ARCANE);
		
		baseStats.put(Stat.DEFENSE, pSpd+pAgi+pPer);
		baseStats.put(Stat.INITIATIVE,pSpd+pPrw+pPer);
		baseStats.put(Stat.ARMOR, pPhy);
		baseStats.put(Stat.WILLPOWER, pPhy+pInt);
		baseStats.put(Stat.COMMAND, pInt);
		baseStats.put(Stat.CONTROL, 2*pArc);
		
		rederiveActiveStats();
	}
	
	private void rederiveActiveStats()
	{
		//reset to base
		activeStats.clear();
		activeStats.putAll(baseStats);
		activeStats.put(Stat.ATTACK, 0);
		activeStats.put(Stat.DAMAGE, 0);
		
		//apply modifiers
		Collection<Modifier<Stat>> m = modifiers.values();
		for(Modifier<Stat> modifier : m)
		{
			Stat stat = modifier.getTrait();
			Integer value = activeStats.get(stat);
			value = modifier.modifiedValue(value);
			activeStats.put(stat, value);
		}
	}
}
