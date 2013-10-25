package com.random.captain.ikrpg.character;

import java.util.*;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.google.gag.annotation.disclaimer.HandsOff;
import com.google.gag.enumeration.Consequence;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.random.captain.ikrpg.gear.LootPack;

class zzBaseCharacter implements Parcelable
{
	int index;
	
	/*Fluff*/
	Fluff fluff;	//character fluff
	
	/*Crunch*/
	Race race;						//race
	Archetype archetype;			//archetype
	GiftedTradition tradition;		//spellcasting tradition
	Set<Career> careers;			//all active careers
	Set<Ability> abilities;			//all known abilities
	Set<Spell> spells;				//all known spells
	Set<Connection> connections;	//all held connections
	Set<Language> languages;		//all known languages
	List<String> specials;			//miscellaneous special rules
	
	Map<Skill, Integer> baseSkills;					//base level of skills
	transient Map<Skill, Integer> activeSkills;		//active level of skills after adding modifiers
	Map<String, Modifier<Skill>> skillModifiers;	//active modifiers on skills
	
	transient Map<Stat, Integer> activeStats;		//active level of stats after adding modifiers
	Map<Stat, Integer> baseStats;					//base level of stats
	Map<Stat, Integer> maxStats;					//max level of stats
	Map<String, Modifier<Stat>> statModifiers;		//active modifiers on stats
	
	/*Gear*/
	LootPack lootPack;	//all held loot
	
	zzTier tier;		//current character tier
	int exp;			//current character exp
	
	zzBaseCharacter()
	{
		fluff = new Fluff();
		careers = new HashSet<Career>();
		abilities = new HashSet<Ability>();
		spells = new HashSet<Spell>();
		connections = new HashSet<Connection>();
		languages = new HashSet<Language>();
		baseSkills = new HashMap<Skill, Integer>();
		activeSkills = new HashMap<Skill, Integer>();
		skillModifiers = new HashMap<String, Modifier<Skill>>();
		activeStats = new HashMap<Stat, Integer>();
		baseStats = new HashMap<Stat, Integer>();
		maxStats = new HashMap<Stat, Integer>();
		statModifiers = new HashMap<String, Modifier<Stat>>();
		lootPack = new LootPack();
	}
	
	public Fluff fluff(){return fluff;} //It's editable on purpose... fluff can't affect anything.  Might change later just in case.
	public Race race(){return race;}
	public Archetype getArchetype(){return archetype;}
	public GiftedTradition getTradition(){return tradition;}
	public Set<Career> getCareers(){return new HashSet<Career>(careers);}
	public Set<Ability> getAbilities(){return new HashSet<Ability>(abilities);}
	public Set<Spell> getSpells(){return new HashSet<Spell>(spells);}
	public Set<Connection> getConnections(){return new HashSet<Connection>(connections);}
	public Set<Language> getLanguages(){return new HashSet<Language>(languages);}
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
	
	public boolean addSkillModifier(Modifier<Skill> modifier, String key)
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
	
	public boolean addStatModifier(Modifier<Stat> modifier, String key)
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
		Collection<Modifier<Skill>> m = skillModifiers.values();
		for(Modifier<Skill> modifier : m)
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
		for(Pair<Skill, Integer> skillPair : skillPairs)
		{baseSkills.put(skillPair.first, skillPair.second);}
		
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
		Collection<Modifier<Stat>> m = statModifiers.values();
		for(Modifier<Stat> modifier : m)
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
		//Index
		toParcel.writeInt(index);
		
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
		
		//Connections
		toParcel.writeParcelableArray(connections.toArray(new Connection[0]),0);
		
		//Languages
		int[] languageOrdinals = new int[languages.size()];index=0;
		for(Language s: languages){languageOrdinals[index++]=s.ordinal();}
		toParcel.writeInt(languages.size());
		toParcel.writeIntArray(languageOrdinals);
		
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
		
		//Gear
		toParcel.writeParcelable(lootPack,0);
	}
	
	public static final Parcelable.Creator<zzBaseCharacter> CREATOR = new Parcelable.Creator<zzBaseCharacter>()
	{
		@SuppressWarnings("unchecked")
		@Override
		public zzBaseCharacter createFromParcel(Parcel in)
		{
			zzBaseCharacter me = new zzBaseCharacter();
			
			//index;
			me.index = in.readInt();
			
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
			pAbilities.addAll((Collection<? extends Ability>) Arrays.asList(pAbilitiesArray));
			me.abilities = pAbilities;
			
			//Spells 
			int spellCount = in.readInt();
			int[] spellOrdinals = new int[spellCount]; in.readIntArray(spellOrdinals);
			Set<Spell> pSpells = new HashSet<Spell>(); Spell[] spells = Spell.values();
			for(int s:spellOrdinals)
			{pSpells.add(spells[s]);}
			me.spells = pSpells;
			
			//Connections
			Parcelable[] pConnectionsArray = in.readParcelableArray(Connection.class.getClassLoader());
			Set<Connection> pConnections = new HashSet<Connection>();
			pConnections.addAll((Collection<? extends Connection>) Arrays.asList(pConnectionsArray));
			me.connections = pConnections;
			
			//Languages
			int languageCount = in.readInt();
			int[] languageOrdinals = new int[languageCount]; in.readIntArray(languageOrdinals);
			Set<Language> pLanguages = new HashSet<Language>(); Language[] languages = Language.values();
			for(int s:languageOrdinals)
			{pLanguages.add(languages[s]);}
			me.languages = pLanguages;
			
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
			Map<String, Modifier<Skill>> pSkillModifiers = new HashMap<String, Modifier<Skill>>();
			int skillModifierCount = in.readInt();
			for(int i=0; i<skillModifierCount; i++)
			{
				String name = in.readString();
				Modifier<Skill> modifier= in.readParcelable(Modifier.class.getClassLoader());
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
			Map<String, Modifier<Stat>> pStatModifiers = new HashMap<String, Modifier<Stat>>();
			int statModifierCount = in.readInt();
			for(int i=0; i<statModifierCount; i++)
			{
				String name = in.readString();
				Modifier<Stat> modifier= in.readParcelable(Modifier.class.getClassLoader());
				pStatModifiers.put(name,modifier);
			}
			me.statModifiers = pStatModifiers;
			
			//EXP
			me.exp = in.readInt();
			me.tier = zzTier.getLevelForEXP(me.exp);
			
			//Gear
			me.lootPack = in.readParcelable(LootPack.class.getClassLoader());
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
		builder.registerTypeAdapter(new TypeToken<Map<String, Modifier<Skill>>>(){}.getType(), new SkillModifierMapSerializer());
		builder.registerTypeAdapter(new TypeToken<Map<String, Modifier<Stat>>>(){}.getType(), new StatModifierMapSerializer());
		Gson gson = builder.create();
		return gson.toJson(this);
	}

	public static zzBaseCharacter fromJson(String jsonString)
	{
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(new TypeToken<Map<Skill,Integer>>(){}.getType(), new SkillMapDeserializer());
		builder.registerTypeAdapter(new TypeToken<Map<Stat,Integer>>(){}.getType(), new StatMapDeserializer());
		builder.registerTypeAdapter(new TypeToken<Map<String, Modifier<Skill>>>(){}.getType(), new SkillModifierMapDeserializer());
		builder.registerTypeAdapter(new TypeToken<Map<String, Modifier<Stat>>>(){}.getType(), new StatModifierMapDeserializer());
		Gson gson = builder.create();
		zzBaseCharacter myChar = gson.fromJson(jsonString, zzBaseCharacter.class);
		
		//rederive
		myChar.deriveStats();
		myChar.deriveSkillCheckLevels();
		myChar.tier = zzTier.getLevelForEXP(myChar.exp);
		
		return myChar;
	}
}
