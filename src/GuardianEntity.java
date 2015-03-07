
public class GuardianEntity extends AbstractEntity {
    
    /*
    #####################
    #       Fields      #
    #####################
    */
    
    // 	guardianno, patientid, givenname, familyname, phone, address, city, state, zip
    
    private static String guardianno;
    private static String patientid;
    private static String givenname;
    private static String familyname;
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
        this.tableName = "GUARDIAN";
        this.tableKeys = new String[]{"guardianno", "patientid", "givenname", "familyname", "phone", "address", "city", "state", "zip"};
        this.tableValues = new String[]{"guard", "pat2", "giv", "fam", "pho", "addr", "city", "st", "zip"};

        this.createInputString();

    }

    public GuardianEntity( String x[] ) {
        this.tableName = "GUARDIAN";
        this.tableKeys = new String[]{"guardianno", "patientid", "givenname", "familyname", "phone", "address", "city", "state", "zip"};

        this.tableValues = new String[tableKeys.length];
        for(int i = 0; i < tableKeys.length; i++ ) {
            tableValues[i] = x[i];
        }

        this.createInputString();
    }
    
    /*
    ######################
    #   Getters/Setters  #
    ######################
    */

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
