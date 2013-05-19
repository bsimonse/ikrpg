package com.random.captain.ikrpg.activities;

import com.random.captain.ikrpg.model.Attributes.*;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.model.BaseCharacter;
import com.random.captain.ikrpg.model.Creators.CharacterCreator;

public class MainActivity extends FragmentActivity
{
	public static final String NEW_CHARACTER = "thisIsANewCharacter";
	private static final int NEW_CHARACTER_ACTIVITY_RESULT = 1;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
		
		try
		{
			
			BaseCharacter myChar = CharacterCreator.createCharacter("Gus",Race.HUMAN, Archetype.GIFTED_FOCUSER, Career.ALCHEMIST, Career.ARCANE_MECHANIK, this);
			SkillsBundle test = myChar.skillsBundle();
			
			//Modifier<Stat> test = Modifier.onTrait(Stat.PERCEPTION,1);
			
			//StatsBundle test = new StatsBundle(Race.HUMAN.startStats());
			//test.addModifier(Modifier.onTrait(Stat.PERCEPTION, 10),"Kick-ass");
			
			Intent myIntent = new Intent();
			Log.i("IKRPG","*Gulp*");
			myIntent.putExtra("test", test);
			Log.i("IKRPG","Oh God oh God oh God");
			
			//Modifier<Stat> didItWork = myIntent.getExtras().getParcelable("test");
			//StatsBundle didItWork = myIntent.getExtras().getParcelable("test");
			SkillsBundle didItWork = myIntent.getExtras().getParcelable("test");
			
			Log.i("IKRPG","Or did it?\n"+didItWork.toString());
			
		}
		catch(Exception e)
		{
			Log.i("IKRPG", "Yeah, good try though. "+e.getMessage());
		}
    }

	public void createNewCharacterTapped(View tappee)
	{
		//Start new character activity!!
		Intent i = new Intent(this, NewCharacterActivity.class);
		startActivityForResult(i, NEW_CHARACTER_ACTIVITY_RESULT);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent i)
	{
		if(requestCode == NEW_CHARACTER_ACTIVITY_RESULT)
		{
			//It... worked?
			Log.i("IKRPG","Character... created(?)");
		}
	}
}
