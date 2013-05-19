package com.random.captain.ikrpg.model.Attributes;

import java.util.*;

import android.content.Context;
import android.os.Parcelable;
import android.util.Pair;
import com.random.captain.ikrpg.model.Attributes.Race;
import com.random.captain.ikrpg.model.Creators.PostCreateHook;
import com.random.captain.ikrpg.model.Creators.PrereqCheck;

public enum Career implements PrereqCheck, Parcelable
{
	//This might be better as JSON... eh.
	//But Laaaaaaaambda
	ALCHEMIST("Alchemist",
				new Pair[] {Pair.create(Skill.HAND_WEAPON, 1), Pair.create(Skill.THROWN_WEAPON, 1), Pair.create(Skill.ALCHEMY, 1), Pair.create(Skill.MEDICINE, 1)},
				new Pair[] {Pair.create(Skill.HAND_WEAPON, 2), Pair.create(Skill.THROWN_WEAPON, 2), Pair.create(Skill.UNARMED, 2), Pair.create(Skill.ALCHEMY, 4),
							Pair.create(Skill.CRAFT, 4), Pair.create(Skill.FORGERY, 2), Pair.create(Skill.MEDICINE,4), Pair.create(Skill.NEGOTIATION, 4),
							Pair.create(Skill.RESEARCH, 4)},
			  	new Ability[] {Ability.GRENADIER, Ability.POISON_RESISTANCE},
				new Ability[] {Ability.BOMBER, Ability.BREW_MASTER, Ability.FAST_COOK, Ability.FIELD_ALCHEMIST, Ability.FIRE_IN_THE_HOLE, Ability.FREE_STYLE,
								Ability.GRENADIER, Ability.POISON_RESISTANCE},
				null, null,
				null, null),
								
	ARCANE_MECHANIK("Arcane Mechanik",
			  new Pair[] {Pair.create(Skill.CRAFT, 1), Pair.create(Skill.MECHANIKAL, 1)},
			  new Pair[] {Pair.create(Skill.HAND_WEAPON, 2), Pair.create(Skill.LIGHT_ARTILLERY, 2), Pair.create(Skill.RIFLE, 2), Pair.create(Skill.COMMAND, 1),
				  Pair.create(Skill.CRAFT, 4), Pair.create(Skill.CRYPTOGRAPHY, 3), Pair.create(Skill.MECHANIKAL,4), Pair.create(Skill.NEGOTIATION, 2),
				  Pair.create(Skill.RESEARCH, 3)},
			  new Ability[] {Ability.INSCRIBE_FORMULAE},
			  new Ability[] {Ability.JACK_MARSHALL, Ability.ACE_COMMANDER, Ability.ARCANE_ENGINEER, Ability.DRIVE_ASSUALT, Ability.DRIVE_PRONTO,
			  		Ability.INSCRIBE_FORMULAE, Ability.RESOURCEFUL, Ability.STEAMO},
			  new Spell[] {Spell.ARCANTRIK_BOLT, Spell.POLARITY_SHIELD},
			  new Spell[] {Spell.JACKHAMMER, Spell.JUMP_START, Spell.LOCOMOTION, Spell.POWER_BOOSTER, Spell.PROTECTION_FROM_ELECTRICITY, Spell.RETURN_FIRE,
			  				Spell.SHORT_OUT, Spell.ARCANTRIK_BOLT, Spell.ELECTRIFY, Spell.FORTIFY, Spell.POLARITY_SHIELD, Spell.POSITIVE_CHARGE,
							Spell.REDLINE, Spell.TEMPER_METAL, Spell.BROADSIDE, Spell.ELECTRICAL_BLAST, Spell.FAIL_SAFE, Spell.FORCE_FIELD,
							Spell.FULL_THROTTLE, Spell.GRIND, Spell.GUIDED_FIRE, Spell.IRON_AGGRESSION, Spell.SUPERIORITY, Spell.BLACK_OUT,
							Spell.TIDE_OF_STEEL, Spell.VOLTAIC_LOCK},
			  new PrereqCheck(){
				  @Override
				  public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities,
				  								SkillsBundle pSkills, StatsBundle pStats, Context appContext){
					  return archetype == Archetype.GIFTED_FOCUSER || archetype == Archetype.GIFTED_WILL_WEAVER;
				  }
			  },
			  new PostCreateHook(){
				  @Override
				  public void doPostCreateHook(Race pRace, Archetype archetype, Set<Career> pCareers, Set<Ability> pAbilities,
				  								SkillsBundle pSkills, StatsBundle pStats, Context appContext){
					  //Normally, user would have a choice
					  int handWeaponLevel = pSkills.getSkillLevel(Skill.HAND_WEAPON);
					  handWeaponLevel+=1;
					  if(handWeaponLevel>2){handWeaponLevel=2;}
					  pSkills.setSkillLevel(Skill.HAND_WEAPON, handWeaponLevel);
				  }
			  });
			  
	private Career(String pName,
					Pair<Skill, Integer>[] pStartSkills, Pair<Skill, Integer>[] pSkills,
				    Ability[] pStartAbilities, Ability[] pAbilities,
					Spell[] pStartSpells, Spell[] pSpells,
					PrereqCheck pPrereqCheck, PostCreateHook pPostCreateHook)
	{
		name = pName;
		startingSkills = pStartSkills != null ? Arrays.asList(pStartSkills) : new ArrayList<Pair<Skill, Integer>>(10);
		careerSkills = pSkills != null ? Arrays.asList(pSkills) : new ArrayList<Pair<Skill, Integer>>(10);
		startingAbilities = pStartAbilities != null ? Arrays.asList(pStartAbilities) : new ArrayList<Ability>(10);
		careerAbilities = pAbilities != null ? Arrays.asList(pAbilities) : new ArrayList<Ability>(10);
		startingSpells = pStartSpells != null ? Arrays.asList(pStartSpells) : new ArrayList<Spell>(10);
		careerSpells = pSpells != null ? Arrays.asList(pSpells) : new ArrayList<Spell>(10);
		prereqCheck = pPrereqCheck;
		postCreateHook = pPostCreateHook;
	}
	
	@Override
	public String toString()
	{
		return displayName();
	}
	
	private String name;
	private Collection<Pair<Skill, Integer>> startingSkills;
	private Collection<Pair<Skill, Integer>> careerSkills;
	private Collection<Ability> startingAbilities;
	private Collection<Ability> careerAbilities;
	private Collection<Spell> startingSpells;
	private Collection<Spell> careerSpells;
	private PrereqCheck prereqCheck;
	private PostCreateHook postCreateHook;
	
	public String displayName(){return name;}
	public Collection<Pair<Skill, Integer>> startingSkills(){return Collections.unmodifiableCollection(startingSkills);}
	public Collection<Pair<Skill, Integer>> careerSkills(){return Collections.unmodifiableCollection(careerSkills);}
	public Collection<Ability> startingAbilities(){return Collections.unmodifiableCollection(startingAbilities);}
	public Collection<Ability> careerAbilities(){return Collections.unmodifiableCollection(careerAbilities);}
	public Collection<Spell> startingSpells(){return Collections.unmodifiableCollection(startingSpells);}
	public Collection<Spell> careerSpells(){return Collections.unmodifiableCollection(careerSpells);}
	public PostCreateHook postCreateHook(){return postCreateHook;}
	
	@Override
	public boolean meetsPrereq(Race race, Archetype archetype, Set<Career> careers, Set<Ability> pAbilities,
							   SkillsBundle pSkills, StatsBundle pStats, Context appContext)
	{
		if(prereqCheck == null){return true;}
		return prereqCheck.meetsPrereq(race, archetype, careers, pAbilities, pSkills, pStats, appContext);
	}
}
