package com.random.captain.ikrpg.model.Attributes;

import android.widget.*;
import com.random.captain.ikrpg.model.Creators.*;
import java.util.*;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.model.BaseCharacter;

public enum Career implements PrereqCheck
{
	//This might be better as JSON... eh.
	//But Laaaaaaaambda
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
			  new Pair[] {Pair.create(Skill.CRAFT, 1), Pair.create(Skill.MECHANIKAL, 1)},
			  new Pair[] {Pair.create(Skill.HAND_WEAPON, 2), Pair.create(Skill.LIGHT_ARTILLERY, 2), Pair.create(Skill.RIFLE, 2), Pair.create(Skill.COMMAND, 1),
				  Pair.create(Skill.CRAFT, 4), Pair.create(Skill.CRYPTOGRAPHY, 3), Pair.create(Skill.MECHANIKAL,4), Pair.create(Skill.NEGOTIATION, 2),
				  Pair.create(Skill.RESEARCH, 3)},
			  new Ability[] {Ability.INSCRIBE_FORMULAE},
			  new Ability[] {Ability.JACK_MARSHALL, Ability.ACE_COMMANDER, Ability.ARCANE_ENGINEER, Ability.DRIVE_ASSUALT, Ability.DRIVE_PRONTO,
			  		Ability.INSCRIBE_FORMULAE, Ability.RESOURCEFUL, Ability.STEAMO},
			  new Spell[] {Spell.ARCANTRIK_BOLT, Spell.POLARITY_SHIELD},
			  new Spell[] {Spell.JACKHAMMER, Spell.JUMP_START, Spell.LOCOMOTION, Spell.POWER_BOOSTER, Spell.PROTECTION_FROM_ELECTRICITY, Spell.RETURN_FIRE,
			  				Spell.SHORT_OUT, Spell.ARCANTRIK_BOLT, Spell.ELECTRIFY, Spell.FORTIFY, Spell.POLARITY_SHIELD, Spell.POSITIVE_CHARGE,
							Spell.REDLINE, Spell.TEMPER_METAL, Spell.BROADSIDE, Spell.ELECTRICAL_BLAST, Spell.FAIL_SAFE, Spell.FORCE_FIELD,
							Spell.FULL_THROTTLE, Spell.GRIND, Spell.GUIDED_FIRE, Spell.IRON_AGGRESSION, Spell.SUPERIORITY, Spell.BLACK_OUT,
							Spell.TIDE_OF_STEEL, Spell.VOLTAIC_LOCK},
			  new PrereqCheck(){
				  @Override
				  public PrereqCheckResult meetsPrereq(BaseCharacter myChar){
					if(myChar.archetype() == null){return new PrereqCheckResult(false, null);}
					return new PrereqCheckResult(myChar.archetype() == Archetype.GIFTED, null);
				  }
			  },
			  new PostCreateHook(){
				  @Override
					public Fragment doPostCreateHook(final BaseCharacter myChar, final PostCreateHookDelegate delegate, final int whichHook){
						
						//determine if choice is needed
						boolean handWeaponMaxed = myChar.skillsBundle().getSkillLevel(Skill.HAND_WEAPON) == 2;
						boolean rifleMaxed = myChar.skillsBundle().getSkillLevel(Skill.RIFLE) == 2;
						
						if(!rifleMaxed && !handWeaponMaxed)
						{
							return new Fragment(){
								private ListView raceList;

							  @Override
							  public View onCreateView(LayoutInflater inflater, ViewGroup pRoot, Bundle bund)
							  {
								  LinearLayout root = (LinearLayout)inflater.inflate(R.layout.frag_choice_list, pRoot, false);
								  
								  ListView choiceList = (ListView)root.findViewById(R.id.listChoiceList);
								  final Skill[] choices = new Skill[]{Skill.HAND_WEAPON, Skill.RIFLE};
								  choiceList.setAdapter(new ArrayAdapter<Skill>(inflater.getContext(), android.R.layout.simple_list_item_1, choices));
								  choiceList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
									  @Override
									  public void onItemClick(AdapterView<?> parent, View view, int which, long id)
									  {
										  Skill choice = choices[which];
										  int currentLevel = myChar.skillsBundle().getSkillLevel(choice);
										  currentLevel += 1;
										  if(currentLevel > 2){currentLevel = 2;}
										  myChar.skillsBundle().setSkillLevel(choice, currentLevel);
										  delegate.hookComplete(whichHook);
									  }
								  });
								  
								  TextView tv = (TextView)root.findViewById(R.id.listChoiceTitle);
								  tv.setText("Choose a military skill to boost");
								  
								  return root;
							  }
					  		};
						}
						else
						{
							//auto-bump the appropriate skill
							if(rifleMaxed && !handWeaponMaxed)
							{
								int handWeaponLevel = myChar.skillsBundle().getSkillLevel(Skill.HAND_WEAPON);
								myChar.skillsBundle().setSkillLevel(Skill.HAND_WEAPON, handWeaponLevel+1);
							}
							else if(!rifleMaxed && handWeaponMaxed)
							{
								int handWeaponLevel = myChar.skillsBundle().getSkillLevel(Skill.RIFLE);
								myChar.skillsBundle().setSkillLevel(Skill.RIFLE, handWeaponLevel+1);
							}
							
							//no choice needed
							return null;
						}
					  }
					
					@Override public int getPriority(){return 50;}
			  }),
			  
	WARCASTER(null,null,null,null,null,null,null,null,null);
			  
			  
			  
	
	//Done!
	private Career(String pName,
					Pair<Skill, Integer>[] pStartSkills, Pair<Skill, Integer>[] pSkills,
				    Ability[] pStartAbilities, Ability[] pAbilities,
					Spell[] pStartSpells, Spell[] pSpells,
					PrereqCheck pPrereqCheck, PostCreateHook pPostCreateHook)
	{
		name = pName;
		startingSkills = pStartSkills != null ? Arrays.asList(pStartSkills) : new ArrayList<Pair<Skill, Integer>>(10);
		careerSkills = pSkills != null ? Arrays.asList(pSkills) : new ArrayList<Pair<Skill, Integer>>(10);
		startingAbilities = pStartAbilities != null ? Arrays.asList(pStartAbilities) : new ArrayList<Ability>(10);
		careerAbilities = pAbilities != null ? Arrays.asList(pAbilities) : new ArrayList<Ability>(10);
		startingSpells = pStartSpells != null ? Arrays.asList(pStartSpells) : new ArrayList<Spell>(10);
		careerSpells = pSpells != null ? Arrays.asList(pSpells) : new ArrayList<Spell>(10);
		prereqCheck = pPrereqCheck;
		postCreateHook = pPostCreateHook;
	}
	
	@Override
	public String toString()
	{
		return displayName();
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
	
	public String displayName(){return name;}
	public Collection<Pair<Skill, Integer>> startingSkills(){return Collections.unmodifiableCollection(startingSkills);}
	public Collection<Pair<Skill, Integer>> careerSkills(){return Collections.unmodifiableCollection(careerSkills);}
	public Collection<Ability> startingAbilities(){return Collections.unmodifiableCollection(startingAbilities);}
	public Collection<Ability> careerAbilities(){return Collections.unmodifiableCollection(careerAbilities);}
	public Collection<Spell> startingSpells(){return Collections.unmodifiableCollection(startingSpells);}
	public Collection<Spell> careerSpells(){return Collections.unmodifiableCollection(careerSpells);}
	public PostCreateHook postCreateHook(){return postCreateHook;}
	
	@Override
	public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
	{
		Set<Career> careers = myChar.careers();
		if(careers != null)
		{for(Career c:careers){if(c!=null && c.ordinal() == this.ordinal()){return new PrereqCheckResult(false, null);}}}
		
		if(prereqCheck == null){return new PrereqCheckResult(true, null);}
		else{return prereqCheck.meetsPrereq(myChar);}
	}
}
