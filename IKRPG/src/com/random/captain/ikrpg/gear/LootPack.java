package com.random.captain.ikrpg.gear;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class LootPack implements Parcelable
{
	int gold;
	Collection<Loot> loot;

	public LootPack()
	{this(0, new ArrayList<Loot>());}
	
	public LootPack(int pGold, Collection<Loot> pLoot)
	{
		gold = pGold;
		loot = pLoot;
	}
	
	public int gold(){return gold;}
	public Collection<Loot> loot(){return Collections.unmodifiableCollection(loot);}
	
	//hmmm.
	public void addLoot(Loot pLoot)
	{loot.add(pLoot);}
	
	public void addLoot(Collection<Loot> pLoot)
	{loot.addAll(pLoot);}
	
	/* Parceling */
	
	@Override public void writeToParcel(Parcel out, int flags)
	{
		
	}
	
	public static final Parcelable.Creator<LootPack> CREATOR = new Parcelable.Creator<LootPack>()
	{
		@Override
		public LootPack createFromParcel(Parcel in)
		{
			return new LootPack();
		}

		@Override public LootPack[] newArray(int size) {return new LootPack[size];}
	};
	
	@Override public int describeContents(){return 0;}
}
