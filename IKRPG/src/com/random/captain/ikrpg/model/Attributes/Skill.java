package com.random.captain.ikrpg.model.Attributes;

public enum Skill
{
	ARCHERY(Stat.POISE, true, true),
	CROSSBOW(Stat.POISE, true, true),
	GREAT_WEAPON(Stat.PROWESS, true, true),
	HAND_WEAPON(Stat.PROWESS, true, true),
	LANCE(Stat.PROWESS, true, true),
	LIGHT_ARTILLERY(Stat.POISE, true, true),
	PISTOL(Stat.POISE, true, true),
	RIFLE(Stat.POISE, true, true),
	SHIELD(Stat.PROWESS, true, true),
	THROWN_WEAPON(Stat.PROWESS, true, true),
	UNARMED(Stat.PROWESS, true, true),
	ALCHEMY(Stat.INTELLECT, false, false),
	ANIMAL_HANDLING(null, false, false),
	BRIBERY(null, false, true),
	CLIMBING(Stat.AGILITY, false, true),
	COMMAND(null, false, false),
	CRAFT(Stat.INTELLECT, false, false),
	CRYPTOGRAPHY(Stat.INTELLECT, false, false),
	DECEPTION(null, false, true),
	DETECTION(Stat.PERCEPTION, false, true),
	DISGUISE(Stat.INTELLECT, false, true),
	DRIVING(Stat.AGILITY, false, true),
	ESCAPE_ARTIST(Stat.AGILITY, false, true),
	ETIQUETTE(null, false, true),
	FELL_CALLING(Stat.POISE, false, false),
	FORENSIC_SCIENCE(Stat.INTELLECT, false, false),
	FORGERY(null, false, false),
	GAMLBING(Stat.PERCEPTION, false, true),
	INTERROGATION(Stat.INTELLECT, false, true),
	INTIMIDATION(null, false, true),
	JUMPING(Stat.PHYSIQUE, false, true),
	LAW(Stat.INTELLECT, false, false),
	LOCK_PICKING(Stat.AGILITY, false, false),
	LORE(Stat.INTELLECT, false, true),
	MECHANIKAL(Stat.INTELLECT, false, false),
	MEDICINE(Stat.INTELLECT, false, true),
	NAVIGATION(Stat.PERCEPTION, false, false),
	NEGOTIATION(null, false, false),
	ORATORY(null, false, false),
	PICKPOCKET(Stat.AGILITY, false, false),
	RESEARCH(Stat.INTELLECT, false, true),
	RIDING(Stat.AGILITY, false, true),
	ROPE_USE(Stat.AGILITY, false, true),
	SAILING(null, false, false),
	SEDUCTION(null, false, false),
	SNEAK(Stat.AGILITY, false, true),
	STREETWISE(Stat.PERCEPTION, false, false),
	SURVIVAL(Stat.PERCEPTION, false, true),
	SWIMMING(Stat.STRENGTH, false, true),
	TRACKING(Stat.PERCEPTION, false, false);
	
	private Skill(Stat pStat, boolean pMilitary, boolean pUntrained)
	{
		governingStat = pStat;
		isMilitary = pMilitary;
		canUseUntrained = pUntrained || pMilitary; //just in case
	}
	
	//Assisting will come later
	
	private boolean isMilitary;
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
	
	public boolean canUseUntrained()
	{
		return canUseUntrained;
	}
}
