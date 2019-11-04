import java.util.*;
/**
 * @author Parth Patel(#500893723)
 * This class indirectly uses class Transaction and SalesTeam and has the methods that help in execution of the CarDealershipSimulator's main method
 */
public class AccountingSystem 
{
	Map<Integer, Transaction> transactionObjects = new TreeMap<Integer, Transaction>();
	
	int[][] monthAndNumOfSales = {{ 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 },
			{ 0, 0 }, { 0, 0 }, { 0, 0 },{ 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 }};
	
	
	String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	
	int[] daysOfEachMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	
	Set<Integer> usedTransactionObj = new TreeSet<Integer>();

	
	public String add(GregorianCalendar date, Car car, String salesPerson, String type, double salePrice) 
	{
        int randomTransactionID;
        
        do 
        {
            randomTransactionID = (int) (99 * Math.random() + 1); // chooses random number between [1,99]
        } 
        while (usedTransactionObj.contains(randomTransactionID)); // Makes sure there are no duplicate IDs
        usedTransactionObj.add(randomTransactionID);
        Transaction transactions = new Transaction(randomTransactionID, date, car, salesPerson, type, salePrice);                             
                                                                                                     			
        transactionObjects.put(randomTransactionID, transactions); // puts made Transaction into the TreeMap
        if (type.equals("BUY"))
        { // Logs in the BUY command
        	monthAndNumOfSales[transactions.getMonth()][0] += 1;
        }
        else if (type.equals("RET")) 
        { // Logs in the RET command
        	monthAndNumOfSales[transactions.getMonth()][1] += 1;
        }
			
        return transactions.display(); // Returns all the Info
    }
	
	
	/**
	 * Returns the transaction if the ID matches the key otherwise returns null
	 * @param ID takes the ID as a parameter
	 * @return Transaction 
	 * 
	 */
	public Transaction getTransaction(int ID) 
	{
		for(int key: transactionObjects.keySet()) 
		{
			if(key == ID) 
			{
				return transactionObjects.get(key);
			}
		}
		return null;
	}
	
	
	 /**
     * Filters all of the Transactions by the month entered as a parameter
     * 
     * @param selectedMonth is the month that is filtered to
     * @return String of all the transactions in selected month separated with ";"
     */
    public String getTransactionsByMonth(int selectedMonth) 
    {
        String result = "";
        Transaction t;
        for (int key : transactionObjects.keySet()) 
        {
            t = transactionObjects.get(key);
            if (t.getMonth() == selectedMonth) 
            {
                result = result + ";" + t.display(); // Separates by ";" for useDelimiter
            }
        }
        return result;
    }
	
	
    /**
     * Gets the number of days in a given month
     * 
     * @param i is the index from which name is required
     * @return String of the month from the index month
     */
    public int getDaysOfMonth(int i) 
    {
        return daysOfEachMonth[i];
    }
    
    
    /**
     * Gets month name from the index of the month
     * 
     * @param i is the index of the month required
     * @return String that is the month
     */
    public String getMonthNameFromNumber(int i) 
    {
        return monthNames[i];
    }
   

    /**
     * Filters all of the Transactions by the year 2019
     * 
     * @return String of all the transactions in 2019 separated with ";"
     */
    public String getTransactionsByYear() 
    {
        String result = "";
        Transaction t;
        for (int key : transactionObjects.keySet()) 
        {
            t = transactionObjects.get(key);
            
            if (t.getYear() == 2019) 
            {
                result = result + ";" + t.display();
            }
        }
        return result;
    }

    /**
     * This method gives the total money that was earned when cars was sold
     * 
     * @return Double value of the total numbers of sales made in terms of price
     */
    public double getTotalSalesByPrice() 
    {
        double result = 0;
        for (int key : transactionObjects.keySet()) 
        {
            result = result + transactionObjects.get(key).getSalePrice();
        }
        return result;
    }

    /**
     * This method gives the average sales per month
     * 
     * @return Double value of the average sales per month ($)
     */
    public int getAverageSales() 
    {
        return ((int) getTotalSalesByPrice() / 12); // dividing by 12 reveals average value
    }

    /**
     * This method finds the total number of cars bought
     * 
     * @return Integer value of the total number of cars sold
     */
    public int getTotalCarsSold() 
    {
        int result = 0;
        
        for (int key : transactionObjects.keySet()) 
        {
            if (transactionObjects.get(key).getOperationType() == "BUY") 
            {
                result++;
            }
        }
        return result;
    }

    /**
     * This method finds the total number of cars returned
     * 
     * @return Integer value of the number of cars returned
     */
    public int getTotalCarsReturned() 
    {
        int result = 0;
        for (int key : transactionObjects.keySet()) 
        {
            if (transactionObjects.get(key).getOperationType() == "RET") 
            {
                result++;
            }
        }
        return result;
    }

    /**
     * This method finds the month that has the most sales made in
     * 
     * @return String of the month with the most sales
     */
    public String findMostSalesMonth() 
    {
        int highestNum = 0;
        String result1 = "";
        for (int i = 0; i <= 11; i++) 
        { // Finds highest Sales
            if (monthAndNumOfSales[i][0] > highestNum) 
            {
                highestNum = monthAndNumOfSales[i][0];
            }
        }
        for (int j = 0; j <= 11; j++) { // Sees if there are other months with same value
            if (monthAndNumOfSales[j][0] == highestNum) 
            {
                result1 = result1 + " " + getMonthNameFromNumber(j);
            }
        }
        result1 = "Best Month(s): " + result1 + " Cars Sold: " + highestNum; // adds proper grammar
        return result1;
    }

    /**
     * This method finds a BUY transaction of a specific Car
     * 
     * @param car is the Car that needs to find the transaction
     * @return Transaction of the Car that was bought
     */
    public Transaction findBUYTransaction(Car car) 
    {
        for (int key : transactionObjects.keySet()) 
        {
            if (transactionObjects.get(key).getCar() == car) 
            {
                return transactionObjects.get(key);
            }
        }
        return null; // If Transaction does not exist
    }
}
