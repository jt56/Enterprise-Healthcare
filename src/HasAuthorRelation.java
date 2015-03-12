public class HasAuthorRelation extends AbstractEntity {
    
 /*
    #####################
    #       Fields      #
    #####################
    */

    // 	patientid, authorid, participatingrole

    // FK: patientid -> Patient.patientid, authorid -> Author.authorid

    private static String patientid;
    private static String authorid;
    private static String participatingrole;
    
    /*
    #######################
    #   Specific Methods  #
    #######################
    */

    public HasAuthorRelation() {
        this.tableName = "has_Author";
        this.tableKeys = new String[]{"patientid", "authorid", "participatingrole"};
        this.tableValues = new String[]{"'pat2'", "'auth'", "'part'"};

        this.createInputString();

    }

    public HasAuthorRelation(String x[]) {
        this.tableName = "has_Author";
        this.tableKeys = new String[]{"patientid", "authorid", "participatingrole"};

        this.tableValues = new String[tableKeys.length];
        for (int i = 0; i < tableKeys.length; i++) {
            if (x[i] == null) {
                tableValues[i] = null;
            } else {
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
        HasAuthorRelation.patientid = patientid;
    }

    public static String getAuthorid() {
        return authorid;
    }

    public static void setAuthorid(String authorid) {
        HasAuthorRelation.authorid = authorid;
    }

    public static String getParticipatingrole() {
        return participatingrole;
    }

    public static void setParticipatingrole(String participatingrole) {
        HasAuthorRelation.participatingrole = participatingrole;
    }
}