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
	private int nameIndex=0;
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
		}
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		Log.i("IKRPG","Rotated");
		if(firstTime)
		{
			Log.i("IKRPG","This is the first time.");
			firstTime = false;
			buildingChar = new zzBaseCharacter();
			nextFrag(zzCreateCharacterHook.CreateHook.START);
		}
		else
		{
			//get top frag
			Log.i("IKRPG","This is NOT the first time.");
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
				{createCharacter();}
				catch(Exception e)
				{
					//Log.e("IKRPG","Character couldn't legally be built! "+e.getMessage());
					nextHook = null; hookType = null;
					setResult(RESULT_FIRST_USER);
					finish();
				}

				//Do postcreatehooks
				if(postCreateHooks.size() > 0)
				{
					zzCreateCharacterHook.CreateHook.POSTCREATE_HOOK.setWhich(-1);
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
				final int nextPostHookCount = completedHook.which()+1;

				if(nextPostHookCount >= postCreateHooks.size())
				{
					nextHook = new zzStaticCreateCharacterHooks.ChooseFluffFragment();
					hookType = zzCreateCharacterHook.CreateHook.FLUFF;
					break;
				}

				nextHook = postCreateHooks.get(nextPostHookCount);	
				hookType = zzCreateCharacterHook.CreateHook.POSTCREATE_HOOK;
				hookType.setWhich(nextPostHookCount);
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
			Log.i("IKRPG","Pushing stack "+nameIndex);
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
			int which = zzCreateCharacterHook.CreateHook.POSTCREATE_HOOK.which()-1;
			zzCreateCharacterHook.CreateHook.POSTCREATE_HOOK.setWhich(which);
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
	
	protected void createCharacter() //throws CharacterNotValidException
	{
		Collection<zzCreateCharacterHook> aHooks;
		postCreateHooks = new ArrayList<zzCreateCharacterHook>(15);
		
		for(Pair<Stat,Integer> startStat : buildingChar.race.startStats())
		{buildingChar.baseStats.put(startStat.first, startStat.second);}
		for(Pair<Stat,Integer> maxStat : buildingChar.race.heroStats())
		{buildingChar.maxStats.put(maxStat.first, maxStat.second);}
		buildingChar.deriveStats();
		
		aHooks = buildingChar.race.postCreateHooks();
		if(aHooks != null){postCreateHooks.addAll(aHooks);}
		
		aHooks = buildingChar.archetype.postCreateHooks();
		if(aHooks!=null){postCreateHooks.addAll(aHooks);}
		
		//skills
		buildingChar.setBaseSkills(buildingChar.careers);
		buildingChar.deriveSkillCheckLevels();
		
		//Careers; Abilities, spells, and post hooks
		for(Career career : buildingChar.careers)
		{
			buildingChar.abilities.addAll(career.startingAbilities());
			buildingChar.spells.addAll(career.startingSpells());
			aHooks = career.postCreateHooks();
			if(aHooks!=null){postCreateHooks.addAll(aHooks);}
		}
		
		//advancement point hook
		postCreateHooks.add(new zzStaticCreateCharacterHooks.ChooseAdvancementPointsHook());
		
		//sort hooks
		Collections.sort(postCreateHooks, new Comparator<zzCreateCharacterHook>(){
				@Override public int compare(zzCreateCharacterHook one, zzCreateCharacterHook two)
				{return one.getPriority() - two.getPriority();}
			});
	}
	
	public void characterComplete()
	{
		Intent i = new Intent();
		i.putExtra(NEW_CHARACTER, new Character(buildingChar));
		setResult(RESULT_OK, i);
		finish();
	}
}
