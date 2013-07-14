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
		fluff=base.fluff;
		race=base.race;
		archetype=base.archetype;
		tradition=base.tradition;
		careers=base.careers;
		abilities=base.abilities;
		spells=base.spells;
		baseSkills=base.baseSkills;
		skillModifiers=base.skillModifiers;
		baseStats=base.baseStats;
		maxStats=base.maxStats;
		statModifiers=base.statModifiers;
		gear=base.gear;
		
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