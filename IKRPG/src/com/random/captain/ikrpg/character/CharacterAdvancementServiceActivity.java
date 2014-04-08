package com.random.captain.ikrpg.character;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import com.random.captain.ikrpg.util.BundleConstants;
import com.random.captain.ikrpg.util.FlowNavigator;
import java.util.ArrayList;
import java.util.List;

public class CharacterAdvancementServiceActivity extends FlowNavigator<zzAdvanceCharacterHook>
{
	//character 'in progress' of being changed during flow
	private zzBaseCharacter buildingChar;
	private int startExp;
	private int endExp;
	private SparseArrayCompat<zzAdvanceCharacterHook> hooks;
	
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
	public List<zzAdvanceCharacterHook> generateFrags()
	{
		//hooks... frags... they're all the same.
		if(hooks == null){generateAllHooks();}
		
		List<zzAdvanceCharacterHook> frags = new ArrayList<zzAdvanceCharacterHook>(30);
		for(int i = startExp; i < endExp; i++)
		{
			zzAdvanceCharacterHook h = hooks.get(i);
			if(h != null){frags.add(h);}
		}
		Log.i("IKRPG","All of "+frags.size());
		return frags;
	}
	
	@Override
	protected Bundle prepBundle(zzAdvanceCharacterHook frag, int fragDex)
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
		hooks = new SparseArrayCompat<zzAdvanceCharacterHook>(200);
		hooks.setValueAt(2,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(2,2));
		hooks.setValueAt(8,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(8,2));
		hooks.setValueAt(18,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(18,2));
		hooks.setValueAt(27,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(27,2));
		hooks.setValueAt(39,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(39,2));
		hooks.setValueAt(55,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(55,2));
		hooks.setValueAt(70,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(70,2));
		hooks.setValueAt(90,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(90,2));
		hooks.setValueAt(105,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(105,2));
		hooks.setValueAt(125,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(125,2));
		hooks.setValueAt(145,new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment(145,2));
	}
}
