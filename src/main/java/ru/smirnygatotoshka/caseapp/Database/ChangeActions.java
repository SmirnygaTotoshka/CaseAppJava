package ru.smirnygatotoshka.caseapp.Database;

import javafx.scene.control.Alert;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Change;
import ru.smirnygatotoshka.caseapp.GlobalResources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeActions {
    public static void add(Change selectedChange){
        Connection con = Database.getConnection();
        try {
            con.setAutoCommit(false);
            if (isAbsenceChange(selectedChange)){


                String query = "INSERT INTO tbl_DoctorChanges (Doctor, Date, StartTime, FinishTime) VALUES (?,?,?,?);";

                PreparedStatement statement = con.prepareStatement(query);
                statement.setInt(1, selectedChange.getDoctor());
                statement.setDate(2, selectedChange.getDate());
                statement.setTime(3, selectedChange.getStartTime());
                statement.setTime(4, selectedChange.getFinishTime());
                int rows = statement.executeUpdate();

                con.commit();

                GlobalResources.alert(Alert.AlertType.INFORMATION, "Смена успешно добавлена.");
            }
            else throw new SQLException("Смена уже зарегистрирована.");
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

    private static boolean isAbsenceChange(Change selectedChange) throws SQLException {
        String query = "SELECT Count(ID) FROM tbl_DoctorChanges WHERE " +
                "Doctor = ? AND Date = ? AND StartTime = ? AND FinishTime = ?;";
        PreparedStatement statement = Database.getConnection().prepareStatement(query);
        statement.setInt(1, selectedChange.getDoctor());
        statement.setDate(2, selectedChange.getDate());
        statement.setTime(3, selectedChange.getStartTime());
        statement.setTime(4, selectedChange.getFinishTime());
        ResultSet rs = statement.executeQuery();
        int count = 0;
        if (rs.next()){
            count = rs.getInt(1);
        }
        return count == 0;
    }

    public static void edit(Change oldChange, Change newChange){
        Connection con = Database.getConnection();
        try {
            con.setAutoCommit(false);

            String query = "UPDATE tbl_DoctorChanges SET Doctor = ?, `Date` = ?, StartTime = ?, FinishTime = ? " +
                    "WHERE Doctor = ? AND `Date` = ? AND StartTime = ? AND FinishTime = ?;";

            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, newChange.getDoctor());
            statement.setDate(2, newChange.getDate());
            statement.setTime(3, newChange.getStartTime());
            statement.setTime(4, newChange.getFinishTime());

            statement.setInt(5, oldChange.getDoctor());
            statement.setDate(6, oldChange.getDate());
            statement.setTime(7, oldChange.getStartTime());
            statement.setTime(8, oldChange.getFinishTime());
            int rows = statement.executeUpdate();

            con.commit();
            GlobalResources.alert(Alert.AlertType.INFORMATION, "Смена успешно изменена.");
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

    public static void delete(Change selectedChange){
        Connection con = Database.getConnection();
        try {
            con.setAutoCommit(false);

            String query = "DELETE FROM tbl_DoctorChanges " +
                    "WHERE Doctor = ? AND `Date` = ? AND StartTime = ? AND FinishTime = ?;";

            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, selectedChange.getDoctor());
            statement.setDate(2, selectedChange.getDate());
            statement.setTime(3, selectedChange.getStartTime());
            statement.setTime(4, selectedChange.getFinishTime());
            int rows = statement.executeUpdate();

            con.commit();
            GlobalResources.alert(Alert.AlertType.INFORMATION, "Смена успешно удалена.");
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
