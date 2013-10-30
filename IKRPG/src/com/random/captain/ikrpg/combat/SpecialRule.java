package com.random.captain.ikrpg.combat;

import android.os.Parcel;
import android.os.Parcelable;
import com.random.captain.ikrpg.combat.SpecialRule;

public class SpecialRule implements Parcelable
{
	public enum SpecialRuleTrigger
	{
		ON_ATTACK, ON_HIT, ON_CRIT, ON_DAMAGE, CONSTANT
	}
	
	private SpecialRuleTrigger trigger;
	private String ruleText;
	
	public SpecialRule(SpecialRuleTrigger pTrigger, String pRuleText)
	{
		trigger = pTrigger;
		ruleText = pRuleText;
	}
	
	/* Parceling */

	@Override public void writeToParcel(Parcel out, int flags)
	{
		out.writeSerializable(trigger);
		out.writeString(ruleText);
	}

	public static final Parcelable.Creator<SpecialRule> CREATOR = new Parcelable.Creator<SpecialRule>()
	{
		@Override
		public SpecialRule createFromParcel(Parcel in)
		{
			SpecialRuleTrigger pTrigger = (SpecialRuleTrigger)in.readSerializable();
			String pRuleText = in.readString();
			
			return new SpecialRule(pTrigger, pRuleText);
		}

		@Override public SpecialRule[] newArray(int size) {return new SpecialRule[size];}
	};

	@Override public int describeContents(){return 0;}
}
