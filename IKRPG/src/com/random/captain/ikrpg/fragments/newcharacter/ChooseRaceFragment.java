package com.random.captain.ikrpg.fragments.newcharacter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.activities.NewCharacterActivity;
import com.random.captain.ikrpg.model.Attributes.Race;

public class ChooseRaceFragment extends Fragment
{
	private NewCharacterActivity hostActivity;
	private ListView raceList;
	
	@Override
	public void onAttach(Activity host)
	{
		super.onAttach(host);
		hostActivity = (NewCharacterActivity)host;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle bund)
	{
		View rootView = inflater.inflate(R.layout.frag_choose_race, root, false);
		
		raceList = (ListView)rootView.findViewById(R.id.raceList);
		raceList.setAdapter(new ArrayAdapter<Race>(getActivity(), android.R.layout.simple_list_item_1, Race.values()));
		raceList.setOnItemClickListener( new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int which, long id)
			{
				raceSelected(Race.values()[which]);
			}
		});
		return rootView;
	}
		
	private void raceSelected(Race race)
	{
		hostActivity.race = race;
		hostActivity.nextFrag(NewCharacterActivity.FragState.RACE);
	}
}
