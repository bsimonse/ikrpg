package com.random.captain.ikrpg.gear;

import com.random.captain.ikrpg.character.*;

import android.os.Parcel;
import android.os.Parcelable;
import com.random.captain.ikrpg.combat.SpecialRule;
import java.util.ArrayList;

public class zzBaseGear implements Parcelable
{
	public enum EquipmentSlots
	{
		HEAD,
		BODY,
		ARM1,
		ARM2;
	}

	String name;
	int goldCost;
	
	CharacterBond bond;
	
	//something something stats
	ArrayList<Modifier<Stat>> statModifiers;
	ArrayList<Modifier<Skill>> skillModifiers;
	ArrayList<Ability> grantedAbilities;
	ArrayList<SpecialRule> specialRules;
	
	@Override public void writeToParcel(Parcel out, int flags)
	{
		//TODO
	}

	public static final Parcelable.Creator<zzBaseGear> CREATOR = new Parcelable.Creator<zzBaseGear>()
	{
		@Override
		public zzBaseGear createFromParcel(Parcel in)
		{
			//TODO
			return new zzBaseGear();
		}

		@Override public zzBaseGear[] newArray(int size) {return new zzBaseGear[size];}
	};

	@Override public int describeContents(){return 0;}
}
