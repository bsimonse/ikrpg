package com.random.captain.ikrpg.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class FlowFragment extends Fragment {

	public interface FlowFragmentDelegate
	{
		public void hookComplete(Bundle b);
	}
	
	protected FlowFragmentDelegate delegate;

	public void prepFlowFragment(Bundle b)
	{
		setArguments(b);
	}

	//The boolean determines whether or not you have a UI.
	//This could probably be done better.
	public boolean startFlowFragment(FlowFragmentDelegate pDelegate)
	{
		delegate = pDelegate;
		if(!hasUI())
		{
			pDelegate.hookComplete(doDefaultCase());
			return false;
		}
		else
		{return true;}
	}
	
	//Do nothing by default
	protected Bundle doDefaultCase(){return new Bundle();}
	
	protected boolean hasUI()
	{
		//assume false
		return false;
	}
}

