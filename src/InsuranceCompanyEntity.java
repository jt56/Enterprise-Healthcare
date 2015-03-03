
public class InsuranceCompanyEntity extends AbstractEntity {
    
    /*
    #####################
    #       Fields      #
    #####################
    */
    
    // payerid, name, policytype
    
    private static String payerid;
    private static String name;
    private static String policytype;

    /*
    #######################
    #   Specific Methods  #
    #######################
    */

    public InsuranceCompanyEntity() {
        this.tableName = "INSURANCE_COMPANY";
        this.tableKeys = new String[]{"payerid", "name", "policytype"};
        this.tableValues = new String[]{"pay", "nam", "plcy"};

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

    public static String getPolicyType() {
        return policytype;
    }

    public static void setPolicyType(String policytype) {
        InsuranceCompanyEntity.policytype = policytype;
    }
}
