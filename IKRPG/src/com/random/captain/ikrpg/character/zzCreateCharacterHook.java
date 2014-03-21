
package com.random.captain.ikrpg.character;

import android.os.Bundle;
import android.view.View;

import com.random.captain.ikrpg.util.BundleConstants;
import com.random.captain.ikrpg.util.FlowFragment;

abstract class zzCreateCharacterHook extends FlowFragment
{
	protected zzBaseCharacter myChar;
	
	@Override
	public void onViewCreated(View v, Bundle b)
	{
		Bundle stuff = getArguments();
		
		if(myChar==null)
		{myChar = zzBaseCharacter.fromJson(stuff.getString(BundleConstants.CHARACTER));}
	}

	abstract int getPriority();
}
