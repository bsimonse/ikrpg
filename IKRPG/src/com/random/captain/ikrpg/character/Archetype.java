package com.random.captain.ikrpg.character;

import android.os.Bundle;
import com.random.captain.ikrpg.character.Stat;
import java.util.Arrays;
import java.util.Collection;

public enum Archetype implements zzPrereqCheck
{
	GIFTED("Gifted",
		new zzCreateCharacterHook[] { 
			new zzCreateCharacterHook(){
				private final String PREV_BASE_ARCANE_STAT = "thisWasThePreviousArcaneStat";
				
				@Override public void startHook(zzBaseCharacter pChar, zzCreateCharacterHookDelegate pDelegate, CreateHook hook)
				{
					super.startHook(pChar, pDelegate, hook);
					if(myChar.careers.contains(Career.WARCASTER)){myChar.tradition = GiftedTradition.FOCUSER;}
					else{myChar.tradition = GiftedTradition.WILL_WEAVER;}
					
					Bundle args = getArguments();
					int value = myChar.getBaseStat(Stat.ARCANE);
					args.putInt(PREV_BASE_ARCANE_STAT, value);
					if(myChar.tradition==GiftedTradition.WILL_WEAVER){myChar.setBaseStat(Stat.ARCANE, 3);}
					else if(myChar.tradition==GiftedTradition.FOCUSER){myChar.setBaseStat(Stat.ARCANE, 2);}
				}
				@Override public void undoHook(){myChar.setBaseStat(Stat.ARCANE, getArguments().getInt(PREV_BASE_ARCANE_STAT));}
				@Override public int getPriority(){return 0;} //no choice necessary
				@Override public boolean hasUI(){return false;}
			}
		},
	
		new zzPrereqCheck(){
			@Override
			public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.race() == Race.HUMAN, null);}
		}
	),
	
	INTELLECTUAL("Intellectual",
		new zzCreateCharacterHook[] { 
			nonGiftedArcaneHook()
		},
		new zzPrereqCheck(){
			@Override
			public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.race() == Race.HUMAN, null); }
		}
	),
	
	MIGHTY("Mighty",
		new zzCreateCharacterHook[] { 
			nonGiftedArcaneHook()
		},
		new zzPrereqCheck(){
			@Override
			public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.race() == Race.HUMAN, null);}
		}
	),
	
	SKILLED("Skilled",
		new zzCreateCharacterHook[] { 
			nonGiftedArcaneHook()
		},
		new zzPrereqCheck(){
			@Override
			public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.race() == Race.HUMAN, null);}
		}
	);
	
	private Archetype(String pName, zzCreateCharacterHook[] pHooks, zzPrereqCheck pPrereq)
	{
		name = pName;
		postCreateHooks = Arrays.asList(pHooks);
		prereq = pPrereq;
	}
	
	private String name;
	private Collection<zzCreateCharacterHook> postCreateHooks;
	private zzPrereqCheck prereq;
	
	public String displayName(){return name;}
	Collection<zzCreateCharacterHook> postCreateHooks(){return postCreateHooks;}
	
	public static zzCreateCharacterHook nonGiftedArcaneHook()
	{
		return new zzCreateCharacterHook(){
				@Override public void startHook(zzBaseCharacter pChar, zzCreateCharacterHookDelegate pDelegate, CreateHook hook)
				{
					super.startHook(pChar, pDelegate, hook);
					myChar.setBaseStat(Stat.ARCANE,0);
					myChar.setMaxStat(Stat.ARCANE,0);
				}
				@Override public void undoHook(){myChar.setMaxStat(Stat.ARCANE, myChar.race.getHeroStatCap(Stat.ARCANE));}
				@Override public int getPriority(){return 0;} //no choice necessary
				@Override public boolean hasUI(){return false;}
			};
	}
	
	@Override public String toString(){return displayName();}
	@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
	{
		if(prereq == null){return new zzPrereqCheckResult(true, null);}
		return prereq.meetsPrereq(myChar);
	}
}
