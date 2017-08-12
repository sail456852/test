package com.yuzhen.test_inheritance;

import org.junit.Test;

public class World {
	// create a another object if this class 
	// is being called to build an object 
	
	@Test
	public void testMyWorld()
	{
		Son yuzhen = new Son();
		yuzhen.abfunc();
	}
}
