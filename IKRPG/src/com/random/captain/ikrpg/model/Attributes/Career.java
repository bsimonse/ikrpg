package com.random.captain.ikrpg.model.Attributes;

import android.util.Pair;
import java.util.ArrayList;
import java.util.Collection;

public enum Career
{
	ALCHEMIST("Alchemist",
				new Pair[] {Pair.create(Skill.HAND_WEAPON, 1), Pair.create(Skill.THROWN_WEAPON, 1), Pair.create(Skill.ALCHEMY, 1), Pair.create(Skill.MEDICINE, 1)},
				new Ability[] {Ability.GRENADIER, Ability.POISON_RESISTANCE},
				new Pair[] {Pair.create(Skill.HAND_WEAPON, 2), Pair.create(Skill.THROWN_WEAPON, 2), Pair.create(Skill.UNARMED, 2), Pair.create(Skill.ALCHEMY, 4),
							Pair.create(Skill.CRAFT, 4), Pair.create(Skill.FORGERY, 2), Pair.create(Skill.MEDICINE,4), Pair.create(Skill.NEGOTIATION, 4),
							Pair.create(Skill.RESEARCH, 4)},
				new Ability[] {Ability.BOMBER, Ability.BREW_MASTER, Ability.FAST_COOK, Ability.FIELD_ALCHEMIST, Ability.FIRE_IN_THE_HOLE, Ability.FREE_STYLE,
								Ability.GRENADIER, Ability.POISON_RESISTANCE}),
								
	ARCANE_MECHANIK("Arcane Mechanik",null,null,null,null);
	
	private Career(String pName,Pair<Skill, Integer>[] pStartSkills, Ability[] pStartAbilities, Pair<Skill, Integer>[] pSkills, Ability[] pAbilities)
	{
		name = pName;
		startingSkills = pStartSkills;
		startingAbilities = pStartAbilities;
		careerSkills = pSkills;
		careerAbilities = pAbilities;
	}
	
	private String name;
	private Pair<Skill, Integer>[] startingSkills;
	private Ability[] startingAbilities;
	private Pair<Skill, Integer>[] careerSkills;
	private Ability[] careerAbilities;
}
