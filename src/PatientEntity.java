import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class PatientEntity extends AbstractEntity {
    
    /*
    #####################
    #       Fields      #
    #####################
    */
    
    // patientid, providerid, patientrole, givenname, familyname, suffix, gender, birthtime, lastaccessed, xmlHealthCreation
    
    private static String patientid;
    
    private static String providerid;
    
    private static String patientrole;
    
    private static String givenname;
    
    private static String familyname;
    
    private static String suffix;
    
    private static String gender;
    
    private static String birthtime;

    private static String lastaccessed;
    
    private static String xmlHealthCreation;

//    private Map<String, String> queryResults = new HashMap<String, String>();
    
    /*
    #######################
    #   Specific Methods  #
    #######################
    */
    
    public PatientEntity() {
        this.tableName = "Patient";
        this.tableKeys = new String[]{"patientid", "providerid", "patientrole", "givenname", "familyname", "suffix", "gender", "birthtime", "lastaccessed", "xmlHealthCreation"};
//        this.tableValues = new String[]{"pat2", "prov", "guard", "give", null, "suf", "gen", "birth", "last", "xml"};
//
//        this.createInputString();
//
    }
    
    public PatientEntity( String x[] ) {
        this.tableName = "Patient";
        this.tableKeys = new String[]{"patientid", "providerid", "patientrole", "givenname", "familyname", "suffix", "gender", "birthtime", "lastaccessed", "xmlHealthCreation"};

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

    public void retrievePatient( String pid ) {
        
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
                queryResults.put("patientid", rs.getString("patientid"));
                queryResults.put("providerid", rs.getString("providerid"));
                queryResults.put("patientrole", rs.getString("patientrole"));
                queryResults.put("givenname", rs.getString("givenname"));
                queryResults.put("familymame", rs.getString("familyname"));
                queryResults.put("suffix", rs.getString("suffix"));
                queryResults.put("gender", rs.getString("gender"));
                queryResults.put("birthtime", rs.getString("birthtime"));
                queryResults.put("lastaccessed", rs.getString("lastaccessed"));
                queryResults.put("xmlHealthCreation", rs.getString("xmlHealthCreation"));
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

//    private void setPatientEntity() {
//        String[] x = new String[]{
//                queryResults.get("patientId"),
//                queryResults.get("providerid"),
//                queryResults.get("patientrole"),
//                queryResults.get("givenname"),
//                queryResults.get("familymame"),
//                queryResults.get("suffix"),
//                queryResults.get("gender"),
//                queryResults.get("birthtime"),
//                queryResults.get("lastaccessed"),
//                queryResults.get("xmlHealthCreation")
//        };
//
//        setAll();
//
//        queryResults.clear();
//    }

    
    /*
    ######################
    #   Getters/Setters  #
    ######################
    */

    public void setAll() {

        setPatientId(queryResults.get("patientid"));

        setProviderId(queryResults.get("providerid"));

        setPatientRole(queryResults.get("patientrole"));

        setGivenName(queryResults.get("givenname"));
        
        setFamilyName(queryResults.get("familyname"));

        setSuffix(queryResults.get("suffix"));
        
        setGender(queryResults.get("gender"));
        
        setBirthTime(queryResults.get("birthtime"));

        setLastAccessed(queryResults.get("lastaccessed"));

        setXmlHealthCreation(queryResults.get("xmlHealthCreation"));                                    
    }
    
    
    public String getPatientId() {
        return patientid;
    }

    public void setPatientId(String patientid) {
        this.patientid = patientid;
    }

    public String getProviderId() {
        return providerid;
    }
    
    public void setProviderId(String providerid) {
        this.providerid = providerid;
    }

    public String getPatientRole() {
        return patientrole;
    }

    public void setPatientRole(String patientrole) {
        this.patientrole = patientrole;
    }

    public String getGivenName() {
        return givenname;
    }

    public void setGivenName(String givenname) {
        this.givenname = givenname;
    }

    public String getFamilyName() {
        return familyname;
    }

    public void setFamilyName(String familyname) {
        this.familyname = familyname;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthTime() {
        return birthtime;
    }
    
    public void setBirthTime(String birthtime) {
        this.birthtime = birthtime;
    }

    public static String getLastAccessed() { return lastaccessed; }

    public static void setLastAccessed(String lastaccessed) { PatientEntity.lastaccessed = lastaccessed; }

    public String getXmlHealthCreation() {
        return xmlHealthCreation;
    }

    public void setXmlHealthCreation(String xmlHealthCreation) {
        this.xmlHealthCreation = xmlHealthCreation;
    }
    
}
