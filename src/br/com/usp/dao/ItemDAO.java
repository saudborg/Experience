package br.com.usp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Saulo Borges
 */
public class ItemDAO {

	public Connection connection;

	public ItemDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Getting the maximum number of items that exists in database this
	 * information is necessary to apply the random method to get aleatory items
	 * 
	 * @return maximum number of items in table
	 */
	public long getNumberOfItems() {
		long count = 0;
		try {
			String sql = "select count(*) from item;";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				count = rs.getLong(1);
		} catch (SQLException e) {
			System.out.println("ERROR ON SELECT COUNT ITEM SCRIPT");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("UNEXPECTED ERROR OCCURRED");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return count;

	}

}
