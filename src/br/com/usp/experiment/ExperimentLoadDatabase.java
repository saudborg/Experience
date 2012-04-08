package br.com.usp.experiment;

import java.util.Calendar;

public class ExperimentLoadDatabase {
	
	public static void main(String[] args) {
		
		long start = Calendar.getInstance().getTimeInMillis();
        		
		LoadConsumerExperiment loadConsumer = new LoadConsumerExperiment();
        //creating 20 random consumers
		loadConsumer.loadConsumerTable(20);
		
		LoadItemExperiment loadItem = new LoadItemExperiment();
		//creating 1000 items
		//the price will be between $50 to $200
		//the service provider distance to the client will be between 1km to 300km
		//the service provider rating will be between 1 to 5
		loadItem.loadItemTable(1000, 50.0, 200.0, 1.0, 300.0, 30, 360, 1, 5);
		
		//TODO: Nao tirei essa parte ainda...
//		LoadConsumerPurchasedItemExperiment loadConsumerPurchasedItemExperiment = new LoadConsumerPurchasedItemExperiment();
//		// 2 consumers and 50 items for each one  
//		loadConsumerPurchasedItemExperiment.loadConsumerPurchasedItemTable(2, 50);
//	
		long stop = Calendar.getInstance().getTimeInMillis();
        System.out.println(">>>>>>>>>>> EXECUTION TIME: " + (stop - start) + " MILLISECONDS <<<<<<<<<<<");
	
	}
}
