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
	
	public StatsBundle(Map<Stat, Integer> pBaseStats)
	{
		baseStats = pBaseStats;
		activeStats = new HashMap<Stat, Integer>();
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
	
	@Override
	public String toString()
	{
		StringBuilder myString = new StringBuilder(100);
		myString.append("Stats:\nPHY: ").append(activeStats.get(Stat.PHYSIQUE)).append("\n");
		myString.append("SPD: ").append(activeStats.get(Stat.SPEED)).append("\n");
		myString.append("STR: ").append(activeStats.get(Stat.STRENGTH)).append("\n");
		myString.append("AGI: ").append(activeStats.get(Stat.AGILITY)).append("\n");
		myString.append("PRW: ").append(activeStats.get(Stat.PROWESS)).append("\n");
		myString.append("POI: ").append(activeStats.get(Stat.POISE)).append("\n");
		myString.append("INT: ").append(activeStats.get(Stat.INTELLECT)).append("\n");
		myString.append("ARC: ").append(activeStats.get(Stat.ARCANE)).append("\n");
		myString.append("PER: ").append(activeStats.get(Stat.PERCEPTION)).append("\n");
		return myString.toString();
	}
	
	/* Parcelling */
	public void writeToParcel(Parcel toParcel, int flags)
	{
		toParcel.writeInt(baseStats.size());
		for(Stat stat : baseStats.keySet())
		{
			toParcel.writeSerializable(stat);
			toParcel.writeInt(activeStats.get(stat));
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
				mod = (Modifier<Stat>)(in.readParcelable(Modifier.class.getClassLoader()));
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
