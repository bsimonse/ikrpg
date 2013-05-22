package com.random.captain.ikrpg.model.Creators;

import com.random.captain.ikrpg.model.BaseCharacter;

public interface PrereqCheck
{
	public PrereqCheckResult meetsPrereq(BaseCharacter myChar);
}
