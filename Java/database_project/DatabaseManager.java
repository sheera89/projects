/*
 * Sandeep Heera
 * 6/1/2017
 * DatabaseManager.java
 * This class is meant to be a connection manager to a mysql database.
 * The constants should be changed to match the credentials and information
 * corresponding to the current user.
 */
package WorkoutTrackingSystem;
import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * This class is a connection manager which interfaces a Java application with a 
 * mysql server.
 * 
 * @author Sandeep Heera
 */
public class DatabaseManager{
    private final String FILE_NAME = "credentials.txt";
    private final String DATABASE_NAME = "heera_sandeep_db";
    private final int NUM_ARGUMENTS = 4;
    private final int USER_NAME_INDEX = 0;
    private final int PASSWORD_INDEX = 1;
    private final int IP_ADDRESS_INDEX = 2;
    private final int PORT_INDEX = 3;
    private Connection adminConnection;
   
    private String adminUserName;
    private String adminPassword;
    private String adminIPAddress;
    private String adminPort;
    private String fullAddress;
    private Scanner scanner;
    
    /**
     * Default constructor. 
     * 
     * @throws SQLException
     * @throws FileNotFoundException 
     */
    public DatabaseManager() throws SQLException, FileNotFoundException{
        this.getFileScanner();
        adminUserName = new String();
        adminPassword = new String();
        adminIPAddress = new String();
        adminPort = new String();
        fullAddress = new String();
     
        for(int i = 0;i < NUM_ARGUMENTS;i++){
            String parsedInput = scanner.next();
            switch(i){
                case USER_NAME_INDEX:
                    parsedInput = scanner.next();
                    adminUserName += parsedInput;
                    break;
                case PASSWORD_INDEX:
                    parsedInput = scanner.next();
                    adminPassword += parsedInput;
                    break;
                case IP_ADDRESS_INDEX:
                    parsedInput = scanner.next();
                    adminIPAddress += parsedInput;
                    break;
                case PORT_INDEX:
                    parsedInput = scanner.next();
                    adminPort += parsedInput;
                    break;
                default:
                    System.out.println("Error parsing credentials.txt.");
                    break;
            }
        }
        fullAddress += "jdbc:mysql://";
        fullAddress += adminIPAddress;
        fullAddress += ":" + adminPort + "/" + DATABASE_NAME +
                       "?autoReconnect=true&useSSL=false";
    }
    
    /**
     * Returns the connection to the mysql server.
     * @return the connection to the mysql server.
     */
    public Connection getAdminConnection(){
        return adminConnection;
    }
    
    /**
     * Closes the connection with the mysql server.
     * 
     * @throws SQLException 
     */
    public void closeConnection() throws SQLException{
        adminConnection.close();
    }
    
    /**
     * Sets the FileScanner attribute. This scanner will be utilized to parse
     * the credentials of the user which are found in the credentials.txt file.
     * 
     * @throws FileNotFoundException 
     */
    public void getFileScanner() throws FileNotFoundException{
        try{
            scanner = new Scanner(new File(FILE_NAME)).useDelimiter("\"");
        } catch(FileNotFoundException e){
            System.out.println("File not found.");
            System.out.println(e.getStackTrace());
        }
    }
    
    /**
     * Connects to the mysql server.
     * 
     * @throws SQLException 
     */
    public void createConnection() throws SQLException{
        adminConnection = DriverManager.getConnection(
                            fullAddress,
                            adminUserName,
                            adminPassword);
    }
}
