package com.random.captain.ikrpg.character;

import java.util.*;

import android.os.Bundle;
import android.util.Pair;
import com.random.captain.ikrpg.IKRPGApp;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.util.BundleConstants;

public enum Race
{
	@SuppressWarnings("unchecked")
	HUMAN(R.string.human_name, R.string.human_desc,
		new Pair[] { Pair.create(Stat.PHYSIQUE, 5), Pair.create(Stat.SPEED, 6), Pair.create(Stat.STRENGTH, 4),
			Pair.create(Stat.AGILITY, 3), Pair.create(Stat.PROWESS, 4), Pair.create(Stat.POISE, 4),
			Pair.create(Stat.INTELLECT, 3), Pair.create(Stat.ARCANE, 0), Pair.create(Stat.PERCEPTION, 3) },
		
		new Pair[] { Pair.create(Stat.PHYSIQUE, 7), Pair.create(Stat.SPEED, 7), Pair.create(Stat.STRENGTH, 6),
		  Pair.create(Stat.AGILITY, 5), Pair.create(Stat.PROWESS, 5), Pair.create(Stat.POISE, 5),
		  Pair.create(Stat.INTELLECT, 5), Pair.create(Stat.ARCANE, 4), Pair.create(Stat.PERCEPTION, 5) },
			  
		new Pair[] { Pair.create(Stat.PHYSIQUE, 8), Pair.create(Stat.SPEED, 7), Pair.create(Stat.STRENGTH, 7),
		  Pair.create(Stat.AGILITY, 6), Pair.create(Stat.PROWESS, 6), Pair.create(Stat.POISE, 6),
		  Pair.create(Stat.INTELLECT, 6), Pair.create(Stat.ARCANE, 6), Pair.create(Stat.PERCEPTION, 6) },
			  
		new Pair[] { Pair.create(Stat.PHYSIQUE, 8), Pair.create(Stat.SPEED, 7), Pair.create(Stat.STRENGTH, 8),
		  Pair.create(Stat.AGILITY, 7), Pair.create(Stat.PROWESS, 7), Pair.create(Stat.POISE, 7),
		  Pair.create(Stat.INTELLECT, 7), Pair.create(Stat.ARCANE, 8), Pair.create(Stat.PERCEPTION, 7) },
		  
		new Archetype[] {Archetype.GIFTED, Archetype.INTELLECTUAL, Archetype.MIGHTY, Archetype.SKILLED},
		
		new zzCharacterAdvancementFragment[] {new raceHook(), new humanHook()}
		),
	
	@SuppressWarnings("unchecked")
	DWARF(R.string.dwarf_name, R.string.dwarf_desc,
		  new Pair[] { Pair.create(Stat.PHYSIQUE, 6), Pair.create(Stat.SPEED, 4), Pair.create(Stat.STRENGTH, 5),
			  Pair.create(Stat.AGILITY, 3), Pair.create(Stat.PROWESS, 4), Pair.create(Stat.POISE, 3),
			  Pair.create(Stat.INTELLECT, 4), Pair.create(Stat.ARCANE, 0), Pair.create(Stat.PERCEPTION, 3) },

		  new Pair[] { Pair.create(Stat.PHYSIQUE, 7), Pair.create(Stat.SPEED, 5), Pair.create(Stat.STRENGTH, 6),
			  Pair.create(Stat.AGILITY, 5), Pair.create(Stat.PROWESS, 5), Pair.create(Stat.POISE, 4),
			  Pair.create(Stat.INTELLECT, 5), Pair.create(Stat.ARCANE, 4), Pair.create(Stat.PERCEPTION, 4) },

		  new Pair[] { Pair.create(Stat.PHYSIQUE, 7), Pair.create(Stat.SPEED, 6), Pair.create(Stat.STRENGTH, 7),
			  Pair.create(Stat.AGILITY, 6), Pair.create(Stat.PROWESS, 6), Pair.create(Stat.POISE, 5),
			  Pair.create(Stat.INTELLECT, 6), Pair.create(Stat.ARCANE, 6), Pair.create(Stat.PERCEPTION, 6) },

		  new Pair[] { Pair.create(Stat.PHYSIQUE, 8), Pair.create(Stat.SPEED, 6), Pair.create(Stat.STRENGTH, 8),
			  Pair.create(Stat.AGILITY, 7), Pair.create(Stat.PROWESS, 7), Pair.create(Stat.POISE, 6),
			  Pair.create(Stat.INTELLECT, 7), Pair.create(Stat.ARCANE, 7), Pair.create(Stat.PERCEPTION, 7) },

		  new Archetype[] {Archetype.GIFTED, Archetype.INTELLECTUAL, Archetype.MIGHTY, Archetype.SKILLED},
		  
		  new zzCharacterAdvancementFragment[] {new raceHook(), new dwarfHook()}
		  ),
	
	@SuppressWarnings("unchecked")
	GOBBER(R.string.gobber_name, R.string.gobber_desc,
		  new Pair[] { Pair.create(Stat.PHYSIQUE, 4), Pair.create(Stat.SPEED, 6), Pair.create(Stat.STRENGTH, 3),
			  Pair.create(Stat.AGILITY, 4), Pair.create(Stat.PROWESS, 4), Pair.create(Stat.POISE, 3),
			  Pair.create(Stat.INTELLECT, 3), Pair.create(Stat.ARCANE, 0), Pair.create(Stat.PERCEPTION, 3) },

		  new Pair[] { Pair.create(Stat.PHYSIQUE, 6), Pair.create(Stat.SPEED, 7), Pair.create(Stat.STRENGTH, 4),
			  Pair.create(Stat.AGILITY, 5), Pair.create(Stat.PROWESS, 5), Pair.create(Stat.POISE, 5),
			  Pair.create(Stat.INTELLECT, 4), Pair.create(Stat.ARCANE, 0), Pair.create(Stat.PERCEPTION, 4) },

		  new Pair[] { Pair.create(Stat.PHYSIQUE, 7), Pair.create(Stat.SPEED, 7), Pair.create(Stat.STRENGTH, 5),
			  Pair.create(Stat.AGILITY, 6), Pair.create(Stat.PROWESS, 6), Pair.create(Stat.POISE, 6),
			  Pair.create(Stat.INTELLECT, 5), Pair.create(Stat.ARCANE, 0), Pair.create(Stat.PERCEPTION, 4) },

		  new Pair[] { Pair.create(Stat.PHYSIQUE, 7), Pair.create(Stat.SPEED, 7), Pair.create(Stat.STRENGTH, 6),
			  Pair.create(Stat.AGILITY, 7), Pair.create(Stat.PROWESS, 7), Pair.create(Stat.POISE, 7),
			  Pair.create(Stat.INTELLECT, 6), Pair.create(Stat.ARCANE, 0), Pair.create(Stat.PERCEPTION, 5) },

		  new Archetype[] {Archetype.INTELLECTUAL, Archetype.MIGHTY, Archetype.SKILLED},
		   
		   new zzCharacterAdvancementFragment[] {new raceHook(), new gobberHook()}
		  ),
		  
	@SuppressWarnings("unchecked")
	IOSAN(R.string.iosan_name, R.string.iosan_desc,
		  new Pair[] { Pair.create(Stat.PHYSIQUE, 5), Pair.create(Stat.SPEED, 6), Pair.create(Stat.STRENGTH, 4),
			  Pair.create(Stat.AGILITY, 3), Pair.create(Stat.PROWESS, 4), Pair.create(Stat.POISE, 4),
			  Pair.create(Stat.INTELLECT, 4), Pair.create(Stat.ARCANE, 0), Pair.create(Stat.PERCEPTION, 3) },

		  new Pair[] { Pair.create(Stat.PHYSIQUE, 7), Pair.create(Stat.SPEED, 7), Pair.create(Stat.STRENGTH, 5),
			  Pair.create(Stat.AGILITY, 5), Pair.create(Stat.PROWESS, 5), Pair.create(Stat.POISE, 5),
			  Pair.create(Stat.INTELLECT, 6), Pair.create(Stat.ARCANE, 4), Pair.create(Stat.PERCEPTION, 5) },

		  new Pair[] { Pair.create(Stat.PHYSIQUE, 7), Pair.create(Stat.SPEED, 7), Pair.create(Stat.STRENGTH, 6),
			  Pair.create(Stat.AGILITY, 6), Pair.create(Stat.PROWESS, 6), Pair.create(Stat.POISE, 6),
			  Pair.create(Stat.INTELLECT, 6), Pair.create(Stat.ARCANE, 6), Pair.create(Stat.PERCEPTION, 6) },

		  new Pair[] { Pair.create(Stat.PHYSIQUE, 7), Pair.create(Stat.SPEED, 7), Pair.create(Stat.STRENGTH, 7),
			  Pair.create(Stat.AGILITY, 7), Pair.create(Stat.PROWESS, 7), Pair.create(Stat.POISE, 7),
			  Pair.create(Stat.INTELLECT, 7), Pair.create(Stat.ARCANE, 8), Pair.create(Stat.PERCEPTION, 7) },

		  new Archetype[] {Archetype.GIFTED, Archetype.INTELLECTUAL, Archetype.MIGHTY, Archetype.SKILLED},
		  
		  new zzCharacterAdvancementFragment[] {new raceHook(), new iosanHook()}
		  ),
	
	@SuppressWarnings("unchecked")
	NYSS(R.string.nyss_name, R.string.nyss_desc,
		  new Pair[] { Pair.create(Stat.PHYSIQUE, 5), Pair.create(Stat.SPEED, 6), Pair.create(Stat.STRENGTH, 4),
			  Pair.create(Stat.AGILITY, 4), Pair.create(Stat.PROWESS, 4), Pair.create(Stat.POISE, 4),
			  Pair.create(Stat.INTELLECT, 3), Pair.create(Stat.ARCANE, 0), Pair.create(Stat.PERCEPTION, 3) },

		  new Pair[] { Pair.create(Stat.PHYSIQUE, 7), Pair.create(Stat.SPEED, 7), Pair.create(Stat.STRENGTH, 6),
			  Pair.create(Stat.AGILITY, 5), Pair.create(Stat.PROWESS, 5), Pair.create(Stat.POISE, 5),
			  Pair.create(Stat.INTELLECT, 5), Pair.create(Stat.ARCANE, 4), Pair.create(Stat.PERCEPTION, 5) },

		  new Pair[] { Pair.create(Stat.PHYSIQUE, 7), Pair.create(Stat.SPEED, 7), Pair.create(Stat.STRENGTH, 7),
			  Pair.create(Stat.AGILITY, 6), Pair.create(Stat.PROWESS, 6), Pair.create(Stat.POISE, 6),
			  Pair.create(Stat.INTELLECT, 6), Pair.create(Stat.ARCANE, 6), Pair.create(Stat.PERCEPTION, 6) },

		  new Pair[] { Pair.create(Stat.PHYSIQUE, 8), Pair.create(Stat.SPEED, 7), Pair.create(Stat.STRENGTH, 8),
			  Pair.create(Stat.AGILITY, 7), Pair.create(Stat.PROWESS, 7), Pair.create(Stat.POISE, 7),
			  Pair.create(Stat.INTELLECT, 6), Pair.create(Stat.ARCANE, 7), Pair.create(Stat.PERCEPTION, 6) },

		 new Archetype[] {Archetype.GIFTED, Archetype.MIGHTY, Archetype.SKILLED},
		 
		 new zzCharacterAdvancementFragment[] {new raceHook(), new nyssHook()}
		  ),
	
	@SuppressWarnings("unchecked")
	OGRUN(R.string.ogrun_name, R.string.ogrun_desc,
		 new Pair[] { Pair.create(Stat.PHYSIQUE, 6), Pair.create(Stat.SPEED, 5), Pair.create(Stat.STRENGTH, 6),
			 Pair.create(Stat.AGILITY, 3), Pair.create(Stat.PROWESS, 4), Pair.create(Stat.POISE, 3),
			 Pair.create(Stat.INTELLECT, 3), Pair.create(Stat.ARCANE, 0), Pair.create(Stat.PERCEPTION, 2) },

		 new Pair[] { Pair.create(Stat.PHYSIQUE, 7), Pair.create(Stat.SPEED, 6), Pair.create(Stat.STRENGTH, 8),
			 Pair.create(Stat.AGILITY, 5), Pair.create(Stat.PROWESS, 5), Pair.create(Stat.POISE, 4),
			 Pair.create(Stat.INTELLECT, 5), Pair.create(Stat.ARCANE, 0), Pair.create(Stat.PERCEPTION, 4) },

		 new Pair[] { Pair.create(Stat.PHYSIQUE, 8), Pair.create(Stat.SPEED, 6), Pair.create(Stat.STRENGTH, 9),
			 Pair.create(Stat.AGILITY, 5), Pair.create(Stat.PROWESS, 6), Pair.create(Stat.POISE, 5),
			 Pair.create(Stat.INTELLECT, 5), Pair.create(Stat.ARCANE, 0), Pair.create(Stat.PERCEPTION, 5) },

		 new Pair[] { Pair.create(Stat.PHYSIQUE, 9), Pair.create(Stat.SPEED, 6), Pair.create(Stat.STRENGTH, 10),
			 Pair.create(Stat.AGILITY, 6), Pair.create(Stat.PROWESS, 7), Pair.create(Stat.POISE, 6),
			 Pair.create(Stat.INTELLECT, 6), Pair.create(Stat.ARCANE, 0), Pair.create(Stat.PERCEPTION, 6) },

		 new Archetype[] {Archetype.MIGHTY, Archetype.SKILLED},
		  
		  new zzCharacterAdvancementFragment[] {new raceHook(), new ogrunHook()}
		 ),
		 
	@SuppressWarnings("unchecked")
	TROLLKIN(R.string.trollkin_name, R.string.trollkin_desc,
		new Pair[] { Pair.create(Stat.PHYSIQUE, 5), Pair.create(Stat.SPEED, 5), Pair.create(Stat.STRENGTH, 5),
			Pair.create(Stat.AGILITY, 3), Pair.create(Stat.PROWESS, 4), Pair.create(Stat.POISE, 2),
			Pair.create(Stat.INTELLECT,3), Pair.create(Stat.ARCANE, 0), Pair.create(Stat.PERCEPTION, 3)},
		
		new Pair[] { Pair.create(Stat.PHYSIQUE, 8), Pair.create(Stat.SPEED, 6), Pair.create(Stat.STRENGTH, 7),
			Pair.create(Stat.AGILITY, 5), Pair.create(Stat.PROWESS, 5), Pair.create(Stat.POISE, 4),
			Pair.create(Stat.INTELLECT,4), Pair.create(Stat.ARCANE, 4), Pair.create(Stat.PERCEPTION, 4)},
		
		new Pair[] { Pair.create(Stat.PHYSIQUE, 9), Pair.create(Stat.SPEED, 6), Pair.create(Stat.STRENGTH, 8),
			Pair.create(Stat.AGILITY, 6), Pair.create(Stat.PROWESS, 6), Pair.create(Stat.POISE, 5),
			Pair.create(Stat.INTELLECT,5), Pair.create(Stat.ARCANE, 6), Pair.create(Stat.PERCEPTION, 5)},
		
		new Pair[] { Pair.create(Stat.PHYSIQUE, 10), Pair.create(Stat.SPEED, 6), Pair.create(Stat.STRENGTH, 9),
			Pair.create(Stat.AGILITY, 7), Pair.create(Stat.PROWESS, 7), Pair.create(Stat.POISE, 6),
			Pair.create(Stat.INTELLECT,6), Pair.create(Stat.ARCANE, 7), Pair.create(Stat.PERCEPTION, 6)},
		
		new Archetype[] {Archetype.GIFTED, Archetype.MIGHTY, Archetype.SKILLED},
			 
		new zzCharacterAdvancementFragment[] {new raceHook(), new trollkinHook()}
	);
	
	private Race(int pNameResourceID, int pDescResource, Pair<Stat, Integer>[] pStartStats,
							Pair<Stat, Integer>[] pHeroStats,
							Pair<Stat, Integer>[] pVetStats,
							Pair<Stat, Integer>[] pEpicStats,
							Archetype[] pArchetypes,
							zzCharacterAdvancementFragment[] pPostCreateHooks)
	{
		name = IKRPGApp.getContext().getString(pNameResourceID);
		desc = IKRPGApp.getContext().getString(pDescResource);
		startStats = Arrays.asList(pStartStats);
		heroStats = Arrays.asList(pHeroStats);
		vetStats = Arrays.asList(pVetStats);
		epicStats = Arrays.asList(pEpicStats);
		archetypes = Arrays.asList(pArchetypes);
		postCreateHooks = pPostCreateHooks != null ? Arrays.asList(pPostCreateHooks) : new ArrayList<zzCharacterAdvancementFragment>(10);
	}
	
	private String name;
	private String desc;
	private Collection<Pair<Stat, Integer>> startStats;
	private Collection<Pair<Stat, Integer>> heroStats;
	private Collection<Pair<Stat, Integer>> vetStats;
	private Collection<Pair<Stat, Integer>> epicStats;
	private Collection<Archetype> archetypes;
	private Collection<zzCharacterAdvancementFragment> postCreateHooks;
	
	@Override
	public String toString(){return displayName();}
	public String displayName(){return name;}
	public String description(){return desc;}
	
	public int getStartStat(Stat pStat)
	{
		for(Pair<Stat, Integer> stat : startStats)
		{if(stat.first == pStat){return stat.second;}}
		
		return 0; //?
	}
	
	public int getHeroStatCap(Stat pStat)
	{
		for(Pair<Stat, Integer> stat : heroStats)
		{if(stat.first == pStat){return stat.second;}}

		return 0; //?
	}
	
	public int getVeteranStatCap(Stat pStat)
	{
		for(Pair<Stat, Integer> stat : vetStats)
		{if(stat.first == pStat){return stat.second;}}

		return 0; //?
	}
	
	public int getEpicStatCap(Stat pStat)
	{
		for(Pair<Stat, Integer> stat : epicStats)
		{if(stat.first == pStat){return stat.second;}}

		return 0; //?
	}

	Collection<Pair<Stat, Integer>> startStats(){return Collections.unmodifiableCollection(startStats);}
	Collection<Pair<Stat, Integer>> heroStats(){return Collections.unmodifiableCollection(heroStats);}
	Collection<Pair<Stat, Integer>> vetStats(){return Collections.unmodifiableCollection(vetStats);}
	Collection<Pair<Stat, Integer>> epicStats(){return Collections.unmodifiableCollection(epicStats);}
	Collection<zzCharacterAdvancementFragment> postCreateHooks(){return postCreateHooks;}
	Collection<Archetype> archetypes(){return archetypes;}
	
	/* Hooks! */
	
	public static class raceHook extends zzCharacterAdvancementFragment
	{
		//stuff happens here!
		@Override protected Bundle doDefaultCase(){
			for(Pair<Stat,Integer> startStat : myChar.race.startStats())
			{myChar.baseStats.put(startStat.first, startStat.second);}
			for(Pair<Stat,Integer> maxStat : myChar.race.heroStats())
			{myChar.maxStats.put(maxStat.first, maxStat.second);}
			myChar.deriveStats();
			Bundle b = new Bundle();
			b.putString(BundleConstants.CHARACTER, myChar.toJson());
			return b;
		}
		
		@Override public int getPriority(){return 0;}
	}
	
	public static class humanHook extends zzCharacterAdvancementFragment
	{
		@Override public int getPriority(){return 49;}
	}
	
	public static class dwarfHook extends zzCharacterAdvancementFragment
	{
		@Override public int getPriority(){return 49;}
	}
	
	public static class gobberHook extends zzCharacterAdvancementFragment
	{
		@Override public int getPriority(){return 49;}
	}
	
	public static class iosanHook extends zzCharacterAdvancementFragment
	{
		@Override public int getPriority(){return 49;}
	}
	
	public static class nyssHook extends zzCharacterAdvancementFragment
	{
		@Override public int getPriority(){return 49;}
	}
	
	public static class ogrunHook extends zzCharacterAdvancementFragment
	{
		@Override public int getPriority(){return 49;}
	}
	
	public static class trollkinHook extends zzCharacterAdvancementFragment
	{
		@Override public int getPriority(){return 49;}
	}
}
