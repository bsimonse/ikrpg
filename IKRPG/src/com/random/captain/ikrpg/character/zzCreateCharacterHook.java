package com.random.captain.ikrpg.character;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

abstract class zzCreateCharacterHook extends Fragment
{
	private static final String CHARACTER = "thisStringHasNothingToDoWithItsName";
	protected zzBaseCharacter myChar;
	protected zzCreateCharacterHookDelegate delegate;
	
	void startHook(zzBaseCharacter pChar, zzCreateCharacterHookDelegate pDelegate)
	{
		Bundle args = getArguments();
		args.putParcelable(CHARACTER,pChar);
		
		myChar = pChar;
		delegate = pDelegate;
	}
	
	@Override
	public void onViewCreated(View v, Bundle b)
	{
		Bundle stuff = getArguments();
		myChar = stuff.getParcelable(CHARACTER);
	}
	
	abstract boolean hasUI();
	abstract void undoHook();

	//Priority Guidlines
	//(this is is no way official)
	//
	//-1: always included in source code, isn't dynamic
	//0: Cannot possible depend on anything other choices (skills granted for free from Race)
	//10: Unlikely to have a dependency, but it likely to be depended on (some career bonuses)
	//50: Has some dependencies and dependents (skill levels chosen for careers)
	//100: Has to go last for some reason
	//
	//Because I'm a moron, if you find a conflict, just bump one of the numbers up.
	abstract int getPriority();
}	

interface zzCreateCharacterHookDelegate
{
	public void hookComplete();
}
