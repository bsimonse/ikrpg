package com.random.captain.ikrpg.model;
import com.random.captain.ikrpg.model.Attributes.*;
import java.util.*;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

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
	
	public BaseCharacter()
	{}
	
	public String name(){return name;}
	public Race race(){return race;}
	public Archetype archetype(){return archetype;}
	public Set<Career> careers(){return careers;}
	public Set<Ability> abilities(){return abilities;}
	public Set<Spell> spells(){return spells;}
	public SkillsBundle skillsBundle(){return skillsBundle;}
	public StatsBundle statsBundle(){return statsBundle;}
	
	@Override
	public int describeContents()
	{
		return 0; //?
	}
	
	/* Parcelling */
	@Override
	public void writeToParcel(Parcel toParcel, int flags)
	{
		toParcel.writeString(name);
		toParcel.writeSerializable(race);
		toParcel.writeSerializable(archetype);
		
		int[] careerOrdinals = new int[careers.size()];int index=0;
		for(Career c: careers){careerOrdinals[index++]=c.ordinal();}
		toParcel.writeInt(careers.size());
		toParcel.writeIntArray(careerOrdinals);
		
		int[] abilityOrdinals = new int[abilities.size()];index=0;
		for(Ability a: abilities){abilityOrdinals[index++]=a.ordinal();}
		toParcel.writeInt(abilities.size());
		toParcel.writeIntArray(abilityOrdinals);
		
		int[] spellOrdinals = new int[spells.size()];index=0;
		for(Spell s: spells){spellOrdinals[index++]=s.ordinal();}
		toParcel.writeInt(spells.size());
		toParcel.writeIntArray(spellOrdinals);
		
		toParcel.writeParcelable(skillsBundle,0);
		toParcel.writeParcelable(statsBundle,0);
	}
	
	public static final Parcelable.Creator<BaseCharacter> CREATOR = new Parcelable.Creator<BaseCharacter>()
	{
		@Override
		public BaseCharacter createFromParcel(Parcel in)
		{
			String pName = in.readString();
			Race pRace = (Race)in.readSerializable();
			Archetype pArchetype = (Archetype)in.readSerializable();
			
			int careerCount = in.readInt();
			int[] careerOrdinals = new int[careerCount]; in.readIntArray(careerOrdinals);
			Set<Career> pCareers = new HashSet<Career>(); Career[] careers = Career.values();
			for(int c:careerOrdinals)
			{pCareers.add(careers[c]);}
			
			int abilityCount = in.readInt();
			int[] abilityOrdinals = new int[abilityCount]; in.readIntArray(abilityOrdinals);
			Set<Ability> pAbilities = new HashSet<Ability>(); Ability[] abilities = Ability.values();
			for(int a:abilityOrdinals)
			{pAbilities.add(abilities[a]);}
			
			int spellCount = in.readInt();
			int[] spellOrdinals = new int[spellCount]; in.readIntArray(spellOrdinals);
			Set<Spell> pSpells = new HashSet<Spell>(); Spell[] spells = Spell.values();
			for(int s:spellOrdinals)
			{pSpells.add(spells[s]);}
			
			SkillsBundle pSkills = in.readParcelable(SkillsBundle.class.getClassLoader());
			StatsBundle pStats = in.readParcelable(StatsBundle.class.getClassLoader());
			
			BaseCharacter me = new BaseCharacter(pName, pRace, pArchetype, pCareers, pAbilities, pSpells, pSkills, pStats);
			return me;
		}

		@Override
		public BaseCharacter[] newArray(int size)
		{
			return new BaseCharacter[size];
		}
	};
	
	@Override
	public String toString()
	{
		StringBuilder myString = new StringBuilder(100);
		myString.append(name).append("\n");
		myString.append(archetype.displayName()).append(" ").append(race.displayName());
		myString.append("\n");
		
		myString.append(statsBundle.toString());
		
		myString.append("\nCareers: \n");
		for(Career career: careers)
		{
			myString.append(career.displayName()).append("\n");
		}
		
		myString.append(skillsBundle.toString());
		
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
