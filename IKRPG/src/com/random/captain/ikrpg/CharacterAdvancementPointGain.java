package com.random.captain.ikrpg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.random.captain.ikrpg.character.Character;
import com.random.captain.ikrpg.character.CharacterAdvancementServiceActivity;

public class CharacterAdvancementPointGain extends Activity
{
	private Character mainChar;
	private int baseEXP;
	private int currentEXP;
	private TextView expTotalView;
	private TextView expDifView;
	private Button loseExp;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_character_advance_point_gain);
		
		mainChar = (Character)getIntent().getExtras().get(MainActivity.PC_EXTRA);
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
					updateEXPDisplay(currentEXP);
				}
			}
		});
		
		Button gainExp = (Button)findViewById(R.id.gainEXP);
		gainExp.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				currentEXP++;
				updateEXPDisplay(currentEXP);
			}
		});
			
		currentEXP = mainChar.exp();
		updateEXPDisplay(currentEXP);
	}
	
	private void updateEXPDisplay(int exp)
	{
		if(expTotalView == null)
		{expTotalView = (TextView)findViewById(R.id.totalEXPLabel);}
		expTotalView.setText(""+exp);
		
		if(expDifView == null)
		{expDifView = (TextView)findViewById(R.id.expGainsText);}
		
		//can't actually go negative... but why not.
		boolean positive = currentEXP >= baseEXP;
		expDifView.setText("You have "+(positive?"gained":"lost")+" "+Math.abs(currentEXP-mainChar.exp())+" exp");
		if(positive)
		{loseExp.setEnabled(true);}
		else
		{loseExp.setEnabled(false);}
	}
	
	public void getBenefits(View v)
	{
		Intent i = new Intent(this, CharacterAdvancementServiceActivity.class);
		i.putExtra(CharacterAdvancementServiceActivity.THE_CHAR, mainChar);
		i.putExtra(CharacterAdvancementServiceActivity.START_EXP, baseEXP);
		i.putExtra(CharacterAdvancementServiceActivity.END_EXP, currentEXP);
		startActivity(i);
	}
}
