package com.random.captain.ikrpg.model.Attributes;

import android.util.Pair;
import com.random.captain.ikrpg.model.Creators.PostCreateHook;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public enum Race
{
	HUMAN("Human",
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
		  
		  null
		);
	
	private Race(String pName, Pair<Stat, Integer>[] pStartStats,
							Pair<Stat, Integer>[] pHeroStats,
							Pair<Stat, Integer>[] pVetStats,
							Pair<Stat, Integer>[] pEpicStats,
							PostCreateHook pPostCreateHook)
	{
		name = pName;
		startStats = Arrays.asList(pStartStats);
		heroStats = Arrays.asList(pHeroStats);
		vetStats = Arrays.asList(pVetStats);
		epicStats = Arrays.asList(pEpicStats);
		postCreateHook = pPostCreateHook;
	}
	
	private String name;
	private Collection<Pair<Stat, Integer>> startStats;
	private Collection<Pair<Stat, Integer>> heroStats;
	private Collection<Pair<Stat, Integer>> vetStats;
	private Collection<Pair<Stat, Integer>> epicStats;
	private PostCreateHook postCreateHook;
	
	public String displayName(){return name;}
	public Collection<Pair<Stat, Integer>> startStats(){return Collections.unmodifiableCollection(startStats);}
	public Collection<Pair<Stat, Integer>> heroStats(){return Collections.unmodifiableCollection(heroStats);}
	public Collection<Pair<Stat, Integer>> vetStats(){return Collections.unmodifiableCollection(vetStats);}
	public Collection<Pair<Stat, Integer>> epicStats(){return Collections.unmodifiableCollection(epicStats);}
	public PostCreateHook postCreateHook(){return postCreateHook;}
}
