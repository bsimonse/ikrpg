package com.random.captain.ikrpg.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class FlowFragment extends Fragment {

	public interface FlowFragmentDelegate
	{
		public void hookComplete(Bundle b);
		public void pushExtraFrag(FlowFragment f, String fragId);
	}
	
	protected FlowFragmentDelegate delegate;
	protected boolean isPrimary = true;
	
	public abstract void setupWithBundle(Bundle b);
	public abstract void restoreFromBundle(Bundle b);
	
	public final void prepFlowFragment(Bundle b)
	{
		setArguments(b);
		setupWithBundle(getArguments());
		getArguments().putBoolean("isPrimary", isPrimary);
	}

	@Override
	public final void onCreate(Bundle b)
	{
		super.onCreate(b);
		restoreFromBundle(getArguments());
		isPrimary = getArguments().getBoolean("isPrimary");
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
	
	public boolean hasUI()
	{return false;}
	
	public void setIsPrimaryFrag(boolean isIt)
	{isPrimary = isIt;}
	
	public boolean isPrimaryFrag()
	{return isPrimary;}
}

