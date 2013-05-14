package com.random.captain.ikrpg.model.Attributes;

public enum Spell
{
	ARCANE_BOLT("Arcane Bolt", "You shoot magic", 2, 12, 0, 11, false, true),
	ARCANE_STRIKE("Arcane Strike", "You shoot magic", 1, 8, 0, 8, false, true);
	
	private Spell(String pName, String pDesc, int pCost, int pRange, int pAOE, int pPow, boolean pUpkeepable, boolean pOffensive)
	{
		spellName = pName;
		description = pDesc;
		cost = pCost;
		range = pRange;
		aoe = pAOE;
		pow = pPow;
		upkeepable = pUpkeepable;
		offensive = pOffensive;
	}
	
	//I reeeally don't want to make an enum for range/aoe... hope I can get away with this.
	public static final int CTRL = -1;
	public static final int SELF = -2;
	public static final int SPECIAL = -3;
	public static final int SP8 = -4;
	
	private String spellName;
	private String description;
	private int cost;
	private int range;
	private int aoe;
	private int pow;
	private boolean upkeepable;
	private boolean offensive;
	
	public String spellName() {return spellName;}
	public String description(){return description;}
	public int cost(){return cost;}
	public int range(){return range;}
	public int aoe(){return aoe;}
	public int pow(){return pow;};
	public boolean isUpkeepable(){return upkeepable;}
	public boolean isOffensive(){return offensive;}
}
