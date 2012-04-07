package br.com.usp.experiment;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Lucas Biccio Ribeiro
 * @version 1.0 Class responsible for generate a test mass of items
 * @since 05/04/2012
 */
public class LoadItemExperiment {

	/**
	 * Method responsible to insert the item register into the database
	 * @param name - item description 
	 * @param price - the price of a item calculated in R$
	 * @param distance - the distance between the client and the service provider location - calculated in KM
	 * @param validity - the period that service is valid, counted in number of calendar days
	 * @param serviceProviderRating - the score of a service provider - a category attribute, 
	 * Example:				1 - terrible
	 *						2 - bad
	 *						3 - normal
	 *						4 - good
	 *						5 - excellent
	 * @param isOnSale - the flag of a service that indicates it´s on sale or not
	 */
	public void insertItem(String name, double price, double distance, int validity, int serviceProviderRating, boolean isOnSale)
	{
		try{
			String sql = "insert into item (name, price, distance, validity, serviceproviderrating, isonsale) values (?, ?, ?, ?, ?, ?)";
			ConnectionFactory factory = new ConnectionFactory();
			PreparedStatement stmt = factory.getConnection().prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setDouble(2, price);
			stmt.setDouble(3, distance);
			stmt.setInt(4, validity);
			stmt.setInt(5, serviceProviderRating);
			stmt.setBoolean(6, isOnSale);
			stmt.execute();
		}catch (SQLException e) {
			System.out.println("ERROR ON INSERT SCRIPT");
			System.out.println(e.getMessage());
		}catch (Exception e) {
			System.out.println("UNEXPECTED ERROR OCCURRED");
			System.out.println(e.getMessage());
			e.printStackTrace();	
		}
	}
	
	/**
	 * Method responsible to generate the random values for the item attribute and insert it on a database.
	 * In addiction, this function has the control of the range values, where we can inform the minimum and the maximum
	 * value for the attributes, and then, the method will respect this interval when generating the random values .
	 * 
	 * @param numberOfItems - number of items that the method will generate
	 * @param minPrice - minimum price
	 * @param maxPrice - maximum price
	 * @param minDistance - minimum distance
	 * @param maxDistance - maximum distance
	 * @param minValidity - minimum validity
	 * @param maxValidity - maximum validity
	 * @param minServiceProviderRating - minimum service provider ranting
	 * @param maxServiceProviderRating - maximum service provider ranting
	 */
	public void loadItemTable (int numberOfItems, double minPrice, double maxPrice, double minDistance, double maxDistance,
											int minValidity, int maxValidity, int minServiceProviderRating, int maxServiceProviderRating)
	{
	    double price = 0.0;
		double distance = 0.0;
		int validity = 0;
		int serviceProviderRating = 0 ;
		double isOnSaleValue = 0;
		boolean isOnSale = false;
		
		for(int i = 1; i < numberOfItems + 1; i++)
		{
			//requiring the random values for the variables to move to the attributes
			price = minPrice + (((maxPrice - minPrice) + 1) * Math.random());
			if(price > maxPrice)
				price = maxPrice;
			distance = minDistance + (((maxDistance - minDistance) + 1) * Math.random());
			if(distance > maxDistance)
				distance = maxDistance;
			validity =  (int) (minValidity + (((maxValidity - minValidity) + 1) * Math.random()));
			serviceProviderRating = (int) (minServiceProviderRating + (((maxServiceProviderRating - minServiceProviderRating) + 1) * Math.random()));
			isOnSaleValue = Math.random();
			if(isOnSaleValue >= 0.5)
				isOnSale = true;
			else
				isOnSale = false;
			String nameItem = "item" + i;
			
			//inserting the register of an item on the database
			insertItem(nameItem, price, distance, validity, serviceProviderRating, isOnSale);
		}		   
	}	
}
