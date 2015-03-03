
public class HealthInformationSystem {

    public static void main(String[] args) {
        PatientEntity p = new PatientEntity();
        p.run();
        
        GuardianEntity g = new GuardianEntity();
        g.run();
        
        AuthorEntity a = new AuthorEntity();
        a.run();
    }
}

