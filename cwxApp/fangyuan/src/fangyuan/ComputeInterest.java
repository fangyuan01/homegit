package fangyuan;

public class ComputeInterest {
	public static void main(String args[]) {

		HelloWorld helloWorld = new HelloWorld();
		helloWorld.hello();
		final double interestRate = 0.1;
		double newBalance;
		double previousBalance = 100;
		newBalance = previousBalance * (1 + interestRate);
		previousBalance = newBalance;
		newBalance = previousBalance * (1 + interestRate);
		previousBalance = newBalance;
		newBalance = previousBalance * (1 + interestRate);
		previousBalance = newBalance;
		newBalance = previousBalance * (1 + interestRate);
		previousBalance = newBalance;
		newBalance = previousBalance * (1 + interestRate);
		System.out.println("newBalance=" + newBalance);// no loop
		// whlie loop
		int i = 1;
		previousBalance = 100;
		newBalance = 0;
		while (i <= 5) {
			newBalance = previousBalance * (1 + interestRate);
			previousBalance = newBalance;
			i++;
		}
		System.out.println("newBalance=" + newBalance);
		// do loop
		i = 1;
		previousBalance = 100;
		newBalance = 0;
		do {
			newBalance = previousBalance * (1 + interestRate);
			previousBalance = newBalance;
			i++;
		} while (i <= 5);
		System.out.println("newBalance=" + newBalance);
		// for loop
		previousBalance = 100;
		newBalance = 0;
		for (i = 1; i <= 5; i++) {
			newBalance = previousBalance * (1 + interestRate);
			previousBalance = newBalance;
		}

		System.out.println("newBalance=" + newBalance);
	}
}
