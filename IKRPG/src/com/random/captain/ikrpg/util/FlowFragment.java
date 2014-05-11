package com.random.captain.ikrpg.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class FlowFragment extends Fragment {

	public interface FlowFragmentDelegate
	{
		public void hookComplete(Bundle b);
	}
	
	protected FlowFragmentDelegate delegate;

	abstract void setupWithBundle(Bundle b);
	abstract void restoreFromBundle(Bundle b);
	
	public final void prepFlowFragment(Bundle b)
	{
		setArguments(b);
		setupWithBundle(getArguments());
	}

	@Override
	public final void onCreate(Bundle b)
	{
		super.onCreate(b);
		restoreFromBundle(getArguments());
	}
	
	//The boolean determines whether or not you have a UI.
	//This could probably be done better.
	public final boolean startFlowFragment(FlowFragmentDelegate pDelegate)
	{
		delegate = pDelegate;
		restoreFromBundle(getArguments());
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
	
	//Default priority is 100
	public int getPriority(){return 100;}
	
	protected boolean hasUI()
	{
		//assume false
		return false;
	}
}

