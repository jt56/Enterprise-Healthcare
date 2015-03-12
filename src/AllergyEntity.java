
public class AllergyEntity extends AbstractEntity {

    // substance, patientid, reaction, status

    // FK: patientid -> Patient.patientid

    private static String substance;
    private static String patientid;
    private static String reaction;
    private static String status;

    /*
    #######################
    #   Specific Methods  #
    #######################
    */

    public AllergyEntity() {
        this.tableName = "Allergies";
        this.tableKeys = new String[]{"substance", "patientid", "reaction", "status"};
        this.tableValues = new String[]{"sub", "pat2", "rct", "stat"};

        this.createInputString();

    }


    public AllergyEntity( String x[] ) {
        this.tableName = "Allergies";
        this.tableKeys = new String[]{"substance", "patientid", "reaction", "status"};

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

    public static String getSubstance() {
        return substance;
    }

    public static void setSubstance(String substance) {
        AllergyEntity.substance = substance;
    }

    public static String getPatientId() {
        return patientid;
    }

    public static void setPatientId(String patientid) {
        AllergyEntity.patientid = patientid;
    }

    public static String getReaction() {
        return reaction;
    }

    public static void setReaction(String reaction) {
        AllergyEntity.reaction = reaction;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        AllergyEntity.status = status;
    }
}
