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

        if ( priv.equals("Patient") || priv.equals("patient") ) {
            PatientView pV = new PatientView(uid);
        }
        if ( priv.equals("Doctor") || priv.equals("doctor") || priv.equals("Author") || priv.equals("author") ) {
            DrAuthorView d = new DrAuthorView(uid);
        }
        if ( priv.equals("Admin") || priv.equals("admin") ) {
            AdminView adv = new AdminView(uid);
        }


    }
    
}

