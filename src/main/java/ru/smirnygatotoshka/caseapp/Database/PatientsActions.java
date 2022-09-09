package ru.smirnygatotoshka.caseapp.Database;

import ru.smirnygatotoshka.caseapp.DataRepresentation.Passport;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Police;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        Connection con = Database.getConnection();
        try {
            con.setAutoCommit(false);
            if (Database.isAbsencePatient(patient,passport,police)){
                //TODO quieries
                String q_i_pass = "INSERT INTO tbl_Passports (Number, Address) VALUES (?,?);";
                String q_i_pol = "INSERT INTO tbl_Police (Number, Organization) VALUES (?,?);";//Not work
                String q_i_pat = "INSERT INTO tbl_Patients (Sirname, Name, SecondName, Sex, Birthday, Priviledge, Employment," +
                        "Workplace, Passport, Snils, Police, FamilyStatus, Telephone) VALUES (?,?);"; // Not work

                PreparedStatement statement = con.prepareStatement(q_i_pass);
                statement.setString(1, passport.getNumber());
                statement.setString(2, passport.getAddress());
                ResultSet rs = statement.executeQuery();

                PreparedStatement statement1 = con.prepareStatement(q_i_pol);
                statement1.setString(1, police.getNumber());
                statement1.setString(2, police.getOrganization());
                ResultSet rs1 = statement1.executeQuery();


                con.commit();
            }
            else throw new SQLException("Пациент и/или его документы уже существуют в системе. Проверьте БД и правильность ведения данных.");
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void edit(Patient whom){
//TODO quieries
    }

    public static void delete(Patient whom){
//TODO quieries
    }




}
