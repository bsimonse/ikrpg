package com.random.captain.ikrpg;

import android.app.Application;
import android.content.Context;

public class IKRPGApp extends Application
{
	private static Context c;
	@Override public void onCreate(){super.onCreate();c=this;}
	
	public static Context getContext()
	{return c;}
}
