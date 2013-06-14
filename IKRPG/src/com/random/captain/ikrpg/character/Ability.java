package com.random.captain.ikrpg.character;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Ability implements Parcelable, zzPrereqCheck
{
	private AbilityEnum ability;
	private String qualifier;

	public Ability(AbilityEnum pAbility)
	{
		this(pAbility, "");
	}

	public Ability(AbilityEnum pAbility, String pQualifier)
	{
		ability = pAbility;
		qualifier = pQualifier==null ? "" : pQualifier;
	}

	@Override public String toString(){
		if(qualifier == null || qualifier.length() < 1)
		{return abilityName();}

		return abilityName()+"("+qualifier+")";
	}

	@Override
	public boolean equals(Object other)
	{
		try
		{
			Ability p = (Ability)other;
			return p.ability == ability && p.qualifier.equals(qualifier);
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override public int hashCode()
	{
		int hash = 0;
		if(ability != null){hash += 13*17*ability.ordinal();}
		if(qualifier != null){hash += 19*qualifier.hashCode();}
		return hash;
	}

	public AbilityEnum abilityEnum(){return ability;}
	public String qualifier(){return qualifier;}

	//mimic abilityEnum API
	public String abilityName(){return ability.displayName();}
	public String longDescription(){return ability.longDescription();}
	public String shortDescription(){return ability.shortDescription();}
	public String pageNumber(){return ability.pageNumbrer();}

	@Override public zzPrereqCheckResult meetsPrereq(zzBaseCharacter myChar) {return ability.meetsPrereq(myChar);}
	
	/* Parcelling */
	public void writeToParcel(Parcel toParcel, int flags)
	{
		toParcel.writeSerializable(ability);
		toParcel.writeString(qualifier);
	}

	public static final Parcelable.Creator<Ability> CREATOR = new Parcelable.Creator<Ability>()
	{
		@Override
		public Ability createFromParcel(Parcel in)
		{
			try
			{
				AbilityEnum pAbility = (AbilityEnum)in.readSerializable();
				String pQualifier = in.readString();
				return new Ability(pAbility, pQualifier);
			}
			catch(Exception e)
			{
				Log.e("IKRPG","Bad news, dude, Ability didn't Parcel correctly!");
				return new Ability(null,null);
			}
		}

		@Override
		public Ability[] newArray(int size)
		{
			return new Ability[size];
		}
	};

	@Override public int describeContents(){return 0;}
}
