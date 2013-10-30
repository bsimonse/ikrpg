package com.random.captain.ikrpg.gear;

import com.random.captain.ikrpg.character.*;

import android.os.Parcel;
import android.os.Parcelable;
import com.random.captain.ikrpg.combat.SpecialRule;
import java.util.ArrayList;
import java.util.List;

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
	String description;
	int goldCost;

	List<Modifier<Stat>> statModifiers;
	List<Modifier<Skill>> skillModifiers;
	List<Ability> grantedAbilities;
	List<SpecialRule> specialRules;
	List<EquipmentSlots> slots;
	
	CharacterBond bond;
	
	Loot()
	{
		this("","",0,null,null,null,null,null,null);
	}
	
	public Loot(String pName, String pDesc, int pCost, List<Modifier<Stat>> pStats, List<Modifier<Skill>> pSkills, List<Ability> pAbilities,
						List<SpecialRule> pRules, List<EquipmentSlots> pSlots, CharacterBond pBond)
	{
		name = pName;
		description = pDesc;
		goldCost = pCost;
		statModifiers = pStats != null ? pStats : new ArrayList<Modifier<Stat>>();
		skillModifiers = pSkills != null ? pSkills : new ArrayList<Modifier<Skill>>();
		grantedAbilities = pAbilities != null ? pAbilities : new ArrayList<Ability>();
		specialRules = pRules != null ? pRules : new ArrayList<SpecialRule>();
		slots = pSlots != null ? pSlots : new ArrayList<EquipmentSlots>();
		bond = pBond;
	}
	
	//Parcelling
	
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
