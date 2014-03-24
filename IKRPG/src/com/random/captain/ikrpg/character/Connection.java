package com.random.captain.ikrpg.character;


public class Connection
{
	String qualifier;
	
	public Connection(String pQualifier){qualifier = pQualifier;}
	
	public static Connection make(String pQualifier){return new Connection(pQualifier);}
}
