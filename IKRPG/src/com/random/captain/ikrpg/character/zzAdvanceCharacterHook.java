package com.random.captain.ikrpg.character;

import android.os.Bundle;
import com.random.captain.ikrpg.util.BundleConstants;
import com.random.captain.ikrpg.util.FlowFragment;

abstract class zzAdvanceCharacterHook extends zzCharacterAdvancementFragment
{
	protected int curExp;
	
	zzAdvanceCharacterHook(int pExp)
	{curExp = pExp;}
	
	public zzAdvanceCharacterHook(){}
	
	@Override
	public void prepFlowFragment(Bundle b)
	{
		super.prepFlowFragment(b);
		getArguments().putInt(BundleConstants.CUR_EXP,curExp);
	}

	//Sooo...
	//I'm not thrilled with how I have to load the character twice...
	//but I couldn't find where in the lifecycle to do what I want.
	//So I have to load the character when it starts (for UI-less fragments)
	//and also when the view is created (for UI-full fragments).
	@Override
	public boolean startFlowFragment(FlowFragment.FlowFragmentDelegate pDelegate)
	{
		myChar = zzBaseCharacter.fromJson(getArguments().getString(BundleConstants.CHARACTER));
		curExp = getArguments().getInt(BundleConstants.CUR_EXP, 0);
		return super.startFlowFragment(pDelegate);
	}
}
