package com.random.captain.ikrpg;
import android.view.*;
import android.widget.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView.OnItemClickListener;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.character.CharacterSheetService;
import com.random.captain.ikrpg.character.CharacterStorageService;
import com.random.captain.ikrpg.character.GameCharacter;
import com.random.captain.ikrpg.util.BundleConstants;

public class CharacterHomeActivity extends Activity
{
	private GameCharacter mainChar;
	private ListView myList;
	private ArrayAdapter<Actions> myAdapter;
	private int getSumEXP = 1234;
	
	private enum Actions
	{
		GAIN_EXP("Gain EXP"), GEAR("Manage Gear"), BUFF("Manage Buffs"), SKILL_CHECK("Skill Check");
		
		private Actions(String pName)
		{name=pName;}
		
		private String name;
		@Override public String toString(){return name;}
	}
	
	@Override
	public void onCreate(Bundle b)
	{
        super.onCreate(b);
        setContentView(R.layout.act_character_home);
		
		mainChar = GameCharacter.fromJson(getIntent().getExtras().getString(BundleConstants.CHARACTER));
		if(mainChar == null){setResult(RESULT_FIRST_USER); finish(); return;}
		
		myList = (ListView)findViewById(R.id.char_home_actions);
		myAdapter = new ArrayAdapter<Actions>(this, R.layout.simple_elan_list_item, Actions.values());
		myList.setAdapter(myAdapter);
		myList.setOnItemClickListener(listener);
		setTitle(mainChar.fluff().name);
	}
	
	private OnItemClickListener listener = new OnItemClickListener()
	{
		@Override public void onItemClick(AdapterView<?> av, View v, int which, long um)
		{
			Actions action = Actions.values()[which];
			
			switch(action)
			{
				case GAIN_EXP:
					Intent i = new Intent(CharacterHomeActivity.this, CharacterAdvancementPointGain.class);
					i.putExtra(BundleConstants.CHARACTER, mainChar.toJson());
					startActivityForResult(i, getSumEXP);
					return;
				case GEAR:
				break;
				case BUFF:
				break;
				case SKILL_CHECK:
				break;
				
				default:break;
			}
		}
	};
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode == getSumEXP && resultCode == RESULT_OK)
		{
			Log.i("IKRPG","Good, it thinks things went okay.");
			mainChar = GameCharacter.fromJson(data.getExtras().getString(BundleConstants.CHARACTER));
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu pMenu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.character_menu, pMenu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem pItem)
	{
		switch(pItem.getItemId())
		{
			case R.id.print_character:
				
				CharacterSheetService.drawCharacterSheet(mainChar, new CharacterSheetService.Delegate(){
					@Override public void characterSheetComplete(boolean worked)
					{
						if(worked)
						{Toast.makeText(CharacterHomeActivity.this, mainChar.fluff().name+" printed!", Toast.LENGTH_SHORT).show();}
						else
						{Toast.makeText(CharacterHomeActivity.this, "Couldn't print character sheet.", Toast.LENGTH_LONG).show();}
					}
				});
				
				return true;
			case R.id.delete_character:
			
				//Are you sure?  Of course you're sure.
				//Or... I could
				//TODO: Prompt user
				CharacterStorageService.deleteCharacter(mainChar.fluff().name, new CharacterStorageService.DeleteDelegate(){
					@Override public void characterDeleted(boolean worked)
					{
						if(worked)
						{
							Toast.makeText(CharacterHomeActivity.this, mainChar.fluff().name+" deleted!", Toast.LENGTH_SHORT).show();
							CharacterHomeActivity.this.finish();
						}
						else
						{Toast.makeText(CharacterHomeActivity.this, "Couldn't... delete character?", Toast.LENGTH_LONG).show();}
					}
				});
				return true;
			default:
				return super.onOptionsItemSelected(pItem);
		}
	}
}
