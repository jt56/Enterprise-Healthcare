import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GuardianEntity extends AbstractEntity {
    
    /*
    #####################
    #       Fields      #
    #####################
    */
    
    // 	guardianno, patientid, givenname, familyname, phone, address, city, state, zip

    // FK: planpatientid -> Patient.patientid
    
    private static String guardianno;
    private static String patientid;
    private static String givenname;
    private static String familyname;
    private static String relationship;
    private static String phone;
    private static String address;
    private static String city;
    private static String state;
    private static String zip;
    
    /*
    #######################
    #   Specific Methods  #
    #######################
    */

    public GuardianEntity() {
        this.tableName = "Guardian";
        this.tableKeys = new String[]{"guardianno", "patientid", "givenname", "familyname", "relationship", "phone", "address", "city", "state", "zip"};
        this.tableValues = new String[]{"'guard'", "'pat2'", "'giv'", "'fam'", "'rel'", "'pho'", "'addr'", "'city'", "'st'", "'zip'"};

        this.createInputString();

    }

    public GuardianEntity( String x[] ) {
        this.tableName = "Guardian";
        this.tableKeys = new String[]{"guardianno", "patientid", "givenname", "familyname", "relationship", "phone", "address", "city", "state", "zip"};

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

    public void retrieveGuardian( String gid ) {

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
//            this.query += " WHERE guardianno = " + "'" + gid + "'";
            this.query += " WHERE patientid = " + "'" + gid + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(this.query);


            while (rs.next()) {
//                "guardianno", "patientid", "givenname", "familyname", "relationship", "phone", "address", "city", "state", "zip"};
                queryResults.put("guardianno", rs.getString("guardianno"));
                queryResults.put("patientid", rs.getString("patientid"));
                queryResults.put("givenname", rs.getString("givenname"));
                queryResults.put("familyname", rs.getString("familyname"));
                queryResults.put("relationship", rs.getString("relationship"));
                queryResults.put("phone", rs.getString("phone"));
                queryResults.put("address", rs.getString("address"));
                queryResults.put("city", rs.getString("city"));
                queryResults.put("state", rs.getString("state"));
                queryResults.put("zip", rs.getString("zip"));
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

    public void setAll() {

        setGuardianno(queryResults.get("guardianno"));
        setPatientid(queryResults.get("patientid"));
        setGivenname(queryResults.get("givenname"));
        setFamilyname(queryResults.get("familyname"));
        setRelationship(queryResults.get("relationship"));
        setPhone(queryResults.get("phone"));
        setAddress(queryResults.get("address"));
        setCity(queryResults.get("city"));
        setState(queryResults.get("state"));
        setZip(queryResults.get("zip"));
    }

    public static String getGuardianno() {
        return guardianno;
    }

    public static void setGuardianno(String guardianno) {
        GuardianEntity.guardianno = guardianno;
    }

    public static String getPatientid() {
        return patientid;
    }

    public static void setPatientid(String patientid) {
        GuardianEntity.patientid = patientid;
    }

    public static String getGivenname() {
        return givenname;
    }

    public static void setGivenname(String givenname) {
        GuardianEntity.givenname = givenname;
    }

    public static String getFamilyname() {
        return familyname;
    }

    public static void setFamilyname(String familyname) {
        GuardianEntity.familyname = familyname;
    }

    public static String getRelationship() {return relationship;}

    public static void setRelationship(String relationship) {GuardianEntity.relationship = relationship;}

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        GuardianEntity.phone = phone;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        GuardianEntity.address = address;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        GuardianEntity.city = city;
    }

    public static String getState() {
        return state;
    }

    public static void setState(String state) {
        GuardianEntity.state = state;
    }

    public static String getZip() {
        return zip;
    }

    public static void setZip(String zip) {
        GuardianEntity.zip = zip;
    }

}
