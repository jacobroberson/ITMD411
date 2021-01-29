//Programmed by Jacob Roberson
/**
 * @author Jacob Roberson
 * This program will call upon methods in AccountHolder.java to run a banking application
 */
//import Scanner class
import java.util.Scanner;
//class starts
public class AccountHolderTest {
	//hard code interest rate
	static double annualInterestRate = 0.04;
	
	//main method starts
	public static void main(String[] args){
		
		
		
		//initialize a scanner object
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the Bank of IIT Account Management Program\n");
		
		//establish initial balance
		System.out.println("Please enter the Acount Balance: ");
		double balance = sc.nextDouble();
		//pass the balance to an AccountHolder object
		AccountHolder account = new AccountHolder(balance);
		
		//pass the interest rate to the AccountHolder object
		account.monthlyInterest(annualInterestRate);
		
		//perform a deposit
		System.out.print("Please enter the deposit amount: ");
		double deposit = sc.nextDouble();
		account.deposit(deposit);
		
		//perform a withdraw
		System.out.print("Please enter the withdraw amount: ");
		double withdraw = sc.nextDouble();
		account.withdraw(withdraw);
		
		//display account information after all actions have been performed
		System.out.printf("Account balance: $%.2f\n", account.getBalance());
		
		//close the scanner object
		sc.close();
	}//main method ends

} //class ends
