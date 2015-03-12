
public class HealthInformationSystem {

    public static void main(String[] args) {
        String hme_vals[] = new String[]{"pat2", "prov", "patrole", "give", null, "suf", "gen", "birth", "xml"};
        PatientEntity pat = new PatientEntity(hme_vals);
        pat.run();

//        GuardianEntity grd = new GuardianEntity();
//        grd.run();
//
//        AuthorEntity aut = new AuthorEntity();
//        aut.run();
//
//        InsuranceCompanyEntity icom = new InsuranceCompanyEntity();
//        icom.run();
//
//        FamilyHistoryEntity fhist = new FamilyHistoryEntity();
//        fhist.run();
//
//        LabTestReportEntity ltr = new LabTestReportEntity();
//        ltr.run();
//
//        AllergyEntity alrg = new AllergyEntity();
//        alrg.run();
//
//        PlanEntity pln = new PlanEntity();
//        pln.run();
    }
    
}

