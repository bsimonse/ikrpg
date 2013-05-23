package com.random.captain.ikrpg.fragments.newcharacter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;
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
		
		//For now
		fluff.name = "Sir Edgar Bartholemew";
		fluff.owningPlayer = "Ben";
		fluffComplete();
	}
	
	private void fluffComplete()
	{
		Log.i("IKRPG","Fluff selected");
		hostActivity.fluff = fluff;
		hostActivity.nextFrag(NewCharacterActivity.FragState.DONE);
	}
}
