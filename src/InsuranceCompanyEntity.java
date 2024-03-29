
public class InsuranceCompanyEntity extends AbstractEntity {
    
    /*
    #####################
    #       Fields      #
    #####################
    */
    
    // payerid, name, policytype
    
    private static String payerid;
    private static String name;
    private static String policyholder;
    private static String policytype;

    /*
    #######################
    #   Specific Methods  #
    #######################
    */

    public InsuranceCompanyEntity() {
        this.tableName = "Insurance_Company";
        this.tableKeys = new String[]{"payerid", "name", "policyholder", "policytype"};
        this.tableValues = new String[]{"'pay'", "'nam'", "'plcy'", "'ptype'"};

        this.createInputString();

    }

    public InsuranceCompanyEntity( String x[] ) {
        this.tableName = "Insurance_Company";
        this.tableKeys = new String[]{"payerid", "name", "policyholder", "policytype"};

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

    public static String getPayerId() {
        return payerid;
    }

    public static void setPayerId(String payerid) {
        InsuranceCompanyEntity.payerid = payerid;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        InsuranceCompanyEntity.name = name;
    }

    public static String getPolicyholder() { return policyholder; }

    public static void setPolicyholder(String policyholder) { InsuranceCompanyEntity.policyholder = policyholder;}

    public static String getPolicyType() {
        return policytype;
    }

    public static void setPolicyType(String policytype) {
        InsuranceCompanyEntity.policytype = policytype;
    }
}
