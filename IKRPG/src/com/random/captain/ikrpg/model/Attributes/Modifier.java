package com.random.captain.ikrpg.model.Attributes;

public class Modifier<S extends Enum<S>>
{
	private int value;
	private S stat;
	
	private Modifier (S pStat)
	{
		this(pStat, 0);
	}
	
	private Modifier(S pStat, int pValue)
	{
		stat = pStat;
		value = pValue;
	}
	
	//Java is weird.
	//Thanks, S.O.!
	//
	//All access is through these static methods for simplicity.
	public static <S2 extends Enum<S2>> Modifier<S2> onStat(S2 pStat, int pValue)
	{
		return new Modifier<S2>(pStat, pValue);
	}
	
	public static <S2 extends Enum<S2>> Modifier<S2> onStat(S2 pStat)
	{
		return new Modifier<S2>(pStat);
	}
	
	public int getValue()
	{
		return value;
	}
	
	public S getStat()
	{
		return stat;
	}
}
