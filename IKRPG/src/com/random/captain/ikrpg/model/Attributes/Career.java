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
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.model.BaseCharacter;

public enum Career implements PrereqCheck
{
	ALCHEMIST("Alchemist",
				new Pair[] {SkillEnum.HAND_WEAPON.pair(1), SkillEnum.THROWN_WEAPON.pair(1), SkillEnum.ALCHEMY.pair(1), SkillEnum.MEDICINE.pair(1)},
				new Pair[] {SkillEnum.HAND_WEAPON.pair(2), SkillEnum.THROWN_WEAPON.pair(2), //etc...
							SkillEnum.CRAFT.pair(4), SkillEnum.RESEARCH.pair(4)},
			  	new Ability[] {AbilityEnum.GRENADIER.make(), AbilityEnum.POISON_RESISTANCE.make()},
				new Ability[] {AbilityEnum.BOMBER.make(), AbilityEnum.BREW_MASTER.make(), AbilityEnum.FAST_COOK.make(),AbilityEnum.FIELD_ALCHEMIST.make(), AbilityEnum.FIRE_IN_THE_HOLE.make(), AbilityEnum.FREE_STYLE.make(),
								AbilityEnum.GRENADIER.make(),AbilityEnum.POISON_RESISTANCE.make()},
				null, null,
				null, null),
								
	ARCANE_MECHANIK("Arcane Mechanik",
				new Pair[] {SkillEnum.CRAFT.pair("gunsmithing", 1), SkillEnum.CRAFT.pair("metalworking", 1), SkillEnum.MECHANIKAL.pair(1)},
				new Pair[] {SkillEnum.HAND_WEAPON.pair(2), SkillEnum.LIGHT_ARTILLERY.pair(2), //etc...
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
				new PrereqCheck(){
					@Override
					public PrereqCheckResult meetsPrereq(BaseCharacter myChar){
						if(myChar.archetype() == null){return new PrereqCheckResult(false, null);}
						return new PrereqCheckResult(myChar.archetype() == Archetype.GIFTED, null);
					}
			  	},
				new PostCreateHook(){
				  
				  private SkillEnum incrementedSkill;
				  private int incrementedSkillPrevValue;
				  
				  @Override public Fragment doPostCreateHook(final BaseCharacter myChar, final PostCreateHookDelegate delegate)
				  {	
						//determine if choice is needed
						boolean handWeaponMaxed = myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.HAND_WEAPON)) == 2;
						boolean rifleMaxed = myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.RIFLE)) == 2;
						
						if(!rifleMaxed && !handWeaponMaxed)
						{
							return new Fragment(){
								private ListView raceList;

							  @Override
							  public View onCreateView(LayoutInflater inflater, ViewGroup pRoot, Bundle bund)
							  {
								  LinearLayout root = (LinearLayout)inflater.inflate(R.layout.frag_choice_list, pRoot, false);
								  
								  ListView choiceList = (ListView)root.findViewById(R.id.listChoiceList);
								  final SkillEnum[] choices = new SkillEnum[]{SkillEnum.HAND_WEAPON, SkillEnum.RIFLE};
								  choiceList.setAdapter(new ArrayAdapter<SkillEnum>(inflater.getContext(), android.R.layout.simple_list_item_1, choices));
								  choiceList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
									  @Override
									  public void onItemClick(AdapterView<?> parent, View view, int which, long id)
									  {
										  incrementedSkill = choices[which];
										  incrementedSkillPrevValue = myChar.skillsBundle().getSkillLevel(new Skill(incrementedSkill));
										  int currentLevel = incrementedSkillPrevValue + 1;
										  if(currentLevel > 2){currentLevel = 2;}
										  myChar.skillsBundle().setSkillLevel(new Skill(incrementedSkill), currentLevel);
										  delegate.hookComplete();
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
								incrementedSkill = SkillEnum.HAND_WEAPON;
								incrementedSkillPrevValue = myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.HAND_WEAPON));
								myChar.skillsBundle().setSkillLevel(new Skill(SkillEnum.HAND_WEAPON), incrementedSkillPrevValue+1);
							}
							else if(!rifleMaxed && handWeaponMaxed)
							{
								incrementedSkill = SkillEnum.RIFLE;
								incrementedSkillPrevValue = myChar.skillsBundle().getSkillLevel(new Skill(SkillEnum.RIFLE));
								myChar.skillsBundle().setSkillLevel(new Skill(SkillEnum.RIFLE), incrementedSkillPrevValue+1);
							}
							
							//no choice needed
							return null;
						}
					  }
					
					@Override public void undoPostCreateHook(BaseCharacter myChar){myChar.skillsBundle().setSkillLevel(new Skill(incrementedSkill), incrementedSkillPrevValue);}
					@Override public int getPriority(){return 50;}
			  }),
	PIRATE("Pirate",null,null,null,null,null,null,null,null),
	WARCASTER("Warcaster",null,null,null,null,null,null,null,null);
			  
			  
			  
	
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
		
		//duplicates not allowed
		if(careers != null && careers.contains(this)){return new PrereqCheckResult(false, null);}
		
		//no prereq means allowed
		if(prereqCheck == null){return new PrereqCheckResult(true, null);}
		else{return prereqCheck.meetsPrereq(myChar);}
	}
}
