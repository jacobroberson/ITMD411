//Programmed by Jacob Roberson
/**
 * @author Jacob Roberson
 * This program utilizes Client.java generate the client records stored in the csv file
 */
//import entire java.io package
import java.io.*;
//import entire java.util package
import java.util.*;
//class starts
public class BankRecords extends Client {
	//Create variables necessary for client data.
	String id; //create a string variable called id
    int age; //create an integer variable called age
    String sex; //create a string variable called sex
    String region; //create a string variable called region
    double income; //create a double variable called income
    String married; //create a string variable called married
    int children; //create an integer variable called children
    String car; //create a string variable called car
    String save_act; //create a string variable called save_act
    String current_act; //create a string variable called current_act
    String mortgage; //create a string variable called mortgage
    String pep; //create a string variable called pep
    
    //create list 
    ArrayList<StringBuffer> l; //create list called l
    
    
  //create getters and setters
  	public String getId() //create id getter
  	{
  		return id; //returns id
  	}
  	
  	public void setId(String id) //create id setter
  	{
  		this.id=id; //set id=id
  	}
  	
  	public int getAge() //create age getter
  	{
  		return age; //returns id
  	}
  	
  	public void setAge(int age) //create age setter
  	{
  		this.age=age; //set age=age 
  	}
  	
  	public String getSex() //create sex getter
  	{
  		return sex; //returns sex
  	}
  	
  	public void setSex(String sex) //create sex setter
  	{
  		this.sex=sex; //set sex=sex
  	}
  	
  	public String getRegion() //create region getter
  	{
  		return region; //returns region
  	}
  	
  	public void setRegion(String region) //create region setter
  	{
  		this.region=region; //set region=region
  	}
  	
  	public double getIncome() //create income getter
  	{
  		return income; //returns income
  	}
  	
  	public void setIncome(double income) //create income setter
  	{
  		this.income=income; //set income=income
  	}
  	
  	public String getMarried() //create married getter
  	{
  		return married; //returns married
  	}
  	
  	public void setMarried(String married) //create married setter
  	{
  		this.married=married; //set married=married
  	}
  	
  	public int getChildren() //create children getter
  	{
  		return children; //returns children
  	}
  	
  	public void setChildren(int children) //create children setter
  	{
  		this.children=children; //set children=children
  	}
  	
  	public String getCar() //create car getter
  	{
  		return car; //returns car
  	}
  	
  	public void setCar(String car) //create car setter
  	{
  		this.car=car; //set car=car
  	}
  	
  	public String getSave_act() //create save_act getter
  	{
  		return save_act; //returns save_act
  	}
  	
  	public void setSave_act(String save_act) //create save_act setter
  	{
  		this.save_act=save_act; //set save_act=save_act
  	}
  	
  	public String getCurrent_act() //create current_act getter
  	{
  		return current_act; //returns current_act
  	}
  	
  	public void setCurrent_act(String current_act) //create current_act setter
  	{
  		this.current_act=current_act; //set current_act=current_act
  	}
  	
  	public String getMortgage() //create mortgage getter
  	{
  		return mortgage; //returns mortgage
  	}
  	
  	public void setMortgage(String mortgage) //create mortgage setter
  	{
  		this.mortgage=mortgage; //set mortgage=mortgage
  	}
  	
  	public String getPep() //create pep getter
  	{
  		return pep; //returns pep
  	}
  	
  	public void setPep(String pep) //create pep setter
  	{
  		this.pep=pep; //set pep=pep
  	}
  	
  	@Override //override readData()
  	//readData starts
  	public void readData()
  	{
  		//try starts
  		try
  		{
  			l = new ArrayList<StringBuffer>();
  			FileReader reader = new FileReader("bank-Detail.csv");
  			BufferedReader writer = new BufferedReader(reader);
  			String r;
  			
  			//while starts
  			while((r = writer.readLine()) != null)
  			{
  				StringBuffer s = new StringBuffer();
  				s.append(r);
  				l.add(s);
  			} //while ends
  			writer.close(); //close write
  		} //try ends
  		
  		//catch starts
  		catch(IOException e)
  		{
  			System.out.println("Error: File could not be found"); //display error message
  			System.exit(0); //end program
  		} //catch ends
  	} //readData ends
  	
  	@Override //override processData
  	//processData starts
  	public void processData()
  	{
  		Iterator<StringBuffer> p = l.iterator();
  		int n = 0; //keeps track of the number of records 
  		
  		System.out.printf("%-12s%-12s%-12s%-12s%-12s%-12s\n", "ID", "Age", "Sex", "Region", "Income", "Mortgage"); //label data
  		//while starts
  		while(p.hasNext())
  		{
  			//if starts
  			if(n == 25) //only collect first 25 records
  			{
  				break; //break loop
  			} //if ends
  			StringBuffer s = p.next();
  			String c[] = s.toString().split(","); //separated by comma
  			//store values
  			this.setId(c[0]);
  			this.setAge(Integer.parseInt(c[1]));
  			this.setSex(c[2]);
  			this.setRegion(c[3]);
  			this.setIncome(Double.parseDouble(c[4]));
  			this.setMortgage(c[10]);
  			this.printData();
  			n++; //add one to n
  		} //while ends
  	} //processData ends
  	
  	@Override //override printData()
  	//printData starts
  	public void printData()
  	{
  		//print id, age, sex, region, income, and mortgage
  		System.out.printf("%-12s%-12s%-12s%-12s%-12s%-12s\n", this.getId(), this.getAge(), this.getSex(), this.getRegion(), this.getIncome(), this.getMortgage());
  	} //printData ends
  	
  	//main method starts 
  	public static void main(String[] args)
  	{
  		BankRecords b = new BankRecords(); //run BankRecords
  		b.readData(); //run readData
  		b.processData(); //run processData
  		
  	} //main method ends
} //class ends

