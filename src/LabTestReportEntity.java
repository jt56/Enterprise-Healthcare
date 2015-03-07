/**
 * Created by heneli on 3/7/15.
 */
public class LabTestReportEntity extends AbstractEntity {
    /*
    CREATE TABLE Lab_Test_Reports(
            labtestresultid VARCHAR(100),
    patientvisitid VARCHAR(100),
    labtestperformeddate VARCHAR(100) NOT NULL,
    labtesttype VARCHAR(100) NOT NULL,
    testresultvalue VARCHAR(100) NOT NULL,
    referencerangehigh VARCHAR(100),
    referencerangelow VARCHAR(100),
    PRIMARY KEY(labtestresultid, patientvisitid)
    );
    */
    
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
        this.tableName = "LAB_TEST_REPORTS";
        this.tableKeys = new String[]{"labtestresultid", "patientvisitid", "labtestperformeddate", "labtesttype", "testresultvalue", "referencerangehigh", "referencerangelow"};
        this.tableValues = new String[]{"lab", "pat", "ldate", "ltyp", "tst", "tsth", "tstl"};

        this.createInputString();

    }


    public LabTestReportEntity( String x[] ) {
        this.tableName = "LAB_TEST_REPORTS";
        this.tableKeys = new String[]{"labtestresultid", "patientvisitid", "labtestperformeddate", "labtesttype", "testresultvalue", "referencerangehigh", "referencerangelow"};

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
