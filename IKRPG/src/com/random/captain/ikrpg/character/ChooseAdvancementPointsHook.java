package com.random.captain.ikrpg.character;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import com.random.captain.ikrpg.R;

class ChooseAdvancementPointsHook extends PostCreateHook
{
	private StatsBundle oldStats;
	
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
	
	@Override boolean hasUI(){return true;}
	
	@Override void startPostCreateHook(BaseCharacter pChar, PostCreateHookDelegate pDelegate)
	{
		myChar = pChar;
		delegate = pDelegate;
		
		oldStats = myChar.statsBundle.clone();
	}
	
	@Override public void undoPostCreateHook()
	{
		StatsBundle charStats = myChar.statsBundle;
		for(Stat stat : Stat.values())
		{
			int oldValue = oldStats.getBaseStat(stat);
			if(oldValue >= 0){charStats.setBaseStat(stat, oldValue);}
		}
	}
	
	@Override public int getPriority(){return 100;}
}
