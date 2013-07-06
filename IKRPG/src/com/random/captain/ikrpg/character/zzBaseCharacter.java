package com.random.captain.ikrpg.character;

import com.google.gson.*;
import java.util.*;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.google.gag.annotation.disclaimer.HandsOff;
import com.google.gag.enumeration.Consequence;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

class zzBaseCharacter implements Parcelable
{
	/*Fluff*/
	Fluff fluff;
	
	/*Crunch*/
	Race race;
	Archetype archetype;
	GiftedTradition tradition;
	Set<Career> careers;
	Set<Ability> abilities;
	Set<Spell> spells;
	
	Map<Skill, Integer> baseSkills;
	transient Map<Skill, Integer> activeSkills;
	Map<String, zzModifier<Skill>> skillModifiers;
	
	transient Map<Stat, Integer> activeStats;
	Map<Stat, Integer> baseStats;
	Map<Stat, Integer> maxStats;
	Map<String, zzModifier<Stat>> statModifiers;
	
	zzLevel level;
	int exp;
	
	zzBaseCharacter()
	{
		fluff = new Fluff();
		careers = new HashSet<Career>();
		abilities = new HashSet<Ability>();
		spells = new HashSet<Spell>();
		baseSkills = new HashMap<Skill, Integer>();
		activeSkills = new HashMap<Skill, Integer>();
		skillModifiers = new HashMap<String, zzModifier<Skill>>();
		activeStats = new HashMap<Stat, Integer>();
		baseStats = new HashMap<Stat, Integer>();
		maxStats = new HashMap<Stat, Integer>();
		statModifiers = new HashMap<String, zzModifier<Stat>>();
	}
	
	public Fluff fluff(){return fluff;} //It's editable on purpose... fluff can't affect anything.  Might change later just in case.
	public Race race(){return race;}
	public Archetype getArchetype(){return archetype;}
	public GiftedTradition getTradition(){return tradition;}
	public Set<Career> getCareers(){return new HashSet<Career>(careers);}
	public Set<Ability> getAbilities(){return new HashSet<Ability>(abilities);}
	public Set<Spell> getSpells(){return new HashSet<Spell>(spells);}
	public int exp(){return exp;}
	
	public void gainEXP(int pExpGain)
	{setEXP(exp+pExpGain);}
	
	/* Skills */
	public int getSkillBaseLevel(Skill skill){return getSkillBaseLevel(skill.skillEnum(), skill.qualifier());}
	public int getSkillBaseLevel(SkillEnum skill){return getSkillBaseLevel(skill,"");}
	public int getSkillBaseLevel(SkillEnum skill, String qualifier)
	{
		Integer value = baseSkills.get(skill.make(qualifier));
		return (value == null ? 0 : value);
	}
	
	public int getSkillActiveLevel(Skill skill){return getSkillActiveLevel(skill.skillEnum(), skill.qualifier());}
	public int getSkillActiveLevel(SkillEnum skill){return getSkillActiveLevel(skill,"");}
	public int getSkillActiveLevel(SkillEnum skill, String qualifier)
	{
		Integer value = activeSkills.get(skill.make(qualifier));
		return (value == null ? 0 : value);
	}
	
	public int getSkillCheckLevel(Skill skill){return getSkillCheckLevel(skill.skillEnum(), skill.qualifier());}
	public int getSkillCheckLevel(SkillEnum skill){return getSkillCheckLevel(skill,"");}
	public int getSkillCheckLevel(SkillEnum skill, String qualifier){return getSkillActiveLevel(skill,qualifier) + getActiveStat(skill.governingStat());}
	
	public Set<Pair<Skill, Integer>> getTrainedSkills()
	{
		Set<Pair<Skill, Integer>> skills = new HashSet<Pair<Skill, Integer>>(20);

		for(Skill skill : baseSkills.keySet())
		{
			int skillLevel = baseSkills.get(skill);
			if(skillLevel > 0)
			{
				Pair<Skill, Integer> aPair = new Pair<Skill, Integer>(skill, skillLevel);
				skills.add(aPair);
			}
		}

		return skills;
	}
	
	public boolean addSkillModifier(zzModifier<Skill> modifier, String key)
	{
		if(skillModifiers.containsKey(key))
		{return false;}

		skillModifiers.put(key, modifier);
		deriveSkillCheckLevels();
		return true;
	}

	public boolean removeSkillModifier(String key)
	{
		boolean result = (skillModifiers.remove(key) != null);
		if(result){deriveSkillCheckLevels();}
		return result;
	}
	
	/* Stats */
	public int getBaseStat(Stat stat)
	{
		Integer value = baseStats.get(stat);
		return value == null ? 0 : value;
	}
	
	public int getMaxStat(Stat stat)
	{
		Integer value = maxStats.get(stat);
		return value == null ? 0 : value;
	}
	
	public int getActiveStat(Stat stat)
	{
		Integer value = activeStats.get(stat);
		return value == null ? 0 : value;
	}
	
	public boolean addStatModifier(zzModifier<Stat> modifier, String key)
	{
		if(statModifiers.containsKey(key))
		{return false;}

		statModifiers.put(key, modifier);
		deriveActiveStats();
		deriveSkillCheckLevels();
		return true;
	}

	public boolean removeStatModifier(String key)
	{
		boolean result = (statModifiers.remove(key) != null);
		if(result){deriveActiveStats();deriveSkillCheckLevels();}
		return result;
	}
	
	/* Abilities */
	public boolean hasAbility(AbilityEnum ability){return abilities.contains(new Ability(ability));}
	public boolean hasAbility(AbilityEnum ability, String qualifier){return abilities.contains(new Ability(ability, qualifier));}

	/* Hidden methods */
	@HandsOff(
		byOrderOf = "Me",
		onPainOf = Consequence.PAPER_CUT)
	void deriveSkillCheckLevels()
	{
		//reset to base
		activeSkills.clear();
		activeSkills.putAll(baseSkills);

		//apply modifiers
		Collection<zzModifier<Skill>> m = skillModifiers.values();
		for(zzModifier<Skill> modifier : m)
		{
			Skill skill = modifier.trait;
			Integer value = activeSkills.get(skill);
			value = modifier.modifiedValue(value);
			activeSkills.put(skill, value);
		}
	} 
	
	void setEXP(int pExpTotal)
	{
		//determine bonuses gained
		//TODO: add bonuses

		exp = pExpTotal;
	}
	
	void setBaseSkills(Map<Skill, Integer> pBaseSkills)
	{
		baseSkills = pBaseSkills != null ? pBaseSkills : new HashMap<Skill,Integer>(10);
		deriveSkillCheckLevels();
	}

	void setBaseSkills(Set<Career> pCareers)
	{
		baseSkills = new HashMap<Skill, Integer>();

		//get starting skills
		if(pCareers != null)
		{
			for(Career career : pCareers)
			{
				Collection<Pair<Skill, Integer>> toIncrement = career.startingSkills();
				for(Pair<Skill, Integer> pair : toIncrement)
				{
					if(baseSkills.containsKey(pair.first))
					{
						//skill already trained; increment
						int currentVal = baseSkills.get(pair.first);
						currentVal += pair.second;
						baseSkills.put(pair.first, currentVal);
					}
					else
					{
						//skill untrained; set
						baseSkills.put(pair.first, pair.second);
					}
				}
			}
		}

		deriveSkillCheckLevels();
	}

	void setSkillLevel(Skill skill, int value)
	{
		baseSkills.put(skill, value);
		deriveSkillCheckLevels();
	}

	void setSkillLevels(Collection<Pair<Skill, Integer>> skillPairs)
	{
		for(Pair skillPair : skillPairs)
		{baseSkills.put((Skill)skillPair.first, (Integer)skillPair.second);}
		
		deriveSkillCheckLevels();
	}
	
	void setBaseStat(Stat stat, int value)
	{
		baseStats.put(stat, value);
		deriveStats();
		deriveSkillCheckLevels();
	}
	
	void setMaxStat(Stat stat, int value)
	{
		maxStats.put(stat, value);
		//deriveSkillCheckLevels();
	}
	
	protected void deriveStats()
	{
		int pPhy = clean(baseStats.get(Stat.PHYSIQUE));
		int pSpd = clean(baseStats.get(Stat.SPEED));
		int pAgi = clean(baseStats.get(Stat.AGILITY));
		int pPrw = clean(baseStats.get(Stat.PROWESS));
		int pPer = clean(baseStats.get(Stat.PERCEPTION));
		int pInt = clean(baseStats.get(Stat.INTELLECT));
		int pArc = clean(baseStats.get(Stat.ARCANE));
		
		baseStats.put(Stat.DEFENSE, pSpd+pAgi+pPer);
		baseStats.put(Stat.INITIATIVE,pSpd+pPrw+pPer);
		baseStats.put(Stat.ARMOR, pPhy);
		baseStats.put(Stat.WILLPOWER, pPhy+pInt);
		baseStats.put(Stat.COMMAND, pInt);
		baseStats.put(Stat.CONTROL, 2*pArc);
		baseStats.put(Stat.MELEE_ATTACK, 0);
		baseStats.put(Stat.MELEE_DAMAGE, 0);
		baseStats.put(Stat.RANGED_ATTACK, 0);
		baseStats.put(Stat.RANGED_DAMAGE, 0);

		deriveActiveStats();
	}

	private int clean(Integer x)
	{if(x == null){return 0;}return x.intValue();}
	
	protected void deriveActiveStats()
	{
		//reset to base
		activeStats.clear();
		activeStats.putAll(baseStats);

		//apply modifiers
		Collection<zzModifier<Stat>> m = statModifiers.values();
		for(zzModifier<Stat> modifier : m)
		{
			Stat stat = modifier.trait;
			Integer value = activeStats.get(stat);
			value = modifier.modifiedValue(value);
			activeStats.put(stat, value);
		}
	}
	
	/* Parcelling */
	@Override
	public void writeToParcel(Parcel toParcel, int flags)
	{
		//Fluff, race, archetype
		toParcel.writeParcelable(fluff, 0);
		toParcel.writeSerializable(race);
		toParcel.writeSerializable(archetype);
		
		//Careers
		int[] careerOrdinals = new int[careers.size()];int index=0;
		for(Career c: careers){careerOrdinals[index++]=c.ordinal();}
		toParcel.writeInt(careers.size());
		toParcel.writeIntArray(careerOrdinals);
		
		//Abilities
		toParcel.writeParcelableArray(abilities.toArray(new Ability[0]), 0);
		
		//Spells
		int[] spellOrdinals = new int[spells.size()];index=0;
		for(Spell s: spells){spellOrdinals[index++]=s.ordinal();}
		toParcel.writeInt(spells.size());
		toParcel.writeIntArray(spellOrdinals);
		
		//Base Skills
		toParcel.writeInt(baseSkills.keySet().size());
		for(Skill skill : baseSkills.keySet())
		{
			toParcel.writeParcelable(skill,0);
			toParcel.writeInt(baseSkills.get(skill));
		}
		
		//Skill modifiers
		toParcel.writeInt(skillModifiers.keySet().size());
		for(String skillName : skillModifiers.keySet())
		{
			toParcel.writeString(skillName);
			toParcel.writeParcelable(skillModifiers.get(skillName),0);
		}

		//Base stats
		toParcel.writeInt(baseStats.keySet().size());
		for(Stat stat : baseStats.keySet())
		{
			toParcel.writeParcelable(stat,0);
			toParcel.writeInt(baseStats.get(stat));
		}
		
		//Max stats
		toParcel.writeInt(maxStats.keySet().size());
		for(Stat stat : maxStats.keySet())
		{
			toParcel.writeParcelable(stat,0);
			toParcel.writeInt(maxStats.get(stat));
		}
		
		//Stat modifiers
		toParcel.writeInt(statModifiers.keySet().size());
		for(String statName : statModifiers.keySet())
		{
			toParcel.writeString(statName);
			toParcel.writeParcelable(statModifiers.get(statName),0);
		}
		
		//Exp
		toParcel.writeInt(exp);
	}
	
	public static final Parcelable.Creator<zzBaseCharacter> CREATOR = new Parcelable.Creator<zzBaseCharacter>()
	{
		@Override
		public zzBaseCharacter createFromParcel(Parcel in)
		{
			zzBaseCharacter me = new zzBaseCharacter();
			//fluff, race, archetype
			me.fluff = in.readParcelable(Fluff.class.getClassLoader());
			me.race = (Race)in.readSerializable();
			me.archetype = (Archetype)in.readSerializable();
			
			//careers
			int careerCount = in.readInt();
			int[] careerOrdinals = new int[careerCount]; in.readIntArray(careerOrdinals);
			Set<Career> pCareers = new HashSet<Career>(); Career[] careers = Career.values();
			for(int c:careerOrdinals)
			{pCareers.add(careers[c]);}
			me.careers = pCareers;
			
			//Abilities
			Parcelable[] pAbilitiesArray = in.readParcelableArray(Ability.class.getClassLoader());
			Set<Ability> pAbilities = new HashSet<Ability>();
			pAbilities.addAll((List<Ability>)Arrays.asList(pAbilitiesArray));
			me.abilities = pAbilities;
			
			//Spells 
			int spellCount = in.readInt();
			int[] spellOrdinals = new int[spellCount]; in.readIntArray(spellOrdinals);
			Set<Spell> pSpells = new HashSet<Spell>(); Spell[] spells = Spell.values();
			for(int s:spellOrdinals)
			{pSpells.add(spells[s]);}
			me.spells = pSpells;
			
			//Base skills
			Map<Skill,Integer> pSkills = new HashMap<Skill,Integer>();
			int skillCount = in.readInt();
			for(int i=0; i<skillCount; i++)
			{
				Skill skill = in.readParcelable(Skill.class.getClassLoader());
				int level = in.readInt();
				pSkills.put(skill,level);
			}
			me.baseSkills=pSkills;
			
			//Skill modifiers
			Map<String, zzModifier<Skill>> pSkillModifiers = new HashMap<String, zzModifier<Skill>>();
			int skillModifierCount = in.readInt();
			for(int i=0; i<skillModifierCount; i++)
			{
				String name = in.readString();
				zzModifier<Skill> modifier= in.readParcelable(zzModifier.class.getClassLoader());
				pSkillModifiers.put(name,modifier);
			}
			me.skillModifiers = pSkillModifiers;
			
			//Base stats
			Map<Stat,Integer> pBaseStats = new HashMap<Stat,Integer>();
			int statCount = in.readInt();
			for(int i=0; i<statCount; i++)
			{
				Stat stat = in.readParcelable(Stat.class.getClassLoader());
				int level = in.readInt();
				pBaseStats.put(stat,level);
			}
			me.baseStats = pBaseStats;
			
			//Max stats
			Map<Stat,Integer> pMaxStats = new HashMap<Stat,Integer>();
			statCount = in.readInt();
			for(int i=0; i<statCount; i++)
			{
				Stat stat = in.readParcelable(Stat.class.getClassLoader());
				int level = in.readInt();
				pMaxStats.put(stat,level);
			}
			me.maxStats=pMaxStats;
			
			//Stat modifiers
			Map<String, zzModifier<Stat>> pStatModifiers = new HashMap<String, zzModifier<Stat>>();
			int statModifierCount = in.readInt();
			for(int i=0; i<statModifierCount; i++)
			{
				String name = in.readString();
				zzModifier<Stat> modifier= in.readParcelable(zzModifier.class.getClassLoader());
				pStatModifiers.put(name,modifier);
			}
			me.statModifiers = pStatModifiers;
			
			me.exp = in.readInt();
			me.level = zzLevel.getLevelForEXP(me.exp);
			
			return me;
		}

		@Override public zzBaseCharacter[] newArray(int size) {return new zzBaseCharacter[size];}
	};
	
	@Override public int describeContents(){return 0;}
	
	@Override
	public String toString()
	{return fluff.name;}
	
	public String toFullString()
	{
		StringBuilder myString = new StringBuilder(100);
		
		myString.append(fluff.toString()+"\n");
		
		myString.append(archetype.displayName()+" "+race.displayName());
		myString.append("\n");
		
		myString.append("EXP: "+exp+"\n");
		
		myString.append("\nCareers: \n");
		for(Career career: careers)
		{myString.append(career.displayName()).append("\n");}

		myString.append("\nStats:\n");
		for(Stat stat : baseStats.keySet())
		{myString.append(stat.toString()+": "+baseStats.get(stat)+"\n");}
		
		myString.append("\nSkills:\n");
		for(Skill skill : baseSkills.keySet())
		{myString.append(skill.toString()+": "+baseSkills.get(skill)+"\n");}
		
		myString.append("\nAbilities: \n");
		for(Ability ability: abilities)
		{myString.append(ability.toString()).append("\n");}
		
		myString.append("\nSpells: \n");
		for(Spell spell: spells)
		{myString.append(spell.displayName()).append("\n");}
		
		return myString.toString();
	}
	
	public String toJson()
	{
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(new TypeToken<Map<Skill,Integer>>(){}.getType(), new SkillMapSerializer());
		builder.registerTypeAdapter(new TypeToken<Map<Stat,Integer>>(){}.getType(), new StatMapSerializer());
		builder.registerTypeAdapter(new TypeToken<Map<String, zzModifier<Skill>>>(){}.getType(), new SkillModifierMapSerializer());
		builder.registerTypeAdapter(new TypeToken<Map<String, zzModifier<Stat>>>(){}.getType(), new StatModifierMapSerializer());
		Gson gson = builder.create();
		return gson.toJson(this);
	}

	public static zzBaseCharacter fromJson(String jsonString)
	{
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(new TypeToken<Map<Skill,Integer>>(){}.getType(), new SkillMapDeserializer());
		builder.registerTypeAdapter(new TypeToken<Map<Stat,Integer>>(){}.getType(), new StatMapDeserializer());
		builder.registerTypeAdapter(new TypeToken<Map<String, zzModifier<Skill>>>(){}.getType(), new SkillModifierMapDeserializer());
		builder.registerTypeAdapter(new TypeToken<Map<String, zzModifier<Stat>>>(){}.getType(), new StatModifierMapDeserializer());
		Gson gson = builder.create();
		zzBaseCharacter myChar = gson.fromJson(jsonString, zzBaseCharacter.class);
		
		//rederive
		myChar.deriveStats();
		myChar.deriveSkillCheckLevels();
		myChar.level = zzLevel.getLevelForEXP(myChar.exp);
		
		return myChar;
	}
}
	
class SkillMapSerializer implements JsonSerializer<Map<Skill, Integer>>
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
	
class SkillMapDeserializer implements JsonDeserializer<Map<Skill,Integer>>
{
	@Override
	public Map<Skill,Integer> deserialize(JsonElement pJson, Type pType, JsonDeserializationContext pContext)
	{
		Map<Skill,Integer> skillMap = new HashMap<Skill,Integer>();
		JsonArray array = (JsonArray)pJson;
		for(JsonObject skillJson : array)
		{
			SkillEnum skill = SkillEnum.values()[skillJson.get("skillOrdinal").getAsInt()];
			skillMap.put(new Skill(skill, skillJson.get("skillQualifier").getAsString()),skillJson.get("level").getAsInt());
		}
		
		return skillMap;
	}
}
	
class StatMapSerializer implements JsonSerializer<Map<Stat, Integer>>
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

class StatMapDeserializer implements JsonDeserializer<Map<Stat,Integer>>
{
	@Override
	public Map<Stat,Integer> deserialize(JsonElement pJson, Type pType, JsonDeserializationContext pContext)
	{
		Map<Stat,Integer> statMap = new HashMap<Stat,Integer>();
		JsonArray array = (JsonArray)pJson;
		for(JsonObject statJson : array)
		{
			statMap.put(Stat.values()[statJson.get("statOrdinal").getAsInt()],statJson.get("level").getAsInt());
		}

		return statMap;
	}
}

class SkillModifierMapSerializer implements JsonSerializer<Map<String, zzModifier<Skill>>>
{
	@Override
	public JsonElement serialize(Map<String, zzModifier<Skill>> pMods, Type pType, JsonSerializationContext pContext)
	{
		JsonArray array = new JsonArray();
		JsonObject obj;
		for(String modName : pMods.keySet())
		{
			zzModifier<Skill> modifier = pMods.get(modName);
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

class SkillModifierMapDeserializer implements JsonDeserializer<Map<String, zzModifier<Skill>>>
{
	@Override
	public Map<String, zzModifier<Skill>> deserialize(JsonElement pJson, Type pType, JsonDeserializationContext pContext)
	{
		Map<String, zzModifier<Skill>> modMap = new HashMap<String, zzModifier<Skill>>();
		JsonArray array = (JsonArray)pJson;
		for(JsonObject modJson : array)
		{
			SkillEnum se = SkillEnum.values()[modJson.get("modifierSkillOrdinal").getAsInt()];
			Skill s = new Skill(se, modJson.get("modifierSkillQualifier").getAsString());
			zzModifier<Skill> myMod = new zzModifier<Skill>(s, modJson.get("value").getAsInt());
			modMap.put(modJson.get("modifierName").getAsString(),myMod);
		}

		return modMap;
	}
}
	
class StatModifierMapSerializer implements JsonSerializer<Map<String, zzModifier<Stat>>>
{
	@Override
	public JsonElement serialize(Map<String, zzModifier<Stat>> pMods, Type pType, JsonSerializationContext pContext)
	{
		JsonArray array = new JsonArray();
		JsonObject obj;
		for(String modName : pMods.keySet())
		{
			zzModifier<Stat> modifier = pMods.get(modName);
			obj = new JsonObject();
			obj.addProperty("modifierName",modName);
			obj.addProperty("modifierStatOrdinal",modifier.trait.ordinal());
			obj.addProperty("value",modifier.value);
			array.add(obj);
		}

		return array;
	}
}

class StatModifierMapDeserializer implements JsonDeserializer<Map<String, zzModifier<Stat>>>
{
	@Override
	public Map<String, zzModifier<Stat>> deserialize(JsonElement pJson, Type pType, JsonDeserializationContext pContext)
	{
		Map<String, zzModifier<Stat>> modMap = new HashMap<String, zzModifier<Stat>>();
		JsonArray array = (JsonArray)pJson;
		for(JsonObject modJson : array)
		{
			Stat stat = Stat.values()[modJson.get("modifierStatOrdinal").getAsInt()];
			zzModifier<Stat> myMod = new zzModifier<Stat>(stat, modJson.get("value").getAsInt());
			modMap.put(modJson.get("modifierName").getAsString(),myMod);
		}

		return modMap;
	}
}
