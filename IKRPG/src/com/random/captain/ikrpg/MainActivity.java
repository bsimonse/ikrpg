package com.random.captain.ikrpg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.character.PC;
import com.random.captain.ikrpg.character.NewCharacterServiceActivity;
import com.random.captain.ikrpg.character.CharacterSheetServiceTask;

public class MainActivity extends FragmentActivity
{
	private static final int NEW_CHARACTER_ACTIVITY_RESULT = 1;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
		
		try
		{
			PC me = CharacterSheetServiceTask.getDummyCharacter();
			
			CharacterSheetServiceTask task = new CharacterSheetServiceTask(this);
			task.execute(me);
		}
		catch(Exception e)
		{
			Log.i("IKRPG", "Yeah, good try though. "+e.getMessage());
		}
    }

	public void createNewCharacterTapped(View tappee)
	{
		//Start new character activity!!
		Intent i = new Intent(this, NewCharacterServiceActivity.class);
		startActivityForResult(i, NEW_CHARACTER_ACTIVITY_RESULT);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent i)
	{
		if(requestCode == NEW_CHARACTER_ACTIVITY_RESULT)
		{
			if(resultCode == RESULT_OK)
			{
				PC myChar = i.getExtras().getParcelable(NewCharacterServiceActivity.NEW_CHARACTER);
				if(myChar != null)
				{
					Log.i("IKRPG","Character created!");
					Log.i("IKRPG",myChar.toString());
				}
				else
				{Log.i("IKRPG","Character didn't come back...");}
			}
			else
			{
				Log.i("IKRPG","Character not created... *sad face*");
			}
		}
	}
}
