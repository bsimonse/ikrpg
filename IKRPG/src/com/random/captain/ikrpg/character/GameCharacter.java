package com.random.captain.ikrpg.character;

import android.os.Parcel;
import android.os.Parcelable;

public class GameCharacter extends zzBaseCharacter
{
	//conversion constructor
	GameCharacter(zzBaseCharacter base)
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
	
	public static final Parcelable.Creator<GameCharacter> CREATOR = new Parcelable.Creator<GameCharacter>()
	{
		@Override
		public GameCharacter createFromParcel(Parcel in)
		{
			return new GameCharacter(zzBaseCharacter.CREATOR.createFromParcel(in));
		}

		@Override public GameCharacter[] newArray(int size) {return new GameCharacter[size];}
	};
	
	public String toJson()
	{return super.toJson();}

	public static GameCharacter fromJson(String json)
	{return new GameCharacter(zzBaseCharacter.fromJson(json));}
}
