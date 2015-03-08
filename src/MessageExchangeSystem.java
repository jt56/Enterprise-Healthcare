import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Interface between the message database and the health information database
 */
public class MessageExchangeSystem {

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

    private Map<String, String> queryResults = new HashMap<String, String>();
    /**
     * The name of the table we are testing with
     */
    private static String incomingProperty = "properties/db_config.properties";
    protected String tableName;
    protected String query;
    protected String tableKeys[];
    protected String tableValues[];

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
            this.dbName = props.getProperty("app.dmmessages");
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

        try {
            tableName = "messages";
            query = "SELECT * FROM " + tableName;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);


            while (rs.next()){
                queryResults.clear();

                queryResults.put("MsgId", rs.getString("MsgId"));

                queryResults.put("Last_Accessed", rs.getString("Last_Accessed"));
                queryResults.put("patientId", rs.getString("patientId"));
                queryResults.put("GivenName", rs.getString("GivenName"));
                queryResults.put("FamilyName", rs.getString("FamilyName"));
                queryResults.put("BirthTime", rs.getString("BirthTime"));
                queryResults.put("providerId", rs.getString("providerId"));
                queryResults.put("GuardianNo", rs.getString("GuardianNo"));
                queryResults.put("Relationship", rs.getString("Relationship"));
                queryResults.put("FirstName", rs.getString("FirstName"));
                queryResults.put("LastName", rs.getString("LastName"));
                queryResults.put("phone", rs.getString("phone"));
                queryResults.put("address", rs.getString("address"));
                queryResults.put("city", rs.getString("city"));
                queryResults.put("state", rs.getString("state"));
                queryResults.put("zip", rs.getString("zip"));

                queryResults.put("AuthorId", rs.getString("AuthorId"));
                queryResults.put("AuthorTitle", rs.getString("AuthorTitle"));
                queryResults.put("AuthorFirstName", rs.getString("AuthorFirstName"));
                queryResults.put("AuthorLastName", rs.getString("AuthorLastName"));
                queryResults.put("ParticipatingRole", rs.getString("ParticipatingRole"));

                queryResults.put("PayerId", rs.getString("PayerId"));
                queryResults.put("Name", rs.getString("Name"));
                queryResults.put("PolicyHolder", rs.getString("PolicyHolder"));
                queryResults.put("PolicyType", rs.getString("PolicyType"));
                queryResults.put("Purpose", rs.getString("Purpose"));
                queryResults.put("RelativeId", rs.getString("RelativeId"));
                queryResults.put("Relation", rs.getString("Relation"));
                queryResults.put("age", rs.getString("age"));
                queryResults.put("Diagnosis", rs.getString("Diagnosis"));

                queryResults.put("Id", rs.getString("Id"));
                queryResults.put("Substance", rs.getString("Substance"));
                queryResults.put("Reaction", rs.getString("Reaction"));
                queryResults.put("Status", rs.getString("Status"));
                queryResults.put("LabTestResultId", rs.getString("LabTestResultId"));
                queryResults.put("PatientVisitId", rs.getString("PatientVisitId"));
                queryResults.put("LabTestPerformedDate", rs.getString("LabTestPerformedDate"));
                queryResults.put("LabTestType", rs.getString("LabTestType"));
                queryResults.put("TestResultValue", rs.getString("TestResultValue"));
                queryResults.put("ReferenceRangeHigh", rs.getString("ReferenceRangeHigh"));
                queryResults.put("ReferenceRangeLow", rs.getString("ReferenceRangeLow"));
                queryResults.put("PlanId", rs.getString("PlanId"));
                queryResults.put("Activity", rs.getString("Activity"));
                queryResults.put("ScheduledDAte", rs.getString("ScheduledDAte"));

                //Test output
                System.out.println(queryResults.get("Last_Accessed"));
            }

            System.out.println("Finished executing query");
        } catch(SQLException e) {
            System.out.println("ERROR: Could not get data");
            e.printStackTrace();
            return;
        }
    }


    private void setAllergyEntity (){

    }
    private void setAuthorEntity (){

    }
    private void setFamilyHistoryEntity (){

    }
    private void setGuardianEntity (){

    }
    private void setInsuranceEntity (){

    }
    private void setLabTestReportEntity(){

    }
    private void setPatientEntity(){

    }
    private void setPlanEntity(){

    }

}
