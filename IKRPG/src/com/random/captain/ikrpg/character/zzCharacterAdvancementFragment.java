package com.random.captain.ikrpg.character;

import android.os.Bundle;
import com.random.captain.ikrpg.util.BundleConstants;
import com.random.captain.ikrpg.util.FlowFragment;

public abstract class zzCharacterAdvancementFragment extends FlowFragment
{
	protected zzBaseCharacter myChar;
	protected int curExp;
	
	@Override
	public void setupWithBundle(Bundle b)
	{
		myChar = zzBaseCharacter.fromJson(getArguments().getString(BundleConstants.CHARACTER,""));
		b.putInt(BundleConstants.CUR_EXP,curExp);
	}
	
	@Override
	public void restoreFromBundle(Bundle b)
	{
		myChar = zzBaseCharacter.fromJson(getArguments().getString(BundleConstants.CHARACTER,""));
		curExp = getArguments().getInt(BundleConstants.CUR_EXP, 0);
	}	
	
	@Override
	protected Bundle doDefaultCase()
	{
		Bundle b = new Bundle();
		b.putString(BundleConstants.CHARACTER, myChar.toJson());
		return b;
	}
}
