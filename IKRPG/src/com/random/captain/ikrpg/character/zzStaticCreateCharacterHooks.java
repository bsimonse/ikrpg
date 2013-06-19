package com.random.captain.ikrpg.character;

import android.widget.*;
import java.util.*;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.character.Archetype;
import com.random.captain.ikrpg.character.Stat;

public class zzStaticCreateCharacterHooks
{
}

class ChooseRaceFragment extends zzCreateCharacterHook
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle bund)
	{
		View rootView = inflater.inflate(R.layout.frag_choice_list, root, false);

		ListView raceList = (ListView)rootView.findViewById(R.id.listChoiceList);
		raceList.setAdapter(new ArrayAdapter<Race>(getActivity(), android.R.layout.simple_list_item_1, Race.values()));
		raceList.setOnItemClickListener( new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int which, long id)
				{
					raceSelected(Race.values()[which]);
				}
			});

		TextView tv = (TextView)rootView.findViewById(R.id.listChoiceTitle);
		tv.setText("Choose your Race");

		return rootView;
	}

	private void raceSelected(Race race)
	{
		Log.i("IKRPG","Race chosen! "+race.toString());
		myChar.race = race;
		delegate.hookComplete();
	}

	@Override public boolean hasUI(){return true;}
	@Override public void undoHook(){myChar.race = null;}
	@Override public int getPriority(){return -1;}
}
	
class ChooseArchetypeHook extends zzCreateCharacterHook
{
	private ListView archetypeList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle bund)
	{
		View rootView = inflater.inflate(R.layout.frag_choice_list, root, false);

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
					archetypeSelected(validArchetypes.get(which));
				}
			});

		TextView tv = (TextView)rootView.findViewById(R.id.listChoiceTitle);
		tv.setText("Choose an Archetype");

		return rootView;
	}

	private void archetypeSelected(Archetype archetype)
	{
		//TODO: ask additional question!
		Log.i("IKRPG","Archetype chosen! "+archetype.toString());
		myChar.archetype = archetype;
		delegate.hookComplete();
	}

	@Override public boolean hasUI(){return true;}
	@Override public void undoHook(){myChar.archetype = null;}
	@Override public int getPriority(){return -1;}
}
	
class ChooseCareerFragment extends zzCreateCharacterHook
{
	static final String SECOND_CAREER = "thisIsMySecondCareer";
	static final String CHOSEN_CAREER = "IhaveChosenThisCareer";

	private ListView careerList;
	private boolean isSecondCareer = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle bund)
	{
		View rootView = inflater.inflate(R.layout.frag_choice_list, root, false);

		Bundle arguments = getArguments();
		if(arguments != null)
		{
			isSecondCareer = getArguments().getBoolean(SECOND_CAREER, false);
		}

		final List<Career> validCareers = new ArrayList<Career>(30);
		for(Career career : Career.values())
		{
			zzPrereqCheckResult result = career.meetsPrereq(myChar);

			//TODO:
			//Additional questions ARE displayed, with stars!

			if(result.additionalQuestion != null || result.isAllowed)
			{validCareers.add(career);}
		}

		careerList = (ListView)rootView.findViewById(R.id.listChoiceList);
		careerList.setAdapter(new ArrayAdapter<Career>(getActivity(), android.R.layout.simple_list_item_1, validCareers.toArray(new Career[validCareers.size()])));
		careerList.setOnItemClickListener( new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int which, long id)
				{
					careerSelected(validCareers.get(which));
				}
			});

		TextView tv = (TextView)rootView.findViewById(R.id.listChoiceTitle);
		tv.setText(isSecondCareer ? "Choose your second career" : "Choose your first career");
		return rootView;
	}

	private void careerSelected(Career career)
	{
		Log.i("IKRPG","Chosen career: "+career.toString());
		getArguments().putSerializable(CHOSEN_CAREER, career);
		myChar.careers.add(career);
		delegate.hookComplete();
	}

	@Override public boolean hasUI(){return true;}
	@Override public void undoHook()
	{
		Career chosenCareer = (Career)getArguments().getSerializable(CHOSEN_CAREER);
		Log.i("IKRPG","Trying to remove "+chosenCareer.toString());
		myChar.careers.remove(chosenCareer);
	}

	@Override public int getPriority(){return -1;}
}
	
class ChooseAdvancementPointsHook extends zzCreateCharacterHook
{
	private Map<Stat, Integer> oldBaseStats;

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

	@Override void startHook(zzBaseCharacter pChar, zzCreateCharacterHookDelegate pDelegate)
	{
		super.startHook(pChar, pDelegate);
		oldBaseStats = myChar.baseStats;
	}

	@Override public void undoHook()
	{
		for(Stat stat : Stat.values())
		{
			int oldValue = oldBaseStats.get(stat);
			if(oldValue >= 0){myChar.setBaseStat(stat, oldValue);}
		}
	}

	@Override public int getPriority(){return 100;}
}

class ChooseAdvancementPointsAdapter extends BaseAdapter
{
	private final static int MAX_INCREASES = 3;
	private zzBaseCharacter character;
	private Map<Stat, Integer> eligibleStats;
	private List<Stat> eStatsList; //for keeping order straight
	private int increases = 0;
	private ListView listView;

	public ChooseAdvancementPointsAdapter(zzBaseCharacter pChar, ListView pListView)
	{
		character = pChar;
		listView = pListView;

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
	
class ChooseFluffFragment extends zzCreateCharacterHook
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle bund)
	{
		View rootView = inflater.inflate(R.layout.frag_choose_fluff, root, false);

		Button doneButton = (Button)rootView.findViewById(R.id.fluffDoneButton);
		doneButton.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					fluffComplete();
				}
			});
		return rootView;
	}

	private void fluffComplete()
	{
		Fluff fluff = new Fluff();
		fluff.name = ((EditText)(getView().findViewById(R.id.nameInput))).getText().toString();
		fluff.characteristics = ((EditText)(getView().findViewById(R.id.characteristicsInput))).getText().toString();
		fluff.height = ((EditText)(getView().findViewById(R.id.heightInput))).getText().toString();
		fluff.weight = ((EditText)(getView().findViewById(R.id.weightInput))).getText().toString();
		fluff.faith = ((EditText)(getView().findViewById(R.id.faithInput))).getText().toString();
		fluff.owningPlayer = ((EditText)(getView().findViewById(R.id.owningPlayerInput))).getText().toString();

		int selectedSex = ((RadioGroup)(getView().findViewById(R.id.sexChoiceGroup))).getCheckedRadioButtonId();
		if(selectedSex == R.id.sexChoiceFemale){fluff.sex = "Female";}
		else{fluff.sex = "Male";}

		myChar.fluff = fluff;
		delegate.hookComplete();
	}

	@Override public boolean hasUI(){return true;}
	@Override public void undoHook(){myChar.fluff = null;}
	@Override public int getPriority(){return -1;}
}
