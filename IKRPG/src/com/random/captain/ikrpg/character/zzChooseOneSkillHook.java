package com.random.captain.ikrpg.character;

public abstract class zzChooseOneSkillHook extends zzChooseOneHook<Skill>
{
	private Skill incrementedSkill;
	private int incrementedSkillPrevValue;
	
	@Override protected void itemSelected(int which)
	{
		incrementedSkill = getOptions().get(which);
		incrementedSkillPrevValue = myChar.getSkillBaseLevel(incrementedSkill);
		int currentLevel = incrementedSkillPrevValue + 1;
		if(currentLevel > 2){currentLevel = 2;}
		myChar.setSkillLevel(incrementedSkill, currentLevel);
	}

	@Override public boolean hasUI()
	{
		int optionCount = 0;
		for(Skill b : getOptions())
		{
			if(myChar.getSkillBaseLevel(b) < 2){optionCount++;}
		}
		
		return optionCount > 1;
	}
	
	@Override protected void doDefaultCase()
	{
		Skill unmaxedSkill = null;
		
		for(Skill s : getOptions())
		{
			if(myChar.getSkillBaseLevel(s) < 2){unmaxedSkill = s;}
		}
		
		if(unmaxedSkill != null)
		{
			incrementedSkill = unmaxedSkill;
			incrementedSkillPrevValue = myChar.getSkillBaseLevel(incrementedSkill);
			myChar.setSkillLevel(incrementedSkill, incrementedSkillPrevValue+1);
		}
	}
	
	@Override public void undoHook()
	{myChar.setSkillLevel(incrementedSkill, incrementedSkillPrevValue);}

}
