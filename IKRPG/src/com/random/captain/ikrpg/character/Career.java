package com.random.captain.ikrpg.character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;

import com.random.captain.ikrpg.IKRPGApp;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.gear.Loot;
import com.random.captain.ikrpg.util.BundleConstants;

public enum Career implements zzPrereqCheck
{
	/* I'm never a fan of suppressing warnings, but it seems that it has to be done here.
	 * There's just no way at compile time to know that I'm telling the truth.
	 * Such is the nature of the current format.
	 * 
	 * An eventual refactor could generate the enum's data at runtime,
	 * and get around the structure that causes this warning.
	 */
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
				50, new Loot[]{}, null, null,
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
				50, new Loot[]{}, null, null,
				new zzPrereqCheck[]{giftedPrereq()},
				new zzCreateCharacterHook[] {new ArcaneMechanikHook()}),
	
	
	@SuppressWarnings("unchecked")
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
			 75, null, null, null,
			 new zzPrereqCheck[]{giftedPrereq()},
			 new zzCreateCharacterHook[] {new ArcanistHook()}),
			 
	@SuppressWarnings("unchecked")
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
			 200, null, null, null,
			 new zzPrereqCheck[]{humanPrereq()},
			 new zzCreateCharacterHook[] {new AristocratWeaponHook(), new LanguageHook()}),
	
	@SuppressWarnings("unchecked")
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
			 75, null, null, null,
			 null,
			 new zzCreateCharacterHook[] {new BountyHunterHook()}),
	
	@SuppressWarnings("unchecked")
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
			 75, null, null, null,
			 null,
			 new zzCreateCharacterHook[] {new CutthroatHook()}),
	
	@SuppressWarnings("unchecked")
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
			 75, null, null, null,
			 null, null),
	
	@SuppressWarnings("unchecked")
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
			 null, null,
			 new zzCreateCharacterHook[] {new ExplorerMilitarySkillHook(), new LanguageHook()}),
	
	@SuppressWarnings("unchecked")
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
			 75, null, null, null,
			 new zzPrereqCheck[] {trollkinPrereq()},
			 new zzCreateCharacterHook[] {new FellCallerHook()}),
	
	@SuppressWarnings("unchecked")
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
			 null, null,
			 new zzCreateCharacterHook[] {new FieldMechanikMilitarySkillHook(), new FieldMechanikJackHook()}),
	
	@SuppressWarnings("unchecked")
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
			 null, null,
			 new zzPrereqCheck[] {giftedPrereq()},
			 new zzCreateCharacterHook[] {new GunMageMilitarySkillHook(), new GunMageMagelockWeaponHook()}),
	
	@SuppressWarnings("unchecked")
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
			 null, null, null,
			 new zzCreateCharacterHook[] {new HighwaymanHook()}),
	
	@SuppressWarnings("unchecked")
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
			 100,null,null,null, null,
			 new zzCreateCharacterHook[] {new LanguageHook(), new InvestigatorMilitarySkillHook(), new InvestigatorHyperPerceptionHook()}),
	
	@SuppressWarnings("unchecked")
	IRON_FANG(R.string.iron_fang_name, true,
			 new Pair[] {SkillEnum.GREAT_WEAPON.pair(1), SkillEnum.SHIELD.pair(1), SkillEnum.COMMAND.pair(1), SkillEnum.SURVIVAL.pair(1)},
			 new Pair[] {SkillEnum.GREAT_WEAPON.pair(4), SkillEnum.LANCE.pair(4), SkillEnum.SHIELD.pair(4), SkillEnum.UNARMED.pair(3),
			 			SkillEnum.COMMAND.pair(4), SkillEnum.SURVIVAL.pair(2)},
			 new Ability[] {AbilityEnum.FAST_REARM.make("blasting pike"), AbilityEnum.SPECIALIZATION.make("blasting pike")},
			 new Ability[] {AbilityEnum.DEFENSIVE_LINE.make(), AbilityEnum.FAST_REARM.make("blasting pike"), AbilityEnum.HYPER_AWARENESS.make(),
			 				AbilityEnum.LOAD_BEARING.make(), AbilityEnum.PRECISION_STRIKE.make(), AbilityEnum.RELENTLESS_CHARGE.make(),
							AbilityEnum.ROCK_SOLID.make(), AbilityEnum.SPECIALIZATION.make("blasting pike"), AbilityEnum.SWIFT_RIDER.make()},
			 null,null,
			 new Connection[] {Connection.make("Khadoran military")},
			 new Connection[] {Connection.make("Khadoran military")},
			 25,
			 new Loot[] {},
			 null, new HolyCrapWhatIsWrongWithMe(){
				 @Override
				 public Collection<Career> 	getEmLater(){
					 return ironFangCareers();
				 } 
			 },
			 new zzPrereqCheck[] {humanPrereq()},
			 null),
	
	@SuppressWarnings("unchecked")
	KNIGHT(R.string.knight_name, false,
			 new Pair[] {SkillEnum.GREAT_WEAPON.pair(1), SkillEnum.HAND_WEAPON.pair(1), SkillEnum.SHIELD.pair(1), SkillEnum.COMMAND.pair(1),
			 			SkillEnum.ETIQUETTE.pair(1), SkillEnum.LORE.pair("knightly order", 1)},
			 new Pair[] {SkillEnum.GREAT_WEAPON.pair(4), SkillEnum.HAND_WEAPON.pair(4), SkillEnum.LANCE.pair(4), SkillEnum.SHIELD.pair(4),
			 			SkillEnum.UNARMED.pair(3), SkillEnum.COMMAND.pair(4), SkillEnum.ETIQUETTE.pair(2), SkillEnum.LAW.pair(2)},
			 new Ability[] {AbilityEnum.CLEAVE.make(), AbilityEnum.DEFENDER.make()},
			 new Ability[] {AbilityEnum.CAVALRY_CHARGE.make(), AbilityEnum.CLEAVE.make(), AbilityEnum.COMBAT_RIDER.make(), AbilityEnum.DEFENDER.make(),
			 				AbilityEnum.DEFENSIVE_LINE.make(), AbilityEnum.EXPERT_RIDER.make(), AbilityEnum.IRON_WILL.make(), AbilityEnum.LOAD_BEARING.make(),
							AbilityEnum.NATURAL_LEADER.make(), AbilityEnum.PRECISION_STRIKE.make(), AbilityEnum.PRESS_THE_ATTACK.make(),
							AbilityEnum.RELENTLESS_CHARGE.make(), AbilityEnum.RIDE_BY_ATTACK.make(), AbilityEnum.SHIELD_SLAM.make()},
			 null, null,
			 new Connection[] {Connection.make("knightly order")},
			 new Connection[] {Connection.make("knightly order")},
			 100, null, null, null,
			 new zzPrereqCheck[] {humanOrIosanPrereq()},
			 null),
	
	@SuppressWarnings("unchecked")
	MAGE_HUNTER(R.string.mage_hunter_name, false,
			 new Pair[] {SkillEnum.HAND_WEAPON.pair(1), SkillEnum.CLIMBING.pair(1), SkillEnum.JUMPING.pair(1), SkillEnum.SNEAK.pair(1),
			 			SkillEnum.TRACKING.pair(1)},
			 new Pair[] {SkillEnum.ARCHERY.pair(4), SkillEnum.CROSSBOW.pair(4), SkillEnum.HAND_WEAPON.pair(4), SkillEnum.THROWN_WEAPON.pair(2),
			 			SkillEnum.DECEPTION.pair(2), SkillEnum.DISGUISE.pair(2), SkillEnum.ROPE_USE.pair(3), SkillEnum.SNEAK.pair(4),
						SkillEnum.SURVIVAL.pair(2), SkillEnum.TRACKING.pair(4)},
			 new Ability[] {AbilityEnum.ARCANE_ASSASSIN.make(), AbilityEnum.IRON_WILL.make()},
			 new Ability[] {AbilityEnum.ARCANE_ASSASSIN.make(), AbilityEnum.CAMOUFLAGE.make(), AbilityEnum.CRACKSHOT.make(), AbilityEnum.CROSSBOWMAN.make(),
			 				AbilityEnum.FAST_DRAW.make(), AbilityEnum.FAST_RELOAD.make(), AbilityEnum.IRON_WILL.make(), AbilityEnum.MAGE_KILLER.make(),
							AbilityEnum.PARRY.make(), AbilityEnum.QUICK_WORK.make(), AbilityEnum.SHADOW_MAGIC.make(), AbilityEnum.TRACELESS_PATH.make()},
			 null, null,
			 new Connection[] {Connection.make("Retribution of Scyrah")},
			 new Connection[] {Connection.make("Retribution of Scyrah")},
			 75, null, null, null,
			 new zzPrereqCheck[] {iosanPrereq()},
			 new zzCreateCharacterHook[] {new MageHunterHook()}),
	
	@SuppressWarnings("unchecked")
	MAN_AT_ARMS(R.string.man_at_arms_name, false,
			 new Pair[] {SkillEnum.GREAT_WEAPON.pair(1), SkillEnum.SHIELD.pair(1), SkillEnum.COMMAND.pair(1), SkillEnum.DETECTION.pair(1)},
			 new Pair[] {SkillEnum.GREAT_WEAPON.pair(4), SkillEnum.HAND_WEAPON.pair(3), SkillEnum.PISTOL.pair(3), SkillEnum.SHIELD.pair(40),
			 			SkillEnum.UNARMED.pair(3), SkillEnum.COMMAND.pair(3), SkillEnum.CRAFT.pair("Metalworking", 2)},
			 new Ability[] {AbilityEnum.DEFENSIVE_LINE.make(), AbilityEnum.SHIELD_GUARD.make()},
			 new Ability[] {AbilityEnum.BODYGUARD.make(), AbilityEnum.CLEAVE.make(), AbilityEnum.DEFENSIVE_LINE.make(), AbilityEnum.GIRDED.make(),
			 				AbilityEnum.IRON_WILL.make(), AbilityEnum.LOAD_BEARING.make(), AbilityEnum.RETALIATORY_STRIKE.make(),
							AbilityEnum.SET_DEFENSE.make(), AbilityEnum.SHIELD_GUARD.make(), AbilityEnum.SPECIALIZATION.make("halberd"),
							AbilityEnum.SPECIALIZATION.make("spear")},
			 null, null,
			 null,
			 new Connection[] {Connection.make("employer")},
			 100, null, null,
			 null, null,
			 new zzCreateCharacterHook[] {new ManAtArmsHook()}),
	
	@SuppressWarnings("unchecked")
	MILITARY_OFFICER(R.string.military_officer_name, false,
	 new Pair[] {SkillEnum.HAND_WEAPON.pair(1), SkillEnum.COMMAND.pair(1), SkillEnum.MEDICINE.pair(1), SkillEnum.NAVIGATION.pair(1)},
	 new Pair[] {SkillEnum.GREAT_WEAPON.pair(4), SkillEnum.HAND_WEAPON.pair(4), SkillEnum.PISTOL.pair(4), SkillEnum.COMMAND.pair(4),
	 				SkillEnum.CRYPTOGRAPHY.pair(4), SkillEnum.ETIQUETTE.pair(4), SkillEnum.INTERROGATION.pair(4), SkillEnum.LAW.pair(4),
					SkillEnum.MEDICINE.pair(4), SkillEnum.NAVIGATION.pair(4), SkillEnum.ORATORY.pair(4)},
	 new Ability[] {AbilityEnum.BATTLE_PLAN_CALL_TO_ACTION.make(), AbilityEnum.NATURAL_LEADER.make(), AbilityEnum.TEAM_LEADER.make()},
	 new Ability[] {AbilityEnum.JACK_MARSHALL.make(), AbilityEnum.ACE_COMMANDER.make(), AbilityEnum.BATTLE_COMMANDER.make(),
	 				AbilityEnum.BATTLE_PLAN_CALL_TO_ACTION.make(), AbilityEnum.BATTLE_PLAN_COORDINATED_STRIKE.make(), AbilityEnum.BATTLE_PLAN_DESPERATE_PACE.make(),
					AbilityEnum.BATTLE_PLAN_GO_TO_GROUND.make(), AbilityEnum.CAVALRY_CHARGE.make(), AbilityEnum.DEFENDER.make(), AbilityEnum.DRIVE_ASSUALT.make(),
					AbilityEnum.DRIVE_PRONTO.make(), AbilityEnum.EXPERT_RIDER.make(), AbilityEnum.GOOD_BREEDING.make(), AbilityEnum.NATURAL_LEADER.make(),
					AbilityEnum.PORT_OF_CALL.make(), AbilityEnum.RIDE_BY_ATTACK.make(), AbilityEnum.SADDLE_SHOT.make(), AbilityEnum.SIGNAL_LANGUAGE.make(),
					AbilityEnum.TEAM_LEADER.make()},
	 null, null, null,
	 new Connection[] {Connection.make("mercenary company"), Connection.make("kingdom's military")},
	 100,
	 new Loot[] {},
	 null,null, null,
	 new zzCreateCharacterHook[] {new MilitaryOfficerWeaponHook()}),
	
	@SuppressWarnings("unchecked")
	PIRATE(R.string.pirate_name, false,
	 new Pair[] {SkillEnum.HAND_WEAPON.pair(1), SkillEnum.CLIMBING.pair(1), SkillEnum.INTIMIDATION.pair(1), SkillEnum.SAILING.pair(1),
	 				SkillEnum.SWIMMING.pair(1)},
	 new Pair[] {SkillEnum.HAND_WEAPON.pair(3), SkillEnum.LIGHT_ARTILLERY.pair(2), SkillEnum.PISTOL.pair(3), SkillEnum.RIFLE.pair(2),
	 				SkillEnum.THROWN_WEAPON.pair(3), SkillEnum.UNARMED.pair(3), SkillEnum.COMMAND.pair(2), SkillEnum.DECEPTION.pair(3),
					SkillEnum.NAVIGATION.pair(4), SkillEnum.NEGOTIATION.pair(2), SkillEnum.ROPE_USE.pair(4), SkillEnum.SAILING.pair(4)},
	 new Ability[] {AbilityEnum.GANG.make(), AbilityEnum.STEADY.make(), AbilityEnum.SPECIALIZATION.make("Cutlass")},
	 new Ability[] {AbilityEnum.BINDING.make(), AbilityEnum.DISEASE_RESISTANCE.make(), AbilityEnum.GANG.make(), AbilityEnum.GUNFIGHTER.make(),
	 				AbilityEnum.HEADBUTT.make(), AbilityEnum.LANGUAGE.make(), AbilityEnum.PORT_OF_CALL.make(), AbilityEnum.QUICK_WORK.make(),
					AbilityEnum.SPECIALIZATION.make("Cutlass"), AbilityEnum.STEADY.make(), AbilityEnum.SUCKER.make(), AbilityEnum.WAYLAY.make()},
	 null, null, null,
	 new Connection[] {Connection.make("pirate crew")},
	 75,
	 null, null, null, null,
	 new zzCreateCharacterHook[] {new PirateHook()}),
	
	@SuppressWarnings("unchecked")
	PISTOLEER(R.string.pistoleer_name, false,
	 new Pair[] {SkillEnum.PISTOL.pair(1), SkillEnum.DETECTION.pair(1), SkillEnum.INTIMIDATION.pair(1), SkillEnum.SNEAK.pair(1)},
	 new Pair[] {SkillEnum.PISTOL.pair(4), SkillEnum.CRAFT.pair("gunsmithing", 4), SkillEnum.SNEAK.pair(3)},
	 new Ability[] {AbilityEnum.FAST_DRAW.make(), AbilityEnum.GUNFIGHTER.make(), AbilityEnum.RETURN_FIRE.make()},
	 new Ability[] {AbilityEnum.CHAIN_ATTACK_PIN_DOWN.make(), AbilityEnum.DODGER.make(), AbilityEnum.FAST_DRAW.make(), AbilityEnum.FAST_RELOAD.make(),
	 				AbilityEnum.GUNFIGHTER.make(), AbilityEnum.RETURN_FIRE.make(), AbilityEnum.SWIFT_HUNTER.make(), AbilityEnum.TARGETEER.make(),
					AbilityEnum.TWO_WEAPON_FIGHTING.make()},
	 null, null, null, null,
	 50,
	 new Loot[] {},
	 null, null, null,
	 new zzCreateCharacterHook[] {new PistoleerHook()}),
	
	 @SuppressWarnings("unchecked")
	PRIEST_OF_MENOTH(R.string.priest_of_menoth_name, false,
	 new Pair[] {SkillEnum.LORE.pair("Menite faith", 1), SkillEnum.ORATORY.pair(1)},
	 new Pair[] {SkillEnum.GREAT_WEAPON.pair(3), SkillEnum.HAND_WEAPON.pair(3), SkillEnum.SHIELD.pair(2), SkillEnum.COMMAND.pair(2),
	 				SkillEnum.CRYPTOGRAPHY.pair(2), SkillEnum.ETIQUETTE.pair(4), SkillEnum.LAW.pair(4), SkillEnum.MEDICINE.pair(4),
					SkillEnum.NEGOTIATION.pair(4), SkillEnum.ORATORY.pair(4), SkillEnum.RESEARCH.pair(4)},
	 new Ability[] {AbilityEnum.DISPEL.make()},
	 new Ability[] {AbilityEnum.CHOIR.make(), AbilityEnum.LANGUAGE.make(), AbilityEnum.NATURAL_LEADER.make(), AbilityEnum.RALLYING_CRY.make(),
	 				AbilityEnum.UNIVERSITY_EDUCATION.make()},
	 new Spell[] {Spell.GUIDED_BLADE, Spell.IGNITE, Spell.IMMOLATION},
	 new Spell[] {Spell.FLAMES_OF_WRATH, Spell.GUIDED_BLADE, Spell.INFLUENCE, Spell.PROTECTION_FROM_FIRE, Spell.ASHEN_CLOUD,
	 				Spell.BANISHING_WARD, Spell.HYMN_OF_BATTLE, Spell.HYMN_OF_PASSAGE, Spell.IGNITE, Spell.IMMOLATION,
					Spell.RIGHTEOUS_FLAMES, Spell.VISION, Spell.WALL_OF_FIRE, Spell.CLEANSING_FIRE, Spell.CREVASSE,
					Spell.CRUSADERS_CALL, Spell.HEX_BLAST, Spell.LAMENTATION, Spell.PURIFICATION, Spell.TRUE_PATH,
					Spell.ASHES_TO_ASHES, Spell.BLAZING_EFFIGY, Spell.HYMN_OF_SHIELDING},
	 new Connection[] {Connection.make("Menite temple")},
	 new Connection[] {Connection.make("church")},
	 75,
	 null, null, null,
	 new zzPrereqCheck[] {giftedPrereq(), humanPrereq()},
	 new zzCreateCharacterHook[] {new PriestWeaponHook()}),
	 
	@SuppressWarnings("unchecked")
	PRIEST_OF_MORROW(R.string.priest_of_morrow_name, false,
	new Pair[] {SkillEnum.LORE.pair("Morrowan faith", 1), SkillEnum.MEDICINE.pair(1)},
	new Pair[] {SkillEnum.GREAT_WEAPON.pair(3), SkillEnum.HAND_WEAPON.pair(3), SkillEnum.SHIELD.pair(2), SkillEnum.COMMAND.pair(2),
		SkillEnum.CRYPTOGRAPHY.pair(2), SkillEnum.ETIQUETTE.pair(4), SkillEnum.LAW.pair(4), SkillEnum.MEDICINE.pair(4),
		SkillEnum.NEGOTIATION.pair(4), SkillEnum.ORATORY.pair(4), SkillEnum.RESEARCH.pair(4)},
	new Ability[] {AbilityEnum.EMPOWER.make()},
	new Ability[] {AbilityEnum.CHOIR.make(), AbilityEnum.LANGUAGE.make(), AbilityEnum.NATURAL_LEADER.make(), AbilityEnum.RALLYING_CRY.make(),
					 AbilityEnum.UNIVERSITY_EDUCATION.make()},
	new Spell[] {Spell.BLADE_OF_RADIANCE, Spell.SOLOVINS_BOON, Spell.TRUE_SIGHT},
	new Spell[] {Spell.BLESSING_OF_HEALTH, Spell.GUIDED_BLADE, Spell.LIGHT_IN_THE_DARKNESS, Spell.SOLOVINS_BOON,
					Spell.AURA_OF_PROTECTION, Spell.BANISHING_WARD, Spell.BLADE_OF_RADIANCE, Spell.BLESSINGS_OF_WAR,
					Spell.EYES_OF_TRUTH, Spell.HAND_OF_FATE, Spell.SHIELD_OF_FAITH, Spell.TRIAGE, Spell.TRUE_SIGHT,
					Spell.BLESSING_OF_MORROW, Spell.CRUSADERS_CALL, Spell.DAYLIGHT, Spell.PRAYER_FOR_GUIDANCE,
					Spell.SANGUINE_BLESSING, Spell.SUNBURST, Spell.TRUE_PATH, Spell.FORCE_OF_FAITH, Spell.HEAL,
					Spell.STAR_FIRE},
	new Connection[] {Connection.make("Morrowan church")},
	new Connection[] {Connection.make("church")},
	75,
	null, null, null,
	new zzPrereqCheck[] {giftedPrereq(), humanPrereq()},
	new zzCreateCharacterHook[] {new PriestWeaponHook()}),

	@SuppressWarnings("unchecked")
	RANGER(R.string.ranger_name, false,
	 new Pair[] {SkillEnum.HAND_WEAPON.pair(1), SkillEnum.DETECTION.pair(1), SkillEnum.SNEAK.pair(1), SkillEnum.SURVIVAL.pair(1),
	 				SkillEnum.TRACKING.pair(1)},
	 new Pair[] {SkillEnum.ARCHERY.pair(4), SkillEnum.CROSSBOW.pair(3), SkillEnum.HAND_WEAPON.pair(2), SkillEnum.PISTOL.pair(2),
	 				SkillEnum.RIFLE.pair(4), SkillEnum.THROWN_WEAPON.pair(4), SkillEnum.UNARMED.pair(3), SkillEnum.COMMAND.pair(3),
					SkillEnum.CRAFT.pair(2), SkillEnum.CRYPTOGRAPHY.pair(1), SkillEnum.MEDICINE.pair(3), SkillEnum.NAVIGATION.pair(4),
					SkillEnum.ROPE_USE.pair(4), SkillEnum.SNEAK.pair(4), SkillEnum.SURVIVAL.pair(4), SkillEnum.TRACKING.pair(4)},
	 new Ability[] {AbilityEnum.CAMOUFLAGE.make(), AbilityEnum.PATHFINDER.make()},
	 new Ability[] {AbilityEnum.BATTLE_PLAN_GO_TO_GROUND.make(), AbilityEnum.BATTLE_PLAN_RECONNAISSANCE.make(), AbilityEnum.BATTLE_PLAN_SHADOW.make(),
	 				AbilityEnum.CAMOUFLAGE.make(), AbilityEnum.DISEASE_RESISTANCE.make(), AbilityEnum.FAST_RELOAD.make(),
					AbilityEnum.LIGHT_CAVALRY.make(), AbilityEnum.NIGHT_FIGHTER.make(), AbilityEnum.PATHFINDER.make(),
					AbilityEnum.PROWL.make(), AbilityEnum.SADDLE_SHOT.make(), AbilityEnum.SIGNAL_LANGUAGE.make(),
					AbilityEnum.SWIFT_HUNTER.make(), AbilityEnum.SWIFT_RIDER.make(), AbilityEnum.TRACELESS_PATH.make()},
	 null, null, null, null,
	 75,
	 null, null, null, null,
	 new zzCreateCharacterHook[] {new RangerWeaponHook()}),
	
	@SuppressWarnings("unchecked")
	RIFLEMAN(R.string.rifleman_name, false,
	 new Pair[] {SkillEnum.RIFLE.pair(1), SkillEnum.CLIMBING.pair(1), SkillEnum.DETECTION.pair(1), SkillEnum.SURVIVAL.pair(1)},
	 new Pair[] {SkillEnum.RIFLE.pair(4), SkillEnum.CRAFT.pair("gunsmithing",4), SkillEnum.SURVIVAL.pair(3)},
	 new Ability[] {AbilityEnum.CRACKSHOT.make(), AbilityEnum.DUAL_SHOT.make(), AbilityEnum.MARKSMAN.make()},
	 new Ability[] {AbilityEnum.CRACKSHOT.make(), AbilityEnum.DUAL_SHOT.make(), AbilityEnum.FAST_RELOAD.make(), AbilityEnum.MARKSMAN.make(),
	 				AbilityEnum.NIGHT_FIGHTER.make(), AbilityEnum.RETURN_FIRE.make(), AbilityEnum.SADDLE_SHOT.make(),
					AbilityEnum.SNIPER.make(), AbilityEnum.SWIFT_HUNTER.make(), AbilityEnum.TARGETEER.make()},
	 null, null, null, null,
	 50,
	 new Loot[] {}, null,
	 null, null, null),
	 
	@SuppressWarnings("unchecked")
	SOLDIER(R.string.soldier_name, false,
	new Pair[] {SkillEnum.DETECTION.pair(1), SkillEnum.DRIVING.pair(1), SkillEnum.MEDICINE.pair(1), SkillEnum.SURVIVAL.pair(1)},
	new Pair[] {SkillEnum.CROSSBOW.pair(3), SkillEnum.GREAT_WEAPON.pair(4), SkillEnum.LIGHT_ARTILLERY.pair(3),
		SkillEnum.HAND_WEAPON.pair(3), SkillEnum.PISTOL.pair(3), SkillEnum.RIFLE.pair(4), SkillEnum.SHIELD.pair(2),
		SkillEnum.THROWN_WEAPON.pair(3), SkillEnum.UNARMED.pair(3), SkillEnum.COMMAND.pair(3), SkillEnum.MEDICINE.pair(3),
		SkillEnum.NAVIGATION.pair(2), SkillEnum.SNEAK.pair(2), SkillEnum.SURVIVAL.pair(3)},
	new Ability[] {AbilityEnum.FIND_COVER.make(), AbilityEnum.SENTRY.make()},
	new Ability[] {AbilityEnum.JACK_MARSHALL.make(), AbilityEnum.CAUTIOUS_ADVANCE.make(), AbilityEnum.CAVALRY_CHARGE.make(),
					AbilityEnum.DISEASE_RESISTANCE.make(), AbilityEnum.FAST_RELOAD.make(), AbilityEnum.FIND_COVER.make(),
					AbilityEnum.GRENADIER.make(), AbilityEnum.HIT_THE_DECK.make(), AbilityEnum.LANGUAGE.make(),
					AbilityEnum.RIDE_BY_ATTACK.make(), AbilityEnum.ROLL_WITH_IT.make(), AbilityEnum.SADDLE_SHOT.make(),
					AbilityEnum.SENTRY.make()},
	null, null, null,
	new Connection[] {Connection.make("kingdom military"), Connection.make("mercenary company")},
	100,
	null, null, null, null,
	new zzCreateCharacterHook[] {new SoldierWeaponHook()}),
	
	@SuppressWarnings("unchecked")
	FIRE_SORCERER(R.string.fire_sorcerer_name, true,
	new Pair[] {SkillEnum.DETECTION.pair(1), SkillEnum.SURVIVAL.pair(1)},
	new Pair[] {SkillEnum.ARCHERY.pair(3), SkillEnum.CROSSBOW.pair(3), SkillEnum.HAND_WEAPON.pair(3),SkillEnum.THROWN_WEAPON.pair(2),
				SkillEnum.UNARMED.pair(2), SkillEnum.SNEAK.pair(3), SkillEnum.SURVIVAL.pair(3)},
	new Ability[] {AbilityEnum.IMMUNITY_FIRE.make()},
	new Ability[] {AbilityEnum.CAMOUFLAGE.make(), AbilityEnum.DODGER.make(), AbilityEnum.ELEMENTAL_MASTERY.make(),
					AbilityEnum.TRACELESS_PATH.make()},
	new Spell[] {Spell.FIRE_STARTER, Spell.HOWLING_FLAMES, Spell.WALL_OF_FIRE},
	new Spell[] {Spell.FIRE_STARTER, Spell.FLAMES_OF_WRATH, Spell.PROTECTION_FROM_FIRE, Spell.ASHEN_CLOUD, Spell.EXTINGUISHER,
				Spell.HOWLING_FLAMES, Spell.IGNITE, Spell.IMMOLATION, Spell.WALL_OF_FIRE, Spell.BARRIER_OF_FLAMES,
				Spell.CLEANSING_FIRE, Spell.FLARE, Spell.FUEL_THE_FLAMES, Spell.INFERNO, Spell.ASHES_TO_ASHES,
				Spell.BLAZING_EFFIGY, Spell.SEA_OF_FIRE},
	null, null,
	75,
	null, null, null,
	new zzPrereqCheck[] {giftedPrereq()},
	new zzCreateCharacterHook[] {new SorcererWeaponHook()}),
	
	@SuppressWarnings("unchecked")
	ICE_SORCERER(R.string.ice_sorcerer_name, true,
	 new Pair[] {SkillEnum.DETECTION.pair(1), SkillEnum.SURVIVAL.pair(1)},
	 new Pair[] {SkillEnum.ARCHERY.pair(3), SkillEnum.CROSSBOW.pair(3), SkillEnum.HAND_WEAPON.pair(3),SkillEnum.THROWN_WEAPON.pair(2),
	 SkillEnum.UNARMED.pair(2), SkillEnum.SNEAK.pair(3), SkillEnum.SURVIVAL.pair(3)},
	 new Ability[] {AbilityEnum.IMMUNITY_COLD.make()},
	 new Ability[] {AbilityEnum.CAMOUFLAGE.make(), AbilityEnum.DODGER.make(), AbilityEnum.ELEMENTAL_MASTERY.make(),
	 AbilityEnum.TRACELESS_PATH.make()},
	 new Spell[] {Spell.BLIZZARD, Spell.CHILLER, Spell.ICE_BOLT},
	 new Spell[] {Spell.BLIZZARD, Spell.ICE_SHIELD, Spell.PROTECTION_FROM_COLD, Spell.CHILLER, Spell.FROSTBITE, Spell.ICE_BOLT,
	 				Spell.ICY_GRIP, Spell.SHATTER_STORM, Spell.STAYING_WINTERS_HAND, Spell.BRITTLE_FROST, Spell.DEEP_FREEZE,
					Spell.FROZEN_GROUND, Spell.HOARFROST, Spell.WINTER_STORM, Spell.FREEZING_GRIP, Spell.FREEZING_MIST,
					Spell.WHITE_OUT},
	 null, null,
	 75,
	 null, null, null,
	 new zzPrereqCheck[] {giftedPrereq()},
	 new zzCreateCharacterHook[] {new SorcererWeaponHook()}),
	 
	@SuppressWarnings("unchecked")
	STONE_SORCERER(R.string.stone_sorcerer_name, true,
	 new Pair[] {SkillEnum.DETECTION.pair(1), SkillEnum.SURVIVAL.pair(1)},
	 new Pair[] {SkillEnum.ARCHERY.pair(3), SkillEnum.CROSSBOW.pair(3), SkillEnum.HAND_WEAPON.pair(3),SkillEnum.THROWN_WEAPON.pair(2),
	 SkillEnum.UNARMED.pair(2), SkillEnum.SNEAK.pair(3), SkillEnum.SURVIVAL.pair(3)},
	 new Ability[] {},
	 new Ability[] {AbilityEnum.CAMOUFLAGE.make(), AbilityEnum.DODGER.make(), AbilityEnum.ELEMENTAL_MASTERY.make(),
	 AbilityEnum.TRACELESS_PATH.make()},
	 new Spell[] {Spell.BATTERING_RAM, Spell.SOLID_GROUND, Spell.STONE_STANCE},
	 new Spell[] {Spell.EARTHS_CRADLE, Spell.ENTANGLE, Spell.STONE_STANCE, Spell.BATTERING_RAM, Spell.FORTIFY, Spell.FOXHOLE,
	 				Spell.ROCK_WALL, Spell.SOLID_GROUND, Spell.STONE_STRENGTH, Spell.CREVASSE, Spell.EARTHQUAKE,
					Spell.INHOSPITABLE_GROUND, Spell.RIFT, Spell.ROCK_HAMMER, Spell.EARTHSPLITTER, Spell.OBLITERATION,
					Spell.SHOCK_WAVE},
	 null, null,
	 75,
	 null, null, null,
	 new zzPrereqCheck[] {giftedPrereq()},
	 new zzCreateCharacterHook[] {new SorcererWeaponHook(), new StoneSorcererHook()}),
	 
	@SuppressWarnings("unchecked")
	STORM_SORCERER(R.string.storm_sorcerer_name, true,
	 new Pair[] {SkillEnum.DETECTION.pair(1), SkillEnum.SURVIVAL.pair(1)},
	 new Pair[] {SkillEnum.ARCHERY.pair(3), SkillEnum.CROSSBOW.pair(3), SkillEnum.HAND_WEAPON.pair(3),SkillEnum.THROWN_WEAPON.pair(2),
	 SkillEnum.UNARMED.pair(2), SkillEnum.SNEAK.pair(3), SkillEnum.SURVIVAL.pair(3)},
	 new Ability[] {},
	 new Ability[] {AbilityEnum.CAMOUFLAGE.make(), AbilityEnum.DODGER.make(), AbilityEnum.ELEMENTAL_MASTERY.make(),
	 AbilityEnum.TRACELESS_PATH.make()},
	 new Spell[] {Spell.RAZOR_WIND, Spell.STORM_TOSSED, Spell.WIND_BLAST},
	 new Spell[] {Spell.FAIR_WINDS, Spell.STORM_TOSSED, Spell.WIND_STRIKE, Spell.BOUNDLESS_CHARGE, Spell.CELERITY,
	 				Spell.RAZOR_WIND, Spell.TELEKINESIS, Spell.WIND_BLAST, Spell.WINGS_OF_AIR, Spell.CHAIN_LIGHTNING,
					Spell.DECELERATION, Spell.FOG_OF_WAR, Spell.LIGHTNING_TENDRILS, Spell.ZEPHYR, Spell.RAGING_WINDS,
					Spell.TEMPEST, Spell.TORNADO},
	 null, null,
	 75,
	 null, null, null,
	 new zzPrereqCheck[] {giftedPrereq()},
	 new zzCreateCharacterHook[] {new SorcererWeaponHook(), new StormSorcererHook()}),
	 
	@SuppressWarnings("unchecked")
	SPY(R.string.spy_name, false,
	new Pair[] {SkillEnum.COMMAND.pair(1), SkillEnum.DECEPTION.pair(1), SkillEnum.DETECTION.pair(1), SkillEnum.DISGUISE.pair(1),
				SkillEnum.SNEAK.pair(1)},
	new Pair[] {SkillEnum.HAND_WEAPON.pair(3), SkillEnum.PISTOL.pair(3), SkillEnum.THROWN_WEAPON.pair(3), SkillEnum.UNARMED.pair(3),
				SkillEnum.BRIBERY.pair(4), SkillEnum.COMMAND.pair(3), SkillEnum.CRYPTOGRAPHY.pair(4), SkillEnum.DECEPTION.pair(4),
				SkillEnum.DISGUISE.pair(4), SkillEnum.ESCAPE_ARTIST.pair(4), SkillEnum.ETIQUETTE.pair(4), SkillEnum.FORGERY.pair(4),
				SkillEnum.INTERROGATION.pair(4), SkillEnum.LAW.pair(4), SkillEnum.LOCK_PICKING.pair(2), SkillEnum.NEGOTIATION.pair(4),
				SkillEnum.SEDUCTION.pair(4), SkillEnum.SNEAK.pair(4), SkillEnum.STREETWISE.pair(4)},
	new Ability[] {AbilityEnum.BATTLE_PLAN_SHADOW.make(), AbilityEnum.COVER_IDENTITY.make()},
	new Ability[] {AbilityEnum.BATTLE_PLAN_SHADOW.make(), AbilityEnum.COVER_IDENTITY.make(), AbilityEnum.IRON_WILL.make(),
					AbilityEnum.LANGUAGE.make(), AbilityEnum.POISON_RESISTANCE.make(), AbilityEnum.PROWL.make(),
					AbilityEnum.SIGNAL_LANGUAGE.make(), AbilityEnum.TRUTH_READER.make(), AbilityEnum.WAYLAY.make()},
	null, null,
	new Connection[] {Connection.make("intelligence network")},
	new Connection[] {Connection.make("any")},
	100,
	new Loot[] {},
	null, null, null,
	new zzCreateCharacterHook[] {new SpyWeaponHook(), new LanguageHook()}),
	
	@SuppressWarnings("unchecked")
	STORMBLADE(R.string.stormblade_name, true,
	new Pair[] {SkillEnum.GREAT_WEAPON.pair(1), SkillEnum.COMMAND.pair(1), SkillEnum.DETECTION.pair(1), SkillEnum.ETIQUETTE.pair(1)},
	new Pair[] {SkillEnum.GREAT_WEAPON.pair(4), SkillEnum.COMMAND.pair(4), SkillEnum.ETIQUETTE.pair(2), SkillEnum.MEDICINE.pair(2)},
	new Ability[] {AbilityEnum.BLASTER.make(), AbilityEnum.SPECIALIZATION.make("Storm Glaive")},
	new Ability[] {AbilityEnum.JACK_MARSHALL.make(), AbilityEnum.BLASTER.make(), AbilityEnum.GUNFIGHTER.make(),
					AbilityEnum.LOAD_BEARING.make(), AbilityEnum.QUICK_WORK.make(), AbilityEnum.RELENTLESS_CHARGE.make(),
					AbilityEnum.SPECIALIZATION.make("Storm Glaive")},
	null, null,
	new Connection[] {Connection.make("Cygnaran military")},
	new Connection[] {Connection.make("Cygnaran military")},
	0,
	new Loot[] {},
		null, new HolyCrapWhatIsWrongWithMe(){
			@Override
			public Collection<Career> getEmLater(){
				return stormbladeCareers();
			} 
		},
	new zzPrereqCheck[] {humanPrereq()},
	null),
	
	@SuppressWarnings("unchecked")
	THIEF(R.string.thief_name, false,
	 new Pair[] {SkillEnum.BRIBERY.pair(1), SkillEnum.DECEPTION.pair(1), SkillEnum.ESCAPE_ARTIST.pair(1), SkillEnum.LOCK_PICKING.pair(2),
	 				SkillEnum.PICKPOCKET.pair(2), SkillEnum.SNEAK.pair(1), SkillEnum.STREETWISE.pair(1)},
	 new Pair[] {SkillEnum.HAND_WEAPON.pair(3), SkillEnum.PISTOL.pair(2), SkillEnum.THROWN_WEAPON.pair(3), SkillEnum.UNARMED.pair(2),
	 				SkillEnum.BRIBERY.pair(4), SkillEnum.CRAFT.pair(2), SkillEnum.DECEPTION.pair(4), SkillEnum.DISGUISE.pair(4),
					SkillEnum.ESCAPE_ARTIST.pair(4), SkillEnum.ETIQUETTE.pair(1), SkillEnum.FORGERY.pair(4), SkillEnum.LAW.pair(2),
					SkillEnum.LOCK_PICKING.pair(4), SkillEnum.NEGOTIATION.pair(4), SkillEnum.PICKPOCKET.pair(4), SkillEnum.SNEAK.pair(4),
					SkillEnum.STREETWISE.pair(4)},
	 new Ability[] {AbilityEnum.CONNIVER.make(), AbilityEnum.DODGER.make()},
	 new Ability[] {AbilityEnum.APPRAISE.make(), AbilityEnum.CAMOUFLAGE.make(), AbilityEnum.CARD_SHARP.make(), AbilityEnum.CONNIVER.make(),
	 				AbilityEnum.DODGER.make(), AbilityEnum.FLEET_FOOT.make(), AbilityEnum.GET_AWAY.make(), AbilityEnum.LANGUAGE.make("Five Cant")},
	 null, null, null,
	 new Connection[] {Connection.make("criminal")},
	 75,
	 new Loot[] {},
	 null, null, null,
	 new zzCreateCharacterHook[] {new ThiefWeaponHook()}),
	
	@SuppressWarnings("unchecked")
	TRENCHER(R.string.trencher_name, true,
	 new Pair[] {SkillEnum.GREAT_WEAPON.pair(1), SkillEnum.RIFLE.pair(1), SkillEnum.THROWN_WEAPON.pair(1)},
	 new Pair[] {SkillEnum.GREAT_WEAPON.pair(3), SkillEnum.LIGHT_ARTILLERY.pair(4), SkillEnum.HAND_WEAPON.pair(3), SkillEnum.PISTOL.pair(3),
	 				SkillEnum.RIFLE.pair(4), SkillEnum.THROWN_WEAPON.pair(4), SkillEnum.UNARMED.pair(3), SkillEnum.COMMAND.pair(3),
					SkillEnum.INTERROGATION.pair(3), SkillEnum.MEDICINE.pair(3), SkillEnum.SNEAK.pair(3), SkillEnum.SURVIVAL.pair(3)},
	 new Ability[] {AbilityEnum.BAYONET_CHARGE.make(), AbilityEnum.DIG_IN.make()},
	 new Ability[] {AbilityEnum.JACK_MARSHALL.make(), AbilityEnum.ANATOMICAL_PRECISION.make(), AbilityEnum.BAYONET_CHARGE.make(),
	 				AbilityEnum.BOMBER.make(), AbilityEnum.DIG_IN.make(), AbilityEnum.FIRE_IN_THE_HOLE.make(), AbilityEnum.GRENADIER.make(),
					AbilityEnum.HIT_THE_DECK.make(), AbilityEnum.RELENTLESS_CHARGE.make(), AbilityEnum.SPECIALIZATION.make("bayonet")},
	 null, null,
	 new Connection[] {Connection.make("Cygnaran military")},
	 new Connection[] {Connection.make("Cygnaran military")},
	 25,
	 new Loot[] {},
		null, new HolyCrapWhatIsWrongWithMe(){
			@Override
			public Collection<Career> getEmLater(){
				return trencherCareers();
			} 
		},
	 new zzPrereqCheck[] {trencherPrereq()},
	 null),
	
	@SuppressWarnings("unchecked")
	WARCASTER(R.string.warcaster_name, true,
	 new Pair[] {SkillEnum.HAND_WEAPON.pair(1), SkillEnum.PISTOL.pair(1), SkillEnum.COMMAND.pair(1), SkillEnum.DETECTION.pair(1)},
	 new Pair[] {SkillEnum.GREAT_WEAPON.pair(3), SkillEnum.HAND_WEAPON.pair(3), SkillEnum.PISTOL.pair(3), SkillEnum.UNARMED.pair(2),
	 				SkillEnum.COMMAND.pair(4)},
	 new Ability[] {AbilityEnum.BOND.make()},
	 new Ability[] {AbilityEnum.BOND.make(), AbilityEnum.FIELD_MARSHAL_MAGICAL_ATTACK.make(), AbilityEnum.FIELD_MARSHAL_RELENTLESS_CHARGE.make(),
	 				AbilityEnum.FIELD_MARSHAL_SHIELD_GUARD.make(), AbilityEnum.NATURAL_LEADER.make()},
	 new Spell[] {Spell.BOUNDLESS_CHARGE, Spell.CONVECTION},
	 new Spell[] {Spell.ARCANE_STRIKE, Spell.JUMP_START, Spell.RETURN_FIRE, Spell.ARCANE_BOLT, Spell.AURA_OF_PROTECTION,
	 				Spell.BATTERING_RAM, Spell.BOUNDLESS_CHARGE, Spell.CONVECTION, Spell.FORTIFY, Spell.FOXHOLE,
					Spell.REDLINE, Spell.REFUGE, Spell.SNIPE, Spell.TEMPER_METAL, Spell.TRANSFERENCE, Spell.AWARENESS,
					Spell.BATTEN_DOWN_THE_HATCHES, Spell.ELIMINATOR, Spell.FAIL_SAFE, Spell.GRIND, Spell.GUIDED_FIRE,
					Spell.IRON_AGGRESSION, Spell.RIFT, Spell.SUPERIORITY},
	 null,
	 new Connection[] {Connection.make("kingdom"), Connection.make("mercenary company")},
	 0,
	 new Loot[] {},
	 new String[] {"A warcaster can boost with mechanikal weapons they have bonded to"},
	 null,
	 new zzPrereqCheck[] {giftedPrereq()},
	 new zzCreateCharacterHook[] {new WarcasterEquipmentHook()});
	 
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
	
	//Done!
	private Career(int pNameResource, boolean pStartOnly,
					Pair<Skill, Integer>[] pStartSkills, Pair<Skill, Integer>[] pSkills,
				    Ability[] pStartAbilities, Ability[] pAbilities,
					Spell[] pStartSpells, Spell[] pSpells,
					Connection[] pStartConnections, Connection[] pConnections,
					int pGold, Loot[] pLoot, String[] pSpecialRules,HolyCrapWhatIsWrongWithMe pAllowedCareers,
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
		allowedCareerGetter = pAllowedCareers;
		
		postCreateHooks = pPostCreateHook != null ? Arrays.asList(pPostCreateHook) : new ArrayList<zzCreateCharacterHook>(10);
	}
	
	@Override public String toString(){return displayName();}
	
	interface HolyCrapWhatIsWrongWithMe
	{
		Collection<Career> getEmLater();
	}
	
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
	private HolyCrapWhatIsWrongWithMe allowedCareerGetter;
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
	public Collection<Career> allowedStartCareers(){if(allowedCareerGetter!=null){return allowedCareerGetter.getEmLater();}return new ArrayList<Career>(5);}
	Collection<zzCreateCharacterHook> postCreateHooks(){return postCreateHooks;}
	
	public boolean agreesWithDuringCreation(Career firstCareer)
	{
		Collection<Career> atRunTime = allowedStartCareers();
		Collection<Career> firstAtRunTime = firstCareer.allowedStartCareers();
		
		//Check to see if I'm happy with other career.
		if(atRunTime.size() > 0 && !atRunTime.contains(firstCareer))
		{return false;}
		
		//Check to see if other career is happy with me
		if(firstAtRunTime.size() > 0 && !firstAtRunTime.contains(this))
		{return false;}
		
		//looks like both are fine with it!
		return true;
	}
	
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
	
	public static zzPrereqCheck iosanPrereq(){
		return new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar){
				if(myChar.race == null){return new zzPrereqCheckResult(false, null);}
				return new zzPrereqCheckResult(myChar.race == Race.IOSAN, null);
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
	
	public static zzPrereqCheck trencherPrereq(){
		return new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar){
				if(myChar.race == null || (myChar.race != Race.HUMAN && myChar.race != Race.OGRUN
					&& myChar.race != Race.TROLLKIN) ){return new zzPrereqCheckResult(false, null);}
				return new zzPrereqCheckResult(true, null);
			}
		};
	}
	
	public static zzPrereqCheck humanOrIosanPrereq(){
		return new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar){
				if(myChar.race == null){return new zzPrereqCheckResult(false, null);}
				return new zzPrereqCheckResult(myChar.race == Race.HUMAN || myChar.race == Race.IOSAN, null);
			}
		};
	}
	
	public static Collection<Career> ironFangCareers(){
		Collection<Career> careers = new ArrayList<Career>(5);
		careers.add(ARISTOCRAT); careers.add(MILITARY_OFFICER); careers.add(SOLDIER); careers.add(WARCASTER);
		return careers;
	}
	
	public static Collection<Career> stormbladeCareers(){
		Collection<Career> careers = new ArrayList<Career>(6);
		careers.add(ARISTOCRAT); careers.add(KNIGHT); careers.add(MAN_AT_ARMS);
		careers.add(MILITARY_OFFICER); careers.add(SOLDIER); careers.add(WARCASTER);
		return careers;
	}
	
	public static Collection<Career> trencherCareers(){
		Collection<Career> careers = new ArrayList<Career>(6);
		careers.add(MILITARY_OFFICER); careers.add(RANGER); careers.add(RIFLEMAN); 
		careers.add(SOLDIER); careers.add(WARCASTER);
		return careers;
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
		@Override
		public void startFlowFragment(FlowFragmentDelegate pDelegate)
		{
			super.startFlowFragment(pDelegate);
			
			myChar.abilities.add(AbilityEnum.GREAT_POWER.make());
			myChar.abilities.add(AbilityEnum.RUNE_READER.make());
			
			Bundle b = new Bundle();
			b.putString(BundleConstants.CHARACTER, myChar.toJson());
			pDelegate.hookComplete(b);
		}
		
		@Override public int getPriority(){return 0;}
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
	}
	
	//TODO: Finish
	public static class BountyHunterHook extends zzCreateCharacterHook
	{
		@Override public int getPriority(){return 49;}
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
		@Override
		public void startFlowFragment(FlowFragmentDelegate pDelegate)
		{
			super.startFlowFragment(pDelegate);
			
			boolean additionNeeded = !(myChar.abilities.contains(AbilityEnum.HYPER_PERCEPTION.make()));
			if(additionNeeded)
			{myChar.abilities.add(AbilityEnum.HYPER_PERCEPTION.make());}
			
			Bundle b = new Bundle();
			b.putString(BundleConstants.CHARACTER, myChar.toJson());
			pDelegate.hookComplete(b);
		}
		
		@Override
		public int getPriority(){return 0;}
	}
	
	public static class MageHunterHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(3);
			l.add(new Skill(SkillEnum.CROSSBOW)); l.add(new Skill(SkillEnum.ARCHERY));
			return l;
		}
	}
	
	public static class ManAtArmsHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(2);
			l.add(SkillEnum.PISTOL.make()); l.add(SkillEnum.HAND_WEAPON.make());
			return l;
		}
	}
	
	public static class MilitaryOfficerWeaponHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(2);
			l.add(SkillEnum.PISTOL.make()); l.add(SkillEnum.GREAT_WEAPON.make());
			return l;
		}
	}
	
	public static class PirateHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(2);
			l.add(SkillEnum.PISTOL.make()); l.add(SkillEnum.THROWN_WEAPON.make());
			return l;
		}
	}
	
	//TODO: Finish
	public static class PistoleerHook extends zzCreateCharacterHook
	{
		@Override public int getPriority(){return 49;}
	}
	
	public static class PriestWeaponHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(2);
			l.add(SkillEnum.HAND_WEAPON.make()); l.add(SkillEnum.GREAT_WEAPON.make());
			return l;
		}
	}
	
	public static class RangerWeaponHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(2);
			l.add(SkillEnum.ARCHERY.make()); l.add(SkillEnum.CROSSBOW.make()); l.add(SkillEnum.RIFLE.make());
			l.add(SkillEnum.PISTOL.make());
			return l;
		}
	}
	
	//TODO: Finish
	public static class SoldierWeaponHook extends zzCreateCharacterHook
	{
		@Override public int getPriority(){return 49;}
	}
	
	public static class SorcererWeaponHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(2);
			l.add(SkillEnum.HAND_WEAPON.make()); l.add(SkillEnum.ARCHERY.make()); l.add(SkillEnum.CROSSBOW.make());
			return l;
		}
	}
	
	//TODO: Finish
	public static class StoneSorcererHook extends zzCreateCharacterHook
	{
		@Override public int getPriority(){return 49;}
	}
	
	//TODO: Finish
	public static class StormSorcererHook extends zzCreateCharacterHook
	{
		@Override public int getPriority(){return 49;}
	}
	
	public static class SpyWeaponHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(2);
			l.add(SkillEnum.HAND_WEAPON.make()); l.add(SkillEnum.PISTOL.make()); l.add(SkillEnum.THROWN_WEAPON.make());
			return l;
		}
	}
	
	public static class ThiefWeaponHook extends zzChooseOneMilitarySkillHook
	{
		@Override protected List<Skill> getOptions()
		{
			List<Skill> l = new ArrayList<Skill>(2);
			l.add(SkillEnum.HAND_WEAPON.make()); l.add(SkillEnum.THROWN_WEAPON.make());
			return l;
		}
	}
	
	//TODO: Finish
	public static class WarcasterEquipmentHook extends zzCreateCharacterHook
	{
		@Override public int getPriority(){return 49;}
	}
}
