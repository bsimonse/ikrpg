package com.random.captain.ikrpg.model.Attributes;

import com.random.captain.ikrpg.model.Creators.*;

import android.support.v4.app.Fragment;
import com.random.captain.ikrpg.model.Attributes.Race;
import com.random.captain.ikrpg.model.Attributes.Stat;
import com.random.captain.ikrpg.model.BaseCharacter;

public enum Archetype implements PrereqCheck
{
	GIFTED_WILL_WEAVER("Gifted (Will Weaver)",
		new PostCreateHook(){
			@Override
			public Fragment doPostCreateHook(BaseCharacter myChar, PostCreateHookDelegate delegate, int which)
			{myChar.statsBundle().setBaseStat(Stat.ARCANE, 3);return null;}
		},
	
		new PrereqCheck(){
			@Override
			public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
			{return new PrereqCheckResult(myChar.race() == Race.HUMAN, null);}
		}
	),
	
	GIFTED_FOCUSER("Gifted (Focuser)",
		new PostCreateHook(){
			@Override
			public Fragment doPostCreateHook(BaseCharacter myChar, PostCreateHookDelegate delegate, int which)
			{myChar.statsBundle().setBaseStat(Stat.ARCANE, 2);return null;}
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
	
	@Override
	public String toString(){return displayName();}
	
	@Override
	public PrereqCheckResult meetsPrereq(BaseCharacter myChar)
	{
		if(prereq == null){return new PrereqCheckResult(true, null);}
		return prereq.meetsPrereq(myChar);
	}
}
