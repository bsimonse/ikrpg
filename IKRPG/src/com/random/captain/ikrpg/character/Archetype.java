package com.random.captain.ikrpg.character;

import android.content.Context;
import android.os.Bundle;
import com.random.captain.ikrpg.IKRPGApp;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.character.Stat;
import java.util.Arrays;
import java.util.Collection;

public enum Archetype implements zzPrereqCheck
{
	GIFTED(R.string.gifted_name, R.string.gifted_longDesc, R.string.gifted_shortDesc, R.string.gifted_page,
		new zzCreateCharacterHook[] { 
			new GiftedArcaneHook()
		},
	
		new zzPrereqCheck(){
			@Override
			public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.race() == Race.HUMAN, null);}
		}
	),
	
	INTELLECTUAL(R.string.intellectual_name, R.string.intellectual_longDesc, R.string.intellectual_shortDesc, R.string.intellectual_page,
		new zzCreateCharacterHook[] { 
			new NonGiftedArcaneHook()
		},
		new zzPrereqCheck(){
			@Override
			public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.race() == Race.HUMAN, null); }
		}
	),
	
	MIGHTY(R.string.mighty_name, R.string.mighty_longDesc, R.string.mighty_shortDesc, R.string.mighty_page,
		new zzCreateCharacterHook[] { 
			new NonGiftedArcaneHook()
		},
		new zzPrereqCheck(){
			@Override
			public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.race() == Race.HUMAN, null);}
		}
	),
	
	SKILLED(R.string.skilled_name, R.string.skilled_longDesc, R.string.skilled_shortDesc, R.string.skilled_page,
		new zzCreateCharacterHook[] { 
			new NonGiftedArcaneHook()
		},
		new zzPrereqCheck(){
			@Override
			public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
			{return new zzPrereqCheckResult(myChar.race() == Race.HUMAN, null);}
		}
	);
	
	private Archetype(int pName, int pLongDesc, int pShortDesc, int pPage, zzCreateCharacterHook[] pHooks, zzPrereqCheck pPrereq)
	{
		Context c = IKRPGApp.getContext();
		name = c.getString(pName);
		longDesc = c.getString(pLongDesc);
		shortDesc = c.getString(pShortDesc);
		page = c.getString(pPage);
		
		postCreateHooks = Arrays.asList(pHooks);
		prereq = pPrereq;
	}
	
	private String name;
	private String longDesc;
	private String shortDesc;
	private String page;
	
	private Collection<zzCreateCharacterHook> postCreateHooks;
	private zzPrereqCheck prereq;
	
	public String displayName(){return name;}
	public String longDescription(){return longDesc;}
	public String shortDescription(){return shortDesc;}
	public String page(){return page;}
	
	Collection<zzCreateCharacterHook> postCreateHooks(){return postCreateHooks;}
	
	public static class GiftedArcaneHook extends zzCreateCharacterHook {
		private final String PREV_BASE_ARCANE_STAT = "thisWasThePreviousArcaneStat";
				
		@Override public void doDefaultCase()
		{
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
	
	public static class NonGiftedArcaneHook extends zzCreateCharacterHook
	{
		@Override public void doDefaultCase()
		{
			myChar.setBaseStat(Stat.ARCANE,0);
			myChar.setMaxStat(Stat.ARCANE,0);
		}
		@Override public void undoHook()
		{
			myChar.setMaxStat(Stat.ARCANE, myChar.race.getHeroStatCap(Stat.ARCANE));
		}
		@Override public int getPriority(){return 0;} //no choice necessary
		@Override public boolean hasUI(){return false;}
	}
	
	@Override public String toString(){return displayName();}
	@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
	{
		if(prereq == null){return new zzPrereqCheckResult(true, null);}
		return prereq.meetsPrereq(myChar);
	}
}
