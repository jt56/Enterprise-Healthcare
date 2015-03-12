
public class AuthorEntity extends AbstractEntity {

    /*
    #####################
    #       Fields      #
    #####################
    */

    // authorid, authortitle, authorfirstname, authorlastname
    private static String authorid;
    private static String authortitle;
    private static String authorfirstname;
    private static String authorlastname;

    /*
    #######################
    #   Specific Methods  #
    #######################
    */

    public AuthorEntity() {
        this.tableName = "Author";
        this.tableKeys = new String[]{"authorid", "authortitle", "authorfirstname", "authorlastname"};
        this.tableValues = new String[]{"auth", "atitl", "afir", "alas"};

        this.createInputString();

    }


    public AuthorEntity( String x[] ) {
        this.tableName = "Author";
        this.tableKeys = new String[]{"authorid", "authortitle", "authorfirstname", "authorlastname"};

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

    public static String getAuthorOd() {
        return authorid;
    }

    public static void setAuthorId(String authorid) {
        AuthorEntity.authorid = authorid;
    }

    public static String getAuthorTitle() {
        return authortitle;
    }

    public static void setAuthorTitle(String authortitle) {
        AuthorEntity.authortitle = authortitle;
    }

    public static String getAuthorFirstName() {
        return authorfirstname;
    }

    public static void setAuthorFirstName(String authorfirstname) {
        AuthorEntity.authorfirstname = authorfirstname;
    }

    public static String getAuthorLastname() {
        return authorlastname;
    }

    public static void setAuthorLastname(String authorlastname) {
        AuthorEntity.authorlastname = authorlastname;
    }

    
}

