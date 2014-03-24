package com.random.captain.ikrpg.character;


public class Skill
{
	private SkillEnum skill;
	private String qualifier;
	
	public Skill(SkillEnum pSkill)
	{
		this(pSkill, "");
	}
	
	public Skill(SkillEnum pSkill, String pQualifier)
	{
		skill = pSkill;
		qualifier = pQualifier == null ? "" : pQualifier;
	}
	
	@Override public String toString(){
		if(qualifier == null || qualifier.length() < 1)
		{return skillName();}
		
		return skillName()+"("+qualifier+")";
	}

	@Override
	public boolean equals(Object other)
	{
		//lazy man's type check
		try
		{
			Skill p = (Skill)other;
			return p.skill == skill && p.qualifier.equals(qualifier);
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override public int hashCode()
	{
		int hash = 0;
		if(skill != null){hash += 13*17*skill.ordinal();}
		if(qualifier != null){hash += 19*qualifier.hashCode();}
		return hash;
	}
	
	public SkillEnum skillEnum(){return skill;}
	public String qualifier(){return qualifier;}
	
	//mimic skillEnum API
	public String skillName(){return skill.displayName();}
	public Stat governingStat(){return skill.governingStat();}
	public boolean isMilitary(){return skill.isMilitary();}
	public boolean isGeneral(){return skill.isGeneral();}
	public boolean isQualifiable(){return skill.isQualifiable();}
	public boolean canUseUntrained(){return skill.canUseUntrained();}
}
