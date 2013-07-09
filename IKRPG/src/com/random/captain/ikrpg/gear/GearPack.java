package com.random.captain.ikrpg.gear;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class GearPack implements Parcelable
{
	int gold;
	ArrayList<zzBaseGear> items;


	/* Parceling */
	
	@Override public void writeToParcel(Parcel out, int flags)
	{
		
	}
	
	public static final Parcelable.Creator<GearPack> CREATOR = new Parcelable.Creator<GearPack>()
	{
		@Override
		public GearPack createFromParcel(Parcel in)
		{
			return new GearPack();
		}

		@Override public GearPack[] newArray(int size) {return new GearPack[size];}
	};
	
	@Override public int describeContents(){return 0;}
}
