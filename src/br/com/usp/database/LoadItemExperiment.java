package br.com.usp.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

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
			String sql = "insert into item (name, price, distance, validity, serviceproviderrating, isonsale) values (?, ?, ?, ?, ?)";
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
	 * value for the iusuariotem attributes, and then, the method will respect this interval when generating the random values .
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
	    String name = "item ";
		double price = 0.0;
		double distance = 0.0;
		int validity = 0;
		int serviceProviderRating = 0 ;
		double isOnSaleValue = 0;
		boolean isOnSale = false;
		
		System.out.println("***********************************************");
		System.out.println("NUMBER OF ITEMS: "+ numberOfItems);
		System.out.println("PRICE: " + minPrice+ " to "+maxPrice);
		System.out.println("DISTANCE: "+ minDistance+ " to "+maxDistance);
		System.out.println("VALIDITY: "+minValidity+" to "+maxValidity);
		System.out.println("IS ON SALE: true or false");
		System.out.println("***********************************************");
		
		for(int i = 0; i < numberOfItems; i++)
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
						
			/*
			//evidence of test - items conjunct printed on console
			//use this print to validate the random attributes values
			DecimalFormat df = new DecimalFormat("0.00");
			System.out.println(i+"\t Price: "+ df.format(price) + "\t Distance: " + df.format(distance) + "\t Validity: " + validity + 
					   "\t ServiceProviderRating: "+ serviceProviderRating + "\t isOnSaleValue: " + df.format(isOnSaleValue) +
					   "\t isOnSale: " + isOnSale);
			*/
		
			//inserting the register of an item on the database
			name = name + i;
			insertItem(name, price, distance, validity, serviceProviderRating, isOnSale);
		}		   
	}
	
	/**
	 * Main method that will load the database with a specific number of items and their random values 
	 * @param args
	 */
	public static void main(String[] args) {
		long start = Calendar.getInstance().getTimeInMillis();
		LoadItemExperiment load = new LoadItemExperiment();
		load.loadItemTable(1000, 50.0, 200.0, 1.0, 300.0, 30, 360, 1, 5);
		long stop = Calendar.getInstance().getTimeInMillis();;
		System.out.println(">>>>>>>>>>> EXECUTION TIME: "+ (stop - start) + " MILISECONDS <<<<<<<<<<<");
	}
	
}
