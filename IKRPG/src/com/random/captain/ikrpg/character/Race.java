package com.random.captain.ikrpg.character;

import java.util.*;

import android.util.Pair;

import com.random.captain.ikrpg.IKRPGApp;
import com.random.captain.ikrpg.R;

public enum Race
{
	@SuppressWarnings("unchecked")
	HUMAN(R.string.human_name,
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
		
		new zzCreateCharacterHook[] {new humanHook()}
		),
	
	@SuppressWarnings("unchecked")
	DWARF(R.string.dwarf_name,
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
		  
		  new zzCreateCharacterHook[] {new dwarfHook()}
		  ),
	
	@SuppressWarnings("unchecked")
	GOBBER(R.string.gobber_name,
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
		   
		  new zzCreateCharacterHook[] {new gobberHook()}
		  ),
		  
	@SuppressWarnings("unchecked")
	IOSAN(R.string.iosan_name,
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
		  
		  new zzCreateCharacterHook[] {new iosanHook()}
		  ),
	
	@SuppressWarnings("unchecked")
	NYSS(R.string.nyss_name,
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
		 
		  new zzCreateCharacterHook[] {new nyssHook()}
		  ),
	
	@SuppressWarnings("unchecked")
	OGRUN(R.string.ogrun_name,
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
		  
		 new zzCreateCharacterHook[] {new ogrunHook()}
		 ),
		 
	@SuppressWarnings("unchecked")
	TROLLKIN(R.string.trollkin_name,
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
			 
		new zzCreateCharacterHook[] {new trollkinHook()}
	);
	
	private Race(int pNameResourceID, Pair<Stat, Integer>[] pStartStats,
							Pair<Stat, Integer>[] pHeroStats,
							Pair<Stat, Integer>[] pVetStats,
							Pair<Stat, Integer>[] pEpicStats,
							Archetype[] pArchetypes,
							zzCreateCharacterHook[] pPostCreateHooks)
	{
		name = IKRPGApp.getContext().getString(pNameResourceID);
		startStats = Arrays.asList(pStartStats);
		heroStats = Arrays.asList(pHeroStats);
		vetStats = Arrays.asList(pVetStats);
		epicStats = Arrays.asList(pEpicStats);
		archetypes = Arrays.asList(pArchetypes);
		postCreateHooks = pPostCreateHooks != null ? Arrays.asList(pPostCreateHooks) : new ArrayList<zzCreateCharacterHook>(10);
	}
	
	private String name;
	private Collection<Pair<Stat, Integer>> startStats;
	private Collection<Pair<Stat, Integer>> heroStats;
	private Collection<Pair<Stat, Integer>> vetStats;
	private Collection<Pair<Stat, Integer>> epicStats;
	private Collection<Archetype> archetypes;
	private Collection<zzCreateCharacterHook> postCreateHooks;
	
	@Override
	public String toString(){return displayName();}
	public String displayName(){return name;}
	
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
	Collection<zzCreateCharacterHook> postCreateHooks(){return postCreateHooks;}
	Collection<Archetype> archetypes(){return archetypes;}
	
	/* Hooks! */
	
	public static class humanHook extends zzCreateCharacterHook
	{
		//stuff happens here!
		@Override public int getPriority(){return 49;}
	}
	
	public static class dwarfHook extends zzCreateCharacterHook
	{
		@Override public int getPriority(){return 49;}
	}
	
	public static class gobberHook extends zzCreateCharacterHook
	{
		@Override public int getPriority(){return 49;}
	}
	
	public static class iosanHook extends zzCreateCharacterHook
	{
		@Override public int getPriority(){return 49;}
	}
	
	public static class nyssHook extends zzCreateCharacterHook
	{
		@Override public int getPriority(){return 49;}
	}
	
	public static class ogrunHook extends zzCreateCharacterHook
	{
		@Override public int getPriority(){return 49;}
	}
	
	public static class trollkinHook extends zzCreateCharacterHook
	{
		@Override public int getPriority(){return 49;}
	}
}
