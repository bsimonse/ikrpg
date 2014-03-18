package com.random.captain.ikrpg.character;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public abstract class zzCharacterAdvancementBoon extends Fragment
{
	//Extreme Overkill because it's funny
	enum BOON {
		OC_SKILL,
		SPELL,
		ABILITY,
		CONNECTION,
		MIL_SKILL,
		STAT,
		ARCHETYPE,
		CAREER
	}
	
	private static final String CHARACTER = "thisStringHasNothingToDoWithItsName";
	
	//Okay, gotta think carefully here.
	
	//For carrying around Boons, obviously
	static public class BoonBucket{
		BOON boon;
		int count;
		
		public BoonBucket(BOON pBoon)
		{this(pBoon, -1);}
		
		public BoonBucket(BOON pBoon, int pCount)
		{
			boon = pBoon;
			count = pCount;
		}
	};

	protected zzBaseCharacter myChar;
	protected zzCharacterAdvancementBoonDelegate delegate;

	public void startBoon(zzBaseCharacter pChar, zzCharacterAdvancementBoonDelegate pDelegate)
	{
		myChar = pChar;
		delegate = pDelegate;

		Bundle args = getArguments();
		args.putParcelable(CHARACTER,myChar);
	}

	void restartBoon(zzCharacterAdvancementBoonDelegate pDelegate)
	{
		Bundle stuff = getArguments();
		myChar = stuff.getParcelable(CHARACTER);
		delegate = pDelegate;
	}

	@Override
	public void onViewCreated(View v, Bundle b)
	{
		Bundle stuff = getArguments();

		if(myChar==null)
		{myChar = stuff.getParcelable(CHARACTER);}
	}

	abstract void undoHook();
}	

interface zzCharacterAdvancementBoonDelegate
{
	public void hookComplete(zzBaseCharacter finishedChar);
}
