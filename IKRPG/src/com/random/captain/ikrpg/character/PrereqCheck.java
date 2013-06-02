package com.random.captain.ikrpg.character;

import android.support.v4.app.Fragment;

interface PrereqCheck
{
	PrereqCheckResult meetsPrereq(BaseCharacter myChar);
}
	
class PrereqCheckResult
{
	boolean isAllowed;
	Fragment additionalQuestion;
	
	PrereqCheckResult(boolean pIsAllowed, Fragment pAdditionalQuestion)
	{
		isAllowed = pIsAllowed;
		additionalQuestion = pAdditionalQuestion;
	}
}
