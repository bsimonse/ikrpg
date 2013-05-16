package com.random.captain.ikrpg.model;
import com.random.captain.ikrpg.model.Attributes.*;

import android.util.Pair;
import java.util.Collection;
import java.util.Set;

public class BaseCharacter
{
	/*Fluff*/
	private String name;
	
	/*Crunch*/
	private Race race;
	private Archetype archetype;
	private Set<Career> careers;
	private Set<Ability> abilities;
	private Set<Spell> spells;
	private SkillsBundle skillsBundle;
	private StatsBundle statsBundle;
	
	public BaseCharacter(String pName, Race pRace, Archetype pArchetype, Set<Career> pCareers, Set<Ability> pAbilities,
							Set<Spell> pSpells, SkillsBundle pSkills, StatsBundle pStats)
	{
		name = pName;
		race = pRace;
		archetype = pArchetype;
		careers = pCareers;
		abilities = pAbilities;
		spells = pSpells;
		skillsBundle = pSkills;
		statsBundle = pStats;
	}
	
	public String name(){return name;}
	public Race race(){return race;}
	public Archetype archetype(){return archetype;}
	public Set<Career> careers(){return careers;}
	public Set<Ability> abilities(){return abilities;}
	public Set<Spell> spells(){return spells;}
	public SkillsBundle skillsBundle(){return skillsBundle;}
	public StatsBundle statsBundle(){return statsBundle;}
	
	public String toString()
	{
		StringBuilder myString = new StringBuilder(100);
		myString.append(name).append("\n");
		myString.append(archetype.displayName()).append(" ").append(race.displayName());
		myString.append("\n");
		
		myString.append("Stats:\nPHY: ").append(statsBundle.getStat(Stat.PHYSIQUE)).append("\n");
		myString.append("SPD: ").append(statsBundle.getStat(Stat.SPEED)).append("\n");
		myString.append("STR: ").append(statsBundle.getStat(Stat.STRENGTH)).append("\n");
		myString.append("AGI: ").append(statsBundle.getStat(Stat.AGILITY)).append("\n");
		myString.append("PRW: ").append(statsBundle.getStat(Stat.PROWESS)).append("\n");
		myString.append("POI: ").append(statsBundle.getStat(Stat.POISE)).append("\n");
		myString.append("INT: ").append(statsBundle.getStat(Stat.INTELLECT)).append("\n");
		myString.append("ARC: ").append(statsBundle.getStat(Stat.ARCANE)).append("\n");
		myString.append("PER: ").append(statsBundle.getStat(Stat.PERCEPTION)).append("\n");
		
		myString.append("\nCareers: \n");
		for(Career career: careers)
		{
			myString.append(career.displayName()).append("\n");
		}
		
		myString.append("\nSkills: \n");
		Collection<Pair<Skill, Integer>> mySkills = skillsBundle.getTrainedSkills();
		for(Pair<Skill, Integer> skillPair : mySkills)
		{
			Skill skill = skillPair.first;
			myString.append(skill.name()).append(": ").append(skillPair.second).append("\n");
		}
		
		myString.append("\nAbilities: \n");
		for(Ability ability: abilities)
		{
			myString.append(ability.displayName()).append("\n");
		}
		
		myString.append("\nSpells: \n");
		for(Spell spell: spells)
		{
			myString.append(spell.displayName()).append("\n");
		}
		
		return myString.toString();
	}
}
