package com.random.captain.ikrpg.character;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import com.random.captain.ikrpg.R;

class ChooseFluffFragment extends PostCreateHook
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
	@Override public void undoPostCreateHook(){myChar.fluff = null;}
	@Override public int getPriority(){return -1;}
}
