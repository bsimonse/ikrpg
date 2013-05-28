package com.random.captain.ikrpg.model;
import com.random.captain.ikrpg.model.Attributes.*;
import java.util.*;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseCharacter implements Parcelable
{
	/*Fluff*/
	private Fluff fluff;
	
	/*Crunch*/
	private Race race;
	private Archetype archetype;
	private GiftedTradition tradition;
	private Set<Career> careers;
	private Set<Ability> abilities;
	private Set<Spell> spells;
	private SkillsBundle skillsBundle;
	private StatsBundle statsBundle;
	private int exp;
	
	/* Constructor */
	public BaseCharacter(Fluff pFluff, Race pRace, Archetype pArchetype, Set<Career> pCareers, Set<Ability> pAbilities,
							Set<Spell> pSpells, SkillsBundle pSkills, StatsBundle pStats)
	{
		fluff = pFluff;
		race = pRace;
		archetype = pArchetype;
		careers = pCareers;
		abilities = pAbilities;
		spells = pSpells;
		skillsBundle = pSkills;
		statsBundle = pStats;
		exp = 0;
		
		//determine tradition
		//may have to move if rules ever change
		if(pArchetype == Archetype.GIFTED)
		{
			if(pCareers.contains(Career.WARCASTER)){tradition = GiftedTradition.FOCUSER;}
			else{tradition = GiftedTradition.WILL_WEAVER;}
		}
	}
	
	public BaseCharacter()
	{this(null,null,null,null,null,null,null,null);}
	
	public Fluff fluff(){return fluff;}
	public Race race(){return race;}
	public Archetype archetype(){return archetype;}
	public GiftedTradition tradition(){return tradition;}
	public Set<Career> careers(){return careers;}
	public Set<Ability> abilities(){return abilities;}
	public Set<Spell> spells(){return spells;}
	public SkillsBundle skillsBundle(){return skillsBundle;}
	public StatsBundle statsBundle(){return statsBundle;}
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
	}
	
	public static final Parcelable.Creator<BaseCharacter> CREATOR = new Parcelable.Creator<BaseCharacter>()
	{
		@Override
		public BaseCharacter createFromParcel(Parcel in)
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
			
			SkillsBundle pSkills = in.readParcelable(SkillsBundle.class.getClassLoader());
			StatsBundle pStats = in.readParcelable(StatsBundle.class.getClassLoader());
			
			BaseCharacter me = new BaseCharacter(pFluff, pRace, pArchetype, pCareers, pAbilities, pSpells, pSkills, pStats);
			return me;
		}

		@Override public BaseCharacter[] newArray(int size) {return new BaseCharacter[size];}
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
