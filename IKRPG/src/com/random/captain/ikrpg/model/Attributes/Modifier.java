package com.random.captain.ikrpg.model.Attributes;

public class Modifier<S extends Enum<S>>
{
	private int value;
	private S trait;
	
	private Modifier (S pTrait)
	{
		this(pTrait, 0);
	}
	
	private Modifier(S pTrait, int pValue)
	{
		trait = pTrait;
		value = pValue;
	}
	
	//Java is weird.
	//Thanks, S.O.!
	//
	//All access is through these static methods for simplicity.
	public static <S2 extends Enum<S2>> Modifier<S2> onTrait(S2 pTrait, int pValue)
	{
		return new Modifier<S2>(pTrait, pValue);
	}
	
	public static <S2 extends Enum<S2>> Modifier<S2> onTrait(S2 pTrait)
	{
		return new Modifier<S2>(pTrait);
	}
	
	public int getValue()
	{
		return value;
	}
	
	public S getTrait()
	{
		return trait;
	}
	
	//future improvement will allow for setting here, rather than incrementing
	public int modifiedValue(int base)
	{
		return base += value;
	}
}
