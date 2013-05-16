package com.random.captain.ikrpg.model.Creators;

import com.random.captain.ikrpg.model.Attributes.*;

import android.content.Context;
import java.util.Set;

public interface PrereqCheck
{
	public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities,
								SkillsBundle pSkills, StatsBundle pStats, Context appContext);
}
