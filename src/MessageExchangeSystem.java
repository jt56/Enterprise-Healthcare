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

            while (rs.next()) {
                queryResults.clear();

                queryResults.put("MsgId", rs.getString("MsgId"));

                queryResults.put("Last_Accessed", rs.getString("Last_Accessed"));
                //Patient
                queryResults.put("patientId", rs.getString("patientId"));
                //providerid it is lower
                //patientrole
                queryResults.put("GivenName", rs.getString("GivenName"));
                queryResults.put("FamilyName", rs.getString("FamilyName"));
                //suffix set to null
                //gender set to null
                queryResults.put("BirthTime", rs.getString("BirthTime"));
                queryResults.put("providerId", rs.getString("providerId"));
                //xmlhealthcreation is this Last_Accessed?


                //Guardians
                queryResults.put("GuardianNo", rs.getString("GuardianNo"));
                queryResults.put("Relationship", rs.getString("Relationship"));
                queryResults.put("FirstName", rs.getString("FirstName"));
                queryResults.put("LastName", rs.getString("LastName"));
                queryResults.put("phone", rs.getString("phone"));
                queryResults.put("address", rs.getString("address"));
                queryResults.put("city", rs.getString("city"));
                queryResults.put("state", rs.getString("state"));
                queryResults.put("zip", rs.getString("zip"));

                //Authors
                queryResults.put("AuthorId", rs.getString("AuthorId"));
                queryResults.put("AuthorTitle", rs.getString("AuthorTitle"));
                queryResults.put("AuthorFirstName", rs.getString("AuthorFirstName"));
                queryResults.put("AuthorLastName", rs.getString("AuthorLastName"));
                queryResults.put("ParticipatingRole", rs.getString("ParticipatingRole"));

                //Insurance Company
                queryResults.put("PayerId", rs.getString("PayerId"));
                queryResults.put("Name", rs.getString("Name"));
                queryResults.put("PolicyHolder", rs.getString("PolicyHolder")); //not in entity
                queryResults.put("PolicyType", rs.getString("PolicyType"));
                queryResults.put("Purpose", rs.getString("Purpose"));

                //Family History
                queryResults.put("RelativeId", rs.getString("RelativeId"));
                queryResults.put("Relation", rs.getString("Relation"));
                queryResults.put("age", rs.getString("age"));
                queryResults.put("Diagnosis", rs.getString("Diagnosis"));

                //Allergies
                queryResults.put("Id", rs.getString("Id")); //not in entity PK?
                queryResults.put("Substance", rs.getString("Substance"));
                queryResults.put("Reaction", rs.getString("Reaction"));
                queryResults.put("Status", rs.getString("Status"));

                //Lab test reports
                queryResults.put("LabTestResultId", rs.getString("LabTestResultId"));
                queryResults.put("PatientVisitId", rs.getString("PatientVisitId"));
                queryResults.put("LabTestPerformedDate", rs.getString("LabTestPerformedDate"));
                queryResults.put("LabTestType", rs.getString("LabTestType"));
                queryResults.put("TestResultValue", rs.getString("TestResultValue"));
                queryResults.put("ReferenceRangeHigh", rs.getString("ReferenceRangeHigh"));
                queryResults.put("ReferenceRangeLow", rs.getString("ReferenceRangeLow"));

                //Plan
                queryResults.put("PlanId", rs.getString("PlanId"));
                //planpatientid
                queryResults.put("Activity", rs.getString("Activity"));
                queryResults.put("ScheduledDate", rs.getString("ScheduledDate"));


                //call the setmethods that input the data to the DB
                setPatientEntity();
                setGuardianEntity();
                setAuthorEntity();
                setInsuranceEntity();
                setFamilyHistoryEntity();
                setAllergyEntity();
                setLabTestReportEntity();
                setPlanEntity();

            }

            System.out.println("Finished executing query");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not get data");
            e.printStackTrace();
            return;
        }
    }

    private void setAllergyEntity() {
        String[] tableValues = new String[]{
                queryResults.get("Id"), //PK
                queryResults.get("Substance"),
                queryResults.get("patientId"),
                queryResults.get("Reaction"),
                queryResults.get("Status")};

        AllergyEntity e = new AllergyEntity(tableValues);
        e.run();

    }

    private void setAuthorEntity() {
        String[] tableKeys = new String[]{
                queryResults.get("AuthorId"),
                queryResults.get("AuthorTitle"),
                queryResults.get("AuthorFirstName"),
                queryResults.get("AuthorLastName")};

        AuthorEntity e = new AuthorEntity(tableKeys);
        e.run();

        setHasAuthor(); // relation
    }

    private void setFamilyHistoryEntity() {
        String[] tableKeys = new String[]{
                queryResults.get("RelativeId"),
                queryResults.get("patientId"),
                queryResults.get("Relation"),
                queryResults.get("age"),
                queryResults.get("Diagnosis")};

        FamilyHistoryEntity e = new FamilyHistoryEntity(tableKeys);
        e.run();
    }

    private void setGuardianEntity() {
        String[] tableKeys = new String[]{
                queryResults.get("GuardianNo"),
                queryResults.get("patientId"),
                queryResults.get("FirstName"),
                queryResults.get("LastName"),
                queryResults.get("Relationship"), //add to SQL file
                queryResults.get("phone"),
                queryResults.get("address"),
                queryResults.get("city"),
                queryResults.get("state"),
                queryResults.get("zip")};

        GuardianEntity e = new GuardianEntity(tableKeys);
        e.run();

    }

    private void setInsuranceEntity() {

        String[] tableKeys = new String[]{
                queryResults.get("PayerId"),
                queryResults.get("Name"),
                queryResults.get("PolicyHolder"), //add
                queryResults.get("PolicyType"),
        };

        InsuranceCompanyEntity e = new InsuranceCompanyEntity(tableKeys);
        e.run();

        setHasInsuranceCompany(); // add relation
    }

    private void setLabTestReportEntity() {
        String[] tableValues = new String[]{
                queryResults.get("LabTestResultId"),
                queryResults.get("PatientVisitId"),
                queryResults.get("LabTestPerformedDate"),
                queryResults.get("LabTestType"),
                queryResults.get("TestResultValue"),
                queryResults.get("ReferenceRangeHigh"),
                queryResults.get("ReferenceRangeLow")};

        LabTestReportEntity e = new LabTestReportEntity(tableValues);
        e.run();

        setVisitsLab();
    }

    private void setPatientEntity() {
        String[] tableValues = new String[]{
                queryResults.get("patientId"),
                queryResults.get("providerId"),
                queryResults.get("GuardianNo"), //patientrole
                queryResults.get("GivenName"),
                queryResults.get("FamilyName"),
                null, //suffix
                null, //gender
                queryResults.get("BirthTime"),
                queryResults.get("Last_Accessed"), // add to SQL
                null }; //"xmlHealthCreation"};

        PatientEntity p = new PatientEntity(tableValues);
        p.run();
    }

    private void setPlanEntity() {
        String[] tableValues = new String[]{
                queryResults.get("PlanId"),
                queryResults.get("patientId"), //"planpatientid"
                queryResults.get("Activity")};

        PlanEntity e = new PlanEntity(tableValues);
        e.run();

        setHasPlan();
    }

    private void setHasInsuranceCompany(){
        String[] tableValues = new String[]{
                queryResults.get("patientId"),
                queryResults.get("PayerId"),
                queryResults.get("Purpose") //add has_Insurance
        };

        HasInsuranceCompanyRelation e = new HasInsuranceCompanyRelation(tableValues);
        e.run();

    }

    private void setVisitsLab(){
        String[] tableValues = new String[]{
                queryResults.get("patientId"),
                queryResults.get("LabTestResultId")};
        VisitsLabRelation e = new VisitsLabRelation(tableValues);
        e.run();

    }

    private void setHasAuthor(){
        String[] tableValues = new String[]{
                queryResults.get("patientId"),
                queryResults.get("AuthorId"),
                queryResults.get("ParticipatingRole")};
        HasAuthorRelation e = new HasAuthorRelation(tableValues);
        e.run();

    }

    private void setHasPlan(){
        String[] tableValues = new String[]{
                queryResults.get("patientId"),
                queryResults.get("PlanId"),
                queryResults.get("ScheduledDate")}; // in has_plan plandate

        HasPlanRelation e = new HasPlanRelation(tableValues);
        e.run();

    }
}
