package com.random.captain.ikrpg.character;

import android.widget.*;
import java.util.*;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.character.Career;
import com.random.captain.ikrpg.gear.Loot;
import com.random.captain.ikrpg.gear.LootPack;
import com.random.captain.ikrpg.util.BundleConstants;
import com.random.captain.ikrpg.util.ChoosePointsAdapter;

//This isn't really a class... I just wanted to put a bunch of stuff in one file.
//It's got to be public for Android to recreate the fragments.
public class zzStaticCreateCharacterHooks
{
	
public static class ChooseRaceFragment extends zzChooseAnAdvancementFragment<Race>
{
	@Override
	List<Race> getItems(){
		return Arrays.asList(Race.values());
	}
	
	@Override
	void onChosen(Race chosen){
		myChar.race = chosen;
	}
	
	@Override
	String getTitle(){
		return "Choose your race";
	}
	
	@Override public int getPriority(){return -1;}
}
	
public static class ChooseArchetypeHook extends zzChooseAnAdvancementFragment<Archetype>
{
	@Override
	List<Archetype> getItems(){
		List<Archetype> validArchetypes = new ArrayList<Archetype>(5);
		for(Archetype at : Archetype.values())
		{
			zzPrereqCheckResult result = at.meetsPrereq(myChar);

			//TODO: list additional questions with stars

			if(result.additionalQuestion != null || result.isAllowed)
			{validArchetypes.add(at);}
		}
		return validArchetypes;
	}
	
	@Override
	void onChosen(Archetype chosen){
		myChar.archetype = chosen;
	}
	
	@Override
	String getTitle(){
		return "Choose an archetype";
	}
	
	@Override public int getPriority(){return -1;}
}
	
public static class ChooseCareerFragment extends zzChooseAnAdvancementFragment<Career>
{
	List<Career> getItems(){
		boolean isSecondCareer = myChar.careers.size() > 0;
		final List<Career> validCareers = new ArrayList<Career>(30);
		for(Career career : Career.values())
		{
			zzPrereqCheckResult result = career.meetsPrereq(myChar);
			if(isSecondCareer)
			{
				//gotta make sure original career "approves"
				Career firstCareer = myChar.careers.toArray(new Career[1])[0];
				if(firstCareer!=null && !firstCareer.agreesWithDuringCreation(career))
				{continue;}
			}

			//TODO:
			//Additional questions ARE displayed, with stars!

			if(result.additionalQuestion != null || result.isAllowed)
			{validCareers.add(career);}
		}
		return validCareers;
	}
	
	void onChosen(Career chosen){
		myChar.careers.add(chosen);
	}
	
	String getTitle(){
		boolean isSecondCareer = myChar.careers.size() > 0;
		return isSecondCareer ? "Choose your second career" : "Choose your first career";
	}
	
	@Override public int getPriority(){return -1;}
}
	
public static class ChooseAdvancementPointsHook extends zzCharacterAdvancementFragment
{
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
				{
					return 3;
				}
	
				@Override
				protected String getLabel(ChoosePointsBundle<Stat> bundle)
				{
					return bundle.item.longName();
				}

				@Override
				protected List<ChoosePointsBundle<Stat>> getItemList()
				{
					return potentialStats;
				}	
			});

		TextView tv = (TextView)root.findViewById(R.id.listChoiceTitle);
		tv.setText("Choose 3 stats");
		
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

public static class CareerFinalizerHook extends zzCharacterAdvancementFragment
{	
	@Override
	public Bundle doDefaultCase()
	{
		//skills
		myChar.setBaseSkills(myChar.careers);
		myChar.deriveSkillCheckLevels();
		//Careers; Abilities, spells, loot, and post hooks
		int startGold = 0;
		Collection<Loot> startLoot = new ArrayList<Loot>();
		for(Career career : myChar.careers)
		{
			myChar.abilities.addAll(career.startingAbilities());
			myChar.spells.addAll(career.startingSpells());
			myChar.connections.addAll(career.startingConnections());
			startGold += career.startGold();
			startLoot.addAll(career.startLoot());
		}
		myChar.lootPack = new LootPack(startGold, startLoot);
		Bundle b = new Bundle();
		b.putString(BundleConstants.CHARACTER, myChar.toJson());
		return b;
	}
	@Override public int getPriority(){return 0;}
}

public static class ChooseFluffFragment extends zzCharacterAdvancementFragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle bund)
	{
		return inflater.inflate(R.layout.frag_choose_fluff, root, false);
	}
	
	@Override
	public void onViewCreated(View rootView, Bundle b)
	{
		super.onViewCreated(rootView, b);
		Button doneButton = (Button)rootView.findViewById(R.id.fluffDoneButton);
		doneButton.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					Fluff fluff = new Fluff();
					fluff.name = ((EditText)(getView().findViewById(R.id.nameInput))).getText().toString();
					fluff.characteristics = ((EditText)(getView().findViewById(R.id.characteristicsInput))).getText().toString();
					fluff.height = ((EditText)(getView().findViewById(R.id.heightInput))).getText().toString();
					fluff.weight = ((EditText)(getView().findViewById(R.id.weightInput))).getText().toString();
					fluff.faith = ((EditText)(getView().findViewById(R.id.faithInput))).getText().toString();
					fluff.owningPlayer = ((EditText)(getView().findViewById(R.id.owningPlayerInput))).getText().toString();

					int selectedSex = ((RadioGroup)(getView().findViewById(R.id.sexChoiceGroup))).getCheckedRadioButtonId();
					//Yep, I'm a cis asshole!  This will be fixed.
					if(selectedSex == R.id.sexChoiceFemale){fluff.sex = "Female";}
					else{fluff.sex = "Male";}

					myChar.fluff = fluff;
					Bundle b = new Bundle();
					b.putString(BundleConstants.CHARACTER, myChar.toJson());
					delegate.hookComplete(b);
				}
			});
	}

	@Override public boolean hasUI(){return true;}
	@Override public int getPriority(){return 101;}
}
	
}
