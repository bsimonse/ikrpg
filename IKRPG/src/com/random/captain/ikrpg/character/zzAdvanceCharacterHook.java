package com.random.captain.ikrpg.character;

import android.os.Bundle;
import android.util.Log;
import com.random.captain.ikrpg.util.BundleConstants;
import com.random.captain.ikrpg.util.FlowFragment;

abstract class zzAdvanceCharacterHook extends zzCharacterAdvancementFragment
{
	protected int curExp;
	
	zzAdvanceCharacterHook(int pExp)
	{curExp = pExp;}
	
	public zzAdvanceCharacterHook()
	{}

	@Override
	public void saveToBundle(Bundle b)
	{
		super.saveToBundle(b);
		b.putInt(BundleConstants.CUR_EXP,curExp);
	}

	@Override
	public void restoreFromBundle(Bundle b)
	{
		super.restoreFromBundle(b);
		curExp = getArguments().getInt(BundleConstants.CUR_EXP, 0);
	}
}
