package com.random.captain.ikrpg.character;

public abstract class zzChooseOneSkillHook extends zzStaticCreateCharacterHooks.ChooseAnAdvancementFragment<Skill>
{	
	@Override protected String getTitle(){return "Choose a skill to boost";}
	
	@Override protected void onChosen(Skill chosen)
	{
		int incrementedSkillPrevValue = myChar.getSkillBaseLevel(chosen);
		int currentLevel = incrementedSkillPrevValue + 1;
		if(currentLevel > 2){currentLevel = 2;}
		myChar.setSkillLevel(chosen, currentLevel);
	}
}
