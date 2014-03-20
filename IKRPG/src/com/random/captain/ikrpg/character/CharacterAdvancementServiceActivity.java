package com.random.captain.ikrpg.character;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;

import com.random.captain.ikrpg.util.BundleConstants;
import com.random.captain.ikrpg.util.FlowFragment;
import com.random.captain.ikrpg.util.FlowNavigator;

public class CharacterAdvancementServiceActivity extends FlowNavigator
{
	//character 'in progress' of being changed during flow
	private zzBaseCharacter buildingChar;
	
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
		if(savedInstanceState != null)
		{buildingChar = zzBaseCharacter.fromJson(savedInstanceState.getString(BundleConstants.CHARACTER));}
		else
		{buildingChar = zzBaseCharacter.fromJson(getIntent().getExtras().getString(BundleConstants.CHARACTER));}
	}

	@Override
	public ArrayList<FlowFragment> generateFrags()
	{
		//Alright, let the fun begin!
		//int startXp, int endXp;
		ArrayList<FlowFragment> f = new ArrayList<FlowFragment>(15);
		f.add(new zzStaticCharacterAdvancementBoons.ChooseOccupationalSkillsFragment());
		return f;
	}
	
	@Override
	protected Bundle prepBundle()
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
		Intent i = new Intent();
		i.putExtra(BundleConstants.CHARACTER, buildingChar.toJson());
		setResult(RESULT_OK, i);
	}
}
