package com.random.captain.ikrpg.character;

import java.util.*;

import android.content.Context;
import android.util.Pair;
import com.random.captain.ikrpg.IKRPGApp;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.gear.Loot;

public enum Career implements zzPrereqCheck
{
	@SuppressWarnings("unchecked")
	ALCHEMIST(R.string.alchemist_name, false,
				new Pair[] {SkillEnum.HAND_WEAPON.pair(1), SkillEnum.THROWN_WEAPON.pair(1), SkillEnum.ALCHEMY.pair(1), SkillEnum.MEDICINE.pair(1)},
			  	new Pair[] {SkillEnum.HAND_WEAPON.pair(2), SkillEnum.THROWN_WEAPON.pair(4), SkillEnum.UNARMED.pair(2), 
							SkillEnum.ALCHEMY.pair(4), SkillEnum.CRAFT.pair(4), SkillEnum.FORGERY.pair(2), SkillEnum.MEDICINE.pair(4),
							SkillEnum.NEGOTIATION.pair(4), SkillEnum.RESEARCH.pair(4)},
			  	new Ability[] {AbilityEnum.GRENADIER.make(), AbilityEnum.POISON_RESISTANCE.make()},
				new Ability[] {AbilityEnum.BOMBER.make(), AbilityEnum.BREW_MASTER.make(), AbilityEnum.FAST_COOK.make(),AbilityEnum.FIELD_ALCHEMIST.make(), AbilityEnum.FIRE_IN_THE_HOLE.make(), AbilityEnum.FREE_STYLE.make(),
								AbilityEnum.GRENADIER.make(),AbilityEnum.POISON_RESISTANCE.make()},
				null, null,
				null, new Connection[] {Connection.make("alchemical order")},
				50, new Loot[]{}, null,
				null, null),
								
	@SuppressWarnings("unchecked")
	ARCANE_MECHANIK(R.string.arcane_mechanik_name, false,
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
				null, new Connection[] {Connection.make("mechaniks organization")},
				50, new Loot[]{}, null,
				new zzPrereqCheck[]{giftedPrereq()},
				new zzCreateCharacterHook[] {new ArcaneMechanikHook()}),
	
	
	ARCANIST(R.string.arcanist_name, false,
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
			 null, new Connection[] {Connection.make("magical order")},
			 75, null, null,
			 new zzPrereqCheck[]{giftedPrereq()},
			 new zzCreateCharacterHook[] {new ArcanistHook()}),
			 
	ARISTOCRAT(R.string.aristocrat_name, true,
			 new Pair[] {SkillEnum.HAND_WEAPON.pair(1),SkillEnum.COMMAND.pair(1),SkillEnum.ETIQUETTE.pair(1)},
			 new Pair[] {SkillEnum.ARCHERY.pair(2),SkillEnum.HAND_WEAPON.pair(3),SkillEnum.LANCE.pair(3),SkillEnum.PISTOL.pair(2),SkillEnum.RIFLE.pair(3),
			 				SkillEnum.BRIBERY.pair(4),SkillEnum.COMMAND.pair(4),SkillEnum.CRYPTOGRAPHY.pair(2),SkillEnum.DECEPTION.pair(4),SkillEnum.ETIQUETTE.pair(4),
							SkillEnum.LAW.pair(4),SkillEnum.NEGOTIATION.pair(4),SkillEnum.ORATORY.pair(4),SkillEnum.SEDUCTION.pair(4)},
			 new Ability[] {AbilityEnum.GOOD_BREEDING.make(), AbilityEnum.PRIVILEGE.make()},
			 new Ability[] {AbilityEnum.ADVISOR.make(), AbilityEnum.APPRAISE.make(), AbilityEnum.BATTLE_PLAN_CALL_TO_ACTION.make(), AbilityEnum.EXPERT_RIDER.make(),
			 				AbilityEnum.GOOD_BREEDING.make(), AbilityEnum.LANGUAGE.make(), AbilityEnum.NATURAL_LEADER.make(), AbilityEnum.POISON_RESISTANCE.make(),
							AbilityEnum.PRIVILEGE.make(), AbilityEnum.RALLYING_CRY.make(), AbilityEnum.SWIFT_RIDER.make()},
			 null, null,
			 new Connection[] {Connection.make("nobility")}, new Connection[] {Connection.make("any...?")},
			 200, null, null,
			 new zzPrereqCheck[]{humanPrereq()},
			 new zzCreateCharacterHook[] {new AristocratWeaponHook(), new LanguageHook()}),
	
	BOUNTY_HUNTER(R.string.bounty_hunter_name, false,
			 new Pair[] {SkillEnum.DETECTION.pair(1), SkillEnum.INTIMIDATION.pair(1), SkillEnum.ROPE_USE.pair(1), SkillEnum.TRACKING.pair(1)},
			 new Pair[] {SkillEnum.BRIBERY.pair(2), SkillEnum.DECEPTION.pair(2), SkillEnum.DISGUISE.pair(2), SkillEnum.INTERROGATION.pair(2), SkillEnum.LAW.pair(2),
			 			SkillEnum.NEGOTIATION.pair(4), SkillEnum.ROPE_USE.pair(4), SkillEnum.SNEAK.pair(3), SkillEnum.STREETWISE.pair(4),
						SkillEnum.CROSSBOW.pair(3), SkillEnum.HAND_WEAPON.pair(2), SkillEnum.PISTOL.pair(2), SkillEnum.RIFLE.pair(3), SkillEnum.UNARMED.pair(4)},
			 new Ability[] {AbilityEnum.BINDING.make(), AbilityEnum.TAKE_DOWN.make()},
			 new Ability[] {AbilityEnum.BINDING.make(), AbilityEnum.CROSSBOWMAN.make(), AbilityEnum.HEADBUTT.make(), AbilityEnum.LANGUAGE.make(), AbilityEnum.PURSUIT.make(),
			 				AbilityEnum.ROLL_WITH_IT.make(), AbilityEnum.TAKE_DOWN.make(), AbilityEnum.WAYLAY.make()},
			 null, null,
			 null, new Connection[]{Connection.make("any...?")},
			 75, null, null,
			 null,
			 new zzCreateCharacterHook[] {new BountyHunterHook()}),
	
	CUTTHROAT(R.string.cutthroat_name, false,
			 new Pair[] {SkillEnum.HAND_WEAPON.pair(1), SkillEnum.INTIMIDATION.pair(1), SkillEnum.SNEAK.pair(1), SkillEnum.STREETWISE.pair(1)},
			 new Pair[] {SkillEnum.CROSSBOW.pair(2), SkillEnum.HAND_WEAPON.pair(4), SkillEnum.THROWN_WEAPON.pair(3), SkillEnum.UNARMED.pair(3),
			 			SkillEnum.INTERROGATION.pair(2), SkillEnum.SNEAK.pair(4), SkillEnum.STREETWISE.pair(4)},
			 new Ability[] {AbilityEnum.ANATOMICAL_PRECISION.make(), AbilityEnum.BACKSTAB.make(), AbilityEnum.PROWL.make()},
			 new Ability[] {AbilityEnum.ANATOMICAL_PRECISION.make(), AbilityEnum.BACKSTAB.make(), AbilityEnum.BLOOD_SPILLER.make(), AbilityEnum.CAMOUFLAGE.make(),
			 				AbilityEnum.CHAIN_ATTACK_BLEED_OUT.make(), AbilityEnum.FAST_DRAW.make(), AbilityEnum.LANGUAGE.make("Five Cant"), AbilityEnum.PROWL.make(),
							AbilityEnum.SPECIALIZATION.make("Assassin Blade"), AbilityEnum.TWO_WEAPON_FIGHTING.make(), AbilityEnum.WAYLAY.make()},
			 null, null, 
			 null, new Connection[]{Connection.make("criminal")},
			 75, null, null,
			 null,
			 new zzCreateCharacterHook[] {new CutthroatHook()}),
	
	DUELIST(R.string.duelist_name, false,
			 new Pair[] {SkillEnum.HAND_WEAPON.pair(1), SkillEnum.PISTOL.pair(1), SkillEnum.GAMBLING.pair(1), SkillEnum.INTIMIDATION.pair(1), SkillEnum.JUMPING.pair(1)},
			 new Pair[] {SkillEnum.HAND_WEAPON.pair(4), SkillEnum.PISTOL.pair(4), SkillEnum.THROWN_WEAPON.pair(3), SkillEnum.UNARMED.pair(2), SkillEnum.ETIQUETTE.pair(2),
			 			SkillEnum.LAW.pair(2), SkillEnum.ORATORY.pair(2), SkillEnum.SEDUCTION.pair(3), SkillEnum.STREETWISE.pair(2)},
			 new Ability[] {AbilityEnum.PARRY.make(), AbilityEnum.RIPOSTE.make()},
			 new Ability[] {AbilityEnum.ACROBATICS.make(), AbilityEnum.FAST_DRAW.make(), AbilityEnum.GUNFIGHTER.make(), AbilityEnum.PARRY.make(),
			 				AbilityEnum.PRECISION_STRIKE.make(), AbilityEnum.QUICK_WORK.make(), AbilityEnum.RIPOSTE.make(), AbilityEnum.ROLL_WITH_IT.make(),
							AbilityEnum.TWO_WEAPON_FIGHTING.make()},
			 null, null, 
			 null, null,
			 75, null, null,
			 null, null),
	
	EXPLORER(R.string.explorer_name, false,
			 new Pair[] {SkillEnum.DETECTION.pair(1), SkillEnum.MEDICINE.pair(1), SkillEnum.NAVIGATION.pair(1), SkillEnum.SURVIVAL.pair(1)},
			 new Pair[] {SkillEnum.ARCHERY.pair(2), SkillEnum.CROSSBOW.pair(2), SkillEnum.HAND_WEAPON.pair(2), SkillEnum.PISTOL.pair(2), SkillEnum.RIFLE.pair(3),
			 			SkillEnum.THROWN_WEAPON.pair(2), SkillEnum.UNARMED.pair(2), SkillEnum.COMMAND.pair(4), SkillEnum.CRAFT.pair(2), SkillEnum.CRYPTOGRAPHY.pair(2),
						SkillEnum.ETIQUETTE.pair(2), SkillEnum.MEDICINE.pair(2), SkillEnum.NAVIGATION.pair(4), SkillEnum.NEGOTIATION.pair(4), SkillEnum.ROPE_USE.pair(4),
						SkillEnum.SURVIVAL.pair(4)},
			 new Ability[] {AbilityEnum.BIG_GAME_HUNTER.make(), AbilityEnum.LANGUAGE.make(), AbilityEnum.PORT_OF_CALL.make()},
			 new Ability[] {AbilityEnum.BATTLE_PLAN_RECONNAISSANCE.make(), AbilityEnum.BIG_GAME_HUNTER.make(), AbilityEnum.DISEASE_RESISTANCE.make(),
			 				AbilityEnum.EXPERT_RIDER.make(), AbilityEnum.LANGUAGE.make(), AbilityEnum.NATURAL_LEADER.make(), AbilityEnum.POISON_RESISTANCE.make(),
							AbilityEnum.PORT_OF_CALL.make(), AbilityEnum.SIGNAL_LANGUAGE.make(), AbilityEnum.SWIFT_RIDER.make()},
			 null, null,
			 new Connection[] {Connection.make("wealthy patron")},
			 new Connection[] {Connection.make("wealthy patron"), Connection.make("isolated tribe/people")},
			 150,
			 new Loot[] {},
			 new String[] {"A character who chooses Explorer as a starting career gains 25 gc each month from their patron for as long as they continue to explore new regions, report back regularly, and bring his patron occasional gifts from exotic places."},
			 null,
			 new zzCreateCharacterHook[] {new ExplorerMilitarySkillHook(), new LanguageHook()}),
	
	FELL_CALLER(R.string.fell_caller_name, false,
			 new Pair[] {SkillEnum.COMMAND.pair(1), SkillEnum.FELL_CALLING.pair(2), SkillEnum.LORE.pair("Trollkin", 1), SkillEnum.ORATORY.pair(1)},
			 new Pair[] {SkillEnum.GREAT_WEAPON.pair(3), SkillEnum.HAND_WEAPON.pair(3), SkillEnum.THROWN_WEAPON.pair(3), SkillEnum.UNARMED.pair(3), SkillEnum.COMMAND.pair(4),
			 			SkillEnum.FELL_CALLING.pair(4), SkillEnum.ORATORY.pair(4), SkillEnum.SEDUCTION.pair(2)},
			 new Ability[] {AbilityEnum.FELL_CALL_SIGNAL_CALL.make(), AbilityEnum.FELL_CALL_SONIC_BLAST.make()},
			 new Ability[] {AbilityEnum.BATTLE_PLAN_CALL_TO_ACTION.make(), AbilityEnum.FELL_CALL_CACOPHONY.make(), AbilityEnum.FELL_CALL_CALL_OF_DEFIANCE.make(),
			 				AbilityEnum.FELL_CALL_GROUND_SHAKER.make(), AbilityEnum.FELL_CALL_HEROIC_BALLAD.make(), AbilityEnum.FELL_CALL_REVERBERATION.make(),
							AbilityEnum.LEGACY_OF_BRAGG.make(), AbilityEnum.NATURAL_LEADER.make()},
			 null, null,
			 null,
			 new Connection[] {Connection.make("kriel")},
			 75, null, null,
			 new zzPrereqCheck[] {trollkinPrereq()},
			 new zzCreateCharacterHook[] {new FellCallerHook()}),
	
	FIELD_MECHANIK(R.string.field_mechanik_name, false,
			 new Pair[] {SkillEnum.COMMAND.pair(1), SkillEnum.CRAFT.pair("metalworking", 1), SkillEnum.MECHANIKAL.pair(1)},
			 new Pair[] {SkillEnum.HAND_WEAPON.pair(2), SkillEnum.PISTOL.pair(2), SkillEnum.COMMAND.pair(3), SkillEnum.CRAFT.pair(4),
			 			SkillEnum.MECHANIKAL.pair(4), SkillEnum.NEGOTIATION.pair(3)},
			 new Ability[] {AbilityEnum.JACK_MARSHALL.make(), AbilityEnum.BODGE.make(), AbilityEnum.HIT_THE_DECK.make()},
			 new Ability[] {AbilityEnum.JACK_MARSHALL.make(), AbilityEnum.ACE_COMMANDER.make(), AbilityEnum.BODGE.make(), AbilityEnum.DODGER.make(),
			 				AbilityEnum.DRIVE_ANCILLARY_ATTACK.make(), AbilityEnum.DRIVE_ASSUALT.make(), AbilityEnum.DRIVE_OFF_ROAD.make(), AbilityEnum.DRIVE_PRONTO.make(),
							AbilityEnum.IRON_SENTINEL.make(), AbilityEnum.SCROUNGE.make(), AbilityEnum.STEAMO.make(), AbilityEnum.TUNE_UP.make()},
			 null, null,
			 null,
			 new Connection[] {Connection.make("mechaniks organization")},
			 25,
			 new Loot[] {},
			 null,
			 null,
			 new zzCreateCharacterHook[] {new FieldMechanikMilitarySkillHook(), new FieldMechanikJackHook()}),
	
	GUN_MAGE(R.string.gun_mage_name, false,
			 new Pair[] {SkillEnum.DETECTION.pair(1), SkillEnum.INTIMIDATION.pair(1)},
			 new Pair[] {SkillEnum.PISTOL.pair(4), SkillEnum.RIFLE.pair(4), SkillEnum.SEDUCTION.pair(2)},
			 new Ability[] {AbilityEnum.CRAFT_RUNE_SHOT.make(), AbilityEnum.FAST_RELOAD.make()},
			 new Ability[] {AbilityEnum.ARCANE_PRECISION.make(), AbilityEnum.CRAFT_RUNE_SHOT.make(), AbilityEnum.FAST_DRAW.make(), AbilityEnum.FAST_RELOAD.make(),
			 				AbilityEnum.GUNFIGHTER.make(), AbilityEnum.KEEN_EYED.make()},
			 new Spell[] {Spell.RUNE_SHOT_ACCURACY, Spell.RUNE_SHOT_BRUTAL, Spell.RUNE_SHOT_THUNDERBOLT},
			 new Spell[] {Spell.RETURN_FIRE, Spell.RUNE_SHOT_ACCURACY, Spell.RUNE_SHOT_BLACK_PENNY, Spell.RUNE_SHOT_BRUTAL, Spell.RUNE_SHOT_IRON_ROT,
			 				Spell.RUNE_SHOT_MOLTEN_SHOT, Spell.RUNE_SHOT_SILENCER, Spell.RUNE_SHOT_SPONTANEOUS_COMBUSTION, Spell.RUNE_SHOT_THUNDERBOLT,
							Spell.FIRE_GROUP, Spell.HEIGHTENED_REFLEXES, Spell.REFUGE, Spell.RUNE_SHOT_FIRE_BEACON, Spell.RUNE_SHOT_SHADOW_FIRE,
							Spell.RUNE_SHOT_TRICK_SHOT, Spell.SNIPE, Spell.TRUE_SIGHT, Spell.GUIDED_FIRE, Spell.RUNE_SHOT_DETONATOR, Spell.RUNE_SHOT_EARTH_SHAKER,
							Spell.RUNE_SHOT_PHANTOM_SEEKER, Spell.RUNE_SHOT_SPELL_CRACKER, Spell.RUNE_SHOT_FREEZE_FIRE, Spell.RUNE_SHOT_HEART_STOPPER,
							Spell.RUNE_SHOT_MOMENTUM},
			 null,
			 new Connection[] {Connection.make("gun mage order")},
			 25,
			 new Loot[] {},
			 null,
			 new zzPrereqCheck[] {giftedPrereq()},
			 new zzCreateCharacterHook[] {new GunMageMilitarySkillHook(), new GunMageMagelockWeaponHook()}),
	
	HIGHWAYMAN(R.string.highwayman_name, false,
			 new Pair[] {SkillEnum.HAND_WEAPON.pair(1)},
			 new Pair[] {SkillEnum.ARCHERY.pair(3), SkillEnum.CROSSBOW.pair(3), SkillEnum.HAND_WEAPON.pair(3), SkillEnum.PISTOL.pair(3),
			 			SkillEnum.UNARMED.pair(3), SkillEnum.BRIBERY.pair(2), SkillEnum.DECEPTION.pair(3), SkillEnum.DISGUISE.pair(3),
						SkillEnum.INTERROGATION.pair(2), SkillEnum.NEGOTIATION.pair(4), SkillEnum.ROPE_USE.pair(4), SkillEnum.SEDUCTION.pair(4),
						SkillEnum.SNEAK.pair(4), SkillEnum.SURVIVAL.pair(2)},
			 new Ability[] {AbilityEnum.AMBUSH.make(), AbilityEnum.SADDLE_SHOT.make()},
			 new Ability[] {AbilityEnum.AMBUSH.make(), AbilityEnum.APPRAISE.make(), AbilityEnum.BINDING.make(), AbilityEnum.EXPERT_RIDER.make(),
			 				AbilityEnum.FAST_DRAW.make(), AbilityEnum.FAST_RELOAD.make(), AbilityEnum.LIGHT_CAVALRY.make(), AbilityEnum.PROWL.make(),
							AbilityEnum.RIDE_BY_ATTACK.make(), AbilityEnum.SADDLE_SHOT.make(), AbilityEnum.SWIFT_HUNTER.make(), AbilityEnum.SWIFT_RIDER.make(),
							AbilityEnum.TRACELESS_PATH.make(), AbilityEnum.TWO_WEAPON_FIGHTING.make(), AbilityEnum.WAYLAY.make()},
			 null, null,
			 null,
			 new Connection[] {Connection.make("criminal")},
			 75,
			 new Loot[] {},
			 null, null,
			 new zzCreateCharacterHook[] {new HighwaymanHook()}),
	
	INVESTIGATOR(R.string.investigator_name, false,
			 new Pair[] {SkillEnum.DETECTION.pair(1), SkillEnum.FORENSIC_SCIENCE.pair(1), SkillEnum.INTERROGATION.pair(1), SkillEnum.LAW.pair(1),
			 			SkillEnum.MEDICINE.pair(1), SkillEnum.SNEAK.pair(1)},
			 new Pair[] {SkillEnum.HAND_WEAPON.pair(2), SkillEnum.PISTOL.pair(2), SkillEnum.UNARMED.pair(2), SkillEnum.CRYPTOGRAPHY.pair(4),
			 			SkillEnum.DECEPTION.pair(4), SkillEnum.ETIQUETTE.pair(2), SkillEnum.FORENSIC_SCIENCE.pair(4), SkillEnum.INTERROGATION.pair(4),
						SkillEnum.LAW.pair(4), SkillEnum.MEDICINE.pair(2), SkillEnum.NEGOTIATION.pair(3), SkillEnum.RESEARCH.pair(4),
						SkillEnum.SNEAK.pair(4), SkillEnum.STREETWISE.pair(4)},
			 new Ability[] {AbilityEnum.ASTUTE.make()},
			 new Ability[] {AbilityEnum.ANATOMICAL_PRECISION.make(), AbilityEnum.ASTUTE.make(), AbilityEnum.IRON_WILL.make(), AbilityEnum.LANGUAGE.make(),
			 				AbilityEnum.PROWL.make(), AbilityEnum.SIGNAL_LANGUAGE.make(), AbilityEnum.TRUTH_READER.make()},
			 null, null,
			 null,
			 new Connection[] {Connection.make("any")},
			 100,null,null,null,
			 new zzCreateCharacterHook[] {new LanguageHook(), new InvestigatorMilitarySkillHook(), new InvestigatorHyperPerceptionHook()}),
			 
	/*TEMPLATE(R.string.arcanist_name, false,
	 new Pair[] {},
	 new Pair[] {},
	 new Ability[] {},
	 new Ability[] {},
	 new Spell[] {},
	 new Spell[] {},
	 new Connection[] {},
	 new Connection[] {},
	 0,
	 new Loot[] {},
	 new String[] {},
	 new zzPrereqCheck[] {giftedPrereq()},
	 new zzCreateCharacterHook[] {new ArcanistHook()}),*/
					  
	//PIRATE(R.string.pirate_name,null,null,null,null,null,null,null,null),
	WARCASTER(R.string.warcaster_name,false,null,null,null,null,null,null,null,null,0,null,null,null,null);			  
	
	//Done!
	private Career(int pNameResource, boolean pStartOnly,
					Pair<Skill, Integer>[] pStartSkills, Pair<Skill, Integer>[] pSkills,
				    Ability[] pStartAbilities, Ability[] pAbilities,
					Spell[] pStartSpells, Spell[] pSpells,
					Connection[] pStartConnections, Connection[] pConnections,
					int pGold, Loot[] pLoot, String[] pSpecialRules,
					zzPrereqCheck[] pPrereqChecks, zzCreateCharacterHook[] pPostCreateHook)
	{
		Context c = IKRPGApp.getContext();
		name = c.getString(pNameResource);
		startOnly = pStartOnly;
		startingSkills = pStartSkills != null ? Arrays.asList(pStartSkills) : new ArrayList<Pair<Skill, Integer>>(10);
		careerSkills = pSkills != null ? Arrays.asList(pSkills) : new ArrayList<Pair<Skill, Integer>>(10);
		startingAbilities = pStartAbilities != null ? Arrays.asList(pStartAbilities) : new ArrayList<Ability>(10);
		careerAbilities = pAbilities != null ? Arrays.asList(pAbilities) : new ArrayList<Ability>(10);
		startingSpells = pStartSpells != null ? Arrays.asList(pStartSpells) : new ArrayList<Spell>(10);
		careerSpells = pSpells != null ? Arrays.asList(pSpells) : new ArrayList<Spell>(10);
		startingConnections = pStartConnections != null ? Arrays.asList(pStartConnections) : new ArrayList<Connection>(5);
		careerConnections = pConnections != null ? Arrays.asList(pConnections) : new ArrayList<Connection>(5);
		startGold = pGold;
		startLoot = pLoot != null ? Arrays.asList(pLoot) : new ArrayList<Loot>(5);
		specialRules = pSpecialRules != null ? Arrays.asList(pSpecialRules) : new ArrayList<String>(5);
		prereqChecks = pPrereqChecks != null ? Arrays.asList(pPrereqChecks) : new ArrayList<zzPrereqCheck>(5);
		postCreateHooks = pPostCreateHook != null ? Arrays.asList(pPostCreateHook) : new ArrayList<zzCreateCharacterHook>(10);
	}
	
	@Override public String toString(){return displayName();}
	
	private String name;
	private boolean startOnly;
	private Collection<Pair<Skill, Integer>> startingSkills;
	private Collection<Pair<Skill, Integer>> careerSkills;
	private Collection<Ability> startingAbilities;
	private Collection<Ability> careerAbilities;
	private Collection<Spell> startingSpells;
	private Collection<Spell> careerSpells;
	private Collection<Connection> startingConnections;
	private Collection<Connection> careerConnections;
	private int startGold;
	private Collection<Loot> startLoot;
	private List<String> specialRules;
	private Collection<zzPrereqCheck> prereqChecks;
	private Collection<zzCreateCharacterHook> postCreateHooks;
	
	public String displayName(){return name;}
	public boolean startOnly(){return startOnly;}
	public Collection<Pair<Skill, Integer>> startingSkills(){return Collections.unmodifiableCollection(startingSkills);}
	public Collection<Pair<Skill, Integer>> careerSkills(){return Collections.unmodifiableCollection(careerSkills);}
	public Collection<Ability> startingAbilities(){return Collections.unmodifiableCollection(startingAbilities);}
	public Collection<Ability> careerAbilities(){return Collections.unmodifiableCollection(careerAbilities);}
	public Collection<Spell> startingSpells(){return Collections.unmodifiableCollection(startingSpells);}
	public Collection<Spell> careerSpells(){return Collections.unmodifiableCollection(careerSpells);}
	public Collection<Connection> startingConnections(){return Collections.unmodifiableCollection(startingConnections);}
	public Collection<Connection> careerConnections(){return Collections.unmodifiableCollection(careerConnections);}
	public int startGold(){return startGold;}
	public Collection<Loot> startLoot(){return startLoot;}
	public List<String> specialRules(){return specialRules;}
	Collection<zzCreateCharacterHook> postCreateHooks(){return postCreateHooks;}
	
	@Override
	public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
	{
		Set<Career> careers = myChar.careers;
		
		//duplicates not allowed
		if(careers != null && careers.contains(this)){return new zzPrereqCheckResult(false, null);}
		
		//no prereq means allowed
		if(prereqChecks == null || prereqChecks.size() == 0){return new zzPrereqCheckResult(true, null);}
		else
		{
			for(zzPrereqCheck check : prereqChecks)
			{
				if(!check.meetsPrereq(myChar).isAllowed){return new zzPrereqCheckResult(false, null);}
			}
			return new zzPrereqCheckResult(true, null);
		}
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
	
	public static zzPrereqCheck humanPrereq(){
		return new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar){
				if(myChar.race == null){return new zzPrereqCheckResult(false, null);}
				return new zzPrereqCheckResult(myChar.race == Race.HUMAN, null);
			}
		};
	}
	
	public static zzPrereqCheck trollkinPrereq(){
		return new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar){
				if(myChar.race == null){return new zzPrereqCheckResult(false, null);}
				return new zzPrereqCheckResult(myChar.race == Race.TROLLKIN, null);
			}
		};
	}
	
	/* Hooks! */
	
	public static class ArcaneMechanikHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(2);
			l.add(new Skill(SkillEnum.RIFLE)); l.add(new Skill(SkillEnum.HAND_WEAPON));
			return l;
		}
	}
	
	public static class ArcanistHook extends zzCreateCharacterHook
	{
		@Override public void doDefaultCase()
		{
			myChar.abilities.add(AbilityEnum.GREAT_POWER.make());
			myChar.abilities.add(AbilityEnum.RUNE_READER.make());
		}
		
		@Override public boolean hasUI(){return false;}
		@Override public int getPriority(){return 0;}
		@Override public void undoHook()
		{
			//TODO: latent error needs to be fixed
			myChar.abilities.remove(AbilityEnum.GREAT_POWER);
			myChar.abilities.add(AbilityEnum.RUNE_READER.make());
		}
	}
	
	public static class AristocratWeaponHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(3);
			l.add(new Skill(SkillEnum.ARCHERY)); l.add(new Skill(SkillEnum.PISTOL)); l.add(new Skill(SkillEnum.RIFLE));
			return l;
		}
	}
	
	public static class LanguageHook extends zzChooseOneHook<Language>
	{
		private Language chosenLanguage;

		@Override public int getPriority(){return 80;}
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
	
	//TODO: Finish
	public static class BountyHunterHook extends zzCreateCharacterHook
	{
		@Override public int getPriority(){return 49;}
		@Override public boolean hasUI(){return false;}
		@Override public void undoHook(){}
		@Override public void doDefaultCase(){}
	}
	
	public static class CutthroatHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(3);
			l.add(new Skill(SkillEnum.CROSSBOW)); l.add(new Skill(SkillEnum.THROWN_WEAPON)); l.add(new Skill(SkillEnum.UNARMED));
			return l;
		}
	}
	
	public static class ExplorerMilitarySkillHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(4);
			l.add(SkillEnum.ARCHERY.make()); l.add(SkillEnum.HAND_WEAPON.make()); l.add(SkillEnum.PISTOL.make()); l.add(SkillEnum.RIFLE.make());
			return l;
		}
	}
	
	public static class FellCallerHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(2);
			l.add(SkillEnum.GREAT_WEAPON.make()); l.add(SkillEnum.HAND_WEAPON.make());
			return l;
		}
	}
	
	public static class FieldMechanikMilitarySkillHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(2);
			l.add(SkillEnum.PISTOL.make()); l.add(SkillEnum.HAND_WEAPON.make());
			return l;
		}
	}
	
	public static class FieldMechanikJackHook extends zzCreateCharacterHook
	{
		@Override public int getPriority(){return 49;}
		@Override public boolean hasUI(){return false;}
		@Override public void undoHook(){}
		@Override public void doDefaultCase(){}
	}
	
	public static class GunMageMilitarySkillHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(2);
			l.add(SkillEnum.PISTOL.make()); l.add(SkillEnum.RIFLE.make());
			return l;
		}
	}
	
	public static class GunMageMagelockWeaponHook extends zzCreateCharacterHook
	{
		@Override public int getPriority(){return 49;}
		@Override public boolean hasUI(){return false;}
		@Override public void undoHook(){}
		@Override public void doDefaultCase(){}
	}
	
	public static class HighwaymanHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(3);
			l.add(SkillEnum.ARCHERY.make()); l.add(SkillEnum.CROSSBOW.make()); l.add(SkillEnum.PISTOL.make());
			return l;
		}
	}
	
	public static class InvestigatorMilitarySkillHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(2);
			l.add(SkillEnum.PISTOL.make()); l.add(SkillEnum.HAND_WEAPON.make());
			return l;
		}
	}
	
	public static class InvestigatorHyperPerceptionHook extends zzCreateCharacterHook
	{
		private static final String THE_KEY = "WhatAWonderKeyThisCouldBe";
		
		@Override public int getPriority(){return 0;}
		@Override public boolean hasUI(){return false;}
		@Override public void doDefaultCase()
		{
			boolean additionNeeded = myChar.abilities.contains(AbilityEnum.HYPER_PERCEPTION.make());
			getArguments().putBoolean(THE_KEY, additionNeeded);
			if(additionNeeded)
			{myChar.abilities.add(AbilityEnum.HYPER_PERCEPTION.make());}
		}
		
		@Override public void undoHook()
		{
			boolean additionWasNeeded = getArguments().getBoolean(THE_KEY);
			if(additionWasNeeded){myChar.abilities.remove(AbilityEnum.HYPER_PERCEPTION.make());}
		}
	}
}
