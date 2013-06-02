package com.random.captain.ikrpg.character;

import android.util.Pair;

public enum SkillEnum
{
	ARCHERY("Archery",Stat.POISE, true, false, false, true),
	CROSSBOW("Crossbow",Stat.POISE, true, false, false, true),
	GREAT_WEAPON("Great Weapon",Stat.PROWESS, true, false, false, true),
	HAND_WEAPON("Hand Weapon",Stat.PROWESS, true, false, false, true),
	LANCE("Lance",Stat.PROWESS, true, false, false, true),
	LIGHT_ARTILLERY("Light Artillery",Stat.POISE, true, false, false, true),
	PISTOL("Pistol",Stat.POISE, true, false, false, true),
	RIFLE("Rifle",Stat.POISE, true, false, false, true),
	SHIELD("Shield",Stat.PROWESS, true, false, false, true),
	THROWN_WEAPON("Thrown Weapon",Stat.PROWESS, true, false, false, true),
	UNARMED("Unarmed Combat",Stat.PROWESS, true, false, false, true),
	ALCHEMY("Alchemy",Stat.INTELLECT, false, false, false, false),
	ANIMAL_HANDLING("Animal Handling",null, false, true, false, false),
	BRIBERY("Bribery",null, false, false, false, true),
	CLIMBING("Climbing",Stat.AGILITY, false, true, false, true),
	COMMAND("Command",null, false, false, false, false),
	CRAFT("Craft",Stat.INTELLECT, false, false, true, false),
	CRYPTOGRAPHY("Crytpography",Stat.INTELLECT, false, false, false, false),
	DECEPTION("Deception",null, false, false, false, true),
	DETECTION("Detection",Stat.PERCEPTION, false, true, false, true),
	DISGUISE("Disguise",Stat.INTELLECT, false, false, false, true),
	DRIVING("Driving",Stat.AGILITY, false, true, false, true),
	ESCAPE_ARTIST("Escape Artist",Stat.AGILITY, false, false, false, true),
	ETIQUETTE("Etiquette",null, false, false, false, true),
	FELL_CALLING("Fell Calling",Stat.POISE, false, false, false, false),
	FORENSIC_SCIENCE("Forensic Science",Stat.INTELLECT, false, false, false, false),
	FORGERY("Forgery",null, false, false, false, false),
	GAMLBING("Gambling",Stat.PERCEPTION, false, true, false, true),
	INTERROGATION("Interrogation",Stat.INTELLECT, false, false, false, true),
	INTIMIDATION("Intimidation",null, false, true, false, true),
	JUMPING("Jumping",Stat.PHYSIQUE, false, true, false, true),
	LAW("Law",Stat.INTELLECT, false, false, false, false),
	LOCK_PICKING("Lock Picking",Stat.AGILITY, false, false, false, false),
	LORE("Lore",Stat.INTELLECT, false, true, true, true),
	MECHANIKAL("Mechanikal Engineering",Stat.INTELLECT, false, false, false, false),
	MEDICINE("Medicine",Stat.INTELLECT, false, false, false, true),
	NAVIGATION("Navigation",Stat.PERCEPTION, false, false, false, false),
	NEGOTIATION("Negotiation",null, false, false, false, false),
	ORATORY("Oratory",null, false, false, false, false),
	PICKPOCKET("Pickpocket",Stat.AGILITY, false, false, false, false),
	RESEARCH("Research",Stat.INTELLECT, false, false, false, true),
	RIDING("Riding",Stat.AGILITY, false, true, false, true),
	ROPE_USE("Rope Use",Stat.AGILITY, false, false, false, true),
	SAILING("Sailing",null, false, false, false, false),
	SEDUCTION("Seduction",null, false, false, false, false),
	SNEAK("Sneak",Stat.AGILITY, false, false, false, true),
	STREETWISE("Streetwise",Stat.PERCEPTION, false, false, false, false),
	SURVIVAL("Survival",Stat.PERCEPTION, false, false, false, true),
	SWIMMING("Swimming",Stat.STRENGTH, false, true, false, true),
	TRACKING("Tracking",Stat.PERCEPTION, false, false, false, false);
	
	private SkillEnum(String pName, Stat pStat, boolean pMilitary, boolean pGeneral, boolean pIsQualifiable, boolean pUntrained)
	{
		name = pName;
		governingStat = pStat;
		isMilitary = pMilitary;
		isGeneral = pGeneral;
		isQualifiable = pIsQualifiable;
		canUseUntrained = pUntrained || pMilitary; //just in case
	}
	
	@Override
	public String toString(){return displayName();}
	
	//Assisting will come later
	
	private String name;
	private boolean isMilitary;
	private boolean isGeneral;
	private boolean canUseUntrained;
	private boolean isQualifiable;
	private Stat governingStat;
	
	public String displayName(){return name;}
	public Stat governingStat(){return governingStat;}
	public boolean isMilitary(){return isMilitary;}
	public boolean isGeneral(){return isGeneral;}
	public boolean isQualifiable(){return isQualifiable;}
	public boolean canUseUntrained(){return canUseUntrained;}
	
	//Are these necessary/helpful?
	public Skill make(){return new Skill(this);}
	public Skill make(String qualifier){return new Skill(this, qualifier);}
	
	public Pair<Skill, Integer> pair(int skillLevel){return new Pair<Skill,Integer>(new Skill(this),skillLevel);}
	public Pair<Skill, Integer> pair(String qualifier, int skillLevel){return new Pair<Skill, Integer>(new Skill(this, qualifier),skillLevel);}
}
