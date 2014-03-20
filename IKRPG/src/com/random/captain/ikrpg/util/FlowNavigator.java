package com.random.captain.ikrpg.util;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.random.captain.ikrpg.R;

public abstract class FlowNavigator extends FragmentActivity {
	
	protected abstract ArrayList<FlowFragment> generateFrags();
	protected abstract void hookComplete(Bundle b);
	protected abstract Bundle prepBundle();
	protected abstract void setResult();
	
	@Override
	public void onSaveInstanceState(Bundle b)
	{
		super.onSaveInstanceState(b);
		b.putInt(BundleConstants.FLOW_INDEX, flowIndex);
		b.putSerializable(BundleConstants.FLOW_FRAGS, frags);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_flow_navigator);

		if(savedInstanceState != null)
		{
			flowIndex = savedInstanceState.getInt(BundleConstants.FLOW_INDEX, -1);
			frags = (ArrayList<FlowFragment>)savedInstanceState.getSerializable(BundleConstants.FLOW_FRAGS);
		}
	}

	@Override
	public void onResume()
	{
		super.onResume();
		
		if(frags == null)
		{
			frags = generateFrags();
			advanceFlow();
		}
		else
		{
			//get top frag
			FragmentManager manager = getSupportFragmentManager();
			int fragCount = manager.getBackStackEntryCount();
			if(fragCount > 0)
			{	
				FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(fragCount-1);
				final FlowFragment topFrag = (FlowFragment)manager.findFragmentByTag(entry.getName());
				startFrag(topFrag);
			}
			
			//else, huh.
			finish();
		}
	}
	
	@Override public void onBackPressed()
	{
		FragmentManager manager = getSupportFragmentManager();

		if(manager.getBackStackEntryCount() <= 1)
		{
			finish();
			return;
		}

		//undo previous thing
		final FlowFragment prevFrag = (FlowFragment)manager.findFragmentByTag(manager.getBackStackEntryAt(manager.getBackStackEntryCount()-2).getName());
		flowIndex--;
		startFrag(prevFrag);
		
		manager.popBackStackImmediate();
	}
	
	private ArrayList<FlowFragment> frags;
	private int flowIndex;
	
	private void advanceFlow()
	{	
		if(flowIndex >= frags.size())
		{
			flowComplete();
			return;
		}

		final FlowFragment nextFrag = frags.get(flowIndex);

		if(nextFrag != null)
		{
			
			nextFrag.prepFlowFragment(prepBundle());
			
			startFrag(nextFrag);

			String fragName = "flag_"+flowIndex;
			FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
			trans.replace(R.id.mainFragmentContainer, nextFrag, fragName);
			trans.addToBackStack(fragName);
			trans.commit();

			flowIndex++;
		}
	}

	private void startFrag(FlowFragment frag)
	{
		frag.startFlowFragment(new FlowFragment.FlowFragmentDelegate() {
			
			@Override
			public void hookComplete(Bundle b) {
				FlowNavigator.this.hookComplete(b);
				advanceFlow();
			}
		});
	}
	
	void flowComplete()
	{
		Log.i("IKRPG","YOU DID IT!");
		setResult();
		finish();
	}
}
