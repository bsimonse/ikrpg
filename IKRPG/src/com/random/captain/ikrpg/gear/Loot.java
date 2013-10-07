package com.random.captain.ikrpg.gear;

import com.random.captain.ikrpg.character.*;
import android.os.Parcel;
import android.os.Parcelable;
import com.random.captain.ikrpg.combat.SpecialRule;
import java.util.ArrayList;
import com.random.captain.ikrpg.character.Modifier;
import com.random.captain.ikrpg.character.Stat;

public class Loot implements Parcelable
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
	ArrayList<Modifier<Stat>> stats;
	
	@Override public void writeToParcel(Parcel out, int flags)
	{
		//TODO
	}

	public static final Parcelable.Creator<Loot> CREATOR = new Parcelable.Creator<Loot>()
	{
		@Override
		public Loot createFromParcel(Parcel in)
		{
			//TODO
			return new Loot();
		}

		@Override public Loot[] newArray(int size) {return new Loot[size];}
	};

	@Override public int describeContents(){return 0;}
}
