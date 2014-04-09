package com.random.captain.ikrpg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.random.captain.ikrpg.character.GameCharacter;
import com.random.captain.ikrpg.character.CharacterAdvancementServiceActivity;
import com.random.captain.ikrpg.character.SkillEnum;
import com.random.captain.ikrpg.util.BundleConstants;

public class CharacterAdvancementPointGain extends Activity
{
	private GameCharacter mainChar;
	private int baseEXP;
	private int currentEXP;
	private TextView expTotalView;
	private TextView expDifView;
	private Button loseExp;
	
	private int buildThatChar = 5;
	
	@Override
	public void onSaveInstanceState(Bundle b)
	{
		super.onSaveInstanceState(b);
		b.putInt(BundleConstants.CUR_EXP, currentEXP);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_character_advance_point_gain);
		
		mainChar = GameCharacter.fromJson(getIntent().getExtras().getString(BundleConstants.CHARACTER));
		baseEXP = mainChar.exp();
		
		//setup EXP
		loseExp = (Button)findViewById(R.id.loseEXP);
		loseExp.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				if(currentEXP > baseEXP)
				{
					currentEXP--;
					updateEXPDisplay();
				}
			}
		});
		
		Button gainExp = (Button)findViewById(R.id.gainEXP);
		gainExp.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				currentEXP++;
				updateEXPDisplay();
			}
		});
			
		currentEXP = mainChar.exp();
		if(savedInstanceState != null)
		{
			currentEXP = savedInstanceState.getInt(BundleConstants.CUR_EXP, currentEXP);
		}
		updateEXPDisplay();
	}
	
	private void updateEXPDisplay()
	{
		//can't actually go negative... but why not.
		//Maybe at some point I should actually allow this.
		loseExp.setEnabled(currentEXP > baseEXP);
	
		if(expTotalView == null)
		{expTotalView = (TextView)findViewById(R.id.totalEXPLabel);}
		expTotalView.setText(""+currentEXP);
		
		if(expDifView == null)
		{expDifView = (TextView)findViewById(R.id.expGainsText);}
		
		boolean positive = currentEXP >= baseEXP;
		expDifView.setText("You have "+(positive?"gained":"lost")+" "+Math.abs(currentEXP-mainChar.exp())+" exp");
	}
	
	public void getBenefits(View v)
	{
		Intent i = new Intent(this, CharacterAdvancementServiceActivity.class);
		i.putExtra(BundleConstants.CHARACTER, mainChar.toJson());
		i.putExtra(BundleConstants.START_EXP, baseEXP);
		i.putExtra(BundleConstants.END_EXP, currentEXP);
		startActivityForResult(i,buildThatChar);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode == buildThatChar && resultCode == RESULT_OK)
		{
			Log.i("IKRPG","Good, it thinks things went okay.");
		}
	}
}
