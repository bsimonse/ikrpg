package com.random.captain.ikrpg.character;

import com.random.captain.ikrpg.character.*;
import java.util.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.Pair;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.character.Character;
import com.random.captain.ikrpg.gear.Loot;
import com.random.captain.ikrpg.gear.LootPack;

public class CharacterAdvancementServiceActivity extends FragmentActivity
{
	private static final String BOON_INDEX = "ThatIsTheUltimateQuestion";
	private static final String BOON = "ToSeekTheHolyGrail";
	public static final String THE_CHAR = "AnybodyWantAnySheep";
	public static final String START_EXP = "readysetgo";
	public static final String END_EXP = "darnthat'stheend";
	private ArrayList<zzCharacterAdvancementBoon> boons;
	
	private int boonIndex;
	private zzBaseCharacter buildingChar;
	
	@Override
	public void onSaveInstanceState(Bundle b)
	{
		super.onSaveInstanceState(b);
		b.putInt(BOON_INDEX, boonIndex);
		b.putSerializable(BOON,boons);
		b.putParcelable(THE_CHAR,buildingChar);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_new_character);

		if(savedInstanceState != null)
		{
			boonIndex = savedInstanceState.getInt(BOON_INDEX, -1);
			boons = (ArrayList<zzCharacterAdvancementBoon>)savedInstanceState.getSerializable(BOON);
			buildingChar = savedInstanceState.getParcelable(THE_CHAR);
		}
	}

	@Override
	public void onResume()
	{
		super.onResume();
		
		Bundle b = getIntent().getExtras();
		if(buildingChar == null)
		{
			zzBaseCharacter theChar = b.getParcelable(THE_CHAR);
			buildingChar = theChar.fromJson(theChar.toJson()); //lol, dumb man's copy constructor
		}
		
		if(boons == null)
		{
			
			boons = generateBoons(b.getInt(START_EXP,0),b.getInt(END_EXP,50));
			nextFrag();
		}
		else
		{
			//get top frag
			FragmentManager manager = getSupportFragmentManager();
			int fragCount = manager.getBackStackEntryCount();
			if(fragCount > 0)
			{	
				FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(fragCount-1);
				final zzCharacterAdvancementBoon topFrag = (zzCharacterAdvancementBoon)manager.findFragmentByTag(entry.getName());

				topFrag.restartBoon(new zzCharacterAdvancementBoonDelegate(){
						@Override public void hookComplete(zzBaseCharacter theChar)
						{CharacterAdvancementServiceActivity.this.hookComplete(theChar);}
					});
			}
			
			//else, huh.
		}
	}

	public void nextFrag()
	{	
		if(boonIndex >= boons.size())
		{
			characterComplete();
			return;
		}
		
		final zzCharacterAdvancementBoon nextBoon;
		final Bundle args = new Bundle();

		nextBoon = boons.get(boonIndex);

		if(nextBoon != null)
		{
			nextBoon.setArguments(args);
			nextBoon.startBoon(buildingChar,
				new zzCharacterAdvancementBoonDelegate(){
					@Override public void hookComplete(zzBaseCharacter theChar)
					{CharacterAdvancementServiceActivity.this.hookComplete(theChar);}
				});

			String boonName = "boon_"+boonIndex;
			FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
			trans.replace(R.id.mainFragmentContainer, nextBoon, boonName);
			trans.addToBackStack(boonName);
			trans.commit();

			boonIndex++;
		}
	}

	public void hookComplete(zzBaseCharacter pChar)
	{
		buildingChar = pChar;
		nextFrag();
	}

	@Override public void onBackPressed()
	{
		FragmentManager manager = getSupportFragmentManager();

		if(manager.getBackStackEntryCount() <= 1)
		{
			Intent i = new Intent();
			i.putExtra("advanced!", (zzBaseCharacter)null);
			setResult(RESULT_OK, i);
			finish();
			return;
		}

		//undo previous thing
		final zzCharacterAdvancementBoon prevFrag = (zzCharacterAdvancementBoon)manager.findFragmentByTag(manager.getBackStackEntryAt(manager.getBackStackEntryCount()-2).getName());

		boonIndex--;
			
		//Undo previous frag that we're chosing again
		prevFrag.undoHook();
		//I have no idea what the best way to do this is.
		prevFrag.restartBoon(new zzCharacterAdvancementBoonDelegate(){
				@Override public void hookComplete(zzBaseCharacter theChar)
				{CharacterAdvancementServiceActivity.this.hookComplete(theChar);}
			});
		
		manager.popBackStackImmediate();
	}

	public ArrayList<zzCharacterAdvancementBoon> generateBoons(int startXp, int endXp)
	{
		//Alright, let the fun begin!
		boons = new ArrayList<zzCharacterAdvancementBoon>(15);
		boons.add(new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment());
		return boons;
	}
	
	public void characterComplete()
	{
		Log.i("IKRPG","YOU DID IT!");
		finish();
	}
}
