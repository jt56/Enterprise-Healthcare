import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class PatientView extends AbstractEntity {

    private Map<String, String> login = new HashMap<String, String>();
    
    private PatientEntity pat;
    
    private GuardianEntity g;
    
    public PatientView( String userId ) {
        System.out.println();
        retrieveLogin();
        System.out.println();
        
        if ( login.get(userId) == null ) {
            System.out.println("User does not exist\n");
            return;
        }
        
        pat = new PatientEntity();
        
        g = new GuardianEntity();

        g.retrieveGuardian( userId );

        printData(userId);
    }

    public void retrieveLogin() {
        System.out.println("Entered Retrieval");
        
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

            this.query = "SELECT patientid FROM Patient";
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(this.query);

            while (rs.next()) {
                login.put(rs.getString("patientid"), rs.getString("patientid"));
            }
            

            System.out.println("Finished executing query");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not get data");
            e.printStackTrace();
            return;
        }
    }
    
    public void printData( String userId ) {
        // patientid, providerid, patientrole, givenname, familyname, suffix, gender, birthtime, lastaccessed, xmlHealthCreation
        pat.retrievePatient(userId);
        System.out.println("\nWelcome " + userId + "\n Below is your information:");


        System.out.print("patientid: ");
        System.out.println(pat.getPatientId());

        System.out.print("providerid: ");
        System.out.println(pat.getProviderId());

        System.out.print("patientrole: ");
        System.out.println(pat.getPatientRole());

        System.out.print("givenname: ");
        System.out.println(pat.getGivenName());

        System.out.print("familyname: ");
        System.out.println(pat.getFamilyName());

        System.out.print("suffix: ");
        System.out.println(pat.getSuffix());

        System.out.print("gender: ");
        System.out.println(pat.getGender());

        System.out.print("birthtime: ");
        System.out.println(pat.getBirthTime());

        System.out.print("lastaccessed: ");
        System.out.println(pat.getLastAccessed());

        System.out.print("xmlHealthCreation: ");
        System.out.println(pat.getXmlHealthCreation());


        System.out.println();
        System.out.println();
        System.out.println();
        
//        pat.setPatientRole(null);
        
        if ( pat.getPatientRole() != null ) {

            g.retrieveGuardian( userId );
            
            // GUARDIAN TABLE

            System.out.print("guardianno: ");
            System.out.println(g.getGuardianno());

            System.out.print("patientid: ");
            System.out.println(g.getPatientid());

            System.out.print("givenname: ");
            System.out.println(g.getGivenname());

            System.out.print("familyname: ");
            System.out.println(g.getFamilyname());

            System.out.print("relationship: ");
            System.out.println(g.getRelationship());

            System.out.print("phone: ");
            System.out.println(g.getPhone());

            System.out.print("address: ");
            System.out.println(g.getAddress());

            System.out.print("city: ");
            System.out.println(g.getCity());

            System.out.print("state: ");
            System.out.println(g.getState());

            System.out.print("zip: ");
            System.out.println(g.getZip());


            System.out.print("\n\nThanks! Goodbye!");

        }
        
    System.out.println(" Updating zip");
    createUpdateQuery( "zip", "15134", userId );

        GuardianEntity gr = new GuardianEntity();
        gr.retrieveGuardian(userId);
        System.out.print("NEWzip: ");
        System.out.println(gr.getZip());

    }

    public void createUpdateQuery( String column, String newVal, String userId ) {
//        this.query = "UPDATE " + this.tableName + " ";
        this.query = "UPDATE Guardian ";
        this.query += "SET " + column + " = '" + newVal + "' ";
        this.query += "WHERE patientid = '" + userId + "';";

        run();
    }
    
    
    
}
