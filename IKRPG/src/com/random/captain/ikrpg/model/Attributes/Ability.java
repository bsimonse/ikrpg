package com.random.captain.ikrpg.model.Attributes;

import android.support.v4.app.FragmentManager;
import com.random.captain.ikrpg.model.Attributes.Ability;
import com.random.captain.ikrpg.model.Attributes.SkillEnum;
import com.random.captain.ikrpg.model.BaseCharacter;
import com.random.captain.ikrpg.model.Creators.PrereqCheck;
import com.random.captain.ikrpg.model.Creators.PrereqCheckResult;

public enum Ability implements PrereqCheck
{
	JACK_MARSHALL("'Jack Marshall", "Kickin'",null),
	ACE_COMMANDER("Ace Commander", "Whee", new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.abilities().contains(Ability.JACK_MARSHALL) && myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.COMMAND)) >= 2, null);}
	}),
	ACROBATICS("Acrobatics","Jump", new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.statsBundle().getBaseStat(Stat.AGILITY) >= 6, null);}
	}),
	ARCANE_ENGINEER("Arcane Engineer", "", new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.MECHANIKAL)) >= 2, null);}
	}),
	BOMBER("Bomber","", new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.THROWN_WEAPON)) >= 3, null);}
	}),
	BREW_MASTER("Brew Master","",new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.ALCHEMY)) >= 2, null);}
	}),
	DRIVE_ASSUALT("Drive: Assault","",new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.abilities().contains(Ability.JACK_MARSHALL), null);}
	}),
	DRIVE_PRONTO("Drive: Pronto","",new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.abilities().contains(Ability.JACK_MARSHALL), null);}
	}),
	FAST_COOK("Fast Cook","",new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.ALCHEMY)) >= 2, null);}
	}),
	FIELD_ALCHEMIST("Field Alchemist", "",new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.ALCHEMY)) >= 2, null);}
	}),
	FIRE_IN_THE_HOLE("Fire in the Hole!","",new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.THROWN_WEAPON)) >= 1, null);}
	}),
	FREE_STYLE("Free Style","",new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.ALCHEMY)) >= 1, null);}
	}),
	GRENADIER("Grenadier","Boom",new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.THROWN_WEAPON)) >= 1, null);}
	}),
	INSCRIBE_FORMULAE("Inscribe Formulae","",new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.MECHANIKAL)) >= 1, null);}
	}),
	POISON_RESISTANCE("Poison Resistance", "Hiss", null),
	RESOURCEFUL("Resourceful","",new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.MECHANIKAL)) >= 3, null);}
	}),
	STEAMO("Steamo","",new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.MECHANIKAL)) >= 2, null);}
	});
	
	private Ability(String pName, String pDesc, PrereqCheck pPrereqCheck)
	{
		name = pName;
		description = pDesc;
		prereq = pPrereqCheck;
	}
	
	private String name;
	private String description;
	private String type;
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
