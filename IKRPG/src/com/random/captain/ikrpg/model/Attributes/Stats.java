package com.random.captain.ikrpg.model.Attributes;

public enum Stats
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
	//"Other" stats
	ATTACK("Attack modifier","ATK"),
	DAMAGE("Damage modifier","DMG");
	
	private Stats(String pLongName, String pShortName)
	{
		this.longName = pLongName;
		this.shortname = pShortName;
	};
	
	String longName;
	String shortname;
}
