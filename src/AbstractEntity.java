import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public abstract class AbstractEntity {

    /**
     * The name of the MySQL account to use (or empty for anonymous)
     */
    private final String userName = "root";

    /**
     * The password for the MySQL account (or empty for anonymous)
     */
    private final String password = "CS174a";

    /**
     * The name of the computer running MySQL
     */
    private final String serverName = "localhost";

    /**
     * The port of the MySQL server (default is 3306)
     */
    private final int portNumber = 3306;

    /**
     * The name of the database we are testing with (this default is installed with MySQL)
     */
    private final String dbName = "health_information_system";
    
    /**
     * The name of the table we are testing with
     */
    protected String tableName;

    protected String query;
    
    protected String tableKeys[];
    
    protected String tableValues[];


    public void createInputString() {
        this.query = "INSERT INTO " + this.tableName + " ( ";

        int i;

        for( i = 0; i < tableKeys.length - 1; i++ ) {
            this.query += this.tableKeys[i] + ", ";
        }

        this.query += this.tableKeys[i] + " ) VALUES ( ";

        for ( i = 0; i < tableValues.length - 1; i++ ) {
            this.query += "'" +  this.tableKeys[i] + "', ";
        }

        this.query += "'" +  this.tableValues[i] + "' );";
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
            if (stmt != null) { stmt.close(); }
        }
    }

    /**
     * Connect to MySQL and do some stuff.
     */
    public void run() {

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
            e.printStackTrace();
            return;
        }

    }
    
}
