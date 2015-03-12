
public class PlanEntity extends AbstractEntity {
    
    // planid, planpatientid, activity

    // FK: planpatientid -> Patient.patientid
    
    private static String planid;
    private static String planpatientid;
    private static String activity;
    
    /*
    #######################
    #   Specific Methods  #
    #######################
    */

    public PlanEntity() {
        this.tableName = "Plan";
        this.tableKeys = new String[]{"planid", "planpatientid", "activity"};
        this.tableValues = new String[]{"'pid'", "'pat2'", "'act'"};

        this.createInputString();

    }


    public PlanEntity( String x[] ) {
        this.tableName = "Plan";
        this.tableKeys = new String[]{"planid", "planpatientid", "activity"};

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

    public static String getPlanId() {
        return planid;
    }

    public static void setPlanId(String planid) {
        PlanEntity.planid = planid;
    }

    public static String getPlanPatientId() {
        return planpatientid;
    }

    public static void setPlanPatientId(String planpatientid) {
        PlanEntity.planpatientid = planpatientid;
    }

    public static String getActivity() {
        return activity;
    }

    public static void setActivity(String activity) {
        PlanEntity.activity = activity;
    }
    
}
