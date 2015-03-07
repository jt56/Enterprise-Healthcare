
public class FamilyHistoryEntity extends AbstractEntity {
   
    /*
    #####################
    #       Fields      #
    #####################
    */

    // relativeid, patientid, patientrelation, age, diagnosis
    
    // FK: patientid -> Patient.patientid
    
    private static String relativeid;
    private static String patientid;
    private static String patientrelation;
    private static String age;
    private static String diagnosis;


    /*
    #######################
    #   Specific Methods  #
    #######################
    */

    public FamilyHistoryEntity() {
        this.tableName = "Family_History";
        this.tableKeys = new String[]{"relativeid", "patientid", "patientrelation", "age", "diagnosis"};
        this.tableValues = new String[]{"rel", "pat", "prel", "ag", "diag"};

        this.createInputString();

    }


    public FamilyHistoryEntity( String x[] ) {
        this.tableName = "Family_History";
        this.tableKeys = new String[]{"relativeid", "patientid", "patientrelation", "age", "diagnosis"};

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

    public static String getRelativeId() {
        return relativeid;
    }

    public static void setRelativeId(String relativeid) {
        FamilyHistoryEntity.relativeid = relativeid;
    }

    public static String getPatientId() {
        return patientid;
    }

    public static void setPatientId(String patientid) {
        FamilyHistoryEntity.patientid = patientid;
    }

    public static String getPatientRelation() {
        return patientrelation;
    }

    public static void setPatientRelation(String patientrelation) {
        FamilyHistoryEntity.patientrelation = patientrelation;
    }

    public static String getAge() {
        return age;
    }

    public static void setAge(String age) {
        FamilyHistoryEntity.age = age;
    }

    public static String getDiagnosis() {
        return diagnosis;
    }

    public static void setDiagnosis(String diagnosis) {
        FamilyHistoryEntity.diagnosis = diagnosis;
    }
    
}
