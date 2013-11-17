package com.random.captain.ikrpg.util;

import com.random.captain.ikrpg.character.*;

import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.random.captain.ikrpg.IKRPGApp;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Map;

public class Utilities
{
	private static String MY_KEY = "OhHelloIDidn'tSeeYouThere";
	private static String ID_KEY = "IAmASpanishGenius";
	
	public static int getAppID()
	{
		SharedPreferences sp = IKRPGApp.getContext().getSharedPreferences(MY_KEY, 0);
		int id = sp.getInt(ID_KEY, -1);
		if(id == -1)
		{
			id = (int)Math.floor(Math.random()*100000);
			SharedPreferences.Editor ed = sp.edit();
			ed.putInt(ID_KEY, id);
			ed.commit();
		}
		return id;
	}
	
	public static String hashToString(String pHash)
	{
		try
		{
			MessageDigest hasher = MessageDigest.getInstance("SHA-1");
			byte[] bytes = hasher.digest(pHash.getBytes("UTF-8"));
			
			StringBuilder sb = new StringBuilder();
			for (byte b : bytes)
			{sb.append(String.format("%02X",b));}
			
			return sb.toString();
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static int hashToInt(String pHash)
	{
		try
		{
			MessageDigest hasher = MessageDigest.getInstance("SHA-1");
			BigInteger b = new BigInteger(hasher.digest(pHash.getBytes("UTF-8")));
			return b.intValue();
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	
	public static <T> String toJson(T thing)
	{
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(new TypeToken<Map<Skill,Integer>>(){}.getType(), new Modifier.SkillMapSerializer());
		builder.registerTypeAdapter(new TypeToken<Map<Stat,Integer>>(){}.getType(), new Modifier.StatMapSerializer());
		builder.registerTypeAdapter(new TypeToken<Map<String, Modifier<Skill>>>(){}.getType(), new Modifier.SkillModifierMapSerializer());
		builder.registerTypeAdapter(new TypeToken<Map<String, Modifier<Stat>>>(){}.getType(), new Modifier.StatModifierMapSerializer());
		Gson gson = builder.create();
		return gson.toJson(thing);
	}

	public static <T> T fromJson(String jsonString, Class<T> pClass)
	{
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(new TypeToken<Map<Skill,Integer>>(){}.getType(), new Modifier.SkillMapDeserializer());
		builder.registerTypeAdapter(new TypeToken<Map<Stat,Integer>>(){}.getType(), new Modifier.StatMapDeserializer());
		builder.registerTypeAdapter(new TypeToken<Map<String, Modifier<Skill>>>(){}.getType(), new Modifier.SkillModifierMapDeserializer());
		builder.registerTypeAdapter(new TypeToken<Map<String, Modifier<Stat>>>(){}.getType(), new Modifier.StatModifierMapDeserializer());
		Gson gson = builder.create();
		return gson.fromJson(jsonString, pClass);
	}
}
