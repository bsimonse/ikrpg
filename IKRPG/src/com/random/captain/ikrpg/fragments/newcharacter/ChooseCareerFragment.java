package com.random.captain.ikrpg.fragments.newcharacter;

import android.widget.*;
import java.util.*;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.activities.NewCharacterActivity;
import com.random.captain.ikrpg.model.Attributes.Career;
import com.random.captain.ikrpg.model.BaseCharacter;
import com.random.captain.ikrpg.model.Creators.PrereqCheckResult;

public class ChooseCareerFragment extends Fragment
{
	private NewCharacterActivity hostActivity;
	private ListView careerList;
	private boolean isSecondCareer = false;
	
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

		Bundle arguments = getArguments();
		if(arguments != null)
		{
			isSecondCareer = getArguments().getBoolean(hostActivity.SECOND_CAREER, false);
		}
		
		final Set<Career> myCareers = new HashSet<Career>();
		myCareers.add(hostActivity.career1);
		
		final List<Career> validCareers = new ArrayList<Career>(30);
		for(Career career : Career.values())
		{
			PrereqCheckResult result = career.meetsPrereq(new BaseCharacter(null, hostActivity.race, hostActivity.archetype, myCareers, null, null, null, null));
			
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
		if(isSecondCareer)
		{
			Log.i("IKRPG","Second career: "+career.toString());
			hostActivity.career2 = career;
			hostActivity.nextFrag(NewCharacterActivity.FragState.CAREER2);
		}
		else
		{
			Log.i("IKRPG","First career: "+career.toString());
			hostActivity.career1 = career;
			hostActivity.nextFrag(NewCharacterActivity.FragState.CAREER1);
		}
	}
	
	//Bleehhhhhh please clean this up
	public static void undo(NewCharacterActivity myAct, boolean first)
	{
		if(first){myAct.career1 = null;}
		else{myAct.career2 = null;}
	}
}
