package ru.smirnygatotoshka.caseapp.Database;

import javafx.scene.control.Alert;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Change;
import ru.smirnygatotoshka.caseapp.GlobalResources;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ChangeActions {

    private static final Time[] morningTimes, eveningTimes;
    private static final Time change_border;
    static {
        Time change_border1;
        Time[] morningTimes1 = new Time[24];
        Time[] eveningTimes1 = new Time[24];
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            change_border1 = new Time(format.parse("14:20").getTime());
            morningTimes1 = new Time[]{
                    new Time(format.parse("08:00").getTime()), new Time(format.parse("08:15").getTime()),
                    new Time(format.parse("08:30").getTime()), new Time(format.parse("08:45").getTime()),
                    new Time(format.parse("09:00").getTime()), new Time(format.parse("09:15").getTime()),
                    new Time(format.parse("09:30").getTime()), new Time(format.parse("09:45").getTime()),
                    new Time(format.parse("10:00").getTime()), new Time(format.parse("10:15").getTime()),
                    new Time(format.parse("10:30").getTime()), new Time(format.parse("10:45").getTime()),
                    new Time(format.parse("11:00").getTime()), new Time(format.parse("11:15").getTime()),
                    new Time(format.parse("11:30").getTime()), new Time(format.parse("11:45").getTime()),
                    new Time(format.parse("12:00").getTime()), new Time(format.parse("12:15").getTime()),
                    new Time(format.parse("12:30").getTime()), new Time(format.parse("12:45").getTime()),
                    new Time(format.parse("13:00").getTime()), new Time(format.parse("13:15").getTime()),
                    new Time(format.parse("13:30").getTime()), new Time(format.parse("13:45").getTime())};
            eveningTimes1 = new Time[]{
                    new Time(format.parse("14:30").getTime()), new Time(format.parse("14:45").getTime()),
                    new Time(format.parse("15:00").getTime()), new Time(format.parse("15:15").getTime()),
                    new Time(format.parse("15:30").getTime()), new Time(format.parse("15:45").getTime()),
                    new Time(format.parse("16:00").getTime()), new Time(format.parse("16:15").getTime()),
                    new Time(format.parse("16:30").getTime()), new Time(format.parse("16:45").getTime()),
                    new Time(format.parse("17:00").getTime()), new Time(format.parse("17:15").getTime()),
                    new Time(format.parse("17:30").getTime()), new Time(format.parse("17:45").getTime()),
                    new Time(format.parse("18:00").getTime()), new Time(format.parse("18:15").getTime()),
                    new Time(format.parse("18:30").getTime()), new Time(format.parse("18:45").getTime()),
                    new Time(format.parse("19:00").getTime()), new Time(format.parse("19:15").getTime()),
                    new Time(format.parse("19:30").getTime()), new Time(format.parse("19:45").getTime()),
                    new Time(format.parse("20:00").getTime()), new Time(format.parse("20:15").getTime())};
        } catch (ParseException e) {
            e.printStackTrace();
            change_border1 = new Time(0L);
        }
        change_border = change_border1;
        morningTimes = morningTimes1;
        eveningTimes = eveningTimes1;

    }


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

                String query1 = "SELECT ID FROM tbl_DoctorChanges WHERE Doctor = ? AND Date = ? AND StartTime  = ? AND FinishTime = ?;";

                PreparedStatement statement1 = con.prepareStatement(query1);
                statement1.setInt(1, selectedChange.getDoctor());
                statement1.setDate(2, selectedChange.getDate());
                statement1.setTime(3, selectedChange.getStartTime());
                statement1.setTime(4, selectedChange.getFinishTime());
                ResultSet set1 = statement1.executeQuery();

                int id = -1;
                if (set1.next())
                    id = set1.getInt(1);

                if (id == -1) throw new SQLException("Смена не обнаружена, не могу добавить расписание.");

                Time[] time = selectedChange.getStartTime().before(change_border) ? morningTimes : eveningTimes;
                String query2 = "INSERT INTO tbl_Schedule (Doctor, `Change`, `Time`) VALUES (?,?,?);";

                for (Time t : time) {

                    PreparedStatement statement2 = con.prepareStatement(query2);
                    statement2.setInt(1, selectedChange.getDoctor());
                    statement2.setInt(2, id);
                    statement2.setTime(3, t);
                    int rows2 = statement2.executeUpdate();
                }
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
            /**
             * TODO
             * Проверка на то, что мы не вносим существующую смену
             * Редактирование времени смены
             *
             * */
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
            /**
             * TODO
             * Удаление времени ассоциированного со сменой
             * */
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

    public static ArrayList<Integer> getDoctorScheduleInDate(Date date, int doctor){
        ArrayList<Integer> list = new ArrayList<>();
        String query = "SELECT ID From tbl_DoctorChanges " +
                "WHERE Date = ? AND Doctor = ?;";

        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(query);
            statement.setDate(1, date);
            statement.setInt(2, doctor);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                list.add(rs.getInt("ID"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return list;
        }
    }
}
