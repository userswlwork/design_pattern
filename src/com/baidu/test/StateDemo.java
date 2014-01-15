package com.baidu.test;

public class StateDemo {
	public static void main(String[] args) {
		
		GumballMachine2 gumballMachine = new GumballMachine2(5);
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		
	}
}

class GumballMachine {
	
	final static int SOLD_OUT = 0;
	final static int NO_QUARTER = 1;
	final static int HAS_QUARTER = 2;
	final static int SOLD = 3;
	
	private int state = SOLD_OUT;
	int count = 0;
	
	public GumballMachine(int count) {
		this.count = count;
		if(this.count > 0) {
			state = NO_QUARTER;
		}
	}
	
	public void insertQuarter() {
		if(state == HAS_QUARTER) {
			System.out.println("You can't insert another quarter");
		} else if(state == NO_QUARTER) {
			state = NO_QUARTER;
			System.out.println("You inserted a quarter");			
		} else if(state == SOLD_OUT) {
			System.out.println("You can't insert a quarter, the machine is sold out");			
		} else if(state == SOLD) {
			System.out.println("Please wait, we're already giving you a gumball");			
		}
	}
	
	public void ejectQuarter() {
		if(state == HAS_QUARTER) {
			state = NO_QUARTER;
			System.out.println("Quarter returned");
		} else if(state == NO_QUARTER) {
			System.out.println("You haven't inserted a quarter");			
		} else if(state == SOLD_OUT) {
			System.out.println("Sorry, you already turned the crank");			
		} else if(state == SOLD) {
			System.out.println("You can't eject, you haven't inserted a quarter yet");			
		}
	}
	
	public void turnCrank() {
		if(state == HAS_QUARTER) {
			System.out.println("You truned ...");
			state = SOLD;
		} else if(state == NO_QUARTER) {
			System.out.println("You turned but there's no quarter");			
		} else if(state == SOLD_OUT) {
			System.out.println("You turned but there are no gumballs");			
		} else if(state == SOLD) {
			System.out.println("Truning twice doesn't get you another gumball");			
		}
	}
	
	public void dispense() {
		if(state == HAS_QUARTER) {
			System.out.println("No gumball dispensed");
			state = SOLD;
		} else if(state == NO_QUARTER) {
			System.out.println("You need to pay first");			
		} else if(state == SOLD_OUT) {
			System.out.println("No gumball dispensed");			
		} else if(state == SOLD) {
			System.out.println("A gumball comes rolling out the slot");
			count = count -1;
			if(count == 0) {
				System.out.println("out of gumballs");
				state = SOLD_OUT;
			} else {
				state = NO_QUARTER;
			}
		}
	}
	
}


class GumballMachine2 {

	State soldOutState;
	State noQuarterState;
	State hasQuarterState;
	State soldState;
	
	private State state = soldOutState;
	int count = 0;
	
	public GumballMachine2(int count) {
		this.count = count;
		if(this.count > 0) {
			state = noQuarterState;
		}
	}
	
	public void insertQuarter() {
		state.insertQuarter();
	}
	
	public void ejectQuarter() {
		state.ejectQuarter();
	}
	
	public void turnCrank() {
		state.turnCrank();
		state.dispense();
	}

	public int getCount() {
		return count;
	}
	
	public void releaseBall() {
		System.out.println("A gumball comes rolling out the slot...");
		if(count != 0) {
			count = count - 1;
		}
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public State getSoldOutState() {
		return soldOutState;
	}

	public State getNoQuarterState() {
		return noQuarterState;
	}
	
	public State getHasQuarterState() {
		return hasQuarterState;
	}

	public State getSoldState() {
		return soldState;
	}

}

interface State {
	public void insertQuarter();
	public void ejectQuarter();
	public void turnCrank();
	public void dispense();
}

class HasQuarterState implements State {
	
	GumballMachine2 gumballMachine;
	
	HasQuarterState(GumballMachine2 gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	public void insertQuarter() {
		System.out.println("You can't insert another quarter");
	}

	public void ejectQuarter() {
		System.out.println("Quarter returned");
	}

	public void turnCrank() {
		System.out.println("You truned ...");
		gumballMachine.setState(gumballMachine.getSoldState());
	}

	public void dispense() {
		System.out.println("No gumball dispensed");
	}
}

class SoldState implements State {


	GumballMachine2 gumballMachine;
	
	SoldState(GumballMachine2 gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	public void insertQuarter() {
		System.out.println("Please wait, we're already giving you a gumball");
	}

	public void ejectQuarter() {
		System.out.println("Sorry, you already turned the crank");
	}

	public void turnCrank() {
		System.out.println("Turning twice doesn't get you another gumball");
	}

	public void dispense() {
		gumballMachine.releaseBall();
		if(gumballMachine.getCount() > 0) {
			gumballMachine.setState(gumballMachine.getNoQuarterState());
		} else {
			System.out.println("out of gumballs");
			gumballMachine.setState(gumballMachine.getSoldOutState());
		}
	}
}


class NoQuarterState implements State {

	GumballMachine2 gumballMachine;
	
	NoQuarterState(GumballMachine2 gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	public void insertQuarter() {
		gumballMachine.setState(gumballMachine.getHasQuarterState());
	}

	public void ejectQuarter() {
		//error info
	}

	public void turnCrank() {
		//error info
	}

	public void dispense() {
		//error info
	}
}

class SoldOutState implements State {

	GumballMachine2 gumballMachine;
	
	SoldOutState(GumballMachine2 gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	public void insertQuarter() {
		gumballMachine.setState(gumballMachine.getHasQuarterState());
	}

	public void ejectQuarter() {
		//error info
		
	}

	public void turnCrank() {
		//error info
	}

	public void dispense() {
		//error info
	}
}


class WinnerState implements State {

	GumballMachine2 gumballMachine;
	
	WinnerState(GumballMachine2 gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	public void insertQuarter() {
		//error info
	}

	public void ejectQuarter() {
		//error info
	}

	public void turnCrank() {
		//error info
	}

	public void dispense() {
		System.out.println("YOU'RE A WINNER! You get two gumballs for you quarter");
		gumballMachine.releaseBall();//one 
		if(gumballMachine.getCount() == 0) {
			gumballMachine.setState(gumballMachine.getSoldOutState());		
		} else {
			gumballMachine.releaseBall();//anther
			if(gumballMachine.getCount() > 0) {
				gumballMachine.setState(gumballMachine.getNoQuarterState());
			} else {
				System.out.println("out of gumballs");
				gumballMachine.setState(gumballMachine.getSoldOutState());
			}
		}
		
	}
}