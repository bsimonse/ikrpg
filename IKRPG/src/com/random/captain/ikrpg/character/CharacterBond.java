package com.random.captain.ikrpg.character;

import android.os.Parcel;
import android.os.Parcelable;

//For anything that can be bonded to a character,
//from tailored plate to warjacks
//(Not a rules "bond", but a link with a character)
public class CharacterBond implements Parcelable
{
	private int charIndex;
	private String charName;

	public CharacterBond(zzBaseCharacter pChar)
	{
		charIndex = pChar.index;
		charName = pChar.fluff.name;
	}
	
	public int getCharIndex(){return charIndex;}
	public String getCharName(){return charName;}
	
	/* Parceling */

	private CharacterBond(int pIndex, String pName)
	{
		charIndex = pIndex;
		charName = pName;
	}
	
	@Override public void writeToParcel(Parcel out, int flags)
	{
		out.writeInt(charIndex);
		out.writeString(charName);
	}

	public static final Parcelable.Creator<CharacterBond> CREATOR = new Parcelable.Creator<CharacterBond>()
	{
		@Override
		public CharacterBond createFromParcel(Parcel in)
		{
			int index = in.readInt();
			String name = in.readString();
			
			return new CharacterBond(index, name);
		}

		@Override public CharacterBond[] newArray(int size) {return new CharacterBond[size];}
	};

	@Override public int describeContents(){return 0;}
}
