package com.random.captain.ikrpg.character;

public enum Language
{
	CASPIAN("Caspian"),
	CYGNARAN("Cygnaran"),
	SULESE("Sulese"),
	LLAELESE("Llaelese"),
	ORDIC("Ordic"),
	SCHARDE("Scharde"),
	KHURZIC("Khurzic"),
	KHADORAN("Khadoran"),
	KOSSITE("Kossite"),
	UMBREAN("Umbrean"),
	MOLGUR("Molgur"),
	GOBBERISH("Gobberish"),
	MOLGUR_OG("Molgur-Og"),
	MOLGUR_TRUL("Molgur-Trul"),
	MORRIDANE("Morridane"),
	IDRIAN("Idrian"),
	THURIAN("Thurian"),
	RHULIC("Rhulic"),
	SHYR("Shyr"),
	AERIC("Aeric");
	
	private Language(String pName)
	{
		displayName = pName;
	}
	
	private String displayName;
}
