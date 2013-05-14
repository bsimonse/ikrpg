package com.random.captain.ikrpg;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.random.captain.ikrpg.model.Attributes.Career;
import com.random.captain.ikrpg.model.Attributes.Race;
import com.random.captain.ikrpg.model.BaseCharacter;
import com.random.captain.ikrpg.model.Creators.CharacterCreator;

public class MainActivity extends Activity
{
	private BaseCharacter myChar;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		myChar = CharacterCreator.createCharacter(Race.HUMAN, Career.ALCHEMIST, Career.ALCHEMIST, this);
    }
	
	@Override
	public void onResume()
	{
		super.onResume();
		Log.i("IKRPG","Let it begin!");
	}
}
