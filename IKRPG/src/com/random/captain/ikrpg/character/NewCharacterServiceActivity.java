package com.random.captain.ikrpg.character;

import android.support.v4.app.*;
import java.util.*;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import com.random.captain.ikrpg.R;
	
public class NewCharacterServiceActivity extends FragmentActivity
{
	public static String NEW_CHARACTER = "thisIsABrandNewCharacter";
	private int nameIndex;
	
	private static enum CreateHook
	{
		START,RACE,ARCHETYPE,CAREER1,CAREER2,POSTCREATE_HOOK,FLUFF,DONE;
		
		private int which;
		public void setWhich(int pWhich){which=pWhich;}
	}
	
	private ArrayList<zzCreateCharacterHook> postCreateHooks = new ArrayList<zzCreateCharacterHook>(15);
	private CreateHook lastFinishedHook;
	private zzBaseCharacter buildingChar;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_new_character);
		
		buildingChar = new zzBaseCharacter();
		nextFrag(CreateHook.START);
	}

	public void nextFrag(CreateHook completedHook)
	{
		lastFinishedHook = completedHook;
		
		final zzCreateCharacterHook nextHook;
		final CreateHook hookType;
		final Bundle args = new Bundle();
		
		switch(completedHook)
		{
			case START:
				nextHook = new ChooseRaceFragment();
				hookType = CreateHook.RACE;
				break;
				
			case RACE:
				nextHook = new ChooseArchetypeHook();
				hookType = CreateHook.ARCHETYPE;
				break;
				
			case ARCHETYPE:
				nextHook = new ChooseCareerFragment();
				hookType = CreateHook.CAREER1;
				break;
			
			case CAREER1:
				nextHook = new ChooseCareerFragment();
				args.putBoolean(ChooseCareerFragment.SECOND_CAREER, true);
				hookType = CreateHook.CAREER2;
			break;
			
			case CAREER2:
				try
				{createCharacter();}
				catch(Exception e)
				{
					Log.e("IKRPG","Character couldn't legally be built! "+e.getMessage());
					nextHook = null; hookType = null;
					setResult(RESULT_FIRST_USER);
					finish();
				}

				//Do postcreatehooks
				if(postCreateHooks.size() > 0)
				{
					CreateHook.POSTCREATE_HOOK.which = -1;
					nextFrag(CreateHook.POSTCREATE_HOOK);
					return;
				}
				else
				{
					nextHook = new ChooseFluffFragment();
					hookType = CreateHook.FLUFF;
					break;
				}
			
			case POSTCREATE_HOOK:
				//Which one, and what is left?
				final int nextPostHookCount = completedHook.which+1;

				if(nextPostHookCount >= postCreateHooks.size())
				{
					nextHook = new ChooseFluffFragment();
					hookType = CreateHook.FLUFF;
					break;
				}

				nextHook = postCreateHooks.get(nextPostHookCount);	
				hookType = CreateHook.POSTCREATE_HOOK;
				hookType.which = nextPostHookCount;
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
					@Override public void hookComplete()
					{NewCharacterServiceActivity.this.hookComplete(hookType);}
				});
			
			FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
			trans.replace(R.id.mainFragmentContainer, nextHook ,""+nameIndex);
			trans.addToBackStack(""+nameIndex);
			trans.commit();
			
			nameIndex++;
			if(!nextHook.hasUI()){nextFrag(hookType);}
		}
	}
	
	public void hookComplete(CreateHook finishedHook)
	{nextFrag(finishedHook);}
	
	@Override public void onBackPressed()
	{
		FragmentManager manager = getSupportFragmentManager();
		
		if(manager.getBackStackEntryCount() <= 1)
		{
			Intent i = new Intent();
			i.putExtra(NEW_CHARACTER, (zzBaseCharacter)null);
			setResult(RESULT_OK, i);
			finish();
			return;
		}
		
		//undo previous thing
		zzCreateCharacterHook topFrag = (zzCreateCharacterHook)manager.findFragmentByTag(manager.getBackStackEntryAt(manager.getBackStackEntryCount()-1).getName());
		zzCreateCharacterHook prevFrag = (zzCreateCharacterHook)manager.findFragmentByTag(manager.getBackStackEntryAt(manager.getBackStackEntryCount()-2).getName());
		
		//If we were on a dynamic hook, decrement
		if(topFrag.getPriority() >= 0){CreateHook.POSTCREATE_HOOK.which--;}
		
		//Undo previous frag that we're chosing again
		prevFrag.undoHook();
		
		manager.popBackStackImmediate();
		if(!prevFrag.hasUI())
		{onBackPressed();}
	}
	
	public void createCharacter() //throws CharacterNotValidException
	{
		zzCreateCharacterHook aHook;
		postCreateHooks = new ArrayList<zzCreateCharacterHook>(15);
		
		//Race
		//Mostly for stats, and post-create bonuses
		buildingChar.statsBundle = new zzStatsBundle(buildingChar.race.startStats(), buildingChar.race.heroStats());
		aHook = buildingChar.race.postCreateHook();
		if(aHook != null){postCreateHooks.add(aHook);}
		
		//Archetype
		//Pick a bonus
		aHook = buildingChar.archetype.postCreateHook();
		if(aHook!=null){postCreateHooks.add(aHook);}
		
		//skills
		buildingChar.skillsBundle = new zzSkillsBundle(buildingChar.statsBundle, buildingChar.careers);
		
		//Careers; Abilities, spells, and post hooks
		for(Career career : buildingChar.careers)
		{
			buildingChar.abilities.addAll(career.startingAbilities());
			buildingChar.spells.addAll(career.startingSpells());
			aHook = career.postCreateHook();
			if(aHook!=null){postCreateHooks.add(aHook);}
		}
		
		//advancement point hook
		postCreateHooks.add(new ChooseAdvancementPointsHook());
		
		//sort hooks
		Collections.sort(postCreateHooks, new Comparator<zzCreateCharacterHook>(){
				@Override public int compare(zzCreateCharacterHook one, zzCreateCharacterHook two)
				{return one.getPriority() - two.getPriority();}
			});
	}
	
	public void characterComplete()
	{
		Intent i = new Intent();
		i.putExtra(NEW_CHARACTER, new PC(buildingChar));
		setResult(RESULT_OK, i);
		finish();
	}
}
