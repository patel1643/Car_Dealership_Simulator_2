import java.util.*;
import java.io.*;
/**
 * @author Parth Patel(#500893723)
 * This class has the main method where all the objects are made by using the File I/O 
 * this class uses all the other classes to simulate the CarDealership
 *
 */
public class CarDealershipSimulator
{
	public static ArrayList<Car> makeArrayList(String textFile) {
		try 
		{ 	// used for catching any exceptions in the program to prevent runtime errors
			Scanner scanner = new Scanner(new File(textFile)), value; //Scanner object created to read from file
			ArrayList<Car> listOfCars = new ArrayList<Car>();	//ArrayList created of type Car
			String mfr, color, model,powerType,wheelsType;	//String that holds the values of manufacturer, color and model which will be used in constructor of Car objects
			int power, maxRange, intModel; //Integer type variables that hold the values of power, maximumRange and model type
			double safetyRating, price;	//Double type variable that holds the safetyRating an price of a car
			boolean AWD;
			Car car;
			while (scanner.hasNextLine()) 
			{
				value = new Scanner(scanner.nextLine());
				mfr = value.next();
				color = value.next();
				powerType = value.next();
				if (powerType.equals("GAS_ENGINE")) 
				{
					power = 1;
				} else {
					power = 0;
				}
				model = value.next();
				if(model.equals("SEDAN")) {
					intModel = 1;
				}
				else if(model.equals("SUV")) {
					intModel = 2;
				}
				else if(model.equals("SPORTS")) {
					intModel = 3;
				}
				else {
					intModel = 4;
				}
				maxRange = value.nextInt();
				safetyRating = value.nextDouble();
				
				wheelsType = value.next();
				if (wheelsType.equals("2WD")) 
				{
					AWD = false;
				} else {
					AWD = true;
				}
				price = value.nextInt();
				if (value.hasNext()) 
				{//this checks if the input line has a value after price if yes it'll be classified as ElectricCar type object
					car = new ElectricCar(mfr, color, power, 4, intModel, maxRange, safetyRating, AWD, price, value.nextInt(), "LITHIUM");
				} 
				else 
				{//if the above condition is false then it would automatically consider as a car and make a Car type object
					car = new Car(mfr, color, power, 4, intModel, maxRange, safetyRating, AWD, price);
				}
				listOfCars.add(car);
			}
			scanner.close(); 
			return listOfCars;
		} 
		catch (Exception e) 
		{//Catches any type of exception thrown by the try block 
			System.err.println(e+"\nEMPTY FILE DETECTED!!!");
			return new ArrayList<Car>();
		}
	}
		

	
	
	
	public static void main(String[] args)
	{
		ArrayList<Car> carsList = makeArrayList("cars.txt");
		String lastObjBought = "";
	    int transIDFound = -1;
	    String transactionStringMonth = "";
	    String salesSummary = "";  
	       
	    boolean returnCarFlag = false;
		//carDealershipObj is the object created(type ArrayList) to store all the cars 
	    
		CarDealership carDealershipObj = new CarDealership();
	  	 
		//scanner object that takes the input in the console area	
		Scanner scanner = new Scanner(System.in);
		Scanner trans = null;
		Scanner monthTrans = null;
	
		//checks the command entered input  
		while(scanner.hasNext()) 
		{
			String string = scanner.nextLine();				
				//checks if the input is "L" if yes it will call the method displayInventory() from the CarDealership class
				if (string.equals("L")) 
				{
					if(carDealershipObj.listSize() == 0) 
					{
						System.out.println("The list is empty please use ADD command to add the cars to the list");
					}
					else
						carDealershipObj.displayInventory();
				
				}
				//checks if the input is "Q" if yes it will quit the program
				else if (string.equals("Q")) 
				{
					scanner.close();
					System.err.println("PROGRAM HAS BEEN TERMINATED");
					return;
				}
				
				//checks if the input is "BUY" followed by an int type value that is the VIN number of the car objects in the ArrayList
				//and would call the method buyCar() from the CarDealership class and remove the car if the entered VIN matches the car VIN
				else if (string.contains("BUY")) 
				{ 								
					try 
					{
						String s = string;
						ArrayList<String> listA = new ArrayList<String>(Arrays.asList(s.split("\\s+")));
						int vinNumber = Integer.parseInt(listA.get(1));
						if(vinNumber >=100 && vinNumber <= 499) 
						{
							lastObjBought = carDealershipObj.buyCar(vinNumber);
							returnCarFlag = true;
							System.out.println(lastObjBought);
						}
						else
							System.err.println("Invalid VIN number entered\nNo such car present on the list");
					}
					
					catch(Exception e) 
					{
						System.err.println("Please enter a VIN number of the car after the command \nBUY *VIN number from the list of cars*");
						System.err.println(e);
					}
					
				}
				//checks if the input is "RET" it would return the car by calling the returnCar() method from the CarDealership class
				//if no car was bought it would print an error 
				else if (string.equals("RET")) 
				{ 
					try 
					{
					if (returnCarFlag) 
					{ 	
						ArrayList<String> list1 = new ArrayList<String>(Arrays.asList(lastObjBought.split("\\s+")));
						 
							 // Number is after ID:
								transIDFound = Integer.parseInt(list1.get(1)); // Converts next Integer to
																							// Integer from String
							
					
						
						lastObjBought = carDealershipObj.returnCar(transIDFound);;
						System.out.println(lastObjBought+"\nCar returned successfully!!");
						
					}
					else	
					{
						System.err.println("Sorry this action cannot be done; please buy a car first");
					}
					}
					catch(Exception e)
					{
						System.err.println(e + "\nIllegal action!!!\nCannot return the same car again!");
					}
				}
				//checks if the input is "ADD" it would add the list of car objcts to the carDealershipObj 
				//by calling the addCars method from the CarDealership class
				else if (string.equals("ADD")) 
				{ 
					if(carDealershipObj.listSize() == 0) 
					{
						carDealershipObj.addCars(carsList);
					}
					else
						{;}
				}
				
				//checks if the input is "SPR" and then calls sortByPrice() method from CarDealership Class
				else if (string.equals("SPR")) 
				{
					carDealershipObj.sortByPrice();
				}
				
				//checks if the input is "SSR" and the calls sortBySafetyRating() method from CarDealership Class
				else if (string.equals("SSR")) 
				{
					carDealershipObj.sortBySafetyRating();
				}
				
				//checks if the input is "SMR" and the calls sortByMaxRange() method from CarDealership Class
				else if (string.equals("SMR")) 
				{
					carDealershipObj.sortByMaxRange();
				}
				
				//checks if the input is "FPR" and the calls priceFilter() method from CarDealership Class after checking if the values are in range 
				//if not it would display an error message by using Exception Class
				else if (string.contains("FPR")) 
				{ 																	
					try 
					{
						
						String s = string;
						ArrayList<String> listA = new ArrayList<String>(Arrays.asList(s.split("\\s+")));
					
						double minimum = Double.parseDouble(listA.get(1));
						double maximum = Double.parseDouble(listA.get(2));
						if(maximum >= minimum)
							carDealershipObj.priceFilter(minimum, maximum);
						else
							System.out.println("Price range invalid!!\nminimum price should be less than maximum price!!\nPlease try again..");
					}
					catch(Exception e) 
					{
						System.out.println("Please enter the price range for the command FPR to filter out the cars in that particular price range");
						System.out.println("Exception: " + e);
					}
					
				}
				
				//checks if the input is "FEL" and the calls electricCarFilter() method from CarDealership Class
				else if (string.equals("FEL")) 
				{ 
					carDealershipObj.electricCarFilter();
				}
				
				//checks if the input is "FAW" and the calls AWDFilter() method from CarDealership Class
				else if (string.equals("FAW")) 
				{ 
					carDealershipObj.AWDFilter();
				}
				
				//checks if the input is "FCL" and the calls filtersClear() method from CarDealership Class
				else if (string.equals("FCL"))
				{ 
					carDealershipObj.filtersClear();
				}				
				
				//Checks if the input has "SALES" if yes then it'll fall into the nested if - else if - else statements and execute the code accordingly
				else if(string.contains("SALES")) 
				{
					try 
					{
					String s = string;
					ArrayList<String> listA = new ArrayList<String>(Arrays.asList(s.split("\\s+")));
						if(string.equals("SALES")) 
						{
							salesSummary = carDealershipObj.getAccountingSystem().getTransactionsByYear();
							if (salesSummary.equals("")) 
							{
								System.out.println("There are no Transactions");
							} 
							else 
							{
								trans = new Scanner(salesSummary); // Puts transactions into Scanner object
								trans.useDelimiter(";");
								while (trans.hasNext()) 
								{ // Prints if there are more transactions
									System.out.println(trans.next());
								}
							}
						}
												
						//checks if the following input is "TEAM" and calls the method accordingly
						else if(listA.get(1).equals("TEAM")) 
						{
							System.out.println(carDealershipObj.getSalesTeam().displayTeam());
						}
						//checks if the following input is "TOPSP" and calls the method accordingly 
						else if(listA.get(1).equals("TOPSP")) 
						{
							System.out.println(carDealershipObj.getSalesTeam().getTopSalesPerson());
						}
						//checks if the following input is an Integer between 1-12 and calls the method accordingly
						else if(listA.get(1).contains("1") || listA.get(1).contains("2") || listA.get(1).contains("3") || listA.get(1).contains("4") ||
								listA.get(1).contains("5") || listA.get(1).contains("6") || listA.get(1).contains("7") || listA.get(1).contains("8") ||
								listA.get(1).contains("9") || listA.get(1).contains("10") || listA.get(1).contains("11") || listA.get(1).contains("12"))
						{
							int monthNumber = Integer.parseInt(listA.get(1));
							if(monthNumber > 0 && monthNumber <=12) 
							{
								transactionStringMonth = carDealershipObj.getAccountingSystem().getTransactionsByMonth(monthNumber);
								monthTrans = new Scanner(transactionStringMonth);
								monthTrans.useDelimiter(";"); // Separates all transactions by ";"
								
								if (transactionStringMonth.equals("")) 
								{
									System.out.println("There are no Transactions in this month");
								} 
								else
								{
									while(monthTrans.hasNext()) 
									{
										System.out.println(monthTrans.next());
									}
								}							
							}
							else {
								System.err.println("Please enter a valid month!!!");
							}
						}
						//checks if the following input is "STATS" and calls the method accordingly
						else if (listA.get(1).equals("STATS")) 
						{
							System.out.println("Total Sales: " + carDealershipObj.getAccountingSystem().getTotalSalesByPrice() 
									+ " Total Sold: "	+ carDealershipObj.getAccountingSystem().getTotalCarsSold() 
									+ " Avg Sales: "	+ carDealershipObj.getAccountingSystem().getAverageSales() 
									+ " Total Returned: "	+ carDealershipObj.getAccountingSystem().getTotalCarsReturned()
									+ " " + carDealershipObj.getAccountingSystem().findMostSalesMonth());
							
						}
						else{							
							;
							}
						}
				catch(Exception e) {
					System.err.println(e + "\nPlease make a valid INPUT!");
				}
				}
					
				
				//if the input doesn't match any of the above commands it would print the error message about the invalid input
				else
				{
					System.err.println("PLEASE MAKE A VALID INPUT!");
				}					
	 
	 }
	scanner.close();
  }
}
		
