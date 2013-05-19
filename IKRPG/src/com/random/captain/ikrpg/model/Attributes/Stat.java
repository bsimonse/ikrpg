package com.random.captain.ikrpg.model.Attributes;

public enum Stat 
{
	//"Normal stats"
	PHYSIQUE("Physique", "PHY"),
	SPEED("Speed","SPD"),
	STRENGTH("Strength","STR"),
	AGILITY("Agility","AGI"),
	POISE("Poise","POI"),
	PROWESS("Prowess","PRW"),
	INTELLECT("Intellect","INT"),
	ARCANE("Arcane","ARC"),
	PERCEPTION("Perception","PER"),
	DEFENSE("Defense","DEF"),
	INITIATIVE("Initiative","INIT"),
	ARMOR("Armor","ARM"),
	WILLPOWER("Willpower","WIL"),
	COMMAND("Command","CMD"),
	CONTROL("Control","CTRL"),
	//"Other" stats
	ATTACK("Attack modifier","ATK"),
	DAMAGE("Damage modifier","DMG");
	
	private Stat(String pLongName, String pShortName)
	{
		this.longName = pLongName;
		this.shortName = pShortName;
	};
	
	String longName;
	String shortName;
	
	public String longName()
	{
		return longName;
	}
	
	public String shortName()
	{
		return shortName;
	}
	
	@Override
	public String toString()
	{
		return longName+"("+shortName+")";
	}
}
