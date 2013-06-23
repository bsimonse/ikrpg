package com.random.captain.ikrpg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.character.CharacterStorageService;
import com.random.captain.ikrpg.character.NewCharacterServiceActivity;
import com.random.captain.ikrpg.character.PC;
import java.util.ArrayList;
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
		
		//load existing characters
		CharacterStorageService chars = new CharacterStorageService(this);
		chars.loadCharacters(chars.getSavedCharacterNames(), PC.class, new CharacterStorageService.LoadingDelegate<PC>(){
			@Override public void charactersLoaded(Set<PC> characters)
			{
				myChars = characters;
				if(myChars != null && myChars.size() > 0)	
				{	
					new Toast(MainActivity.this).makeText(MainActivity.this, "Characters loaded!", Toast.LENGTH_SHORT).show();
					StringBuilder b = new StringBuilder("Characters loaded:\n");
					for(PC myChar : myChars.toArray(new PC[0]))
					{
						b.append(myChar.fluff().name+"\n");
					}
					Log.i("IKRPG",b.toString());
				}
				else
				{
					Log.e("IKRPG","Characters failed to load.  Sad.");
					new Toast(MainActivity.this).makeText(MainActivity.this, "Couldn't load characters!", Toast.LENGTH_SHORT).show();
				}
			}
		});
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
					Set<PC> characters = new HashSet<PC>();
					characters.add(myChar);
					new CharacterStorageService(this).saveCharacter(characters, new CharacterStorageService.SavingDelegate()
					{
						@Override public void characterSaveComplete(boolean worked)
						{
							if(worked){new Toast(MainActivity.this).makeText(MainActivity.this, "Character saved!", Toast.LENGTH_SHORT).show();}
							else{new Toast(MainActivity.this).makeText(MainActivity.this, "Character save failed.", Toast.LENGTH_SHORT).show();}
						}
					});
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
