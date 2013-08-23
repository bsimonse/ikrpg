package com.random.captain.ikrpg;

import android.view.*;
import android.widget.*;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.AdapterView.OnItemClickListener;
import com.google.gag.annotation.remark.ShoutOutTo;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.character.Character;
import com.random.captain.ikrpg.character.CharacterCreationServiceActivity;
import com.random.captain.ikrpg.character.CharacterStorageService;
import java.util.HashSet;
import java.util.Set;


public class MainActivity extends FragmentActivity
{
	public static final String PC_EXTRA = "IKRPG_PC";
	private static final int NEW_CHARACTER_ACTIVITY_RESULT = 1;
    private ArrayAdapter<Character> myListAdapter;
	private Set<Character> myChars = new HashSet<Character>();
	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
		
		//Cheating!
		/*PC Pascal = CharacterSheetService.getPascal();
		CharacterSheetService s = new CharacterSheetService(this);
		s.drawCharacterSheet(Pascal, new CharacterSheetService.Delegate(){
				@Override
				public void characterSheetComplete(boolean success)
				{
					//Toast!
					if(success){new Toast(MainActivity.this).makeText(MainActivity.this, "Pascal printed!", Toast.LENGTH_SHORT).show();}
				}
			});*/
    }

	@Override
	public void onResume()
	{
		super.onResume();
		reloadCharacters();
	}
	
	private void reloadCharacters()
	{
		ProgressBar proBar = (ProgressBar)findViewById(R.id.loadingCharacterSpinner);
		proBar.setVisibility(View.VISIBLE);

		//(re)load existing characters
		CharacterStorageService.loadCharacters(CharacterStorageService.getSavedCharacterNames(), Character.class, new CharacterStorageService.LoadingDelegate<Character>(){
				@Override public void charactersLoaded(Set<Character> characters)
				{
					ProgressBar proBar = (ProgressBar)findViewById(R.id.loadingCharacterSpinner);
					proBar.setVisibility(View.GONE);

					myChars = characters;
					if(myChars != null && myChars.size() > 0)	
					{	
						ListView characterList = (ListView)findViewById(R.id.characterList);
						characterList.setVisibility(View.VISIBLE);
						myListAdapter = new ArrayAdapter<Character>(MainActivity.this, android.R.layout.simple_list_item_1, myChars.toArray(new Character[0]));
						characterList.setAdapter(myListAdapter);
						characterList.setOnItemClickListener(characterClicked);
					}
					else
					{
						Log.e("IKRPG","Characters failed to load.  Sad.");
						Toast.makeText(MainActivity.this, "Couldn't load characters!", Toast.LENGTH_SHORT).show();
					}
				}
			});
	}
	
	private OnItemClickListener characterClicked = new OnItemClickListener()
	{
		@Override
		public void onItemClick(AdapterView<?> adapter, View v, int position, long huh)
		{
			Character whichChar = (Character)myListAdapter.getItem(position);
			Intent i = new Intent(MainActivity.this, CharacterHomeActivity.class);
			i.putExtra(PC_EXTRA,whichChar);
			startActivity(i);
		}
	};
	
	
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
				Intent i = new Intent(this, CharacterCreationServiceActivity.class);
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
				final Character myChar = i.getExtras().getParcelable(CharacterCreationServiceActivity.NEW_CHARACTER);
				if(myChar != null)
				{
					Log.i("IKRPG","Character created!");
					
					//save character
					CharacterStorageService.saveCharacter(myChar, new CharacterStorageService.SavingDelegate()
					{
						@Override public void characterSaved(boolean worked)
						{
							if(worked)
							{
								Toast.makeText(MainActivity.this, "Character saved!", Toast.LENGTH_SHORT).show();
								reloadCharacters();
							}
							else
							{Toast.makeText(MainActivity.this, "Character save failed.", Toast.LENGTH_SHORT).show();}
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
