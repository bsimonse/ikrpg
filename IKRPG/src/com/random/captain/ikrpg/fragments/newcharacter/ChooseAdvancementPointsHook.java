package com.random.captain.ikrpg.fragments.newcharacter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.model.Attributes.Stat;
import com.random.captain.ikrpg.model.Attributes.StatsBundle;
import com.random.captain.ikrpg.model.BaseCharacter;
import com.random.captain.ikrpg.model.Creators.PostCreateHook;
import com.random.captain.ikrpg.model.Creators.PostCreateHookDelegate;

public class ChooseAdvancementPointsHook implements PostCreateHook
{
	private StatsBundle oldStats;
	
	@Override public Fragment doPostCreateHook(final BaseCharacter myChar, final PostCreateHookDelegate delegate)
	{
		oldStats = myChar.statsBundle().clone();
		
		return new Fragment(){
			@Override
			public View onCreateView(LayoutInflater inflater, ViewGroup pRoot, Bundle bund)
			{
				ViewGroup root = (ViewGroup)inflater.inflate(R.layout.frag_choose_advancement_points, pRoot, false);
				
				final ListView list = (ListView)root.findViewById(R.id.chooseAdvancementPointsList);
				list.setAdapter(new ChooseAdvancementPointsAdapter(myChar, list));
				
				Button submitButton = (Button)root.findViewById(R.id.chooseAdvancementPointsSubmit);
				submitButton.setOnClickListener(new View.OnClickListener(){
					@Override public void onClick(View v)
					{
						ChooseAdvancementPointsAdapter adapter = (ChooseAdvancementPointsAdapter)list.getAdapter();
						adapter.lockInStats();
						delegate.hookComplete();
					}
				});
				return root;
			}
		};
	}
	
	@Override public void undoPostCreateHook(BaseCharacter myChar)
	{
		//revert stats
		//is this stupid?  Mabye.
		//blehhhhhhhh
		StatsBundle charStats = myChar.statsBundle();
		for(Stat stat : Stat.values())
		{
			int oldValue = oldStats.getBaseStat(stat);
			Log.i("IKRPG","Reverting(?) base stat for "+stat.shortName()+" back to "+oldValue);
			if(oldValue >= 0){charStats.setBaseStat(stat, oldValue);}
		}
	}
	
	@Override public int getPriority(){return 100;}
}
