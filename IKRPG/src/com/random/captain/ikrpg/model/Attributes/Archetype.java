package com.random.captain.ikrpg.model.Attributes;

import com.random.captain.ikrpg.model.Creators.*;

import android.support.v4.app.Fragment;
import com.random.captain.ikrpg.model.Attributes.Race;
import com.random.captain.ikrpg.model.Attributes.Stat;
import com.random.captain.ikrpg.model.BaseCharacter;

public enum Archetype implements PrereqCheck
{
	GIFTED("Gifted",
		new PostCreateHook(){
			private int prevBaseArcaneStat;
			@Override
			public Fragment doPostCreateHook(BaseCharacter myChar, PostCreateHookDelegate delegate, int which)
			{
				prevBaseArcaneStat = myChar.statsBundle().getBaseStat(Stat.ARCANE);
				if(myChar.tradition()==GiftedTradition.WILL_WEAVER){myChar.statsBundle().setBaseStat(Stat.ARCANE, 3);}
				else if(myChar.tradition()==GiftedTradition.FOCUSER){myChar.statsBundle().setBaseStat(Stat.ARCANE, 2);}
				
				return null;
			}
			@Override public void undoPostCreateHook(BaseCharacter myChar){myChar.statsBundle().setBaseStat(Stat.ARCANE, prevBaseArcaneStat);}
			@Override public int getPriority(){return 0;} //no choice necessary
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
	public PostCreateHook postCreateHook(){return postCreateHook;}
	
	@Override public String toString(){return displayName();}
	@Override public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
	{
		if(prereq == null){return new PrereqCheckResult(true, null);}
		return prereq.meetsPrereq(myChar);
	}
}
