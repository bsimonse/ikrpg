package com.random.captain.ikrpg.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import com.random.captain.ikrpg.R;

public class MainActivity extends FragmentActivity
{
	private static final int NEW_CHARACTER_ACTIVITY_RESULT = 1;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
    }

	public void createNewCharacterTapped(View tappee)
	{
		//Start new character activity!!
		Intent i = new Intent(this, NewCharacterActivity.class);
		startActivityForResult(i, NEW_CHARACTER_ACTIVITY_RESULT);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent i)
	{
		if(requestCode == NEW_CHARACTER_ACTIVITY_RESULT)
		{
			//It... worked?
			Log.i("IKRPG","Character... created(?)");
		}
	}
}
