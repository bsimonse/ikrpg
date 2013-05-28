package com.random.captain.ikrpg.fragments.newcharacter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.model.Attributes.Stat;
import com.random.captain.ikrpg.model.BaseCharacter;
import java.util.HashSet;
import java.util.Set;

public class ChooseAdvancementPointsAdapter extends BaseAdapter
{
	private BaseCharacter character;
	private Set<Stat> eligibleStats;
	private Stat[] eStatsArray;
	
	public ChooseAdvancementPointsAdapter(BaseCharacter pChar)
	{
		character = pChar;

		//remember all stats that can be increased
		eligibleStats = new HashSet<Stat>(10);
		for(Stat stat : Stat.values()){if(pChar.getBaseStat(stat) < pChar.getMaxStat(stat)){eligibleStats.add(stat);}}
		eStatsArray = eligibleStats.toArray(new Stat[0]);
	}
	
	@Override public int getCount(){return eligibleStats.size();}
	
	@Override public long getItemId(int which){return which;}
	@Override public Object getItem(int which){return eStatsArray[which];}
	
	@Override public View getView(int position, View convertView, ViewGroup parent)
	{
		ChooseAdvancementListItemViewHolder viewHolder;
		
		if(convertView == null)
		{
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.list_item_choose_advancement_points, parent, false);
			viewHolder = new ChooseAdvancementListItemViewHolder(convertView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ChooseAdvancementListItemViewHolder)convertView.getTag();
		}
		
		Stat pStat = (Stat)getItem(position);
		
		viewHolder.textLabel.setText(pStat.toString());
		viewHolder.valueLabel.setText(""+character.getBaseStat(pStat));
		
		viewHolder.incrementButton.setOnClickListener(new View.OnClickListener(){
			@Override public void onClick(View v){
				//increment!
			}
		});
		
		viewHolder.decrementButton.setOnClickListener(new View.OnClickListener(){
			@Override public void onClick(View v){
				//decrement!
			}
		});
			
		return convertView;
	}
}
