package com.random.captain.ikrpg.model.Attributes;

import android.util.Pair;
import com.random.captain.ikrpg.model.Creators.PostCreateHook;
import com.random.captain.ikrpg.model.Creators.PrereqCheck;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public enum Career
{
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
			  new Pair[] {Pair.create(Skill.HAND_WEAPON, 1), Pair.create(Skill.THROWN_WEAPON, 1), Pair.create(Skill.ALCHEMY, 1), Pair.create(Skill.MEDICINE, 1)},
			  new Pair[] {Pair.create(Skill.HAND_WEAPON, 2), Pair.create(Skill.THROWN_WEAPON, 2), Pair.create(Skill.UNARMED, 2), Pair.create(Skill.ALCHEMY, 4),
				  Pair.create(Skill.CRAFT, 4), Pair.create(Skill.FORGERY, 2), Pair.create(Skill.MEDICINE,4), Pair.create(Skill.NEGOTIATION, 4),
				  Pair.create(Skill.RESEARCH, 4)},
			  new Ability[] {Ability.GRENADIER, Ability.POISON_RESISTANCE},
			  new Ability[] {Ability.BOMBER, Ability.BREW_MASTER, Ability.FAST_COOK, Ability.FIELD_ALCHEMIST, Ability.FIRE_IN_THE_HOLE, Ability.FREE_STYLE,
				  Ability.GRENADIER, Ability.POISON_RESISTANCE},
			  null, null,
			  null, null);
			  
	private Career(String pName,
					Pair<Skill, Integer>[] pStartSkills, Pair<Skill, Integer>[] pSkills,
				    Ability[] pStartAbilities, Ability[] pAbilities,
					Spell[] pStartSpells, Spell[] pSpells,
					PrereqCheck pPrereqCheck, PostCreateHook pPostCreateHook)
	{
		name = pName;
		startingSkills = Arrays.asList(pStartSkills);
		careerSkills = Arrays.asList(pSkills);
		startingAbilities = Arrays.asList(pStartAbilities);
		careerAbilities = Arrays.asList(pAbilities);
		startingSpells = Arrays.asList(pStartSpells);
		careerSpells = Arrays.asList(pSpells);
		prereqCheck = pPrereqCheck;
		postCreateHook = pPostCreateHook;
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
	
	public Collection<Pair<Skill, Integer>> startingSkills(){return Collections.unmodifiableCollection(startingSkills);}
	public Collection<Pair<Skill, Integer>> careerSkills(){return Collections.unmodifiableCollection(careerSkills);}
	public Collection<Ability> startingAbilities(){return Collections.unmodifiableCollection(startingAbilities);}
	public Collection<Ability> careerAbilities(){return Collections.unmodifiableCollection(careerAbilities);}
	public Collection<Spell> startingSpells(){return Collections.unmodifiableCollection(startingSpells);}
	public Collection<Spell> careerSpells(){return Collections.unmodifiableCollection(careerSpells);}
	public PrereqCheck prereqCheck(){return prereqCheck;}
	public PostCreateHook postCreateHook(){return postCreateHook;}
}
