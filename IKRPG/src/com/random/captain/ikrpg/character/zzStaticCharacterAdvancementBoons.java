package com.random.captain.ikrpg.character;

import android.widget.*;
import java.util.*;

import android.os.Bundle;
import android.util.Pair;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.util.BundleConstants;

public class zzStaticCharacterAdvancementBoons
{
	//just a nice little helper
	public static int skillCapForEXP(int curExp)
	{
		if(curExp < 50)
		{return 2;}
		else if(curExp < 100)
		{return 3;}
		else
		{return 4;}	
	}
	
	public static class ChooseOccupationalSkillsFragment extends zzAdvanceCharacterHook
	{
		List<SkillEnum> potentialSkills;
		Set<SkillEnum> chosenSkills;
		
		int choiceCount;
		
		public ChooseOccupationalSkillsFragment(int pCurExp, int pChoiceCount)
		{
			super(pCurExp);
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
			
			final Button butt = (Button)rootView.findViewById(R.id.continueButton);
			
			//determine eligible skills
			//an eligible skill is
			// - available in one+ of the character's careers
			// - a non-military skill
			// - not already at career cap
			// - not already at level cap

			for(Career career : myChar.careers)
			{
				for(Pair<Skill,Integer> skillCap : career.careerSkills())
				{
					int curLevel = myChar.getSkillBaseLevel(skillCap.first);
					if(!skillCap.first.isMilitary() && curLevel < skillCap.second && curLevel < skillCapForEXP(curExp))
					{
						potentialSkills.add(skillCap.first.skillEnum());
					}
				}
			}
			
			//now looking for general skills
			for(SkillEnum skill : SkillEnum.generalSkills())
			{
				int curLevel = myChar.getSkillBaseLevel(skill);
				if(curLevel < skillCapForEXP(curExp)){potentialSkills.add(skill);}
			}
			
			//might as well
			Collections.sort(potentialSkills);
			
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
						{chosenSkills.add(chosen);}
						else
						{chosenSkills.remove(chosen);}
						
						if(skillList.getCheckedItemCount() >= choiceCount)
						{butt.setVisibility(View.VISIBLE);}
						else
						{butt.setVisibility(View.GONE);}
					}
				});

			TextView tv = (TextView)rootView.findViewById(R.id.listChoiceTitle);
			tv.setText("Choose your skills");
			
			butt.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)
				{
					choicesComplete();
				}
			});
		}
		
		@Override protected boolean hasUI(){return true;}
	}
}
