//Programmed by Jacob Roberson
/**
 * @author Jacob Roberson
 * This program extends BankRecords to write records to a file
 */
//import java.io
import java.io.*;
//import java.util
import java.util.*;
//class starts
public class Records extends BankRecords 
{
	//initialize filewriter
	private static FileWriter fw = null;
	//records starts
	public Records()
	{
		//try starts
		try
		{
			//create txt file to store records
			fw = new FileWriter("bankrecords.txt");
		} //try ends
		//catch starts
		catch(IOException e)
		{
			e.printStackTrace();
		} //catch ends
	} //records ends
	
	//f starts
	private static void f()
	{
		Arrays.sort(rec, new fCompare());
		//initialize int to track number of females
		int fc = 0;
		//for starts
		for(int n = 0; n < rec.length; n++)
		{
			//if starts
			//checks to see if a female has a mortgage and savings account
			if(rec[n].getSex().equals("FEMALE") && rec[n].getMortgage().equals("YES") && rec[n].getSave_act().equals("YES"))
			{
				//add one to fc
				fc++;
			} //if ends
		} //for ends
		//print total number of females with mortgages and savings accounts
		System.out.println("\nFemales with a Mortgage and Savings Account:" + fc);
		//try starts
		try
		{
			//writes the number of females with a mortgage and savings account to file
			fw.write("Females with a Mortgage and Savings Account:" + fc + "\n");
		} //try ends
		//catch starts
		catch(IOException e)
		{
			e.printStackTrace();
		} //catch ends
	} //f ends
	
	//m starts
	private static void m()
	{
		Arrays.sort(rec, new mCompare());
		int i = 0; //number of inner city
		int t = 0; //number of town
		int r = 0; //number of rural
		int s = 0; //number of suburb
		
		//for starts
		for(int n = 0; n < rec.length; n++)
		{
			//if starts
			//checks to see if a male has a car and 1 child in the inner city
			if(rec[n].getSex().equals("MALE") && rec[n].getRegion().equals("INNER_CITY") && rec[n].getCar().equals("YES") && rec[n].getChildren() == 1)
			{
				//add one to i
				i++;
			} //if ends
		} //for ends
		//print number of males with a car and 1 child in the inner city
		System.out.println("Males with a car and 1 child in the inner city:" + i);
		//for starts
		for(int n = 0; n < rec.length; n++)
		{
			//if starts
			//checks to see if a male has a car and 1 child in a town
			if(rec[n].getSex().equals("MALE") && rec[n].getRegion().equals("TOWN") && rec[n].getCar().equals("YES") && rec[n].getChildren() == 1)
			{
				//add one to t
				t++;
			} //if ends
		} //for ends
		//print the number of males with a car and 1 child in a town
		System.out.println("Males with a car and 1 child in a town:" + t);
		//for starts
		for(int n = 0; n < rec.length; n++)
		{
			//if starts
			//checks to see if a male has a car and 1 child in the rural
			if(rec[n].getSex().equals("MALE") && rec[n].getRegion().equals("RURAL") && rec[n].getCar().equals("YES") && rec[n].getChildren() == 1)
			{
				//add one to r
				r++;
			} //if ends
		} //for ends
		//print the number of males with a car and 1 child in the rural
		System.out.println("Males with a car and 1 child in the rural:" + r);
		//for starts
		for(int n = 0; n < rec.length; n++)
		{
			//if starts
			//checks to see if a male has a car and 1 child in the suburban
			if(rec[n].getSex().equals("MALE") && rec[n].getRegion().equals("SUBURBAN") && rec[n].getCar().equals("YES") && rec[n].getChildren() == 1)
			{
				//add one to s
				s++;
			} //if ends
		} //for ends
		//print the number of males with a car and 1 child in the suburban
		System.out.println("Males with a car and 1 child in the suburban:" + s);
		//try starts
		try
		{
			//write male numbers to the file
			fw.write("\nMales with a car and 1 child in the inner city:" + i + "\n" + "Males with a car and 1 child in a town:" + t + "\n" + "Males with a car and 1 child in the rural:" + i + "\n" + "Males with a car and 1 child in the suburban:" + s + "\n");
		} //try ends
		//catch starts
		catch(IOException e)
		{
			e.printStackTrace();
		} //catch ends
	} //m ends
	
	//AvgIncome starts
	public static void AvgIncome()
	{
		Arrays.sort(rec, new IncomeCompare());
		//initialize average
		double avg = 0;
		//initialize sum
		double sum = 0;
		//for starts
		for(int n = 0; n < rec.length; n++)
		{
			//calculates the total income
			sum += rec[n].getIncome();
		} //for ends
		//calculates average
		avg = sum/rec.length;
		//prints average
		System.out.printf("\nAverage Income: $%.2f\n" , avg);
		//try starts
		try
		{
			//write average income to the file
			fw.write("Average Income:" + avg + "\n\n");
		} //try ends
		//catch starts
		catch(IOException e)
		{
			e.printStackTrace();
		} //catch ends
	} //AvgIncome ends
	
	//mAvg starts
	public static void mAvg()
	{
		Arrays.parallelSort(rec, new mIncome());
		double avg = 0; //initialize average
		double sum = 0; //initialize sum
		//for starts
		for(int n = 0; n < rec.length; n++)
		{
			//if starts
			if(rec[n].getSex().equals("MALE"))
			{
				//calculates the total male income
				sum += rec[n].getIncome();
			} //if ends
		} //for ends
		//calculates average
		avg = sum/rec.length;
		//prints average
		System.out.printf("Average Male Income: $%.2f", avg);
		//try starts
		try
		{
			//write average male income to the file
			fw.write("Average Male Income: " + avg + "\n");
		} //try ends
		//catch starts
		catch(IOException e)
		{
			e.printStackTrace();
		} //catch ends
	} //mAvg ends
	
	//fAvg starts
	public static void fAvg()
	{
		Arrays.parallelSort(rec, new fIncome());
		double avg = 0; //initialize average
		double sum = 0; //initialize sum
		//for starts
		for(int n = 0; n < rec.length; n++)
		{
			//if starts
			if(rec[n].getSex().equals("FEMALE"))
			{
				//calculates the total female income
				sum += rec[n].getIncome();
			} //if ends
		} //for starts
		//calculates average
		avg = sum/rec.length;
		//prints average
		System.out.printf("Average Female Income: $%.2f\n", avg);
		//try starts
		try
		{
			//write average female income to the file
			fw.write("Average Female Income: " + avg + "\n");
		} //try ends
		//catch starts
		catch(IOException e)
		{
			e.printStackTrace();
		} //catch ends
	} //fAvg ends
	//main starts
	public static void main(String args[]) throws IOException
	{
		Records obj = new Records();
		obj.readData();
		
		System.out.println("Data analytic result: \n");
		fw.write("Data analytic result: \n\n");
		fAvg(); //run fAvg
		mAvg(); //run mAvg
		AvgIncome(); //run AvgIncome
		f(); //run f
		System.out.println("");
		m(); //run m
		//try starts
		try
		{
			fw.close();
		} //try ends
		//catch starts
		catch(IOException e)
		{
			e.printStackTrace();
		} //catch ends
	} //main ends
} //class ends