package com.random.captain.ikrpg.character;

import java.util.*;

import android.content.Intent;
import android.os.Bundle;
import com.random.captain.ikrpg.util.BundleConstants;
import com.random.captain.ikrpg.util.FlowNavigator;
import com.random.captain.ikrpg.util.Utilities;

public class CharacterCreationServiceActivity extends FlowNavigator<zzCreateCharacterHook>
{
	private zzBaseCharacter buildingChar;
	
	@Override
	public void onSaveInstanceState(Bundle b)
	{
		super.onSaveInstanceState(b);
		b.putString(BundleConstants.CHARACTER,buildingChar.toJson());
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		if(savedInstanceState != null)
		{
			buildingChar = zzBaseCharacter.fromJson(savedInstanceState.getString(BundleConstants.CHARACTER));
		}
		else
		{
			buildingChar = new zzBaseCharacter();
		}
	}
	
	@Override
	protected void hookComplete(Bundle b)
	{
		String characterJson = b.getString(BundleConstants.CHARACTER);
		if(characterJson != null)
		{buildingChar = zzBaseCharacter.fromJson(characterJson);}
	}
	
	@Override
	protected Bundle prepBundle(zzCreateCharacterHook hook, int notUsed)
	{
		Bundle b = new Bundle();
		b.putString(BundleConstants.CHARACTER, buildingChar.toJson());
		return b;
	}
	
	@Override
	protected List<zzCreateCharacterHook> generateFrags()
	{
		ArrayList<zzCreateCharacterHook> flowFrags = new ArrayList<zzCreateCharacterHook>(15);
		Collection<zzCreateCharacterHook> tempFrags;
		
		//all the static ones
		flowFrags.add(new zzStaticCreateCharacterHooks.ChooseRaceFragment());
		flowFrags.add(new zzStaticCreateCharacterHooks.ChooseArchetypeHook());
		flowFrags.add(new zzStaticCreateCharacterHooks.ChooseCareerFragment());
		flowFrags.add(new zzStaticCreateCharacterHooks.ChooseCareerFragment());
		flowFrags.add(new zzStaticCreateCharacterHooks.ChooseAdvancementPointsHook());
		flowFrags.add(new zzStaticCreateCharacterHooks.ChooseFluffFragment());
		flowFrags.add(new zzStaticCreateCharacterHooks.CareerFinalizerHook());
		
		//all the possible dynamic ones
		if(buildingChar.race != null)
		{
			tempFrags = buildingChar.race.postCreateHooks();
			if(tempFrags != null){flowFrags.addAll(tempFrags);}
		}
		
		if(buildingChar.archetype != null)
		{
			tempFrags = buildingChar.archetype.postCreateHooks();
			if(tempFrags!=null){flowFrags.addAll(tempFrags);}
		}
		
		if(buildingChar.careers != null)
		{
			for(Career career : buildingChar.careers)
			{
				tempFrags = career.postCreateHooks();
				if(tempFrags!=null){flowFrags.addAll(tempFrags);}
			}
		}
		
		//sort hooks into proper order
		Collections.sort(flowFrags, new Comparator<zzCreateCharacterHook>(){
				@Override public int compare(zzCreateCharacterHook one, zzCreateCharacterHook two)
				{return one.getPriority() - two.getPriority();}
			});
			
		return flowFrags;
	}
	
	@Override
	protected void setResult()
	{
		//Stamp an index on it;
		buildingChar.index = Utilities.hashToInt(buildingChar.fluff.name + Utilities.getAppID());
		
		Intent i = new Intent();
		i.putExtra(BundleConstants.CHARACTER, buildingChar.toJson());
		setResult(RESULT_OK, i);
	}
	
	//Cheating!
	public static GameCharacter getPascal()
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
		pascal.index = 123456;

		return new GameCharacter(pascal);
	}
}
