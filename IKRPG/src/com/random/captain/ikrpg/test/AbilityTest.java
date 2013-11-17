package com.random.captain.ikrpg.test;

import android.util.Log;
import com.random.captain.ikrpg.character.Ability;
import com.random.captain.ikrpg.character.AbilityEnum;
import com.random.captain.ikrpg.util.Utilities;

public class AbilityTest
{
	public static boolean runTests()
	{
		try
		{	
			Ability basicAbility = AbilityEnum.BACKSTAB.make();
			String json = Utilities.toJson(basicAbility);
			Ability basicFromJson = Utilities.fromJson(json, Ability.class);
			if(!basicAbility.equals(basicFromJson))
			{
				//test fail!
				Log.e("IKRPG","Test fail!  basicAbility JSON conversion failed");
			}
			else{Log.i("IKRPG","basicAbility test passed!");}
		}
		catch(Exception e){Log.e("IKRPG","basicAbility test exception: "+e.getMessage());}
		
		try
		{	
			Ability qualifiedAbility = AbilityEnum.LANGUAGE.make("Awesome");
			String json = Utilities.toJson(qualifiedAbility);
			Ability qualifiedFromJson = Utilities.fromJson(json, Ability.class);
			if(!qualifiedAbility.equals(qualifiedFromJson))
			{
				//test fail!
				Log.e("IKRPG","Test fail!  qualifiedAbility JSON conversion failed");
			}
			else{Log.i("IKRPG","qualifiedAbility test passed!");}
		}
		catch(Exception e){Log.e("IKRPG","qualifiedAbility test exception: "+e.getMessage());}
		
		try
		{	
			Ability basicAbilityA = AbilityEnum.BINDING.make();
			Ability basicAbilityB = AbilityEnum.EMPOWER.make();
			
			Ability qualifiedAbilityA = AbilityEnum.LANGUAGE.make("Awesome");
			Ability qualifiedAbilityB = AbilityEnum.LANGUAGE.make("Spiffy");
			Ability qualifiedAbilityC = AbilityEnum.BINDING.make("Spiffy");
			
			Ability basicAFromJson = Utilities.fromJson(Utilities.toJson(basicAbilityA), Ability.class);
			Ability basicBFromJson = Utilities.fromJson(Utilities.toJson(basicAbilityB), Ability.class);
			Ability qualifiedAFromJson = Utilities.fromJson(Utilities.toJson(qualifiedAbilityA), Ability.class);
			Ability qualifiedBFromJson = Utilities.fromJson(Utilities.toJson(qualifiedAbilityB), Ability.class);
			Ability qualifiedCFromJson = Utilities.fromJson(Utilities.toJson(qualifiedAbilityC), Ability.class);
			
			if(basicAFromJson.equals(basicBFromJson) || basicBFromJson.equals(basicAFromJson))
			{Log.e("IKRPG","Test fail!  basicAbilities should not be equal");}
			
			else if(qualifiedAFromJson.equals(qualifiedBFromJson) || qualifiedBFromJson.equals(qualifiedAFromJson))
			{Log.e("IKRPG","Test fail!  qualifiedAbilities with different qualifiers should not be equal");}
			
			else if(qualifiedBFromJson.equals(qualifiedCFromJson) || qualifiedCFromJson.equals(qualifiedBFromJson))
			{Log.e("IKRPG","Test fail!  qualifiedAbilities with different skills should not be equal");}
			
			else if(basicAFromJson.equals(qualifiedCFromJson) || qualifiedCFromJson.equals(basicAFromJson))
			{Log.e("IKRPG","Test fail!  basic and qualified should not be equal");}
 
			else{Log.i("IKRPG","Ability inequality test passed!");}
		}
		catch(Exception e){Log.e("IKRPG","Ability inequality test exception: "+e.getMessage());}
		
		return true;
	}
}
