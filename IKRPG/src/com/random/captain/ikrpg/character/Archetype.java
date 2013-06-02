package com.random.captain.ikrpg.character;

import android.os.Bundle;
import com.random.captain.ikrpg.character.Stat;

public enum Archetype implements PrereqCheck
{
	GIFTED("Gifted",
		new PostCreateHook(){
			private final String PREV_BASE_ARCANE_STAT = "thisWasThePreviousArcaneStat";
			
			@Override public void startPostCreateHook(BaseCharacter pChar, PostCreateHookDelegate pDelegate)
			{
				super.startPostCreateHook(pChar, pDelegate);
				if(myChar.careers.contains(Career.WARCASTER)){myChar.tradition = GiftedTradition.FOCUSER;}
				else{myChar.tradition = GiftedTradition.WILL_WEAVER;}
				
				Bundle args = getArguments();
				int value = myChar.statsBundle.getBaseStat(Stat.ARCANE);
				args.putInt(PREV_BASE_ARCANE_STAT, value);
				if(myChar.tradition==GiftedTradition.WILL_WEAVER){myChar.statsBundle.setBaseStat(Stat.ARCANE, 3);}
				else if(myChar.tradition==GiftedTradition.FOCUSER){myChar.statsBundle.setBaseStat(Stat.ARCANE, 2);}
			}
			@Override public void undoPostCreateHook(){myChar.statsBundle.setBaseStat(Stat.ARCANE, getArguments().getInt(PREV_BASE_ARCANE_STAT));}
			@Override public int getPriority(){return 0;} //no choice necessary
			@Override public boolean hasUI(){return false;}
		},
	
		new PrereqCheck(){
			@Override
			public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.race() == Race.HUMAN, null);}
		}
	),
	
	INTELLECTUAL("Intellectual",
		null,
		new PrereqCheck(){
			@Override
			public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.race() == Race.HUMAN, null); }
		}
	),
	
	MIGHTY("Mighty",
		null,
		new PrereqCheck(){
			@Override
			public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.race() == Race.HUMAN, null);}
		}
	),
	
	SKILLED("Skilled",
		null,
		new PrereqCheck(){
			@Override
			public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.race() == Race.HUMAN, null);}
		}
	);
	
	private Archetype(String pName, PostCreateHook pHook, PrereqCheck pPrereq)
	{
		name = pName;
		postCreateHook = pHook;
		prereq = pPrereq;
	}
	
	private String name;
	private PostCreateHook postCreateHook;
	private PrereqCheck prereq;
	
	public String displayName(){return name;}
	PostCreateHook postCreateHook(){return postCreateHook;}
	
	@Override public String toString(){return displayName();}
	@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
	{
		if(prereq == null){return new PrereqCheckResult(true, null);}
		return prereq.meetsPrereq(myChar);
	}
}
