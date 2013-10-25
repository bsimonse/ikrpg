package com.random.captain.ikrpg.character;

import android.os.Parcel;
import android.os.Parcelable;

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
		lootPack=base.lootPack;
		
		exp=base.exp;
		tier=zzTier.getLevelForEXP(exp);
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
