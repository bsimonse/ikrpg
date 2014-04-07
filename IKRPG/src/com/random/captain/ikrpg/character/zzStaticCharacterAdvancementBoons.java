package com.random.captain.ikrpg.character;

import android.widget.*;
import java.util.*;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.util.BundleConstants;
import android.util.SparseBooleanArray;

public class zzStaticCharacterAdvancementBoons
{
	public static class ChooseOccupationalSkillsFragment extends zzCharacterAdvancementFragment
	{
		List<SkillEnum> potentialSkills;
		Set<SkillEnum> chosenSkills;
		int choiceCount;
		
		public ChooseOccupationalSkillsFragment(int pChoiceCount)
		{
			choiceCount = pChoiceCount;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle bund)
		{
			return inflater.inflate(R.layout.frag_choice_list, root, false);
		}

		private void choicesComplete()
		{
			//For now, completely ignoring skill gains in qualified skills
			for(SkillEnum skill : chosenSkills)
			{myChar.setSkillLevel(skill.make(), myChar.getSkillBaseLevel(skill)+1);}
			
			Bundle b = new Bundle();
			b.putString(BundleConstants.CHARACTER, myChar.toJson());
			delegate.hookComplete(b);
		}
		
		@Override
		public void onViewCreated(View rootView, Bundle b)
		{
			super.onViewCreated(rootView,b);
			
			potentialSkills = new ArrayList<SkillEnum>(10);
			chosenSkills = new HashSet<SkillEnum>(10);
			
			//cheat!
			//But soon will actually get all eligible.
			potentialSkills.add(SkillEnum.ALCHEMY);
			potentialSkills.add(SkillEnum.HAND_WEAPON);
			potentialSkills.add(SkillEnum.PISTOL);
			potentialSkills.add(SkillEnum.RESEARCH);
			
			final ListView skillList = (ListView)rootView.findViewById(R.id.listChoiceList);
			skillList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			
			skillList.setAdapter(new ArrayAdapter<SkillEnum>(getActivity(), android.R.layout.simple_list_item_multiple_choice, potentialSkills){
				@Override
				public boolean isEnabled(int position)
				{
					return skillList.getCheckedItemCount() < choiceCount ||
					skillList.getCheckedItemPositions().get(position);
				}
			});
			
			skillList.setOnItemClickListener( new AdapterView.OnItemClickListener(){
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int which, long id)
					{
						SparseBooleanArray checked = skillList.getCheckedItemPositions();
						SkillEnum chosen = potentialSkills.get(which);
						boolean selected = checked.get(which);
						
						if(selected)
						{
							Log.i("IKRPG","Alright, selected "+chosen);
							chosenSkills.add(chosen);
						}
						else
						{
							Log.i("IKRPG","Whoops, I didn't mean THAT "+chosen+".");
							chosenSkills.remove(chosen);
						}
					}
				});

			TextView tv = (TextView)rootView.findViewById(R.id.listChoiceTitle);
			tv.setText("Choose your skills");
		}
		
		@Override protected boolean hasUI(){return true;}
	}
}
