package com.random.captain.ikrpg.character;

import java.util.*;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

class zzSkillsBundle implements Parcelable
{
	private zzStatsBundle stats;
	private Map<Skill, Integer> activeSkills;
	private Map<Skill, Integer> baseSkills;
	
	private Map<String, zzModifier<Skill>> modifiers;
	
	zzSkillsBundle(zzStatsBundle pStats, Set<Career> pCareers)
	{
		stats = pStats;
		activeSkills = new HashMap<Skill, Integer>();
		baseSkills = new HashMap<Skill, Integer>();
		
		modifiers = new HashMap<String, zzModifier<Skill>>();
		
		//get starting skills
		if(pCareers != null)
		{
			for(Career career : pCareers)
			{
				Collection<Pair<Skill, Integer>> toIncrement = career.startingSkills();
				for(Pair<Skill, Integer> pair : toIncrement)
				{
					if(baseSkills.containsKey(pair.first))
					{
						//skill already trained; increment
						int currentVal = baseSkills.get(pair.first);
						currentVal += pair.second;
						baseSkills.put(pair.first, currentVal);
					}
					else
					{
						//skill untrained; set
						baseSkills.put(pair.first, pair.second);
					}
				}
			}
		}
		
		rederiveSkills();
	}
	
	zzSkillsBundle(zzStatsBundle pStats, Map<Skill, Integer> pBaseSkills, Map<String, zzModifier<Skill>> pModifiers)
	{
		stats = pStats;
		baseSkills = pBaseSkills;
		modifiers = pModifiers;
		activeSkills = new HashMap<Skill, Integer>();
		
		rederiveSkills();
	}
	
	int getSkillLevel(Skill skill)
	{
		Integer value = activeSkills.get(skill);
		if(value == null){value = 0;}
		
		Stat stat = skill.governingStat();
		if(stat == null){return -1;}
		
		return value+stats.getStat(stat);
	}
	
	int getBaseSkillLevel(Skill skill)
	{
		Integer value = baseSkills.get(skill);
		if(value == null){return 0;}
		return value.intValue();
	}
	
	void setSkillLevel(Skill skill, int value)
	{
		baseSkills.put(skill, value);
		rederiveSkills();
	}
	
	void setSkillLevels(Collection<Pair<Skill, Integer>> skillPairs)
	{
		for(Pair skillPair : skillPairs)
		{
			baseSkills.put((Skill)skillPair.first, (Integer)skillPair.second);
		}
	}
	
	Collection<Pair<Skill, Integer>> getTrainedSkills()
	{
		Collection<Pair<Skill, Integer>> skills = new ArrayList<Pair<Skill, Integer>>(20);
		
		for(Skill skill : baseSkills.keySet())
		{
			int skillLevel = baseSkills.get(skill);
			if(skillLevel > 0)
			{
				Pair<Skill, Integer> aPair = new Pair<Skill, Integer>(skill, skillLevel);
				skills.add(aPair);
			}
		}
		
		return skills;
	}
	
	boolean addModifier(zzModifier<Skill> modifier, String key)
	{
		if(modifiers.containsKey(key))
		{
			return false;
		}

		modifiers.put(key, modifier);
		rederiveSkills();
		return true;
	}

	boolean removeModifier(String key)
	{
		boolean result = (modifiers.remove(key) != null);
		if(result){rederiveSkills();}
		return result;
	}

	private void rederiveSkills()
	{
		//reset to base
		activeSkills.clear();
		activeSkills.putAll(baseSkills);

		//apply modifiers
		Collection<zzModifier<Skill>> m = modifiers.values();
		for(zzModifier<Skill> modifier : m)
		{
			Skill skill = modifier.trait;
			Integer value = activeSkills.get(skill);
			value = modifier.modifiedValue(value);
			activeSkills.put(skill, value);
		}
	}
	
	@Override
	public String toString()
	{
		StringBuilder myString = new StringBuilder(100);
		myString.append("\nSkills: \n");
		for(Pair<Skill, Integer> skillPair : getTrainedSkills())
		{
			Skill skill = skillPair.first;
			myString.append(skill.toString()).append(": ").append(skillPair.second).append("\n");
		}
		
		return myString.toString();
	}
	
	@Override
	public zzSkillsBundle clone(){return new zzSkillsBundle(stats.clone(), new HashMap<Skill, Integer>(baseSkills), new HashMap<String, zzModifier<Skill>>(modifiers));}
	
	/* Parcelling */
	public void writeToParcel(Parcel toParcel, int flags)
	{
		//Stats
		toParcel.writeParcelable(stats,0);
		
		//Base skills
		toParcel.writeInt(baseSkills.size());
		for(Skill skill : baseSkills.keySet())
		{
			toParcel.writeParcelable(skill, 0);
			toParcel.writeInt(baseSkills.get(skill));
		}

		//Modifiers
		toParcel.writeInt(modifiers.size());
		for(String modName : modifiers.keySet())
		{
			toParcel.writeString(modName);
			toParcel.writeParcelable(modifiers.get(modName), 0);
		}
	}

	public static final Parcelable.Creator<zzSkillsBundle> CREATOR = new Parcelable.Creator<zzSkillsBundle>()
	{
		@Override
		public zzSkillsBundle createFromParcel(Parcel in)
		{
			zzStatsBundle pStats = in.readParcelable(zzStatsBundle.class.getClassLoader());
			
			int baseSize = in.readInt();
			Map<Skill, Integer> baseSkills = new HashMap<Skill, Integer>(baseSize);
			Skill skill;
			int value;
			for(int i=0; i<baseSize; i++)
			{
				skill = in.readParcelable(Skill.class.getClassLoader());
				value = in.readInt();
				baseSkills.put(skill, value);
			}

			baseSize = in.readInt();
			Map<String, zzModifier<Skill>> modifiers = new HashMap<String, zzModifier<Skill>>(baseSize);
			String key;
			zzModifier<Skill> mod;
			for(int i=0; i<baseSize; i++)
			{
				key = in.readString();
				mod = (in.readParcelable(zzModifier.class.getClassLoader()));
				modifiers.put(key, mod);
			}

			zzSkillsBundle me = new zzSkillsBundle(pStats, baseSkills, modifiers);
			return me;
		}

		@Override
		public zzSkillsBundle[] newArray(int size)
		{
			return new zzSkillsBundle[size];
		}
	};
	
	@Override public int describeContents(){return 0;}
}
