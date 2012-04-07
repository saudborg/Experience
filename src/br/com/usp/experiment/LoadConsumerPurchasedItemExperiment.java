package br.com.usp.experiment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author Lucas Biccio Ribeiro
 * @since 06/04/2012
 * @version 1.0
 * Class responsible for load and store the mass test of item that as purchased by some user in a random way
 */
public class LoadConsumerPurchasedItemExperiment {

	/**
	 * Method responsible for insert the relation of item that was bought by some consumer
	 * @param idConsumer - consumer큦 id
	 * @param idItem - item큦 id
	 */
	public void insertConsumerPurchasedItem(long idConsumer, long idItem)
	{
		try{
			String sql = "insert into consumer_purchased_item(id_consumer, id_item, purchased_date) values (?, ?, current_timestamp); ";
			ConnectionFactory factory = new ConnectionFactory();
			PreparedStatement stmt = factory.getConnection().prepareStatement(sql);
			stmt.setLong(1, idConsumer);
			stmt.setLong(2, idItem);
			stmt.execute();
		}catch (SQLException e) {
            System.out.println("ERROR ON INSERT CONSUMER PURCHASED ITEM SCRIPT");
            System.out.println(e.getMessage());
        }catch (Exception e) {
            System.out.println("UNEXPECTED ERROR OCCURRED");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }			
	}
	
	/**
	 * Method responsible for insert the preference of consumer by a choice of 2 items
	 * @param idConsumer - consumer큦 id
	 * @param idItemI - i item큦 id
	 * @param idItemJ - j item큦 id
	 * @param idPreferedItem - preferred item큦 id, in case of no choice the system puts the value '?'   
	 */
	public void insertConsumerPreference (long idConsumer, long idItemI, long idItemJ, String idPreferredItem)
	{
		try{
			String sql = "insert into consumer_preference(id_consumer, id_item_i, id_item_j, id_item_prefered) values (?, ?, ?, ?); ";
			ConnectionFactory factory = new ConnectionFactory();
			PreparedStatement stmt = factory.getConnection().prepareStatement(sql);
			stmt.setLong(1, idConsumer);
			stmt.setLong(2, idItemI);
			stmt.setLong(3, idItemJ);
			stmt.setString(4, idPreferredItem);
			stmt.execute();
		}catch (SQLException e) {
            System.out.println("ERROR ON INSERT CONSUMER PREFERENCE SCRIPT");
            System.out.println(e.getMessage());
        }catch (Exception e) {
            System.out.println("UNEXPECTED ERROR OCCURRED");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
	}
	
	public String elicitation (long idItemI, long idItemJ)
	{
		String result = "";
		double preferenceRate = Math.random();
		if(preferenceRate >= 0.5)
			result = Long.toString(idItemI);
		else
		{
			if(preferenceRate == 0)
				result = "?";
			result = Long.toString(idItemJ);
		}
		return result;
	}		
	
	/**
	 * Method responsible for load the relation of item that was bought by some consumer
	 * @param numberOfConsumers - number Of Consumers that bought something
	 * @param numberOfItemByConsumer - numbers of items that was purchased by each consumer, 
	 * example: if numberOfconsumerItemByConsumer = 50, each consumer has bought 50 items 
	 */
	public void loadConsumerPurchasedItemTable(int numberOfConsumers, int numberOfItemByConsumer) 
	{
		HashMap<Long, Long> consumerHashMap = new HashMap<Long, Long>();
		long numberOfConsumerInTable = 0;
		long numberOfItemInTable = 0;
		int counterConsumer = 0;
		long counterItem = 0;
		
		//getting the maximum number of consumers that exists in database
		//this information is necessary to apply the random method to get aleatory consumers
		try{
			String sql = "select count(*) from consumer;";
			ConnectionFactory factory = new ConnectionFactory();
			PreparedStatement stmt = factory.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				numberOfConsumerInTable = rs.getLong(1);
		}catch (SQLException e) {
            System.out.println("ERROR ON SELECT COUNT CONSUMER SCRIPT");
            System.out.println(e.getMessage());
        }catch (Exception e) {
            System.out.println("UNEXPECTED ERROR OCCURRED");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
		
		//getting the costumer in a random way and set them into a hashMap
		while(counterConsumer != numberOfConsumers)
		{
			long randomIndex = (long) (1 + ((numberOfConsumerInTable) * Math.random()));
			if(consumerHashMap.containsValue(randomIndex) == false);
			{
				consumerHashMap.put((long) counterConsumer, randomIndex);
				counterConsumer++;
			}
		}
		
		//getting the maximum number of items that exists in database
		//this information is necessary to apply the random method to get aleatory items
		try{
			String sql = "select count(*) from item;";
			ConnectionFactory factory = new ConnectionFactory();
			PreparedStatement stmt = factory.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				numberOfItemInTable = rs.getLong(1);
		}catch (SQLException e) {
            System.out.println("ERROR ON SELECT COUNT ITEM SCRIPT");
            System.out.println(e.getMessage());
        }catch (Exception e) {
            System.out.println("UNEXPECTED ERROR OCCURRED");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
		
		//walking around consumerHashMap to set them some items
		for(long i = 0; i < numberOfConsumers; i++)
		{	
			if(consumerHashMap.get(i)!=null)
			{
				while(counterItem != numberOfItemByConsumer )
				{	//getting two items in a random way
					long randomIndexI = (long) (1 + ((numberOfItemInTable) * Math.random()));
					long randomIndexJ = (long) (1 + ((numberOfItemInTable) * Math.random()));
					//choosing one of the two items gotten
					String itemPreferred = elicitation(randomIndexI, randomIndexJ);
					//inserting into preference table
					insertConsumerPreference(consumerHashMap.get(i), randomIndexI, randomIndexJ, itemPreferred);
					//if the consumer could not choose one of those two items, do not insert into consumer purchased item table
					if(!itemPreferred.equals("?"))
						insertConsumerPurchasedItem(consumerHashMap.get(i), Long.parseLong(itemPreferred));
					counterItem++;
				}
			}
			counterItem = 0;
		}																	
	}
}
