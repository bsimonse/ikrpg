package com.random.captain.ikrpg;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.random.captain.ikrpg.model.Attributes.Archetype;
import com.random.captain.ikrpg.model.Attributes.Career;
import com.random.captain.ikrpg.model.Attributes.Race;
import com.random.captain.ikrpg.model.BaseCharacter;
import com.random.captain.ikrpg.model.Creators.CharacterCreator;
import com.random.captain.ikrpg.model.Creators.CharacterNotValidException;

public class MainActivity extends Activity
{
	private BaseCharacter myChar;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		try
		{
			myChar = CharacterCreator.createCharacter("Gus", Race.HUMAN, Archetype.GIFTED_WILL_WEAVER, Career.ALCHEMIST, Career.ARCANE_MECHANIK, this);
		}
		catch(CharacterNotValidException e)
		{
			Log.i("IKRPG","Character not valid! "+e.getMessage());
		}
    }
	
	@Override
	public void onResume()
	{
		super.onResume();
		Log.i("IKRPG","Let it begin!");
		Log.i("IKRPG",myChar.toString());
	}
}
