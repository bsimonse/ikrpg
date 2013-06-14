package com.random.captain.ikrpg.character;

public enum AbilityEnum implements zzPrereqCheck
{
	JACK_MARSHALL("'Jack Marshall", "Kickin'","Cool","999",false, false, null),
	ACE_COMMANDER("Ace Commander", "Whee", "Cool","999",false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.hasAbility(AbilityEnum.JACK_MARSHALL) && myChar.getSkillLevel(SkillEnum.COMMAND) >= 2, null);}
	}),
	ACROBATICS("Acrobatics","Jump", "Cool","999",false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.AGILITY) >= 6, null);}
	}),
	ARCANE_ENGINEER("Arcane Engineer", "", "Cool","999",false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.MECHANIKAL) >= 2, null);}
	}),
	BOMBER("Bomber","", "Cool","999",false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.THROWN_WEAPON) >= 3, null);}
	}),
	BREW_MASTER("Brew Master","","Cool","999",false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.ALCHEMY) >= 2, null);}
	}),
	DRIVE_ASSUALT("Drive: Assault","","Cool","999",false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.hasAbility(AbilityEnum.JACK_MARSHALL), null);}
	}),
	DRIVE_PRONTO("Drive: Pronto","","Cool","999",false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.hasAbility(AbilityEnum.JACK_MARSHALL), null);}
	}),
	FAST_COOK("Fast Cook","","Cool","999",false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.ALCHEMY) >= 2, null);}
	}),
	FIELD_ALCHEMIST("Field Alchemist", "","Cool","999",false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.ALCHEMY) >= 2, null);}
	}),
	FIRE_IN_THE_HOLE("Fire in the Hole!","","Cool","999",false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.THROWN_WEAPON) >= 1, null);}
	}),
	FREE_STYLE("Free Style","","Cool","999",false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.ALCHEMY) >= 1, null);}
	}),
	GRENADIER("Grenadier","Boom","Cool","999",false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.THROWN_WEAPON) >= 1, null);}
	}),
	INSCRIBE_FORMULAE("Inscribe Formulae","","Cool","999",false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.MECHANIKAL) >= 1, null);}
	}),
	POISON_RESISTANCE("Poison Resistance", "Hiss", "Cool","999",false, false, null),
	RESOURCEFUL("Resourceful","","Cool","999",false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.MECHANIKAL) >= 3, null);}
	}),
	STEAMO("Steamo","","Cool","999",false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillLevel(SkillEnum.MECHANIKAL) >= 2, null);}
	});
	
	private AbilityEnum(String pName, String pLongDesc, String pShortDesc, String pPageNumber, boolean pFromArchetype, boolean pIsQualifiable, zzPrereqCheck pPrereqCheck)
	{
		name = pName;
		longDescription = pLongDesc;
		shortDescription = pShortDesc;
		pageNumber = pPageNumber;
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
