package com.random.captain.ikrpg.model.Creators;
import android.support.v4.app.Fragment;
import com.random.captain.ikrpg.model.BaseCharacter;

public interface PostCreateHook
{
	public Fragment doPostCreateHook(BaseCharacter myChar, PostCreateHookDelegate delegate, int which);
}	
