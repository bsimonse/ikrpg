package com.random.captain.ikrpg.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.fragments.newcharacter.ChooseArchtypeFragment;
import com.random.captain.ikrpg.fragments.newcharacter.ChooseCareerFragment;
import com.random.captain.ikrpg.fragments.newcharacter.ChooseRaceFragment;
import com.random.captain.ikrpg.model.Attributes.Archetype;
import com.random.captain.ikrpg.model.Attributes.Career;
import com.random.captain.ikrpg.model.Attributes.Race;
import com.random.captain.ikrpg.model.BaseCharacter;
import com.random.captain.ikrpg.model.Creators.CharacterCreator;
	
public class NewCharacterActivity extends FragmentActivity
{
	public static enum FragState
	{
		START,
		RACE,
		ARCHETYPE,
		CAREER1,
		CAREER2
	}
	
	public static String SECOND_CAREER = "thisIsMySecondCareer";
	
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
		nextFrag(FragState.START);
	}

	public void nextFrag(FragState currFrag)
	{
		Fragment nextFrag = null;
		if(currFrag == FragState.START)
		{
			nextFrag = new ChooseRaceFragment();
		}
		else if(currFrag == FragState.RACE)
		{
			nextFrag = new ChooseArchtypeFragment();
		}
		else if(currFrag == FragState.ARCHETYPE)
		{
			nextFrag = new ChooseCareerFragment();
		}
		else if(currFrag == FragState.CAREER1)
		{
			nextFrag = new ChooseCareerFragment();
			Bundle args = new Bundle();
			args.putBoolean(SECOND_CAREER, true);
			nextFrag.setArguments(args);
		}
		else if(currFrag == FragState.CAREER2)
		{
			//done!
			try
			{
				BaseCharacter myChar = CharacterCreator.createCharacter("Gus",race,archetype,career1,career2,this);
				Intent finished = new Intent();
				finished.putExtra(MainActivity.NEW_CHARACTER, myChar);
				setResult(RESULT_OK, finished);
				finish();
			}
			catch(Exception e)
			{
				setResult(RESULT_FIRST_USER);
				finish();
			}
		}
		
		if(nextFrag != null)
		{
			FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
			trans.replace(R.id.mainFragmentContainer, nextFrag);
			trans.addToBackStack(null);
			trans.commit();
		}
	}
	
	public void prevFrag()
	{
		
	}
}
