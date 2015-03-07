
public class PatientEntity extends AbstractEntity {
    
    /*
    #####################
    #       Fields      #
    #####################
    */
    
    // patientid, providerid, patientrole, givenname, familyname, suffix, gender, birthtime, xmlHealthCreation
    
    private static String patientid;
    
    private static String providerid;
    
    private static String patientrole;
    
    private static String givenname;
    
    private static String familyname;
    
    private static String suffix;
    
    private static String gender;
    
    private static String birthtime;
    
    private static String xmlHealthCreation;
    
    /*
    #######################
    #   Specific Methods  #
    #######################
    */
    
    public PatientEntity() {
        this.tableName = "PATIENT";
        this.tableKeys = new String[]{"patientid", "providerid", "patientrole", "givenname", "familyname", "suffix", "gender", "birthtime", "xmlHealthCreation"};
//        this.tableValues = new String[]{"pat2", "prov", "patrole", "give", "fam", "suf", "gen", "birth", "xml"};

        this.createInputString();
        
    }
    
    public PatientEntity( String x[] ) {
        this.tableName = "PATIENT";
        this.tableKeys = new String[]{"patientid", "providerid", "patientrole", "givenname", "familyname", "suffix", "gender", "birthtime", "xmlHealthCreation"};

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

    public String getPatientId() {
        return patientid;
    }

    public void setPatientId(String providerid) {
        this.patientid = providerid;
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

    public String getXmlHealthCreation() {
        return xmlHealthCreation;
    }

    public void setXmlHealthCreation(String xmlHealthCreation) {
        this.xmlHealthCreation = xmlHealthCreation;
    }
    
}
