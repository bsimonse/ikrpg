package com.random.captain.ikrpg;

import com.random.captain.ikrpg.character.*;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.random.captain.ikrpg.R;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends FragmentActivity
{
	private static final int NEW_CHARACTER_ACTIVITY_RESULT = 1;
    
	private Set<PC> myChars = new HashSet<PC>();
	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
		
		//try loading character?!
		new LoadCharacterServiceTask<PC>(this, PC.class, new LoadCharacterServiceTask.Delegate<PC>(){
			@Override public void charactersLoaded(Set<PC> characters)
			{
				myChars = characters;
				if(characters != null && characters.size() > 0)	
				{	
					Log.i("IKRPG","Holy crap.");
					new Toast(MainActivity.this).makeText(MainActivity.this, "Character... loaded!?", Toast.LENGTH_SHORT).show();
					Log.i("IKRPG",myChars.toArray(new PC[0])[0].toString());
				}
				else
				{
					new Toast(MainActivity.this).makeText(MainActivity.this, "Couldn't load Billy", Toast.LENGTH_SHORT).show();
					Log.i("IKRPG","Billy doesn't exist yet.");
				}
			}
		}).execute("Billy");
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
					
					//save character
					new SaveCharacterServiceTask<PC>(this, new SaveCharacterServiceTask.Delegate()
					{
						@Override public void characterSaveComplete(boolean worked)
						{
							if(worked){new Toast(MainActivity.this).makeText(MainActivity.this, "Character saved!", Toast.LENGTH_SHORT).show();}
							else{new Toast(MainActivity.this).makeText(MainActivity.this, "Character save failed.", Toast.LENGTH_SHORT).show();}
						}
					}).execute(myChar);
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
