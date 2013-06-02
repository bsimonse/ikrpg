package com.random.captain.ikrpg.character;

import android.widget.*;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.random.captain.ikrpg.R;

class ChooseRaceFragment extends PostCreateHook
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
	@Override public void undoPostCreateHook(){myChar.race = null;}
	@Override public int getPriority(){return -1;}
}
