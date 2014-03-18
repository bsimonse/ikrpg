package com.random.captain.ikrpg.character;

import android.widget.*;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.random.captain.ikrpg.R;
import java.util.ArrayList;

public class zzStaticCharacterAdvancementBoons
{
	public static class ChooseOccupationalSkillsFragment extends zzCharacterAdvancementBoon
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
						skillSelected(potentialSkills.get(which));
					}
				});

			TextView tv = (TextView)rootView.findViewById(R.id.listChoiceTitle);
			tv.setText("Choose your skills");
		}

		private void skillSelected(SkillEnum skill)
		{
			//myChar.race = race;
			Log.i("IKRPG","Alright, selected "+skill);
			delegate.hookComplete(myChar);
		}

		@Override public boolean hasUI(){return true;}
		@Override public void doDefaultCase(){}	//nothing since always has UI
		@Override public void undoHook(){myChar.race = null;}
		@Override public int getPriority(){return -1;}
	}
}
