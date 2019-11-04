import java.util.*;
/**
 * @author Parth Patel(#500893723)
 * the class SalesTeam has all the information on the sales team working at the dealership and contains methods necessary to access the class
 */
public class SalesTeam {
	private LinkedList<String> team;
	int[] salesPersonSales = { 0, 0, 0, 0, 0, 0 };
	
	/**
	 * Constructor method to initialize the LinkedList variable team and adding team members to the team
	 */
	public SalesTeam() {
		team = new LinkedList<String>();
		team.add(0, "Parth");
		team.add(1, "Ron");
		team.add(2, "Hardy");
		team.add(3, "Div");
		team.add(4, "Jay");	
		team.add(5, "Mike");
	}
	
	/**
	 * @return team
	 * this method returns the LinkedList of team
	 */
	public LinkedList<String> getTeamList()
	{
		return team;
	}
	
	
	/**
	 * @return String
	 * This method returns a string of a random team member name
	 */
	public String getRandomSalesPerson() 
	{
		Random random = new Random();
		int index = random.nextInt(6);
		String randomPerson = getTeamList().get(index);
		salesPersonSales[index] += 1;
		return randomPerson;
	}
	
	
	/**
	 * @return String
	 * this method returns a string containing names of all the team members at the CarDealership
	 */
	public String displayTeam() 
	{
		ListIterator<String> iterator = team.listIterator();
		String string = "";
		
		while(iterator.hasNext()) 
		{
			string = string + iterator.next() + " ";
		}
		return "Team: " + string;
	
	}
	
	
	/**
	 * @param i 
	 * @return String
	 * this method returns a name of a specific person required that is the index "i" of the person entered as a parameter
	 */
	public String getPersonName(int i) {
			String name = "";
			try {
				name = team.get(i);
			} 
			catch (IndexOutOfBoundsException j) {
				System.out.println("\nArray out of bounds exception\nPlease enter a valid input");
			}
			catch (Exception e) {
				System.out.println(e);
			}
			
			return name;
		}
		
	
	/**
	 * @param name
	 * @return index
	 * this method returns the index of the person when the method is passed a parameter in the form of Name of the person
	 */
	public int getPersonIndex(String name) 
	{
		String str = "";
		int index = 0;
		for(int i =0; i < 6; i++) 
		{
			str = team.get(i);
			if(str.equals(name)) 
			{
				index = i;
			}
			else {
				;
			}
		}
		return index;
	}
	
	
	/**
	 * @return Stirng name of person
	 * this method returns the name of the person that has sold maximum cars 
	 */
	public String getTopSalesPerson() 
	{
	        int highest = 0, indexOfHighest = 0;
	        String result = "";
	        for (int i = 0; i < team.size(); i++) 
	        { // finds the highest value of sales in the list of sales persons
	            if (salesPersonSales[i] > highest) 
	            {
	                indexOfHighest = i;
	                highest = salesPersonSales[i];
	            }
	        }
	        result = team.get(indexOfHighest);
	        for (int i = 0; i < team.size(); i++) { // checks if any sales person also has the highest number (ties)
	            if (salesPersonSales[i] == highest && i != indexOfHighest) 
	            {
	                result = result + " " + team.get(i);
	            }
	        }
	        return "Top Sales Person(s): " + result + " with Highest Sales Number: " + highest;
	    }

}
