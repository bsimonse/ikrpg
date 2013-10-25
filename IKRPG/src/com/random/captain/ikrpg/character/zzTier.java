package com.random.captain.ikrpg.character;

enum zzTier
{
	HERO("Hero",0,2),
	VETERAN("Veteran",50,3),
	EPIC("Epic",100,4);
	
	private zzTier(String pName, int pExpPoint, int pSkillCap)
	{
		name = pName;
		expPoint = pExpPoint;
		skillCap = pSkillCap;
	}
	
	private String name;
	private int expPoint;
	private int skillCap;
	
	public String toString(){return name;}
	public int skillCap(){return skillCap;}
	
	public static zzTier getLevelForEXP(int exp)
	{
		if(exp>=EPIC.expPoint){return EPIC;}
		if(exp>=VETERAN.expPoint){return VETERAN;}
		return HERO;
	}
}
