/**
 * @author Parth Patel(#500893723)
 * CarDealership class this class has methods that will be used in CarDealershipSimulator
 *
 */
import java.util.*;
public class CarDealership 
{	
	private boolean AWDFilter; 
	private boolean electricCarFilter;
	private boolean priceFilter;
	private ArrayList<Car> cars;
	private double maxPrice;
	private double minPrice;
	
	private SalesTeam salesTeam;
	private AccountingSystem accountingSystem;
		
	public Random random;
	
	
	/**
	 * this constructor method initializes the variable type ArrayList cars
	 * salesTeam type SalesTeam and accountingSystem type AccountingSystem
	 */
	public CarDealership() 
	{
		cars = new ArrayList<Car>();
		salesTeam = new SalesTeam();
		accountingSystem = new AccountingSystem();
	}
	
	
	/**
	 * @return cars
	 * returns the ArrayList cars
	 */
	public ArrayList<Car> getCars() 
	{
		return cars;
	}
	
	
	/**
	 * @param newCars
	 * takes the parameter newCars type ArrayList and adds the list of car objects to the ArrayList cars
	 */
	public void addCars(ArrayList<Car> newCars) 
	{
		for(Car a: newCars) 
		{
		cars.add(a);
		}
	}
	
	
	/**
	 * @return i
	 * returns the size of an ArrayList 
	 */
	public int listSize() 
	{
		int i = getCars().size();
		return i;				
	}
	
	
	/**
	 * @param vin
	 * @return String
	 * this method takes a vin number as a parameter and searches througt the arrayList of cars and returns the object,
	 * and then it removes the object from the arryaList of Cars
	 * it returns the information about the Car object removed from the arrayLisr of cars
	 */
	public String buyCar(int vin)
	{
		int month,day;
		
		Car car = null;
		String person = salesTeam.getRandomSalesPerson();
		String result = "";
		for(int index = 0; index < cars.size(); index++)
			
		{		
			car = cars.get(index);
			if(car.getVIN() == vin) 
			{
				month = ((int) (12 * Math.random())); // Chooses random number from [0, 11]
                day = ((int) (accountingSystem.getDaysOfMonth(month) * Math.random() + 1));
                GregorianCalendar date = new GregorianCalendar(2019, month, day);
                result = accountingSystem.add(date, car, person, "BUY", car.getPrice());
                
                cars.remove(car);
            }
		}
		return result; 
	}

	
	/**
	 * @param returncar 
	 * this method adds the removed car back to the ArrayList of cars 
	 */
	public String returnCar(int transID) 
	{
		int month,day,randomDay;
		
		Car car = null;
		
		
		Transaction transaction = accountingSystem.getTransaction(transID);
		car = transaction.getCar(); // gets Car of the transaction
        month = transaction.getMonth(); // gets month of transaction
        day = transaction.getDay(); // gets day of transaction
        randomDay = (int) ((accountingSystem.getDaysOfMonth(month) - day + 1) * Math.random() + (day)); // finds day
        // after BUY
        // to
        // determine
        // RET day
        cars.add(car); // adds car back to list
        GregorianCalendar date = new GregorianCalendar(2019, month, randomDay); // creates date for RET transaction
        String result = accountingSystem.add(date, car, transaction.getSalesPerson(), "RET", transaction.getSalePrice());
        return result;
    }
		
	
	/**
	 * @return SalesTeam salesTeam
	 * returns the private variable salesTeam type SalesTeam
	 */
	public SalesTeam getSalesTeam()
	{
		return salesTeam;
	}


	/**
	 * @return AccountingSystem accountingSystem
	 * this method returns the private variable accountingSystem type AccountingSystem
	 */
	public AccountingSystem getAccountingSystem() {
		return accountingSystem;
	}
	
	
	/**
	 * @return AWDFilter
	 * this method returns the private variable AWDFilter
	 */
	public boolean isAWDFilter() 
	{
		return AWDFilter;
	}	


	/**
	 * @return electricCarFilter
	 * this method returns the private variable electricCarFilter
	 */
	public boolean isElectricCarFilter() {
		return electricCarFilter;
	}


	/**
	 * @return priceFilter
	 * this method returns the private variable priceFilter
	 */
	public boolean isPriceFilter() {
		return priceFilter;
	}


	

/**
 * this method checks which one of the filters are on, that is "true" 
 * and prints the information about the objects accordingly
 */
public void displayInventory() {
	for(int x = 0; x < cars.size(); x++){
		//Checks if electric car filter and AWD filter is on 
        if(isElectricCarFilter() == true && isAWDFilter() == true)
        {
            //if the above filters are on, then it checks if the price filter is on
        	if (isPriceFilter() == true)
            {
                if(cars.get(x).getPower() == 1 && cars.get(x).getPrice() > minPrice && cars.get(x).getPrice() < maxPrice && cars.get(x).getAWD() == true)
                {
                    System.out.println(x + " " + cars.get(x).display());
                }
                else{;}
            }
        	//if the price filter is off it jumps to this block
            else{
                if(cars.get(x).getPower() == 1 && cars.get(x).getAWD() == true)
                {
                    System.out.println(x + " " + cars.get(x).display());
                }
                else{;}

            }

        }
        //checks of the electric car filter is on and AWD filter is off
        else if (isElectricCarFilter() == true && isAWDFilter() == false)
        {
            //if the above condition is satisfied then it executes the following block only if the price filter is on
        	if (isPriceFilter() == true)
            {
                if(cars.get(x).getPower() == 1 && cars.get(x).getPrice() >= minPrice && cars.get(x).getPrice() <= maxPrice)
                {
                    System.out.println(x + " " + cars.get(x).display());
                }
                else{;}
            }
            //if price filter is off the program would jump to this block of code and execute it
        	else
            {
                if(cars.get(x).getPower() == 1)
                {
                    System.out.println(x + " " + cars.get(x).display());
                }
                else{;}

            }
        }
      //checks of the electric car filter is off and AWD filter is on
        else if (isElectricCarFilter() == false && isAWDFilter() == true)
        {
        	//if the above condition is satisfied then it executes the following block only if the price filter is on
        	if (isPriceFilter() == true)
            {
                if(cars.get(x).getPrice() >= minPrice && cars.get(x).getPrice() <= maxPrice && cars.get(x).getAWD() == true)
                {
                    System.out.println(x + " " + cars.get(x).display());
                }
                else{;}
            }
        	//if price filter is off the program would jump to this block of code and execute it
            else {
                if(cars.get(x).getAWD() == true)
                {
                    System.out.println(x + " " + cars.get(x).display());
                }
                else{;}
            }

        }
      //checks of the electric car filter is off and AWD filter is off
        else if (isElectricCarFilter() == false && isAWDFilter() == false)
        {
        	//if the above condition is satisfied then it executes the following block only if the price filter is on
        	if (isPriceFilter() == true)
            {
                if(cars.get(x).getPrice() >= minPrice && cars.get(x).getPrice() <= maxPrice)
                {
                    System.out.println(x + " " + cars.get(x).display());
                }
                else{;}
            }
        	//if price filter is off the program would jump to this block of code and execute it
            else
            {
                System.out.println(x + " " + cars.get(x).display());
            }
        }
	}
}

	
	
	
	/**
	 * This is an inner class made to implement the interface Comparator
	 * to compare two objects based on the maxRange attribute
	 *
	 */
	class maxRangeSort implements Comparator<Object>
	{
		
	
		/** 
		 * @param1 Object o1 is the first object
		 * @param2 Object o2 is the second object
		 * this method overrides the abstract method from the Comparator interface
		 * it takes two objects as parameters and casts them into Car objects
		 * compares them with respect to the attribute maxRange and returns an integer according to the condition
		 * 
		 */
		public int compare(Object o1, Object o2) 
		{
			Car o3 = (Car) o1;
			Car o4 = (Car) o2;
			
			if(o4.getMaxRange() < o3.getMaxRange())
				return 1;
			else if(o4.getMaxRange() > o3.getMaxRange())
				return -1;
			else
				return 0;
		}
	}
		
	
	/**
	 * this is an inner class to implement the Comparator interface 
	 * to compare two objects based on safetyRating attribute
	 *
	 */
	class safetyRatingSort implements Comparator<Object>
	{
		
		/** 
		 * @param1 Object o1 is the first object
		 * @param2 Object o2 is the second object
		 * this method overrides the abstract method from the Comparator interface
		 * it takes two objects as parameters and casts them into Car objects
		 * compares them with respect to the attribute safetyRating and returns an integer according to the condition
		 */
		public int compare(Object o1, Object o2) 
		{
			Car o3 = (Car) o1;
			Car o4 = (Car) o2;
			
			if(o4.getSafetyRating() < o3.getSafetyRating())
				return 1;
			else if(o4.getSafetyRating() > o3.getSafetyRating())
				return -1;
			else
				return 0;
		}
	}	
	
	
	
	/**
	 * this method turns on the electricCarFilter on that is it assigns boolean true to the variable electricCarFilter
	 */
	public void electricCarFilter() 
	{
		this.electricCarFilter = true;	
	}
	
	
	/**
	 * this method turns on the AWDFilter on that is it assigns boolean true to the variable AWDFilter
	 */
	public void AWDFilter() 
	{
		this.AWDFilter = true;
	}
	
	
	/**
	 * @param minPrice1
	 * @param maxPrice1 
	 * this method turns on the priceFilter on that is it assigns boolean true to the variable priceFilter and assigns 
	 * the value passed as parameters to the minPrice and maxPrice variables 
	 */
	public void priceFilter(double minPrice1, double maxPrice1) 
	{
		this.maxPrice = maxPrice1;
		this.minPrice = minPrice1;
		this.priceFilter = true;
	}
	
	
	/**
	 * this method turns off all the filters that is it assigns boolean false
	 * to AWDFilter, electricCarFilter and priceFilter instance variables
	 * also makes the variables maxPrice and minPrice 0
	 */
	public void filtersClear() 
	{
		this.AWDFilter = false;
		this.electricCarFilter = false;
		this.priceFilter= false;
		this.maxPrice = 0;
		this.minPrice = 0;
	}
	
	
	/**
	 * this method calls the framework collections and sort the cars according in ascending order 
	 * with respect to their prices
	 */
	public void sortByPrice() 
	{
		Collections.sort(cars);
	}
	
	
	/**
	 * this method calls the framework collections and sort the cars according in ascending order 
	 * with respect to their safetyRating
	 */
	public void sortBySafetyRating() 
	 {
		Collections.sort(cars ,new safetyRatingSort()); 
	 }
 
	
	/**
	 * this method calls the framework collections and sort the cars according in ascending order 
	 * with respect to their maxRange 
	 */
	public void sortByMaxRange() 
	  { 
		Collections.sort(cars, new maxRangeSort());	  
	  }
	  



}
