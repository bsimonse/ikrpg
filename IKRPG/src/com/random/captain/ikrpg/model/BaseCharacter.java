package com.random.captain.ikrpg.model;
import com.random.captain.ikrpg.model.Attributes.*;

import java.util.Set;

public class BaseCharacter
{
	private Race race;
	private Set<Career> careers;
	private Set<Ability> abilities;
	private Set<Spell> spells;
	private SkillsBundle skillsBundle;
	private StatsBundle statsBundle;
	
	public BaseCharacter(Race pRace, Set<Career> pCareers, Set<Ability> pAbilities, SkillsBundle pSkills, StatsBundle pStats)
	{
		race = pRace;
		careers = pCareers;
		abilities=pAbilities;
		skillsBundle = pSkills;
		statsBundle = pStats;
	}
	
	public Race race()
	{
		return race;
	}
	
	public Set<Career> careers()
	{
		return careers;
	}
	
	public Set<Ability> abilities()
	{
		return abilities;
	}
	
	public SkillsBundle skillsBundle()
	{
		return skillsBundle;
	}
	
	public StatsBundle statsBundle()
	{
		return statsBundle;
	}
}
