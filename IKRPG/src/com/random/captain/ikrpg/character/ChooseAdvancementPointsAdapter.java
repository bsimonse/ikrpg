package com.random.captain.ikrpg.character;

import java.util.*;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.random.captain.ikrpg.R;

class ChooseAdvancementPointsAdapter extends BaseAdapter
{
	private final static int MAX_INCREASES = 3;
	private BaseCharacter character;
	private Map<Stat, Integer> eligibleStats;
	private List<Stat> eStatsList; //for keeping order straight
	private int increases = 0;
	private ListView listView;
	
	public ChooseAdvancementPointsAdapter(BaseCharacter pChar, ListView pListView)
	{
		character = pChar;
		listView = pListView;
		
		//remember all stats that can be increased
		eligibleStats = new HashMap<Stat, Integer>(10);
		for(Stat stat : Stat.values()){if(pChar.getBaseStat(stat) < pChar.getMaxStat(stat)){eligibleStats.put(stat, pChar.getBaseStat(stat));}}
		
		eStatsList = new ArrayList<Stat>(10);
		eStatsList.addAll(eligibleStats.keySet());
		Collections.sort(eStatsList, new Comparator<Stat>(){
			@Override public int compare(Stat first, Stat second)
			{
				return first.ordinal() - second.ordinal();
			}
		});
	}
	
	public void lockInStats()
	{
		StatsBundle stats = character.statsBundle;
		for(Stat stat : eligibleStats.keySet())
		{stats.setBaseStat(stat, eligibleStats.get(stat));}
	}
	
	@Override public int getCount(){return eStatsList.size();}
	@Override public long getItemId(int which){return which;}
	@Override public Object getItem(int which){return eStatsList.get(which);}
	@Override public View getView(int position, View convertView, ViewGroup parent)
	{
		final ChooseAdvancementListItemViewHolder viewHolder;
		
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
		
		final Stat pStat = (Stat)getItem(position);
		
		viewHolder.textLabel.setText(pStat.toString());
		viewHolder.valueLabel.setText(""+eligibleStats.get(pStat)+"/"+character.getMaxStat(pStat));
		
		viewHolder.incrementButton.setOnClickListener(new View.OnClickListener(){
			@Override public void onClick(View v){
				if(increases >= MAX_INCREASES){return;}
				
				int oldValue = eligibleStats.get(pStat);
				int newValue = oldValue + 1;
				if(newValue <= character.getMaxStat(pStat))
				{
					increases++;
					eligibleStats.put(pStat, newValue);
					Log.i("IKRPG","New value of "+pStat.shortName()+" is "+newValue);
					notifyDataSetChanged();
				}
			}
		});
		
		viewHolder.decrementButton.setOnClickListener(new View.OnClickListener(){
			@Override public void onClick(View v){
				if(increases == 0){return;}
				
				int oldValue = eligibleStats.get(pStat);
				int newValue = oldValue - 1;
				if(newValue >= character.getBaseStat(pStat))
				{
					increases--;
					eligibleStats.put(pStat, newValue);
					Log.i("IKRPG","New value of "+pStat.shortName()+" is "+newValue);
					notifyDataSetChanged();
				}
			}
		});
		
		//is increment button disabled
		if(increases >= MAX_INCREASES || eligibleStats.get(pStat) >= character.getMaxStat(pStat))
		{viewHolder.incrementButton.setEnabled(false);}
		else{viewHolder.incrementButton.setEnabled(true);}
		
		//is decrement button disabled
		if(increases <= 0 || eligibleStats.get(pStat) <= character.getBaseStat(pStat))
		{viewHolder.decrementButton.setEnabled(false);}
		else{viewHolder.decrementButton.setEnabled(true);}
		
		return convertView;
	}
}
