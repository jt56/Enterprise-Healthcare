import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdminView extends AbstractEntity {

    private Map<String, String> login = new HashMap<String, String>();
    
    private PatientEntity pat;

    private PlanEntity p;

    private AllergyEntity a;
    
//    private AuthorEntity auth;

    public AdminView( String userId ) {
        System.out.println();
        retrieveLogin();
        System.out.println();

        if ( login.get(userId) == null ) {
            System.out.println("User does not exist\n");
            return;
        }

        pat = new PatientEntity();
        p = new PlanEntity();
        a = new AllergyEntity();
//        auth = new AuthorEntity();

        printPlanData(userId); //takes planid
//        printAllergyData(userId); //take patientid
        
        a.retrieveAllergy(userId, true);
        p.retrievePlan(userId);

        printUserData(userId);
        printPlanData(userId);
        a.printAllergyData(userId);
        
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

        boolean isEdit = true;
        System.out.println("Would you like to edit a value:");
        Scanner reader = new Scanner(System.in);

        boolean changePatient = false;
        boolean changeAllergy = false;
        boolean changePlan = false;

        while (isEdit) {
            System.out.println(" Which table: Patient, Plan, or Allergies ");
            String table;
            table = reader.next();
            if (table.equals("Patient")) {
                System.out.println(" What value would you like to update: ");
                String updateColumn;
                updateColumn = reader.next();
                if (Arrays.asList(pat.tableKeys).contains(updateColumn.toString())) {
                    System.out.println(" What should the value be: ");
                    String updateVal;
                    updateVal = reader.next();
                    createAllergyUpdateQuery(table, updateColumn, updateVal.toString(), userId);
                    changePatient = true;
                } else
                    System.out.println("Column not in table");
            } else if (table.equals("Plan")) {
                System.out.println(" What value would you like to update: ");
                String updateColumn;
                updateColumn = reader.next();
                if (Arrays.asList(p.tableKeys).contains(updateColumn.toString())) {
                    System.out.println(" What should the value be: ");
                    String updateVal;
                    updateVal = reader.next();
                    createPlanUpdateQuery(table, updateColumn, updateVal.toString(), userId);
                    changePlan = true;
                } else
                    System.out.println("Column not in table");
            } else if (table.equals("Allergies")) {
                System.out.println(" What value would you like to update: ");
                String updateColumn;
                updateColumn = reader.next();
                if (Arrays.asList(a.tableKeys).contains(updateColumn.toString())) {
                    System.out.println(" What should the value be: ");
                    String updateVal;
                    updateVal = reader.next();
                    createUpdateQuery(table, updateColumn, updateVal.toString(), userId);
                    changeAllergy = true;
                } else
                    System.out.println("Column not in table");
            } else
                System.out.println("Incorrect input");

            System.out.println("Would you like to edit another value:");
            String input = reader.next();
            input.toString();
            if ( input.equals("y") || input.equals("yes") || input.equals("Y") || input.equals("Yes") ) {
                isEdit = true;
            } else
                isEdit = false;


        }

        if ( changePatient ) {
            printUserData(userId);
        }

        if ( changeAllergy ) {
            AllergyEntity b = new AllergyEntity();
            b.retrieveAllergy(userId, true);
        }
        
        if ( changePlan ) {
            printPlanData(userId);
        }

        boolean isRun = false;
        
        System.out.println(" Run Query?");
        String qry = reader.next();
        qry.toString();
        if ( qry.equals("y") || qry.equals("yes") || qry.equals("Y") || qry.equals("Yes") ) {
            isRun = true;
        } else
            isRun = false;
        
        if(isRun) {
            this.query = query1();
            run();
        }
    }
    
//    public String query1() {
//        String q = "SELECT A.substance, COUNT(A.patientid) " +
//                "FROM Allergies A " +
//                "GROUP BY A.substance;";
//        
//        return q;
//    }

    public String query1() {
        String q = "SELECT A.patientid, COUNT(A.substance) AS num FROM Allergies A WHERE (num > 1) GROUP BY A.patientid;";
        return q;
    }
    
    public void createUpdateQuery( String table, String column, String newVal, String userId ) {
//        this.query = "UPDATE " + this.tableName + " ";
        this.query = "UPDATE " + table + " ";
        this.query += "SET " + column + " = '" + newVal + "' ";
        this.query += "WHERE patientid = '" + userId + "';";

        run();
    }

    public void createPlanUpdateQuery( String table, String column, String newVal, String userId ) {
//        this.query = "UPDATE " + this.tableName + " ";
        this.query = "UPDATE " + table + " ";
        this.query += "SET " + column + " = '" + newVal + "' ";
        this.query += "WHERE planpatientid = '" + userId + "';";

        run();
    }

    public void createAllergyUpdateQuery( String table, String column, String newVal, String userId ) {
//        this.query = "UPDATE " + this.tableName + " ";
        this.query = "UPDATE " + table + " ";
        this.query += "SET " + column + " = '" + newVal + "' ";
        this.query += "WHERE patientid = '" + userId + "';";

        run();
    }
    
    public void printUserData( String userId ) {
        // patientid, providerid, patientrole, givenname, familyname, suffix, gender, birthtime, lastaccessed, xmlHealthCreation
        pat.retrievePatient(userId);
        System.out.println();

        System.out.println("PATIENT INFO");

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

    }

    public void printPlanData(String userId){
//        String[]{"planid", "planpatientid", "activity"}

        p.retrievePlan(userId);

        System.out.println("PLAN INFO");
        
        System.out.print("planid: ");
        System.out.println(p.getPlanId());

        System.out.print("planpatientid: ");
        System.out.println(p.getPlanPatientId());

        System.out.print("activity: ");
        System.out.println(p.getActivity() + "\n");
    }

}
