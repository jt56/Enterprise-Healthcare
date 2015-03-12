
public class HasPlanRelation extends AbstractEntity {
    
    /*
    #####################
    #       Fields      #
    #####################
    */
    
    // 	patientid, planid, plandate
    
    // FK: patientid -> Patient.patientid, planid -> Plan.planid
    
    private static String patientid;
    private static String planid;
    private static String plandate;
    
    /*
    #######################
    #   Specific Methods  #
    #######################
    */

    public HasPlanRelation(){
        this.tableName="has_Plan";
        this.tableKeys=new String[]{"patientid", "planid", "plandate"};
        this.tableValues=new String[]{"'pat2'","'pid'","'pdate'"};

        this.createInputString();

    }
    
    public HasPlanRelation(String x[]) {
        this.tableName="has_Plan";
        this.tableKeys=new String[]{"patientid", "planid", "plandate"};
    
        this.tableValues=new String[tableKeys.length];
        for(int i=0;i<tableKeys.length;i++){
            if(x[i]==null) {
                tableValues[i]=null;
            } 
            else {
                tableValues[i]="'"+x[i]+"'";
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
        HasPlanRelation.patientid = patientid;
    }

    public static String getPlanid() {
        return planid;
    }

    public static void setPlanid(String planid) {
        HasPlanRelation.planid = planid;
    }

    public static String getPlandate() {
        return plandate;
    }

    public static void setPlandate(String plandate) {
        HasPlanRelation.plandate = plandate;
    }
}