package ru.smirnygatotoshka.caseapp.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Passport;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Reference;
import ru.smirnygatotoshka.caseapp.GlobalResources;

import java.sql.*;
import java.util.HashMap;

import static java.sql.DriverManager.*;

/**
 * TODO connections pooling
 * */
public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/ambulatoryCases";
    private static final String user = "stotoshka";
    private static final String password = "meowmeow";

    // JDBC variables for opening and managing connection
    private static Connection con;

    public static Connection getConnection() {
        return con;
    }

    static {
        try {
            con = DriverManager.getConnection(url,user, password);
        } catch (SQLException e) {
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу подключиться к БД, потому что " + e.getMessage() );

        }
    }

    public static ResultSet execute(String query)throws SQLException{
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
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
                System.out.println("Cannot close patients stmt, because " + e.getMessage());
            }
            return references;
        }
    }
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
