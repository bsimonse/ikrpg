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
import com.random.captain.ikrpg.model.Attributes.Archetype;
import java.util.ArrayList;
import java.util.List;

public class ChooseArchtypeFragment extends Fragment
{
	private NewCharacterActivity hostActivity;
	private ListView archetypeList;

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

		final List<Archetype> validArchetypes = new ArrayList<Archetype>(5);
		for(Archetype at : Archetype.values())
		{
			if(at.meetsPrereq(hostActivity.race, null, null, null, null, null, getActivity()))
			{
				validArchetypes.add(at);
			}
		}
		
		archetypeList = (ListView)rootView.findViewById(R.id.raceList);
		archetypeList.setAdapter(new ArrayAdapter<Archetype>(getActivity(), android.R.layout.simple_list_item_1, validArchetypes.toArray(new Archetype[validArchetypes.size()])));
		archetypeList.setOnItemClickListener( new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int which, long id)
				{
					archetypeSelected(validArchetypes.get(which));
				}
			});
		return rootView;
	}

	private void archetypeSelected(Archetype archetype)
	{
		hostActivity.archetype = archetype;
		hostActivity.nextFrag(NewCharacterActivity.FragState.ARCHETYPE);
	}
}
