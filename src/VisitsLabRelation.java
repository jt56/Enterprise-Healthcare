
public class VisitsLabRelation extends AbstractEntity {

 /*
    #####################
    #       Fields      #
    #####################
    */

    // 	patientid, labtestresultid

    // FK: patientid -> Patient.patientid, labtestresultid -> Lab_Test_Reports.labtestresultid

    private static String patientid;
    private static String labtestresultid;
    
    /*
    #######################
    #   Specific Methods  #
    #######################
    */

    public VisitsLabRelation() {
        this.tableName = "visits_Lab";
        this.tableKeys = new String[]{"patientid", "labtestresultid"};
        this.tableValues = new String[]{"'pat2'", "'lab'"};

        this.createInputString();

    }

    public VisitsLabRelation( String x[] ) {
        this.tableName = "visits_Lab";
        this.tableKeys = new String[]{"patientid", "labtestresultid"};
        
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
        VisitsLabRelation.patientid = patientid;
    }

    public static String getLabtestresultid() {
        return labtestresultid;
    }

    public static void setLabtestresultid(String labtestresultid) {
        VisitsLabRelation.labtestresultid = labtestresultid;
    }
}
