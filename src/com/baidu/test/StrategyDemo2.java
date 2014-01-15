package com.baidu.test;

public class StrategyDemo2 {
	public static void main(String[] args) {
		Person2 person2 = new Person2();
		person2.goToWork(new BusTripModel());
		person2.goToWork(new CarTripModel());
		person2.goToWork(new TrainTripModel());
	}	
}



interface TripModel {
	public String getModel();
}

class BusTripModel implements TripModel {

	@Override
	public String getModel() {
		// TODO Auto-generated method stub
		return "bus";
	}
	
}

class CarTripModel implements TripModel {
	
	@Override
	public String getModel() {
		// TODO Auto-generated method stub
		return "car";
	}
	
}

class TrainTripModel implements TripModel {
	
	@Override
	public String getModel() {
		// TODO Auto-generated method stub
		return "train";
	}
	
}

class Person2 {
	public void goToWork(TripModel tripModel) {
		System.out.println("go to work by " + tripModel.getModel());
	}
}


