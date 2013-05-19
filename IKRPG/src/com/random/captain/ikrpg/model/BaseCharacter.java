package com.random.captain.ikrpg.model;
import com.random.captain.ikrpg.model.Attributes.*;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import java.util.Collection;
import java.util.Set;

public class BaseCharacter implements Parcelable
{
	/*Fluff*/
	private String name;
	
	/*Crunch*/
	private Race race;
	private Archetype archetype;
	private Set<Career> careers;
	private Set<Ability> abilities;
	private Set<Spell> spells;
	private SkillsBundle skillsBundle;
	private StatsBundle statsBundle;
	
	/* Constructor */
	public BaseCharacter(String pName, Race pRace, Archetype pArchetype, Set<Career> pCareers, Set<Ability> pAbilities,
							Set<Spell> pSpells, SkillsBundle pSkills, StatsBundle pStats)
	{
		name = pName;
		race = pRace;
		archetype = pArchetype;
		careers = pCareers;
		abilities = pAbilities;
		spells = pSpells;
		skillsBundle = pSkills;
		statsBundle = pStats;
	}
	
	public String name(){return name;}
	public Race race(){return race;}
	public Archetype archetype(){return archetype;}
	public Set<Career> careers(){return careers;}
	public Set<Ability> abilities(){return abilities;}
	public Set<Spell> spells(){return spells;}
	public SkillsBundle skillsBundle(){return skillsBundle;}
	public StatsBundle statsBundle(){return statsBundle;}
	
	public int describeContents()
	{
		return 0; //?
	}
	
	/* Parcelling */
	public void writeToParcel(Parcel toParcel, int flags)
	{
		toParcel.writeString(name);
		toParcel.writeSerializable(race);
		toParcel.writeSerializable(archetype);
		toParcel.writeTypedArray(careers.toArray(new Career[careers.size()]),0);
		toParcel.writeTypedArray(abilities.toArray(new Ability[abilities.size()]),0);
		toParcel.writeTypedArray(spells.toArray(new Spell[spells.size()]),0);
		toParcel.writeParcelable(skillsBundle,0);
		toParcel.writeParcelable(statsBundle,0);
	}
	
	public static final Parcelable.Creator<BaseCharacter> CREATOR = new Parcelable.Creator<BaseCharacter>()
	{
		@Override
		public BaseCharacter createFromParcel(Parcel in)
		{
			BaseCharacter me = new BaseCharacter(null, null, null, null, null, null, null, null);
			return me;
		}

		@Override
		public BaseCharacter[] newArray(int size)
		{
			return new BaseCharacter[size];
		}
	};
	
	public String toString()
	{
		StringBuilder myString = new StringBuilder(100);
		myString.append(name).append("\n");
		myString.append(archetype.displayName()).append(" ").append(race.displayName());
		myString.append("\n");
		
		myString.append("\nCareers: \n");
		for(Career career: careers)
		{
			myString.append(career.displayName()).append("\n");
		}
		
		myString.append("\nSkills: \n");
		Collection<Pair<Skill, Integer>> mySkills = skillsBundle.getTrainedSkills();
		for(Pair<Skill, Integer> skillPair : mySkills)
		{
			Skill skill = skillPair.first;
			myString.append(skill.name()).append(": ").append(skillPair.second).append("\n");
		}
		
		myString.append("\nAbilities: \n");
		for(Ability ability: abilities)
		{
			myString.append(ability.displayName()).append("\n");
		}
		
		myString.append("\nSpells: \n");
		for(Spell spell: spells)
		{
			myString.append(spell.displayName()).append("\n");
		}
		
		return myString.toString();
	}
}
