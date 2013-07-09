package com.random.captain.ikrpg.gear;

import android.os.Parcel;
import android.os.Parcelable;
import com.random.captain.ikrpg.character.Modifier;
import java.util.ArrayList;

public class zzBaseGear implements Parcelable
{
	String name;
	int goldCost;
	
	//something something stats
	ArrayList<Modifier> stats;
	
	@Override public void writeToParcel(Parcel out, int flags)
	{

	}

	public static final Parcelable.Creator<zzBaseGear> CREATOR = new Parcelable.Creator<zzBaseGear>()
	{
		@Override
		public zzBaseGear createFromParcel(Parcel in)
		{
			return new zzBaseGear();
		}

		@Override public zzBaseGear[] newArray(int size) {return new zzBaseGear[size];}
	};

	@Override public int describeContents(){return 0;}
}
