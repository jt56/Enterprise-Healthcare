
public class LabTestReportEntity extends AbstractEntity {
    
    /*
    #####################
    #       Fields      #
    #####################
    */

    // labtestresultid, patientvisitid, labtestperformeddate, labtesttype, testresultvalue, referencerangehigh, referencerangelow

    private static String labtestresultid;
    private static String patientvisitid;
    private static String labtestperformeddate;
    private static String labtesttype;
    private static String testresultvalue;
    private static String referencerangehigh;
    private static String referencerangelow;


    /*
    #######################
    #   Specific Methods  #
    #######################
    */

    public LabTestReportEntity() {
        this.tableName = "Lab_Test_Reports";
        this.tableKeys = new String[]{"labtestresultid", "patientvisitid", "labtestperformeddate", "labtesttype", "testresultvalue", "referencerangehigh", "referencerangelow"};
        this.tableValues = new String[]{"lab", "pat", "ldate", "ltyp", "tst", "tsth", "tstl"};

        this.createInputString();

    }


    public LabTestReportEntity( String x[] ) {
        this.tableName = "Lab_Test_Reports";
        this.tableKeys = new String[]{"labtestresultid", "patientvisitid", "labtestperformeddate", "labtesttype", "testresultvalue", "referencerangehigh", "referencerangelow"};

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

    public static String getLabTestResultId() {
        return labtestresultid;
    }

    public static void setLabTestResultId(String labtestresultid) {
        LabTestReportEntity.labtestresultid = labtestresultid;
    }

    public static String getPatientVisitId() {
        return patientvisitid;
    }

    public static void setPatientVisitId(String patientvisitid) {
        LabTestReportEntity.patientvisitid = patientvisitid;
    }

    public static String getLabTestPerformedDate() {
        return labtestperformeddate;
    }

    public static void setLabTestPerformedDate(String labtestperformeddate) {
        LabTestReportEntity.labtestperformeddate = labtestperformeddate;
    }

    public static String getLabTestType() {
        return labtesttype;
    }

    public static void setLabTestType(String labtesttype) {
        LabTestReportEntity.labtesttype = labtesttype;
    }

    public static String getTestResultValue() {
        return testresultvalue;
    }

    public static void setTestResultValue(String testresultvalue) {
        LabTestReportEntity.testresultvalue = testresultvalue;
    }

    public static String getReferenceRangeHigh() {
        return referencerangehigh;
    }

    public static void setReferenceRangeHigh(String referencerangehigh) {
        LabTestReportEntity.referencerangehigh = referencerangehigh;
    }

    public static String getReferenceRangeLow() {
        return referencerangelow;
    }

    public static void setReferenceRangeLow(String referencerangelow) {
        LabTestReportEntity.referencerangelow = referencerangelow;
    }
    
}
