import java.text.*;
import java.util.*;
/**
 * @author Parth Patel(#500893723)
 * This class keeps track of the transactions that is buy and return operations being made in the main method
 * it helps to keep everything organized in one place
 *
 */
public class Transaction 
{
	
	int transactionID;	
	GregorianCalendar date; //date object type GregorianCalendar is initialized
	SimpleDateFormat dateformat;
	Car car;	//Car type object initialized
	String person;
	double price;
	String type; //buy or ret
	
	public Transaction(int transactionID, GregorianCalendar date, Car car, String person, String type, double price) 
	{		
		this.transactionID = transactionID;
		this.date = date;
		this.person = person;
		this.price = price;
		this.car = car;
		this.type = type;		
	}
	
	/**
	 * @return String
	 * this method returns a string containing the transaction information including the name of the sales person and transaction type that is buy or ret
	 *  
	 */
	public String display() 
	{
		dateformat = new SimpleDateFormat("EEE, MMM dd, YYYY");
        String string = "ID: " + transactionID + " " + dateformat.format(date.getTime()) + " " + type
                + " SalesPerson: " + person + " Car: " + car.display();
        return string;
	}
	

	/**
	 * @return year
	 * this method returns a year
	 */
	public int getYear() 
	{
        return date.get(date.YEAR);
    }

	
    /**
     * @return month
     * this method returns a month in form of integer
     */
    public int getMonth() 
    {
        return date.get(date.MONTH);
    }

    
    /**
     * @return day
     * this method returns a day in form of integer
     */
    public int getDay() {
        return date.get(date.DAY_OF_MONTH);
    }
    

    /**
     * @return String
     * this method returns the name of the salesPerson
     */
    public String getSalesPerson() 
    {
        return person;
    }

    
    /**
     * @return String
     * this method returns a string stating what type of operation was done 
     * that is either buy or return
     */
    public String getOperationType() 
    {
        return type;
    }


    /**
     * @return Car
     * this method returns a Car 
     */
    public Car getCar() 
    {
        return car;
    }

    
    /**
     * @return price
     * this method returns the price of a Car
     */
    public double getSalePrice()
    {
        return price;
    }

    
    
    /**
     * @return transactionID
     * returns the transactionID 
     */
    public int getTransactionID() {
        return transactionID;
    }
 
}
