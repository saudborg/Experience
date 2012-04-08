package br.com.usp.factory;

import java.sql.SQLException;

import br.com.usp.dao.ConsumerDAO;
import br.com.usp.dao.ItemDAO;
import br.com.usp.experiment.ConnectionFactory;

/**
 * 
 * 
 * @author Saulo Borges
 *
 */
public class DAOFactory {
	
	private static DAOFactory instance = null;
	
	private ConnectionFactory cf = new ConnectionFactory();
	
	private ConsumerDAO consumerDAO;
	
	private ItemDAO itemDAO;
	
	public static DAOFactory getInstance()
	{
		if(instance == null)
			instance = new DAOFactory();
		return instance;
	}
	
	public ConsumerDAO getConsumerDAO()
	{
		try {
			consumerDAO = new ConsumerDAO(cf.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return consumerDAO;
	}
	
	public ItemDAO getItemDAO()
	{
		try {
			itemDAO = new ItemDAO(cf.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemDAO;
	}

}
