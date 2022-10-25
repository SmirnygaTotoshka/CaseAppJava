package ru.smirnygatotoshka.caseapp.Database;

import javafx.scene.control.Alert;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Doctor;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.DataRepresentation.ScheduleItem;
import ru.smirnygatotoshka.caseapp.GlobalResources;

import java.sql.*;

public class ScheduleActions {

    public static void edit(ScheduleItem item, Patient patient){
        Connection con = Database.getConnection();
        try {


            int id_patient = Database.getPatientId(patient);
            if (!hasVisit(item,id_patient)) {
                con.setAutoCommit(false);
                String query2 = "UPDATE tbl_Schedule SET Patient = ? WHERE Doctor = ? AND `Change` = ? AND `Time` = ?;";

                PreparedStatement statement2 = con.prepareStatement(query2);
                statement2.setInt(1, id_patient);
                statement2.setInt(2, item.getDoctor());
                statement2.setInt(3, item.getChange());
                statement2.setTime(4, item.getTime());
                int rows2 = statement2.executeUpdate();

            }
            else throw new SQLException("Данный пациент уже записан сегодня на прием.");

            con.commit();
            GlobalResources.alert(Alert.AlertType.INFORMATION, "Пациент записан.");
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

    private static boolean hasVisit(ScheduleItem item, int id_patient) {
        Connection con = Database.getConnection();
        boolean flag = false;
        try {//TODO
            con.setAutoCommit(false);
            String query1 = "SELECT Date FROM tbl_DoctorChanges WHERE ID = ?;";
            String query2 = "SELECT Count(ID) FROM tbl_Schedule WHERE Patient = ? AND " +
                    "`Change` IN (SELECT ID FROM tbl_DoctorChanges WHERE Date = ?);";

            PreparedStatement statement = con.prepareStatement(query1);
            statement.setInt(1, item.getChange());
            ResultSet rs1 = statement.executeQuery();
            Date date;
            if (rs1.next()){
                date = rs1.getDate(1);
            }
            else throw new SQLException("Смена не найдена на указанную дату.");

            PreparedStatement statement1 = con.prepareStatement(query2);
            statement1.setInt(1, id_patient);
            statement1.setDate(2, date);
            ResultSet rs2 = statement1.executeQuery();
            if (rs2.next()){
                flag = rs2.getInt(1) > 0;
            }
            else throw new SQLException("Не найдено слотов для записи.");

            con.commit();
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
            return flag;
        }
    }

    public static void delete(ScheduleItem item){
        Connection con = Database.getConnection();
        try {
            con.setAutoCommit(false);
            String query2 = "UPDATE tbl_Schedule SET Patient = ? WHERE Doctor = ? AND `Change` = ? AND `Time` = ?;";

            PreparedStatement statement2 = con.prepareStatement(query2);
            statement2.setInt(1, -100);
            statement2.setInt(2, item.getDoctor());
            statement2.setInt(3, item.getChange());
            statement2.setTime(4, item.getTime());
            int rows2 = statement2.executeUpdate();

            con.commit();
            GlobalResources.alert(Alert.AlertType.INFORMATION, "Запись отменена.");
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
}
