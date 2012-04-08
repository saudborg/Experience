package br.com.usp.enumerator;

public enum FunctionConsumer {

	LINEAR_FUNCTION(1),
	
	EXPONENTIAL_FUNCTION(2);
	
	private FunctionConsumer(int type) {
		typeFunction = type;
	}
	
	public void setTypeFunction(int typeFunction) {
		this.typeFunction = typeFunction;
	}

	public int getTypeFunction() {
		return typeFunction;
	}

	private int typeFunction;
	
	
	
}
