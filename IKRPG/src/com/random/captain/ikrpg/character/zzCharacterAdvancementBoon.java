package com.random.captain.ikrpg.character;

import android.os.Bundle;

import com.random.captain.ikrpg.util.BundleConstants;
import com.random.captain.ikrpg.util.FlowFragment;

public abstract class zzCharacterAdvancementBoon extends FlowFragment
{
	protected zzBaseCharacter myChar;

	@Override
	public void prepFlowFragment(Bundle b)
	{
		super.prepFlowFragment(b);
		getArguments().putString(BundleConstants.CHARACTER,b.getString(BundleConstants.CHARACTER));
	}

	@Override
	public void startFlowFragment(FlowFragmentDelegate pDelegate)
	{
		super.startFlowFragment(pDelegate);
		myChar = zzBaseCharacter.fromJson(getArguments().getString(BundleConstants.CHARACTER));
	}
}
