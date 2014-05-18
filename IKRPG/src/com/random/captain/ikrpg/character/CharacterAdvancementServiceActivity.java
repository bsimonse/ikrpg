package com.random.captain.ikrpg.character;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.SparseArrayCompat;
import com.random.captain.ikrpg.util.BundleConstants;
import com.random.captain.ikrpg.util.FlowNavigator;
import java.util.ArrayList;
import java.util.List;

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
		for(int i = startExp; i < endExp; i++)
		{
			zzCharacterAdvancementFragment h = hooks.valueAt(i);
			if(h != null){frags.add(h);}
		}
		return frags;
	}
	
	@Override
	protected Bundle prepBundle(zzCharacterAdvancementFragment frag, int fragDex)
	{
		Bundle b = new Bundle();
		b.putString(BundleConstants.CHARACTER, buildingChar.toJson());
		
		return b;
	}
	
	@Override
	protected void hookComplete(Bundle b)
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
		hooks.setValueAt(2,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(2,2));
		hooks.setValueAt(4,new zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon(4));
		hooks.setValueAt(8,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(8,2));
		hooks.setValueAt(10,new zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon(10));
		hooks.setValueAt(18,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(18,2));
		hooks.setValueAt(21,new zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon(21));
		hooks.setValueAt(27,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(27,2));
		hooks.setValueAt(33,new zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon(33));
		hooks.setValueAt(39,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(39,2));
		hooks.setValueAt(42,new zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon(42));
		hooks.setValueAt(55,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(55,2));
		hooks.setValueAt(60,new zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon(60));
		hooks.setValueAt(70,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(70,2));
		hooks.setValueAt(75,new zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon(75));
		hooks.setValueAt(90,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(90,2));
		hooks.setValueAt(95,new zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon(95));
		hooks.setValueAt(105,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(105,2));
		hooks.setValueAt(115,new zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon(115));
		hooks.setValueAt(125,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(125,2));
		hooks.setValueAt(130,new zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon(130));
		hooks.setValueAt(145,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(145,2));
		hooks.setValueAt(150,new zzStaticCharacterAdvancementBoons.ChooseSkillSpellConnectionMilitaryBoon(150));
	}
}
