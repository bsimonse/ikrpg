package com.random.captain.ikrpg;

import java.util.HashSet;
import java.util.Set;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gag.annotation.remark.ShoutOutTo;
import com.random.captain.ikrpg.character.CharacterCreationServiceActivity;
import com.random.captain.ikrpg.character.CharacterStorageService;
import com.random.captain.ikrpg.character.GameCharacter;
import com.random.captain.ikrpg.util.BundleConstants;


public class MainActivity extends FragmentActivity
{
	private static final int NEW_CHARACTER_ACTIVITY_RESULT = 1;
    private ArrayAdapter<GameCharacter> myListAdapter;
	private Set<GameCharacter> myChars = new HashSet<GameCharacter>();
	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
		
		//LootJSONMaker.getSomeJSON();
		
		//Log.i("IKRPG","Running tests!");
		//TestSuite.runSuite();
		
		//Cheating!
		/*Character Pascal = CharacterSheetService.getPascal();
		CharacterSheetService.drawCharacterSheet(Pascal, new CharacterSheetService.Delegate(){
				@Override
				public void characterSheetComplete(boolean success)
				{
					//Toast!
					if(success){Toast.makeText(MainActivity.this, "Pascal printed!", Toast.LENGTH_SHORT).show();}
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
		CharacterStorageService.loadCharacters(CharacterStorageService.getSavedCharacterNames(), GameCharacter.class, new CharacterStorageService.LoadingDelegate<GameCharacter>(){
				@Override public void charactersLoaded(Set<GameCharacter> characters)
				{
					ProgressBar proBar = (ProgressBar)findViewById(R.id.loadingCharacterSpinner);
					proBar.setVisibility(View.GONE);

					myChars = characters;
					if(myChars != null && myChars.size() > 0)	
					{	
						ListView characterList = (ListView)findViewById(R.id.characterList);
						characterList.setVisibility(View.VISIBLE);
						myListAdapter = new ArrayAdapter<GameCharacter>(MainActivity.this, android.R.layout.simple_list_item_1, myChars.toArray(new GameCharacter[0]));
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
			GameCharacter whichChar = myListAdapter.getItem(position);
			Intent i = new Intent(MainActivity.this, CharacterHomeActivity.class);
			i.putExtra(BundleConstants.CHARACTER,whichChar.toJson());
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
				final GameCharacter myChar = GameCharacter.fromJson(i.getExtras().getString(BundleConstants.CHARACTER));
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
