package com.random.captain.ikrpg.character;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.util.BundleConstants;

public class zzStaticCharacterAdvancementBoons
{
	public static class ChooseOccupationalSkillsFragment extends zzCharacterAdvancementFragment
	{
		ArrayList<SkillEnum> potentialSkills;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle bund)
		{
			return inflater.inflate(R.layout.frag_choice_list, root, false);
		}

		@Override
		public void onViewCreated(View rootView, Bundle b)
		{
			super.onViewCreated(rootView,b);
			
			potentialSkills = new ArrayList<SkillEnum>(5);
			
			//cheat!
			potentialSkills.add(SkillEnum.ALCHEMY);
			potentialSkills.add(SkillEnum.HAND_WEAPON);
			
			ListView skillList = (ListView)rootView.findViewById(R.id.listChoiceList);
			skillList.setAdapter(new ArrayAdapter<SkillEnum>(getActivity(), android.R.layout.simple_list_item_1, potentialSkills));
			skillList.setOnItemClickListener( new AdapterView.OnItemClickListener(){
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int which, long id)
					{
						SkillEnum chosen = potentialSkills.get(which);
						Log.i("IKRPG","Alright, selected "+chosen);
						//increment
						//In future, don't modify myChar to help back button work right
						myChar.setSkillLevel(chosen.make(), myChar.getSkillBaseLevel(chosen)+1);
						Bundle b = new Bundle();
						b.putString(BundleConstants.CHARACTER, myChar.toJson());
						delegate.hookComplete(b);
					}
				});

			TextView tv = (TextView)rootView.findViewById(R.id.listChoiceTitle);
			tv.setText("Choose your skills");
		}
		
		@Override protected boolean hasUI(){return true;}
	}
}
