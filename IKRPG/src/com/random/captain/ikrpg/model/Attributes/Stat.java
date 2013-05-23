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
	MELEE_ATTACK("Melee attack modifier","ATK(M)"),
	MELEE_DAMAGE("Melee damage modifier","DMG(M)"),
	RANGED_ATTACK("Ranged attack modifier","ATK(R)"),
	RANGED_DAMAGE("Ranged damage modifier","DMG(R)");
	
	private Stat(String pLongName, String pShortName)
	{
		this.longName = pLongName;
		this.shortName = pShortName;
	};
	
	private String longName;
	private String shortName;
	
	public String longName(){return longName;}
	public String shortName(){return shortName;}
	
	@Override
	public String toString(){return longName+"("+shortName+")";}
}
