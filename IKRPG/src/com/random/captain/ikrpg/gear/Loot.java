package com.random.captain.ikrpg.gear;

import java.util.ArrayList;
import java.util.List;

import com.random.captain.ikrpg.character.Ability;
import com.random.captain.ikrpg.character.CharacterBond;
import com.random.captain.ikrpg.character.Modifier;
import com.random.captain.ikrpg.character.Skill;
import com.random.captain.ikrpg.character.Stat;
import com.random.captain.ikrpg.combat.SpecialRule;

public class Loot
{
	public enum EquipmentSlots
	{
		HEAD,
		BODY,
		ARM1,
		ARM2;
	}

	String name;
	String description;
	int goldCost;

	List<Modifier<Stat>> statModifiers;
	List<Modifier<Skill>> skillModifiers;
	List<Ability> grantedAbilities;
	List<SpecialRule> specialRules;
	List<EquipmentSlots> slots;
	
	CharacterBond bond;
	
	Loot()
	{
		this("","",0,null,null,null,null,null,null);
	}
	
	public Loot(String pName, String pDesc, int pCost, List<Modifier<Stat>> pStats, List<Modifier<Skill>> pSkills, List<Ability> pAbilities,
						List<SpecialRule> pRules, List<EquipmentSlots> pSlots, CharacterBond pBond)
	{
		name = pName;
		description = pDesc;
		goldCost = pCost;
		statModifiers = pStats != null ? pStats : new ArrayList<Modifier<Stat>>();
		skillModifiers = pSkills != null ? pSkills : new ArrayList<Modifier<Skill>>();
		grantedAbilities = pAbilities != null ? pAbilities : new ArrayList<Ability>();
		specialRules = pRules != null ? pRules : new ArrayList<SpecialRule>();
		slots = pSlots != null ? pSlots : new ArrayList<EquipmentSlots>();
		bond = pBond;
	}
}
