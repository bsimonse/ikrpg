package com.random.captain.ikrpg.character;

import android.content.Context;
import com.random.captain.ikrpg.IKRPGApp;
import com.random.captain.ikrpg.R;

public enum AbilityEnum implements zzPrereqCheck
{
	JACK_MARSHALL(R.string.jack_marshal_name, R.string.jack_marshal_longDesc, R.string.jack_marshal_shortDesc, R.string.jack_marshal_page,
					false, false, null),
					
	ACE_COMMANDER(R.string.ace_commander_name, R.string.ace_commander_longDesc, R.string.ace_commander_shortDesc, R.string.ace_commander_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.hasAbility(AbilityEnum.JACK_MARSHALL) && myChar.getSkillLevel(SkillEnum.COMMAND) >= 2, null);}
	}),
	
	ACROBATICS(R.string.acrobatics_name, R.string.acrobatics_longDesc, R.string.acrobatics_shortDesc, R.string.acrobatics_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.AGILITY) >= 6, null);}
	}),
	
	ARCANE_ENGINEER(R.string.arcane_engineer_name, R.string.arcane_engineer_longDesc, R.string.arcane_engineer_shortDesc, R.string.arcane_engineer_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.MECHANIKAL) >= 2, null);}
	}),
	
	BOMBER(R.string.bomber_name, R.string.bomber_longDesc, R.string.bomber_shortDesc, R.string.bomber_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.THROWN_WEAPON) >= 3, null);}
	}),
	
	BREW_MASTER(R.string.brew_master_name, R.string.brew_master_longDesc, R.string.brew_master_shortDesc, R.string.brew_master_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.ALCHEMY) >= 2, null);}
	}),
	
	DRIVE_ASSUALT(R.string.drive_assault_name, R.string.drive_assault_longDesc, R.string.drive_assault_shortDesc, R.string.drive_assault_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.hasAbility(AbilityEnum.JACK_MARSHALL), null);}
	}),
	
	DRIVE_PRONTO(R.string.drive_pronto_name, R.string.drive_pronto_longDesc, R.string.drive_pronto_shortDesc, R.string.drive_pronto_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.hasAbility(AbilityEnum.JACK_MARSHALL), null);}
	}),
	
	FAST_COOK(R.string.fast_cook_name, R.string.fast_cook_longDesc, R.string.fast_cook_shortDesc, R.string.fast_cook_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.ALCHEMY) >= 2, null);}
	}),
	
	FIELD_ALCHEMIST(R.string.field_alchemist_name, R.string.field_alchemist_longDesc, R.string.field_alchemist_shortDesc, R.string.field_alchemist_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.ALCHEMY) >= 2, null);}
	}),
	
	FIRE_IN_THE_HOLE(R.string.fire_in_the_hole_name, R.string.fire_in_the_hole_longDesc, R.string.fire_in_the_hole_shortDesc, R.string.fire_in_the_hole_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.THROWN_WEAPON) >= 1, null);}
	}),
	
	FREE_STYLE(R.string.free_style_name, R.string.free_style_longDesc, R.string.free_style_shortDesc, R.string.free_style_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.ALCHEMY) >= 1, null);}
	}),
	
	GRENADIER(R.string.grenadier_name, R.string.grenadier_longDesc, R.string.grenadier_shortDesc, R.string.grenadier_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.THROWN_WEAPON) >= 1, null);}
	}),
	
	INSCRIBE_FORMULAE(R.string.inscribe_formulae_name, R.string.inscribe_formulae_longDesc, R.string.inscribe_formulae_shortDesc, R.string.inscribe_formulae_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.MECHANIKAL) >= 1, null);}
	}),
	
	POISON_RESISTANCE(R.string.poison_resistance_name, R.string.poison_resistance_longDesc, R.string.poison_resistance_shortDesc, R.string.poison_resistance_page,
					false, false, null),
					
	RESOURCEFUL(R.string.resourceful_name, R.string.resourceful_longDesc, R.string.resourceful_shortDesc, R.string.resourceful_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.MECHANIKAL) >= 3, null);}
	}),
	
	STEAMO(R.string.steamo_name, R.string.steamo_longDesc, R.string.steamo_shortDesc, R.string.steamo_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.MECHANIKAL) >= 2, null);}
	});
	
	private AbilityEnum(int pNameResourceID, int pLongDescResourceID, int pShortDescResourceID, int pPageNumberResourceID, boolean pFromArchetype, boolean pIsQualifiable, zzPrereqCheck pPrereqCheck)
	{
		Context c = IKRPGApp.getContext();
		name = c.getString(pNameResourceID);
		longDescription = c.getString(pLongDescResourceID);
		shortDescription = c.getString(pShortDescResourceID);
		pageNumber = c.getString(pPageNumberResourceID);
		fromArchetype = pFromArchetype;
		isQualifiable = pIsQualifiable;
		prereq = pPrereqCheck;
	}
	
	private String name;
	private String longDescription;
	private String shortDescription;
	private String pageNumber;
	private boolean fromArchetype;
	private boolean isQualifiable;
	private zzPrereqCheck prereq;
	
	public String displayName(){return name;}
	public String longDescription(){return longDescription;}
	public String shortDescription(){return shortDescription;}
	public String pageNumbrer(){return pageNumber;}
	public boolean isQualifiable(){return isQualifiable;}
	
	@Override
	public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
	{
		if(prereq == null){return new zzPrereqCheckResult(true,null);}
		else{return prereq.meetsPrereq(myChar);}
	}
	
	public Ability make(){return new Ability(this);}
}
