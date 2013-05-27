package com.random.captain.ikrpg.model.Creators;
import android.support.v4.app.Fragment;
import com.random.captain.ikrpg.model.BaseCharacter;

public interface PostCreateHook
{
	//Apologies in advanced for the non-intuitive API...
	//Something probably can and will be done to clean it up.
	//
	//In the meantime, your post create hook can modify any setting in myChar, and when done,
	//call delegate.hookComplete(callbackId);.  Since that's pretty much constant, I'm sure it can be cleaned.
	public Fragment doPostCreateHook(BaseCharacter myChar, PostCreateHookDelegate delegate);
	public void undoPostCreateHook(BaseCharacter myChar);
	
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
