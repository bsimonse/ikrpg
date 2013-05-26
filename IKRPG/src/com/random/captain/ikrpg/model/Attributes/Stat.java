package com.random.captain.ikrpg.model.Attributes;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.random.captain.ikrpg.model.Attributes.Modifier;

public enum Stat implements Parcelable
{
	//"Normal stats"
	PHYSIQUE("Physique", "PHY"),
	SPEED("Speed","SPD"),
	STRENGTH("Strength","STR"),
	AGILITY("Agility","AGI"),
	POISE("Poise","POI"),
	PROWESS("Prowess","PRW"),
	INTELLECT("Intellect","INT"),
	ARCANE("Arcane","ARC"),
	PERCEPTION("Perception","PER"),
	DEFENSE("Defense","DEF"),
	INITIATIVE("Initiative","INIT"),
	ARMOR("Armor","ARM"),
	WILLPOWER("Willpower","WIL"),
	COMMAND("Command","CMD"),
	CONTROL("Control","CTRL"),
	//"Other" stats
	MELEE_ATTACK("Melee attack modifier","ATK(M)"),
	MELEE_DAMAGE("Melee damage modifier","DMG(M)"),
	RANGED_ATTACK("Ranged attack modifier","ATK(R)"),
	RANGED_DAMAGE("Ranged damage modifier","DMG(R)");
	
	private Stat(String pLongName, String pShortName)
	{
		this.longName = pLongName;
		this.shortName = pShortName;
	};
	
	private String longName;
	private String shortName;
	
	public String longName(){return longName;}
	public String shortName(){return shortName;}
	
	@Override
	public String toString(){return longName+"("+shortName+")";}
	
	/* Parcelling */
	public void writeToParcel(Parcel toParcel, int flags)
	{
		toParcel.writeInt(this.ordinal());
	}

	public static final Parcelable.Creator<Stat> CREATOR = new Parcelable.Creator<Stat>()
	{
		@Override
		public Stat createFromParcel(Parcel in)
		{
			try
			{
				int value = in.readInt();
				return Stat.values()[value];
			}
			catch(Exception e)
			{
				Log.e("IKRPG","Bad news, dude, Stat didn't Parcel correctly!");
				return null;
			}
		}

		@Override
		public Stat[] newArray(int size)
		{
			return new Stat[size];
		}
	};

	public int describeContents()
	{
		return 0;
	}
}
