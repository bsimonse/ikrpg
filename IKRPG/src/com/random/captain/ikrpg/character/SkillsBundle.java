package com.random.captain.ikrpg.character;

import java.util.*;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

class SkillsBundle implements Parcelable
{
	private StatsBundle stats;
	private Map<Skill, Integer> activeSkills;
	private Map<Skill, Integer> baseSkills;
	
	private Map<String, Modifier<Skill>> modifiers;
	
	SkillsBundle(StatsBundle pStats, Set<Career> pCareers)
	{
		stats = pStats;
		activeSkills = new HashMap<Skill, Integer>();
		baseSkills = new HashMap<Skill, Integer>();
		
		modifiers = new HashMap<String, Modifier<Skill>>();
		
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
	
	SkillsBundle(StatsBundle pStats, Map<Skill, Integer> pBaseSkills, Map<String, Modifier<Skill>> pModifiers)
	{
		stats = pStats;
		baseSkills = pBaseSkills;
		modifiers = pModifiers;
		activeSkills = new HashMap<Skill, Integer>();
		
		rederiveSkills();
	}
	
	int getSkillCheckValue(Skill skill)
	{
		Integer value = activeSkills.get(skill);
		if(value == null){return 0;}
		return value.intValue();
	}
	
	int getSkillLevel(Skill skill)
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
	
	boolean addModifier(Modifier<Skill> modifier, String key)
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
		Collection<Modifier<Skill>> m = modifiers.values();
		for(Modifier<Skill> modifier : m)
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
	public SkillsBundle clone(){return new SkillsBundle(stats.clone(), new HashMap<Skill, Integer>(baseSkills), new HashMap<String, Modifier<Skill>>(modifiers));}
	
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

	public static final Parcelable.Creator<SkillsBundle> CREATOR = new Parcelable.Creator<SkillsBundle>()
	{
		@Override
		public SkillsBundle createFromParcel(Parcel in)
		{
			StatsBundle pStats = in.readParcelable(StatsBundle.class.getClassLoader());
			
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
			Map<String, Modifier<Skill>> modifiers = new HashMap<String, Modifier<Skill>>(baseSize);
			String key;
			Modifier<Skill> mod;
			for(int i=0; i<baseSize; i++)
			{
				key = in.readString();
				mod = (in.readParcelable(Modifier.class.getClassLoader()));
				modifiers.put(key, mod);
			}

			SkillsBundle me = new SkillsBundle(pStats, baseSkills, modifiers);
			return me;
		}

		@Override
		public SkillsBundle[] newArray(int size)
		{
			return new SkillsBundle[size];
		}
	};
	
	@Override public int describeContents(){return 0;}
}
