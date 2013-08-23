package com.random.captain.ikrpg.character;


public enum Spell
{
	ARCANE_BOLT("Arcane Bolt", "You shoot magic", 2, 12, 0, 11, false, true),
	ARCANE_STRIKE("Arcane Strike", "You shoot magic", 1, 8, 0, 8, false, true),
	ARCANTRIK_BOLT("Arcantrik Bolt","",2,10,0,12,false,true),
	BLACK_OUT("Black Out","",4,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	BROADSIDE("Broadside","",3,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	ELECTRICAL_BLAST("Electrical Blast","",3,8,3,13,false,true),
	ELECTRIFY("Electrify","",2,6,0,0,true,false),
	FAIL_SAFE("Fail Safe","",3,6,0,0,true,false),
	FORCE_FIELD("Force Field","",3,SpellRange.SELF,SpellRange.CTRL,0,true,false),
	FORTIFY("Fortify","",2,6,0,0,true,false),
	FULL_THROTTLE("Full Throttle","",3,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	GRIND("Grind","",3,10,0,14,false,true),
	GUIDED_FIRE("Guided Fire","",3,SpellRange.SELF, SpellRange.CTRL, 0, false, false),
	IRON_AGGRESSION("Iron Aggression","",3,6,0,0,true,false),
	JACKHAMMER("Jackhammer","",1,6,0,0,false,false),
	JUMP_START("Jump Start","",1,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	LOCOMOTION("Locomotion","",1,6,0,0,false,false),
	POLARITY_SHIELD("Polarity Shield","",2,6,0,0,true,false),
	POSITIVE_CHARGE("Positive Charge","",2,6,0,0,false,false),
	POWER_BOOSTER("Power Booster","",1,5,0,0,false,false),
	PROTECTION_FROM_ELECTRICITY("Protection From Electricity","",1,6,0,0,true,false),
	REDLINE("Redline","",2,6,0,0,true,false),
	REFUGE("Refuge","",2,6,0,0,true,false),
	RETURN_FIRE("Return Fire","",1,6,0,0,false,false),
	SHORT_OUT("Short Out","",1,8,0,0,false,true),
	SUPERIORITY("Superiority","",3,6,0,0,true,false),
	TEMPER_METAL("Temper Metal","",2,6,0,0,true,false),
	TIDE_OF_STEEL("Tide of Steel","",4,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	VOLTAIC_LOCK("Voltaic Lock","",4,10,SpellRange.SPECIAL,0,false,true);
	
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

	private String spellName;
	private String description;
	private int cost;
	private int range;
	private int aoe;
	private int pow;
	private boolean upkeepable;
	private boolean offensive;
	
	public String displayName() {return spellName;}
	public String description(){return description;}
	public int cost(){return cost;}
	public int range(){return range;}
	public int aoe(){return aoe;}
	public int pow(){return pow;};
	public boolean isUpkeepable(){return upkeepable;}
	public boolean isOffensive(){return offensive;}
}
	
class SpellRange
{	
	public static final int CTRL = -1;
	public static final int SELF = -2;
	public static final int SPECIAL = -3;
	public static final int SP8 = -4;
	public static final int WALL = -5;
}
