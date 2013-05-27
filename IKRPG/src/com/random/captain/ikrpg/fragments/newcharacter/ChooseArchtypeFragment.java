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
import com.random.captain.ikrpg.model.Attributes.Archetype;
import com.random.captain.ikrpg.model.BaseCharacter;
import com.random.captain.ikrpg.model.Creators.PrereqCheckResult;
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
		View rootView = inflater.inflate(R.layout.frag_choice_list, root, false);

		final List<Archetype> validArchetypes = new ArrayList<Archetype>(5);
		for(Archetype at : Archetype.values())
		{
			PrereqCheckResult result = at.meetsPrereq(new BaseCharacter(null, hostActivity.race, null, null, null, null, null, null));
			
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
		hostActivity.archetype = archetype;
		hostActivity.nextFrag(NewCharacterActivity.FragState.ARCHETYPE);
	}
	
	//Bleehhhhhh please clean this up
	public static void undo(NewCharacterActivity myAct){myAct.archetype = null;}
}
