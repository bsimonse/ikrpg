package com.random.captain.ikrpg.character;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.random.captain.ikrpg.R;

public class zzChooseAdvancementListItemViewHolder
{
	public TextView textLabel;
	public Button incrementButton;
	public Button decrementButton;
	public TextView valueLabel;
	
	public zzChooseAdvancementListItemViewHolder(View root)
	{
		textLabel = (TextView)root.findViewById(R.id.chooseAdvancementPointsStatNameLabel);
		incrementButton = (Button)root.findViewById(R.id.chooseAdvancementPointsIncrementButton);
		decrementButton = (Button)root.findViewById(R.id.chooseAdvancementPointsDecrementButton);
		valueLabel = (TextView)root.findViewById(R.id.chooseAdvancementPointsStatValueLabel);
	}
}
