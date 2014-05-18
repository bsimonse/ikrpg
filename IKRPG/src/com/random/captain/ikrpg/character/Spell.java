package com.random.captain.ikrpg.character;


import android.content.Context;
import com.random.captain.ikrpg.IKRPGApp;
import com.random.captain.ikrpg.R;

public enum Spell
{
	ARCANE_BOLT(R.string.arcane_bolt_name, R.string.arcane_bolt_desc, 2, 12, 0, 11, false, true),
	ARCANE_STRIKE(R.string.arcane_strike_name, R.string.arcane_strike_desc, 1, 8, 0, 8, false, true),
	ARCANTRIK_BOLT(R.string.arcantrik_bolt_name, R.string.arcantrik_bolt_desc,2,10,0,12,false,true),
	ASHEN_CLOUD(R.string.ashen_cloud_name,R.string.ashen_cloud_desc,2,SpellRange.CTRL,3,0,true,false),
	ASHES_TO_ASHES(R.string.ashes_to_ashes_name,R.string.ashes_to_ashes_desc,4,8,SpellRange.SPECIAL,10,false,true),
	AURA_OF_PROTECTION(R.string.aura_of_protection_name,R.string.aura_of_protection_desc,2,SpellRange.SELF,SpellRange.CTRL,0,true,false),
	AWARENESS(R.string.awareness_name,R.string.awareness_desc,3,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	BANISHING_WARD(R.string.banishing_ward_name,R.string.banishing_ward_desc,2,6,0,0,true,false),
	BARRIER_OF_FLAMES(R.string.barrier_of_flames_name,R.string.barrier_of_flames_desc,3,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	BATTERING_RAM(R.string.battering_ram_name,R.string.battering_ram_desc,2,6,0,12,false,true),
	BATTEN_DOWN_THE_HATCHES(R.string.batten_down_the_hatches_name,R.string.batten_down_the_hatches_desc,3,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	BLACK_OUT(R.string.black_out_name, R.string.black_out_desc,4,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	BLADE_OF_RADIANCE(R.string.blade_of_radiance_desc,R.string.blade_of_radiance_name,2,10,0,10,false,true),
	BLAZING_EFFIGY(R.string.blazing_effigy_name,R.string.blazing_effigy_desc,4,SpellRange.SELF,SpellRange.SPECIAL,14,false,false),
	BLESSING_OF_HEALTH(R.string.blessing_of_health_name,R.string.blessing_of_health_desc,1,6,0,0,true,false),
	BLESSING_OF_MORROW(R.string.blessing_of_morrow_name,R.string.blessing_of_morrow_desc,3,SpellRange.SELF,SpellRange.CTRL,0,true,false),
	BLESSINGS_OF_WAR(R.string.blessings_of_war_name,R.string.blessings_of_war_desc,2,6,0,0,true,false),
	BLIZZARD(R.string.blizzard_name,R.string.blizzard_desc,1,6,0,0,false,false),
	BRITTLE_FROST(R.string.brittle_frost_name,R.string.brittle_frost_desc,3,8,0,0,true,true),
	BOUNDLESS_CHARGE(R.string.boundless_charge_name,R.string.boundless_charge_desc,2,6,0,0,false,false),
	BROADSIDE(R.string.broadside_name,R.string.broadside_desc,3,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	CELERITY(R.string.celerity_name,R.string.celerity_desc,2,6,0,0,true,false),
	CHAIN_LIGHTNING(R.string.chain_lightning_name,R.string.chain_lightning_desc,3,10,0,10,false,true),
	CHILLER(R.string.chiller_name,R.string.chiller_desc,2,6,0,0,true,false),
	CLEANSING_FIRE(R.string.cleansing_fire_name,R.string.cleansing_fire_desc,3,8,3,14,false,true),
	CONVECTION(R.string.convection_name,R.string.convection_desc,2,10,0,12,false,true),
	CREVASSE(R.string.crevasse_name,R.string.crevasse_desc,3,8,0,12,false,true),
	CRUSADERS_CALL(R.string.crusaders_call_name,R.string.crusaders_call_desc,3,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	DAYLIGHT(R.string.daylight_name,R.string.daylight_desc,3,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	DECELERATION(R.string.deceleration_name,R.string.deceleration_desc,3,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	DEEP_FREEZE(R.string.deep_freeze_name,R.string.deep_freeze_desc,3,SpellRange.SELF,0,0,false,false),
	EARTHQUAKE(R.string.earthquake_name,R.string.earthquake_desc,3,10,5,0,false,true),
	EARTHS_CRADLE(R.string.earths_cradle_name,R.string.earths_cradle_desc,1,SpellRange.SELF,0,0,true,false),
	EARTHSPLITTER(R.string.earthsplitter_name,R.string.earthsplitter_desc,4,10,3,14,false,true),
	ELECTRICAL_BLAST(R.string.electrical_blast_name,R.string.electrical_blast_desc,3,8,3,13,false,true),
	ELECTRIFY(R.string.electrify_name,R.string.electrify_desc,2,6,0,0,true,false),
	ELIMINATOR(R.string.eliminator_name,R.string.eliminator_desc,3,8,3,13,false,true),
	ENTANGLE(R.string.entangle_name,R.string.entangle_desc,1,8,0,0,false,true),
	EYES_OF_TRUTH(R.string.eyes_of_truth_name,R.string.eyes_of_truth_desc,2,SpellRange.SELF,0,0,true,false),
	EXTINGUISHER(R.string.extinguisher_name,R.string.extinguisher_desc,2,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	FAIL_SAFE(R.string.fail_safe_name,R.string.fail_safe_desc,3,6,0,0,true,false),
	FAIR_WINDS(R.string.fair_winds_name,R.string.fair_winds_desc,1,SpellRange.SELF,0,0,false,false),
	FIRE_GROUP(R.string.fire_group_name,R.string.fire_group_desc,2,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	FIRE_STARTER(R.string.fire_starter_name,R.string.fire_starter_desc,1,8,0,0,false,true),
	FLAMES_OF_WRATH(R.string.flames_of_wrath_name,R.string.flames_of_wrath_desc,1,6,0,0,false,false),
	FLARE(R.string.flare_name,R.string.flare_desc,3,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	FOG_OF_WAR(R.string.fog_of_war_name,R.string.fog_of_war_desc,3,SpellRange.SELF,SpellRange.CTRL,0,true,false),
	FORCE_FIELD(R.string.force_field_name,R.string.force_field_desc,3,SpellRange.SELF,SpellRange.CTRL,0,true,false),
	FORCE_HAMMER(R.string.force_hammer_name,R.string.force_hammer_desc,4,10,0,12,false,true),
	FORCE_OF_FAITH(R.string.force_of_faith_name,R.string.force_of_faith_desc,4,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	FORTIFY(R.string.fortify_name,R.string.fortify_desc,2,6,0,0,true,false),
	FOXHOLE(R.string.foxhole_name,R.string.foxhole_desc,2,SpellRange.CTRL,5,0,true,false),
	FREEZING_GRIP(R.string.freezing_grip_name,R.string.freezing_grip_desc,4,8,0,0,false,true),
	FREEZING_MIST(R.string.freezing_mist_name,R.string.freezing_mist_desc,4,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	FROZEN_GROUND(R.string.frozen_ground_name,R.string.frozen_ground_desc,3,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	FROSTBITE(R.string.frostbite_name,R.string.frostbite_desc,2,SpellRange.SP8,0,12,false,true),
	FUEL_THE_FLAMES(R.string.fuel_the_flames_name,R.string.fuel_the_flames_desc,3,SpellRange.SELF,SpellRange.CTRL,0,true,false),
	FULL_THROTTLE(R.string.full_throttle_name,R.string.full_throttle_desc,3,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	GRIND(R.string.grind_name,R.string.grind_desc,3,10,0,14,false,true),
	GUIDED_BLADE(R.string.guided_blade_name,R.string.guided_blade_desc,1,6,0,0,false,false),
	GUIDED_FIRE(R.string.guided_fire_name,R.string.guided_fire_desc,3,SpellRange.SELF, SpellRange.CTRL, 0, false, false),
	HAND_OF_FATE(R.string.hand_of_fate_name,R.string.hand_of_fate_desc,2,6,0,0,true,false),
	HEAL(R.string.heal_name,R.string.heal_desc,4,SpellRange.SPECIAL,0,0,false,false),
	HEIGHTENED_REFLEXES(R.string.heightened_reflexes_name,R.string.heightened_reflexes_desc,2,6,0,0,true,false),
	HEX_BLAST(R.string.hex_blast_name,R.string.hex_blast_desc,3,10,3,13,false,true),
	HOARFROST(R.string.hoarfrost_name,R.string.hoarfrost_desc,3,8,3,14,false,true),
	HOWLING_FLAMES(R.string.howling_flames_name,R.string.howling_flames_desc,2,SpellRange.SP8,0,10,false,true),
	HYMN_OF_BATTLE(R.string.hymn_of_battle_name,R.string.hymn_of_battle_desc,2,6,0,0,false,false),
	HYMN_OF_PASSAGE(R.string.hymn_of_passage_name,R.string.hymn_of_passage_desc,2,6,0,0,false,false),
	HYMN_OF_SHIELDING(R.string.hymn_of_shielding_name,R.string.hymn_of_shielding_desc,4,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	ICE_BOLT(R.string.ice_bolt_name,R.string.ice_bolt_desc,2,10,0,12,false,true),
	ICE_SHIELD(R.string.ice_shield_name,R.string.ice_shield_desc,1,6,0,0,true,false),
	ICY_GRIP(R.string.icy_grip_name,R.string.icy_grip_desc,2,8,0,0,true,true),
	IGNITE(R.string.ignite_name,R.string.ignite_desc,2,6,0,0,true,false),
	IMMOLATION(R.string.immolation_name,R.string.immolation_desc,2,8,0,12,false,true),
	INFERNO(R.string.inferno_name,R.string.inferno_desc,3,10,3,12,false,true),
	INFLUENCE(R.string.influence_name,R.string.influence_desc,1,10,0,0,false,true),
	INHOSPITABLE_GROUND(R.string.inhospitable_ground_name,R.string.inhospitable_ground_desc,3,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	IRON_AGGRESSION(R.string.iron_aggression_name,R.string.iron_aggression_desc,3,6,0,0,true,false),
	JACKHAMMER(R.string.jackhammer_name,R.string.jackhammer_desc,1,6,0,0,false,false),
	JUMP_START(R.string.jump_start_name,R.string.jump_start_desc,1,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	LAMENTATION(R.string.lamentation_name,R.string.lamentation_desc,3,SpellRange.SELF,SpellRange.CTRL,0,true,false),
	LIGHT_IN_THE_DARKNESS(R.string.light_in_the_darkness_name,R.string.light_in_the_darkness_desc,1,SpellRange.SELF,SpellRange.CTRL,0,true,false),
	LIGHTNING_TENDRILS(R.string.lightning_tendrils_name,R.string.lightning_tendrils_desc,3,6,0,0,true,false),
	LOCOMOTION(R.string.locomotion_name,R.string.locomotion_desc,1,6,0,0,false,false),
	MIRAGE(R.string.mirage_name,R.string.mirage_desc,3,6,0,0,true,false),
	OBLITERATION(R.string.obliteration_name,R.string.obliteration_desc,4,10,4,15,false,true),
	OCCULTATION(R.string.occultation_name,R.string.occultation_desc,2,6,0,0,true,false),
	OVERMIND(R.string.overmind_name,R.string.overmind_desc,4,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	POLARITY_SHIELD(R.string.polarity_shield_name,R.string.polarity_shield_desc,2,6,0,0,true,false),
	POSITIVE_CHARGE(R.string.positive_charge_name,R.string.positive_charge_desc,2,6,0,0,false,false),
	POWER_BOOSTER(R.string.power_booster_name,R.string.power_booster_desc,1,5,0,0,false,false),
	PRAYER_FOR_GUIDANCE(R.string.prayer_for_guidance_name,R.string.prayer_for_guidance_desc,3,6,0,0,false,false),
	PROTECTION_FROM_COLD(R.string.protection_from_cold_name,R.string.protection_from_cold_desc,1,6,0,0,true,false),
	PROTECTION_FROM_CORROSION(R.string.protection_from_corrosion_name,R.string.protection_from_corrosion_desc,1,6,0,0,true,false),
	PROTECTION_FROM_ELECTRICITY(R.string.protection_from_electricity_name,R.string.protection_from_electricity_desc,1,6,0,0,true,false),
	PROTECTION_FROM_FIRE(R.string.protection_from_fire_name,R.string.protection_from_fire_desc,1,6,0,0,true,false),
	PURIFICATION(R.string.purification_name,R.string.purification_desc,3,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	RAGING_WINDS(R.string.raging_winds_name,R.string.raging_winds_desc,4,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	RAZOR_WIND(R.string.razor_wind_name,R.string.razor_wind_desc,2,10,0,12,false,true),
	REDLINE(R.string.redline_name,R.string.redline_desc,2,6,0,0,true,false),
	REFUGE(R.string.refuge_name,R.string.refuge_desc,2,6,0,0,true,false),
	RETURN_FIRE(R.string.return_fire_spell_name,R.string.return_fire_desc,1,6,0,0,false,false),
	RIFT(R.string.rift_name,R.string.rift_desc,3,8,4,13,false,true),
	RIGHTEOUS_FLAMES(R.string.righteous_flames_name,R.string.righteous_flames_desc,2,6,0,0,false,false),
	RIME(R.string.rime_name,R.string.rime_desc,2,6,0,0,false,false),
	ROCK_HAMMER(R.string.rock_hammer_name,R.string.rock_hammer_desc,3,10,3,14,false,true),
	ROCK_WALL(R.string.rock_wall_name,R.string.rock_wall_desc,2,SpellRange.CTRL,SpellRange.WALL,0,true,false),
	RUNE_SHOT_ACCURACY(R.string.rune_shot_accuracy_name,R.string.rune_shot_accuracy_desc,1,SpellRange.SELF,0,0,false,false),
	RUNE_SHOT_BLACK_PENNY(R.string.rune_shot_black_penny_name,R.string.rune_shot_black_penny_desc,1,SpellRange.SELF,0,0,false,false),
	RUNE_SHOT_BRUTAL(R.string.rune_shot_brutal_name,R.string.rune_shot_brutal_desc,1,SpellRange.SELF,0,0,false,false),
	RUNE_SHOT_DETONATOR(R.string.rune_shot_detonator_name,R.string.rune_shot_detonator_desc,3,SpellRange.SELF,4,0,false,false),
	RUNE_SHOT_EARTH_SHAKER(R.string.rune_shot_earth_shaker_name,R.string.rune_shot_earth_shaker_desc,3,SpellRange.SELF,5,0,false,false),
	RUNE_SHOT_FIRE_BEACON(R.string.rune_shot_fire_beacon_name,R.string.rune_shot_fire_beacon_desc,2,SpellRange.SELF,5,0,false,false),
	RUNE_SHOT_FREEZE_FIRE(R.string.rune_shot_freeze_fire_name,R.string.rune_shot_freeze_fire_desc,4,SpellRange.SELF,0,0,false,false),
	RUNE_SHOT_HEART_STOPPER(R.string.rune_shot_heart_stopper_name,R.string.rune_shot_heart_stopper_desc,4,SpellRange.SELF,0,0,false,false),
	RUNE_SHOT_IRON_ROT(R.string.rune_shot_iron_rot_name,R.string.rune_shot_iron_rot_desc,1,SpellRange.SELF,0,0,false,false),
	RUNE_SHOT_MOLTEN_SHOT(R.string.rune_shot_molten_shot_name,R.string.rune_shot_molten_shot_desc,1,SpellRange.SELF,0,0,false,false),
	RUNE_SHOT_MOMENTUM(R.string.rune_shot_momentum_name,R.string.rune_shot_momentum_desc,4,SpellRange.SELF,0,0,false,false),
	RUNE_SHOT_PHANTOM_SEEKER(R.string.rune_shot_phantom_seeker_name,R.string.rune_shot_phantom_seeker_desc,3,SpellRange.SELF,0,0,false,false),
	RUNE_SHOT_SHADOW_FIRE(R.string.rune_shot_shadow_fire_name,R.string.rune_shot_shadow_fire_desc,2,SpellRange.SELF,0,0,false,false),
	RUNE_SHOT_SILENCER(R.string.rune_shot_silencer_name,R.string.rune_shot_silencer_desc,1,SpellRange.SELF,0,0,false,false),
	RUNE_SHOT_SPELL_CRACKER(R.string.rune_shot_spell_cracker_name,R.string.rune_shot_spell_cracker_desc,3,SpellRange.SELF,0,0,false,false),
	RUNE_SHOT_SPONTANEOUS_COMBUSTION(R.string.rune_shot_spontaneous_combustion_name,R.string.rune_shot_spontaneous_combustion_desc,1,SpellRange.SELF,3,0,false,false),
	RUNE_SHOT_THUNDERBOLT(R.string.rune_shot_thunderbolt_name,R.string.rune_shot_thunderbolt_desc,1,SpellRange.SELF,0,0,false,false),
	RUNE_SHOT_TRICK_SHOT(R.string.rune_shot_trick_shot_name,R.string.rune_shot_trick_shot_desc,2,SpellRange.SELF,0,0,false,false),
	SANGUINE_BLESSING(R.string.sanguine_blessing_name,R.string.sanguine_blessing_desc,3,SpellRange.SELF,SpellRange.CTRL,0,true,false),
	SEA_OF_FIRE(R.string.sea_of_fire_name,R.string.sea_of_fire_desc,4,SpellRange.SELF,5,0,false,false),
	SHATTER_STORM(R.string.shatter_storm_name,R.string.shatter_storm_desc,2,6,0,0,true,false),
	SHIELD_OF_FAITH(R.string.shield_of_faith_name,R.string.shield_of_faith_desc,2,6,0,0,true,false),
	SHOCK_WAVE(R.string.shock_wave_name,R.string.shock_wave_desc,4,SpellRange.SELF,5,13,false,false),
	SHORT_OUT(R.string.short_out_name,R.string.short_out_desc,1,8,0,0,false,true),
	SNIPE(R.string.snipe_name,R.string.snipe_desc,2,6,0,0,true,false),
	SOLID_GROUND(R.string.solid_ground_name,R.string.solid_ground_desc,2,SpellRange.SELF,SpellRange.CTRL,0,true,false),
	SOLOVINS_BOON(R.string.solovins_boon_name,R.string.solovins_boon_desc,1,SpellRange.SELF,0,0,true,false),
	STAR_FIRE(R.string.star_fire_name,R.string.star_fire_desc,4,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	STAYING_WINTERS_HAND(R.string.staying_winters_hand_name,R.string.staying_winters_hand_desc,2,SpellRange.SELF,SpellRange.CTRL,0,true,false),
	STONE_STANCE(R.string.stone_stance_name,R.string.stone_stance_desc,1,6,0,0,false,false),
	STONE_STRENGTH(R.string.stone_strength_name,R.string.stone_strength_desc,2,6,0,0,true,false),
	STORM_TOSSED(R.string.storm_tossed_name,R.string.storm_tossed_desc,1,8,0,0,false,true),
	SUNBURST(R.string.sunburst_name,R.string.sunburst_desc,3,10,3,13,false,true),
	SUPERIORITY(R.string.superiority_name,R.string.superiority_desc,3,6,0,0,true,false),
	TELEKINESIS(R.string.telekinesis_name,R.string.telekinesis_desc,2,8,0,0,false,true),
	TEMPER_METAL(R.string.temper_metal_name,R.string.temper_metal_desc,2,6,0,0,true,false),
	TEMPEST(R.string.tempest_name,R.string.tempest_desc,4,8,4,12,false,true),
	TIDE_OF_STEEL(R.string.tide_of_steel_name,R.string.tide_of_steel_desc,4,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	TORNADO(R.string.tornado_name,R.string.tornado_desc,4,10,0,13,false,true),
	TRANSFERENCE(R.string.transference_name,R.string.transference_desc,2,SpellRange.SELF,SpellRange.CTRL,0,true,false),
	TRIAGE(R.string.triage_name,R.string.triage_desc,2,SpellRange.B2B, 0,0,false,false),
	TRUE_PATH(R.string.true_path_name,R.string.true_path_desc,3,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	TRUE_SIGHT(R.string.true_sight_name,R.string.true_sight_desc,2,SpellRange.SELF,0,0,true,false),
	VISION(R.string.vision_name,R.string.vision_desc,2,6,0,0,true,false),
	VOLTAIC_LOCK(R.string.voltaic_lock_name,R.string.voltaic_lock_desc,4,10,SpellRange.SPECIAL,0,false,true),
	WALL_OF_FIRE(R.string.wall_of_fire_name,R.string.wall_of_fire_desc,2,SpellRange.CTRL,SpellRange.WALL,0,true,false),
	WHITE_OUT(R.string.white_out_name,R.string.white_out_desc,4,SpellRange.SELF,SpellRange.CTRL,0,true,false),
	WIND_BLAST(R.string.wind_blast_name,R.string.wind_blast_desc,2,SpellRange.CTRL,5,0,false,false),
	WIND_STRIKE(R.string.wind_strike_name,R.string.wind_strike_desc,1,6,0,0,false,true),
	WINGS_OF_AIR(R.string.wings_of_air_name,R.string.wings_of_air_desc,2,SpellRange.SELF,0,0,false,false),
	WINTER_STORM(R.string.winter_storm_name,R.string.winter_storm_desc,3,SpellRange.SELF,SpellRange.CTRL,0,false,false),
	ZEPHYR(R.string.zephyr_name,R.string.zephyr_desc,3,6,0,0,false,false);
	
	private Spell(int pName, int pDesc, int pCost, int pRange, int pAOE, int pPow, boolean pUpkeepable, boolean pOffensive)
	{
		Context c = IKRPGApp.getContext();
		spellName = c.getResources().getString(pName);
		description = c.getResources().getString(pDesc);
		cost = pCost;
		range = pRange;
		aoe = pAOE;
		pow = pPow;
		upkeepable = pUpkeepable;
		offensive = pOffensive;
	}

	private String spellName;
	private String description;
	private int cost;
	private int range;
	private int aoe;
	private int pow;
	private boolean upkeepable;
	private boolean offensive;
	
	@Override
	public String toString() {return displayName();}
	public String displayName() {return spellName;}
	public String description(){return description;}
	public int cost(){return cost;}
	public int range(){return range;}
	public int aoe(){return aoe;}
	public int pow(){return pow;};
	public boolean isUpkeepable(){return upkeepable;}
	public boolean isOffensive(){return offensive;}
}
	
class SpellRange
{	
	public static final int CTRL = -1;
	public static final int SELF = -2;
	public static final int SPECIAL = -3;
	public static final int B2B = -4;
	public static final int SP8 = -5;
	public static final int WALL = -6;
}
