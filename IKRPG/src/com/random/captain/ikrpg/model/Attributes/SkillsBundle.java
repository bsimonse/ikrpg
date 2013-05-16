package com.random.captain.ikrpg.model.Attributes;

import java.util.*;

import android.util.Pair;

public class SkillsBundle
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
}
