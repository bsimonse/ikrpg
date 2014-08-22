package com.random.captain.ikrpg.util;

import android.content.Context;
import android.widget.TextView;
import android.util.AttributeSet;
import android.graphics.Typeface;

public class ElanTextView extends TextView
{
	public ElanTextView(Context c)
	{
		super(c);
		changeFont();
	}
	
	public ElanTextView(Context c, AttributeSet attrs)
	{
		super(c,attrs);
		changeFont();
	}
	
	public ElanTextView(Context c, AttributeSet attrs, int hm)
	{
		super(c, attrs, hm);
		changeFont();
	}
	
	protected void changeFont()
	{
		Typeface elan = Typeface.createFromAsset(getContext().getAssets(), "ITC_Elan_Black.ttf");
		setTypeface(elan);
	}
}
