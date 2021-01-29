//Programmed by Jacob Roberson
/**
 * @author Jacob Roberson
 * This program compares males
 */

//import Comparator
import java.util.Comparator;
//class starts
public class mCompare implements Comparator<BankRecords>
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
		
		return x.getCar().compareTo(y.getCar());
	} //compare ends
} //class ends
