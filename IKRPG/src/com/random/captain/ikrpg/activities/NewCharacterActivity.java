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
	private FragState lastFinishedFrag;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_new_character);
		nextFrag(FragState.START);
	}

	public void nextFrag(FragState completedFrag)
	{
		lastFinishedFrag = completedFrag;
		
		Fragment nextFrag = null;
		switch(completedFrag)
		{
			case START: nextFrag = new ChooseRaceFragment(); break;
			case RACE: nextFrag = new ChooseArchtypeFragment(); break;
			case ARCHETYPE: nextFrag = new ChooseCareerFragment(); break;
			
			case CAREER1:
				nextFrag = new ChooseCareerFragment();
				Bundle args = new Bundle();
				args.putBoolean(SECOND_CAREER, true);
				nextFrag.setArguments(args);
			break;
			
			case CAREER2:
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

					Collections.sort(postCreateHooks, new Comparator<PostCreateHook>(){
							@Override public int compare(PostCreateHook one, PostCreateHook two)
							{return one.getPriority() - two.getPriority();}
						});
						
					PostCreateHook hook = postCreateHooks.get(0);
					Fragment doFrag = hook.doPostCreateHook(new BaseCharacter(null, race, archetype, myCareers, myAbilities, mySpells, mySkills, myStats), this, 0);
					if(doFrag != null)
					{nextFrag = doFrag;}
					else
					{
						completedFrag = FragState.POSTCREATE_HOOK;
						completedFrag.which = 0;
						nextFrag(completedFrag);return;
					}
				}
				else
				{
					Log.i("IKRPG","No post create hooks found");
					nextFrag = new ChooseFluffFragment(); break;
				}
			break;
			
			case POSTCREATE_HOOK:
				//Which one, and what is left?
				int nextHook = completedFrag.which+1;

				if(nextHook >= postCreateHooks.size())
				{nextFrag = new ChooseFluffFragment(); break;}

				Log.i("IKRPG","Evaluating hook "+nextHook);

				PostCreateHook hook = postCreateHooks.get(nextHook);
				Fragment doFrag = hook.doPostCreateHook(new BaseCharacter(null, race, archetype, myCareers, myAbilities, mySpells, mySkills, myStats), this, nextHook);
				if(doFrag != null)
				{nextFrag = doFrag;}
				else
				{
					completedFrag.which = nextHook;
					nextFrag(completedFrag);return;
				}
			break;
			
			case FLUFF: characterComplete(); break;
		}
		
		if(nextFrag != null)
		{
			FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
			trans.replace(R.id.mainFragmentContainer, nextFrag);
			trans.addToBackStack(null);
			trans.commit();
		}
	}
	
	public void hookComplete(int finishedHookIndex)
	{
		FragState finishedHook = FragState.POSTCREATE_HOOK;
		finishedHook.which = finishedHookIndex;
		nextFrag(finishedHook);
	}
	
	//Oh my God my logic is convoluted
	//Major cleanup needed after it works
	@Override public void onBackPressed()
	{
		//undo previous thing
		switch(lastFinishedFrag)
		{
			case START: break;//nothing to do here
			case RACE: ChooseRaceFragment.undo(this); lastFinishedFrag = FragState.START; break;
			case ARCHETYPE: ChooseArchtypeFragment.undo(this); lastFinishedFrag = FragState.RACE; break;
			case CAREER1: ChooseCareerFragment.undo(this, true); lastFinishedFrag = FragState.ARCHETYPE; break;
			case CAREER2: ChooseCareerFragment.undo(this, false); lastFinishedFrag = FragState.CAREER1; break;

			case POSTCREATE_HOOK:
				int completedFrag = lastFinishedFrag.which;
				postCreateHooks.get(completedFrag).undoPostCreateHook(new BaseCharacter(null, race, archetype, myCareers, myAbilities, mySpells, mySkills, myStats));
				if(completedFrag <= 0){lastFinishedFrag = FragState.CAREER1;}
				else{lastFinishedFrag.which--;}
			break;
			 
			case FLUFF: //remember!  Broken pattern!  Needs fixing!
				int lastHook = postCreateHooks.size()-1;
				postCreateHooks.get(lastHook).undoPostCreateHook(new BaseCharacter(null, race, archetype, myCareers, myAbilities, mySpells, mySkills, myStats));
				lastFinishedFrag = FragState.POSTCREATE_HOOK;
				lastFinishedFrag.which = lastHook;
				break;
				
			case DONE: break; //wait, what?
			default: break;
		}
		super.onBackPressed();
	}
	
	public void createCharacter() //throws CharacterNotValidException
	{
		PostCreateHook aHook;
		myCareers = new HashSet<Career>(2);
		myAbilities = new HashSet<Ability>(10);
		mySpells = new HashSet<Spell>(10);
		postCreateHooks = new ArrayList<PostCreateHook>(15);
		
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
		aHook = archetype.postCreateHook();
		if(aHook!=null){postCreateHooks.add(aHook);}
		
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
