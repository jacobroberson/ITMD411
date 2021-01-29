//Programmed by Jacob Roberson
/**
 * @author Jacob Roberson
 * This program compares income
 */

//import Comparator
import java.util.Comparator;
//class starts
public class IncomeCompare implements Comparator<BankRecords>
{
	@Override //override
	//compare starts
	public int compare(BankRecords x, BankRecords y)
	{
		int result = String.valueOf(x.getIncome()).compareTo(String.valueOf(y.getIncome()));
		return result;
	} //compare ends
} //class ends
