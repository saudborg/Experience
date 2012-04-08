package br.com.usp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.usp.entity.Consumer;

/**
 * @author Saulo Borges
 */
public class ConsumerDAO {

	public Connection connection;

	public ConsumerDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Create the list of users from the database
	 * 
	 * @return list of consumers
	 */
	public List<Consumer> getListConsumer() {
		try {
			List<Consumer> listConsumers = new ArrayList<Consumer>();
			String sql = "select * from CONSUMER";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Consumer consumer;
			while (rs.next()) {
				consumer = new Consumer();
				consumer.setId(rs.getInt(1));
				consumer.setName(rs.getString(2));
				consumer.setProbability(rs.getDouble(8));
				List<Double> listParams = new ArrayList<Double>();
				listParams.add(rs.getDouble(3));
				listParams.add(rs.getDouble(4));
				listParams.add(rs.getDouble(5));
				listParams.add(rs.getDouble(6));
				listParams.add(rs.getDouble(7));
				consumer.setListParams(listParams);

				listConsumers.add(consumer);
			}

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Getting the maximum number of consumers that exists in database this
	 * information is necessary to apply the random method to get aleatory
	 * consumers
	 * 
	 * @return count in table consumer
	 */
	public long getCountNumberConsumers() {
		long count = 0;
		try {
			String sql = "select count(*) from consumer;";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				count = rs.getLong(1);
		} catch (SQLException e) {
			System.out.println("ERROR ON SELECT COUNT CONSUMER SCRIPT");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("UNEXPECTED ERROR OCCURRED");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return count;
	}
}
