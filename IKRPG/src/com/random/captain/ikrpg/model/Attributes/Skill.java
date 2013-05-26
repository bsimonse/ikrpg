package com.random.captain.ikrpg.model.Attributes;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;
import com.random.captain.ikrpg.model.Attributes.Modifier;

public class Skill implements Parcelable
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
		qualifier = pQualifier==null ? "" : pQualifier;
	}
	
	@Override public String toString(){
		if(qualifier == null || qualifier.length() < 1)
		{return skillName();}
		
		return skillName()+"("+qualifier+")";
	}

	@Override
	public boolean equals(Object other)
	{
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
	
	//Helper methods for Career
	public static Pair<Skill, Integer> skillPair(SkillEnum pSkill, int level)
	{
		return skillPair(pSkill, "", level);
	}
	
	public static Pair<Skill, Integer> skillPair(SkillEnum pSkill, String qualifier, int level)
	{
		return new Pair<Skill, Integer>(new Skill(pSkill, qualifier), level);
	}
	
	/* Parcelling */
	public void writeToParcel(Parcel toParcel, int flags)
	{
		toParcel.writeSerializable(skill);
		toParcel.writeString(qualifier);
	}

	public static final Parcelable.Creator<Skill> CREATOR = new Parcelable.Creator<Skill>()
	{
		@Override
		public Skill createFromParcel(Parcel in)
		{
			try
			{
				SkillEnum pSkill = (SkillEnum)in.readSerializable();
				String pQualifier = in.readString();
				return new Skill(pSkill, pQualifier);
			}
			catch(Exception e)
			{
				Log.e("IKRPG","Bad news, dude, Skill didn't Parcel correctly!");
				return new Skill(null,null);
			}
		}

		@Override
		public Skill[] newArray(int size)
		{
			return new Skill[size];
		}
	};

	public int describeContents()
	{
		return 0;
	}
}
