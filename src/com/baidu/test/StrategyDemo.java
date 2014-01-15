package com.baidu.test;

public class StrategyDemo {
	public static void main(String[] args) {
		Person person = new Person();
		person.goToWork("bus");
		person.goToWork("car");
		person.goToWork("train");
	}	
}



class Person {
	public void goToWork(String tripModel) {
		if("bus".equalsIgnoreCase(tripModel)) {
			System.out.println("go to work by bus");
		} else if("car".equalsIgnoreCase(tripModel)) {
			System.out.println("go to work by car");
		} else if("train".equalsIgnoreCase(tripModel)) {
			System.out.println("go to work by train");
		}
	}
}


