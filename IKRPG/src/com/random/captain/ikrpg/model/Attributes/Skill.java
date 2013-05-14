package com.random.captain.ikrpg.model.Attributes;

public enum Skill
{
	ARCHERY(Stat.POISE, true, false, true),
	CROSSBOW(Stat.POISE, true, false, true),
	GREAT_WEAPON(Stat.PROWESS, true, false, true),
	HAND_WEAPON(Stat.PROWESS, true, false, true),
	LANCE(Stat.PROWESS, true, false, true),
	LIGHT_ARTILLERY(Stat.POISE, true, false, true),
	PISTOL(Stat.POISE, true, false, true),
	RIFLE(Stat.POISE, true, false, true),
	SHIELD(Stat.PROWESS, true, false, true),
	THROWN_WEAPON(Stat.PROWESS, true, false, true),
	UNARMED(Stat.PROWESS, true, false, true),
	ALCHEMY(Stat.INTELLECT, false, false, false),
	ANIMAL_HANDLING(null, false, true, false),
	BRIBERY(null, false, false, true),
	CLIMBING(Stat.AGILITY, false, true, true),
	COMMAND(null, false, false, false),
	CRAFT(Stat.INTELLECT, false, false, false),
	CRYPTOGRAPHY(Stat.INTELLECT, false, false, false),
	DECEPTION(null, false, false, true),
	DETECTION(Stat.PERCEPTION, false, true, true),
	DISGUISE(Stat.INTELLECT, false, false, true),
	DRIVING(Stat.AGILITY, false, true, true),
	ESCAPE_ARTIST(Stat.AGILITY, false, false, true),
	ETIQUETTE(null, false, false, true),
	FELL_CALLING(Stat.POISE, false, false, false),
	FORENSIC_SCIENCE(Stat.INTELLECT, false, false, false),
	FORGERY(null, false, false, false),
	GAMLBING(Stat.PERCEPTION, false, true, true),
	INTERROGATION(Stat.INTELLECT, false, false, true),
	INTIMIDATION(null, false, true, true),
	JUMPING(Stat.PHYSIQUE, false, true, true),
	LAW(Stat.INTELLECT, false, false, false),
	LOCK_PICKING(Stat.AGILITY, false, false, false),
	LORE(Stat.INTELLECT, false, true, true),
	MECHANIKAL(Stat.INTELLECT, false, false, false),
	MEDICINE(Stat.INTELLECT, false, false, true),
	NAVIGATION(Stat.PERCEPTION, false, false, false),
	NEGOTIATION(null, false, false, false),
	ORATORY(null, false, false, false),
	PICKPOCKET(Stat.AGILITY, false, false, false),
	RESEARCH(Stat.INTELLECT, false, false, true),
	RIDING(Stat.AGILITY, false, true, true),
	ROPE_USE(Stat.AGILITY, false, false, true),
	SAILING(null, false, false, false),
	SEDUCTION(null, false, false, false),
	SNEAK(Stat.AGILITY, false, false, true),
	STREETWISE(Stat.PERCEPTION, false, false, false),
	SURVIVAL(Stat.PERCEPTION, false, false, true),
	SWIMMING(Stat.STRENGTH, false, true, true),
	TRACKING(Stat.PERCEPTION, false, false, false);
	
	private Skill(Stat pStat, boolean pMilitary, boolean pGeneral, boolean pUntrained)
	{
		governingStat = pStat;
		isMilitary = pMilitary;
		isGeneral = pGeneral;
		canUseUntrained = pUntrained || pMilitary; //just in case
	}
	
	//Assisting will come later
	
	private boolean isMilitary;
	private boolean isGeneral;
	private boolean canUseUntrained;
	private Stat governingStat;
	
	public Stat governingStat()
	{
		return governingStat;
	}
	
	public boolean isMilitary()
	{
		return isMilitary;
	}
	
	public boolean isGeneral()
	{
		return isGeneral;
	}
	
	public boolean canUseUntrained()
	{
		return canUseUntrained;
	}
}
