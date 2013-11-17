package com.random.captain.ikrpg.test;

import android.util.Log;
import com.random.captain.ikrpg.character.Skill;
import com.random.captain.ikrpg.character.SkillEnum;
import com.random.captain.ikrpg.util.Utilities;

public class SkillTest
{
	public static boolean runTests()
	{
		//Basic equality
		try
		{
			Skill basicSkill = new Skill(SkillEnum.HAND_WEAPON);
			String json = Utilities.toJson(basicSkill);
			Skill basicFromJson = Utilities.fromJson(json, Skill.class);
			
			if(!basicSkill.equals(basicFromJson) || !basicFromJson.equals(basicSkill))
			{
				//test fail!
				Log.e("IKRPG","Test fail!  basicSkill JSON conversion failed\n"+basicSkill.toString()+"\n"+basicFromJson.toString());
			}
			else{Log.i("IKRPG","basicSkill test passed!");}
		}
		catch(Exception e){Log.e("IKRPG","basicSkill test exception");}
		
		//Qualified equality
		try
		{	
			Skill qualifiedSkill = new Skill(SkillEnum.CRAFT, "metalworking");
			String json = Utilities.toJson(qualifiedSkill);
			Skill qualifiedFromJson = Utilities.fromJson(json, Skill.class);
			if(!qualifiedSkill.equals(qualifiedFromJson) || !qualifiedFromJson.equals(qualifiedSkill))
			{
				//test fail!
				Log.e("IKRPG","Test fail!  qualifiedSkill JSON conversion failed\n"+qualifiedSkill.toString()+"\n"+qualifiedFromJson.toString());
			}
			else{Log.i("IKRPG","qualifiedSkill test passed!");}
		}
		catch(Exception e){Log.e("IKRPG","qualifiedSkill test exception");}
		
		//Assured inequality
		try
		{	
			Skill basicSkillA = SkillEnum.CLIMBING.make();
			Skill basicSkillB = SkillEnum.GAMBLING.make();
			
			Skill qualifiedSkillA = SkillEnum.CRAFT.make("metalworking");
			Skill qualifiedSkillB = SkillEnum.CRAFT.make("fishing");
			Skill qualifiedSkillC = SkillEnum.CLIMBING.make("fishing");
			
			Skill basicAFromJson = Utilities.fromJson(Utilities.toJson(basicSkillA), Skill.class);
			Skill basicBFromJson = Utilities.fromJson(Utilities.toJson(basicSkillB), Skill.class);
			Skill qualifiedAFromJson = Utilities.fromJson(Utilities.toJson(qualifiedSkillA), Skill.class);
			Skill qualifiedBFromJson = Utilities.fromJson(Utilities.toJson(qualifiedSkillB), Skill.class);
			Skill qualifiedCFromJson = Utilities.fromJson(Utilities.toJson(qualifiedSkillC), Skill.class);
			
			if(basicAFromJson.equals(basicBFromJson) || basicBFromJson.equals(basicAFromJson))
			{Log.e("IKRPG","Test fail!  basicSkills should not be equal");}
			
			else if(qualifiedAFromJson.equals(qualifiedBFromJson) || qualifiedBFromJson.equals(qualifiedAFromJson))
			{Log.e("IKRPG","Test fail!  qualifiedSkills of different qualifiers should not be equal");}
			
			else if(qualifiedBFromJson.equals(qualifiedCFromJson) || qualifiedCFromJson.equals(qualifiedBFromJson))
			{Log.e("IKRPG","Test fail!  qualifiedSkills of different skills should not be equal");}
			
			else if(basicAFromJson.equals(qualifiedCFromJson) || qualifiedCFromJson.equals(basicAFromJson))
			{Log.e("IKRPG","Test fail!  basicSkills should not equal qualifiedSkills");}
			
			else{Log.i("IKRPG","skill inequality test passed!");}
		}
		catch(Exception e){Log.e("IKRPG","skill inequality test exception");}
		
		return true;
	}
}
