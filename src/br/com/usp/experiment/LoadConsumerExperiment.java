package br.com.usp.experiment;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Lucas Biccio Ribeiro
 * @author Saulo Dias Borges
 * @since 05/04/2012
 * @version 1.0 by Lucas Biccio Ribeiro - Class responsible for load the Consumer database
 * @version 2.0 by Saulo Dias Borges - Maintenance on methodConsumer and elaboration of the method normalize 
 * @version 3.0 BY Lucas Biccio Ribeiro - Maintenance on method normalize and methodConsumer
 */
public class LoadConsumerExperiment {

    /**
     * Method responsible for insert the register into the database
     * 
     * @param name - consumer´s name
     * @param paramA - reference to item's price
     * @param paramB - reference to item's distance
     * @param paramC - reference to item's validity
     * @param paramD - reference to item's service provider
     * @param paramE - reference to item is available to sale or not
     * @param probability - the probability of been the wanted consumer by searching the consumer preferred items
     */
    public void insertConsumer(String name, double paramA, double paramB, double paramC, double paramD, double paramE, double probability) 
    {
        try {
            String sql = "insert into consumer (name, paramA, paramB, paramC, paramD, paramE, probability) values (? , ? , ? , ? , ? , ?, ?);";
            ConnectionFactory factory = new ConnectionFactory();
            PreparedStatement stmt = factory.getConnection().prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setDouble(2, paramA);
            stmt.setDouble(3, paramB);
            stmt.setDouble(4, paramC);
            stmt.setDouble(5, paramD);
            stmt.setDouble(6, paramE);
            stmt.setDouble(7, probability);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("ERROR ON INSERT SCRIPT");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("UNEXPECTED ERROR OCCURRED");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method responsible for load the consumer database. It receive as an input the number of consumer must be created.
     * @param numberOfConsumers - the number of consumer that the method will generate
     */
    public void loadConsumerTable(double numberOfConsumers) {
        ArrayList<Double> parameterList = new ArrayList<Double>();
    	double paramA = 0;
        double paramB = 0;
        double paramC = 0;
        double paramD = 0;
        double paramE = 0;
        double probability = 1.0 / numberOfConsumers;
        
        for (int i = 1; i < numberOfConsumers + 1; i++) {
            // requiring the random values for the variables to move to the attributes
            // the method nextDouble from class Random return a double value bigger than 0 and equal or lower than zero.
        	Random r = new Random();
            paramA = r.nextDouble();
            paramB = r.nextDouble();
            paramC = r.nextDouble();
            paramD = r.nextDouble();
            paramE = r.nextDouble();
            String nameLinearUser = "consumer" + i;

            // invoke the method normalize to consist the sum of the parameters, it must return a list of all parameters normalized.       
            parameterList = normalize(paramA, paramB, paramC, paramD, paramE);
            
            // inserting the register of an item on the database
            insertConsumer(nameLinearUser, parameterList.get(0), parameterList.get(1), parameterList.get(2),
            		parameterList.get(3), parameterList.get(4), probability);
        }
    }

    /**
     * Method responsible for normalizing the parameters passed
     * @param parameters
     * @return a {@link ArrayList} of {@link Double} order by the order elements passed
     */
    private ArrayList<Double> normalize(double... parameters) {
    	ArrayList<Double> parameterList = new ArrayList<Double>();
    	
    	//quantity of parameters passed as an input to normalize
    	int size = parameters.length;
    	double total = 0.0;
    	
    	//summing the parameters values into variable total
    	for(int i = 0; i < size; i++)
    		total = total + parameters[i];
    	
    	//if total > 1, return a list order by the parameters normalized
    	if(total > 1)
    	{
	    	for(int i = 0; i < size; i++)
	    		parameterList.add(parameters[i]/total);
    	}
    	return parameterList;
    }
        
}
