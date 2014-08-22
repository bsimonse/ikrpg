package com.random.captain.ikrpg.character;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.SparseArrayCompat;

import com.random.captain.ikrpg.util.BundleConstants;
import com.random.captain.ikrpg.util.FlowFragment;
import com.random.captain.ikrpg.util.FlowNavigator;

public class CharacterAdvancementServiceActivity extends FlowNavigator<zzCharacterAdvancementFragment>
{
	//character 'in progress' of being changed during flow
	private zzBaseCharacter buildingChar;
	private int startExp;
	private int endExp;
	private SparseArrayCompat<zzCharacterAdvancementFragment> hooks;
	
	@Override
	public void onSaveInstanceState(Bundle b)
	{
		super.onSaveInstanceState(b);
		b.putString(BundleConstants.CHARACTER,buildingChar.toJson());
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		
		if(savedInstanceState != null)
		{buildingChar = zzBaseCharacter.fromJson(savedInstanceState.getString(BundleConstants.CHARACTER));}
		else
		{buildingChar = zzBaseCharacter.fromJson(extras.getString(BundleConstants.CHARACTER));}
		
		//these don't change, so don't store them.
		startExp = extras.getInt(BundleConstants.START_EXP, 0);
		endExp = extras.getInt(BundleConstants.END_EXP, 0);
	}

	@Override
	public List<zzCharacterAdvancementFragment> generateFrags()
	{
		//hooks... frags... they're all the same.
		if(hooks == null){generateAllHooks();}
		
		List<zzCharacterAdvancementFragment> frags = new ArrayList<zzCharacterAdvancementFragment>(30);
		for(int i = startExp+1; i <= endExp; i++)
		{
			zzCharacterAdvancementFragment h = hooks.valueAt(i);
			if(h != null){frags.add(h);}
		}
		return frags;
	}
	
	@Override
	protected Bundle prepBundle(FlowFragment frag, int fragDex)
	{
		Bundle b = new Bundle();
		b.putString(BundleConstants.CHARACTER, buildingChar.toJson());
		return b;
	}
	
	@Override
	protected void flowHookComplete(Bundle b)
	{
		buildingChar = zzBaseCharacter.fromJson(b.getString(BundleConstants.CHARACTER));
	}
	
	@Override
	protected void setResult()
	{
		buildingChar.exp = endExp;
		Intent i = new Intent();
		i.putExtra(BundleConstants.CHARACTER, buildingChar.toJson());
		setResult(RESULT_OK, i);
	}
	
	//all the possible hooks!
	private void generateAllHooks()
	{
		hooks = new SparseArrayCompat<zzCharacterAdvancementFragment>(200);
		hooks.setValueAt(2,zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment.make(2,2));
		hooks.setValueAt(4,zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon.make(4));
		hooks.setValueAt(6,zzStaticCharacterAdvancementBoons.ChooseStatPointsBoon.make(6,1));
		hooks.setValueAt(8,zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment.make(8,2));
		hooks.setValueAt(10,zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon.make(10));
		hooks.setValueAt(12,zzStaticCharacterAdvancementBoons.ChooseArchetypeBenefitFragment.make(12));
		hooks.setValueAt(15,zzStaticCharacterAdvancementBoons.ChooseStatPointsBoon.make(15,1));
		hooks.setValueAt(18,zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment.make(18,2));
		hooks.setValueAt(21,zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon.make(21));
		hooks.setValueAt(24,zzStaticCharacterAdvancementBoons.ChooseStatPointsBoon.make(24,1));
		hooks.setValueAt(27,zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment.make(27,2));
		hooks.setValueAt(30,zzStaticCharacterAdvancementBoons.ChooseArchetypeOrCareerBoon.make(30));
		hooks.setValueAt(33,zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon.make(33));
		hooks.setValueAt(36,zzStaticCharacterAdvancementBoons.ChooseStatPointsBoon.make(36,1));
		hooks.setValueAt(39,zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment.make(39,2));
		hooks.setValueAt(42,zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon.make(42));
		hooks.setValueAt(45,zzStaticCharacterAdvancementBoons.ChooseStatPointsBoon.make(45,1));
		hooks.setValueAt(50,zzStaticCharacterAdvancementBoons.ChooseArchetypeBenefitFragment.make(50));
		hooks.setValueAt(55,zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment.make(55,2));
		hooks.setValueAt(60,zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon.make(60));
		hooks.setValueAt(65,zzStaticCharacterAdvancementBoons.ChooseStatPointsBoon.make(65,1));
		hooks.setValueAt(70,zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment.make(70,2));
		hooks.setValueAt(75,zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon.make(75));
		hooks.setValueAt(80,zzStaticCharacterAdvancementBoons.ChooseArchetypeOrCareerBoon.make(80));
		hooks.setValueAt(85,zzStaticCharacterAdvancementBoons.ChooseStatPointsBoon.make(85,1));
		hooks.setValueAt(90,zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment.make(90,2));
		hooks.setValueAt(95,zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon.make(95));
		hooks.setValueAt(100,zzStaticCharacterAdvancementBoons.ChooseStatPointsBoon.make(100,1));
		hooks.setValueAt(105,zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment.make(105,2));
		hooks.setValueAt(110,zzStaticCharacterAdvancementBoons.ChooseArchetypeOrCareerBoon.make(110));
		hooks.setValueAt(115,zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon.make(115));
		hooks.setValueAt(120,zzStaticCharacterAdvancementBoons.ChooseStatPointsBoon.make(120,1));
		hooks.setValueAt(125,zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment.make(125,2));
		hooks.setValueAt(130,zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon.make(130));
		hooks.setValueAt(135,zzStaticCharacterAdvancementBoons.ChooseStatPointsBoon.make(135,1));
		hooks.setValueAt(140,zzStaticCharacterAdvancementBoons.ChooseArchetypeBenefitFragment.make(140));
		hooks.setValueAt(145,zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment.make(145,2));
		hooks.setValueAt(150,zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon.make(150));
	}
}
