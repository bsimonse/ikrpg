package com.random.captain.ikrpg.character;

import java.util.*;

import android.os.Parcel;
import android.os.Parcelable;

class zzBaseCharacter implements Parcelable
{
	/*Fluff*/
	Fluff fluff;
	
	/*Crunch*/
	Race race;
	Archetype archetype;
	GiftedTradition tradition;
	Set<Career> careers;
	Set<Ability> abilities;
	Set<Spell> spells;
	zzSkillsBundle skillsBundle;
	zzStatsBundle statsBundle;
	zzLevel level;
	int exp;
	
	/* Constructor */
	zzBaseCharacter(Fluff pFluff, Race pRace, Archetype pArchetype, Set<Career> pCareers, Set<Ability> pAbilities,
							Set<Spell> pSpells, zzSkillsBundle pSkills, zzStatsBundle pStats, int pExp)
	{
		fluff = pFluff;
		race = pRace;
		archetype = pArchetype;
		careers = pCareers;
		abilities = pAbilities;
		spells = pSpells;
		skillsBundle = pSkills;
		statsBundle = pStats;
		exp = pExp;
		level = zzLevel.getLevelForEXP(pExp);
	}
	
	zzBaseCharacter()
	{
		this(new Fluff(),null,null,new HashSet<Career>(),new HashSet<Ability>(),new HashSet<Spell>(),null, null,0);
	}
	
	public Fluff fluff(){return fluff;}
	public Race race(){return race;}
	public Archetype getArchetype(){return archetype;}
	public GiftedTradition getTradition(){return tradition;}
	public Set<Career> getCareers(){return new HashSet<Career>(careers);}
	public Set<Ability> getAbilities(){return new HashSet<Ability>(abilities);}
	public Set<Spell> getSpells(){return new HashSet<Spell>(spells);}
	public int exp(){return exp;}
	
	public void gainEXP(int pExpGain)
	{setEXP(exp+pExpGain);}
	
	public void setEXP(int pExpTotal)
	{
		//determine bonuses gained
		//TODO: add bonuses
		
		exp = pExpTotal;
	}
	
	/* Convenience methods for hooks and prereqs*/
	
	public boolean hasAbility(AbilityEnum ability){return abilities.contains(new Ability(ability));}
	public boolean hasAbility(AbilityEnum ability, String qualifier){return abilities.contains(new Ability(ability, qualifier));}
	
	public int getSkillLevel(SkillEnum skill){return skillsBundle.getSkillLevel(skill.make());}
	public int getSkillLevel(SkillEnum skill, String qualifier){return skillsBundle.getSkillLevel(skill.make(qualifier));}
	
	public int getBaseStat(Stat stat){return statsBundle.getBaseStat(stat);}
	public int getMaxStat(Stat stat){return statsBundle.getMaxStat(stat);}
	
	/* Parcelling */
	@Override
	public void writeToParcel(Parcel toParcel, int flags)
	{
		toParcel.writeParcelable(fluff, 0);
		
		toParcel.writeSerializable(race);
		toParcel.writeSerializable(archetype);
		
		int[] careerOrdinals = new int[careers.size()];int index=0;
		for(Career c: careers){careerOrdinals[index++]=c.ordinal();}
		toParcel.writeInt(careers.size());
		toParcel.writeIntArray(careerOrdinals);
		
		toParcel.writeParcelableArray(abilities.toArray(new Ability[0]), 0);
		
		int[] spellOrdinals = new int[spells.size()];index=0;
		for(Spell s: spells){spellOrdinals[index++]=s.ordinal();}
		toParcel.writeInt(spells.size());
		toParcel.writeIntArray(spellOrdinals);
		
		toParcel.writeParcelable(skillsBundle,0);
		toParcel.writeParcelable(statsBundle,0);
		
		toParcel.writeInt(exp);
	}
	
	public static final Parcelable.Creator<zzBaseCharacter> CREATOR = new Parcelable.Creator<zzBaseCharacter>()
	{
		@Override
		public zzBaseCharacter createFromParcel(Parcel in)
		{
			Fluff pFluff = in.readParcelable(Fluff.class.getClassLoader());
			
			Race pRace = (Race)in.readSerializable();
			Archetype pArchetype = (Archetype)in.readSerializable();
			
			int careerCount = in.readInt();
			int[] careerOrdinals = new int[careerCount]; in.readIntArray(careerOrdinals);
			Set<Career> pCareers = new HashSet<Career>(); Career[] careers = Career.values();
			for(int c:careerOrdinals)
			{pCareers.add(careers[c]);}
			
			Parcelable[] pAbilitiesArray = in.readParcelableArray(Ability.class.getClassLoader());
			Set<Ability> pAbilities = new HashSet<Ability>();
			pAbilities.addAll((List<Ability>)Arrays.asList(pAbilitiesArray));
			
			int spellCount = in.readInt();
			int[] spellOrdinals = new int[spellCount]; in.readIntArray(spellOrdinals);
			Set<Spell> pSpells = new HashSet<Spell>(); Spell[] spells = Spell.values();
			for(int s:spellOrdinals)
			{pSpells.add(spells[s]);}
			
			zzSkillsBundle pSkills = in.readParcelable(zzSkillsBundle.class.getClassLoader());
			zzStatsBundle pStats = in.readParcelable(zzStatsBundle.class.getClassLoader());
			
			int pExp = in.readInt();
			zzBaseCharacter me = new zzBaseCharacter(pFluff, pRace, pArchetype, pCareers, pAbilities, pSpells, pSkills, pStats, pExp);
			return me;
		}

		@Override public zzBaseCharacter[] newArray(int size) {return new zzBaseCharacter[size];}
	};
	
	@Override public int describeContents(){return 0;}
	
	@Override
	public String toString()
	{
		StringBuilder myString = new StringBuilder(100);
		
		myString.append(fluff.toString()+"\n");
		
		myString.append(archetype.displayName()+" "+race.displayName());
		myString.append("\n");
		
		myString.append("EXP: "+exp+"\n");
		
		myString.append(statsBundle.toString());
		
		myString.append("\nCareers: \n");
		for(Career career: careers)
		{myString.append(career.displayName()).append("\n");}
		
		myString.append(skillsBundle.toString());
		
		myString.append("\nAbilities: \n");
		for(Ability ability: abilities)
		{myString.append(ability.toString()).append("\n");}
		
		myString.append("\nSpells: \n");
		for(Spell spell: spells)
		{myString.append(spell.displayName()).append("\n");}
		
		return myString.toString();
	}
}
