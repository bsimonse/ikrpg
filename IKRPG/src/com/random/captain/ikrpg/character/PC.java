package com.random.captain.ikrpg.character;

import com.random.captain.ikrpg.character.*;
import java.util.*;

import android.os.Parcel;
import android.os.Parcelable;

public class PC extends zzBaseCharacter
{
	public PC()
	{
		super(null, null, null, null, null, null, null, null,0);
	}
	
	//temporary; not really a copy constructor
	public PC(zzBaseCharacter base)
	{
		fluff=base.fluff;
		race=base.race;
		archetype=base.archetype;
		tradition=base.tradition;
		careers=base.careers;
		abilities=base.abilities;
		spells=base.spells;
		skillsBundle=base.skillsBundle;
		statsBundle=base.statsBundle;
		exp=base.exp;
	}
	
	public static final Parcelable.Creator<PC> CREATOR = new Parcelable.Creator<PC>()
	{
		@Override
		public PC createFromParcel(Parcel in)
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

			zzBaseCharacter me = new zzBaseCharacter(pFluff, pRace, pArchetype, pCareers, pAbilities, pSpells, pSkills, pStats,0);
			return new PC(me);
		}

		@Override public PC[] newArray(int size) {return new PC[size];}
	};
}
