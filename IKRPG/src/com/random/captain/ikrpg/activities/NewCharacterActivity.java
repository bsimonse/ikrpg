package com.random.captain.ikrpg.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.model.Attributes.Archetype;
import com.random.captain.ikrpg.model.Attributes.Career;
import com.random.captain.ikrpg.model.Attributes.Race;

public class NewCharacterActivity extends FragmentActivity
{
	private Fragment mainFrag;
	
	//String pName, Race pRace, Archetype pArchetype, Career career1, Career career2, Context appContext
	
	public Race race;
	public Archetype archetype;
	public Career career1;
	public Career career2;
	public String name;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_new_character);
		
		mainFrag = getSupportFragmentManager().findFragmentById(R.id.mainFragment);
	}
	
	public void nextFrag()
	{
		Log.i("IKRPG","This is where I'd push the next one.");
	}
	
	public void prevFrag()
	{
		
	}
}
