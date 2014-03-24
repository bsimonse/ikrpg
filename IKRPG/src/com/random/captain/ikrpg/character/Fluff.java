package com.random.captain.ikrpg.character;


public class Fluff
{
	public String name;
	public String sex;
	public String characteristics;
	public String height;
	public String weight;
	public String faith;
	public String owningPlayer;
	
	public Fluff(){name=owningPlayer="???";}
	public Fluff(String pName){this();name=pName;}
	public Fluff(String pName, String pSex, String pCharacteristics, String pHeight, String pWeight, String pFaith, String pOwningPlayer)
	{
		name=pName;
		sex=pSex;
		characteristics=pCharacteristics;
		height=pHeight;
		weight=pWeight;
		faith=pFaith;
		owningPlayer=pOwningPlayer;
	}
	
	@Override
	public String toString()
	{
		StringBuilder mString = new StringBuilder(100);
		
		mString.append(name+"\n");
		mString.append("Sex: "+sex+"\n");
		mString.append("Characteristics: "+characteristics+"\n");
		mString.append("Height: "+height+"\n");
		mString.append("Weight: "+weight+"\n");
		mString.append("Faith: "+faith+"\n");
		mString.append("Owning player: "+owningPlayer);
		
		return mString.toString();
	}
}
