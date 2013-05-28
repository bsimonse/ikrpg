package com.random.captain.ikrpg.fragments.newcharacter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.model.Attributes.Stat;
import com.random.captain.ikrpg.model.BaseCharacter;
import com.random.captain.ikrpg.model.Creators.PostCreateHook;
import com.random.captain.ikrpg.model.Creators.PostCreateHookDelegate;
import java.util.List;

public class ChooseAdvancementPointsHook implements PostCreateHook
{
	private List<Pair<Stat, Integer>> statBoosts;
	
	@Override public Fragment doPostCreateHook(final BaseCharacter myChar, final PostCreateHookDelegate delegate)
	{
		return new Fragment(){
			@Override
			public View onCreateView(LayoutInflater inflater, ViewGroup pRoot, Bundle bund)
			{
				ViewGroup root = (ViewGroup)inflater.inflate(R.layout.frag_choose_advancement_points, pRoot, false);
				
				ListView list = (ListView)root.findViewById(R.id.chooseAdvancementPointsList);
				list.setAdapter(new ChooseAdvancementPointsAdapter(myChar));
				
				Button submitButton = (Button)root.findViewById(R.id.chooseAdvancementPointsSubmit);
				submitButton.setOnClickListener(new View.OnClickListener(){
					@Override public void onClick(View v)
					{
						delegate.hookComplete();
					}
				});
				return root;
			}
		};
	}
	
	@Override public void undoPostCreateHook(BaseCharacter myChar)
	{
		//decrement boosted stats
		for(Pair<Stat, Integer> boost : statBoosts)
		{
			int currentVal = myChar.getBaseStat(boost.first);
			currentVal -= boost.second;
			myChar.statsBundle().setBaseStat(boost.first, currentVal);
		}
	}
	
	@Override public int getPriority(){return 100;}
}
