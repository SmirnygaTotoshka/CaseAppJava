package ru.smirnygatotoshka.caseapp.Database;

import javafx.collections.ObservableList;
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

public class PatientsActions{

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
        catch (Exception e) {
            try {
                e.printStackTrace();
                GlobalResources.alert(Alert.AlertType.ERROR, "Error " + e.getMessage());
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
        Connection con = Database.getConnection();
        try {
            con.setAutoCommit(false);
            int organization = Database.getPrimaryKeyByValue("spr_SMO", police.getOrganization());
            int sex = Database.getPrimaryKeyByValue("spr_Sex", patient.getSex());
            int priviledge = Database.getPrimaryKeyByValue("spr_Priviledge", patient.getPriviledge());
            int employment = Database.getPrimaryKeyByValue("spr_Employment", patient.getEmployment());
            int family_status = Database.getPrimaryKeyByValue("spr_FamilyStatus", patient.getFamilyStatus());

            String q_i_pass = "UPDATE tbl_Passports SET Number = ?, Address = ? WHERE Number = ?;";
            String q_i_pol = "UPDATE tbl_Polices SET Number = ?, Organization = ? WHERE Number = ?;";

            int pass = -1;
            int pol = -1;

            String q_i_pat = "UPDATE tbl_Patients SET Sirname = ?, Name = ?, SecondName = ?, Sex = ?, Birthday = ?, Priviledge = ?, Employment = ?," +
                        "Workplace = ?, Passport = ?, Snils = ?, Police = ?, FamilyStatus = ?, Telephone = ? WHERE Passport = ? AND Snils = ? AND Police = ?;";



            int whom_pass = -1;
            int whom_pol = -1;

            PreparedStatement whom_pass_query = con.prepareStatement("SELECT ID, Number FROM tbl_Passports WHERE Number = ?;");
            whom_pass_query.setString(1, whom.getPassport());
            ResultSet rs2 = whom_pass_query.executeQuery();
            if (rs2.next()){
                whom_pass = rs2.getInt(1);
            }
            else {
                throw new SQLException("Couldnt find the passport " + whom.getPassport());
            }

            PreparedStatement whom_pol_query = con.prepareStatement("SELECT ID, Number FROM tbl_Polices WHERE Number = ?;");
            whom_pol_query.setString(1, whom.getPolice());
            ResultSet rs3 = whom_pol_query.executeQuery();
            if (rs3.next()){
                whom_pol = rs3.getInt(1);
            }
            else {
                throw new SQLException("Couldnt find the police " + whom.getPolice());
            }

            PreparedStatement statement = con.prepareStatement(q_i_pass);
            statement.setString(1, passport.getNumber());
            statement.setString(2, passport.getAddress());
            statement.setString(3, whom.getPassport());
            int rows = statement.executeUpdate();

            PreparedStatement statement1 = con.prepareStatement(q_i_pol);
            statement1.setString(1, police.getNumber());
            statement1.setInt(2, organization);
            statement1.setString(3, whom.getPolice());
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
            statement2.setInt(14, whom_pass);
            statement2.setString(15, whom.getSnils());
            statement2.setInt(16, whom_pol);
            int rows2 = statement2.executeUpdate();

            con.commit();
            GlobalResources.alert(Alert.AlertType.INFORMATION, "Данные изменены.");
        } catch (SQLException e) {
            try {
                e.printStackTrace();
                GlobalResources.alert(Alert.AlertType.ERROR, "SQL Error " + e.getErrorCode() + ". State = " + e.getSQLState() + ". Message = " + e.getMessage());
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        catch (Exception e) {
            try {
                e.printStackTrace();
                GlobalResources.alert(Alert.AlertType.ERROR, "Error " + e.getMessage());
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

    public static void delete(Patient whom){
        Connection con = Database.getConnection();
        try {
            setPatient(whom);
            con.setAutoCommit(false);

            int whom_pass = -1;
            int whom_pol = -1;

            PreparedStatement whom_pass_query = con.prepareStatement("SELECT ID, Number FROM tbl_Passports WHERE Number = ?;");
            whom_pass_query.setString(1, whom.getPassport());
            ResultSet rs2 = whom_pass_query.executeQuery();
            if (rs2.next()){
                whom_pass = rs2.getInt(1);
            }
            else {
                throw new SQLException("Couldnt find the passport " + whom.getPassport());
            }

            PreparedStatement whom_pol_query = con.prepareStatement("SELECT ID, Number FROM tbl_Polices WHERE Number = ?;");
            whom_pol_query.setString(1, whom.getPolice());
            ResultSet rs3 = whom_pol_query.executeQuery();
            if (rs3.next()){
                whom_pol = rs3.getInt(1);
            }
            else {
                throw new SQLException("Couldnt find the police " + whom.getPolice());
            }


            int sex = Database.getPrimaryKeyByValue("spr_Sex", patient.getSex());
            int priviledge = Database.getPrimaryKeyByValue("spr_Priviledge", patient.getPriviledge());
            int employment = Database.getPrimaryKeyByValue("spr_Employment", patient.getEmployment());
            int family_status = Database.getPrimaryKeyByValue("spr_FamilyStatus", patient.getFamilyStatus());



            String q_i_pat = "DELETE FROM tbl_Patients WHERE Sirname = ? AND Name = ? AND  SecondName = ? AND Sex = ? AND Birthday = ? AND Priviledge = ? AND Employment = ? AND " +
                    "Workplace = ? AND Passport = ? AND Snils = ? AND Police = ? AND FamilyStatus = ? AND Telephone = ?;";


            PreparedStatement statement2 = con.prepareStatement(q_i_pat);
            statement2.setString(1, patient.getSirname());
            statement2.setString(2, patient.getName());
            statement2.setString(3, patient.getSecondName());
            statement2.setInt(4, sex);
            statement2.setDate(5, patient.getDob());
            statement2.setInt(6, priviledge);
            statement2.setInt(7, employment);
            statement2.setString(8, patient.getWorkplace());
            statement2.setInt(9, whom_pass);
            statement2.setString(10, patient.getSnils());
            statement2.setInt(11, whom_pol);
            statement2.setInt(12, family_status);
            statement2.setString(13, patient.getTelephone());
            int rows2 = statement2.executeUpdate();


            String q_i_pass = "DELETE FROM tbl_Passports WHERE ID = ?;";
            String q_i_pol = "DELETE FROM tbl_Polices WHERE ID = ?;";

            PreparedStatement statement = con.prepareStatement(q_i_pass);
            statement.setInt(1, whom_pass);
            int rows = statement.executeUpdate();

            PreparedStatement statement1 = con.prepareStatement(q_i_pol);
            statement1.setInt(1,whom_pol);
            int rows1 = statement1.executeUpdate();


            con.commit();
            GlobalResources.alert(Alert.AlertType.INFORMATION, "Пациент откреплен.");
        } catch (SQLException e) {
            try {
                e.printStackTrace();
                GlobalResources.alert(Alert.AlertType.ERROR, "SQL Error " + e.getErrorCode() + ". State = " + e.getSQLState() + ". Message = " + e.getMessage());
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        catch (Exception e) {
            try {
                e.printStackTrace();
                GlobalResources.alert(Alert.AlertType.ERROR, "Error " + e.getMessage());
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
        if (count == 0 && (!isAbsencePolice(police) || !isAbsencePassport(passport)))
            throw new SQLException("Паспорт " + passport.getNumber() + " или полис " + police.getNumber() + " закреплены за другим пациентом.");
        return count == 0 && isAbsencePolice(police) && isAbsencePassport(passport);

    }

    public static boolean isAbsencePassport(Passport passport) throws SQLException {
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

    public static boolean isAbsencePolice(Police police) throws SQLException {
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

    public static Passport getPassportByNumber(String number){
        String query = "SELECT Number,Address FROM tbl_Passports WHERE Number = " + number + ";";
        ObservableList<Passport> passports = Database.getPassports(query);
        if (passports.size() == 0) {
            GlobalResources.alert(Alert.AlertType.WARNING, "Паспорт не найден.");
        }
        else if (passports.size() == 1) {
            return passports.get(0);
        }
        else if (passports.size() > 1) {
            GlobalResources.alert(Alert.AlertType.WARNING, "В БД несколько одинаковых паспортов! Проверьте БД!");
        }
        return null;
    }

    public static Police getPoliceByNumber(String number){
        String query = "SELECT Number,spr_SMO.NAME as Organization FROM tbl_Polices " +
                "INNER JOIN spr_SMO ON spr_SMO.ID = tbl_Polices.Organization " +
                "WHERE Number = " + number + ";";
        ObservableList<Police> polices = Database.getPolices(query);
        if (polices.size() == 0) {
            GlobalResources.alert(Alert.AlertType.WARNING, "Паспорт не найден.");
        }
        else if (polices.size() == 1) {
            return polices.get(0);
        }
        else if (polices.size() > 1) {
            GlobalResources.alert(Alert.AlertType.WARNING, "В БД несколько одинаковых паспортов! Проверьте БД!");
        }
        return null;
    }


}
