package com.random.captain.ikrpg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.character.CharacterSheetServiceTask;
import com.random.captain.ikrpg.character.NewCharacterServiceActivity;
import com.random.captain.ikrpg.character.PC;
import com.google.gson.*;
import com.random.captain.ikrpg.character.SkillEnum;

public class MainActivity extends FragmentActivity
{
	private static final int NEW_CHARACTER_ACTIVITY_RESULT = 1;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
		
		//try
		//{
			//PC me = CharacterSheetServiceTask.getDummyCharacter();
			//CharacterSheetServiceTask task = new CharacterSheetServiceTask(this);
			//task.execute(me);
			
			//String jSON = me.toJson();
			//Log.i("IKRPG",jSON);
			//PC other = PC.fromJson(jSON);
			//Log.i("IKRPG",other.toString());
			
			//Log.i("IKRPG","It worked. Kinda.");
		//}
		//catch(Exception e)
		//{
		//	Log.i("IKRPG", "Yeah, good try though. "+e.getMessage());
		//}
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
					//Log.i("IKRPG",myChar.toString());
					
					String json = myChar.toJson();
					PC other = PC.fromJson(json);
					Log.i("IKRPG",json);
					Log.i("IKRPG",other.toString());
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
