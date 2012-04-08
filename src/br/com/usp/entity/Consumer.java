package br.com.usp.entity;

import java.util.List;

public class Consumer {

	private Integer id;
	
	private String name;
	
	private List<Double> listParams;
	
	private double probability;
	
	private int type;

	public Consumer(Integer id, String name, List<Double> listParams, double probability) {
		this.id = id;
		this.name = name;
		this.listParams = listParams;
		this.probability = probability;
	}
	
	public Consumer() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Double> getListParams() {
		return listParams;
	}

	public void setListParams(List<Double> listParams) {
		this.listParams = listParams;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
	public double getResult(Item i)
	{
		return 0;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	
	
	
}
