package com.random.captain.ikrpg.character;


public class GameCharacter extends zzBaseCharacter
{
	//conversion constructor
	GameCharacter(zzBaseCharacter base)
	{
		super();
		fluff=base.fluff;
		race=base.race;
		archetype=base.archetype;
		tradition=base.tradition;
		careers=base.careers;
		abilities=base.abilities;
		spells=base.spells;
		baseSkills=base.baseSkills;
		skillModifiers=base.skillModifiers;
		baseStats=base.baseStats;
		maxStats=base.maxStats;
		statModifiers=base.statModifiers;
		lootPack=base.lootPack;
		
		exp=base.exp;
		tier=zzTier.getLevelForEXP(exp);
		deriveStats();
		deriveSkillCheckLevels();
	}
	
	public String toJson()
	{return super.toJson();}

	public static GameCharacter fromJson(String json)
	{return new GameCharacter(zzBaseCharacter.fromJson(json));}
}
