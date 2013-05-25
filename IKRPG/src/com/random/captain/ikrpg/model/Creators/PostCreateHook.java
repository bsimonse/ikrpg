package com.random.captain.ikrpg.model.Creators;
import android.support.v4.app.Fragment;
import com.random.captain.ikrpg.model.BaseCharacter;

public interface PostCreateHook
{
	public Fragment doPostCreateHook(BaseCharacter myChar, PostCreateHookDelegate delegate, int which);
	
	//Priority Guidlines
	//(this is is no way official)
	//
	//0: Cannot possible depend on anything other choices (skills granted for free from Race)
	//10: Unlikely to have a dependency, but it likely to be depended on (some career bonuses)
	//50: Has some dependencies and dependents (skill levels chosen for careers)
	//100: Has to go last for some reason
	//
	//Because I'm a moron, if you find a conflict, just bump one of the numbers up.
	public int getPriority();
}	
