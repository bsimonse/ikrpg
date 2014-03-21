package com.random.captain.ikrpg.character;

public abstract class zzChooseOneSkillHook extends zzChooseOneHook<Skill>
{
	private Skill incrementedSkill;
	private int incrementedSkillPrevValue;
	
	@Override protected String getTitle(){return "Choose a skill to boost";}
	
	@Override protected void itemSelected(int which)
	{
		incrementedSkill = getOptions().get(which);
		incrementedSkillPrevValue = myChar.getSkillBaseLevel(incrementedSkill);
		int currentLevel = incrementedSkillPrevValue + 1;
		if(currentLevel > 2){currentLevel = 2;}
		myChar.setSkillLevel(incrementedSkill, currentLevel);
	}
}
