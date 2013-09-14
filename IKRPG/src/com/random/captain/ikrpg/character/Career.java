package com.random.captain.ikrpg.character;

import java.util.*;

import android.content.Context;
import android.util.Pair;
import com.random.captain.ikrpg.IKRPGApp;
import com.random.captain.ikrpg.R;

public enum Career implements zzPrereqCheck
{
	@SuppressWarnings("unchecked")
	ALCHEMIST(R.string.alchemist_name,
				new Pair[] {SkillEnum.HAND_WEAPON.pair(1), SkillEnum.THROWN_WEAPON.pair(1), SkillEnum.ALCHEMY.pair(1), SkillEnum.MEDICINE.pair(1)},
			  	new Pair[] {SkillEnum.HAND_WEAPON.pair(2), SkillEnum.THROWN_WEAPON.pair(4), SkillEnum.UNARMED.pair(2), 
							SkillEnum.ALCHEMY.pair(4), SkillEnum.CRAFT.pair(4), SkillEnum.FORGERY.pair(2), SkillEnum.MEDICINE.pair(4),
							SkillEnum.NEGOTIATION.pair(4), SkillEnum.RESEARCH.pair(4)},
			  	new Ability[] {AbilityEnum.GRENADIER.make(), AbilityEnum.POISON_RESISTANCE.make()},
				new Ability[] {AbilityEnum.BOMBER.make(), AbilityEnum.BREW_MASTER.make(), AbilityEnum.FAST_COOK.make(),AbilityEnum.FIELD_ALCHEMIST.make(), AbilityEnum.FIRE_IN_THE_HOLE.make(), AbilityEnum.FREE_STYLE.make(),
								AbilityEnum.GRENADIER.make(),AbilityEnum.POISON_RESISTANCE.make()},
				null, null,
				null, null),
								
	@SuppressWarnings("unchecked")
	ARCANE_MECHANIK(R.string.arcane_mechanik_name,
				new Pair[] {SkillEnum.CRAFT.pair("gunsmithing", 1), SkillEnum.CRAFT.pair("metalworking", 1), SkillEnum.MECHANIKAL.pair(1)},
				new Pair[] {SkillEnum.HAND_WEAPON.pair(2), SkillEnum.LIGHT_ARTILLERY.pair(2), SkillEnum.RIFLE.pair(2), 
							SkillEnum.COMMAND.pair(1), SkillEnum.CRAFT.pair(4), SkillEnum.CRYPTOGRAPHY.pair(3), SkillEnum.MECHANIKAL.pair(4),
							SkillEnum.NEGOTIATION.pair(2), SkillEnum.RESEARCH.pair(3)},
				new Ability[] {new Ability(AbilityEnum.INSCRIBE_FORMULAE)},
				new Ability[] {new Ability(AbilityEnum.JACK_MARSHALL), new Ability(AbilityEnum.ACE_COMMANDER), new Ability(AbilityEnum.ARCANE_ENGINEER), new Ability(AbilityEnum.DRIVE_ASSUALT), new Ability(AbilityEnum.DRIVE_PRONTO),
								   new Ability(AbilityEnum.INSCRIBE_FORMULAE), new Ability(AbilityEnum.RESOURCEFUL), new Ability(AbilityEnum.STEAMO)},
				new Spell[] {Spell.ARCANTRIK_BOLT, Spell.POLARITY_SHIELD},
				new Spell[] {Spell.JACKHAMMER, Spell.JUMP_START, Spell.LOCOMOTION, Spell.POWER_BOOSTER, Spell.PROTECTION_FROM_ELECTRICITY, Spell.RETURN_FIRE,
			  				Spell.SHORT_OUT, Spell.ARCANTRIK_BOLT, Spell.ELECTRIFY, Spell.FORTIFY, Spell.POLARITY_SHIELD, Spell.POSITIVE_CHARGE,
							Spell.REDLINE, Spell.TEMPER_METAL, Spell.BROADSIDE, Spell.ELECTRICAL_BLAST, Spell.FAIL_SAFE, Spell.FORCE_FIELD,
							Spell.FULL_THROTTLE, Spell.GRIND, Spell.GUIDED_FIRE, Spell.IRON_AGGRESSION, Spell.SUPERIORITY, Spell.BLACK_OUT,
							Spell.TIDE_OF_STEEL, Spell.VOLTAIC_LOCK},
				giftedPrereq(),
				new zzCreateCharacterHook[] {new ArcaneMechanikHook()}),
	
	
	ARCANIST(R.string.arcanist_name,
			 new Pair[] {SkillEnum.LORE.pair("Arcane",1), SkillEnum.RESEARCH.pair(1)},
			 new Pair[] {SkillEnum.CRAFT.pair(2), SkillEnum.ETIQUETTE.pair(2), SkillEnum.NEGOTIATION.pair(2), SkillEnum.ORATORY.pair(2), SkillEnum.RESEARCH.pair(4)},
			 new Ability[] {AbilityEnum.GREAT_POWER.make()},
			 new Ability[] {AbilityEnum.ARCANE_DEFENSES.make(), AbilityEnum.ARCANE_SCHOLAR.make(), AbilityEnum.GREAT_POWER.make(), AbilityEnum.UNIVERSITY_EDUCATION.make()},
			 new Spell[] {Spell.ARCANE_BOLT, Spell.AURA_OF_PROTECTION, Spell.LIGHT_IN_THE_DARKNESS},
			 new Spell[] {Spell.ARCANE_STRIKE, Spell.BLIZZARD, Spell.FIRE_STARTER,Spell.GUIDED_BLADE,Spell.INFLUENCE,Spell.LIGHT_IN_THE_DARKNESS,Spell.PROTECTION_FROM_COLD,
			 				Spell.PROTECTION_FROM_CORROSION, Spell.PROTECTION_FROM_ELECTRICITY, Spell.PROTECTION_FROM_FIRE, Spell.STORM_TOSSED, Spell.ARCANE_BOLT,
							Spell.ASHEN_CLOUD,Spell.AURA_OF_PROTECTION,Spell.BANISHING_WARD,Spell.CELERITY,Spell.FOXHOLE,Spell.HAND_OF_FATE,Spell.HOWLING_FLAMES,
							Spell.ICY_GRIP,Spell.OCCULTATION,Spell.ROCK_WALL,Spell.TELEKINESIS,Spell.TRUE_SIGHT,Spell.VISION,Spell.WIND_BLAST,Spell.FOG_OF_WAR,
							Spell.FORCE_FIELD,Spell.HEX_BLAST,Spell.INHOSPITABLE_GROUND,Spell.MIRAGE,Spell.RIFT,Spell.ROCK_HAMMER,Spell.ZEPHYR,Spell.FORCE_HAMMER,
							Spell.OVERMIND,Spell.TEMPEST},
			 giftedPrereq(),
			 new zzCreateCharacterHook[] {new ArcanistHook()}),
			 
	ARISTOCRAT(R.string.aristocrat_name,
			 new Pair[] {SkillEnum.HAND_WEAPON.pair(1),SkillEnum.COMMAND.pair(1),SkillEnum.ETIQUETTE.pair(1)},
			 new Pair[] {SkillEnum.ARCHERY.pair(2),SkillEnum.HAND_WEAPON.pair(3),SkillEnum.LANCE.pair(3),SkillEnum.PISTOL.pair(2),SkillEnum.RIFLE.pair(3),
			 				SkillEnum.BRIBERY.pair(4),SkillEnum.COMMAND.pair(4),SkillEnum.CRYPTOGRAPHY.pair(2),SkillEnum.DECEPTION.pair(4),SkillEnum.ETIQUETTE.pair(4),
							SkillEnum.LAW.pair(4),SkillEnum.NEGOTIATION.pair(4),SkillEnum.ORATORY.pair(4),SkillEnum.SEDUCTION.pair(4)},
			 new Ability[] {},
			 new Ability[] {},
			 new Spell[] {},
			 new Spell[] {},
			 giftedPrereq(),
			 new zzCreateCharacterHook[] {new AristocratWeaponHook(), new AristocratLanguageHook()}),
			 
	/*ARCANIST(R.string.arcanist_name,
				new Pair[] {},
				new Pair[] {},
				new Ability[] {},
				new Ability[] {},
				new Spell[] {},
				new Spell[] {},
				giftedPrereq(),
				new zzCreateCharacterHook[] {new ArcanistHook()}),*/
				
	DUELIST(R.string.duelist_name,null,null,null,null,null,null,null,null),		  
	PIRATE(R.string.pirate_name,null,null,null,null,null,null,null,null),
	WARCASTER(R.string.warcaster_name,null,null,null,null,null,null,null,null);
			  
	
	//Done!
	private Career(int pNameResource,
					Pair<Skill, Integer>[] pStartSkills, Pair<Skill, Integer>[] pSkills,
				    Ability[] pStartAbilities, Ability[] pAbilities,
					Spell[] pStartSpells, Spell[] pSpells,
					zzPrereqCheck pPrereqCheck, zzCreateCharacterHook[] pPostCreateHook)
	{
		Context c = IKRPGApp.getContext();
		name = c.getString(pNameResource);
		startingSkills = pStartSkills != null ? Arrays.asList(pStartSkills) : new ArrayList<Pair<Skill, Integer>>(10);
		careerSkills = pSkills != null ? Arrays.asList(pSkills) : new ArrayList<Pair<Skill, Integer>>(10);
		startingAbilities = pStartAbilities != null ? Arrays.asList(pStartAbilities) : new ArrayList<Ability>(10);
		careerAbilities = pAbilities != null ? Arrays.asList(pAbilities) : new ArrayList<Ability>(10);
		startingSpells = pStartSpells != null ? Arrays.asList(pStartSpells) : new ArrayList<Spell>(10);
		careerSpells = pSpells != null ? Arrays.asList(pSpells) : new ArrayList<Spell>(10);
		prereqCheck = pPrereqCheck;
		postCreateHooks = pPostCreateHook != null ? Arrays.asList(pPostCreateHook) : new ArrayList<zzCreateCharacterHook>(10);
	}
	
	@Override public String toString(){return displayName();}
	
	private String name;
	private Collection<Pair<Skill, Integer>> startingSkills;
	private Collection<Pair<Skill, Integer>> careerSkills;
	private Collection<Ability> startingAbilities;
	private Collection<Ability> careerAbilities;
	private Collection<Spell> startingSpells;
	private Collection<Spell> careerSpells;
	private zzPrereqCheck prereqCheck;
	private Collection<zzCreateCharacterHook> postCreateHooks;
	
	public String displayName(){return name;}
	public Collection<Pair<Skill, Integer>> startingSkills(){return Collections.unmodifiableCollection(startingSkills);}
	public Collection<Pair<Skill, Integer>> careerSkills(){return Collections.unmodifiableCollection(careerSkills);}
	public Collection<Ability> startingAbilities(){return Collections.unmodifiableCollection(startingAbilities);}
	public Collection<Ability> careerAbilities(){return Collections.unmodifiableCollection(careerAbilities);}
	public Collection<Spell> startingSpells(){return Collections.unmodifiableCollection(startingSpells);}
	public Collection<Spell> careerSpells(){return Collections.unmodifiableCollection(careerSpells);}
	Collection<zzCreateCharacterHook> postCreateHooks(){return postCreateHooks;}
	
	@Override
	public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
	{
		Set<Career> careers = myChar.careers;
		
		//duplicates not allowed
		if(careers != null && careers.contains(this)){return new zzPrereqCheckResult(false, null);}
		
		//no prereq means allowed
		if(prereqCheck == null){return new zzPrereqCheckResult(true, null);}
		else{return prereqCheck.meetsPrereq(myChar);}
	}
	
	/* Prereqs! */
	
	public static zzPrereqCheck giftedPrereq(){
		return new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar){
				if(myChar.archetype == null){return new zzPrereqCheckResult(false, null);}
				return new zzPrereqCheckResult(myChar.archetype == Archetype.GIFTED, null);
			}
		};
	}
	
	/* Hooks! */
	
	public static class ArcaneMechanikHook extends zzChooseOneSkillHook
	{
		@Override protected String getTitle() {return "Choose a military skill to boost";}
		
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(2);
			l.add(new Skill(SkillEnum.RIFLE)); l.add(new Skill(SkillEnum.HAND_WEAPON));
			return l;
		}
	}
	
	public static class ArcanistHook extends zzCreateCharacterHook
	{
		@Override public void startHook(zzBaseCharacter pChar, zzCreateCharacterHookDelegate pDelegate, CreateHook pHook)
		{
			super.startHook(pChar, pDelegate, pHook);
			myChar.abilities.add(AbilityEnum.GREAT_POWER.make());
			pDelegate.hookComplete(myChar);
		}
		@Override public boolean hasUI(){return false;}
		@Override public int getPriority(){return 0;}
		@Override public void undoHook()
		{
			myChar.abilities.remove(AbilityEnum.GREAT_POWER);
		}
	}
	
	public static class AristocratWeaponHook extends zzChooseOneSkillHook
	{
		@Override protected String getTitle() {return "Choose a military skill to boost";}

		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(3);
			l.add(new Skill(SkillEnum.ARCHERY)); l.add(new Skill(SkillEnum.PISTOL)); l.add(new Skill(SkillEnum.RIFLE));
			return l;
		}
	}
	
	public static class AristocratLanguageHook extends zzChooseOneHook<Language>
	{
		private Language chosenLanguage;

		@Override protected String getTitle(){return "Choose a language to learn:";}
		@Override protected List<Language> getOptions() {return Arrays.asList(Language.values());}
		
		@Override protected void itemSelected(int which)
		{
			chosenLanguage = getOptions().get(which);
			myChar.languages.add(chosenLanguage);
		}

		@Override public boolean hasUI(){return true;}

		@Override protected void doDefaultCase() {//can't get called, since always hasUI.
		}

		@Override public void undoHook()
		{myChar.languages.remove(chosenLanguage);}
	}
}
