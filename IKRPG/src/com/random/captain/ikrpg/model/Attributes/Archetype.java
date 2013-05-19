package com.random.captain.ikrpg.model.Attributes;

import android.content.Context;
import com.random.captain.ikrpg.model.Attributes.Race;
import com.random.captain.ikrpg.model.Creators.PostCreateHook;
import com.random.captain.ikrpg.model.Creators.PrereqCheck;
import java.util.Set;

public enum Archetype implements PrereqCheck
{
	//needs waaaaay more work
	GIFTED_WILL_WEAVER("Gifted (Will Weaver)",
	new PostCreateHook(){
			public void doPostCreateHook(Race pRace, Archetype archetype, Set<Career> pCareers, Set<Ability> pAbilities,
										 SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{
				pStats.setBaseStat(Stat.ARCANE, 3);
			}
	},
	
	new PrereqCheck(){
		@Override
		public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities,
							   SkillsBundle pSkills, StatsBundle pStats, Context appContext)
		{
			return race == Race.HUMAN; 
		}
	}),
	
	GIFTED_FOCUSER("Gifted (Focuser)",
	new PostCreateHook(){
			public void doPostCreateHook(Race pRace, Archetype archetype, Set<Career> pCareers, Set<Ability> pAbilities,
										 SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{
				pStats.setBaseStat(Stat.ARCANE, 2);
			}
	},
	
	new PrereqCheck(){
			@Override
			public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities,
									   SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{
				return race == Race.HUMAN; 
			}
		}),
	
	INTELLECTUAL("Intellectual", null,
	
	new PrereqCheck(){
			@Override
			public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities,
									   SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{
				return race == Race.HUMAN; 
			}
		}),
	
	MIGHTY("Mighty", null,
	new PrereqCheck(){
			@Override
			public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities,
									   SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{
				return race == Race.HUMAN; 
			}
		}),
	
	SKILLED("Skilled", null,
	new PrereqCheck(){
			@Override
			public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities,
									   SkillsBundle pSkills, StatsBundle pStats, Context appContext)
			{
				return race == Race.HUMAN; 
			}
		});
	
	private Archetype(String pName, PostCreateHook pHook, PrereqCheck pPrereq)
	{
		name = pName;
		postCreateHook = pHook;
		prereq = pPrereq;
	}
	
	@Override
	public String toString()
	{
		return displayName();
	}
	
	private String name;
	private PostCreateHook postCreateHook;
	private PrereqCheck prereq;
	
	public String displayName(){return name;}
	public PostCreateHook postCreateHook(){return postCreateHook;}
	public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities,
							   SkillsBundle pSkills, StatsBundle pStats, Context appContext)
	{
		if(prereq == null){return true;}
		return prereq.meetsPrereq(race, archetype, careers, pAbilities, pSkills, pStats, appContext);
	}
}
