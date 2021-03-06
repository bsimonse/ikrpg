package com.random.captain.ikrpg.character;

import android.widget.*;
import java.util.*;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gag.annotation.remark.Hack;
import com.google.gag.annotation.remark.PleaseDontShowUpOn;
import com.google.gag.enumeration.Website;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.util.BundleConstants;
import com.random.captain.ikrpg.util.ChoosePointsAdapter;

public class zzStaticCharacterAdvancementBoons
{
	public static enum BOON_CHOICE
	{
		SPELL("Spell"),
		ABILITY("Ability"),
		CONNECTION("Connection"),
		MILITARY_SKILL("Military skill"),
		ARCHTYPE_BENEFIT("Archetype benefit"),
		CAREER_AND_SKILLS("New career and two occupational skills");
		
		private String displayName;
		BOON_CHOICE(String pDisplayName)
		{displayName = pDisplayName;}

		@Override
		public String toString()
		{return displayName;}
	}
	
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
	
	@SuppressLint("ValidFragment")
	public static class ChooseOccupationalSkillsFragment extends zzCharacterAdvancementFragment
	{
		final List<ChoosePointsAdapter.ChoosePointsBundle<SkillEnum>> potentialSkills = new ArrayList<ChoosePointsAdapter.ChoosePointsBundle<SkillEnum>>(20);
		Set<SkillEnum> chosenSkills;
		
		int choiceCount;
		
		public static ChooseOccupationalSkillsFragment make(int pCurExp, int pChoiceCount)
		{
			ChooseOccupationalSkillsFragment frag = new ChooseOccupationalSkillsFragment();
			frag.choiceCount = pChoiceCount;
			frag.curExp = pCurExp;
			return frag;
		}

		@Override
		public void setupWithBundle(Bundle b)
		{
			super.setupWithBundle(b);
			b.putInt(BundleConstants.CHOICE_COUNT,choiceCount);
		}

		@Override
		public void restoreFromBundle(Bundle b)
		{
			super.restoreFromBundle(b);
			choiceCount = getArguments().getInt(BundleConstants.CHOICE_COUNT, 1);
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle bund)
		{return inflater.inflate(R.layout.frag_choice_list, root, false);}

		private void choicesComplete()
		{
			//For now, completely ignoring skill gains in qualified skills
			for(ChoosePointsAdapter.ChoosePointsBundle<SkillEnum> skill : potentialSkills)
			{
				myChar.setSkillLevel(skill.item.make(), skill.curVal);
			}
			
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

			potentialSkills.clear();
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
			Collections.sort(potentialSkills, new Comparator<ChoosePointsAdapter.ChoosePointsBundle<SkillEnum>>(){
				@Override
				public int compare(ChoosePointsAdapter.ChoosePointsBundle<SkillEnum> first, ChoosePointsAdapter.ChoosePointsBundle<SkillEnum> second)
				{
					return first.item.compareTo(second.item);
				}
			});
			
			final ListView skillList = (ListView)rootView.findViewById(R.id.listChoiceList);

			skillList.setAdapter(new ChoosePointsAdapter<SkillEnum>(myChar)
			{
				@Override public int getCount(){
					//check for continue button enabled
					if(getIncreaseCount() <= getIncreases())
					{butt.setVisibility(View.VISIBLE);}
					else
					{butt.setVisibility(View.GONE);}
					
					return super.getCount();
				}
				
				@Override
				protected int getIncreaseCount()
				{
					return choiceCount;
				}
	
				@Override
				protected String getLabel(ChoosePointsBundle<SkillEnum> bundle)
				{
					return bundle.item.displayName();
				}

				@Override
				protected List<ChoosePointsBundle<SkillEnum>> getItemList()
				{
					return potentialSkills;
				}	
			});

			TextView tv = (TextView)rootView.findViewById(R.id.listChoiceTitle);
			tv.setText("Choose "+choiceCount+" skills");
			
			butt.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v)
				{
					choicesComplete();
				}
			});
		}
		
		@Override public boolean hasUI(){return true;}
	}
	
	public static class ChooseStatPointsBoon extends zzCharacterAdvancementFragment
	{
		int choiceCount;
		
		public static ChooseStatPointsBoon make(int curExp, int pChoiceCount)
		{
			ChooseStatPointsBoon frag = new ChooseStatPointsBoon();
			frag.choiceCount = pChoiceCount;
			frag.curExp = curExp;
			return frag;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup pRoot, Bundle bund)
		{return inflater.inflate(R.layout.frag_choice_list, pRoot, false);}

		@Override
		public void setupWithBundle(Bundle b)
		{
			super.setupWithBundle(b);
			b.putInt(BundleConstants.CHOICE_COUNT,choiceCount);
		}

		@Override
		public void restoreFromBundle(Bundle b)
		{
			super.restoreFromBundle(b);
			choiceCount = getArguments().getInt(BundleConstants.CHOICE_COUNT, 1);
		}
		
		@Override
		public void onViewCreated(View root, Bundle b)
		{
			super.onViewCreated(root, b);
			final ListView list = (ListView)root.findViewById(R.id.listChoiceList);
			final Button butt = (Button)root.findViewById(R.id.continueButton);

			//remember all stats that can be increased
			final List<ChoosePointsAdapter.ChoosePointsBundle<Stat>> potentialStats = new ArrayList<ChoosePointsAdapter.ChoosePointsBundle<Stat>>(10);
			Map<Stat, Integer> eligibleStats = new HashMap<Stat, Integer>(10);
			for(Stat stat : Stat.values())
			{
				int curLevel = myChar.getBaseStat(stat);
				int maxLevel = myChar.getMaxStat(stat);
				if(curLevel < maxLevel)
				{
					eligibleStats.put(stat, myChar.getBaseStat(stat));
					ChoosePointsAdapter.ChoosePointsBundle<Stat> bundle = new ChoosePointsAdapter.ChoosePointsBundle<Stat>(curLevel, curLevel, maxLevel, stat);
					potentialStats.add(bundle);
				}
			}

			Collections.sort(potentialStats, new Comparator<ChoosePointsAdapter.ChoosePointsBundle<Stat>>(){
					@Override
					public int compare(ChoosePointsAdapter.ChoosePointsBundle<Stat> first, ChoosePointsAdapter.ChoosePointsBundle<Stat> second)
					{
						return first.item.ordinal() - second.item.ordinal();
					}
				});

			list.setAdapter(new ChoosePointsAdapter<Stat>(myChar)
				{
					@Override public int getCount(){
						//check for continue button enabled
						if(getIncreaseCount() <= getIncreases())
						{butt.setVisibility(View.VISIBLE);}
						else
						{butt.setVisibility(View.GONE);}

						return super.getCount();
					}

					@Override
					protected int getIncreaseCount()
					{return choiceCount;}

					@Override
					protected String getLabel(ChoosePointsBundle<Stat> bundle)
					{return bundle.item.longName();}

					@Override
					protected List<ChoosePointsBundle<Stat>> getItemList()
					{return potentialStats;}	
				});

			butt.setOnClickListener(new View.OnClickListener(){
					@Override public void onClick(View v)
					{
						for(ChoosePointsAdapter.ChoosePointsBundle<Stat> stat : potentialStats)
						{myChar.setBaseStat(stat.item, stat.curVal);}
						Bundle b = new Bundle();
						b.putString(BundleConstants.CHARACTER, myChar.toJson());
						delegate.hookComplete(b);
					}
				});
		}

		@Override public boolean hasUI(){return true;}
		@Override public int getPriority(){return 100;}
	}
	
	public static class ChooseSkillSpellConnectionMilitaryBoon extends zzCharacterAdvancementFragment
	{
		public static ChooseSkillSpellConnectionMilitaryBoon make(int pExp)
		{
			ChooseSkillSpellConnectionMilitaryBoon frag = new ChooseSkillSpellConnectionMilitaryBoon();
			frag.curExp = pExp;
			return frag;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup pRoot, Bundle bund)
		{
			return inflater.inflate(R.layout.frag_choice_list, pRoot, false);
		}

		@Override
		public void onViewCreated(View root, Bundle b)
		{
			super.onViewCreated(root, b);
			final ListView list = (ListView)root.findViewById(R.id.listChoiceList);
			final ArrayList<BOON_CHOICE> choices = new ArrayList<BOON_CHOICE>();
			
			//To be nice, let's spend a little effort determining what options are available
			Set<Spell> spellBook = new HashSet<Spell>();
			for(Career c : myChar.careers)
			{spellBook.addAll(c.careerSpells());}
			if(myChar.spells.size() < spellBook.size())
			{
				//there's always something left to learn!
				choices.add(BOON_CHOICE.SPELL);
			}
			
			Set<Ability> possibleAbilities = new HashSet<Ability>();
			for(Career c : myChar.careers)
			{possibleAbilities.addAll(c.careerAbilities());}
			if(myChar.abilities.size() < possibleAbilities.size())
			{
				choices.add(BOON_CHOICE.ABILITY);
			}
			
			choices.add(BOON_CHOICE.CONNECTION);
			
			//C'mon, you're not going to max this, are you?
			choices.add(BOON_CHOICE.MILITARY_SKILL);
			
			list.setAdapter(new ArrayAdapter<BOON_CHOICE>(getActivity(), android.R.layout.simple_list_item_1, choices));
			list.setOnItemClickListener( new AdapterView.OnItemClickListener(){
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int which, long id)
					{
						ChooseSpellFragment spellFrag;
						ChooseAbilityFragment abilityFrag;
						ChooseMilitarySkillFrag militaryFrag;
						
						switch(choices.get(which))
						{
							case SPELL:
								spellFrag = new ChooseSpellFragment();
								spellFrag.setIsPrimaryFrag(false);
								delegate.pushExtraFrag(spellFrag, "spellFrag");
								break;
							case ABILITY:
								abilityFrag = new ChooseAbilityFragment();
								abilityFrag.setIsPrimaryFrag(false);
								delegate.pushExtraFrag(abilityFrag, "abilityFrag");
								break;
							case MILITARY_SKILL:
								militaryFrag = new ChooseMilitarySkillFrag();
								militaryFrag.setIsPrimaryFrag(false);
								delegate.pushExtraFrag(militaryFrag , "militaryFrag");
								break;
							default:
								Bundle b = new Bundle();
								b.putString(BundleConstants.CHARACTER, myChar.toJson());
								delegate.hookComplete(b);
						}
					}
				});
		}

		@Override public boolean hasUI(){return true;}
		@Override public int getPriority(){return 100;}
	}
	
	public static class ChooseSpellFragment extends zzChooseAnAdvancementFragment<Spell>
	{
		List<Spell> getItems(){
			//Grab all spells that can be learned in careers and aren't already known
			List<Spell> spells = new ArrayList<Spell>();
			Set<Spell> knownSpells = myChar.spells;
			
			for(Career c : myChar.careers)
			{
				for(Spell spell : c.careerSpells())
				{
					if(!knownSpells.contains(spell)){spells.add(spell);}
				}
			}
			
			//bleh.  Apologies for the terrible UI.
			Collections.sort(spells);
			
			return spells;
		}

		void onChosen(Spell chosen){
			myChar.spells.add(chosen);
		}

		String getTitle(){
			return "Choose a spell to learn";
		}
	}
	
	public static class ChooseAbilityFragment extends zzChooseAnAdvancementFragment<Ability>
	{
		List<Ability> getItems(){
			//Grab all abilities that can be learned in careers and aren't already known
			List<Ability> abilities = new ArrayList<Ability>();
			Set<Ability> knownAbilities = myChar.abilities;

			for(Career c : myChar.careers)
			{
				for(Ability ability : c.careerAbilities())
				{
					if(!knownAbilities.contains(ability) && ability.meetsPrereq(myChar).isAllowed)
					{
						abilities.add(ability);
					}
				}
			}

			//bleh.  Apologies for the terrible UI.
			Collections.sort(abilities, new Comparator<Ability>(){
				public int compare(Ability a, Ability b)
				{
					return a.abilityEnum().compareTo(b.abilityEnum());
				}
			});

			return abilities;
		}

		void onChosen(Ability chosen){
			myChar.abilities.add(chosen);
		}

		String getTitle(){
			return "Choose an ability to learn";
		}
	}
	
	public static class ChooseMilitarySkillFrag extends zzChooseOneMilitarySkillHook
	{
		@Override
		public List<Skill> getItems()
		{
			Set<Skill> miliSkills = new HashSet<Skill>();
			
			for(Career career : myChar.careers)
			{
				for(Pair<Skill,Integer> skillCap : career.careerSkills())
				{
					int curLevel = myChar.getSkillBaseLevel(skillCap.first);
					if(skillCap.first.isMilitary() && curLevel < skillCap.second && curLevel < skillCapForEXP(curExp))
					{
						miliSkills.add(skillCap.first);
					}
				}
			}
			
			return new ArrayList<Skill>(miliSkills);
		}
	}
	
	public static class ChooseArchetypeBenefitFragment extends zzChooseAnAdvancementFragment<Ability>
	{
		public static ChooseArchetypeBenefitFragment make(int pExp)
		{
			ChooseArchetypeBenefitFragment frag = new ChooseArchetypeBenefitFragment();
			frag.curExp = pExp;
			return frag;
		}
		
		List<Ability> getItems(){
			//Grab all abilities that can be learned in careers and aren't already known
			List<Ability> abilities = new ArrayList<Ability>();
			Set<Ability> knownAbilities = myChar.abilities;

			for(Ability ability : AbilityEnum.forArchetype(myChar.archetype))
			{
				if(!knownAbilities.contains(ability) && ability.meetsPrereq(myChar).isAllowed)
				{
					abilities.add(ability);
				}
			}
			
			//bleh.  Apologies for the terrible UI.
			Collections.sort(abilities, new Comparator<Ability>(){
					public int compare(Ability a, Ability b)
					{
						return a.abilityEnum().compareTo(b.abilityEnum());
					}
				});

			return abilities;
		}

		void onChosen(Ability chosen){
			myChar.abilities.add(chosen);
		}

		String getTitle(){
			return "Choose a "+myChar.archetype+" archetype benefit!";
		}
	}
	
	public static class ChooseArchetypeOrCareerBoon extends zzCharacterAdvancementFragment
	{
		public static ChooseArchetypeOrCareerBoon make(int pExp)
		{
			ChooseArchetypeOrCareerBoon frag = new ChooseArchetypeOrCareerBoon();
			frag.curExp = pExp;
			return frag;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup pRoot, Bundle bund)
		{
			return inflater.inflate(R.layout.frag_choice_list, pRoot, false);
		}

		@Override
		public void onViewCreated(View root, Bundle b)
		{
			super.onViewCreated(root, b);
			final ListView list = (ListView)root.findViewById(R.id.listChoiceList);
			final ArrayList<BOON_CHOICE> choices = new ArrayList<BOON_CHOICE>();

			choices.add(BOON_CHOICE.ARCHTYPE_BENEFIT);
			choices.add(BOON_CHOICE.CAREER_AND_SKILLS);

			list.setAdapter(new ArrayAdapter<BOON_CHOICE>(getActivity(), android.R.layout.simple_list_item_1, choices));
			list.setOnItemClickListener( new AdapterView.OnItemClickListener(){
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int which, long id)
					{
						ChooseArchetypeBenefitFragment archetypeFrag;
						zzStaticCreateCharacterHooks.ChooseCareerFragment careerFrag;
						final ChooseOccupationalSkillsFragment skillsFrag = ChooseOccupationalSkillsFragment.make(curExp,2);

						switch(choices.get(which))
						{
							case ARCHTYPE_BENEFIT:
								archetypeFrag = ChooseArchetypeBenefitFragment.make(curExp);
								archetypeFrag.setIsPrimaryFrag(false);
								delegate.pushExtraFrag(archetypeFrag, "archetypeFrag");
								break;
							case CAREER_AND_SKILLS:
								careerFrag = new zzStaticCreateCharacterHooks.ChooseCareerFragment(){
									@Override
									@Hack
									@PleaseDontShowUpOn(Website.STACKOVERFLOW)
									public void onViewCreated(View rootView, Bundle b)
									{
										super.onViewCreated(rootView,b);
										ListView listView = (ListView)rootView.findViewById(R.id.listChoiceList);
										listView.setOnItemClickListener( new AdapterView.OnItemClickListener(){
												@Override
												public void onItemClick(AdapterView<?> parent, View view, int which, long id)
												{
													onChosen(getItems().get(which));
													Bundle b = new Bundle();
													b.putString(BundleConstants.CHARACTER, myChar.toJson());
													ChooseOccupationalSkillsFragment skillsFrag = ChooseOccupationalSkillsFragment.make(curExp,2);
													skillsFrag.setIsPrimaryFrag(false);
													delegate.pushExtraFrag(skillsFrag, "skillsFrag");
												}
											});
									}
								};
								careerFrag.setIsPrimaryFrag(false);
								delegate.pushExtraFrag(careerFrag, "careerFrag");
								
								break;
							default:
								Bundle b = new Bundle();
								b.putString(BundleConstants.CHARACTER, myChar.toJson());
								delegate.hookComplete(b);
						}
					}
				});
		}

		@Override public boolean hasUI(){return true;}
		@Override public int getPriority(){return 100;}
	}
}
