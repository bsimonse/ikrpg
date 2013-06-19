package com.random.captain.ikrpg.character;

import java.util.*;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.random.captain.ikrpg.character.Skill;

public class PC extends zzBaseCharacter
{
	public PC()
	{
		super();
	}
	
	//temporary; not really a copy constructor
	public PC(zzBaseCharacter base)
	{
		super();
		if(base.fluff!=null){fluff=base.fluff;}
		if(base.race!=null)race=base.race;
		if(base.archetype!=null)archetype=base.archetype;
		if(base.tradition!=null)tradition=base.tradition;
		if(base.careers!=null)careers=base.careers;
		if(base.abilities!=null)abilities=base.abilities;
		if(base.spells!=null)spells=base.spells;
		if(base.baseSkills!=null)baseSkills=base.baseSkills;
		if(base.skillModifiers!=null)skillModifiers=base.skillModifiers;
		if(base.baseStats!=null)baseStats=base.baseStats;
		if(base.maxStats!=null)maxStats=base.maxStats;
		if(base.statModifiers!=null)statModifiers=base.statModifiers;
		exp=base.exp;
		level=zzLevel.getLevelForEXP(exp);
		deriveStats();
		deriveSkillCheckLevels();
	}
	
	public static final Parcelable.Creator<PC> CREATOR = new Parcelable.Creator<PC>()
	{
		@Override
		public PC createFromParcel(Parcel in)
		{
			return new PC(zzBaseCharacter.CREATOR.createFromParcel(in));
		}

		@Override public PC[] newArray(int size) {return new PC[size];}
	};
	
	public String toJson()
	{
		return super.toJson();
	}

	public static PC fromJson(String json)
	{
		return new PC(zzBaseCharacter.fromJson(json));
	}
}
