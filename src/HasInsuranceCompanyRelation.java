public class HasInsuranceCompanyRelation extends AbstractEntity {

    
 /*
    #####################
    #       Fields      #
    #####################
    */

    // 	patientid, payerid, purpose

    // FK: patientid -> Patient.patientid, payerid -> Insurance_Company.payerid

    private static String patientid;
    private static String payerid;
    private static String purpose;
    
    /*
    #######################
    #   Specific Methods  #
    #######################
    */

    public HasInsuranceCompanyRelation() {
        this.tableName = "has_Insurance_Company";
        this.tableKeys = new String[]{"patientid", "payerid", "purpose"};
        this.tableValues = new String[]{"'pat2'", "'pay'", "'purp'"};

        this.createInputString();

    }

    public HasInsuranceCompanyRelation( String x[] ) {
        this.tableName = "has_Insurance_Company";
        this.tableKeys = new String[]{"patientid", "payerid", "purpose"};

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
    
    /*
    ######################
    #   Getters/Setters  #
    ######################
    */

    public static String getPatientid() {
        return patientid;
    }

    public static void setPatientid(String patientid) {
        HasInsuranceCompanyRelation.patientid = patientid;
    }

    public static String getPayerid() {
        return payerid;
    }

    public static void setPayerid(String payerid) {
        HasInsuranceCompanyRelation.payerid = payerid;
    }

    public static String getPurpose() {
        return purpose;
    }

    public static void setPurpose(String purpose) {
        HasInsuranceCompanyRelation.purpose = purpose;
    }
}
