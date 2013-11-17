package com.random.captain.ikrpg.test;

public class TestSuite
{
	//A 'rachet' test suite (I'm told that's a thing.)
	public static boolean runSuite()
	{
		return SkillTest.runTests() && AbilityTest.runTests() && CharacterBondTest.runTests();
	}
}
