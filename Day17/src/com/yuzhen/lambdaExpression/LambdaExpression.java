package com.yuzhen.lambdaExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class LambdaExpression {
	public static void main(String[] args) {
		//0. use object (reference) directly 
		CodeToRun obj = new CodeToRun();
		new Thread(
					obj
				).start();
		
		
		// 1. use anonymous class 
		new Thread(
				new CodeToRun()
				).start();
		
		
		// 2. use the anonymous class to do that
		new Thread(
				new Runnable() {
					@Override
					public void run() {
						System.out.println("Implementing class object");
					}
				}
				).start();
		
		// 3. lambda expression 
		new Thread(
			()-> System.out.println("LambdaExpression!!")
				).start();
		
		// 4. lambda expression. multi lines statements 
		new Thread(
				()-> {
					System.out.println("Lambda Expression line 1");
					System.out.println("Lambda Expression line 2");
					System.out.println("Lambda Expresion line 3");
				}
				).start();
	}
	
	
	@Test 
	public void testLambda()
	{
		Employee john = new Employee("John Doe",30);
		Employee tim = new Employee("Tim Buchalka",21);
		Employee jack = new Employee("Jack Hill",40);
		Employee snow = new Employee("Snow White",22);
		
		List<Employee> employees = new ArrayList<>();
		employees.add(john);
		employees.add(tim);
		employees.add(jack);
		employees.add(snow);
		// implement 
		Collections.sort(employees, new Comparator<Employee>(){
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		// lambda expression way 
		Collections.sort(employees,
				(Employee o1 , Employee o2)->{
					return o1.getName().compareTo(o2.getName());
				}
				);
		// lambda expression way simplified
				Collections.sort(employees,
						(o1 , o2)->{
							return o1.getName().compareTo(o2.getName());
						}
						);
		
		for (Employee employee : employees) {
			System.out.println(employee.getName());
		}
	}
}

class CodeToRun implements Runnable {

	@Override
	public void run() {
		System.out.println("run() in CodeToRun class working !");
	}
}




class Employee {
	private String name;
	private int age;
	
	public Employee(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
