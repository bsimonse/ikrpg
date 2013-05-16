package com.random.captain.ikrpg.model.Attributes;

import android.content.Context;
import com.random.captain.ikrpg.model.Creators.PrereqCheck;
import java.util.Set;

public enum Ability implements PrereqCheck
{
	JACK_MARSHALL("'Jack Marshall", "Kickin'",null),
	ACE_COMMANDER("Ace Commander", "Whee", new PrereqCheck(){
			@Override public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities, SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{return pAbilities.contains(Ability.JACK_MARSHALL) && pSkills.getSkillLevel(Skill.COMMAND) >= 2;}
	}),
	ACROBATICS("Acrobatics","Jump", new PrereqCheck(){
			@Override public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities, SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{return pStats.getBaseStat(Stat.AGILITY) >= 6;}
	}),
	ARCANE_ENGINEER("Arcane Engineer", "", new PrereqCheck(){
			@Override public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities, SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{return pSkills.getSkillLevel(Skill.MECHANIKAL) >= 2;}
	}),
	BOMBER("Bomber","", new PrereqCheck(){
			@Override public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities, SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{return pSkills.getSkillLevel(Skill.THROWN_WEAPON) >= 3;}
	}),
	BREW_MASTER("Brew Master","",new PrereqCheck(){
			@Override public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities, SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{return pSkills.getSkillLevel(Skill.ALCHEMY) >= 2;}
	}),
	DRIVE_ASSUALT("Drive: Assault","",new PrereqCheck(){
			@Override public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities, SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{return pAbilities.contains(Ability.JACK_MARSHALL);}
	}),
	DRIVE_PRONTO("Drive: Pronto","",new PrereqCheck(){
			@Override public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities, SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{return pAbilities.contains(Ability.JACK_MARSHALL);}
	}),
	FAST_COOK("Fast Cook","",new PrereqCheck(){
			@Override public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities, SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{return pSkills.getSkillLevel(Skill.ALCHEMY) >= 2;}
	}),
	FIELD_ALCHEMIST("Field Alchemist", "",new PrereqCheck(){
			@Override public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities, SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{return pSkills.getSkillLevel(Skill.ALCHEMY) >= 2;}
	}),
	FIRE_IN_THE_HOLE("Fire in the Hole!","",new PrereqCheck(){
			@Override public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities, SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{return pSkills.getSkillLevel(Skill.THROWN_WEAPON) >= 1;}
	}),
	FREE_STYLE("Free Style","",new PrereqCheck(){
			@Override public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities, SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{return pSkills.getSkillLevel(Skill.ALCHEMY) >= 1;}
	}),
	GRENADIER("Grenadier","Boom",new PrereqCheck(){
			@Override public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities, SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{return pSkills.getSkillLevel(Skill.THROWN_WEAPON) >= 1;}
	}),
	INSCRIBE_FORMULAE("Inscribe Formulae","",new PrereqCheck(){
			@Override public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities, SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{return pSkills.getSkillLevel(Skill.MECHANIKAL) >= 1;}
	}),
	POISON_RESISTANCE("Poison Resistance", "Hiss", null),
	RESOURCEFUL("Resourceful","",new PrereqCheck(){
			@Override public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities, SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{return pSkills.getSkillLevel(Skill.MECHANIKAL) >= 3;}
	}),
	STEAMO("Steamo","",new PrereqCheck(){
			@Override public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities, SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{return pSkills.getSkillLevel(Skill.MECHANIKAL) >= 2;}
	});
	
	private Ability(String pName, String pDesc, PrereqCheck pPrereqCheck)
	{
		name = pName;
		description = pDesc;
		prereq = pPrereqCheck;
	}
	
	private String name;
	private String description;
	private PrereqCheck prereq;
	
	public String displayName(){return name;}
	public String description(){return description;}
	
	@Override
	public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities,
							   SkillsBundle pSkills, StatsBundle pStats, Context appContext)
	{
		if(prereq == null){return true;}
		
		return prereq.meetsPrereq(race, archetype, careers, pAbilities, pSkills, pStats, appContext);
	}
}
