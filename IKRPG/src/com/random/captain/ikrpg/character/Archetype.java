package com.random.captain.ikrpg.character;

import java.util.Arrays;
import java.util.Collection;

import android.content.Context;
import android.os.Bundle;

import com.random.captain.ikrpg.IKRPGApp;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.util.BundleConstants;

public enum Archetype implements zzPrereqCheck
{
	GIFTED(R.string.gifted_name, R.string.gifted_longDesc, R.string.gifted_shortDesc, R.string.gifted_page,
		new zzCreateCharacterHook[] { 
			new GiftedArcaneHook()
		},
	
		null
	),

	INTELLECTUAL(R.string.intellectual_name, R.string.intellectual_longDesc, R.string.intellectual_shortDesc, R.string.intellectual_page,
		new zzCreateCharacterHook[] { 
			new NonGiftedArcaneHook()
		},
		null
	),
	
	MIGHTY(R.string.mighty_name, R.string.mighty_longDesc, R.string.mighty_shortDesc, R.string.mighty_page,
		new zzCreateCharacterHook[] { 
			new NonGiftedArcaneHook()
		},
		null
	),
	
	SKILLED(R.string.skilled_name, R.string.skilled_longDesc, R.string.skilled_shortDesc, R.string.skilled_page,
		new zzCreateCharacterHook[] { 
			new NonGiftedArcaneHook()
		},
		null
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
		
		@Override
		public void startFlowFragment(FlowFragmentDelegate pDelegate)
		{
			super.startFlowFragment(pDelegate);
			
			if(myChar.careers.contains(Career.WARCASTER)){myChar.tradition = GiftedTradition.FOCUSER;}
			else{myChar.tradition = GiftedTradition.WILL_WEAVER;}

			if(myChar.tradition==GiftedTradition.WILL_WEAVER){myChar.setBaseStat(Stat.ARCANE, 3);}
			else if(myChar.tradition==GiftedTradition.FOCUSER){myChar.setBaseStat(Stat.ARCANE, 2);}
			
			Bundle b = new Bundle();
			b.putString(BundleConstants.CHARACTER, myChar.toJson());
			pDelegate.hookComplete(b);
		}
		
		@Override public int getPriority(){return 0;} //no choice necessary
	}
	
	public static class NonGiftedArcaneHook extends zzCreateCharacterHook
	{
		@Override
		public void startFlowFragment(FlowFragmentDelegate pDelegate)
		{
			super.startFlowFragment(pDelegate);
			
			myChar.setBaseStat(Stat.ARCANE,0);
			myChar.setMaxStat(Stat.ARCANE,0);
			
			Bundle b = new Bundle();
			b.putString(BundleConstants.CHARACTER, myChar.toJson());
			pDelegate.hookComplete(b);
		}
		
		@Override public int getPriority(){return 0;} //no choice necessary
	}
	
	@Override public String toString(){return displayName();}
	@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar)
	{
		boolean inRace = myChar.race().archetypes().contains(this);
		if(prereq == null){return new zzPrereqCheckResult(inRace, null);}
		zzPrereqCheckResult result = prereq.meetsPrereq(myChar);
		result.isAllowed = result.isAllowed && inRace;
		return result;
	}
}
