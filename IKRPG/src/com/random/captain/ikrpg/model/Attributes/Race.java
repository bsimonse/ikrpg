package com.random.captain.ikrpg.model.Attributes;

import android.util.Pair;
import com.random.captain.ikrpg.model.Attributes.Skill;

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
		  Pair.create(Stat.INTELLECT, 7), Pair.create(Stat.ARCANE, 8), Pair.create(Stat.PERCEPTION, 7) }
		);
	
	private Race(String pName, Pair<Stat, Integer>[] pStartStats,
							Pair<Stat, Integer>[] pHeroStats,
							Pair<Stat, Integer>[] pVetStats,
							Pair<Stat, Integer>[] pEpicStats)
	{
		name = pName;
		startStats = pStartStats;
		heroStats = pHeroStats;
		vetStats = pVetStats;
		epicStats = pEpicStats;
	}
	
	private String name;
	private Pair<Stat, Integer>[] startStats;
	private Pair<Stat, Integer>[] heroStats;
	private Pair<Stat, Integer>[] vetStats;
	private Pair<Stat, Integer>[] epicStats;
}
