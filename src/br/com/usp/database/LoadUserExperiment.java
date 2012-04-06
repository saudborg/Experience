package br.com.usp.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;

/**
 * 
 * @author Lucas Biccio Ribeiro
 * @author Saulo Dias Borges
 * @since 05/04/2012
 * @version 1.0 Class responsible for load the Consumer database
 */
public class LoadUserExperiment {

    /**
     * Method responsible for insert the register into the database
     * 
     * @param name - consumer´s name
     * @param paramA - reference to item's price
     * @param paramB - reference to item's distance
     * @param paramC - reference to item's validaty
     * @param paramD - reference to item's service provider
     * @param paramE - reference to item is available to sale or not
     */
    public void insertConsumer(String name, double paramA, double paramB, double paramC, double paramD, double paramE) 
    {
        try {
            String sql = "insert into consumer (name, paramA, paramB, paramC, paramD, paramE) values (? , ? , ? , ? , ? , ?);";
            ConnectionFactory factory = new ConnectionFactory();
            PreparedStatement stmt = factory.getConnection().prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setDouble(2, paramA);
            stmt.setDouble(3, paramB);
            stmt.setDouble(4, paramC);
            stmt.setDouble(5, paramD);
            stmt.setDouble(6, paramE);
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
     * Method responsible for load the consumer database. It recive as an input the number of consumer must be created.
     * 
     * @param numberOfConsumers - the number of consumer that the method will generate
     */
    public void loadUserTable(int numberOfUsers) {
        String name = "user";
        double paramA = 0;
        double paramB = 0;
        double paramC = 0;
        double paramD = 0;
        double paramE = 0;

        for (int i = 0; i < numberOfUsers; i++) {
            // requiring the random values for the variables to move to the attributes
            Random r = new Random();
            paramA = r.nextDouble();
            paramB = r.nextDouble();
            paramC = r.nextDouble();
            paramD = r.nextDouble();
            paramE = r.nextDouble();

            normalize(paramA, paramB, paramC, paramD, paramE);
            String nameLinearUser = name + i;

            // inserting the register of an item on the database
            insertConsumer(nameLinearUser, paramA, paramB, paramC, paramD, paramE);
        }
    }

    /**
     * Method responsible for normalizing the attributes for that the sum of all equals 1
     * 
     * @param paramA
     * @param paramB
     * @param paramC
     * @param paramD
     * @param paramE
     */
    private void normalize(double paramA, double paramB, double paramC, double paramD, double paramE) {
        double total = paramA + paramB + paramC + paramD + paramE;
        if (total > 1) {
            paramA = paramA / total;
            paramB = paramB / total;
            paramC = paramC / total;
            paramD = paramD / total;
            paramE = paramE / total;
        }
    }
    
    
    /**
     * Main method
     * 
     * @param args
     */
    public static void main(String[] args) {
        long start = Calendar.getInstance().getTimeInMillis();
        LoadUserExperiment load = new LoadUserExperiment();
        load.loadUserTable(20);
        long stop = Calendar.getInstance().getTimeInMillis();
        ;
        System.out.println(">>>>>>>>>>> EXECUTION TIME: " + (stop - start) + "MILISECONDS <<<<<<<<<<<");
    }

}
