package com.random.captain.ikrpg.model.Attributes;

import android.support.v4.app.FragmentManager;
import com.random.captain.ikrpg.model.Attributes.Ability;
import com.random.captain.ikrpg.model.Attributes.SkillEnum;
import com.random.captain.ikrpg.model.BaseCharacter;
import com.random.captain.ikrpg.model.Creators.PrereqCheck;
import com.random.captain.ikrpg.model.Creators.PrereqCheckResult;

public enum AbilityEnum implements PrereqCheck
{
	JACK_MARSHALL("'Jack Marshall", "Kickin'",false, null),
	ACE_COMMANDER("Ace Commander", "Whee", false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.abilities().contains(AbilityEnum.JACK_MARSHALL) && myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.COMMAND)) >= 2, null);}
	}),
	ACROBATICS("Acrobatics","Jump", false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.statsBundle().getBaseStat(Stat.AGILITY) >= 6, null);}
	}),
	ARCANE_ENGINEER("Arcane Engineer", "", false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.MECHANIKAL)) >= 2, null);}
	}),
	BOMBER("Bomber","", false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.THROWN_WEAPON)) >= 3, null);}
	}),
	BREW_MASTER("Brew Master","",false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.ALCHEMY)) >= 2, null);}
	}),
	DRIVE_ASSUALT("Drive: Assault","",false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.abilities().contains(AbilityEnum.JACK_MARSHALL), null);}
	}),
	DRIVE_PRONTO("Drive: Pronto","",false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.abilities().contains(AbilityEnum.JACK_MARSHALL), null);}
	}),
	FAST_COOK("Fast Cook","",false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.ALCHEMY)) >= 2, null);}
	}),
	FIELD_ALCHEMIST("Field Alchemist", "",false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.ALCHEMY)) >= 2, null);}
	}),
	FIRE_IN_THE_HOLE("Fire in the Hole!","",false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.THROWN_WEAPON)) >= 1, null);}
	}),
	FREE_STYLE("Free Style","",false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.ALCHEMY)) >= 1, null);}
	}),
	GRENADIER("Grenadier","Boom",false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.THROWN_WEAPON)) >= 1, null);}
	}),
	INSCRIBE_FORMULAE("Inscribe Formulae","",false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.MECHANIKAL)) >= 1, null);}
	}),
	POISON_RESISTANCE("Poison Resistance", "Hiss", false, null),
	RESOURCEFUL("Resourceful","",false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.MECHANIKAL)) >= 3, null);}
	}),
	STEAMO("Steamo","",false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.MECHANIKAL)) >= 2, null);}
	});
	
	private AbilityEnum(String pName, String pDesc, boolean pArchetypeDriven, PrereqCheck pPrereqCheck)
	{
		name = pName;
		description = pDesc;
		archetypeDriven = pArchetypeDriven;
		prereq = pPrereqCheck;
	}
	
	private String name;
	private String description;
	private boolean archetypeDriven;
	private PrereqCheck prereq;
	
	public String displayName(){return name;}
	public String description(){return description;}
	
	@Override
	public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
	{
		if(prereq == null){return new PrereqCheckResult(true,null);}
		else{return prereq.meetsPrereq(myChar);}
	}
}