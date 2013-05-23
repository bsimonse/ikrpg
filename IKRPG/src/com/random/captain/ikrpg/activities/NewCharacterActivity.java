package com.random.captain.ikrpg.activities;

import com.random.captain.ikrpg.fragments.newcharacter.*;
import com.random.captain.ikrpg.model.Attributes.*;
import java.util.*;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.Pair;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.model.BaseCharacter;
import com.random.captain.ikrpg.model.Creators.PostCreateHook;
import com.random.captain.ikrpg.model.Creators.PostCreateHookDelegate;
	
public class NewCharacterActivity extends FragmentActivity implements PostCreateHookDelegate
{
	public static String NEW_CHARACTER = "thisIsABrandNewCharacter";
	public static enum FragState
	{
		START,
		RACE,
		ARCHETYPE,
		CAREER1,
		CAREER2,
		POSTCREATE_HOOK,
		FLUFF,
		DONE;
		
		//private FragState(){}
		
		private int which;
		public void setWhich(int pWhich){which=pWhich;}
	}
	
	public static String SECOND_CAREER = "thisIsMySecondCareer";
	
	public Race race;
	public Archetype archetype;
	public Career career1;
	public Career career2;
	public Fluff fluff;
	
	private Set<Career> myCareers = new HashSet<Career>(2);
	private StatsBundle myStats;
	private SkillsBundle mySkills;
	private Set<Ability> myAbilities = new HashSet<Ability>(10);
	private Set<Spell> mySpells = new HashSet<Spell>(10);
	private ArrayList<PostCreateHook> postCreateHooks = new ArrayList<PostCreateHook>(15);
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_new_character);
		nextFrag(FragState.START);
	}

	public void nextFrag(FragState currFrag)
	{
		Fragment nextFrag = null;
		if(currFrag == FragState.START)
		{
			nextFrag = new ChooseRaceFragment();
		}
		else if(currFrag == FragState.RACE)
		{
			nextFrag = new ChooseArchtypeFragment();
		}
		else if(currFrag == FragState.ARCHETYPE)
		{
			nextFrag = new ChooseCareerFragment();
		}
		else if(currFrag == FragState.CAREER1)
		{
			nextFrag = new ChooseCareerFragment();
			Bundle args = new Bundle();
			args.putBoolean(SECOND_CAREER, true);
			nextFrag.setArguments(args);
		}
		else if(currFrag == FragState.CAREER2)
		{
			//are there any other questions?
			try
			{createCharacter();}
			catch(Exception e)
			{
				Log.e("IKRPG","Character couldn't legally be built! "+e.getMessage());
				setResult(RESULT_FIRST_USER);
				finish();
			}
			
			//Do postcreatehooks
			if(postCreateHooks.size() > 0)
			{
				//start with first hook
				Log.i("IKRPG","Starting post create hooks");
				FragState hookStart = FragState.POSTCREATE_HOOK;
				hookStart.which = -1;
				nextFrag(hookStart);return;
			}
			else
			{
				//We did it!
				Log.i("IKRPG","No post create hooks found");
				nextFrag(FragState.FLUFF);return;
			}
			
		}
		else if(currFrag == FragState.POSTCREATE_HOOK)
		{
			//Which one, and what is left?
			int nextHook = currFrag.which+1;
			
			Log.i("IKRPG","Evaluating hook "+nextHook);
			
			if(nextHook >= postCreateHooks.size())
			{nextFrag(FragState.FLUFF);return;}
			
			PostCreateHook hook = postCreateHooks.get(nextHook);
			Fragment doFrag = hook.doPostCreateHook(new BaseCharacter(null, race, archetype, myCareers, myAbilities, mySpells, mySkills, myStats), this, nextHook);
			if(doFrag != null)
			{nextFrag = doFrag;}
			else
			{
				currFrag.which = nextHook;
				nextFrag(currFrag);return;
			}
		}
		else if(currFrag == FragState.FLUFF)
		{
			nextFrag = new ChooseFluffFragment();
		}
		else if(currFrag == FragState.DONE)
		{
			//Sweet.
			//TODO: confirm dialog
			
			characterComplete();
		}
		
		if(nextFrag != null)
		{
			FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
			trans.replace(R.id.mainFragmentContainer, nextFrag);
			trans.addToBackStack(null);
			trans.commit();
		}
	}
	
	public void hookComplete(int which)
	{
		//pop stack
		getSupportFragmentManager().popBackStack();
		
		FragState finishedHook = FragState.POSTCREATE_HOOK;
		finishedHook.which = which;
		nextFrag(finishedHook);return;
	}
	
	public void createCharacter() //throws CharacterNotValidException
	{
		//TODO: revisit
		//if(career1 == career2)
		//{throw new CharacterNotValidException("Same career passed twice");}
		
		PostCreateHook aHook;
		
		//Setup
		myCareers.add(career1);
		myCareers.add(career2);
		
		//Race
		//Mostly for stats, and post-create bonuses
		myStats = new StatsBundle(race.startStats());
		aHook = race.postCreateHook();
		if(aHook != null){postCreateHooks.add(aHook);}
		
		//Archetype
		//Pick a bonus
		
		//Gotta assume this has already been done, or risk double-asking.
		//TODO: revisist
		//PrereqCheckResult = archetype.meetsPrereq(new BaseCharacter(name, race, archetype, myCareers, null, null, null, null));
		//{throw new CharacterNotValidException("Archetype not compatible with Race");}
		
		aHook = archetype.postCreateHook();
		if(aHook!=null){postCreateHooks.add(aHook);}
		
		//Careers
		//Gotta assume this has already been done, or risk double-asking.
		//TODO: revisist
		//if(!career1.meetsPrereq(new BaseCharacter(name, race, archetype, null, null, null, null, null), manager) ||
		//	!career2.meetsPrereq(new BaseCharacter(name, race, archetype, null, null, null, null, null), manager))
		//{throw new CharacterNotValidException("Career(s) not allowed");}
		
		//skills
		mySkills = new SkillsBundle(myStats);
		mySkills.setSkillLevels(career1.startingSkills());
		
		//second one is not so easy... we have to increment, not set
		Collection<Pair<Skill, Integer>> career2Skills = career2.startingSkills();
		for(Pair<Skill, Integer> skill : career2Skills)
		{
			int currSkillLevel = mySkills.getSkillLevel(skill.first);
			currSkillLevel+=skill.second;
			if(currSkillLevel > 2){currSkillLevel = 2;}
			mySkills.setSkillLevel(skill.first, currSkillLevel);
		}
		
		//Abilities
		myAbilities.addAll(career1.startingAbilities());
		myAbilities.addAll(career2.startingAbilities());
		
		//Spells
		mySpells.addAll(career1.startingSpells());
		mySpells.addAll(career2.startingSpells());
		
		//career hooks
		aHook = career1.postCreateHook();
		if(aHook!=null){postCreateHooks.add(aHook);}
		aHook = career2.postCreateHook();
		if(aHook!=null){postCreateHooks.add(aHook);}
	}
	
	public void characterComplete()
	{
		Log.i("IKRPG","Creating character...");
		BaseCharacter baby = new BaseCharacter(fluff, race, archetype, myCareers, myAbilities, mySpells, mySkills, myStats);
		Intent i = new Intent();
		i.putExtra(NEW_CHARACTER, baby);
		setResult(RESULT_OK, i);
		finish();
	}
}
