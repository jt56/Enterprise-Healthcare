import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AllergyEntity extends AbstractEntity {

    // substance, patientid, reaction, status

    // FK: patientid -> Patient.patientid

    private static String id;
    private static String substance;
    private static String patientid;
    private static String reaction;
    private static String status;

    /*
    #######################
    #   Specific Methods  #
    #######################
    */

    public AllergyEntity() {
        this.tableName = "Allergies";
        this.tableKeys = new String[]{"id", "substance", "patientid", "reaction", "status"};
//        this.tableValues = new String[]{"'id'", "'sub'", "'pat2'", "'rct'", "'stat'"};

//        this.createInputString();

    }


    public AllergyEntity( String x[] ) {
        this.tableName = "Allergies";
        this.tableKeys = new String[]{"id", "substance", "patientid", "reaction", "status"};

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

    public void retrieveAllergy(String pid, boolean print){
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
            this.query += " WHERE patientid = " + "'" + pid + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(this.query);


            while (rs.next()) {
                this.tableKeys = new String[]{"id", "substance", "patientid", "reaction", "status"};

                queryResults.put("id", rs.getString("id"));
                queryResults.put("substance", rs.getString("substance"));
                queryResults.put("patientid", rs.getString("patientid"));
                queryResults.put("reaction", rs.getString("reaction"));
                queryResults.put("status", rs.getString("status"));
            }

            setAll();
            if(print){
                printAllergyData(pid);
            }

            queryResults.clear();

            System.out.println("Finished executing query");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not get data");
            e.printStackTrace();
            return;
        }
    }

    public void printAllergyData(String userId ){
//        this.tableKeys = new String[]{"id", "substance", "patientid", "reaction", "status"};
//        retrieveAllergy(userId, true);

        System.out.println("ALLERGY INFO");
        
        System.out.print("id: ");
        System.out.println(getId());

        System.out.print("substance: ");
        System.out.println(getSubstance());

        System.out.print("patientid: ");
        System.out.println(getPatientId());

        System.out.print("reaction: ");
        System.out.println(getReaction());

        System.out.print("status: ");
        System.out.println(getStatus() + "\n");
    }
    /*
    ######################
    #   Getters/Setters  #
    ######################
    */

    public void setAll(){
        setId(queryResults.get("id"));
        setSubstance(queryResults.get("substance"));
        setPatientId(queryResults.get("patientid"));
        setReaction(queryResults.get("reaction"));
        setStatus(queryResults.get("status"));
    }

    public String getId() { return id; }

    public void setId(String id) { AllergyEntity.id = id; }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        AllergyEntity.substance = substance;
    }

    public String getPatientId() {
        return patientid;
    }

    public void setPatientId(String patientid) {
        AllergyEntity.patientid = patientid;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        AllergyEntity.reaction = reaction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        AllergyEntity.status = status;
    }
}
