package com.random.captain.ikrpg.character;

import android.os.Bundle;
import com.random.captain.ikrpg.character.Stat;

public enum Archetype implements zzPrereqCheck
{
	GIFTED("Gifted",
		new zzCreateCharacterHook(){
			private final String PREV_BASE_ARCANE_STAT = "thisWasThePreviousArcaneStat";
			
			@Override public void startHook(zzBaseCharacter pChar, zzCreateCharacterHookDelegate pDelegate)
			{
				super.startHook(pChar, pDelegate);
				if(myChar.careers.contains(Career.WARCASTER)){myChar.tradition = GiftedTradition.FOCUSER;}
				else{myChar.tradition = GiftedTradition.WILL_WEAVER;}
				
				Bundle args = getArguments();
				int value = myChar.statsBundle.getBaseStat(Stat.ARCANE);
				args.putInt(PREV_BASE_ARCANE_STAT, value);
				if(myChar.tradition==GiftedTradition.WILL_WEAVER){myChar.statsBundle.setBaseStat(Stat.ARCANE, 3);}
				else if(myChar.tradition==GiftedTradition.FOCUSER){myChar.statsBundle.setBaseStat(Stat.ARCANE, 2);}
			}
			@Override public void undoHook(){myChar.statsBundle.setBaseStat(Stat.ARCANE, getArguments().getInt(PREV_BASE_ARCANE_STAT));}
			@Override public int getPriority(){return 0;} //no choice necessary
			@Override public boolean hasUI(){return false;}
		},
	
		new zzPrereqCheck(){
			@Override
			public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.race() == Race.HUMAN, null);}
		}
	),
	
	INTELLECTUAL("Intellectual",
		null,
		new zzPrereqCheck(){
			@Override
			public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.race() == Race.HUMAN, null); }
		}
	),
	
	MIGHTY("Mighty",
		null,
		new zzPrereqCheck(){
			@Override
			public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.race() == Race.HUMAN, null);}
		}
	),
	
	SKILLED("Skilled",
		null,
		new zzPrereqCheck(){
			@Override
			public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.race() == Race.HUMAN, null);}
		}
	);
	
	private Archetype(String pName, zzCreateCharacterHook pHook, zzPrereqCheck pPrereq)
	{
		name = pName;
		postCreateHook = pHook;
		prereq = pPrereq;
	}
	
	private String name;
	private zzCreateCharacterHook postCreateHook;
	private zzPrereqCheck prereq;
	
	public String displayName(){return name;}
	zzCreateCharacterHook postCreateHook(){return postCreateHook;}
	
	@Override public String toString(){return displayName();}
	@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
	{
		if(prereq == null){return new zzPrereqCheckResult(true, null);}
		return prereq.meetsPrereq(myChar);
	}
}
