package ru.smirnygatotoshka.caseapp.Controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import ru.smirnygatotoshka.caseapp.DataRepresentation.User;
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.RegistratorARM;

import java.io.IOException;
import java.net.Authenticator;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutorisationController {
    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    protected void startLoginClick(){
        try {
            boolean success = User.authorisation(login.getText().strip(), password.getText());
            if (success){
                if (User.getUser().getRole() == 3){
                    try {
                        RegistratorARM registratorARM = new RegistratorARM();
                        GlobalResources.openedStages.put("Registrator_main", registratorARM);
                        GlobalResources.openedStages.get("Auth").hide();
                    }
                    catch (IOException e){
                        GlobalResources.alert(Alert.AlertType.ERROR,"Не могу открыть АРМ - " + e.getLocalizedMessage());
                    }
                }
                else {
                    GlobalResources.alert(Alert.AlertType.INFORMATION,"Успех!");
                }
            }
            else {
                GlobalResources.alert(Alert.AlertType.WARNING,"Неверный логин или пароль");

            }
        }
        catch (SQLException e) {
            GlobalResources.alert(Alert.AlertType.ERROR,"Возникла ошибка БД - " + e.getLocalizedMessage());

        }
    }

    @FXML
    protected void setShowingPassword(){

    }
}