
package com.random.captain.ikrpg.character;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

abstract class zzCreateCharacterHook extends Fragment
{
	static enum CreateHook
	{
		START,RACE,ARCHETYPE,CAREER1,CAREER2,POSTCREATE_HOOK,FLUFF,DONE;
	}
	
	private static final String CHARACTER = "thisStringHasNothingToDoWithItsName";
	private static final String HOOK = "neitherDoesThisOne";
	
	protected zzBaseCharacter myChar;
	protected zzCreateCharacterHookDelegate delegate;
	protected CreateHook hook;
	
	public void startHook(zzBaseCharacter pChar, zzCreateCharacterHookDelegate pDelegate, CreateHook pHook)
	{
		myChar = pChar;
		hook = pHook;
		delegate = pDelegate;

		Bundle args = getArguments();
		args.putParcelable(CHARACTER,myChar);
		args.putSerializable(HOOK,hook);
		
		if(!hasUI())
		{doDefaultCase();}
	}
	
	//doesn't do anything; just a clearer name
	//only done for hooks with a UI
	void restartHook(zzCreateCharacterHookDelegate pDelegate)
	{
		Bundle stuff = getArguments();
		myChar = stuff.getParcelable(CHARACTER);
		hook = (CreateHook)stuff.getSerializable(HOOK);
		delegate = pDelegate;
	}
	
	@Override
	public void onViewCreated(View v, Bundle b)
	{
		Bundle stuff = getArguments();
		
		if(myChar==null)
		{myChar = stuff.getParcelable(CHARACTER);}
		
		if(hook==null)
		{hook = (CreateHook)stuff.getSerializable(HOOK);}
	}
	
	abstract boolean hasUI();
	abstract void doDefaultCase();
	abstract void undoHook();
	
	public final CreateHook getHook(){return hook;}
	
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
	public void hookComplete(zzBaseCharacter finishedChar);
}
