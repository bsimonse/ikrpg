package com.random.captain.ikrpg.fragments.newcharacter;

import android.widget.*;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
		View rootView = inflater.inflate(R.layout.frag_choice_list, root, false);
		
		raceList = (ListView)rootView.findViewById(R.id.listChoiceList);
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
		hostActivity.race = race;
		hostActivity.nextFrag(NewCharacterActivity.FragState.RACE);
	}
	
	//Bleehhhhhh please clean this up
	public static void undo(NewCharacterActivity myAct){myAct.race = null;}
}
