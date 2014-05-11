package com.random.captain.ikrpg.character;

import android.os.Bundle;
import android.view.View;
import com.random.captain.ikrpg.util.BundleConstants;
import com.random.captain.ikrpg.util.FlowFragment;
import com.random.captain.ikrpg.util.FlowFragment.FlowFragmentDelegate;

public abstract class zzCharacterAdvancementFragment extends FlowFragment
{
	protected zzBaseCharacter myChar;
	protected int curExp;

	zzCharacterAdvancementFragment(int pExp)
	{super();curExp = pExp;}

	public zzCharacterAdvancementFragment()
	{this(0);}
	
	@Override
	public void saveToBundle(Bundle b)
	{
		if(myChar != null){b.putString(BundleConstants.CHARACTER, myChar.toJson());}
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
