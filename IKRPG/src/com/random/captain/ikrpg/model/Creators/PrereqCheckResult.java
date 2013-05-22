package com.random.captain.ikrpg.model.Creators;

import android.support.v4.app.Fragment;

public class PrereqCheckResult
{
	public boolean isAllowed;
	public Fragment additionalQuestion;
	
	public PrereqCheckResult(boolean pIsAllowed, Fragment pAdditionalQuestion)
	{
		isAllowed = pIsAllowed;
		additionalQuestion = pAdditionalQuestion;
	}
}
