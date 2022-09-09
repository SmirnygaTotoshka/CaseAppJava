package ru.smirnygatotoshka.caseapp.Database;

import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Passport;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Police;
import ru.smirnygatotoshka.caseapp.GlobalResources;

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
            if (isAbsencePatient(patient,passport,police)){
                //TODO quieries
                int organization = Database.getPrimaryKeyByValue("spr_SMO", police.getOrganization());
                int sex = Database.getPrimaryKeyByValue("spr_Sex", patient.getSex());
                int priviledge = Database.getPrimaryKeyByValue("spr_Priviledge", patient.getPriviledge());
                int employment = Database.getPrimaryKeyByValue("spr_Employment", patient.getEmployment());
                int family_status = Database.getPrimaryKeyByValue("spr_FamilyStatus", patient.getFamilyStatus());

                String q_i_pass = "INSERT INTO tbl_Passports (Number, Address) VALUES (?,?);";
                String q_i_pol = "INSERT INTO tbl_Polices (Number, Organization) VALUES (?,?);";

                int pass = -1;
                int pol = -1;

                String q_i_pat = "INSERT INTO tbl_Patients (Sirname, Name, SecondName, Sex, Birthday, Priviledge, Employment," +
                        "Workplace, Passport, Snils, Police, FamilyStatus, Telephone) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";

                PreparedStatement statement = con.prepareStatement(q_i_pass);
                statement.setString(1, passport.getNumber());
                statement.setString(2, passport.getAddress());
                int rows = statement.executeUpdate();

                PreparedStatement statement1 = con.prepareStatement(q_i_pol);
                statement1.setString(1, police.getNumber());
                statement1.setInt(2, organization);
                int rows1 = statement1.executeUpdate();

                PreparedStatement stat_pass = con.prepareStatement("SELECT ID, Number FROM tbl_Passports WHERE Number = ?;");
                stat_pass.setString(1, passport.getNumber());
                ResultSet rs = stat_pass.executeQuery();
                if (rs.next()){
                    pass = rs.getInt(1);
                }
                else {
                    throw new SQLException("Couldnt find the passport " + passport);
                }

                PreparedStatement stat_pol = con.prepareStatement("SELECT ID, Number FROM tbl_Polices WHERE Number = ?;");
                stat_pol.setString(1, police.getNumber());
                ResultSet rs1 = stat_pol.executeQuery();
                if (rs1.next()){
                    pol = rs1.getInt(1);
                }
                else {
                    throw new SQLException("Couldnt find the police " + police);
                }

                PreparedStatement statement2 = con.prepareStatement(q_i_pat);
                statement2.setString(1, patient.getSirname());
                statement2.setString(2, patient.getName());
                statement2.setString(3, patient.getSecondName());
                statement2.setInt(4, sex);
                statement2.setDate(5, patient.getDob());
                statement2.setInt(6, priviledge);
                statement2.setInt(7, employment);
                statement2.setString(8, patient.getWorkplace());
                statement2.setInt(9, pass);
                statement2.setString(10, patient.getSnils());
                statement2.setInt(11, pol);
                statement2.setInt(12, family_status);
                statement2.setString(13, patient.getTelephone());
                int rows2 = statement2.executeUpdate();

                con.commit();

                GlobalResources.alert(Alert.AlertType.INFORMATION, "Пациент успешно прикреплен.");
            }
            else throw new SQLException("Пациент и/или его документы уже существуют в системе. Проверьте БД и правильность ведения данных.");
        } catch (SQLException e) {
            try {
                e.printStackTrace();
                GlobalResources.alert(Alert.AlertType.ERROR, "SQL Error " + e.getErrorCode() + ". State = " + e.getSQLState() + ". Message = " + e.getMessage());
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

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


    static boolean isAbsencePatient(Patient patient,Passport passport, Police police) throws SQLException {
        String query = "SELECT Count(Sirname) FROM tbl_Patients WHERE (Sirname = ? AND Name= ? AND SecondName = ?) AND Snils = ?;";
        PreparedStatement statement = Database.getConnection().prepareStatement(query);
        statement.setString(1, patient.getSirname());
        statement.setString(2, patient.getName());
        statement.setString(3, patient.getSecondName());
        statement.setString(4, patient.getSnils());
        ResultSet rs = statement.executeQuery();
        int count = 0;
        if (rs.next()){
            count = rs.getInt(1);
        }
        return count == 0 & isAbsencePolice(police) & isAbsencePassport(passport);

    }

    static boolean isAbsencePassport(Passport passport) throws SQLException {
        String query = "SELECT Count(Number) FROM tbl_Passports WHERE Number = ?;";
        PreparedStatement statement = Database.getConnection().prepareStatement(query);
        statement.setString(1, passport.getNumber());
        ResultSet rs = statement.executeQuery();
        int count = 0;
        if (rs.next()){
            count = rs.getInt(1);
        }
        return count == 0;

    }

    static boolean isAbsencePolice(Police police) throws SQLException {
        String query = "SELECT Count(Number) FROM tbl_Polices WHERE Number = ?;";
        PreparedStatement statement = Database.getConnection().prepareStatement(query);
        statement.setString(1, police.getNumber());
        ResultSet rs = statement.executeQuery();
        int count = 0;
        if (rs.next()){
            count = rs.getInt(1);
        }
        return count == 0;

    }



}
