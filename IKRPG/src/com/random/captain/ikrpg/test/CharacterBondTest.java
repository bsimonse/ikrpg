package com.random.captain.ikrpg.test;

import android.util.Log;

import com.random.captain.ikrpg.character.Character;
import com.random.captain.ikrpg.character.CharacterBond;
import com.random.captain.ikrpg.character.CharacterCreationServiceActivity;
import com.random.captain.ikrpg.util.Utilities;

public class CharacterBondTest
{
	public static boolean runTests()
	{
		Character testChar = CharacterCreationServiceActivity.getPascal();
		
		try
		{	
			CharacterBond basicBond = new CharacterBond(testChar);
			String json = Utilities.toJson(basicBond);
			CharacterBond basicFromJson = Utilities.fromJson(json, CharacterBond.class);
			if(!basicBond.equals(basicFromJson))
			{
				//test fail!
				Log.e("IKRPG","Test fail!  basicBond JSON conversion failed");
			}
			else{Log.i("IKRPG","basicBond test passed!");}
		}
		catch(Exception e){Log.e("IKRPG","basicBond test exception: "+e.getMessage());}
		
		return true;
	}
}
