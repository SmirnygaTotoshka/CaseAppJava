package ru.smirnygatotoshka.caseapp.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import ru.smirnygatotoshka.caseapp.DataRepresentation.*;
import ru.smirnygatotoshka.caseapp.GlobalResources;

import java.sql.*;

/**
 * TODO connections pooling
 * */
public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/ambulatoryCases";
    private static final String user = "stotoshka";
    private static final String password = "meowmeow";

    // JDBC variables for opening and managing connection
    private static Connection con;

    public synchronized static Connection getConnection() {
        return con;
    }

    static {
        try {
            con = DriverManager.getConnection(url,user, password);
        } catch (SQLException e) {
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу подключиться к БД, потому что " + e.getMessage() );

        }
    }

    /*public static ResultSet execute(String query)throws SQLException{
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }*/
/**
 * TODO not prepared statement. Not use for user searching query!
 * */
    public static ObservableList<Patient> getPatients(String query){
        ObservableList<Patient> patients = FXCollections.observableArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()){
                String sirname = rs.getString("Sirname");
                String name = rs.getString("Name");
                String secondName = rs.getString("SecondName");
                String sex = rs.getString("Sex");
                Date dob = rs.getDate("Birthday");
                String priviledge = rs.getString("Priviledge");
                String employment = rs.getString("Employment");
                String workplace = rs.getString("Workplace");
                String passport = rs.getString("Passport");
                String police = rs.getString("Police");
                String snils = rs.getString("Snils");
                String familyStatus = rs.getString("FamilyStatus");
                String telephone = rs.getString("Telephone");
                Patient patient = new Patient(sirname,name,secondName,sex,dob,priviledge,employment,workplace,passport,
                        police,snils, familyStatus,telephone);
                patients.add(patient);
            }
        }
        catch (SQLException se){
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу получить данные о пациентах из БД, потому что " + se.getMessage() );
        }
        finally {
            try{
                if (stmt != null){
                    stmt.close();
                }
                if (rs != null){
                    rs.close();
                }
            }
            catch(SQLException e){
                System.out.println("Cannot close patients stmt, because " + e.getMessage());
            }
            return patients;
        }
    }

    /**
     * TODO not prepared statement. Not use for user searching query!
     * */
    public static ObservableList<Change> getChanges(String query){
        ObservableList<Change> changes = FXCollections.observableArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()){
                int doctor = rs.getInt("Doctor");
                Date date = rs.getDate("Date");
                Time start = rs.getTime("Start_Time");
                Time finish = rs.getTime("Finish_Time");
                Change patient = new Change(doctor, date, start, finish);
                changes.add(patient);
            }
        }
        catch (SQLException se){
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу получить данные о сменах из БД, потому что " + se.getMessage() );
        }
        finally {
            try{
                if (stmt != null){
                    stmt.close();
                }
                if (rs != null){
                    rs.close();
                }
            }
            catch(SQLException e){
                System.out.println("Cannot close patients stmt, because " + e.getMessage());
            }
            return changes;
        }
    }

    /**
     * TODO not prepared statement. Not use for user searching query!
     * */
    public static ObservableList<Passport> getPassports(String query){
        ObservableList<Passport> passports = FXCollections.observableArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()){
                String number = rs.getString("Number");
                String address = rs.getString("Address");
                Passport passport = new Passport(number,address);
                passports.add(passport);
            }
        }
        catch (SQLException se){
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу получить данные о пасспортах из БД, потому что " + se.getMessage() );
        }
        finally {
            try{
                if (stmt != null){
                    stmt.close();
                }
                if (rs != null){
                    rs.close();
                }
            }
            catch(SQLException e){
                System.out.println("Cannot close patients stmt, because " + e.getMessage());
            }
            return passports;
        }
    }

    /**
     * TODO not prepared statement. Not use for user searching query!
     * */
    public static ObservableList<Police> getPolices(String query){
        ObservableList<Police> polices = FXCollections.observableArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()){
                String number = rs.getString("Number");
                String org = rs.getString("Organization");
                Police police = new Police(number,org);
                polices.add(police);
            }
        }
        catch (SQLException se){
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу получить данные о пасспортах из БД, потому что " + se.getMessage() );
        }
        finally {
            try{
                if (stmt != null){
                    stmt.close();
                }
                if (rs != null){
                    rs.close();
                }
            }
            catch(SQLException e){
                System.out.println("Cannot close patients stmt, because " + e.getMessage());
            }
            return polices;
        }
    }

    public static ObservableList<Reference> getReference(String reference_table){
        String query = "SELECT NAME FROM " + reference_table + ";";
        ObservableList<Reference> references = FXCollections.observableArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()){
                String r = rs.getString("NAME");
                Reference ref = new Reference(r);
                references.add(ref);
            }
        }
        catch (SQLException se){
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу получить данные о пациентах из БД, потому что " + se.getMessage() );
        }
        finally {
            try{
                if (stmt != null){
                    stmt.close();
                }
                if (rs != null){
                    rs.close();
                }
            }
            catch(SQLException e){
                GlobalResources.alert(Alert.AlertType.ERROR,"Cannot close patients stmt, because " + e.getMessage());
            }
            return references;
        }
    }

    static int getPrimaryKeyByValue(String spr_name, String value){
        String query = "SELECT ID, NAME FROM " + spr_name + " WHERE NAME = ?;";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, value);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return rs.getInt(1);
            }
            else{
                throw new SQLException("Value " + value + " doesn`t exist in " + spr_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static ObservableList<Doctor> getDoctors(Reference department){
        int code_dep = getPrimaryKeyByValue("spr_Departments", department.getNAME());
        String query = "SELECT Sirname,tbl_Doctors.Name as Name,SecondName,spr_Sex.NAME as Sex,Birthday," +
                "                spr_Positions.NAME as Position,spr_Speciality.NAME as Speciality," +
                "                spr_Departments.NAME as Department,Telephone FROM tbl_Doctors " +
                "INNER JOIN spr_Sex ON spr_Sex.ID = tbl_Doctors.Sex " +
                "INNER JOIN spr_Positions ON spr_Positions.ID = tbl_Doctors.Position  "+
                "INNER JOIN spr_Speciality ON spr_Speciality.ID = tbl_Doctors.Speciality " +
                "INNER JOIN spr_Departments ON spr_Departments.ID = tbl_Doctors.Department " +
                "WHERE Department = ?;";
        PreparedStatement statement = null;
        ResultSet rs = null;
        ObservableList<Doctor> doctors = FXCollections.observableArrayList();
        try {
            statement = con.prepareStatement(query);
            statement.setInt(1, code_dep);
            rs = statement.executeQuery();
            while (rs.next()){
                String sirname = rs.getString("Sirname");
                String name = rs.getString("Name");
                String secondName = rs.getString("SecondName");
                String sex = rs.getString("Sex");
                Date dob = rs.getDate("Birthday");
                String pos = rs.getString("Position");
                String spec = rs.getString("Speciality");
                String dep = rs.getString("Department");
                String tel = rs.getString("Telephone");
                Doctor doc = new Doctor(sirname,name,secondName,sex,dob,pos,spec,dep,tel);
                doctors.add(doc);
            }
        }
        catch (SQLException se){
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу получить данные о пациентах из БД, потому что " + se.getMessage() );
        }
        finally {
            try{
                if (statement != null){
                    statement.close();
                }
                if (rs != null){
                    rs.close();
                }
            }
            catch(SQLException e){
                GlobalResources.alert(Alert.AlertType.ERROR,"Cannot close patients stmt, because " + e.getMessage());
            }
            return doctors;
        }
    }

    public static int getDoctorId(Doctor doctor) {
        int code_spec = getPrimaryKeyByValue("spr_Speciality", doctor.getSpeciality());
        int code_pos = getPrimaryKeyByValue("spr_Positions", doctor.getPosition());
        int code_dep = getPrimaryKeyByValue("spr_Departments", doctor.getDepartment());
        int code_sex = getPrimaryKeyByValue("spr_Sex", doctor.getSex());
        int id = -100;

        String query = "SELECT ID FROM tbl_Doctors WHERE Sirname = ? AND Name = ? AND SecondName = ? " +
                "AND Sex = ? AND Birthday = ? AND Position = ? AND Speciality = ? AND Department = ? AND Telephone = ?;";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = con.prepareStatement(query);
            statement.setString(1, doctor.getSirname());
            statement.setString(2, doctor.getName());
            statement.setString(3, doctor.getSecondName());
            statement.setInt(4, code_sex);
            statement.setDate(5, doctor.getDob());

            statement.setInt(6, code_pos);
            statement.setInt(7, code_spec);
            statement.setInt(8, code_dep);
            statement.setString(9, doctor.getTelephone());

            rs = statement.executeQuery();
            if (rs.next())
                id = rs.getInt(1);
        }
        catch (SQLException se){
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу получить данные о врачах из БД, потому что " + se.getMessage() );
            return -1;
        }
        finally {
            try{
                if (statement != null){
                    statement.close();
                }
                if (rs != null){
                    rs.close();
                }
                return id;
            }
            catch(SQLException e){
                GlobalResources.alert(Alert.AlertType.ERROR,"Cannot close patients stmt, because " + e.getMessage());
                return -2;
            }
        }
    }

    /*public static ObservableList<Change> getChanges(Reference department){
        int code_dep = getPrimaryKeyByValue("spr_Departments", department.getNAME());
        String query = "SELECT Sirname,tbl_Doctors.Name as Name,SecondName,spr_Sex.NAME as Sex,Birthday," +
                "                spr_Positions.NAME as Position,spr_Speciality.NAME as Speciality," +
                "                spr_Departments.NAME as Department,Telephone FROM tbl_Doctors " +
                "INNER JOIN spr_Sex ON spr_Sex.ID = tbl_Doctors.Sex " +
                "INNER JOIN spr_Positions ON spr_Positions.ID = tbl_Doctors.Position  "+
                "INNER JOIN spr_Speciality ON spr_Speciality.ID = tbl_Doctors.Speciality " +
                "INNER JOIN spr_Departments ON spr_Departments.ID = tbl_Doctors.Department " +
                "WHERE Department = ?;";
        PreparedStatement statement = null;
        ResultSet rs = null;
        ObservableList<Doctor> changes = FXCollections.observableArrayList();
        try {
            statement = con.prepareStatement(query);
            statement.setInt(1, code_dep);
            rs = statement.executeQuery();
            while (rs.next()){
                String sirname = rs.getString("Sirname");
                String name = rs.getString("Name");
                String secondName = rs.getString("SecondName");
                String sex = rs.getString("Sex");
                Date dob = rs.getDate("Birthday");
                String pos = rs.getString("Position");
                String spec = rs.getString("Speciality");
                String dep = rs.getString("Department");
                String tel = rs.getString("Telephone");
                Doctor doc = new Doctor(sirname,name,secondName,sex,dob,pos,spec,dep,tel);
                changes.add(doc);
            }
        }
        catch (SQLException se){
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу получить данные о пациентах из БД, потому что " + se.getMessage() );
        }
        finally {
            try{
                if (statement != null){
                    statement.close();
                }
                if (rs != null){
                    rs.close();
                }
            }
            catch(SQLException e){
                GlobalResources.alert(Alert.AlertType.ERROR,"Cannot close patients stmt, because " + e.getMessage());
            }
            return changes;
        }
    }*/

  /*  public static void addPatient(Patient patient,Passport passport, Police police){
        try {//TODO
            con.setAutoCommit(false);
            if (isAbsencePatient(patient,passport,police)){
                String q_i_pass = "INSERT INTO tbl_Passports (Number, Address) VALUES (?,?);";
                String q_i_pol = "INSERT INTO tbl_Police (Number, Organization) VALUES (?,?);";
                String q_i_pat = "INSERT INTO tbl_Patients (Sirname, Name, SecondName, Sex, Birthday, Priviledge, Employment," +
                        "Workplace,";

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
    }*/


    /*public ResultSet execute(String query, Object[] params)throws SQLException{
        PreparedStatement preparedStatement = con.prepareStatement(query);
        for (Object o: params){
            preparedStatement.set
        }
        ResultSet rs = stmt.executeQuery(query);
        try{
            stmt.close();
            rs.close();
        }
        catch (SQLException se){
            throw new SQLException(se);
        }
        finally {
            return rs;
        }
    }*/
}
