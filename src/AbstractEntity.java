import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by james on 2/27/15.
 */
public class AbstractEntity {

    /** The name of the MySQL account to use (or empty for anonymous) */
    private final String userName = "root";

    /** The password for the MySQL account (or empty for anonymous) */
    private final String password = "CS174a";

    /** The name of the computer running MySQL */
    private final String serverName = "localhost";

    /** The port of the MySQL server (default is 3306) */
    private final int portNumber = 3306;

    /** The name of the database we are testing with (this default is installed with MySQL) */
    private final String dbName = "health_information_system";

    /** The name of the table we are testing with */
    private final String tableName = "TestTable";

    /**
     * Get a new database connection
     *
     * @return
     * @throws java.sql.SQLException
     */
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

//    public boolean executeInsertPatient(Connection conn, String co)

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

//		// Create a table
        try {
            String createString =
                    "CREATE TABLE " + this.tableName+ " ( " +
                            "ID INTEGER NOT NULL, " +
                            "FIRSTNAME varchar(100) NOT NULL, " +
                            "LASTNAME varchar(100) NOT NULL, " +
                            "PRIMARY KEY (ID))";
            this.executeUpdate(conn, createString);
            System.out.println("Created a table");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not create the table");
            e.printStackTrace();
            return;
        }

        //input patient
        try {
//            INSERT INTO Books (isbn, title, author, qty_in_stock, price, year_published) VALUES ('2234267890', 'AI: Part 2', 'Joe Moe', '4', 11, 1998);

            String queryString =
//                    "INSERT INTO Patient " +
//                            "(patientid, providerid, patientrole, givenname, familyname, suffix, gender, birthtime, xmlhealthcreation)" +
//                            " VALUES " +
//                            "(1234, 4321, 666, 'james', NULL, 'jr', 'm', '2015-12-31', '2007-12-31 23:59:59');";
                    "INSERT INTO  " + this.tableName + " " +
                            "(ID, FIRSTNAME, LASTNAME)" +
                            " VALUES " +
                            "(1234, 'james', 'thompson');";
            this.executeUpdate(conn, queryString);
            System.out.println("Inserted into testtable");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not insert user");
            e.printStackTrace();
            return;
        }


        // Drop the table
        try {
            String dropString = "DROP TABLE " + this.tableName;
            this.executeUpdate(conn, dropString);
            System.out.println("Dropped the table");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not drop the table");
            e.printStackTrace();
            return;
        }
    }

}
