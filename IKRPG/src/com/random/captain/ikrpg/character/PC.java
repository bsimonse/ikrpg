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
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Skill.class,new SkillSerializer());
		//builder.registerTypeAdapter(zzSkillsBundle.class,new zzSkillsBundleSerializer());

		Gson gson = builder.create();
		return gson.toJson(this);
	}

	public static PC fromJson(String json)
	{
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Skill.class,new SkillDeserializer());
		//builder.registerTypeAdapter(zzSkillsBundle.class,new zzSkillsBundleDeserializer());
		Gson gson = builder.create();

		return gson.fromJson(json, PC.class);
	}
}
