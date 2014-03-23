package com.random.captain.ikrpg.character;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.util.BundleConstants;

public abstract class zzChooseOneHook<T> extends zzCreateCharacterHook
{
	protected abstract String getTitle();
	protected abstract List<T> getOptions();
	protected abstract void itemSelected(int which);
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup pRoot, Bundle bund)
	{
		LinearLayout root = (LinearLayout)inflater.inflate(R.layout.frag_choice_list, pRoot, false);
		ListView choiceList = (ListView)root.findViewById(R.id.listChoiceList);
		choiceList.setAdapter(new ArrayAdapter<T>(inflater.getContext(), android.R.layout.simple_list_item_1, getOptions()));
		choiceList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
				@Override public void onItemClick(AdapterView<?> parent, View view, int which, long id)
				{
					itemSelected(which);
					Bundle b = new Bundle();
					b.putString(BundleConstants.CHARACTER, myChar.toJson());
					delegate.hookComplete(b);
				}
			});

		TextView tv = (TextView)root.findViewById(R.id.listChoiceTitle);
		tv.setText(getTitle());

		return root;
	}

	@Override protected boolean hasUI(){return true;}
	@Override public int getPriority(){return 50;}
}
