package com.random.captain.ikrpg.model.Attributes;

public enum Skill
{
	ARCHERY("Archery",Stat.POISE, true, false, true),
	CROSSBOW("Crossbow",Stat.POISE, true, false, true),
	GREAT_WEAPON("Great Weapon",Stat.PROWESS, true, false, true),
	HAND_WEAPON("Hand Weapon",Stat.PROWESS, true, false, true),
	LANCE("Lance",Stat.PROWESS, true, false, true),
	LIGHT_ARTILLERY("Light Artillery",Stat.POISE, true, false, true),
	PISTOL("Pistol",Stat.POISE, true, false, true),
	RIFLE("Rifle",Stat.POISE, true, false, true),
	SHIELD("Shield",Stat.PROWESS, true, false, true),
	THROWN_WEAPON("Thrown Weapon",Stat.PROWESS, true, false, true),
	UNARMED("Unarmed Combat",Stat.PROWESS, true, false, true),
	ALCHEMY("Alchemy",Stat.INTELLECT, false, false, false),
	ANIMAL_HANDLING("Animal Handling",null, false, true, false),
	BRIBERY("Bribery",null, false, false, true),
	CLIMBING("Climbing",Stat.AGILITY, false, true, true),
	COMMAND("Command",null, false, false, false),
	CRAFT("Craft (X)",Stat.INTELLECT, false, false, false),
	CRYPTOGRAPHY("Crytpography",Stat.INTELLECT, false, false, false),
	DECEPTION("Deception",null, false, false, true),
	DETECTION("Detection",Stat.PERCEPTION, false, true, true),
	DISGUISE("Disguise",Stat.INTELLECT, false, false, true),
	DRIVING("Driving",Stat.AGILITY, false, true, true),
	ESCAPE_ARTIST("Escape Artist",Stat.AGILITY, false, false, true),
	ETIQUETTE("Etiquette",null, false, false, true),
	FELL_CALLING("Fell Calling",Stat.POISE, false, false, false),
	FORENSIC_SCIENCE("Forensic Science",Stat.INTELLECT, false, false, false),
	FORGERY("Forgery",null, false, false, false),
	GAMLBING("Gambling",Stat.PERCEPTION, false, true, true),
	INTERROGATION("Interrogation",Stat.INTELLECT, false, false, true),
	INTIMIDATION("Intimidation",null, false, true, true),
	JUMPING("Jumping",Stat.PHYSIQUE, false, true, true),
	LAW("Law",Stat.INTELLECT, false, false, false),
	LOCK_PICKING("Lock Picking",Stat.AGILITY, false, false, false),
	LORE("Lore (X)",Stat.INTELLECT, false, true, true),
	MECHANIKAL("Mechanikal Engineering",Stat.INTELLECT, false, false, false),
	MEDICINE("Medicine",Stat.INTELLECT, false, false, true),
	NAVIGATION("Navigation",Stat.PERCEPTION, false, false, false),
	NEGOTIATION("Negotiation",null, false, false, false),
	ORATORY("Oratory",null, false, false, false),
	PICKPOCKET("Pickpocket",Stat.AGILITY, false, false, false),
	RESEARCH("Research",Stat.INTELLECT, false, false, true),
	RIDING("Riding",Stat.AGILITY, false, true, true),
	ROPE_USE("Rope Use",Stat.AGILITY, false, false, true),
	SAILING("Sailing",null, false, false, false),
	SEDUCTION("Seduction",null, false, false, false),
	SNEAK("Sneak",Stat.AGILITY, false, false, true),
	STREETWISE("Streetwise",Stat.PERCEPTION, false, false, false),
	SURVIVAL("Survival",Stat.PERCEPTION, false, false, true),
	SWIMMING("Swimming",Stat.STRENGTH, false, true, true),
	TRACKING("Tracking",Stat.PERCEPTION, false, false, false);
	
	private Skill(String pName, Stat pStat, boolean pMilitary, boolean pGeneral, boolean pUntrained)
	{
		name = pName;
		governingStat = pStat;
		isMilitary = pMilitary;
		isGeneral = pGeneral;
		canUseUntrained = pUntrained || pMilitary; //just in case
	}
	
	@Override
	public String toString(){return name;}
	
	//Assisting will come later
	
	private String name;
	private boolean isMilitary;
	private boolean isGeneral;
	private boolean canUseUntrained;
	private Stat governingStat;
	
	public String displayName(){return name;}
	public Stat governingStat(){return governingStat;}
	public boolean isMilitary(){return isMilitary;}
	public boolean isGeneral(){return isGeneral;}
	public boolean canUseUntrained(){return canUseUntrained;}
}
