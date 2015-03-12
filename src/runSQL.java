import java.io.*;
import java.sql.*;
import java.util.Properties;

import org.apache.ibatis.jdbc.ScriptRunner;


/**
 * Runs a given script on the database
 */


public class runSQL {
    /**
     * The name of the MySQL account to use (or empty for anonymous)
     */
    private static String userName;

    /**
     * The password for the MySQL account (or empty for anonymous)
     */
    private static String password;

    /**
     * The name of the computer running MySQL
     */
    private static String serverName;

    /**
     * The port of the MySQL server (default is 3306)
     */
    private static int portNumber;

    /**
     * The name of the database we are testing with (this default is installed with MySQL)
     */
    private static String dbName;

    private static String incomingProperty = "properties/db_config.properties";


    private Properties loadEnvProperties(String fileName) throws Exception {
        InputStream is = new FileInputStream(fileName);
        Properties props = new Properties();
        props.load(is);
        is.close();
        return props;
    }

    public void setProperties() {
        File propertyFile = new File(incomingProperty);

        if (propertyFile.exists()) {
            Properties props = new Properties();

            try {
                props = loadEnvProperties(incomingProperty);
            } catch (Exception e) {
                throw new RuntimeException(incomingProperty + " does not exist.");
            }

            this.portNumber = Integer.parseInt(props.getProperty("app.port"));
            this.userName = props.getProperty("app.user");
            this.password = props.getProperty("app.pass");
            this.serverName = props.getProperty("app.server");
            this.dbName = props.getProperty("app.db");
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        conn = DriverManager.getConnection("jdbc:mysql://"
                        + this.serverName + ":" + this.portNumber + "/" + this.dbName,
                connectionProps);

        return conn;
    }

    public void run(String filePath) {

        setProperties();

        // Connect to MySQL
        Connection conn = null;
        try {
            conn = this.getConnection();
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not connect to the database");
            e.printStackTrace();
            return;
        }

        try{
            ScriptRunner runner = new ScriptRunner(conn);
            System.out.println("Executed " + filePath);
            runner.runScript(new BufferedReader(new FileReader(filePath)));
        } catch (Exception e){
            System.err.println("Error: file not found " + filePath);
            e.printStackTrace();
        }


    }

}
