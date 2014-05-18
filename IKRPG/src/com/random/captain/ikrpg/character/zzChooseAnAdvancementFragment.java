package com.random.captain.ikrpg.character;

import android.widget.*;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.random.captain.ikrpg.R;
import com.random.captain.ikrpg.util.BundleConstants;
import java.util.List;

public abstract class zzChooseAnAdvancementFragment<E> extends zzCharacterAdvancementFragment
	{
		abstract List<E> getItems();
		abstract void onChosen(E chosen);
		abstract String getTitle();
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle bund)
		{return inflater.inflate(R.layout.frag_choice_list, root, false);}

		@Override
		public void onViewCreated(View rootView, Bundle b)
		{
			super.onViewCreated(rootView,b);
			ListView listView = (ListView)rootView.findViewById(R.id.listChoiceList);
			listView.setAdapter(new ArrayAdapter<E>(getActivity(), android.R.layout.simple_list_item_1, getItems()));
			listView.setOnItemClickListener( new AdapterView.OnItemClickListener(){
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int which, long id)
					{
						onChosen(getItems().get(which));
						Bundle b = new Bundle();
						b.putString(BundleConstants.CHARACTER, myChar.toJson());
						delegate.hookComplete(b);
					}
				});

			TextView tv = (TextView)rootView.findViewById(R.id.listChoiceTitle);
			tv.setText(getTitle());
		}

		@Override public boolean hasUI(){return true;}
		@Override public int getPriority(){return -1;}
	}
