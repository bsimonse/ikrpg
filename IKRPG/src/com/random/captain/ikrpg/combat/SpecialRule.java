package com.random.captain.ikrpg.combat;


public class SpecialRule
{
	public enum SpecialRuleTrigger
	{
		ON_ATTACK, ON_HIT, ON_CRIT, ON_DAMAGE, CONSTANT
	}
	
	SpecialRuleTrigger trigger;
	String ruleText;
	
	public SpecialRule(SpecialRuleTrigger pTrigger, String pRuleText)
	{
		trigger = pTrigger;
		ruleText = pRuleText;
	}
}
