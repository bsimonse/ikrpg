package com.random.captain.ikrpg.character;

import android.support.v4.app.Fragment;

interface zzPrereqCheck
{
	zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar);
}
	
class zzPrereqCheckResult
{
	boolean isAllowed;
	Fragment additionalQuestion;
	
	zzPrereqCheckResult(boolean pIsAllowed)
	{this(pIsAllowed, null);}
	
	zzPrereqCheckResult(boolean pIsAllowed, Fragment pAdditionalQuestion)
	{
		isAllowed = pIsAllowed;
		additionalQuestion = pAdditionalQuestion;
	}
}
