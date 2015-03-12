import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DrAuthorView extends AbstractEntity {

    private Map<String, String> login = new HashMap<String, String>();

    private PatientEntity pat;

    private PlanEntity p;

    private AllergyEntity a;

    public DrAuthorView( String userId ) {
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



//        printPlanData(userId); //takes planid
        p.retrievePlan(userId);
//        printAllergyData(userId); //take patientid
        a.retrieveAllergy(userId, true);

        menu(userId);

        printUserData(userId);
    }

    public void menu(String userId){
        Scanner reader = new Scanner(System.in);
        String input;
        System.out.println("Welcome Doctor/Author");

        boolean isEdit = true;
        boolean changePlan = false;
        boolean changeAllergies = false;

        System.out.println("Would you like to edit a value:");

        while (isEdit) {
            System.out.println(" Which table, Plan or Allergies: ");
            String table;
            table = reader.next();
            System.out.println(table);
            if (table.equals("Plan")) {
                System.out.println(" What value would you like to update: ");
                String updateColumn;
                updateColumn = reader.next();
                if (Arrays.asList(p.tableKeys).contains(updateColumn.toString())) {
                    System.out.println(" What should the value be: ");
                    String updateVal;
                    updateVal = reader.next();
                    createUpdateQueryPlan(table, updateColumn, updateVal.toString(), userId);
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
                    createUpdateQueryAllergies(table, updateColumn, updateVal.toString(), userId);
                    changeAllergies = true;
                } else
                    System.out.println("Column not in table");
            } else
                System.out.println("Incorrect input");

            System.out.println("Would you like to edit another value:");
            input = reader.next();
            input.toString();
            if ( input.equals("y") || input.equals("yes") || input.equals("Y") || input.equals("Yes") ) {
                isEdit = true;
            } else
                isEdit = false;


        }

        if ( changePlan ) {
            printPlan(userId);
        }

        if ( changeAllergies ) {
            printAllergy(userId);
        }
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

    public void printPlan(String userId){
//        String[]{"planid", "planpatientid", "activity"}

        p.retrievePlan(userId);

        System.out.print("planid: ");
        System.out.println(p.getPlanId());

        System.out.print("planpatientid: ");
        System.out.println(p.getPlanPatientId());

        System.out.print("activity: ");
        System.out.println(p.getActivity() + "\n");
    }

    public void printAllergy(String userId ){
//        this.tableKeys = new String[]{"id", "substance", "patientid", "reaction", "status"};
        a.retrieveAllergy(userId, false);

        System.out.print("id: ");
        System.out.println(a.getId());

        System.out.print("substance: ");
        System.out.println(a.getSubstance());

        System.out.print("patientid: ");
        System.out.println(a.getPatientId());

        System.out.print("reatction: ");
        System.out.println(a.getReaction());

        System.out.print("status: ");
        System.out.println(a.getStatus() + "\n");
    }

    public void printUserData( String userId ) {
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

    }
    public void createUpdateQueryPlan( String table, String column, String newVal, String userId ) {
//        this.query = "UPDATE " + this.tableName + " ";
        this.query = "UPDATE " + table + " ";
        this.query += "SET " + column + " = '" + newVal + "' ";
        this.query += "WHERE planpatientid = '" + userId + "';";

        run();
    }
    public void createUpdateQueryAllergies( String table, String column, String newVal, String userId ) {
//        this.query = "UPDATE " + this.tableName + " ";
        this.query = "UPDATE " + table + " ";
        this.query += "SET " + column + " = '" + newVal + "' ";
        this.query += "WHERE patientid = '" + userId + "';";

        run();
    }
}
