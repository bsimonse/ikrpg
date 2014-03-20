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

	public void startFlowFragment(FlowFragmentDelegate pDelegate)
	{
		delegate = pDelegate;
	}
}

