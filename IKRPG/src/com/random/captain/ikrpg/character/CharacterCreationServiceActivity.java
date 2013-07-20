package com.random.captain.ikrpg.character;

import java.util.*;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.Pair;
import com.google.gag.annotation.remark.ShoutOutTo;
import com.random.captain.ikrpg.R;


public class CharacterCreationServiceActivity extends FragmentActivity
{
	public static String SAVED_CHARACTER = "whatDoYouDoWithADrunkenSailor";
	@ShoutOutTo("CalvinAndHobbes")
	public static String FIRST_TIME = "howManyBoardsWouldTheMongolsHoardIfTheMongolHordesGotBored";
	public static String NAME_INDEX = "IEvenLikeTheWordTeam";
	public static String NEW_CHARACTER = "thisIsABrandNewCharacter";
	public static String POST_HOOK_INDEX = "onMyGodDeaconStopIt";
	private int nameIndex=0;
	private int postHookIndex=0;
	private boolean firstTime = true;
	
	private ArrayList<zzCreateCharacterHook> postCreateHooks = new ArrayList<zzCreateCharacterHook>(15);
	private zzCreateCharacterHook.CreateHook lastFinishedHook;
	private zzBaseCharacter buildingChar;
	
	@Override
	public void onSaveInstanceState(Bundle b)
	{
		super.onSaveInstanceState(b);
		b.putParcelable(SAVED_CHARACTER, buildingChar);
		b.putBoolean(FIRST_TIME, firstTime);
		b.putInt(NAME_INDEX, nameIndex);
		b.putInt(POST_HOOK_INDEX, postHookIndex);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_new_character);
		
		if(savedInstanceState != null)
		{
			buildingChar = savedInstanceState.getParcelable(SAVED_CHARACTER);
			firstTime = savedInstanceState.getBoolean(FIRST_TIME, true);
			nameIndex = savedInstanceState.getInt(NAME_INDEX, 0);
			postHookIndex = savedInstanceState.getInt(POST_HOOK_INDEX, -1);
		}
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		if(firstTime)
		{
			firstTime = false;
			buildingChar = new zzBaseCharacter();
			nextFrag(zzCreateCharacterHook.CreateHook.START);
		}
		else
		{
			//get top frag
			FragmentManager manager = getSupportFragmentManager();
			int fragCount = manager.getBackStackEntryCount();
			if(fragCount > 0)
			{	
				FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(fragCount-1);
				final zzCreateCharacterHook topFrag = (zzCreateCharacterHook)manager.findFragmentByTag(entry.getName());
				
				topFrag.restartHook(new zzCreateCharacterHookDelegate(){
					@Override public void hookComplete(zzBaseCharacter theChar)
					{CharacterCreationServiceActivity.this.hookComplete(theChar, topFrag.getHook());}
				});
			}
			
			try
			{postCreateHooks = generateHooks(buildingChar);}
			catch(Exception e)
			{
				//huh.
			}
		}
	}
	
	public void nextFrag(zzCreateCharacterHook.CreateHook completedHook)
	{
		lastFinishedHook = completedHook;
		
		final zzCreateCharacterHook nextHook;
		final zzCreateCharacterHook.CreateHook hookType;
		final Bundle args = new Bundle();
		
		switch(completedHook)
		{
			case START:
				nextHook = new zzStaticCreateCharacterHooks.ChooseRaceFragment();
				hookType = zzCreateCharacterHook.CreateHook.RACE;
				break;
				
			case RACE:
				nextHook = new zzStaticCreateCharacterHooks.ChooseArchetypeHook();
				hookType = zzCreateCharacterHook.CreateHook.ARCHETYPE;
				break;
				
			case ARCHETYPE:
				nextHook = new zzStaticCreateCharacterHooks.ChooseCareerFragment();
				hookType = zzCreateCharacterHook.CreateHook.CAREER1;
				break;
			
			case CAREER1:
				nextHook = new zzStaticCreateCharacterHooks.ChooseCareerFragment();
				args.putBoolean(zzStaticCreateCharacterHooks.ChooseCareerFragment.SECOND_CAREER, true);
				hookType = zzCreateCharacterHook.CreateHook.CAREER2;
			break;
			
			case CAREER2:
				try
				{postCreateHooks = generateHooks(buildingChar);}
				catch(Exception e)
				{
					nextHook = null; hookType = null;
					setResult(RESULT_FIRST_USER);
					finish();
				}

				//Do postcreatehooks
				if(postCreateHooks.size() > 0)
				{
					nextFrag(zzCreateCharacterHook.CreateHook.POSTCREATE_HOOK);
					return;
				}
				else
				{
					nextHook = new zzStaticCreateCharacterHooks.ChooseFluffFragment();
					hookType = zzCreateCharacterHook.CreateHook.FLUFF;
					break;
				}
			
			case POSTCREATE_HOOK:
				//Which one, and what is left?

				if(postHookIndex >= postCreateHooks.size())
				{
					nextHook = new zzStaticCreateCharacterHooks.ChooseFluffFragment();
					hookType = zzCreateCharacterHook.CreateHook.FLUFF;
					break;
				}

				nextHook = postCreateHooks.get(postHookIndex);
				hookType = zzCreateCharacterHook.CreateHook.POSTCREATE_HOOK;
				postHookIndex++;
			break;
			
			case FLUFF: nextHook = null; hookType = null; characterComplete(); break;
			
			default:
			nextHook = null;
			hookType = null;
			break;
		}
		
		if(nextHook != null)
		{
			nextHook.setArguments(args);
			nextHook.startHook(buildingChar,
				new zzCreateCharacterHookDelegate(){
					@Override public void hookComplete(zzBaseCharacter theChar)
					{CharacterCreationServiceActivity.this.hookComplete(theChar, nextHook.getHook());}
				}, hookType);
			
			FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
			trans.replace(R.id.mainFragmentContainer, nextHook, ""+nameIndex);
			trans.addToBackStack(""+nameIndex);
			trans.commit();
			
			nameIndex++;
			if(!nextHook.hasUI()){nextFrag(hookType);}
		}
	}
	
	public void hookComplete(zzBaseCharacter pChar, zzCreateCharacterHook.CreateHook finishedHook)
	{buildingChar=pChar;nextFrag(finishedHook);}
	
	@Override public void onBackPressed()
	{
		Log.i("IKRPG","Back");
		FragmentManager manager = getSupportFragmentManager();
		
		if(manager.getBackStackEntryCount() <= 1)
		{
			firstTime = true;
			Intent i = new Intent();
			i.putExtra(NEW_CHARACTER, (zzBaseCharacter)null);
			setResult(RESULT_OK, i);
			finish();
			return;
		}
		
		//undo previous thing
		final zzCreateCharacterHook topFrag = (zzCreateCharacterHook)manager.findFragmentByTag(manager.getBackStackEntryAt(manager.getBackStackEntryCount()-1).getName());
		final zzCreateCharacterHook prevFrag = (zzCreateCharacterHook)manager.findFragmentByTag(manager.getBackStackEntryAt(manager.getBackStackEntryCount()-2).getName());
		
		//If we were on a dynamic hook, decrement
		if(topFrag.getPriority() >= 0)
		{
			postHookIndex--;
		}
		
		//Undo previous frag that we're chosing again
		//I have no idea what the best way to do this is.
		prevFrag.restartHook(new zzCreateCharacterHookDelegate(){
				@Override public void hookComplete(zzBaseCharacter theChar)
				{CharacterCreationServiceActivity.this.hookComplete(theChar, prevFrag.getHook());}
			});
		prevFrag.undoHook();
		
		manager.popBackStackImmediate();
		if(!prevFrag.hasUI())
		{onBackPressed();}
	}
	
	protected ArrayList<zzCreateCharacterHook> generateHooks(zzBaseCharacter myChar)
	{
		Collection<zzCreateCharacterHook> hookStore;
		ArrayList<zzCreateCharacterHook> finalHooks = new ArrayList<zzCreateCharacterHook>(15);
		
		if(buildingChar.race != null)
		{
			for(Pair<Stat,Integer> startStat : buildingChar.race.startStats())
			{buildingChar.baseStats.put(startStat.first, startStat.second);}
			for(Pair<Stat,Integer> maxStat : buildingChar.race.heroStats())
			{buildingChar.maxStats.put(maxStat.first, maxStat.second);}
			buildingChar.deriveStats();	//whoops.
			hookStore = buildingChar.race.postCreateHooks();
			if(hookStore != null){finalHooks.addAll(hookStore);}
		}
		
		if(buildingChar.archetype != null)
		{
			hookStore = buildingChar.archetype.postCreateHooks();
			if(hookStore!=null){finalHooks.addAll(hookStore);}
		}
		
		if(buildingChar.careers != null)
		{
			//skills
			buildingChar.setBaseSkills(buildingChar.careers);
			buildingChar.deriveSkillCheckLevels();
			
			//Careers; Abilities, spells, and post hooks
			for(Career career : buildingChar.careers)
			{
				buildingChar.abilities.addAll(career.startingAbilities());
				buildingChar.spells.addAll(career.startingSpells());
				hookStore = career.postCreateHooks();
				if(hookStore!=null){finalHooks.addAll(hookStore);}
			}
		}
		
		//advancement point hook
		finalHooks.add(new zzStaticCreateCharacterHooks.ChooseAdvancementPointsHook());
		
		//sort hooks
		Collections.sort(finalHooks, new Comparator<zzCreateCharacterHook>(){
				@Override public int compare(zzCreateCharacterHook one, zzCreateCharacterHook two)
				{return one.getPriority() - two.getPriority();}
			});
			
		return finalHooks;
	}
	
	public void characterComplete()
	{
		Intent i = new Intent();
		i.putExtra(NEW_CHARACTER, new Character(buildingChar));
		setResult(RESULT_OK, i);
		finish();
	}
}
