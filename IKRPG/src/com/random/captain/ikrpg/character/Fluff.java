package com.random.captain.ikrpg.character;

import android.os.Parcel;
import android.os.Parcelable;

public class Fluff implements Parcelable
{
	public String name;
	public String sex;
	public String characteristics;
	public String height;
	public String weight;
	public String faith;
	public String owningPlayer;
	
	public Fluff(){name=owningPlayer="???";}
	public Fluff(String pName){this();name=pName;}
	public Fluff(String pName, String pSex, String pCharacteristics, String pHeight, String pWeight, String pFaith, String pOwningPlayer)
	{
		name=pName;
		sex=pSex;
		characteristics=pCharacteristics;
		height=pHeight;
		weight=pWeight;
		faith=pFaith;
		owningPlayer=pOwningPlayer;
	}
	
	@Override
	public String toString()
	{
		StringBuilder mString = new StringBuilder(100);
		
		mString.append(name+"\n");
		mString.append("Sex: "+sex+"\n");
		mString.append("Characteristics: "+characteristics+"\n");
		mString.append("Height: "+height+"\n");
		mString.append("Weight: "+weight+"\n");
		mString.append("Faith: "+faith+"\n");
		mString.append("Owning player: "+owningPlayer);
		
		return mString.toString();
	}
	
	@Override
	public void writeToParcel(Parcel toParcel, int flags)
	{
		toParcel.writeString(name);
		toParcel.writeString(sex);
		toParcel.writeString(characteristics);
		toParcel.writeString(height);
		toParcel.writeString(weight);
		toParcel.writeString(faith);
		toParcel.writeString(owningPlayer);
	}
	
	public static final Parcelable.Creator<Fluff> CREATOR = new Parcelable.Creator<Fluff>()
	{
		@Override public Fluff createFromParcel(Parcel in)
		{
			Fluff mFluff = new Fluff();
			mFluff.name = in.readString();
			mFluff.sex = in.readString();
			mFluff.characteristics = in.readString();
			mFluff.height = in.readString();
			mFluff.weight = in.readString();
			mFluff.faith = in.readString();
			mFluff.owningPlayer = in.readString();
			return mFluff;
		}
		
		@Override public Fluff[] newArray(int size)
		{
			return new Fluff[size];
		}
	};
	
	@Override public int describeContents() {return 0;}
}
