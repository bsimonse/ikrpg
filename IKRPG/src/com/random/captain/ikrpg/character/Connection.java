package com.random.captain.ikrpg.character;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Connection implements Parcelable
{
	private String qualifier;
	
	public Connection(String pQualifier){qualifier = pQualifier;}
	
	public static Connection make(String pQualifier){return new Connection(pQualifier);}
	
	/* Parcelling */
	public void writeToParcel(Parcel toParcel, int flags)
	{
		toParcel.writeString(qualifier);
	}

	public static final Parcelable.Creator<Connection> CREATOR = new Parcelable.Creator<Connection>()
	{
		@Override
		public Connection createFromParcel(Parcel in)
		{
			try
			{
				String pQualifier = in.readString();
				return new Connection(pQualifier);
			}
			catch(Exception e)
			{
				Log.e("IKRPG","Bad news, dude, Connection didn't Parcel correctly!");
				return new Connection("unknown");
			}
		}

		@Override public Connection[] newArray(int size){return new Connection[size];}
	};

	@Override public int describeContents(){return 0;}
}
