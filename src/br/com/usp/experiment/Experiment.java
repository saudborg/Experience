package br.com.usp.experiment;

import java.util.List;

import br.com.usp.entity.ConsumerExponentialFunction;
import br.com.usp.entity.ConsumerLinearFunction;

public class Experiment {
	
	static List<ConsumerLinearFunction> consumerLinearList;
	static List<ConsumerExponentialFunction> consumerExponentialList;
	
	private static final int NUMBER_ITERATIONS = 50;
	
	public static void main(String[] args) {
		LoadConsumerPurchasedItemExperiment lcpie = new LoadConsumerPurchasedItemExperiment();
		
		lcpie.loadConsumers(consumerLinearList, consumerExponentialList);
		lcpie.evaluationUsers(NUMBER_ITERATIONS, consumerLinearList, consumerExponentialList);
	}
}
