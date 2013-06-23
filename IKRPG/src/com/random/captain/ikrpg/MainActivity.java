package com.random.captain.ikrpg;

import android.view.*;
import android.widget.*;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.gag.annotation.remark.ShoutOutTo;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.character.CharacterStorageService;
import com.random.captain.ikrpg.character.NewCharacterServiceActivity;
import com.random.captain.ikrpg.character.PC;
import java.util.HashSet;
import java.util.Set;


public class MainActivity extends FragmentActivity
{
	private static final int NEW_CHARACTER_ACTIVITY_RESULT = 1;
    private ArrayAdapter myListAdapter;
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
				ProgressBar proBar = (ProgressBar)findViewById(R.id.loadingCharacterSpinner);
				proBar.setVisibility(View.GONE);
				
				myChars = characters;
				if(myChars != null && myChars.size() > 0)	
				{	
					ListView characterList = (ListView)findViewById(R.id.characterList);
					characterList.setVisibility(View.VISIBLE);
					myListAdapter = new ArrayAdapter<PC>(MainActivity.this, android.R.layout.simple_list_item_1, myChars.toArray(new PC[0]));
					characterList.setAdapter(myListAdapter);
				}
				else
				{
					Log.e("IKRPG","Characters failed to load.  Sad.");
					new Toast(MainActivity.this).makeText(MainActivity.this, "Couldn't load characters!", Toast.LENGTH_SHORT).show();
				}
			}
		});
    }

	@ShoutOutTo("Android Docs")
	@Override
	public boolean onCreateOptionsMenu(Menu pMenu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, pMenu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem pItem)
	{
		switch(pItem.getItemId())
		{
			case R.id.new_character:
				//Start new character activity!!
				Intent i = new Intent(this, NewCharacterServiceActivity.class);
				startActivityForResult(i, NEW_CHARACTER_ACTIVITY_RESULT);
				return true;
			default:
				return super.onOptionsItemSelected(pItem);
		}
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
					myChars.add(myChar);
					Set<PC> characters = new HashSet<PC>();
					characters.add(myChar);
					new CharacterStorageService(this).saveCharacter(characters, new CharacterStorageService.SavingDelegate()
					{
						@Override public void characterSaveComplete(boolean worked)
						{
							if(worked){new Toast(MainActivity.this).makeText(MainActivity.this, "Character saved!", Toast.LENGTH_SHORT).show();}
							else{new Toast(MainActivity.this).makeText(MainActivity.this, "Character save failed.", Toast.LENGTH_SHORT).show();}
							
							ListView characterList = (ListView)findViewById(R.id.characterList);
							myListAdapter = new ArrayAdapter<PC>(MainActivity.this, android.R.layout.simple_list_item_1, myChars.toArray(new PC[0]));
							characterList.setAdapter(myListAdapter);
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
