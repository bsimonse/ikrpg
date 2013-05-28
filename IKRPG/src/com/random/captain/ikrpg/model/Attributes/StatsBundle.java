package com.random.captain.ikrpg.model.Attributes;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StatsBundle implements Parcelable
{
	private Map<Stat, Integer> activeStats;
	private Map<Stat, Integer> baseStats;
	private Map<Stat, Integer> maxStats;
	
	private Map<String, Modifier<Stat>> modifiers;
	
	public StatsBundle(Collection<Pair<Stat, Integer>> pBaseStats, Collection<Pair<Stat, Integer>> pMaxStats)
	{
		activeStats = new HashMap<Stat, Integer>();
		baseStats = new HashMap<Stat, Integer>();
		maxStats = new HashMap<Stat, Integer>();
		
		for(Pair<Stat, Integer> stat : pBaseStats)
		{baseStats.put(stat.first, stat.second);}
		
		for(Pair<Stat, Integer> stat : pMaxStats)
		{maxStats.put(stat.first, stat.second);}
		
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

	public int getMaxStat(Stat stat)
	{
		Integer value = maxStats.get(stat);
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
		{return false;}
		
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
		int pPhy = baseStats.get(Stat.PHYSIQUE);
		int pStr = baseStats.get(Stat.STRENGTH);
		int pSpd = baseStats.get(Stat.SPEED);
		int pAgi = baseStats.get(Stat.AGILITY);
		int pPrw = baseStats.get(Stat.PROWESS);
		int pPoi = baseStats.get(Stat.POISE);
		int pPer = baseStats.get(Stat.PERCEPTION);
		int pInt = baseStats.get(Stat.INTELLECT);
		int pArc = baseStats.get(Stat.ARCANE);
		
		baseStats.put(Stat.DEFENSE, pSpd+pAgi+pPer);
		baseStats.put(Stat.INITIATIVE,pSpd+pPrw+pPer);
		baseStats.put(Stat.ARMOR, pPhy);
		baseStats.put(Stat.WILLPOWER, pPhy+pInt);
		baseStats.put(Stat.COMMAND, pInt);
		baseStats.put(Stat.CONTROL, 2*pArc);
		baseStats.put(Stat.MELEE_ATTACK, 0);
		baseStats.put(Stat.MELEE_DAMAGE, 0);
		baseStats.put(Stat.RANGED_ATTACK, 0);
		baseStats.put(Stat.RANGED_DAMAGE, 0);
		
		rederiveActiveStats();
	}
	
	private void rederiveActiveStats()
	{
		//reset to base
		activeStats.clear();
		activeStats.putAll(baseStats);
		
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
	
	@Override
	public String toString()
	{
		StringBuilder myString = new StringBuilder(200);
		
		myString.append("Stats:\n"+Stat.PHYSIQUE.toString()+activeStats.get(Stat.PHYSIQUE)+"\n");
		myString.append(Stat.SPEED.toString()+": "+activeStats.get(Stat.SPEED)+"\n");
		myString.append(Stat.STRENGTH.toString()+": "+activeStats.get(Stat.STRENGTH)+"\n");
		myString.append(Stat.AGILITY.toString()+": "+activeStats.get(Stat.AGILITY)+"\n");
		myString.append(Stat.PROWESS.toString()+": "+activeStats.get(Stat.PROWESS)+"\n");
		myString.append(Stat.POISE.toString()+": "+activeStats.get(Stat.POISE)+"\n");
		myString.append(Stat.INTELLECT.toString()+": "+activeStats.get(Stat.INTELLECT)+"\n");
		myString.append(Stat.ARCANE.toString()+": "+activeStats.get(Stat.ARCANE)+"\n");
		myString.append(Stat.PERCEPTION.toString()+": "+activeStats.get(Stat.PERCEPTION)+"\n");
		myString.append(Stat.WILLPOWER.toString()+": "+activeStats.get(Stat.WILLPOWER)+"\n");
		myString.append(Stat.DEFENSE.toString()+": "+activeStats.get(Stat.DEFENSE)+"\n");
		myString.append(Stat.ARMOR.toString()+": "+activeStats.get(Stat.ARMOR)+"\n");
		myString.append(Stat.INITIATIVE.toString()+": "+activeStats.get(Stat.INITIATIVE)+"\n");
		myString.append(Stat.COMMAND.toString()+": "+activeStats.get(Stat.COMMAND)+"\n");
		myString.append(Stat.CONTROL.toString()+": "+activeStats.get(Stat.CONTROL)+"\n");
		myString.append(Stat.MELEE_ATTACK.toString()+": "+activeStats.get(Stat.MELEE_ATTACK)+"\n");
		myString.append(Stat.MELEE_DAMAGE.toString()+": "+activeStats.get(Stat.MELEE_DAMAGE)+"\n");
		myString.append(Stat.RANGED_ATTACK.toString()+": "+activeStats.get(Stat.RANGED_ATTACK)+"\n");
		myString.append(Stat.RANGED_DAMAGE.toString()+": "+activeStats.get(Stat.RANGED_DAMAGE)+"\n");
		
		return myString.toString();
	}
	
	/* Parcelling */
	public void writeToParcel(Parcel toParcel, int flags)
	{
		toParcel.writeInt(baseStats.size());
		for(Stat stat : baseStats.keySet())
		{
			toParcel.writeSerializable(stat);
			toParcel.writeInt(baseStats.get(stat));
		}
		
		toParcel.writeInt(modifiers.size());
		for(String modName : modifiers.keySet())
		{
			toParcel.writeString(modName);
			toParcel.writeParcelable(modifiers.get(modName), 0);
		}
	}

	public static final Parcelable.Creator<StatsBundle> CREATOR = new Parcelable.Creator<StatsBundle>()
	{
		@Override
		public StatsBundle createFromParcel(Parcel in)
		{
			int baseSize = in.readInt();
			Map<Stat, Integer> baseStats = new HashMap<Stat, Integer>(baseSize);
			Stat stat;
			int value;
			for(int i=0; i<baseSize; i++)
			{
				stat = (Stat)in.readSerializable();
				value = in.readInt();
				baseStats.put(stat, value);
			}
			
			baseSize = in.readInt();
			Map<String, Modifier<Stat>> modifiers = new HashMap<String, Modifier<Stat>>(baseSize);
			String key;
			Modifier<Stat> mod;
			for(int i=0; i<baseSize; i++)
			{
				key = in.readString();
				mod = (in.readParcelable(Modifier.class.getClassLoader()));
				modifiers.put(key, mod);
			}
			
			StatsBundle me = new StatsBundle(baseStats, modifiers);
			return me;
		}

		@Override
		public StatsBundle[] newArray(int size)
		{
			return new StatsBundle[size];
		}
	};
	
	private StatsBundle(Map<Stat, Integer> pBaseStats, Map<String, Modifier<Stat>> pModifiers)
	{
		baseStats = pBaseStats;
		activeStats = new HashMap<Stat, Integer>();
		modifiers = pModifiers;
		rederiveBaseStats();
	}
	
	public int describeContents()
	{
		return 0;
	}
}
