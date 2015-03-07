
public class HealthInformationSystem {

    public static void main(String[] args) {
        String hme_vals[] = new String[]{"pat2", "prov", "patrole", "give", "fam", "suf", "gen", "birth", "xml"};
        PatientEntity p = new PatientEntity(hme_vals);
        p.run();

        GuardianEntity g = new GuardianEntity();
        g.run();

        AuthorEntity at = new AuthorEntity();
        at.run();

        InsuranceCompanyEntity ic = new InsuranceCompanyEntity();
        ic.run();
        
        FamilyHistoryEntity fh = new FamilyHistoryEntity();
        fh.run();
        
        LabTestReportEntity ltr = new LabTestReportEntity();
        ltr.run();
        
        AllergyEntity al = new AllergyEntity();
        al.run();
    }
    
}

