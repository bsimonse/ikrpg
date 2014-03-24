package com.random.captain.ikrpg.character;


//For anything that can be bonded to a character,
//from tailored plate to warjacks
//(Not a rules "bond", but a link with a character)
public class CharacterBond
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
	
	@Override
	public boolean equals(Object other)
	{
		try
		{
			CharacterBond b = (CharacterBond)other;
			return charIndex == b.charIndex && charName.equals(b.charName);
		}
		catch(Exception e)
		{return false;}
	}
}
