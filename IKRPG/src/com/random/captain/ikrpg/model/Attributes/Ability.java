package com.random.captain.ikrpg.model.Attributes;

public enum Ability
{
	JACK_MARSHALL("'Jack Marshall", "Kickin'"),
	ACE_COMMANDER("Ace Commander", "Whee"),
	ACROBATICS("Acrobatics","Jump"),
	BOMBER("Bomber",""),
	BREW_MASTER("Brew Master",""),
	FAST_COOK("Fast Cook",""),
	FIELD_ALCHEMIST("Field Alchemist", ""),
	FIRE_IN_THE_HOLE("Fire in the Hole!",""),
	FREE_STYLE("Free Style",""),
	GRENADIER("Grenadier","Boom"),
	POISON_RESISTANCE("Poison Resistance", "Hiss");
	
	private Ability(String pName, String pDesc)
	{
		name = pName;
		description = pDesc;
	}
	
	private String name;
	private String description;
	
	public String getName(){return name;}
	public String getDescription(){return description;}
}
