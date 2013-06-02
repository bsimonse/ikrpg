package com.random.captain.ikrpg.character;

public enum AbilityEnum implements PrereqCheck
{
	JACK_MARSHALL("'Jack Marshall", "Kickin'",false, false, null),
	ACE_COMMANDER("Ace Commander", "Whee", false, false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.hasAbility(AbilityEnum.JACK_MARSHALL) && myChar.getSkillLevel(SkillEnum.COMMAND) >= 2, null);}
	}),
	ACROBATICS("Acrobatics","Jump", false, false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.getBaseStat(Stat.AGILITY) >= 6, null);}
	}),
	ARCANE_ENGINEER("Arcane Engineer", "", false, false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.getSkillLevel(SkillEnum.MECHANIKAL) >= 2, null);}
	}),
	BOMBER("Bomber","", false, false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.getSkillLevel(SkillEnum.THROWN_WEAPON) >= 3, null);}
	}),
	BREW_MASTER("Brew Master","",false, false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.getSkillLevel(SkillEnum.ALCHEMY) >= 2, null);}
	}),
	DRIVE_ASSUALT("Drive: Assault","",false, false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.hasAbility(AbilityEnum.JACK_MARSHALL), null);}
	}),
	DRIVE_PRONTO("Drive: Pronto","",false, false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.hasAbility(AbilityEnum.JACK_MARSHALL), null);}
	}),
	FAST_COOK("Fast Cook","",false, false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.getSkillLevel(SkillEnum.ALCHEMY) >= 2, null);}
	}),
	FIELD_ALCHEMIST("Field Alchemist", "",false, false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.getSkillLevel(SkillEnum.ALCHEMY) >= 2, null);}
	}),
	FIRE_IN_THE_HOLE("Fire in the Hole!","",false, false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.getSkillLevel(SkillEnum.THROWN_WEAPON) >= 1, null);}
	}),
	FREE_STYLE("Free Style","",false, false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.getSkillLevel(SkillEnum.ALCHEMY) >= 1, null);}
	}),
	GRENADIER("Grenadier","Boom",false, false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.getSkillLevel(SkillEnum.THROWN_WEAPON) >= 1, null);}
	}),
	INSCRIBE_FORMULAE("Inscribe Formulae","",false, false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.getSkillLevel(SkillEnum.MECHANIKAL) >= 1, null);}
	}),
	POISON_RESISTANCE("Poison Resistance", "Hiss", false, false, null),
	RESOURCEFUL("Resourceful","",false, false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.getSkillLevel(SkillEnum.MECHANIKAL) >= 3, null);}
	}),
	STEAMO("Steamo","",false, false, new PrereqCheck(){
			@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.getSkillLevel(SkillEnum.MECHANIKAL) >= 2, null);}
	});
	
	private AbilityEnum(String pName, String pDesc, boolean pArchetypeDriven, boolean pIsQualifiable, PrereqCheck pPrereqCheck)
	{
		name = pName;
		description = pDesc;
		archetypeDriven = pArchetypeDriven;
		isQualifiable = pIsQualifiable;
		prereq = pPrereqCheck;
	}
	
	private String name;
	private String description;
	private boolean archetypeDriven;
	private boolean isQualifiable;
	private PrereqCheck prereq;
	
	public String displayName(){return name;}
	public String description(){return description;}
	public boolean isQualifiable(){return isQualifiable;}
	
	@Override
	public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
	{
		if(prereq == null){return new PrereqCheckResult(true,null);}
		else{return prereq.meetsPrereq(myChar);}
	}
	
	//Editting needed?
	public Ability make(){return new Ability(this);}
}
