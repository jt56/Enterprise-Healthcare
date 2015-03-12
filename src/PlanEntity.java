import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlanEntity extends AbstractEntity {
    
    // planid, planpatientid, activity

    // FK: planpatientid -> Patient.patientid
    
    private static String planid;
    private static String planpatientid;
    private static String activity;
    
    /*
    #######################
    #   Specific Methods  #
    #######################
    */

    public PlanEntity() {
        this.tableName = "Plan";
        this.tableKeys = new String[]{"planid", "planpatientid", "activity"};
//        this.tableValues = new String[]{"'pid'", "'pat2'", "'act'"};

//        this.createInputString();

    }


    public PlanEntity( String x[] ) {
        this.tableName = "Plan";
        this.tableKeys = new String[]{"planid", "planpatientid", "activity"};

        this.tableValues = new String[tableKeys.length];
        for(int i = 0; i < tableKeys.length; i++ ) {
            if ( x[i] == null ) {
                tableValues[i] = null;
            }
            else {
                tableValues[i] = "'" + x[i] + "'";
            }
        }
        
        this.createInputString();
    }

    public void retrievePlan(String pid ) {

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

            this.query = "SELECT * FROM " + this.tableName;
            this.query += " WHERE planpatientid = " + "'" + pid + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(this.query);


            while (rs.next()) {
                this.tableKeys = new String[]{"planid", "planpatientid", "activity"};

                queryResults.put("planid", rs.getString("planid"));
                queryResults.put("planpatientid", rs.getString("planpatientid"));
                queryResults.put("activity", rs.getString("activity"));
            }

            setAll();

            queryResults.clear();

            System.out.println("Finished executing query");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not get data");
            e.printStackTrace();
            return;
        }
    }
    
    /*
    ######################
    #   Getters/Setters  #
    ######################
    */

    public void setAll(){
        setPlanId(queryResults.get("planid"));
        setPlanPatientId(queryResults.get("planpatientid"));
        setActivity(queryResults.get("activity"));
    }

    public String getPlanId() {
        return planid;
    }

    public void setPlanId(String planid) {
        PlanEntity.planid = planid;
    }

    public String getPlanPatientId() {
        return planpatientid;
    }

    public void setPlanPatientId(String planpatientid) {
        PlanEntity.planpatientid = planpatientid;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        PlanEntity.activity = activity;
    }
    
}
