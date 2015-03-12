import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public abstract class AbstractEntity {
    
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
    
    /**
     * The name of the table we are testing with
     */
    private static String incomingProperty = "properties/db_config.properties";
    protected String tableName;
    protected String query;
    protected String tableKeys[];
    protected String tableValues[];
    protected Map<String, String> queryResults = new HashMap<String, String>();
    
    private Properties loadEnvProperties (String fileName) throws Exception {
        InputStream is = new FileInputStream(fileName);
        Properties props = new Properties();
        props.load(is);
        is.close();
        return props;
    }

    public void setProperties() {
        File propertyFile = new File(incomingProperty);

        if(propertyFile.exists()) {
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
    
    public void createInputString() {
        int col;
        this.query = "INSERT INTO " + this.tableName + " ( ";
        
        for( col = 0; col < tableKeys.length - 1; col++ ) {
            this.query += this.tableKeys[col] + ", ";
        }
        this.query += this.tableKeys[col] + " ) VALUES ( ";

        for ( col = 0; col < tableValues.length - 1; col++ ) {
            this.query += this.tableValues[col] + ", ";
        }
        this.query += this.tableValues[col] + " );";
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
    
    /**
     * Run a SQL command which does not return a recordset:
     * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
     *
     * @throws SQLException If something goes wrong
     */
    public boolean executeUpdate(Connection conn, String command) throws SQLException {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(command); // This will throw a SQLException if it fails
            return true;
        } finally {

            // This will run whether we throw an exception or not
            if (stmt != null) { stmt.close(); conn.close();}
        }
    }

    /**
     * Connect to MySQL and do some stuff.
     */
    public void run() {

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
        
        // Print query for testing
        System.out.println(this.query);
        
        try {
            this.executeUpdate(conn, this.query);
            System.out.println("Executed query");
        } catch(SQLException e) {
            System.out.println("ERROR: Could not input data");
//            e.printStackTrace();
            return;
        }

        try{
            conn.close();

        } catch (SQLException e){
            System.out.println("Could not close connection");
        }

    }
    
}
