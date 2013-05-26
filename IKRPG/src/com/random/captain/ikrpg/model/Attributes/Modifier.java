package com.random.captain.ikrpg.model.Attributes;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.random.captain.ikrpg.model.Attributes.Modifier;
import java.io.Serializable;

public class Modifier<S extends Parcelable> implements Parcelable
{
	private int value;
	private S trait;
	private Class<S> genericClass;
	
	private Modifier (S pTrait)
	{
		this(pTrait, 0);
	}
	
	private Modifier(S pTrait, int pValue)
	{
		trait = pTrait;
		value = pValue;
		genericClass = (Class<S>)trait.getClass();
	}
	
	//Java is weird.
	//Thanks, S.O.!
	//
	//All access is through these static methods for simplicity.
	public static <S2 extends Parcelable> Modifier<S2> onTrait(S2 pTrait, int pValue)
	{return new Modifier<S2>(pTrait, pValue);}
	
	public static <S2 extends Parcelable> Modifier<S2> onTrait(S2 pTrait)
	{return new Modifier<S2>(pTrait);}
	
	public int getValue(){return value;}
	public S getTrait(){return trait;}
	
	//future improvement will allow for setting here, rather than incrementing
	public int modifiedValue(int base){return base + value;}
	
	@Override
	public String toString()
	{
		return trait.toString()+": "+value;
	}
	
	/* Parcelling */
	public void writeToParcel(Parcel toParcel, int flags)
	{
		toParcel.writeSerializable(genericClass);
		toParcel.writeInt(value);
		toParcel.writeParcelable(trait, 0);
	}

	public static final Parcelable.Creator<Modifier> CREATOR = new Parcelable.Creator<Modifier>()
	{
		@Override
		public Modifier createFromParcel(Parcel in)
		{
			try
			{
				Class which = (Class)in.readSerializable();
				int value = in.readInt();
				Parcelable trait = in.readParcelable(which.getClassLoader());
				return Modifier.onTrait(trait, value);
			}
			catch(Exception e)
			{
				Log.e("IKRPG","Bad news, dude, Modifier didn't Parcel correctly!");
				return Modifier.onTrait(null, 0);
			}
		}

		@Override
		public Modifier[] newArray(int size)
		{
			return new Modifier[size];
		}
	};

	public int describeContents()
	{
		return 0;
	}
}
