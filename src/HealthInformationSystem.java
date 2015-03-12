
public class HealthInformationSystem {

    public static void main(String[] args) {
        RunSQL r = new RunSQL();
        r.run("healthInformationSystem.sql");
        
        String hme_vals[] = new String[]{"pat2", "prov", "guard", "give", null, "suf", "gen", "birth", "last", "xml"};
        PatientEntity pat = new PatientEntity(hme_vals);
        pat.run();

        GuardianEntity grd = new GuardianEntity();
        grd.run();

        AuthorEntity aut = new AuthorEntity();
        aut.run();

        InsuranceCompanyEntity icom = new InsuranceCompanyEntity();
        icom.run();

        FamilyHistoryEntity fhist = new FamilyHistoryEntity();
        fhist.run();

        LabTestReportEntity ltr = new LabTestReportEntity();
        ltr.run();

        AllergyEntity alrg = new AllergyEntity();
        alrg.run();

        PlanEntity pln = new PlanEntity();
        pln.run();

        VisitsLabRelation vlr = new VisitsLabRelation();
        vlr.run();

        HasInsuranceCompanyRelation hicr = new HasInsuranceCompanyRelation();
        hicr.run();

        HasAuthorRelation har = new HasAuthorRelation();
        har.run();

        HasPlanRelation hpr = new HasPlanRelation();
        hpr.run();


//        pat.retrievePatient( "pat2" );
//        System.out.println(pat.getGender());
//        System.out.println(pat.getPatientId());
//        System.out.println(pat.getProviderId());
//
//        System.out.println();
//        grd.retrieveGuardian("guard");
//        System.out.println(grd.getPatientid());
//        System.out.println(grd.getGuardianno());

        PatientView pV = new PatientView("pat2asdfasfg");

        PatientView pV2 = new PatientView("pat2");

    }
    
}

