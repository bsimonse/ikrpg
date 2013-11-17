package com.random.captain.ikrpg.character;

import com.google.gson.*;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.random.captain.ikrpg.character.Skill;
import com.random.captain.ikrpg.character.Stat;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Modifier<S extends Parcelable> implements Parcelable
{
	int value;
	S trait;
	Class<S> genericClass;
	
	public Modifier (S pTrait){this(pTrait, 0);}	
	@SuppressWarnings("unchecked")
	public Modifier(S pTrait, int pValue)
	{
		trait = pTrait;
		value = pValue;
		genericClass = (Class<S>)trait.getClass();
	}
	
	//future improvement will allow for setting here, rather than incrementing
	int modifiedValue(int base){return base + value;}
	
	@Override
	public String toString()
	{return trait.toString()+": "+value;}
	
	/* Parcelling */
	public void writeToParcel(Parcel toParcel, int flags)
	{
		toParcel.writeSerializable(genericClass);
		toParcel.writeInt(value);
		toParcel.writeParcelable(trait, 0);
	}

	public static final Parcelable.Creator<Modifier<?>> CREATOR = new Parcelable.Creator<Modifier<?>>()
	{
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Modifier<?> createFromParcel(Parcel in)
		{
			try
			{
				Class<? extends Parcelable> which = (Class<? extends Parcelable>)in.readSerializable();
				int value = in.readInt();
				Parcelable trait = in.readParcelable(which.getClassLoader());
				return new Modifier(trait, value);
			}
			catch(Exception e)
			{
				Log.e("IKRPG","Bad news, dude, Modifier didn't Parcel correctly!");
				return null;
			}
		}

		@Override public Modifier<?>[] newArray(int size){return new Modifier<?>[size];}
	};

	@Override public int describeContents() {return 0;}
	
	public static class SkillMapSerializer implements JsonSerializer<Map<Skill, Integer>>
	{
		@Override
		public JsonElement serialize(Map<Skill, Integer> pSkills, Type pType, JsonSerializationContext pContext)
		{
			JsonArray array = new JsonArray();
			JsonObject obj;
			for(Skill skill : pSkills.keySet())
			{
				obj = new JsonObject();
				obj.addProperty("skillOrdinal",skill.skillEnum().ordinal());
				obj.addProperty("skillQualifier",skill.qualifier());
				obj.addProperty("level",pSkills.get(skill));
				array.add(obj);
			}
	
			return array;
		}
	}
	
	public static class SkillMapDeserializer implements JsonDeserializer<Map<Skill,Integer>>
	{
		@Override
		public Map<Skill,Integer> deserialize(JsonElement pJson, Type pType, JsonDeserializationContext pContext)
		{
			Map<Skill,Integer> skillMap = new HashMap<Skill,Integer>();
			JsonArray array = (JsonArray)pJson;
			for(JsonElement skillJsonE : array)
			{
				JsonObject skillJson = (JsonObject) skillJsonE;
				SkillEnum skill = SkillEnum.values()[skillJson.get("skillOrdinal").getAsInt()];
				skillMap.put(new Skill(skill, skillJson.get("skillQualifier").getAsString()),skillJson.get("level").getAsInt());
			}
	
			return skillMap;
		}
	}

	public static class StatMapSerializer implements JsonSerializer<Map<Stat, Integer>>
	{
		@Override
		public JsonElement serialize(Map<Stat, Integer> pStats, Type pType, JsonSerializationContext pContext)
		{
			JsonArray array = new JsonArray();
			JsonObject obj;
			for(Stat stat : pStats.keySet())
			{
				obj = new JsonObject();
				obj.addProperty("statOrdinal",stat.ordinal());
				obj.addProperty("level",pStats.get(stat));
				array.add(obj);
			}
	
			return array;
		}
	}
	
	public static class StatMapDeserializer implements JsonDeserializer<Map<Stat,Integer>>
	{
		@Override
		public Map<Stat,Integer> deserialize(JsonElement pJson, Type pType, JsonDeserializationContext pContext)
		{
			Map<Stat,Integer> statMap = new HashMap<Stat,Integer>();
			JsonArray array = (JsonArray)pJson;
			for(JsonElement statJsonE : array)
			{
				JsonObject statJson = (JsonObject) statJsonE;
				statMap.put(Stat.values()[statJson.get("statOrdinal").getAsInt()],statJson.get("level").getAsInt());
			}
	
			return statMap;
		}
	}
	
	public static class SkillModifierMapSerializer implements JsonSerializer<Map<String, Modifier<Skill>>>
	{
		@Override
		public JsonElement serialize(Map<String, Modifier<Skill>> pMods, Type pType, JsonSerializationContext pContext)
		{
			JsonArray array = new JsonArray();
			JsonObject obj;
			for(String modName : pMods.keySet())
			{
				Modifier<Skill> modifier = pMods.get(modName);
				obj = new JsonObject();
				obj.addProperty("modifierName",modName);
				obj.addProperty("modifierSkillOrdinal",modifier.trait.skillEnum().ordinal());
				obj.addProperty("modifierSkillQualifier",modifier.trait.qualifier());
				obj.addProperty("value",modifier.value);
				array.add(obj);
			}
	
			return array;
		}
	}

	public static class SkillModifierMapDeserializer implements JsonDeserializer<Map<String, Modifier<Skill>>>
	{
		@Override
		public Map<String, Modifier<Skill>> deserialize(JsonElement pJson, Type pType, JsonDeserializationContext pContext)
		{
			Map<String, Modifier<Skill>> modMap = new HashMap<String, Modifier<Skill>>();
			JsonArray array = (JsonArray)pJson;
			for(JsonElement modJsonE : array)
			{
				JsonObject modJson = (JsonObject) modJsonE;
				SkillEnum se = SkillEnum.values()[modJson.get("modifierSkillOrdinal").getAsInt()];
				Skill s = new Skill(se, modJson.get("modifierSkillQualifier").getAsString());
				Modifier<Skill> myMod = new Modifier<Skill>(s, modJson.get("value").getAsInt());
				modMap.put(modJson.get("modifierName").getAsString(),myMod);
			}
	
			return modMap;
		}
	}

	public static class StatModifierMapSerializer implements JsonSerializer<Map<String, Modifier<Stat>>>
	{
		@Override
		public JsonElement serialize(Map<String, Modifier<Stat>> pMods, Type pType, JsonSerializationContext pContext)
		{
			JsonArray array = new JsonArray();
			JsonObject obj;
			for(String modName : pMods.keySet())
			{
				Modifier<Stat> modifier = pMods.get(modName);
				obj = new JsonObject();
				obj.addProperty("modifierName",modName);
				obj.addProperty("modifierStatOrdinal",modifier.trait.ordinal());
				obj.addProperty("value",modifier.value);
				array.add(obj);
			}
	
			return array;
		}
	}

	public static class StatModifierMapDeserializer implements JsonDeserializer<Map<String, Modifier<Stat>>>
	{
		@Override
		public Map<String, Modifier<Stat>> deserialize(JsonElement pJson, Type pType, JsonDeserializationContext pContext)
		{
			Map<String, Modifier<Stat>> modMap = new HashMap<String, Modifier<Stat>>();
			JsonArray array = (JsonArray)pJson;
			for(JsonElement modJsonE : array)
			{
				JsonObject modJson = (JsonObject)modJsonE;
				Stat stat = Stat.values()[modJson.get("modifierStatOrdinal").getAsInt()];
				Modifier<Stat> myMod = new Modifier<Stat>(stat, modJson.get("value").getAsInt());
				modMap.put(modJson.get("modifierName").getAsString(),myMod);
			}
	
			return modMap;
		}
	}
}
