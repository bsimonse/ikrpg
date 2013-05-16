package com.random.captain.ikrpg.model.Creators;
import com.random.captain.ikrpg.model.Attributes.*;

import android.content.Context;
import java.util.Set;

public interface PostCreateHook
{
	public void doPostCreateHook(Race pRace, Archetype archetype, Set<Career> pCareers, Set<Ability> pAbilities,
									SkillsBundle pSkills, StatsBundle pStats, Context appContext);
}	
