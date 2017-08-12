package com.yuzhen.test_inheritance;

public class Son extends Father{
	

	@Override
	public void abfunc() {
		System.out.println("overriding the father's abstract function");
	}
}
