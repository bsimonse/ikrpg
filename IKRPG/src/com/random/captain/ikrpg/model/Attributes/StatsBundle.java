package com.random.captain.ikrpg.model.Attributes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StatsBundle
{
	private int phyBase, phyActive;
	private int spdBase, spdActive;
	private int strBase, strActive;
	private int agiBase, agiActive;
	private int poiBase, poiActive;
	private int prwBase, prwActive;
	private int intBase, intActive;
	private int arcBase, arcActive;
	private int perBase, perActive;
	private int defBase, defActive;
	private int initBase, initActive;
	private int armBase, armActive;
	private int wilBase, wilActive;
	private int atkActive;
	private int dmgActive;
	
	private Map<String, Modifier<Stats>> modifiers;
	
	public StatsBundle(int pPhy, int pSpd, int pStr, int pAgi, int pPoi, int pPrw, int pInt, int pArc, int pPer)
	{
		phyBase = pPhy;
		spdBase = pSpd;
		strBase = pStr;
		agiBase = pAgi;
		poiBase = pPoi;
		prwBase = pPrw;
		intBase = pInt;
		arcBase = pArc;
		perBase = pPer;
		
		defBase = spdBase + agiBase + perBase;
		initBase = spdBase + prwBase + perBase;
		armBase = phyBase;
		wilBase = phyBase + intBase;
		
		modifiers = new HashMap<String, Modifier<Stats>>();
		
		rederiveStats();
	}
	
	public int getPhysique(){return phyActive;}
	public int getSpeed(){return spdActive;}
	public int getStrength(){return strActive;}
	public int getPoise(){return poiActive;}
	public int getProwess(){return prwActive;}
	public int getAgility(){return agiActive;}
	public int getIntellect(){return intActive;}
	public int getArcane(){return arcActive;}
	public int getPerception(){return perActive;}
	public int getDefense(){return defActive;}
	public int getInitiative(){return initActive;}
	public int getArmor(){return armActive;}
	public int getWillpower(){return wilActive;}
	public int getAttack(){return atkActive;}
	public int getDamage(){return dmgActive;}
	
	public int getBasePhysique(){return phyBase;}
	public int getBaseSpeed(){return spdBase;}
	public int getBaseStrength(){return strBase;}
	public int getBasePoise(){return poiBase;}
	public int getBaseProwess(){return prwBase;}
	public int getBaseAgility(){return agiBase;}
	public int getBaseIntellect(){return intBase;}
	public int getBaseArcane(){return arcBase;}
	public int getBasePerception(){return perBase;}
	public int getBaseDefense(){return defBase;}
	public int getBaseInitiative(){return initBase;}
	public int getBaseArmor(){return armBase;}
	public int getBaseWillpower(){return wilBase;}
	
	public boolean addModifier(Modifier<Stats> modifier, String key)
	{
		if(modifiers.containsKey(key))
		{
			return false;
		}
		
		modifiers.put(key, modifier);
		rederiveStats();
		return true;
	}
	
	public boolean removeModifier(String key)
	{
		boolean result = (modifiers.remove(key) != null);
		if(result){rederiveStats();}
		return result;
	}
	
	private void rederiveStats()
	{
		//reset to base
		phyActive = phyBase;
		spdActive = spdBase;
		strActive = strBase;
		agiActive = agiBase;
		poiActive = poiBase;
		prwActive = prwBase;
		intActive = intBase;
		arcActive = arcBase;
		perActive = perBase;
		defActive = defBase;
		initActive = initBase;
		armActive = armBase;
		wilActive = wilBase;
		atkActive = dmgActive = 0;
		
		//apply modifiers
		Collection<Modifier<Stats>> m = modifiers.values();
		for(Modifier<Stats> modifier : m)
		{
			Stats stat = modifier.getTrait();
			switch(stat)
			{
				case PHYSIQUE: phyActive = modifier.modifiedValue(phyActive); break;
				case SPEED: spdActive = modifier.modifiedValue(spdActive); break;
				case STRENGTH: strActive = modifier.modifiedValue(strActive); break;
				case AGILITY: agiActive = modifier.modifiedValue(agiActive); break;
				case POISE: poiActive = modifier.modifiedValue(poiActive); break;
				case PROWESS: prwActive = modifier.modifiedValue(prwActive); break;
				case INTELLECT: intActive = modifier.modifiedValue(intActive); break;
				case ARCANE: arcActive = modifier.modifiedValue(arcActive);break;
				case PERCEPTION: perActive = modifier.modifiedValue(perActive);break;
				case DEFENSE: defActive = modifier.modifiedValue(defActive); break;
				case INITIATIVE: initActive = modifier.modifiedValue(initActive); break;
				case ARMOR: armActive = modifier.modifiedValue(armActive);break;
				case WILLPOWER: wilActive = modifier.modifiedValue(wilActive);break;
				case ATTACK: atkActive = modifier.modifiedValue(atkActive); break;
				case DAMAGE: dmgActive = modifier.modifiedValue(dmgActive); break;
				default: break;
			}
		}
	}
}
