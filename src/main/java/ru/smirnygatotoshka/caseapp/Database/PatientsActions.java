package ru.smirnygatotoshka.caseapp.Database;

import ru.smirnygatotoshka.caseapp.DataRepresentation.Passport;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Police;

public class PatientsActions {

    private static Patient patient;
    private static Passport passport;
    private static Police police;

    public static void setPatient(Patient patient) {
        PatientsActions.patient = patient;
    }

    public static void setPassport(Passport passport) {
        PatientsActions.passport = passport;
    }

    public static void setPolice(Police police) {
        PatientsActions.police = police;
    }

    public static void add(){

    }

    public static void edit(){

    }

    public static void delete(){

    }


}
