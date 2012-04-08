package br.com.usp.entity;

import br.com.usp.enumerator.FunctionConsumer;

public class ConsumerLinearFunction extends Consumer{

	public ConsumerLinearFunction(Consumer c) {
		super(c.getId(), c.getName(), c.getListParams(), c.getProbability());
		setType(FunctionConsumer.LINEAR_FUNCTION.getTypeFunction());
	}
	
	public double getResult(Item i)
	{
		// Calcular a funcao
		return 0;
	}
}
