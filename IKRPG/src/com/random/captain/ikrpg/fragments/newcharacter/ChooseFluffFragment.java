package com.random.captain.ikrpg.fragments.newcharacter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.activities.NewCharacterActivity;
import com.random.captain.ikrpg.model.Attributes.Fluff;

public class ChooseFluffFragment extends Fragment
{
	private NewCharacterActivity hostActivity;
	private Fluff fluff;
	
	@Override
	public void onAttach(Activity host)
	{
		super.onAttach(host);
		hostActivity = (NewCharacterActivity)host;
		fluff = new Fluff();
	}
	
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
		fluff.name = ((EditText)(getView().findViewById(R.id.nameInput))).getText().toString();
		fluff.characteristics = ((EditText)(getView().findViewById(R.id.characteristicsInput))).getText().toString();
		fluff.height = ((EditText)(getView().findViewById(R.id.heightInput))).getText().toString();
		fluff.weight = ((EditText)(getView().findViewById(R.id.weightInput))).getText().toString();
		fluff.faith = ((EditText)(getView().findViewById(R.id.faithInput))).getText().toString();
		fluff.owningPlayer = ((EditText)(getView().findViewById(R.id.owningPlayerInput))).getText().toString();
		
		int selectedSex = ((RadioGroup)(getView().findViewById(R.id.sexChoiceGroup))).getCheckedRadioButtonId();
		if(selectedSex == R.id.sexChoiceFemale){fluff.sex = "Female";}
		else{fluff.sex = "Male";}
		
		hostActivity.fluff = fluff;
		hostActivity.nextFrag(NewCharacterActivity.FragState.FLUFF);
	}
	
	public static void undo(NewCharacterActivity myAct){myAct.fluff = null;}
}
