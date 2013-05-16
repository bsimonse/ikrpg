package com.random.captain.ikrpg.model.Creators;

import com.random.captain.ikrpg.model.Attributes.*;
import java.util.*;

import android.content.Context;
import android.util.Pair;
import com.random.captain.ikrpg.model.BaseCharacter;

public class CharacterCreator
{
	public static BaseCharacter createCharacter(String pName, Race pRace, Archetype pArchetype, Career career1, Career career2, Context appContext)
			throws CharacterNotValidException
	{
		if(career1 == career2)
		{throw new CharacterNotValidException("Same career passed twice");}
		
		ArrayList<PostCreateHook> postCreateHooks = new ArrayList<PostCreateHook>(15);
		PostCreateHook aHook;
		Set<Career> myCareers = new HashSet<Career>(2);
		
		//Setup
		myCareers.add(career1);
		myCareers.add(career2);
		
		//Race
		//Mostly for stats, and post-create bonuses
		StatsBundle myStats = new StatsBundle(pRace.startStats());
		aHook = pRace.postCreateHook();
		if(aHook != null){postCreateHooks.add(aHook);}
		
		//Archetype
		//Pick a bonus
		
		aHook = pArchetype.postCreateHook();
		if(aHook!=null){postCreateHooks.add(aHook);}
		
		if(!pArchetype.meetsPrereq(pRace, pArchetype, myCareers, null, null, null, appContext))
		{throw new CharacterNotValidException("Archetype not compatible with Race");}
		
		//Careers
		SkillsBundle mySkills = new SkillsBundle(myStats);
		Set<Ability> myAbilities = new HashSet<Ability>(10);
		Set<Spell> mySpells = new HashSet<Spell>(10);
		
		if(!career1.meetsPrereq(pRace, pArchetype, myCareers, null, null, null, appContext) || !career2.meetsPrereq(pRace, pArchetype, myCareers, null, null,null, appContext))
		{throw new CharacterNotValidException("Career(s) not allowed");}
		
		//skills
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
		
		//Do postcreatehooks
		for(PostCreateHook hook : postCreateHooks)
		{
			hook.doPostCreateHook(pRace, pArchetype, myCareers, myAbilities, mySkills, myStats, appContext);
		}
		
		return new BaseCharacter(pName, pRace, pArchetype, myCareers, myAbilities, mySpells, mySkills, myStats);
	}
}
