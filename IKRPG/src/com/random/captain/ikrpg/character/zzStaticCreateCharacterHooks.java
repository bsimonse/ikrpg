package com.random.captain.ikrpg.character;

import android.widget.*;
import java.util.*;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.gear.Loot;
import com.random.captain.ikrpg.gear.LootPack;
import com.random.captain.ikrpg.util.BundleConstants;

//This isn't really a class... I just wanted to put a bunch of stuff in one file.
//It's got to be public for Android to recreate the fragments... so no encapsulation for you.
public class zzStaticCreateCharacterHooks
{
	
public static class ChooseRaceFragment extends zzCreateCharacterHook
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle bund)
	{
		return inflater.inflate(R.layout.frag_choice_list, root, false);
	}

	@Override
	public void onViewCreated(View rootView, Bundle b)
	{
		super.onViewCreated(rootView,b);
		ListView raceList = (ListView)rootView.findViewById(R.id.listChoiceList);
		raceList.setAdapter(new ArrayAdapter<Race>(getActivity(), android.R.layout.simple_list_item_1, Race.values()));
		raceList.setOnItemClickListener( new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int which, long id)
				{
					Race chosen = Race.values()[which];
					myChar.race = chosen;
					Bundle b = new Bundle();
					b.putString(BundleConstants.CHARACTER, myChar.toJson());
					delegate.hookComplete(b);
				}
			});

		TextView tv = (TextView)rootView.findViewById(R.id.listChoiceTitle);
		tv.setText("Choose your Race");
	}
	
	@Override protected boolean hasUI(){return true;}
	@Override public int getPriority(){return -1;}
}
	
public static class ChooseArchetypeHook extends zzCreateCharacterHook
{
	private ListView archetypeList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle bund)
	{
		return inflater.inflate(R.layout.frag_choice_list, root, false);
	}

	@Override
	public void onViewCreated(View rootView, Bundle b)
	{
		super.onViewCreated(rootView, b);
		final List<Archetype> validArchetypes = new ArrayList<Archetype>(5);
		for(Archetype at : Archetype.values())
		{
			zzPrereqCheckResult result = at.meetsPrereq(myChar);

			//TODO: list additional questions with stars

			if(result.additionalQuestion != null || result.isAllowed)
			{validArchetypes.add(at);}
		}

		archetypeList = (ListView)rootView.findViewById(R.id.listChoiceList);
		archetypeList.setAdapter(new ArrayAdapter<Archetype>(getActivity(), android.R.layout.simple_list_item_1, validArchetypes.toArray(new Archetype[validArchetypes.size()])));
		archetypeList.setOnItemClickListener( new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int which, long id)
				{
					//TODO: ask additional question!
					Archetype archetype = validArchetypes.get(which);
					myChar.archetype = archetype;
					Bundle b = new Bundle();
					b.putString(BundleConstants.CHARACTER, myChar.toJson());
					delegate.hookComplete(b);
				}
			});

		TextView tv = (TextView)rootView.findViewById(R.id.listChoiceTitle);
		tv.setText("Choose an Archetype");
	}
	
	@Override protected boolean hasUI(){return true;}
	@Override public int getPriority(){return -1;}
}
	
public static class ChooseCareerFragment extends zzCreateCharacterHook
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle bund)
	{
		return inflater.inflate(R.layout.frag_choice_list, root, false);
	}
	
	@Override
	public void onViewCreated(View rootView, Bundle b)
	{
		super.onViewCreated(rootView, b);
		
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

		ListView careerList = (ListView)rootView.findViewById(R.id.listChoiceList);
		careerList.setAdapter(new ArrayAdapter<Career>(getActivity(), android.R.layout.simple_list_item_1, validCareers.toArray(new Career[validCareers.size()])));
		careerList.setOnItemClickListener( new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int which, long id)
				{
					Career career = validCareers.get(which);
					myChar.careers.add(career);
					Bundle b = new Bundle();
					b.putString(BundleConstants.CHARACTER, myChar.toJson());
					delegate.hookComplete(b);
				}
			});

		TextView tv = (TextView)rootView.findViewById(R.id.listChoiceTitle);
		tv.setText(isSecondCareer ? "Choose your second career" : "Choose your first career");
	}

	@Override protected boolean hasUI(){return true;}
	@Override public int getPriority(){return -1;}
}
	
public static class ChooseAdvancementPointsHook extends zzCreateCharacterHook
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
		final ListView list = (ListView)root.findViewById(R.id.chooseAdvancementPointsList);
		list.setAdapter(new ChooseAdvancementPointsAdapter(myChar));

		Button submitButton = (Button)root.findViewById(R.id.chooseAdvancementPointsSubmit);
		submitButton.setOnClickListener(new View.OnClickListener(){
				@Override public void onClick(View v)
				{
					ChooseAdvancementPointsAdapter adapter = (ChooseAdvancementPointsAdapter)list.getAdapter();
					adapter.lockInStats();
					Bundle b = new Bundle();
					b.putString(BundleConstants.CHARACTER, myChar.toJson());
					delegate.hookComplete(b);
				}
			});
	}

	@Override protected boolean hasUI(){return true;}
	@Override public int getPriority(){return 100;}
}

public static class ChooseAdvancementPointsAdapter extends BaseAdapter
{
	private final static int MAX_INCREASES = 3;
	private zzBaseCharacter character;
	private Map<Stat, Integer> eligibleStats;
	private List<Stat> eStatsList; //for keeping order straight
	private int increases = 0;

	public ChooseAdvancementPointsAdapter(zzBaseCharacter pChar)
	{
		character = pChar;

		//remember all stats that can be increased
		eligibleStats = new HashMap<Stat, Integer>(10);
		for(Stat stat : Stat.values()){if(pChar.getBaseStat(stat) < pChar.getMaxStat(stat)){eligibleStats.put(stat, pChar.getBaseStat(stat));}}

		eStatsList = new ArrayList<Stat>(10);
		eStatsList.addAll(eligibleStats.keySet());
		Collections.sort(eStatsList, new Comparator<Stat>(){
				@Override public int compare(Stat first, Stat second)
				{
					return first.ordinal() - second.ordinal();
				}
			});
	}

	public void lockInStats()
	{
		for(Stat stat : eligibleStats.keySet())
		{character.setBaseStat(stat, eligibleStats.get(stat));}
	}

	@Override public int getCount(){return eStatsList.size();}
	@Override public long getItemId(int which){return which;}
	@Override public Object getItem(int which){return eStatsList.get(which);}
	@Override public View getView(int position, View convertView, ViewGroup parent)
	{
		final zzChooseAdvancementListItemViewHolder viewHolder;

		if(convertView == null)
		{
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.list_item_choose_advancement_points, parent, false);
			viewHolder = new zzChooseAdvancementListItemViewHolder(convertView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (zzChooseAdvancementListItemViewHolder)convertView.getTag();
		}

		final Stat pStat = (Stat)getItem(position);

		viewHolder.textLabel.setText(pStat.toString());
		viewHolder.valueLabel.setText(""+eligibleStats.get(pStat)+"/"+character.getMaxStat(pStat));

		viewHolder.incrementButton.setOnClickListener(new View.OnClickListener(){
				@Override public void onClick(View v){
					if(increases >= MAX_INCREASES){return;}

					int oldValue = eligibleStats.get(pStat);
					int newValue = oldValue + 1;
					if(newValue <= character.getMaxStat(pStat))
					{
						increases++;
						eligibleStats.put(pStat, newValue);
						notifyDataSetChanged();
					}
				}
			});

		viewHolder.decrementButton.setOnClickListener(new View.OnClickListener(){
				@Override public void onClick(View v){
					if(increases == 0){return;}

					int oldValue = eligibleStats.get(pStat);
					int newValue = oldValue - 1;
					if(newValue >= character.getBaseStat(pStat))
					{
						increases--;
						eligibleStats.put(pStat, newValue);
						notifyDataSetChanged();
					}
				}
			});

		//is increment button disabled
		if(increases >= MAX_INCREASES || eligibleStats.get(pStat) >= character.getMaxStat(pStat))
		{viewHolder.incrementButton.setEnabled(false);}
		else{viewHolder.incrementButton.setEnabled(true);}

		//is decrement button disabled
		if(increases <= 0 || eligibleStats.get(pStat) <= character.getBaseStat(pStat))
		{viewHolder.decrementButton.setEnabled(false);}
		else{viewHolder.decrementButton.setEnabled(true);}

		return convertView;
	}
}

public static class CareerFinalizerHook extends zzCreateCharacterHook
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

	@Override public int getPriority(){return 75;}
}

public static class ChooseFluffFragment extends zzCreateCharacterHook
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

	@Override protected boolean hasUI(){return true;}
	@Override public int getPriority(){return 101;}
}
	
}
