package com.random.captain.ikrpg.character;

import android.content.Context;
import com.random.captain.ikrpg.IKRPGApp;
import com.random.captain.ikrpg.R;

public enum AbilityEnum implements zzPrereqCheck
{
	JACK_MARSHALL(R.string.jack_marshal_name, R.string.jack_marshal_longDesc, R.string.jack_marshal_shortDesc, R.string.jack_marshal_page,
					false, false, null),
					
	ACE_COMMANDER(R.string.ace_commander_name, R.string.ace_commander_longDesc, R.string.ace_commander_shortDesc, R.string.ace_commander_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.hasAbility(AbilityEnum.JACK_MARSHALL) && myChar.getSkillBaseLevel(SkillEnum.COMMAND) >= 2, null);}
	}),
	
	ACROBATICS(R.string.acrobatics_name, R.string.acrobatics_longDesc, R.string.acrobatics_shortDesc, R.string.acrobatics_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.AGILITY) >= 6, null);}
	}),
	
	ADVISOR(R.string.advisor_name, R.string.advisor_longDesc, R.string.advisor_shortDesc, R.string.advisor_page,
				false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.COMMAND) >= 2);}
	}),
	
	AMBUSH(R.string.ambush_name, R.string.ambush_longDesc, R.string.ambush_shortDesc, R.string. ambush_page,
				false, false, null),
	
	ANATOMICAL_PRECISION(R.string.anatomical_precision_name, R.string.anatomical_precision_longDesc, R.string.anatomical_precision_shortDesc, R.string.anatomical_precision_page,
				false, false, null),
				
	APPRAISE(R.string.appraise_name, R.string.appraise_longDesc, R.string.appraise_shortDesc, R.string.appraise_page,
				false, false, null),
				
	ARCANE_ASSASSIN(R.string.arcane_assassin_name, R.string.arcane_assassin_longDesc, R.string.arcane_assassin_shortDesc, R.string.arcane_assassin_page,
				false, false, null),
				
	ARCANE_DEFENSES(R.string.arcane_defenses_name, R.string.arcane_defenses_longDesc, R.string.arcane_defenses_shortDesc, R.string.arcane_defenses_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.ARCANE) >= 5, null);}
	}),
	
	ARCANE_ENGINEER(R.string.arcane_engineer_name, R.string.arcane_engineer_longDesc, R.string.arcane_engineer_shortDesc, R.string.arcane_engineer_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.MECHANIKAL) >= 2, null);}
	}),
	
	ARCANE_PRECISION(R.string.arcane_precision_name, R.string.arcane_precision_longDesc, R.string.arcane_precision_shortDesc, R.string.arcane_precision_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.DETECTION) >= 3, null);}
	}),
	
	ARCANE_SCHOLAR(R.string.arcane_scholar_name, R.string.arcane_scholar_longDesc, R.string.arcane_scholar_shortDesc, R.string.arcane_scholar_page,
				false, false, null),
			
	ASTUTE(R.string.astute_name, R.string.astute_longDesc, R.string.astute_shortDesc, R.string.astute_page,
				false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.DETECTION) >= 1, null);}
	}),
	
	BACKSTAB(R.string.backstab_name, R.string.backstab_longDesc, R.string.backstab_shortDesc, R.string.backstab_page,
				false, false, null),
				
	BATTLE_COMMANDER(R.string.battle_commander_page, R.string.battle_commander_longDesc, R.string.battle_commander_shortDesc, R.string.battle_commander_page,
				false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.COMMAND) >= 3, null);}
	}),
	
	BATTLE_PLAN_CALL_TO_ACTION(R.string.battle_plan_call_to_action_name, R.string.battle_plan_call_to_action_longDesc, R.string.battle_plan_call_to_action_shortDesc, R.string.battle_plan_call_to_action_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.COMMAND) >= 1, null);}
		}),
	
	BATTLE_PLAN_COORDINATED_STRIKE(R.string.battle_plan_coordinated_strike_name, R.string.battle_plan_coordinated_strike_longDesc, R.string.battle_plan_coordinated_strike_shortDesc, R.string.battle_plan_coordinated_strike_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.COMMAND) >= 1, null);}
		}),
	
	BATTLE_PLAN_DESPERATE_PACE(R.string.battle_plan_desperate_pace_name, R.string.battle_plan_desperate_pace_longDesc, R.string.battle_plan_desperate_pace_shortDesc, R.string.battle_plan_desperate_pace_page,
				false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.COMMAND) >= 3, null);}
	}),
	
	BATTLE_PLAN_GO_TO_GROUND(R.string.battle_plan_go_to_ground_name, R.string.battle_plan_go_to_ground_longDesc, R.string.battle_plan_go_to_ground_shortDesc, R.string.battle_plan_go_to_ground_page,
				false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.COMMAND) >= 2, null);}
	}),
	
	BATTLE_PLAN_RECONNAISSANCE(R.string.battle_plan_reconnaissance_name, R.string.battle_plan_reconnaissance_longDesc, R.string.battle_plan_reconnaissance_shortDesc, R.string.battle_plan_reconnaissance_page,
				false,false,new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.COMMAND) >= 2 && myChar.getSkillBaseLevel(SkillEnum.SURVIVAL) >= 3, null);}
	}),
	
	BATTLE_PLAN_SHADOW(R.string.battle_plan_shadow_name, R.string.battle_plan_shadow_longDesc, R.string.battle_plan_shadow_shortDesc, R.string.battle_plan_shadow_page,
				false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.COMMAND) >= 1, null);}
	}),
	
	BAYONET_CHARGE(R.string.bayonet_charge_name, R.string.bayonet_charge_longDesc, R.string.bayonet_charge_shortDesc, R.string.bayonet_charge_page,
				false,false,null),
	
	BIG_GAME_HUNTER(R.string.big_game_hunter_name, R.string.big_game_hunter_longDesc, R.string.big_game_hunter_shortDesc, R.string.big_game_hunter_page,
				false,false,new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.SURVIVAL) >= 1, null);}
	}),
	
	BINDING(R.string.binding_name, R.string.binding_longDesc, R.string.binding_shortDesc, R.string.binding_page,
			false,false,new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.ROPE_USE) >= 1, null);}
	}),
	
	BLASTER(R.string.blaster_name, R.string.blaster_longDesc, R.string.blaster_shortDesc, R.string.blaster_page,
			false,false,null),
			
	BLOOD_SPILLER(R.string.blood_spiller_name, R.string.blood_spiller_longDesc, R.string.blood_spiller_shortDesc, R.string.blood_spiller_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.HAND_WEAPON) >= 3, null);}
	}),
	
	BODGE(R.string.bodge_name, R.string.bodge_longDesc, R.string.bodge_shortDesc, R.string.bodge_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.MECHANIKAL) >= 1, null);}
	}),
	
	BODYGUARD(R.string.bodyguard_name, R.string.bodyguard_longDesc, R.string.bodyguard_shortDesc, R.string.bodyguard_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.SHIELD) >= 3 && myChar.hasAbility(Ability.SHIELD_GUARD), null);}
	}),
	
	BOMBER(R.string.bomber_name, R.string.bomber_longDesc, R.string.bomber_shortDesc, R.string.bomber_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.THROWN_WEAPON) >= 3, null);}
	}),
	
	BOND(R.string.bond_name, R.string.bond_longDesc, R.string.bond_shortDesc,R.string.bond_page,
			false, true, null),
			
	BREW_MASTER(R.string.brew_master_name, R.string.brew_master_longDesc, R.string.brew_master_shortDesc, R.string.brew_master_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.ALCHEMY) >= 2, null);}
	}),
	
	CAMOUFLAGE(R.string.camouflage_name, R.string.camouflage_longDesc, R.string.camouflage_shortDesc, R.string.camouflage_name,
				false, false, null),
			
	CARD_SHARP(R.string.card_sharp_name, R.string.card_sharp_longDesc, R.string.card_sharp_shortDesc, R.string.card_sharp_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.GAMBLING) >= 2, null);}
		}),
	
	CAUTIOUS_ADVANCE(R.string.cautious_advance_name, R.string.cautious_advance_longDesc, R.string.cautious_advance_shortDesc, R.string.cautious_advance_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.SURVIVAL) >= 3, null);}
	}),
	
	CAVALRY_CHARGE(R.string.cavalry_charge_name, R.string.cavalry_charge_longDesc, R.string.cavalry_charge_shortDesc, R.string.cavalry_charge_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.RIDING) >= 1, null);}
	}),
	
	CHAIN_ATTACK_BLEED_OUT(R.string.chain_attack_bleed_out_name, R.string.chain_attack_bleed_out_longDesc, R.string.chain_attack_bleed_out_shortDesc, R.string.chain_attack_bleed_out_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.HAND_WEAPON) >= 3 && myChar.hasAbility(AbilityEnum.TWO_WEAPON_FIGHTING), null);}
	}),
	
	CHAIN_ATTACK_PIN_DOWN(R.string.chain_attack_pin_down_name, R.string.chain_attack_pin_down_longDesc, R.string.chain_attack_pin_down_shortDesc, R.string.chain_attack_pin_down_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.PISTOL) >= 3 && myChar.hasAbility(AbilityEnum.TWO_WEAPON_FIGHTING), null);}
	}),
	
	CHOIR(R.string.choir_name, R.string.choir_longDesc, R.string.choir_shortDesc, R.string.choir_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.ARCANE) >= 4, null);}
	}),
	
	CLEAVE(R.string.cleave_name, R.string.cleave_longDesc, R.string.cleave_shortDesc, R.string.cleave_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.GREAT_WEAPON) >= 1, null);}
	}),
	
	COMBAT_RIDER(R.string.combat_rider_name, R.string.combat_rider_longDesc, R.string.combat_rider_shortDesc, R.string.combat_rider_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.RIDING) >= 1, null);}
	}),
	
	CONNIVER(R.string.conniver_name, R.string.conniver_longDesc, R.string.conniver_shortDesc, R.string.conniver_page,
				false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.BRIBERY) >= 1 && myChar.getSkillBaseLevel(SkillEnum.DECEPTION) >= 1, null);}
	}),
	
	COVER_IDENTITY(R.string.cover_identity_name, R.string.cover_identity_longDesc, R.string.cover_identity_shortDesc, R.string.cover_identity_page,
			false, true, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.DISGUISE) >= 1, null);}
	}),
	
	CRACKSHOT(R.string.crackshot_name, R.string.crackshot_longDesc, R.string.crackshot_shortDesc, R.string.crackshot_page,
			false, false, null),
			
	CRAFT_RUNE_SHOT(R.string.craft_rune_shot_name, R.string.craft_rune_shot_longDesc, R.string.craft_rune_shot_shortDesc, R.string.craft_rune_shot_page,
			false, false, null),
			
	CROSSBOWMAN(R.string.crossbowman_name, R.string.crossbowman_longDesc, R.string.crossbowman_shortDesc, R.string.crossbowman_page,
			false, false, null),
	
	DEFENDER(R.string.defender_name, R.string.defender_longDesc, R.string.defender_shortDesc, R.string.defender_page,
			false, false, null),
		
	DEFENSIVE_LINE(R.string.defensive_line_name, R.string.defensive_line_longDesc, R.string.defensive_line_shortDesc, R.string.defensive_line_page,
			false, false, null),
			
	DIG_IN(R.string.dig_in_name, R.string.dig_in_longDesc, R.string.dig_in_shortDesc, R.string.dig_in_page,
			false, false, null),
			
	DISEASE_RESISTANCE(R.string.disease_resistance_name, R.string.disease_resistance_longDesc, R.string.disease_resistance_shortDesc, R.string.disease_resistance_page,
			false, false, null),
			
	DISPEL(R.string.dispel_name, R.string.dispel_longDesc, R.string.dispel_shortDesc, R.string.dispel_page,
			false, false, null),
	
	DODGER(R.string.dodger_name, R.string.dodger_longDesc, R.string.dodger_shortDesc, R.string.dodger_page,
			false, false, null),
	
	DRIVE_ANCILLARY_ATTACK(R.string.drive_ancillary_attack_name, R.string.drive_ancillary_attack_longDesc, R.string.drive_ancillary_attack_shortDesc, R.string.drive_ancillary_attack_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.hasAbility(AbilityEnum.JACK_MARSHALL), null);}
	}),
	
	DRIVE_ASSUALT(R.string.drive_assault_name, R.string.drive_assault_longDesc, R.string.drive_assault_shortDesc, R.string.drive_assault_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.hasAbility(AbilityEnum.JACK_MARSHALL), null);}
	}),
	
	DRIVE_OFF_ROAD(R.string.drive_off_road_name, R.string.drive_off_road_longDesc, R.string.drive_off_road_shortDesc, R.string.drive_off_road_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.hasAbility(AbilityEnum.JACK_MARSHALL), null);}
	}),
	
	DRIVE_PRONTO(R.string.drive_pronto_name, R.string.drive_pronto_longDesc, R.string.drive_pronto_shortDesc, R.string.drive_pronto_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.hasAbility(AbilityEnum.JACK_MARSHALL), null);}
	}),
	
	DUAL_SHOT(R.string.dual_shot_name, R.string.dual_shot_longDesc, R.string.dual_shot_shortDesc, R.string.dual_shot_page,
			false, false ,null),
	
	ELEMENTAL_MASTERY(R.string.elemental_mastery_name, R.string.elemental_mastery_longDesc, R.string.elemental_mastery_shortDesc, R.string.elemental_mastery_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.ARCANE) >= 5, null);}
	}),
	
	EMPOWER(R.string.empower_name, R.string.empower_longDesc, R.string.empower_shortDesc, R.string.empower_page,
			false, false, null),
		
	EXPERT_RIDER(R.string.expert_rider_name, R.string.expert_rider_longDesc, R.string.expert_rider_shortDesc, R.string.expert_rider_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.RIDING) >= 2, null);}
	}),
	
	FAST_COOK(R.string.fast_cook_name, R.string.fast_cook_longDesc, R.string.fast_cook_shortDesc, R.string.fast_cook_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.ALCHEMY) >= 2, null);}
	}),
	
	FAST_DRAW(R.string.fast_draw_name, R.string.fast_draw_longDesc, R.string.fast_draw_shortDesc, R.string.fast_draw_page,
			false, false, null),
			
	FAST_REARM(R.string.fast_rearm_name, R.string.fast_rearm_longDesc, R.string.fast_rearm_shortDesc, R.string.fast_rearm_page,
			false, true, null),
	
	FAST_RELOAD(R.string.fast_reload_name, R.string.fast_reload_longDesc, R.string.fast_reload_shortDesc, R.string.fast_reload_page,
			false, false, null),
	
	FELL_CALL_CACOPHONY(R.string.fell_call_cacophony_name, R.string.fell_call_cacophony_longDesc, R.string.fell_call_cacophony_shortDesc, R.string.fell_call_cacophony_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.FELL_CALLING) >= 2, null);}
	}),
	
	FELL_CALL_CALL_OF_DEFIANCE(R.string.fell_call_call_of_defiance_name, R.string.fell_call_call_of_defiance_longDesc, R.string.fell_call_call_of_defiance_shortDesc, R.string.fell_call_call_of_defiance_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.FELL_CALLING) >= 2, null);}
	}),
	
	FIELD_ALCHEMIST(R.string.field_alchemist_name, R.string.field_alchemist_longDesc, R.string.field_alchemist_shortDesc, R.string.field_alchemist_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.ALCHEMY) >= 2, null);}
	}),
	
	FIRE_IN_THE_HOLE(R.string.fire_in_the_hole_name, R.string.fire_in_the_hole_longDesc, R.string.fire_in_the_hole_shortDesc, R.string.fire_in_the_hole_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.THROWN_WEAPON) >= 1, null);}
	}),
	
	FREE_STYLE(R.string.free_style_name, R.string.free_style_longDesc, R.string.free_style_shortDesc, R.string.free_style_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.ALCHEMY) >= 1, null);}
	}),
	
	GANG(R.string.gang_name, R.string.gang_longDesc, R.string.gang_shortDesc, R.string.gang_page,
		false, false, null),
		
	GRENADIER(R.string.grenadier_name, R.string.grenadier_longDesc, R.string.grenadier_shortDesc, R.string.grenadier_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.THROWN_WEAPON) >= 1, null);}
	}),
	
	INSCRIBE_FORMULAE(R.string.inscribe_formulae_name, R.string.inscribe_formulae_longDesc, R.string.inscribe_formulae_shortDesc, R.string.inscribe_formulae_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.MECHANIKAL) >= 1, null);}
	}),
	
	PARRY(R.string.parry_name, R.string.parry_longDesc, R.string.parry_shortDesc, R.string.parry_page,
					  false, false, null),
					  
	POISON_RESISTANCE(R.string.poison_resistance_name, R.string.poison_resistance_longDesc, R.string.poison_resistance_shortDesc, R.string.poison_resistance_page,
					false, false, null),
	
	PRECISION_STRIKE(R.string.precision_strike_name, R.string.precision_strike_longDesc, R.string.precision_strike_shortDesc, R.string.precision_strike_page,
					false, false, null),
					
	RESOURCEFUL(R.string.resourceful_name, R.string.resourceful_longDesc, R.string.resourceful_shortDesc, R.string.resourceful_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.MECHANIKAL) >= 3, null);}
	}),
	
	RIPOSTE(R.string.riposte_name, R.string.riposte_longDesc, R.string.riposte_shortDesc, R.string.riposte_page,
		  false, false, null),
	
	SPECIALIZATION(R.string.specialization_name, R.string.specialization_longDesc, R.string.specialization_shortDesc, R.string.specialization_page,
			false, true, null),
	
	STEADY(R.string.steady_name, R.string.steady_longDesc, R.string.steady_shortDesc, R.string.steady_page,
			false, false, null),
			
	STEAMO(R.string.steamo_name, R.string.steamo_longDesc, R.string.steamo_shortDesc, R.string.steamo_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.MECHANIKAL) >= 2, null);}
	}),
	
	//For Pascal
	MIGHTY(R.string.mighty_name, R.string.mighty_longDesc, R.string.mighty_shortDesc, R.string.mighty_page,
		true, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.archetype == Archetype.MIGHTY, null);}
	}),
	
	FEAT_INVULNERABLE(R.string.feat_invulnerable_name, R.string.feat_invulnerable_longDesc, R.string.feat_invulnerable_shortDesc, R.string.feat_invulnerable_page,
		true, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.archetype == Archetype.MIGHTY, null);}
		});
	
	//End for Pascal
	
	private AbilityEnum(int pNameResourceID, int pLongDescResourceID, int pShortDescResourceID, int pPageNumberResourceID, boolean pFromArchetype, boolean pIsQualifiable, zzPrereqCheck pPrereqCheck)
	{
		Context c = IKRPGApp.getContext();
		name = c.getString(pNameResourceID);
		longDescription = c.getString(pLongDescResourceID);
		shortDescription = c.getString(pShortDescResourceID);
		pageNumber = c.getString(pPageNumberResourceID);
		fromArchetype = pFromArchetype;
		isQualifiable = pIsQualifiable;
		prereq = pPrereqCheck;
	}
	
	private String name;
	private String longDescription;
	private String shortDescription;
	private String pageNumber;
	private boolean fromArchetype;
	private boolean isQualifiable;
	private zzPrereqCheck prereq;
	
	public String displayName(){return name;}
	public String longDescription(){return longDescription;}
	public String shortDescription(){return shortDescription;}
	public String pageNumbrer(){return pageNumber;}
	public boolean isQualifiable(){return isQualifiable;}
	public boolean isFromArchetype(){return fromArchetype;}
	
	@Override
	public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
	{
		if(prereq == null){return new zzPrereqCheckResult(true,null);}
		else{return prereq.meetsPrereq(myChar);}
	}
	
	public Ability make(){return new Ability(this);}
	public Ability make(String qualifier){return new Ability(this, qualifier);}
}
