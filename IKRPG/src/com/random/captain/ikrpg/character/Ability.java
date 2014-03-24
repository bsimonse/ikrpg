package com.random.captain.ikrpg.character;


public class Ability implements zzPrereqCheck
{
	private AbilityEnum ability;
	private String qualifier;

	public Ability(AbilityEnum pAbility)
	{
		this(pAbility, "");
	}

	public Ability(AbilityEnum pAbility, String pQualifier)
	{
		ability = pAbility;
		qualifier = pQualifier==null ? "" : pQualifier;
	}

	@Override public String toString(){
		if(qualifier == null || qualifier.length() < 1)
		{return abilityName();}

		return abilityName()+"("+qualifier+")";
	}

	@Override
	public boolean equals(Object other)
	{
		try
		{
			Ability p = (Ability)other;
			return p.ability == ability && p.qualifier.equals(qualifier);
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override public int hashCode()
	{
		int hash = 0;
		if(ability != null){hash += 13*17*ability.ordinal();}
		if(qualifier != null){hash += 19*qualifier.hashCode();}
		return hash;
	}

	public AbilityEnum abilityEnum(){return ability;}
	public String qualifier(){return qualifier;}

	//mimic abilityEnum API
	public String abilityName(){return ability.displayName();}
	public String longDescription(){return ability.longDescription();}
	public String shortDescription(){return ability.shortDescription();}
	public String pageNumber(){return ability.pageNumbrer();}

	@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar) {return ability.meetsPrereq(myChar);}
}
