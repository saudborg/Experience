package br.com.usp.entity;

import br.com.usp.enumerator.FunctionConsumer;

public class ConsumerExponentialFunction extends Consumer {

	public ConsumerExponentialFunction(Consumer c) {
		super(c.getId(), c.getName(), c.getListParams(), c.getProbability());
		setType(FunctionConsumer.EXPONENTIAL_FUNCTION.getTypeFunction());
	}
	
	public double getResult(Item i)
	{
		// Calcular a funcao
		return 0;
	}
	
}
