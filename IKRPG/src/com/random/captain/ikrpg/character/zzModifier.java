package com.random.captain.ikrpg.character;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

class zzModifier<S extends Parcelable> implements Parcelable
{
	int value;
	S trait;
	Class<S> genericClass;
	
	zzModifier (S pTrait){this(pTrait, 0);}	
	zzModifier(S pTrait, int pValue)
	{
		trait = pTrait;
		value = pValue;
		genericClass = (Class<S>)trait.getClass();
	}
	
	//Java is weird.
	//Thanks, S.O.!
	//
	//All access is through these static methods for simplicity.
	/*static <S2 extends Parcelable> zzModifier<S2> onTrait(S2 pTrait, int pValue)
	{return new zzModifier<S2>(pTrait, pValue);}
	
	static <S2 extends Parcelable> zzModifier<S2> onTrait(S2 pTrait)
	{return new zzModifier<S2>(pTrait);}*/
	
	//future improvement will allow for setting here, rather than incrementing
	int modifiedValue(int base){return base + value;}
	
	@Override
	public String toString()
	{return trait.toString()+": "+value;}
	
	/* Parcelling */
	public void writeToParcel(Parcel toParcel, int flags)
	{
		toParcel.writeSerializable(genericClass);
		toParcel.writeInt(value);
		toParcel.writeParcelable(trait, 0);
	}

	public static final Parcelable.Creator<zzModifier> CREATOR = new Parcelable.Creator<zzModifier>()
	{
		@Override
		public zzModifier createFromParcel(Parcel in)
		{
			try
			{
				Class which = (Class)in.readSerializable();
				int value = in.readInt();
				Parcelable trait = in.readParcelable(which.getClassLoader());
				return new zzModifier(trait, value);
			}
			catch(Exception e)
			{
				Log.e("IKRPG","Bad news, dude, Modifier didn't Parcel correctly!");
				return null;//ew zzModifier(null, 0);
			}
		}

		@Override public zzModifier[] newArray(int size){return new zzModifier[size];}
	};

	@Override public int describeContents() {return 0;}
}
