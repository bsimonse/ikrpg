package com.random.captain.ikrpg.util;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.character.zzBaseCharacter;
import com.random.captain.ikrpg.character.zzChooseAdvancementListItemViewHolder;

public abstract class ChoosePointsAdapter<E> extends BaseAdapter
{
	public static class ChoosePointsBundle<E>
	{
		public int curVal;
		public int maxVal;
		public int minVal;
		public E item;

		public ChoosePointsBundle()
		{
			//whee
		}
		
		public ChoosePointsBundle(int pCur, int pMin, int pMax, E pItem)
		{
			curVal = pCur;
			maxVal = pMax;
			minVal = pMin;
			item = pItem;
		}
	}
	
	protected abstract int getIncreaseCount();
	protected abstract List<ChoosePointsBundle<E>> getItemList();
	protected abstract String getLabel(ChoosePointsBundle<E> item);

	protected zzBaseCharacter character;

	private int increases = 0;
	protected int getIncreases(){return increases;}
	
	private List<ChoosePointsBundle<E>> itemList;

	public ChoosePointsAdapter(zzBaseCharacter pChar)
	{
		character = pChar;
	}

	@Override public int getCount(){
		if(itemList == null){itemList = getItemList();}
		return itemList.size();
	}
	@Override public long getItemId(int which){return which;}
	@Override public Object getItem(int which){
		if(itemList == null){itemList = getItemList();}
		return itemList.get(which);
	}
	@Override public View getView(int position, View convertView, ViewGroup parent)
	{
		final zzChooseAdvancementListItemViewHolder viewHolder;

		if(convertView == null)
		{
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.list_item_choose_advancement_points, parent, false);
			viewHolder = new zzChooseAdvancementListItemViewHolder(convertView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (zzChooseAdvancementListItemViewHolder)convertView.getTag();
		}

		@SuppressWarnings("unchecked")
		final ChoosePointsBundle<E> bundle = (ChoosePointsBundle<E>)getItem(position);

		viewHolder.textLabel.setText(getLabel(bundle));
		viewHolder.valueLabel.setText(""+bundle.curVal+"/"+bundle.maxVal);

		viewHolder.incrementButton.setOnClickListener(new View.OnClickListener(){
			@Override public void onClick(View v){
				if(increases >= getIncreaseCount()){return;}

				if(bundle.curVal < bundle.maxVal)
				{
					increases++;
					bundle.curVal++;
					notifyDataSetChanged();
				}
			}
		});

		viewHolder.decrementButton.setOnClickListener(new View.OnClickListener(){
			@Override public void onClick(View v){
				if(increases == 0){return;}

				if(bundle.curVal > bundle.minVal)
				{
					increases--;
					bundle.curVal--;
					notifyDataSetChanged();
				}
			}
		});

		//is increment button disabled
		if(increases >= getIncreaseCount() || bundle.curVal >= bundle.maxVal)
		{viewHolder.incrementButton.setEnabled(false);}
		else{viewHolder.incrementButton.setEnabled(true);}

		//is decrement button disabled
		if(increases <= 0 || bundle.curVal <= bundle.minVal)
		{viewHolder.decrementButton.setEnabled(false);}
		else{viewHolder.decrementButton.setEnabled(true);}

		return convertView;
	}
}
