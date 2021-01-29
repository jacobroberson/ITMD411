//Programmed by Jacob Roberson
/**
 * @author Jacob Roberson
 * This program contains methods to control the operations of a banking application
 */

//class starts
public class AccountHolder {
	static double annualInterestRate = 0.04;
	double balance;
	
	//this is the constructor to initialize the balance
		public AccountHolder(double balance) {
			if(balance >= 0) {
				this.balance = balance;
				System.out.printf("Initial Balance: $%.2f\n", balance);
			}
			else {
				System.out.println("Initial balances cannot be negative\nPlease run the program again");
				System.exit(0);
			}
			
		};
		
	//this is method will control the deposit operation
		public void deposit(double amount) {
			balance += amount;
		}
		
	//this is method will control the withdraw operation
		public void withdraw(double amount) {
			if((balance - amount) < 50) {
				System.out.println("ERROR: The account balance may not fall below $50.00\nPlease run the program again");
				System.exit(0);
			}
			else {
				balance -= amount;
			}
		}
		
	//this is method will control the interest operation
		public void monthlyInterest(double annualInterestRate) {
			balance += balance * (annualInterestRate / 12.0);
		}
		
	//returns balance to AccountHolderTest	
		public double getBalance()
		{
			return balance;
		}

		

} //class ends
