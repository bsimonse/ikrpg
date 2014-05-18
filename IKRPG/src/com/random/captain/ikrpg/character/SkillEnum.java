package com.random.captain.ikrpg.character;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Pair;

import com.random.captain.ikrpg.IKRPGApp;
import com.random.captain.ikrpg.R;

public enum SkillEnum
{
	ARCHERY(R.string.archery_name, R.string.archery_desc, Stat.POISE, true, false, false, true),
	CROSSBOW(R.string.crossbow_name, R.string.crossbow_desc, Stat.POISE, true, false, false, true),
	GREAT_WEAPON(R.string.great_weapon_name, R.string.great_weapon_desc, Stat.PROWESS, true, false, false, true),
	HAND_WEAPON(R.string.hand_weapon_name, R.string.hand_weapon_desc, Stat.PROWESS, true, false, false, true),
	LANCE(R.string.lance_name, R.string.lance_desc, Stat.PROWESS, true, false, false, true),
	LIGHT_ARTILLERY(R.string.light_artillery_name, R.string.light_artillery_desc, Stat.POISE, true, false, false, true),
	PISTOL(R.string.pistol_name, R.string.pistol_desc, Stat.POISE, true, false, false, true),
	RIFLE(R.string.rifle_name, R.string.rifle_desc, Stat.POISE, true, false, false, true),
	SHIELD(R.string.shield_name, R.string.shield_desc, Stat.PROWESS, true, false, false, true),
	THROWN_WEAPON(R.string.thrown_weapon_name, R.string.thrown_weapon_desc, Stat.PROWESS, true, false, false, true),
	UNARMED(R.string.unarmed_combat_name, R.string.unarmed_combat_desc, Stat.PROWESS, true, false, false, true),
	ALCHEMY(R.string.alchemy_name, R.string.alchemy_desc, Stat.INTELLECT, false, false, false, false),
	ANIMAL_HANDLING(R.string.animal_handling_name, R.string.animal_handling_desc, null, false, true, false, false),
	BRIBERY(R.string.bribery_name, R.string.bribery_desc, null, false, false, false, true),
	CLIMBING(R.string.climbing_name, R.string.climbing_desc, Stat.AGILITY, false, true, false, true),
	COMMAND(R.string.command_name, R.string.command_desc, null, false, false, false, false),
	CRAFT(R.string.craft_name, R.string.craft_desc, Stat.INTELLECT, false, false, true, false),
	CRYPTOGRAPHY(R.string.cryptography_name, R.string.cryptography_desc, Stat.INTELLECT, false, false, false, false),
	DECEPTION(R.string.deception_name, R.string.deception_desc, null, false, false, false, true),
	DETECTION(R.string.detection_name, R.string.detection_desc, Stat.PERCEPTION, false, true, false, true),
	DISGUISE(R.string.disguise_name, R.string.disguise_desc, Stat.INTELLECT, false, false, false, true),
	DRIVING(R.string.driving_name, R.string.driving_desc, Stat.AGILITY, false, true, false, true),
	ESCAPE_ARTIST(R.string.escape_artist_name, R.string.escape_artist_desc, Stat.AGILITY, false, false, false, true),
	ETIQUETTE(R.string.etiquette_name, R.string.etiquette_desc, null, false, false, false, true),
	FELL_CALLING(R.string.fell_calling_name, R.string.fell_calling_desc, Stat.POISE, false, false, false, false),
	FORENSIC_SCIENCE(R.string.forensic_science_name, R.string.forensic_science_desc, Stat.INTELLECT, false, false, false, false),
	FORGERY(R.string.forgery_name, R.string.forgery_desc, null, false, false, false, false),
	GAMBLING(R.string.gambling_name, R.string.gambling_desc, Stat.PERCEPTION, false, true, false, true),
	INTERROGATION(R.string.interrogation_name, R.string.interrogation_desc, Stat.INTELLECT, false, false, false, true),
	INTIMIDATION(R.string.intimidation_name, R.string.intimidation_desc, null, false, true, false, true),
	JUMPING(R.string.jumping_name, R.string.jumping_desc,  Stat.PHYSIQUE, false, true, false, true),
	LAW(R.string.law_name, R.string.law_desc, Stat.INTELLECT, false, false, false, false),
	LOCK_PICKING(R.string.lock_picking_name, R.string.lock_picking_desc, Stat.AGILITY, false, false, false, false),
	LORE(R.string.lore_name, R.string.lore_desc, Stat.INTELLECT, false, true, true, true),
	MECHANIKAL(R.string.mechanikal_engineering_name, R.string.mechanikal_engineering_desc, Stat.INTELLECT, false, false, false, false),
	MEDICINE(R.string.medicine_name, R.string.medicine_desc, Stat.INTELLECT, false, false, false, true),
	NAVIGATION(R.string.navigation_name, R.string.navigation_desc, Stat.PERCEPTION, false, false, false, false),
	NEGOTIATION(R.string.negotiation_name, R.string.negotiation_desc, null, false, false, false, false),
	ORATORY(R.string.oratory_name, R.string.oratory_desc, null, false, false, false, false),
	PICKPOCKET(R.string.pickpocket_name, R.string.pickpocket_desc, Stat.AGILITY, false, false, false, false),
	RESEARCH(R.string.research_name, R.string.research_desc, Stat.INTELLECT, false, false, false, true),
	RIDING(R.string.riding_name, R.string.riding_desc, Stat.AGILITY, false, true, false, true),
	ROPE_USE(R.string.rope_use_name, R.string.rope_use_desc, Stat.AGILITY, false, false, false, true),
	SAILING(R.string.sailing_name, R.string.sailing_desc, null, false, false, false, false),
	SEDUCTION(R.string.seduction_name, R.string.seduction_desc, null, false, false, false, false),
	SNEAK(R.string.sneak_name, R.string.sneak_desc, Stat.AGILITY, false, false, false, true),
	STREETWISE(R.string.streetwise_name, R.string.streetwise_desc, Stat.PERCEPTION, false, false, false, false),
	SURVIVAL(R.string.survival_name, R.string.survival_desc, Stat.PERCEPTION, false, false, false, true),
	SWIMMING(R.string.swimming_name, R.string.swimming_desc, Stat.STRENGTH, false, true, false, true),
	TRACKING(R.string.tracking_name, R.string.tracking_desc, Stat.PERCEPTION, false, false, false, false);
	
	private SkillEnum(int pNameResource, int pDescResource, Stat pStat, boolean pMilitary, boolean pGeneral, boolean pIsQualifiable, boolean pUntrained)
	{
		Context c = IKRPGApp.getContext();
		name = c.getString(pNameResource);
		desc = c.getString(pDescResource);
		governingStat = pStat;
		isMilitary = pMilitary;
		isGeneral = pGeneral;
		isQualifiable = pIsQualifiable;
		canUseUntrained = pUntrained || pMilitary; //just in case
	}
	
	@Override
	public String toString(){return displayName();}
	
	//Assisting will come later
	
	private String name;
	private String desc;
	private boolean isMilitary;
	private boolean isGeneral;
	private boolean canUseUntrained;
	private boolean isQualifiable;
	private Stat governingStat;
	
	public String displayName(){return name;}
	public String description(){return desc;}
	public Stat governingStat(){return governingStat;}
	public boolean isMilitary(){return isMilitary;}
	public boolean isGeneral(){return isGeneral;}
	public boolean isQualifiable(){return isQualifiable;}
	public boolean canUseUntrained(){return canUseUntrained;}
	
	public Skill make(){return new Skill(this);}
	public Skill make(String qualifier){return new Skill(this, qualifier);}
	
	public Pair<Skill, Integer> pair(int skillLevel){return new Pair<Skill,Integer>(new Skill(this),skillLevel);}
	public Pair<Skill, Integer> pair(String qualifier, int skillLevel){return new Pair<Skill, Integer>(new Skill(this, qualifier),skillLevel);}
	
	private static List<SkillEnum> generalSkills;
	public static List<SkillEnum> generalSkills()
	{
		if(generalSkills == null)
		{
			generalSkills = new ArrayList<SkillEnum>(40);
			for(SkillEnum skill : SkillEnum.values())
			{
				if(skill.isGeneral()){generalSkills.add(skill);}
			}
		}
		
		return generalSkills;
	}
}
