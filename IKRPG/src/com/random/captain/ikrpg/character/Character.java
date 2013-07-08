package com.random.captain.ikrpg.character;

import java.util.*;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.random.captain.ikrpg.character.Skill;

public class Character extends zzBaseCharacter
{
	//conversion constructor
	Character(zzBaseCharacter base)
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
	
	public static final Parcelable.Creator<Character> CREATOR = new Parcelable.Creator<Character>()
	{
		@Override
		public Character createFromParcel(Parcel in)
		{
			return new Character(zzBaseCharacter.CREATOR.createFromParcel(in));
		}

		@Override public Character[] newArray(int size) {return new Character[size];}
	};
	
	public String toJson()
	{return super.toJson();}

	public static Character fromJson(String json)
	{return new Character(zzBaseCharacter.fromJson(json));}
}
