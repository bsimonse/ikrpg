package com.random.captain.ikrpg.model.Attributes;

import java.util.*;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.random.captain.ikrpg.model.Attributes.StatsBundle;

public class SkillsBundle implements Parcelable
{
	private StatsBundle stats;
	private Map<Skill, Integer> activeSkills;
	private Map<Skill, Integer> baseSkills;
	
	private Map<String, Modifier<Skill>> modifiers;
	
	public SkillsBundle(StatsBundle pStats)
	{
		stats = pStats;
		activeSkills = new HashMap<Skill, Integer>();
		baseSkills = new HashMap<Skill, Integer>();
		
		Skill[] allSkills = Skill.values();
		for(Skill skill : allSkills)
		{
			baseSkills.put(skill, 0);
		}
		
		modifiers = new HashMap<String, Modifier<Skill>>();
		
		rederiveSkills();
	}
	
	public int getSkillCheckValue(Skill skill)
	{
		Integer value = activeSkills.get(skill);
		if(value == null){return 0;}
		return value.intValue();
	}
	
	public int getSkillLevel(Skill skill)
	{
		Integer value = baseSkills.get(skill);
		if(value == null){return 0;}
		return value.intValue();
	}
	
	public void setSkillLevel(Skill skill, int value)
	{
		baseSkills.put(skill, value);
		rederiveSkills();
	}
	
	public void setSkillLevels(Collection<Pair<Skill, Integer>> skillPairs)
	{
		for(Pair skillPair : skillPairs)
		{
			baseSkills.put((Skill)skillPair.first, (Integer)skillPair.second);
		}
	}
	
	public Collection<Pair<Skill, Integer>> getTrainedSkills()
	{
		Collection<Pair<Skill, Integer>> skills = new ArrayList<Pair<Skill, Integer>>(20);
		
		for(Skill skill : Skill.values())
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
	
	public boolean addModifier(Modifier<Skill> modifier, String key)
	{
		if(modifiers.containsKey(key))
		{
			return false;
		}

		modifiers.put(key, modifier);
		rederiveSkills();
		return true;
	}

	public boolean removeModifier(String key)
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
			Skill skill = modifier.getTrait();
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
			myString.append(skill.name()).append(": ").append(skillPair.second).append("\n");
		}
		
		return myString.toString();
	}
	
	/* Parcelling */
	public void writeToParcel(Parcel toParcel, int flags)
	{
		//TODO: do
		/*private StatsBundle stats;
		private Map<Skill, Integer> baseSkills;

		private Map<String, Modifier<Skill>> modifiers;*/
		
		toParcel.writeParcelable(stats,0);
		
		toParcel.writeInt(baseSkills.size());
		for(Skill stat : baseSkills.keySet())
		{
			toParcel.writeSerializable(stat);
			toParcel.writeInt(baseSkills.get(stat));
		}

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
			StatsBundle pStats = (StatsBundle)in.readParcelable(StatsBundle.class.getClassLoader());
			
			int baseSize = in.readInt();
			Map<Skill, Integer> baseSkills = new HashMap<Skill, Integer>(baseSize);
			Skill skill;
			int value;
			for(int i=0; i<baseSize; i++)
			{
				skill = (Skill)in.readSerializable();
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
				mod = (Modifier<Skill>)(in.readParcelable(Modifier.class.getClassLoader()));
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

	private SkillsBundle(StatsBundle pStats, Map<Skill, Integer> pBaseSkills, Map<String, Modifier<Skill>> pModifiers)
	{
		stats = pStats;
		activeSkills = new HashMap<Skill, Integer>();
		baseSkills = pBaseSkills;
		modifiers = pModifiers;

		rederiveSkills();
	}
	
	public int describeContents()
	{
		return 0;
	}
}
