package ru.smirnygatotoshka.caseapp.DataRepresentation;

import javafx.scene.control.Alert;
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.GlobalResources;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String login;
    private byte role;

    private static User user;

    private User() {
    }

    public static User getUser() {
        if (user == null){
            user = new User();
        }
        return user;
    }

    private User(int id, String login, byte role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }

    public static boolean authorisation(String login,String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String q = "SELECT id,user_login,role FROM tbl_Users WHERE user_login = ? AND user_password = ?;";
            preparedStatement = Database.getConnection().prepareStatement(q);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            ResultSet result = preparedStatement.executeQuery();
            int i = 0;
            while (result.next()){
                user = new User(result.getInt(1),result.getString(2),result.getByte(3));
                i++;
            }
            return i == 1;
        }
        catch (Exception e){
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу авторизоваться, потому что " + e.getLocalizedMessage());

            return false;
        }
        finally {
            preparedStatement.close();
        }
    }
}
