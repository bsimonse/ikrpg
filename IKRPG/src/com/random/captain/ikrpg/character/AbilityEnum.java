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
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.SHIELD) >= 3 && myChar.hasAbility(AbilityEnum.SHIELD_GUARD), null);}
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
	
	FELL_CALL_GROUND_SHAKER(R.string.fell_call_ground_shaker_name,  R.string.fell_call_ground_shaker_longDesc, R.string.fell_call_ground_shaker_shortDesc, R.string.fell_call_ground_shaker_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.FELL_CALLING) >= 3, null);}
	}),
	
	FELL_CALL_HEROIC_BALLAD(R.string.fell_call_heroic_ballad_name, R.string.fell_call_heroic_ballad_longDesc, R.string.fell_call_heroic_ballad_shortDesc, R.string.fell_call_heroic_ballad_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.FELL_CALLING) >= 2, null);}
	}),
	
	FELL_CALL_REVERBERATION(R.string.fell_call_reverberation_name, R.string.fell_call_reverberation_longDesc, R.string.fell_call_reverberation_shortDesc, R.string.fell_call_reverberation_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.FELL_CALLING) >= 2, null);}
	}),
	
	FELL_CALL_SIGNAL_CALL(R.string.fell_call_signal_call_name, R.string.fell_call_signal_call_longDesc, R.string.fell_call_signal_call_shortDesc, R.string.fell_call_signal_call_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.FELL_CALLING) >= 1, null);}
	}),
	
	FELL_CALL_SONIC_BLAST(R.string.fell_call_sonic_blast_name, R.string.fell_call_sonic_blast_longDesc, R.string.fell_call_sonic_blast_shortDesc, R.string.fell_call_sonic_blast_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.FELL_CALLING) >= 2, null);}
	}),
	
	FIELD_ALCHEMIST(R.string.field_alchemist_name, R.string.field_alchemist_longDesc, R.string.field_alchemist_shortDesc, R.string.field_alchemist_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.ALCHEMY) >= 1, null);}
	}),
	
	FIELD_MARSHAL_MAGICAL_ATTACK(R.string.field_marshal_magical_attack_name, R.string.field_marshal_magical_attack_longDesc, R.string.field_marshal_magical_attack_shortDesc, R.string.field_marshal_magical_attack_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.ARCANE) >= 5, null);}
	}),
	
	FIELD_MARSHAL_RELENTLESS_CHARGE(R.string.field_marshal_relentless_charge_name, R.string.field_marshal_relentless_charge_longDesc, R.string.field_marshal_relentless_charge_shortDesc, R.string.field_marshal_relentless_charge_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.ARCANE) >= 5, null);}
	}),
	
	FIELD_MARSHAL_SHIELD_GUARD(R.string.field_marshal_shield_guard_name, R.string.field_marshal_shield_guard_longDesc, R.string.field_marshal_shield_guard_shortDesc, R.string.field_marshal_shield_guard_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.ARCANE) >= 7, null);}
	}),
	
	FIND_COVER(R.string.find_cover_name, R.string.find_cover_longDesc, R.string.find_cover_shortDesc, R.string.find_cover_page,
			false, false, null),
	
	FIRE_IN_THE_HOLE(R.string.fire_in_the_hole_name, R.string.fire_in_the_hole_longDesc, R.string.fire_in_the_hole_shortDesc, R.string.fire_in_the_hole_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.THROWN_WEAPON) >= 1, null);}
	}),
	
	FLEET_FOOT(R.string.fleet_foot_name, R.string.fleet_foot_longDesc, R.string.fleet_foot_shortDesc, R.string.fleet_foot_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.SPEED) >= 7, null);}
	}),
	
	FREE_STYLE(R.string.free_style_name, R.string.free_style_longDesc, R.string.free_style_shortDesc, R.string.free_style_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.ALCHEMY) >= 1, null);}
	}),
	
	GANG(R.string.gang_name, R.string.gang_longDesc, R.string.gang_shortDesc, R.string.gang_page,
		false, false, null),
	
	GET_AWAY(R.string.get_away_name, R.string.get_away_longDesc, R.string.get_away_shortDesc, R.string.get_away_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.ESCAPE_ARTIST) >= 3 && myChar.hasAbility(AbilityEnum.DODGER), null);}
	}),
	
	GIRDED(R.string.girded_name, R.string.girded_longDesc, R.string.girded_shortDesc, R.string.girded_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.SHIELD) >= 2, null);}
	}),
	
	GOOD_BREEDING(R.string.good_breeding_name, R.string.good_breeding_longDesc, R.string.good_breeding_shortDesc, R.string.good_breeding_page,
			false, false, null),
			
	GREAT_POWER(R.string.great_power_name, R.string.great_power_longDesc, R.string.great_power_shortDesc, R.string.great_power_page,
			false, false, null),
			
	GRENADIER(R.string.grenadier_name, R.string.grenadier_longDesc, R.string.grenadier_shortDesc, R.string.grenadier_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.THROWN_WEAPON) >= 1, null);}
	}),
	
	GUNFIGHTER(R.string.gunfighter_name, R.string.gunfighter_longDesc, R.string.gunfighter_shortDesc, R.string.gunfighter_page,
			false, false, null),
		
	HEADBUTT(R.string.head_butt_name, R.string.head_butt_longDesc, R.string.head_butt_shortDesc, R.string.head_butt_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.UNARMED) >= 2 && myChar.getBaseStat(Stat.STRENGTH) >= 5, null);}
	}),
	
	HIT_THE_DECK(R.string.hit_the_deck_name, R.string.hit_the_deck_longDesc, R.string.hit_the_deck_shortDesc, R.string.hit_the_deck_page,
			false, false, null),
			
	HYPER_AWARENESS(R.string.hyper_awareness_name, R.string.hyper_awareness_longDesc, R.string.hyper_awareness_shortDesc, R.string.hyper_awareness_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.COMMAND) >= 3, null);}
	}),
	
	IMMUNITY_COLD(R.string.immunity_cold_name, R.string.immunity_cold_longDesc, R.string.immunity_cold_shortDesc, R.string.immunity_cold_page,
			false, false, null),
			
	IMMUNITY_CORROSION(R.string.immunity_corrosion_name, R.string.immunity_corrosion_longDesc, R.string.immunity_corrosion_shortDesc, R.string.immunity_corrosion_page,
				  false, false, null),
				  
	IMMUNITY_ELECTRICITY(R.string.immunity_electricity_name, R.string.immunity_electricity_longDesc, R.string.immunity_electricity_shortDesc, R.string.immunity_electricity_page,
				  false, false, null),
				  
	IMMUNITY_FIRE(R.string.immunity_fire_name, R.string.immunity_fire_longDesc, R.string.immunity_fire_shortDesc, R.string.immunity_fire_page,
				  false, false, null),
				  
	INSCRIBE_FORMULAE(R.string.inscribe_formulae_name, R.string.inscribe_formulae_longDesc, R.string.inscribe_formulae_shortDesc, R.string.inscribe_formulae_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.MECHANIKAL) >= 1, null);}
	}),
	
	IRON_SENTINEL(R.string.iron_sentinel_name, R.string.iron_sentinel_longDesc, R.string.iron_sentinel_shortDesc, R.string.iron_sentinel_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.COMMAND) >= 3 && myChar.hasAbility(AbilityEnum.JACK_MARSHALL), null);}
	}),
		
	IRON_WILL(R.string.iron_will_name, R.string.iron_will_longDesc, R.string.iron_will_shortDesc, R.string.iron_will_page,
			false, false, null),
	
	KEEN_EYED(R.string.keen_eyed_name, R.string.keen_eyed_longDesc, R.string.keen_eyed_shortDesc, R.string.keen_eyed_page,
		false, false, null),
		
	LANGUAGE(R.string.language_name, R.string.language_longDesc, R.string.language_shortDesc, R.string.language_page,
			false, true, null),
	
	LEGACY_OF_BRAGG(R.string.legacy_of_bragg_name, R.string.legacy_of_bragg_longDesc, R.string.legacy_of_bragg_shortDesc, R.string.legacy_of_bragg_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.FELL_CALLING) >= 2, null);}
	}),
		
	LIGHT_CAVALRY(R.string.light_cavalry_name, R.string.light_cavalry_longDesc, R.string.light_cavalry_shortDesc, R.string.light_cavalry_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.RIDING) >= 2, null);}
	}),
	
	LOAD_BEARING(R.string.load_bearing_name, R.string.load_bearing_longDesc, R.string.load_bearing_shortDesc, R.string.load_bearing_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.STRENGTH) >= 5, null);}
	}),
	
	MAGE_KILLER(R.string.mage_killer_name, R.string.mage_killer_longDesc, R.string.mage_killer_shortDesc, R.string.mage_killer_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.PERCEPTION) >= 6, null);}
	}),
	
	MARKSMAN(R.string.marksman_name, R.string.marksman_longDesc, R.string.marksman_shortDesc, R.string.marksman_page,
		false, false, null),
		
	NATURAL_LEADER(R.string.natural_leader_name, R.string.natural_leader_longDesc, R.string.natural_leader_shortDesc, R.string.natural_leader_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.COMMAND) >= 1, null);}
	}),
	
	NIGHT_FIGHTER(R.string.night_fighter_name, R.string.night_fighter_longDesc, R.string.night_fighter_shortDesc, R.string.night_fighter_page,
		false, false, null),
		
	PARRY(R.string.parry_name, R.string.parry_longDesc, R.string.parry_shortDesc, R.string.parry_page,
					  false, false, null),
					
	PATHFINDER(R.string.pathfinder_name, R.string.pathfinder_longDesc, R.string.pathfinder_shortDesc, R.string.pathfinder_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.SURVIVAL) >= 1, null);}
	}),
	
	POISON_RESISTANCE(R.string.poison_resistance_name, R.string.poison_resistance_longDesc, R.string.poison_resistance_shortDesc, R.string.poison_resistance_page,
					false, false, null),
	
	PORT_OF_CALL(R.string.port_of_call_name, R.string.port_of_call_longDesc, R.string.port_of_call_shortDesc, R.string.port_of_call_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.NAVIGATION) >= 1, null);}
	}),
	
	PRECISION_STRIKE(R.string.precision_strike_name, R.string.precision_strike_longDesc, R.string.precision_strike_shortDesc, R.string.precision_strike_page,
					false, false, null),
		
	PRESS_THE_ATTACK(R.string.press_the_attack_name, R.string.press_the_attack_longDesc, R.string.press_the_attack_shortDesc, R.string.press_the_attack_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.SHIELD) >= 3 && myChar.hasAbility(AbilityEnum.SHIELD_SLAM), null);}
	}),
	
	PRIVILEGE(R.string.privilege_name, R.string.privilege_longDesc, R.string.privilege_shortDesc, R.string.privilege_page,
		false, false, null),
		
	PROWL(R.string.prowl_name, R.string.prowl_longDesc, R.string.prowl_shortDesc, R.string.prowl_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.SNEAK) >= 1, null);}
	}),
	
	PURSUIT(R.string.pursuit_name, R.string.pursuit_longDesc, R.string.pursuit_shortDesc, R.string.pursuit_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.TRACKING) >= 3, null);}
	}),
	
	QUICK_WORK(R.string.quick_work_name, R.string.quick_work_longDesc, R.string.quick_work_shortDesc, R.string.quick_work_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.AGILITY) >= 5, null);}
	}),
	
	RALLYING_CRY(R.string.rallying_cry_name, R.string.rallying_cry_longDesc, R.string.rallying_cry_shortDesc, R.string.rallying_cry_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.COMMAND) >= 3, null);}
	}),
	
	RELENTLESS_CHARGE(R.string.relentless_charge_name, R.string.relentless_charge_longDesc, R.string.relentless_charge_shortDesc, R.string.relentless_charge_page,
		false, false, null),
		
	RESOURCEFUL(R.string.resourceful_name, R.string.resourceful_longDesc, R.string.resourceful_shortDesc, R.string.resourceful_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.MECHANIKAL) >= 3, null);}
	}),
	
	RETALIATORY_STRIKE(R.string.retaliatory_strike_name, R.string.retaliatory_strike_longDesc, R.string.retaliatory_strike_shortDesc, R.string.retaliatory_strike_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.PHYSIQUE) >= 7, null);}
	}),
	
	RETURN_FIRE(R.string.return_fire_ability_name, R.string.return_fire_ability_longDesc, R.string.return_fire_ability_shortDesc, R.string.return_fire_ability_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.hasAbility(AbilityEnum.FAST_DRAW), null);}
	}),
	
	RIDE_BY_ATTACK(R.string.ride_by_attack_name, R.string.ride_by_attack_longDesc, R.string.ride_by_attack_shortDesc, R.string.ride_by_attack_page,
		false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.RIDING) >= 2, null);}
	}),
	
	RIPOSTE(R.string.riposte_name, R.string.riposte_longDesc, R.string.riposte_shortDesc, R.string.riposte_page,
		  false, false, null),
	
	ROCK_SOLID(R.string.rock_solid_name, R.string.rock_solid_longDesc, R.string.rock_solid_shortDesc, R.string.rock_solid_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.PHYSIQUE) >= 8, null);}
	}),
	
	ROLL_WITH_IT(R.string.roll_with_it_name, R.string.roll_with_it_longDesc, R.string.roll_with_it_shortDesc, R.string.roll_with_it_page,
			false, false, null),
			
	SADDLE_SHOT(R.string.saddle_shot_name, R.string.saddle_shot_longDesc, R.string.saddle_shot_shortDesc, R.string.saddle_shot_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.RIDING) >= 1, null);}
	}),
	
	SCROUNGE(R.string.scrounge_name, R.string.scrounge_longDesc, R.string.scrounge_shortDesc, R.string.scrounge_page,
			false, false, null),
		
	SENTRY(R.string.sentry_name, R.string.sentry_longDesc, R.string.sentry_shortDesc, R.string.sentry_page,
			false, false, null),
			
	SET_DEFENSE(R.string.set_defense_name, R.string.set_defense_longDesc, R.string.set_defense_shortDesc, R.string.set_defense_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.GREAT_WEAPON) >= 2, null);}
	}),
	
	SHADOW_MAGIC(R.string.shadow_magic_name, R.string.shadow_magic_longDesc, R.string.shadow_magic_shortDesc, R.string.shadow_magic_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.SNEAK) >= 2 && myChar.archetype == Archetype.GIFTED, null);}
	}),
	
	SHIELD_GUARD(R.string.shield_guard_name, R.string.shield_guard_longDesc, R.string.shield_guard_shortDesc, R.string.shield_guard_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.SHIELD) >= 1, null);}
	}),
	
	SHIELD_SLAM(R.string.shield_slam_name, R.string.shield_slam_longDesc, R.string.shield_slam_shortDesc, R.string.shield_slam_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.STRENGTH) >= 6, null);}
	}),
	
	SIGNAL_LANGUAGE(R.string.signal_language_name, R.string.signal_language_longDesc, R.string.signal_language_shortDesc, R.string.signal_language_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.CRYPTOGRAPHY) >= 1, null);}
	}),
	
	SNIPER(R.string.sniper_name, R.string.sniper_longDesc, R.string.sniper_shortDesc, R.string.sniper_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.RIFLE) >= 3, null);}
	}),
	
	SPECIALIZATION(R.string.specialization_name, R.string.specialization_longDesc, R.string.specialization_shortDesc, R.string.specialization_page,
			false, true, null),
	
	STEADY(R.string.steady_name, R.string.steady_longDesc, R.string.steady_shortDesc, R.string.steady_page,
			false, false, null),
			
	STEAMO(R.string.steamo_name, R.string.steamo_longDesc, R.string.steamo_shortDesc, R.string.steamo_page,
					false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.MECHANIKAL) >= 2, null);}
	}),
	
	SUCKER(R.string.sucker_name, R.string.sucker_longDesc, R.string.sucker_shortDesc, R.string.sucker_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.INTIMIDATION) >= 3, null);}
	}),
	
	SWIFT_HUNTER(R.string.swift_hunter_name, R.string.swift_hunter_longDesc, R.string.swift_hunter_shortDesc, R.string.swift_hunter_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.AGILITY) >= 6, null);}
	}),
	
	SWIFT_RIDER(R.string.swift_rider_name, R.string.swift_rider_longDesc, R.string.swift_rider_shortDesc, R.string.swift_rider_page,
			false, false, null),
			
	TAKE_DOWN(R.string.take_down_name, R.string.take_down_longDesc, R.string.take_down_shortDesc, R.string.take_down_page,
			false, false, null),
			
	TARGETEER(R.string.targeteer_name, R.string.targeteer_longDesc, R.string.targeteer_shortDesc, R.string.targeteer_page,
			false, false, null),
		
	TEAM_LEADER(R.string.team_leader_name, R.string.team_leader_longDesc, R.string.team_leader_shortDesc, R.string.team_leader_page,
			false, false, null),
			
	TRACELESS_PATH(R.string.traceless_path_name, R.string.traceless_path_longDesc, R.string.traceless_path_shortDesc, R.string.traceless_path_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.SNEAK) >= 2, null);}
	}),
	
	TRUTH_READER(R.string.truth_reader_name, R.string.truth_reader_longDesc, R.string.truth_reader_shortDesc, R.string.truth_reader_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.DETECTION) >= 3, null);}
	}),
	
	TUNE_UP(R.string.tune_up_name, R.string.tune_up_longDesc, R.string.tune_up_shortDesc, R.string.tune_up_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getSkillBaseLevel(SkillEnum.MECHANIKAL) >= 3, null);}
	}),
	
	TWO_WEAPON_FIGHTING(R.string.two_weapon_fighting_name, R.string.two_weapon_fighting_longDesc, R.string.two_weapon_fighting_shortDesc, R.string.two_weapon_fighting_page,
			false, false, new zzPrereqCheck(){
			@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.getBaseStat(Stat.AGILITY) >= 4, null);}
	}),
	
	UNIVERSITY_EDUCATION(R.string.university_education_name, R.string.university_education_longDesc, R.string.university_education_shortDesc, R.string.university_education_page,
			false, false, null),
			
	WAYLAY(R.string.waylay_name, R.string.waylay_longDesc, R.string.waylay_shortDesc, R.string.waylay_page,
			false, false, null),
			
			
	/* Archetype Abilities */
	
	COMBAT_CASTER(R.string.combat_caster_name, R.string.combat_caster_longDesc, R.string.combat_caster_shortDesc, R.string.combat_caster_page,
			true, false, GIFTED_CHECK()),
	FAST_CASTER(R.string.fast_caster_name, R.string.fast_caster_longDesc, R.string.fast_caster_shortDesc, R.string.fast_caster_page,
				  true, false, GIFTED_CHECK()),
	FEAT_DOMINATOR(R.string.feat_dominator_name, R.string.feat_dominator_longDesc, R.string.feat_dominator_shortDesc, R.string.feat_dominator_page,
				  true, false, GIFTED_CHECK()),
	FEAT_POWEFUL_CASTER(R.string.feat_powerful_caster_name, R.string.feat_powerful_caster_longDesc, R.string.feat_powerful_caster_shortDesc, R.string.feat_powerful_caster_page,
				  true, false, GIFTED_CHECK()),
	FEAT_QUICK_CAST(R.string.feat_quick_cast_name, R.string.feat_quick_cast_longDesc, R.string.feat_quick_cast_shortDesc, R.string.feat_quick_cast_page,
				  true, false, GIFTED_CHECK()),
	FEAT_STRENGTH_OF_WILL(R.string.feat_strength_of_will_name, R.string.feat_strength_of_will_longDesc, R.string.feat_strength_of_will_shortDesc, R.string.feat_strength_of_will_page,
				  true, false, GIFTED_CHECK()),
	MAGIC_SENSITIVITY(R.string.magic_sensitivity_name, R.string.magic_sensitivity_longDesc, R.string.magic_sensitivity_shortDesc, R.string.magic_sensitivity_page,
				  true, false, GIFTED_CHECK()),
	RUNE_READER(R.string.rune_reader_name, R.string.rune_reader_longDesc, R.string.rune_reader_shortDesc, R.string.rune_reader_page,
				  true, false, GIFTED_CHECK()),
	WARDING_CIRCLE(R.string.warding_circle_name, R.string.warding_circle_longDesc, R.string.warding_circle_shortDesc, R.string.warding_circle_page,
				  true, false, GIFTED_CHECK()),
				  
	INTELLECTUAL(R.string.intellectual_name, R.string.intellectual_longDesc, R.string.intellectual_shortDesc, R.string.intellectual_page,
				   true, false, INTELLECTUAL_CHECK()),
	BATTLEFIELD_COORDINATION(R.string.battlefield_coordination_name, R.string.battlefield_coordination_longDesc, R.string.battlefield_coordination_shortDesc, R.string.battlefield_coordination_page,
				   true, false, INTELLECTUAL_CHECK()),
	FEAT_FLAWLESS_TIMING(R.string.feat_flawless_timing_name, R.string.feat_flawless_timing_longDesc, R.string.feat_flawless_timing_shortDesc, R.string.feat_flawless_timing_page,
				   true, false, INTELLECTUAL_CHECK()),
	FEAT_PRESCIENT(R.string.feat_prescient_name, R.string.feat_prescient_longDesc, R.string.feat_prescient_shortDesc, R.string.feat_prescient_page,
				   true, false, INTELLECTUAL_CHECK()),
	FEAT_PERFECT_PLOT(R.string.feat_perfect_plot_name, R.string.feat_perfect_plot_longDesc, R.string.feat_perfect_plot_shortDesc, R.string.feat_perfect_plot_page,
				   true, false, INTELLECTUAL_CHECK()),
	FEAT_PLAN_OF_ACTION(R.string.feat_plan_of_action_name, R.string.feat_plan_of_action_longDesc, R.string.feat_plan_of_action_shortDesc, R.string.feat_plan_of_action_page,
				   true, false, INTELLECTUAL_CHECK()),
	FEAT_QUICK_THINKING(R.string.feat_quick_thinking_name, R.string.feat_quick_thinking_longDesc, R.string.feat_quick_thinking_shortDesc, R.string.feat_quick_thinking_page,
				   true, false, INTELLECTUAL_CHECK()),
	FEAT_UNCONVENTIONAL_WARFARE(R.string.feat_unconventional_warfare_name, R.string.feat_unconventional_warfare_longDesc, R.string.feat_unconventional_warfare_shortDesc, R.string.feat_unconventional_warfare_page,
				   true, false, INTELLECTUAL_CHECK()),
	GENIUS(R.string.genius_name, R.string.genius_longDesc, R.string.genius_shortDesc, R.string.genius_page,
				   true, false, INTELLECTUAL_CHECK()),
	HYPER_PERCEPTION(R.string.hyper_perception_name, R.string.hyper_perception_longDesc, R.string.hyper_perception_shortDesc, R.string.hyper_perception_page,
				   true, false, INTELLECTUAL_CHECK()),
	PHOTOGRAPHIC_MEMORY(R.string.photographic_memory_name, R.string.photographic_memory_longDesc, R.string.photographic_memory_shortDesc, R.string.photographic_memory_page,
				   true, false, INTELLECTUAL_CHECK()),
				   
	MIGHTY(R.string.mighty_name, R.string.mighty_longDesc, R.string.mighty_shortDesc, R.string.mighty_page,
		true, false, MIGHTY_CHECK()),
	BEAT_BACK(R.string.beat_back_name, R.string.beat_back_longDesc, R.string.beat_back_shortDesc, R.string.beat_back_page,
		true, false, MIGHTY_CHECK()),
	FEAT_BACK_SWING(R.string.feat_back_swing_name, R.string.feat_back_swing_longDesc, R.string.feat_back_swing_shortDesc, R.string.feat_back_swing_page,
		true, false, MIGHTY_CHECK()),
	FEAT_BOUNDING_LEAP(R.string.feat_bounding_leap_name, R.string.feat_bounding_leap_longDesc, R.string.feat_bounding_leap_shortDesc, R.string.feat_bounding_leap_page,
		true, false, MIGHTY_CHECK()),
	FEAT_COUNTER_CHARGE(R.string.feat_counter_charge_name, R.string.feat_counter_charge_longDesc, R.string.feat_counter_charge_shortDesc, R.string.feat_counter_charge_page,
		true, false, MIGHTY_CHECK()),
	FEAT_INVULNERABLE(R.string.feat_invulnerable_name, R.string.feat_invulnerable_longDesc, R.string.feat_invulnerable_shortDesc, R.string.feat_invulnerable_page,
		true, false, MIGHTY_CHECK()),
	FEAT_REVITALIZE(R.string.feat_revitalize_name, R.string.feat_revitalize_longDesc, R.string.feat_revitalize_shortDesc, R.string.feat_revitalize_page,
		true, false, MIGHTY_CHECK()),
	FEAT_SHIELD_BREAKER(R.string.feat_shield_breaker_name, R.string.feat_shield_breaker_longDesc, R.string.feat_shield_breaker_shortDesc, R.string.feat_shield_breaker_page,
		true, false, MIGHTY_CHECK()),
	FEAT_VENDETTA(R.string.feat_vendetta_name, R.string.feat_vendetta_longDesc, R.string.feat_vendetta_shortDesc, R.string.feat_vendetta_page,
		true, false, MIGHTY_CHECK()),
	RIGHTEOUS_ANGER(R.string.righteous_anger_name, R.string.righteous_anger_longDesc, R.string.righteous_anger_shortDesc, R.string.righteous_anger_page,
		true, false, MIGHTY_CHECK()),
	TOUGH(R.string.tough_name, R.string.tough_longDesc, R.string.tough_shortDesc, R.string.tough_page,
		true, false, MIGHTY_CHECK()),

	SKILLED(R.string.skilled_name, R.string.skilled_longDesc, R.string.skilled_shortDesc, R.string.skilled_page,
		true, false, SKILLED_CHECK()),
	AMBIDEXTROUS(R.string.ambidextrous_name, R.string.ambidextrous_longDesc, R.string.ambidextrous_shortDesc, R.string.ambidextrous_page,
		true, false, SKILLED_CHECK()),
	CAGEY(R.string.cagey_name, R.string.cagey_longDesc, R.string.cagey_shortDesc, R.string.cagey_page,
		true, false, SKILLED_CHECK()),
	DEFT(R.string.deft_name, R.string.deft_longDesc, R.string.deft_shortDesc, R.string.deft_page,
		true, false, SKILLED_CHECK()),
	FEAT_DEFENSIVE_STRIKE(R.string.feat_defensive_strike_name, R.string.feat_defensive_strike_longDesc, R.string.feat_defensive_strike_shortDesc, R.string.feat_defensive_strike_page,
		true, false, SKILLED_CHECK()),
	FEAT_DISARM(R.string.feat_disarm_name, R.string.feat_disarm_longDesc, R.string.feat_disarm_shortDesc, R.string.feat_disarm_page,
		true, false, SKILLED_CHECK()),
	FEAT_SWASHBUCKLER(R.string.feat_swashbuckler_name, R.string.feat_swashbuckler_longDesc, R.string.feat_swashbuckler_shortDesc, R.string.feat_swashbuckler_page,
		true, false, SKILLED_CHECK()),
	FEAT_UNTOUCHABLE(R.string.feat_untouchable_name, R.string.feat_untouchable_longDesc, R.string.feat_untouchable_shortDesc, R.string.feat_untouchable_page,
		true, false, SKILLED_CHECK()),
	PRETERNATURAL_AWARENESS(R.string.preternatural_awareness_name, R.string.preternatural_awareness_longDesc, R.string.preternatural_awareness_shortDesc, R.string.preternatural_awareness_page,
		true, false, SKILLED_CHECK()),
	SIDESTEP(R.string.sidestep_name, R.string.sidestep_longDesc, R.string.sidestep_shortDesc, R.string.sidestep_page,
		true, false, SKILLED_CHECK()),
	VIRTUOSO(R.string.virtuoso_name, R.string.virtuoso_longDesc, R.string.virtuoso_shortDesc, R.string.virtuoso_page,
		true, true, SKILLED_CHECK());
	
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
	
	private static zzPrereqCheck GIFTED_CHECK(){return new zzPrereqCheck(){
		@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
		{return new zzPrereqCheckResult(myChar.archetype == Archetype.GIFTED, null);}
	};}
	
	private static zzPrereqCheck INTELLECTUAL_CHECK(){return new zzPrereqCheck(){
		@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
		{return new zzPrereqCheckResult(myChar.archetype == Archetype.INTELLECTUAL, null);}
	};}
	
	private static zzPrereqCheck MIGHTY_CHECK(){return new zzPrereqCheck(){
		@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
		{return new zzPrereqCheckResult(myChar.archetype == Archetype.MIGHTY, null);}
	};}
	
	private static zzPrereqCheck SKILLED_CHECK(){return new zzPrereqCheck(){
		@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
		{return new zzPrereqCheckResult(myChar.archetype == Archetype.SKILLED, null);}
	};}
}
