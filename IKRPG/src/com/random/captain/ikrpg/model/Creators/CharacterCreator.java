package com.random.captain.ikrpg.model.Creators;

import android.content.Context;
import com.random.captain.ikrpg.model.Attributes.Career;
import com.random.captain.ikrpg.model.Attributes.Race;
import com.random.captain.ikrpg.model.BaseCharacter;
import java.util.ArrayList;

public class CharacterCreator
{
	public static BaseCharacter createCharacter(Race pRace, Career career1, Career career2, Context appContext)
	{
		ArrayList<PostCreateHook> postCreateHooks = new ArrayList<PostCreateHook>();
		
		return new BaseCharacter(null, null, null, null, null);
	}
}
