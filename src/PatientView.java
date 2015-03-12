import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.Console;
import java.util.Arrays;

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

//        pat.setPatientRole(null);

        if (pat.getPatientRole() != null) {

            g.retrieveGuardian(userId);

            // GUARDIAN TABLE
            System.out.println("GUARDIAN INFO");

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

        boolean isEdit = true;
        System.out.println("Would you like to edit a value:");
        Scanner reader = new Scanner(System.in);
//        String input = reader.next();

//
//        if ( input == "y" || input == "yes" || input == "Y" || input == "Yes" ) {
//            isEdit = true;
//        }

        boolean changePatient = false;
        boolean changeGuardian = false;
        
        while (isEdit) {
            System.out.println(" Which table, Patient or Guardian: ");
            String table;
            table = reader.next();
            System.out.println(table);
            if (table.equals("Patient")) {
                System.out.println(" What value would you like to update: ");
                String updateColumn;
                updateColumn = reader.next();
                if (Arrays.asList(pat.tableKeys).contains(updateColumn.toString())) {
                    System.out.println(" What should the value be: ");
                    String updateVal;
                    updateVal = reader.next();
                    createUpdateQuery(table, updateColumn, updateVal.toString(), userId);
                    changePatient = true;
                } else
                    System.out.println("Column not in table");
            } else if (table.equals("Guardian")) {
                System.out.println(" What value would you like to update: ");
                String updateColumn;
                updateColumn = reader.next();
                if (Arrays.asList(g.tableKeys).contains(updateColumn.toString())) {
                    System.out.println(" What should the value be: ");
                    String updateVal;
                    updateVal = reader.next();
                    createUpdateQuery(table, updateColumn, updateVal.toString(), userId);
                    changeGuardian = true;
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
            printPatient(userId);
        }

        if ( changeGuardian ) {
            printGuardian(userId);
//            System.out.print("NEWzip: ");
//            System.out.println(gr.getZip());
        }
    }

    
    public void printPatient( String uid ) {
        
        PatientEntity pp = new PatientEntity();
        pp.retrievePatient(uid);
        
        pp.retrievePatient(uid);

        System.out.println(" UPDATED PATIENT INFO");

        System.out.print("patientid: ");
        System.out.println(pp.getPatientId());

        System.out.print("providerid: ");
        System.out.println(pp.getProviderId());

        System.out.print("patientrole: ");
        System.out.println(pp.getPatientRole());

        System.out.print("givenname: ");
        System.out.println(pp.getGivenName());

        System.out.print("familyname: ");
        System.out.println(pp.getFamilyName());

        System.out.print("suffix: ");
        System.out.println(pp.getSuffix());

        System.out.print("gender: ");
        System.out.println(pp.getGender());

        System.out.print("birthtime: ");
        System.out.println(pp.getBirthTime());

        System.out.print("lastaccessed: ");
        System.out.println(pp.getLastAccessed());

        System.out.print("xmlHealthCreation: ");
        System.out.println(pp.getXmlHealthCreation());

        System.out.println();
        System.out.println();
        
    }

    public void printGuardian( String uid ) {
        GuardianEntity gr = new GuardianEntity();
        gr.retrieveGuardian(uid);

        // GUARDIAN TABLE
        System.out.println("UPDATED GUARDIAN INFO");

        System.out.print("guardianno: ");
        System.out.println(gr.getGuardianno());

        System.out.print("patientid: ");
        System.out.println(gr.getPatientid());

        System.out.print("givenname: ");
        System.out.println(gr.getGivenname());

        System.out.print("familyname: ");
        System.out.println(gr.getFamilyname());

        System.out.print("relationship: ");
        System.out.println(gr.getRelationship());

        System.out.print("phone: ");
        System.out.println(gr.getPhone());

        System.out.print("address: ");
        System.out.println(gr.getAddress());

        System.out.print("city: ");
        System.out.println(gr.getCity());

        System.out.print("state: ");
        System.out.println(gr.getState());

        System.out.print("zip: ");
        System.out.println(gr.getZip());
        
        System.out.println();
        System.out.println();
    }

    public void createUpdateQuery( String table, String column, String newVal, String userId ) {
//        this.query = "UPDATE " + this.tableName + " ";
        this.query = "UPDATE " + table + " ";
        this.query += "SET " + column + " = '" + newVal + "' ";
        this.query += "WHERE patientid = '" + userId + "';";

        run();
    }
    
    
    
}
