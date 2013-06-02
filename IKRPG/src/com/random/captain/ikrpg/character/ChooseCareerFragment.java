package com.random.captain.ikrpg.character;

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

class ChooseCareerFragment extends PostCreateHook
{
	static final String SECOND_CAREER = "thisIsMySecondCareer";
	static final String CHOSEN_CAREER = "IhaveChosenThisCareer";
	
	private ListView careerList;
	private boolean isSecondCareer = false;
	private Career chosenCareer;
	
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
			PrereqCheckResult result = career.meetsPrereq(myChar);
			
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
	@Override public void undoPostCreateHook()
	{
		Career chosenCareer = (Career)getArguments().getSerializable(CHOSEN_CAREER);
		Log.i("IKRPG","Trying to remove "+chosenCareer.toString());
		myChar.careers.remove(chosenCareer);
	}
	
	@Override public int getPriority(){return -1;}
}
