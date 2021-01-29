//Programmed by Jacob Roberson
/**
 * @author Jacob Roberson
 * This program compares male income
 */

//import Comparator
import java.util.Comparator;
//class starts
public class mIncome implements Comparator<BankRecords>
{
	@Override //override
	//compare starts
	public int compare(BankRecords x, BankRecords y)
	{
		int result = x.getSex().compareTo(y.getSex());
		//if starts
		if(result != 0)
		{
			return result;
		} //if ends
		
		return String.valueOf(x.getIncome()).compareTo(String.valueOf(y.getIncome()));
	} //compare ends
} //class ends
