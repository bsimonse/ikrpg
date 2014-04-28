package com.random.captain.ikrpg.character;

import java.util.*;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.character.SkillEnum;
import com.random.captain.ikrpg.util.BundleConstants;
import com.random.captain.ikrpg.util.ChoosePointsAdapter;

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
	
	public class ChooseOccupationalSkillsFragment extends zzAdvanceCharacterHook
	{
		final List<ChoosePointsAdapter.ChoosePointsBundle<SkillEnum>> potentialSkills = new ArrayList<ChoosePointsAdapter.ChoosePointsBundle<SkillEnum>>(20);
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
			
			chosenSkills = new HashSet<SkillEnum>(20);
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
						ChoosePointsAdapter.ChoosePointsBundle<SkillEnum> bundle = new ChoosePointsAdapter.ChoosePointsBundle<SkillEnum>(curLevel, curLevel, Math.min(skillCap.second.intValue(), skillCapForEXP(curExp)), skillCap.first.skillEnum());
						potentialSkills.add(bundle);
					}
				}
			}
			
			//now looking for general skills
			for(SkillEnum skill : SkillEnum.generalSkills())
			{
				int curLevel = myChar.getSkillBaseLevel(skill);
				if(curLevel < skillCapForEXP(curExp))
				{
					ChoosePointsAdapter.ChoosePointsBundle<SkillEnum> bundle = new ChoosePointsAdapter.ChoosePointsBundle<SkillEnum>(curLevel, curLevel, Math.min(4, skillCapForEXP(curExp)), skill);
					potentialSkills.add(bundle);
				}
			}
			
			//might as well
			//Collections.sort(potentialSkills);
			
			final ListView skillList = (ListView)rootView.findViewById(R.id.listChoiceList);

			skillList.setAdapter(new ChooseSkillPoint(myChar, potentialSkills, choiceCount));

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
	
	public ChooseOccupationalSkillsFragment please(int pCurExp, int pChoiceCount)
	{
		return new ChooseOccupationalSkillsFragment(pCurExp, pChoiceCount);
	}
	
	public static class ChooseSkillPoint extends ChoosePointsAdapter<SkillEnum>
	{
		List<ChoosePointsAdapter.ChoosePointsBundle<SkillEnum>> skillList;
		int choiceCount;

		public ChooseSkillPoint(zzBaseCharacter myChar, List<ChoosePointsAdapter.ChoosePointsBundle<SkillEnum>> skills, int cCount)
		{
			super(myChar);
			skillList = skills;
			Log.i("IKRPG","NOW it's been set.");
			choiceCount = cCount;
		}

		@Override
		protected int getIncreaseCount()
		{
			return choiceCount;
		}

		@Override
		protected String getLabel(ChoosePointsBundle<SkillEnum> bundle)
		{
			return bundle.item.name();
		}

		@Override
		protected List<ChoosePointsBundle<SkillEnum>> getItemList()
		{
			Log.i("IKRPG",skillList == null ? "Why is it null?" : "But it's not null...");
			return skillList;
		}
	}
	
}
