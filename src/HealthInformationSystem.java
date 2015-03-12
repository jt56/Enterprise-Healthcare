import java.util.Scanner;

public class HealthInformationSystem {

    public static void main(String[] args) {

        MessageExchangeSystem mes = new MessageExchangeSystem();
        mes.run();
        
        
        Scanner reader = new Scanner(System.in);
//        String input = reader.next();

//
//        if ( input == "y" || input == "yes" || input == "Y" || input == "Yes" ) {
//            isEdit = true;
//        }
        System.out.println("Patient, Doctor, Author, or Admin: ");
        String priv;
        priv = reader.next();
        priv.toString();
//        PatientView pV = new PatientView("0");

        System.out.println("What is your id?");
        String uid;
        uid = reader.next();
        uid.toString();
        
        if ( priv == "Patient" || priv == "patient") {
            PatientView pV = new PatientView(uid);
        }
        if ( priv == "Doctor" || priv == "doctor" || priv == "Author" || priv == "author" ) {
            DrAuthorView d = new DrAuthorView(uid);
        }
        if ( priv == "Admin" || priv == "admin" ) {
            AdminView adv = new AdminView(uid);
        }


    }
    
}

