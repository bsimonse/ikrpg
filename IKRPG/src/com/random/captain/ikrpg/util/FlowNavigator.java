package com.random.captain.ikrpg.util;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.random.captain.ikrpg.R;

public abstract class FlowNavigator<F extends FlowFragment> extends FragmentActivity {
	
	protected abstract List<F> generateFrags();
	protected abstract void hookComplete(Bundle b);
	protected abstract Bundle prepBundle(FlowFragment flowFrag, int fragIndex);
	protected abstract void setResult();
	
	private List<F> frags;
	private int flowIndex;
	
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

		//if the top frag is a primary frag
		final FlowFragment currentFrag = (FlowFragment)manager.findFragmentByTag(manager.getBackStackEntryAt(manager.getBackStackEntryCount()-1).getName());
		if(currentFrag.isPrimaryFrag())
		{
			flowIndex--;
		
			//what was last primary index with a UI?
			while(!frags.get(flowIndex-1).hasUI())
			{flowIndex--;}
		}
		
		//pop to last UI fragment
		final FlowFragment prevFrag = (FlowFragment)manager.findFragmentByTag(manager.getBackStackEntryAt(manager.getBackStackEntryCount()-2).getName());
		startFrag(prevFrag);
		manager.popBackStackImmediate();
	}
	
	private void advanceFlow()
	{	
		//hmmm.
		frags = generateFrags();
		
		if(flowIndex >= frags.size())
		{
			flowComplete();
			return;
		}
		
		final F nextFrag = frags.get(flowIndex);

		if(nextFrag != null)
		{
			flowIndex++;
			nextFrag.prepFlowFragment(prepBundle(nextFrag, flowIndex));
			
			if(startFrag(nextFrag))
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
			
			@Override
			public void pushExtraFrag(FlowFragment frag, String fragId)
			{
				frag.prepFlowFragment(prepBundle(frag, flowIndex));
				if(startFrag(frag))
				{
					FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
					trans.replace(R.id.mainFragmentContainer, frag, fragId);
					trans.addToBackStack(fragId);
					trans.commit();
				}
			}
		});
	}
	
	void flowComplete()
	{
		setResult();
		finish();
	}
}
