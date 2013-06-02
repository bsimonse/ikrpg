package com.random.captain.ikrpg.character;

import android.support.v4.app.Fragment;

abstract class PostCreateHook extends Fragment
{
	protected BaseCharacter myChar;
	protected PostCreateHookDelegate delegate;
	
	void startPostCreateHook(BaseCharacter pChar, PostCreateHookDelegate pDelegate)
	{
		myChar = pChar;
		delegate = pDelegate;
	}
	
	abstract boolean hasUI();
	abstract void undoPostCreateHook();

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

interface PostCreateHookDelegate
{
	public void hookComplete();
}
