package com.random.captain.ikrpg.util;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import com.random.captain.ikrpg.R;
import java.util.ArrayList;

public abstract class FlowNavigator extends FragmentActivity {
	
	protected abstract ArrayList<? extends FlowFragment> generateFrags();
	protected abstract void hookComplete(Bundle b);
	protected abstract Bundle prepBundle();
	protected abstract void setResult();
	
	@Override
	public void onSaveInstanceState(Bundle b)
	{
		super.onSaveInstanceState(b);
		b.putInt(BundleConstants.FLOW_INDEX, flowIndex);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_flow_navigator);

		if(savedInstanceState != null)
		{
			flowIndex = savedInstanceState.getInt(BundleConstants.FLOW_INDEX, -1);
		}
	}

	@Override
	public void onResume()
	{
		super.onResume();
		
		if(flowIndex < 1)
		{
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
			else
			{
				//else, huh.
				finish();
			}
		}
	}
	
	@Override public void onBackPressed()
	{
		frags = generateFrags();
		FragmentManager manager = getSupportFragmentManager();

		if(manager.getBackStackEntryCount() <= 1)
		{
			setResult(RESULT_CANCELED);
			finish();
			return;
		}

		flowIndex--;
		
		//what was last index with a UI?
		while(!frags.get(flowIndex-1).hasUI())
		{flowIndex--;}
		
		//pop to last UI fragment
		final FlowFragment prevFrag = (FlowFragment)manager.findFragmentByTag(manager.getBackStackEntryAt(manager.getBackStackEntryCount()-2).getName());
		startFrag(prevFrag);
		manager.popBackStackImmediate();
	}
	
	private ArrayList<? extends FlowFragment> frags;
	private int flowIndex;
	
	private void advanceFlow()
	{	
		//hmmm.
		frags = generateFrags();
		
		if(flowIndex >= frags.size())
		{
			flowComplete();
			return;
		}
		
		final FlowFragment nextFrag = frags.get(flowIndex);

		if(nextFrag != null)
		{
			flowIndex++;
			nextFrag.prepFlowFragment(prepBundle());
			
			//I don't like that I have to call attention to this pattern.
			boolean hasUI = startFrag(nextFrag);

			if(hasUI)
			{
				String fragName = "flag_"+flowIndex;
				FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.mainFragmentContainer, nextFrag, fragName);
				trans.addToBackStack(fragName);
				trans.commit();
			}
		}
	}

	private boolean startFrag(FlowFragment frag)
	{
		return frag.startFlowFragment(new FlowFragment.FlowFragmentDelegate() {
			
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
