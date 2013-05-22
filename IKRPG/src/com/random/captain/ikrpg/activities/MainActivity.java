package com.random.captain.ikrpg.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.model.BaseCharacter;

public class MainActivity extends FragmentActivity
{
	private static final int NEW_CHARACTER_ACTIVITY_RESULT = 1;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
		
		/*try
		{
			
			BaseCharacter myChar = CharacterCreator.createCharacter("Gus",Race.HUMAN, Archetype.GIFTED_FOCUSER, Career.ALCHEMIST, Career.ARCANE_MECHANIK, this);
			//SkillsBundle test = myChar.skillsBundle();
			
			//Modifier<Stat> test = Modifier.onTrait(Stat.PERCEPTION,1);
			
			//StatsBundle test = new StatsBundle(Race.HUMAN.startStats());
			//test.addModifier(Modifier.onTrait(Stat.PERCEPTION, 10),"Kick-ass");
			
			Intent myIntent = new Intent();
			Log.i("IKRPG","*Gulp*");
			myIntent.putExtra("test", myChar);
			Log.i("IKRPG","Oh God oh God oh God");
			
			//Modifier<Stat> didItWork = myIntent.getExtras().getParcelable("test");
			//StatsBundle didItWork = myIntent.getExtras().getParcelable("test");
			//SkillsBundle didItWork = myIntent.getExtras().getParcelable("test");
			BaseCharacter didItWork = myIntent.getExtras().getParcelable("test");
			
			Log.i("IKRPG","Or did it?\n"+didItWork.toString());
			
		}
		catch(Exception e)
		{
			Log.i("IKRPG", "Yeah, good try though. "+e.getMessage());
		}*/
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
			if(resultCode == RESULT_OK)
			{
				BaseCharacter myChar = i.getExtras().getParcelable(NewCharacterActivity.NEW_CHARACTER);
				Log.i("IKRPG","Character... created(?)");
				if(myChar != null)
				{Log.i("IKRPG",myChar.toString());}
				else
				{Log.i("IKRPG","Didn't come back...");}
			}
			else
			{
				Log.i("IKRPG","Character not created... *sad face*");
			}
		}
	}
}
