package com.yuzhen.test_inheritance;
/**
 * This is the father class
 */
public abstract class Father {
	public void speak()
	{
		System.out.println("Hey I am the father! good to see you");
	}
	
	public void run(){
		System.out.println("Father: I can run 80 miles per hour");
	}
	/**
	 *  father private function
	 */
	private void sex()
	{
		System.out.println("my favorite AV idol is Hanato_Yui");
	}
	
	
	protected void inherited()
	{
		System.out.println("You are my true heir!");
	}
	
	// abstract function here 
	abstract public void abfunc();
	
}
