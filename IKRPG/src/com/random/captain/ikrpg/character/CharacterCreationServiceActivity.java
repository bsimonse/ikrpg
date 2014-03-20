package com.random.captain.ikrpg.character;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.Pair;

import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.gear.Loot;
import com.random.captain.ikrpg.gear.LootPack;
import com.random.captain.ikrpg.util.BundleConstants;
import com.random.captain.ikrpg.util.Utilities;

public class CharacterCreationServiceActivity extends FragmentActivity
{

	private int nameIndex=0;
	private int postHookIndex=0;
	private boolean firstTime = true;
	
	private ArrayList<zzCreateCharacterHook> postCreateHooks = new ArrayList<zzCreateCharacterHook>(15);
	private zzBaseCharacter buildingChar;
	
	@Override
	public void onSaveInstanceState(Bundle b)
	{
		super.onSaveInstanceState(b);
		b.putParcelable(BundleConstants.CHARACTER, buildingChar);
		b.putBoolean("l", firstTime);
		b.putInt("n", nameIndex);
		b.putInt("P", postHookIndex);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_flow_navigator);
		
		if(savedInstanceState != null)
		{
			buildingChar = savedInstanceState.getParcelable(BundleConstants.CHARACTER);
			firstTime = savedInstanceState.getBoolean("l", true);
			nameIndex = savedInstanceState.getInt("n", 0);
			postHookIndex = savedInstanceState.getInt("p", -1);
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
					return;
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
			i.putExtra(BundleConstants.CHARACTER, (zzBaseCharacter)null);
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
			
			//Careers; Abilities, spells, loot, and post hooks
			int startGold = 0;
			Collection<Loot> startLoot = new ArrayList<Loot>();
			for(Career career : buildingChar.careers)
			{
				buildingChar.abilities.addAll(career.startingAbilities());
				buildingChar.spells.addAll(career.startingSpells());
				buildingChar.connections.addAll(career.startingConnections());
				hookStore = career.postCreateHooks();
				if(hookStore!=null){finalHooks.addAll(hookStore);}
				startGold += career.startGold();
				startLoot.addAll(career.startLoot());
			}
			buildingChar.lootPack = new LootPack(startGold, startLoot);
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
	
	@SuppressLint("UseValueOf")	//what is this I don't even
	public void characterComplete()
	{
		//Stamp an index on it;
		buildingChar.index = makeIndex(buildingChar);
		
		Intent i = new Intent();
		i.putExtra(BundleConstants.CHARACTER, new Character(buildingChar));
		setResult(RESULT_OK, i);
		finish();
	}
	
	static int makeIndex(zzBaseCharacter guy)
	{
		//simple hash based on character name and app random ID
		String toHash = guy.fluff.name + Utilities.getAppID();
		return Utilities.hashToInt(toHash);
	}
	
	//Cheating!
	public static Character getPascal()
	{
		//At 24 EXP
		zzBaseCharacter pascal = new zzBaseCharacter();

		//Fluff
		Fluff fluff = new Fluff();
		fluff.name = "Pascal Bateu";
		fluff.sex = "Male";
		fluff.weight = "170 lbs";
		fluff.faith = "Morrowan";
		fluff.owningPlayer = "Ben";
		fluff.height = "5' 11\"";
		fluff.characteristics = "";
		pascal.fluff = fluff;

		//Race
		pascal.race = Race.HUMAN;

		//Archetype
		pascal.archetype = Archetype.MIGHTY;

		//Careers
		Set<Career> careers = new HashSet<Career>(2);
		careers.add(Career.PIRATE);
		careers.add(Career.DUELIST);
		pascal.careers = careers;

		//Stats
		pascal.setBaseStat(Stat.PHYSIQUE, 7);
		pascal.setMaxStat(Stat.PHYSIQUE, 7);
		pascal.setBaseStat(Stat.SPEED, 7);
		pascal.setMaxStat(Stat.SPEED, 7);
		pascal.setBaseStat(Stat.STRENGTH, 5);
		pascal.setMaxStat(Stat.STRENGTH, 6);
		pascal.setBaseStat(Stat.AGILITY, 5);
		pascal.setMaxStat(Stat.AGILITY, 5);
		pascal.setBaseStat(Stat.PROWESS, 4);
		pascal.setMaxStat(Stat.PROWESS, 5);
		pascal.setBaseStat(Stat.POISE, 4);
		pascal.setMaxStat(Stat.POISE, 5);
		pascal.setBaseStat(Stat.INTELLECT, 3);
		pascal.setMaxStat(Stat.INTELLECT, 5);
		pascal.setBaseStat(Stat.PERCEPTION, 4);
		pascal.setMaxStat(Stat.PERCEPTION, 5);

		//Armor modifiers!
		pascal.addStatModifier(new Modifier<Stat>(Stat.ARMOR, 7), "ARMOR_BONUS");
		pascal.addStatModifier(new Modifier<Stat>(Stat.DEFENSE, -2), "DEF_PENALTY");

		//Skills
		Map<Skill, Integer> skills = new HashMap<Skill,Integer>(20);
		skills.put(SkillEnum.HAND_WEAPON.make(), 2);
		skills.put(SkillEnum.PISTOL.make(), 2);
		skills.put(SkillEnum.CLIMBING.make(), 2);
		skills.put(SkillEnum.INTIMIDATION.make(), 2);
		skills.put(SkillEnum.SWIMMING.make(), 1);
		skills.put(SkillEnum.SAILING.make(), 1);
		skills.put(SkillEnum.GAMBLING.make(), 1);
		skills.put(SkillEnum.JUMPING.make(), 1);
		skills.put(SkillEnum.STREETWISE.make(), 1);
		skills.put(SkillEnum.LAW.make(), 1);
		skills.put(SkillEnum.NEGOTIATION.make(), 1);
		skills.put(SkillEnum.DECEPTION.make(),1);
		skills.put(SkillEnum.UNARMED.make(), 1);
		pascal.setBaseSkills(skills);

		//Abilities
		Set<Ability> abilities = new HashSet<Ability>();
		abilities.add(AbilityEnum.PRECISION_STRIKE.make());
		abilities.add(AbilityEnum.MIGHTY.make());
		abilities.add(AbilityEnum.FEAT_INVULNERABLE.make());
		abilities.add(AbilityEnum.GANG.make());
		abilities.add(AbilityEnum.STEADY.make());
		abilities.add(AbilityEnum.SPECIALIZATION.make("Cutlass"));
		abilities.add(AbilityEnum.PARRY.make());
		abilities.add(AbilityEnum.RIPOSTE.make());
		abilities.add(AbilityEnum.FAST_DRAW.make());
		pascal.abilities = abilities;

		//id
		pascal.index = makeIndex(pascal);

		return new Character(pascal);
	}
}
